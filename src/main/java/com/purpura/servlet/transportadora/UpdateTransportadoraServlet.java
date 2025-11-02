package com.purpura.servlet.transportadora;

import com.purpura.common.ErroServlet;
import com.purpura.common.Regex;
import com.purpura.dao.DAO;
import com.purpura.dao.TransportadoraDAO;
import com.purpura.exception.ConnectionFailedException;
import com.purpura.exception.NotFoundException;
import com.purpura.model.Transportadora;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Servlet responsável por atualizar os dados de uma Transportadora existente.
 *
 * Recebe os dados via POST, valida o e-mail e atualiza o registro no banco de dados.
 *
 * ================== CLASSE FAZ USO DE REGEX ============================
 *
 * @author
 *     Bruna de Jesus e Kevin de Oliveira
 */
@WebServlet(name = "UpdateTransportadoraServlet", value = "/transportadora/update")
public class UpdateTransportadoraServlet extends HttpServlet {

    /**
     * Processa o POST para atualizar uma Transportadora.
     *
     * @param request  objeto HttpServletRequest contendo os parâmetros do formulário
     * @param response objeto HttpServletResponse para redirecionamento ou forward
     * @throws jakarta.servlet.ServletException se ocorrer erro no servlet
     * @throws IOException                      se ocorrer erro de I/O
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws jakarta.servlet.ServletException, IOException {

        // Em caso de erro, essas variáveis são usadas para repassar informações de contexto
        String lista = "listaTransportadoras";
        String caminho = "/WEB-INF/CRUD/transportadora.jsp";
        DAO<Transportadora> dao = new TransportadoraDAO();

        try {
            // Cria um mapa contendo os parâmetros enviados pelo formulário
            Map<String, String> params = new LinkedHashMap<>();
            request.getParameterMap().forEach((key, values) -> params.put(key, values[0]));

            // Cria um objeto 'Transportadora' a partir dos parâmetros recebidos
            Transportadora model = new Transportadora(params);

            // Atualiza os atributos do modelo com os novos valores do formulário
            model.setCNmTransportadora(params.get("cNmTransportadora"));
            model.setCRegiaoAtendida(params.get("cRegiaoAtendida"));

            // ==================== VALIDAÇÕES DE DADOS ====================

            // Valida o e-mail
            if (!Regex.validarEmail(model.getCEmail())) {
                ErroServlet.setErro(request, response, dao,
                        "Não foi possível cadastrar transportadora. Insira um e-mail válido.",
                        lista, caminho);
                return;
            }
            model.setCEmail(params.get("cEmail"));

            // Atualiza o registro no banco de dados
            dao.update(model);

            // Após atualização bem-sucedida, redireciona para a listagem de transportadoras
            response.sendRedirect(request.getContextPath() + "/transportadora/list");

        } catch (ConnectionFailedException | NotFoundException | NumberFormatException e) {
            // Trata erros de conexão ou formatação e mostra mensagem personalizada
            request.setAttribute("erro", "Erro ao atualizar Transportadora: " + e.getMessage());
        }
    }
}
