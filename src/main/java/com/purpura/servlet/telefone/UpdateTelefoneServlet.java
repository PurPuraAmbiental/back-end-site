package com.purpura.servlet.telefone;

import com.purpura.common.ErroServlet;
import com.purpura.common.Regex;
import com.purpura.dao.EmpresaDAO;
import com.purpura.dao.TelefoneDAO;
import com.purpura.dto.TelefoneView;
import com.purpura.exception.ConnectionFailedException;
import com.purpura.exception.NotFoundException;
import com.purpura.model.Empresa;
import com.purpura.model.Telefone;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.purpura.common.Constants.ERROR_PAGE;

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

        // Variáveis usadas em caso de erro para repassar contexto
        TelefoneDAO telefoneDAO = new TelefoneDAO();
        String lista = "listaTelefones";
        String caminho = "/WEB-INF/CRUD/telefone.jsp";

        try {
            // ==================== CAPTURA E PREPARAÇÃO DOS PARÂMETROS ====================
            Map<String, String> params = new LinkedHashMap<>();
            request.getParameterMap().forEach((key, values) -> params.put(key, values[0]));

            // Recupera lista atual de telefones (usada em caso de erro)
            List<TelefoneView> telefoneViews = telefoneDAO.listarComEmpresa();

            // ==================== VALIDAÇÃO DE DADOS ====================

            // Busca empresa associada pelo nome informado no formulário
            String nomeEmpresa = params.get("cNmEmpresa");
            EmpresaDAO empresaDAO = new EmpresaDAO();
            Empresa empresa = empresaDAO.findByAttribute("cNmEmpresa", nomeEmpresa);

            // Verifica se a empresa existe
            if (empresa == null) {
                telefoneViewSetErro(request, response, telefoneDAO, telefoneViews,
                        "Não foi possível atualizar o telefone! Insira uma empresa cadastrada anteriormente.",
                        lista, caminho);
                return;
            }

            // Verifica se a empresa está ativa
            if (empresa.getCAtivo() != '1') {
                telefoneViewSetErro(request, response, telefoneDAO, telefoneViews,
                        "Não foi possível atualizar o telefone! Insira uma empresa ativa.",
                        lista, caminho);
                return;
            }

            // Atribui o CNPJ da empresa validada
            params.put("cCnpj", empresa.getCCnpj());

            // Cria o modelo base a partir dos parâmetros recebidos
            Telefone model = new Telefone(params);

            // Valida o número de telefone com REGEX
            if (!Regex.validarTelefone(model.getCNrTelefone())) {
                telefoneViewSetErro(request, response, telefoneDAO, telefoneViews,
                        "Não foi possível atualizar o telefone! Insira um número de telefone válido.",
                        lista, caminho);
                return;
            }

            // ==================== ATUALIZAÇÃO DO MODELO ====================
            model.setNCdTelefone(Integer.parseInt(params.get("nCdTelefone")));
            model.setCNrTelefone(params.get("cNrTelefone"));
            model.setCDescricao(params.get("cDescricao"));
            model.setCCnpj(params.get("cCnpj"));

            // ==================== ATUALIZAÇÃO NO BANCO ====================
            telefoneDAO.update(model);

            // Redireciona após atualização bem-sucedida
            response.sendRedirect(request.getContextPath() + "/telefone/list");

        } catch (ConnectionFailedException | NotFoundException e) {
            e.printStackTrace();
            ErroServlet.setErro(request, response, telefoneDAO, e, lista, ERROR_PAGE);
        } catch (Exception e) {
            e.printStackTrace();
            ErroServlet.setErro(request, response, telefoneDAO,
                    "Ocorreu um erro inesperado.",
                    lista, ERROR_PAGE);
        }
    }

    /**
     * Método auxiliar para setar erro e repassar a lista de telefones
     * para a view quando ocorre validação ou erro de atualização.
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
