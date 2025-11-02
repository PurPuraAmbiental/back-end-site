package com.purpura.servlet.transportadora;

import com.purpura.dao.TransportadoraDAO;
import com.purpura.exception.ConnectionFailedException;
import com.purpura.exception.NotFoundException;
import com.purpura.model.Transportadora;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * Servlet responsável por listar as transportadoras cadastradas no sistema.
 *
 * Pode receber parâmetros opcionais "nomeTransportadora" e "regiao" para filtrar os resultados.
 * Encaminha as informações obtidas para a página JSP "transportadora.jsp".
 *
 *
 * @author
 *      Bruna de Jesus e Kevin de Oliveira
 */
@WebServlet(name = "ListTransportadoraServlet", value = "/transportadora/list")
public class ListTransportadoraServlet extends HttpServlet {

    /**
     * Processa requisições GET para exibir a lista de transportadoras.
     *
     * Pode receber parâmetros opcionais para filtrar os resultados.
     *
     * @param request  objeto HttpServletRequest contendo parâmetros da requisição (ex: nomeTransportadora, regiao)
     * @param response objeto HttpServletResponse para redirecionamento ou forward
     * @throws ServletException se ocorrer erro no servlet
     * @throws IOException      se ocorrer erro de I/O
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Define variáveis auxiliares para redirecionamento em caso de erro
        String lista = "listaTransportadoras";
        String caminho = "/WEB-INF/CRUD/transportadora.jsp";

        try {
            // Cria o DAO responsável pelas operações com a entidade Transportadora
            TransportadoraDAO transportadoraDAO = new TransportadoraDAO();

            // Captura os parâmetros do filtro enviados pela página (usados para filtragem)
            String nomeTransportadora = request.getParameter("nomeTransportadora");
            String regiao = request.getParameter("regiao");

            // Chama o método do DAO para buscar a lista de transportadoras no banco com os filtros aplicados
            List<Transportadora> transportadoras = transportadoraDAO.listarTransportadorasFiltradas(nomeTransportadora, regiao);

            // Define os atributos que serão acessados na página JSP:
            // - listaTransportadoras: lista das transportadoras buscadas
            // - regiao: valor do filtro usado
            request.setAttribute(lista, transportadoras);
            request.setAttribute("regiao", regiao);

            // Encaminha a requisição e a resposta para a página de exibição
            RequestDispatcher rd = request.getRequestDispatcher(caminho);
            rd.forward(request, response);

        } catch (ConnectionFailedException | NotFoundException e) {
            // Define uma mensagem de erro na requisição, que será exibida na página de erro
            request.setAttribute("erro", "Erro ao carregar lista de Transportes: " + e.getMessage());
            e.printStackTrace();
            RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            // Define mensagem genérica para erros não previstos
            request.setAttribute("erro", "Erro inesperado ao buscar Transportes: " + e.getMessage());
            e.printStackTrace();
            RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
            rd.forward(request, response);
        }
    }
}
