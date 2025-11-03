package com.purpura.servlet.residuo;

import com.purpura.common.ErroServlet;
import com.purpura.dao.ResiduoDAO;
import com.purpura.dto.ResiduoView;
import com.purpura.exception.ConnectionFailedException;
import com.purpura.exception.NotFoundException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import static com.purpura.common.Constants.ERROR_PAGE;

/**
 * Servlet responsável por listar os resíduos cadastrados no sistema.
 *
 * Pode receber parâmetros opcionais ("precoMin", "precoMax", "volumeMin", "volumeMax", "unidade") para filtrar os resultados.
 * Encaminha as informações obtidas para a página JSP "residuos.jsp".
 *
 *
 * @author
 *      Bruna de Jesus e Kevin de Oliveira
 */
@WebServlet(name = "ListResiduosServlet", value = "/residuo/list")
public class ListResiduosServlet extends HttpServlet {

    /**
     * Processa requisições GET para exibir a lista de resíduos.
     *
     * Pode receber parâmetros opcionais para filtrar os resultados.
     *
     * @param request  objeto HttpServletRequest contendo parâmetros da requisição
     * @param response objeto HttpServletResponse para redirecionamento ou forward
     * @throws ServletException se ocorrer erro no servlet
     * @throws IOException      se ocorrer erro de I/O
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Define variáveis auxiliares para redirecionamento em caso de erro
        String lista = "listaResiduos";
        String caminho = "/WEB-INF/CRUD/residuos.jsp";
        // Cria o DAO responsável pelas operações com a entidade Resíduo
        ResiduoDAO residuoDAO = new ResiduoDAO();
        
        try {


            // Captura os parâmetros do filtro enviados pela página (usados para filtragem)
            String precoMinStr = request.getParameter("precoMin");
            String precoMaxStr = request.getParameter("precoMax");
            String volumeMinStr = request.getParameter("volumeMin");
            String volumeMaxStr = request.getParameter("volumeMax");
            String unidade = request.getParameter("unidade");

            Double precoMin = (precoMinStr != null && !precoMinStr.isEmpty()) ? Double.parseDouble(precoMinStr) : null;
            Double precoMax = (precoMaxStr != null && !precoMaxStr.isEmpty()) ? Double.parseDouble(precoMaxStr) : null;
            Double volumeMin = (volumeMinStr != null && !volumeMinStr.isEmpty()) ? Double.parseDouble(volumeMinStr) : null;
            Double volumeMax = (volumeMaxStr != null && !volumeMaxStr.isEmpty()) ? Double.parseDouble(volumeMaxStr) : null;
            if (unidade != null && unidade.isBlank()) unidade = null;

            // Chama o método do DAO para buscar a lista de resíduos no banco com os filtros aplicados
            List<ResiduoView> residuos = residuoDAO.listarComEmpresaFiltrado(precoMin, precoMax, volumeMin, volumeMax, unidade);

            // Define os atributos que serão acessados na página JSP:
            // - listaResiduos: lista dos resíduos buscados
            // - precoMin, precoMax, volumeMin, volumeMax, unidade: valores dos filtros usados
            request.setAttribute(lista, residuos);
            request.setAttribute("precoMin", precoMinStr);
            request.setAttribute("precoMax", precoMaxStr);
            request.setAttribute("volumeMin", volumeMinStr);
            request.setAttribute("volumeMax", volumeMaxStr);
            request.setAttribute("unidade", unidade);

            // Encaminha a requisição e a resposta para a página de exibição
            RequestDispatcher rd = request.getRequestDispatcher(caminho);
            rd.forward(request, response);

        } catch (ConnectionFailedException | NotFoundException e) {
            // Define uma mensagem de erro na requisição, será exibida na página de erro
            e.printStackTrace();
            ErroServlet.setErro(request, response, residuoDAO, "Falha ao conectar no banco de dados", lista, ERROR_PAGE);

        } catch (Exception e) {
            // Define mensagem genérica para erros não previstos
            e.printStackTrace();
            ErroServlet.setErro(request, response, residuoDAO, "Ocorreu um erro inesperado.", lista, ERROR_PAGE);
        }
    }
}
