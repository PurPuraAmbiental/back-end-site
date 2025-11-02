package com.purpura.servlet.administrador;

import com.purpura.common.ErroServlet;
import com.purpura.common.Regex;
import com.purpura.dao.AdministradorDAO;
import com.purpura.dao.DAO;
import com.purpura.exception.ConnectionFailedException;
import com.purpura.exception.NotFoundException;
import com.purpura.model.Administrador;
import com.purpura.model.Empresa;
import com.purpura.util.Criptografia;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.purpura.common.Constants.ERROR_PAGE;

/**
 * Servlet responsável por inserir um novo Administrador no sistema.
 *
 * Recebe dados via POST, valida e-mail e senha, criptografa a senha
 * e salva o administrador no banco. Dependendo do parâmetro "origem",
 * redireciona para a lista de administradores ou adiciona o usuário à sessão.
 *
 *
 * =============== ESTA CLASSE FAZ USO DE REGEX ===============================
 *
 * @author Bruna de Jesus e Kevin de Oliveira
 */
@WebServlet(name = "InsertAdministradorServlet", value = "/administrador/insert")
public class InsertAdministradorServlet extends HttpServlet {
    /**
     * Processa o POST para inserir um Administrador.
     *
     * @param request  objeto HttpServletRequest contendo os parâmetros do formulário
     * @param response objeto HttpServletResponse para redirecionamento ou forward
     * @throws jakarta.servlet.ServletException se ocorrer erro no servlet
     * @throws IOException                      se ocorrer erro de I/O
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws jakarta.servlet.ServletException, IOException {
        // Em caso de erro, essas variáveis são usadas para repassar informações de contexto
        DAO<Administrador> dao = new AdministradorDAO();
        String caminho = "/WEB-INF/CRUD/administrador.jsp";
        String lista = "listaAdministradores";
        try {
            // Cria um mapa para armazenar os parâmetros enviados pelo formulário
            // O request.getParameterMap() retorna um Map<String, String[]>, então pegamos o primeiro valor de cada campo.
            Map<String, String> params = new LinkedHashMap<>();
            request.getParameterMap().forEach((key, values) -> params.put(key, values[0]));

            // Cria um objeto 'Administrador' a partir dos parâmetros do formulário.
            Administrador model = new Administrador(params);

            // ==================== VALIDAÇÕES DE DOS DADOS ====================

            // Verifica se o e-mail digitado possui formato válido usando uma expressão regular (Regex).
            if (!Regex.validarEmail(model.getCEmail())) {
                // Se o e-mail for inválido, define uma mensagem de erro e retorna para a página de administrador.jsp.
                ErroServlet.setErro(request, response, dao, "Insira um E-mail valido", lista, caminho);
                return; // interrompe a continuidade do servlet
            }

            // Verifica se já existe um administrador cadastrado com o mesmo e-mail.
            else if (dao.findById(model.getCEmail()) != null) {
                // Caso exista, mostra uma mensagem de erro e interrompe o processo.
                ErroServlet.setErro(request, response, dao,
                        "E-mail ja cadastrado anteriormente", lista, caminho);
                return;
            }

            // Verifica se a senha possui pelo menos 6 caracteres.
            else if (model.getCSenha().length() < 6) {
                // Se for menor, exibe mensagem de erro e interrompe.
                ErroServlet.setErro(request, response, dao,
                        "A senha deve possuir no minimo 6 caracteres", lista, caminho);
                return;
            }

            // Caso todas as validações passem:
            else {
                // Verifica se o parâmetro 'cSenha' (campo de senha) existe.
                if (params.containsKey("cSenha")) {
                    // Criptografa a senha utilizando a classe utilitária Criptografia.
                    String hash = Criptografia.criptografar(params.get("cSenha"));

                    // Substitui a senha original pelo hash no mapa.
                    params.put("cSenha", hash);

                    // Atualiza também o objeto 'model' com a senha criptografada.
                    model.setCSenha(params.get("cSenha"));
                }
            }

            // ==================== SALVAMENTO NO BANCO ====================

            // Após passar nas validações e criptografar a senha, salva o administrador no banco de dados.
            dao.save(model);

            // ==================== REDIRECIONAMENTO ====================

            // Verifica se existe o parâmetro "origem" na requisição.
            // Esse parâmetro indica de qual pagina o servlet foi solicitado: do cadastro (se for diferente de null)
            // ou do Insert do crud (se for null)
            String origem = null;
            origem = request.getParameter("origem");

            if (origem != null) {
                // Se "origem" estiver presente, significa que o administrador acabou de se cadastrar
                // pela lading page.
                // Cria uma sessão e armazena o objeto 'Administrador' como atributo "usuario", para mostrar
                // o nome do usuario na pagina.
                HttpSession session = request.getSession();
                session.setAttribute("usuario", model);

                // Encaminha o usuário para o painel principal (crud.jsp) sem perder o estado da requisição.
                request.getRequestDispatcher("/WEB-INF/CRUD/crud.jsp").forward(request, response);
                return;
            } else {
                // Caso não exista "origem", o cadastro foi feito a partir do CRUD de administradores.
                // Então redireciona o navegador para a lista de administradores.
                response.sendRedirect(request.getContextPath() + "/administrador/list");
            }

        } catch (ConnectionFailedException | NotFoundException e) {
            // Captura erros relacionados ao banco de dados (ex: falha na conexão ou registro não encontrado)
            // e mostra mensagem de erro personalizada.

            e.printStackTrace();

            ErroServlet.setErro(request, response, dao, e, lista, ERROR_PAGE);
        } catch (ParseException e) {
            // Captura erros ao converter ou processar parâmetros (ex: formato de data incorreto)

            e.printStackTrace();
            ErroServlet.setErro(request, response, dao, e, lista, ERROR_PAGE);
        }
    }
}
