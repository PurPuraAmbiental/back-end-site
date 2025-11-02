package com.purpura.servlet.empresa;

import com.purpura.common.ErroServlet;
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
 * Servlet responsável por atualizar os dados de uma Empresa existente.
 *
 * Recebe os dados via POST, valida o tamanho da senha, realiza a criptografia
 * e atualiza o registro no banco de dados.
 *
 * @author
 *     Bruna de Jesus e Kevin de Oliveira
 */
@WebServlet(name = "UpdateEmpresaServlet", value = "/empresa/update")
public class UpdateEmpresaServlet extends HttpServlet {

    /**
     * Processa o POST para atualizar uma Empresa.
     *
     * @param request  objeto HttpServletRequest contendo os parâmetros do formulário
     * @param response objeto HttpServletResponse para redirecionamento ou forward
     * @throws jakarta.servlet.ServletException se ocorrer erro no servlet
     * @throws IOException                      se ocorrer erro de I/O
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws jakarta.servlet.ServletException, IOException {

        // Em caso de erro, essas variáveis são usadas para repassar informações de contexto
        DAO<Empresa> dao = new EmpresaDAO();
        String caminho = "/CRUD/administrador.jsp";
        String lista = "listaAdministradores";

        try {
            // Cria um mapa contendo os parâmetros enviados pelo formulário
            Map<String, String> params = new LinkedHashMap<>();
            request.getParameterMap().forEach((key, values) -> params.put(key, values[0]));

            // Cria um objeto 'Empresa' a partir dos parâmetros recebidos
            Empresa model = new Empresa(params);

            // ==================== VALIDAÇÕES DE DOS DADOS ====================

            // Valida o tamanho da senha
            if (model.getCSenha().length() < 6) {
                ErroServlet.setErro(request, response, dao,
                        "Não foi possivel atualizar Empresa! \n Sua senha deve ter 6 ou mais caracteres validos",
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

            // Após atualização bem-sucedida, redireciona para a listagem de empresas
            response.sendRedirect(request.getContextPath() + "/empresa/list");

        } catch (ConnectionFailedException | NotFoundException e) {
            // Trata erros de conexão com o banco e mostra mensagem personalizada
            e.printStackTrace();
            ErroServlet.setErro(request, response, dao, e, lista, ERROR_PAGE);

        } catch (ParseException e) {
            // Trata erros de conversão ou parsing de parâmetros
            e.printStackTrace();
            ErroServlet.setErro(request, response, dao, e, lista, ERROR_PAGE);
        }
    }
}
