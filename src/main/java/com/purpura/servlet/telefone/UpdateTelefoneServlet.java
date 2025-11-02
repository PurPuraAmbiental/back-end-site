package com.purpura.servlet.telefone;

import com.purpura.common.Regex;
import com.purpura.dao.EmpresaDAO;
import com.purpura.dao.TelefoneDAO;
import com.purpura.dto.TelefoneView;
import com.purpura.exception.ConnectionFailedException;
import com.purpura.exception.NotFoundException;
import com.purpura.model.Empresa;
import com.purpura.model.Telefone;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Servlet responsável por atualizar os dados de um Telefone existente.
 *
 * Recebe os dados via POST, valida o número de telefone e a empresa associada,
 * e atualiza o registro no banco de dados.
 *
 * ============================= ESSA CLASSE FAZ USO DE REGEX ==========================================
 *
 * @author
 *     Bruna de Jesus e Kevin de Oliveira
 */
@WebServlet(name = "UpdateTelefoneServlet", value = "/telefone/update")
public class UpdateTelefoneServlet extends HttpServlet {

    /**
     * Processa o POST para atualizar um Telefone.
     *
     * @param request  objeto HttpServletRequest contendo os parâmetros do formulário
     * @param response objeto HttpServletResponse para redirecionamento ou forward
     * @throws jakarta.servlet.ServletException se ocorrer erro no servlet
     * @throws IOException                      se ocorrer erro de I/O
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws jakarta.servlet.ServletException, IOException {

        // Em caso de erro, essas variáveis são usadas para repassar informações de contexto
        TelefoneDAO telefoneDAO = new TelefoneDAO();
        String lista = "listaTelefones";
        String caminho = "/WEB-INF/CRUD/telefone.jsp";

        try {
            // Cria um mapa contendo os parâmetros enviados pelo formulário
            Map<String, String> params = new LinkedHashMap<>();
            request.getParameterMap().forEach((key, values) -> params.put(key, values[0]));

            // Recupera a empresa pelo nome
            String nomeEmpresa = params.get("cNmEmpresa");
            EmpresaDAO empresaDAO = new EmpresaDAO();
            Empresa empresa = empresaDAO.findByAttribute("cNmEmpresa", nomeEmpresa);
            params.put("cCnpj", empresa.getCCnpj());

            // Cria um objeto 'Telefone' a partir dos parâmetros recebidos
            Telefone model = new Telefone(params);

            // ==================== VALIDAÇÕES DE DADOS ====================
            List<TelefoneView> telefoneViews = telefoneDAO.listarComEmpresa();

            // Valida o número de telefone
            if (!Regex.validarTelefone(model.getCNrTelefone())) {
                telefoneViewSetErro(request, response, telefoneDAO, telefoneViews,
                        "Não foi possível atualizar Telefone! Insira um Telefone válido",
                        lista, caminho);
                return;
            }

            // Valida se a empresa existe
            if (empresa == null) {
                telefoneViewSetErro(request, response, telefoneDAO, telefoneViews,
                        "Não foi possível atualizae Telefone! Insira uma empresa cadastrada anteriormente",
                        lista, caminho);
                return;
            }

            // Valida se a empresa está ativa
            if (empresa.getCAtivo() != '1') {
                telefoneViewSetErro(request, response, telefoneDAO, telefoneViews,
                        "Nao foi possivel atualizar Telefone! Insira uma empresa ativa",
                        lista, caminho);
                return;
            }

            // Atualiza os atributos do modelo com os novos valores do formulário
            model.setNCdTelefone(Integer.parseInt(params.get("nCdTelefone")));
            model.setCNrTelefone(params.get("cNrTelefone"));
            model.setCDescricao(params.get("cDescricao"));

            // Atualiza o registro no banco de dados
            telefoneDAO.update(model);

            // Após atualização bem-sucedida, redireciona para a listagem de telefones
            response.sendRedirect(request.getContextPath() + "/telefone/list");

        } catch (ConnectionFailedException | NotFoundException  e) {
            // Trata erros de conexão com o banco e mostra mensagem personalizada
            request.setAttribute("erro", "Erro ao atualizar Telefone: " + e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
            rd.forward(request, response);
        }
    }

    /**
     * Método auxiliar para setar erro e repassar a lista de telefones
     * para a view quando ocorre validação ou erro de atualização.
     *
     * @param request  objeto HttpServletRequest
     * @param response objeto HttpServletResponse
     * @param telefoneDAO DAO de Telefone
     * @param telefoneView lista de TelefoneView
     * @param mensagem mensagem de erro a ser exibida
     * @param lista nome do atributo da lista na requisição
     * @param caminho caminho da página JSP
     * @throws jakarta.servlet.ServletException se ocorrer erro no servlet
     * @throws IOException                      se ocorrer erro de I/O
     */
    public void telefoneViewSetErro(HttpServletRequest request, HttpServletResponse response,
                                    TelefoneDAO telefoneDAO, List<TelefoneView> telefoneView,
                                    String mensagem, String lista, String caminho)
            throws jakarta.servlet.ServletException, IOException {

        telefoneView = telefoneDAO.listarComEmpresa();
        request.setAttribute(lista, telefoneView);
        request.setAttribute("erro", mensagem);
        request.getRequestDispatcher(caminho).forward(request, response);
    }
}
