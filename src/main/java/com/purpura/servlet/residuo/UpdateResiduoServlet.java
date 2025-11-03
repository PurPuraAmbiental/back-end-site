package com.purpura.servlet.residuo;

import com.purpura.common.ErroServlet;
import com.purpura.dao.DAO;
import com.purpura.dao.EmpresaDAO;
import com.purpura.dao.ResiduoDAO;
import com.purpura.dto.ResiduoView;
import com.purpura.exception.ConnectionFailedException;
import com.purpura.exception.NotFoundException;
import com.purpura.model.Empresa;
import com.purpura.model.Residuo;
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
 * Servlet responsável por atualizar os dados de um resíduo.
 * Rota: /residuo/update
 *
 * Este servlet recebe os dados do formulário de edição, realiza validações
 * e atualiza o registro no banco de dados. Caso haja falhas, exibe mensagens
 * de erro apropriadas na tela de resíduos.
 */
@WebServlet(name = "UpdateResiduoServlet", value = "/residuo/update")
public class UpdateResiduoServlet extends HttpServlet {

    /**
     * Método principal do servlet. Processa o formulário de atualização de resíduos.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws jakarta.servlet.ServletException, IOException {

        DAO<Residuo> dao = new ResiduoDAO();
        String lista = "listaResiduos";
        String caminho = "/WEB-INF/CRUD/residuos.jsp";

        try {
            // Cria um mapa com os parâmetros enviados pelo formulário
            Map<String, String> params = new LinkedHashMap<>();
            request.getParameterMap().forEach((key, values) -> params.put(key, values[0]));

            System.out.println(params.get("cNmEmpresa") + " | " + params.get("cCnpj"));

            // Recupera a empresa informada no formulário
            String nomeEmpresa = params.get("cNmEmpresa");
            EmpresaDAO empresaDAO = new EmpresaDAO();
            Empresa empresa = empresaDAO.findByAttribute("cNmEmpresa", nomeEmpresa);

            // Instancia o DAO de Resíduo e obtém a lista de resíduos (para o caso de erro)
            ResiduoDAO residuoDAO = new ResiduoDAO();
            List<ResiduoView> listaResiduos = residuoDAO.listarComEmpresa();

            // --- VALIDAÇÃO DE EMPRESA ---
            // Se a empresa informada não existe, exibe erro e interrompe o processo
            if (empresa == null) {
                residuoViewSetErro(request, response, residuoDAO, listaResiduos,
                        "Não foi possível atualizar o Resíduo! Insira uma empresa cadastrada anteriormente.",
                        lista, caminho);
                return;
            }

            // Se a empresa existir, define o CNPJ correspondente nos parâmetros
            params.put("cCnpj", empresa.getCCnpj());

            // Cria um objeto Residuo com base nos parâmetros recebidos
            Residuo model = new Residuo(params);

            // Verifica se a empresa está ativa
            if (empresa.getCAtivo() != '1') {
                residuoViewSetErro(request, response, residuoDAO, listaResiduos,
                        "Não foi possível atualizar o Resíduo! Insira uma empresa ativa.",
                        lista, caminho);
                return;
            }

            // --- ATUALIZA OS DADOS DO RESÍDUO ---
            model.setCNmResiduo(params.get("cNmResiduo"));
            model.setCCategoria(params.get("cCategoria"));
            model.setCDescricao(params.get("cDescricao"));
            model.setCTipoUnidade(params.get("cNmTipoUnidade"));
            model.setNPrecoPadrao(Double.parseDouble(params.get("nPrecoPadrao")));
            model.setNVolumePadrao(Double.parseDouble(params.get("nVolumePadrao")));
            model.setCTipoUnidade(params.get("cTipoUnidade"));

            // Atualiza o registro no banco
            dao.update(model);

            // Redireciona de volta para a lista de resíduos
            response.sendRedirect(request.getContextPath() + "/residuo/list");

        } catch (NumberFormatException e) {
            // Erro ao converter valores numéricos (ex: preço, volume)
            e.printStackTrace();
            ErroServlet.setErro(request, response, dao,
                    "Erro ao processar parâmetros numéricos.", lista, ERROR_PAGE);

        } catch (ConnectionFailedException | NotFoundException e) {
            // Erros relacionados à conexão ou busca de registros
            e.printStackTrace();
            ErroServlet.setErro(request, response, dao, e, lista, ERROR_PAGE);

        } catch (Exception e) {
            // Erro genérico não previsto
            e.printStackTrace();
            ErroServlet.setErro(request, response, dao,
                    "Ocorreu um erro inesperado ao atualizar o resíduo.",
                    lista, ERROR_PAGE);
        }
    }

    /**
     * Define atributos de erro e encaminha o usuário para a tela de resíduos.
     *
     * @param request       Requisição HTTP
     * @param response      Resposta HTTP
     * @param residuoDAO    DAO responsável pelas operações de resíduo
     * @param residuoView   Lista de resíduos com informações de empresa
     * @param mensagem      Mensagem de erro exibida ao usuário
     * @param lista         Nome do atributo que contém a lista de resíduos
     * @param caminho       Caminho da JSP de destino
     */
    public void residuoViewSetErro(HttpServletRequest request, HttpServletResponse response,
                                   ResiduoDAO residuoDAO, List<ResiduoView> residuoView,
                                   String mensagem, String lista, String caminho)
            throws jakarta.servlet.ServletException, IOException {

        residuoView = residuoDAO.listarComEmpresa();
        request.setAttribute(lista, residuoView);
        request.setAttribute("erro", mensagem);
        request.getRequestDispatcher(caminho).forward(request, response);
    }
}
