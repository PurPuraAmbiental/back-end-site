package com.purpura.servlet.administrador;

import com.purpura.common.ErroServlet;
import com.purpura.dao.AdministradorDAO;
import com.purpura.dao.DAO;
import com.purpura.exception.ConnectionFailedException;
import com.purpura.model.Administrador;
import com.purpura.util.Criptografia;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Servlet responsável por atualizar os dados de um Administrador existente.
 *
 * Recebe os dados via POST, valida o tamanho da senha, realiza a criptografia
 * e atualiza o registro no banco de dados.
 *
 * @author
 *     Bruna de Jesus e Kevin de Oliveira
 */
@WebServlet(name = "UpdateAdministradorServlet", value = "/administrador/update")
public class UpdateAdministradorServlet extends HttpServlet {

    /**
     * Processa o POST para atualizar um Administrador.
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
        String caminho = "WEB-INF/CRUD/administrador.jsp";
        String lista = "listaAdministradores";

        try {
            // Cria um mapa contendo os parâmetros enviados pelo formulário
            Map<String, String> params = new LinkedHashMap<>();
            request.getParameterMap().forEach((key, values) -> params.put(key, values[0]));

            // Cria um objeto 'Administrador' a partir dos parâmetros recebidos
            Administrador model = new Administrador(params);

            // Atualiza os atributos do modelo com os novos valores do formulário
            model.setCSenha(params.get("cSenha"));
            model.setCNmAdministrador(params.get("cNmAdministrador"));

            // ==================== VALIDAÇÕES DE DOS DADOS ====================

            // Valida o tamanho da senha
            if (model.getCSenha().length() < 6) {
                ErroServlet.setErro(request, response, dao,
                        "Não foi possível atualizar o Administrador! Insira uma senha com no mínimo 6 caracteres.",
                        lista, caminho);
                return;
            }

            // Se o parâmetro de senha existir, criptografa antes de atualizar no banco
            if (params.containsKey("cSenha")) {
                String hash = Criptografia.criptografar(params.get("cSenha"));
                params.put("cSenha", hash);
                model.setCSenha(params.get("cSenha"));
            }

            // Atualiza o registro no banco de dados
            dao.update(model);

            // Após atualização bem-sucedida, redireciona para a listagem de administradores
            response.sendRedirect(request.getContextPath() + "/administrador/list");

        } catch (ConnectionFailedException e) {
            // Trata erros de conexão com o banco e mostra mensagem personalizada
            ErroServlet.setErro(request, response, dao,
                    "Erro ao atualizar Administrador: " + e.getMessage(), lista, caminho);

        } catch (ParseException e) {
            // Trata erros de conversão ou parsing de parâmetros
            System.out.println(e.getMessage());
            ErroServlet.setErro(request, response, dao,
                    "Erro ao processar os parâmetros: " + e.getMessage(), lista, caminho);
        }
    }
}
