package com.purpura.servlet.empresa;

import com.purpura.common.ErroServlet;
import com.purpura.common.Regex;
import com.purpura.dao.DAO;
import com.purpura.dao.EmpresaDAO;
import com.purpura.exception.ConnectionFailedException;
import com.purpura.exception.NotFoundException;
import com.purpura.model.Empresa;
import com.purpura.util.Criptografia;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.purpura.common.Constants.ERROR_PAGE;

/**
 * Servlet responsável por inserir uma nova Empresa no sistema.
 *
 * Esta classe recebe os dados do formulário via método POST, valida os campos
 * utilizando expressões regulares (Regex), realiza a criptografia da senha
 * e salva o registro no banco de dados caso todas as verificações sejam bem-sucedidas.
 *
 * =============== ESTA CLASSE FAZ USO DE REGEX ===============================
 *
 * CRUD -> CREATE
 *
 * @author Bruna
 * @author Kevin
 */
@WebServlet(name = "InsertEmpresaServlet", value = "/empresa/insert")
public class InsertEmpresaServlet extends HttpServlet {

    /**
     * Processa o POST para inserir uma nova Empresa.
     *
     * @param request  objeto HttpServletRequest contendo os parâmetros enviados pelo formulário
     * @param response objeto HttpServletResponse usado para redirecionar ou encaminhar respostas
     * @throws jakarta.servlet.ServletException se ocorrer erro no servlet
     * @throws IOException                      se ocorrer erro de I/O
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws jakarta.servlet.ServletException, IOException {

        // Define o caminho do JSP e o nome da lista usados em caso de erro
        String caminho = "/WEB-INF/CRUD/empresa.jsp";
        String lista = "listaEmpresas";

        // Cria o DAO responsável pelas operações com a entidade Empresa
        DAO<Empresa> dao = new EmpresaDAO();

        try {
            // ==================== CAPTURA DOS PARÂMETROS ====================

            // Converte o mapa de parâmetros do request para um Map<String, String>
            Map<String, String> params = new LinkedHashMap<>();
            request.getParameterMap().forEach((key, values) -> params.put(key, values[0]));

            // Cria o objeto de modelo Empresa a partir dos parâmetros recebidos
            Empresa model = new Empresa(params);

            // ==================== VALIDAÇÕES DOS DADOS ====================

            // Valida o formato do e-mail usando Regex
            if (!Regex.validarEmail(model.getCEmail())) {
                ErroServlet.setErro(request, response, dao,
                        "Não foi possivel cadastrar Empresa! Digite um e-mail válido",
                        lista, caminho);
            }

            // Valida o formato do CNPJ usando Regex
            if (!Regex.validarCnpj(model.getCCnpj())) {
                ErroServlet.setErro(request, response, dao,
                        "Não foi possivel cadastrar Empresa! Digite um CNPJ válido",
                        lista, caminho);
                return; // Interrompe o fluxo se o CNPJ for inválido
            } else {
                // Remove caracteres especiais do CNPJ antes de salvar
                model.setCCnpj(model.getCCnpj()
                        .replace("/", "")
                        .replace(".", "")
                        .replace("-", ""));
                System.out.println(model.getCCnpj()); // Apenas para verificação em log
            }

            // Verifica se já existe empresa cadastrada com o mesmo CNPJ
            if (dao.findById(model.getCCnpj()) != null) {
                ErroServlet.setErro(request, response, dao,
                        "Esse CNPJ já foi cadastrado! Digite um CNPJ válido",
                        lista, caminho);
                return;
            }

            // Verifica se o e-mail já está cadastrado no sistema
            if (dao.findByAttribute("cEmail", model.getCEmail()) != null) {
                ErroServlet.setErro(request, response, dao,
                        "Esse e-mail já foi cadastrado! Digite um e-mail válido",
                        lista, caminho);
                return;
            }

            // Verifica se a senha possui o tamanho mínimo de 6 caracteres
            if (model.getCSenha().length() < 6) {
                ErroServlet.setErro(request, response, dao,
                        "Não foi possível cadastrar Empresa! A senha deve ter 6 ou mais caracteres válidos",
                        lista, caminho);
                return;
            }

            // ==================== CRIPTOGRAFIA DA SENHA ====================

            // Verifica se o parâmetro "cSenha" foi enviado no formulário
            if (params.containsKey("cSenha")) {
                // Criptografa a senha e atualiza o mapa e o modelo
                String hash = Criptografia.criptografar(params.get("cSenha"));
                params.put("cSenha", hash);
                model.setCSenha(params.get("cSenha"));
            }

            // ==================== SALVAMENTO NO BANCO ====================

            // Salva a nova Empresa no banco de dados
            dao.save(model);

            // Redireciona o usuário para a lista de empresas após o cadastro bem-sucedido
            response.sendRedirect(request.getContextPath() + "/empresa/list");

        } catch (ConnectionFailedException | NotFoundException e) {
            // Trata erros relacionados ao banco de dados (conexão ou busca de dados)
            ErroServlet.setErro(request, response, dao, e, lista, ERROR_PAGE);

        } catch (ParseException e) {
            e.printStackTrace();
            ErroServlet.setErro(request, response, dao, e, lista, ERROR_PAGE);
        }
    }
}
