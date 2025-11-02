package com.purpura.servlet.enderecoEmpresa;

import com.purpura.dao.EnderecoEmpresaDAO;
import com.purpura.dto.EnderecoEmpresaView;
import com.purpura.exception.ConnectionFailedException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * Servlet responsável por listar os endereços das empresas cadastradas no sistema.
 *
 * Pode receber parâmetros opcionais ("estado", "nomeEmpresa") para filtrar os resultados.
 * Encaminha as informações obtidas para a página JSP "endereco.jsp".
 *
 *
 * @author
 *      Bruna de Jesus e Kevin de Oliveira
 */
@WebServlet(name = "ListEnderecosEmpresaServlet", value = "/endereco-empresa/list")
public class ListEnderecosEmpresaServlet extends HttpServlet {

    /**
     * Processa requisições GET para exibir a lista de endereços das empresas.
     *
     * Pode receber parâmetros opcionais para filtrar os resultados.
     *
     * @param request  objeto HttpServletRequest contendo parâmetros da requisição (ex: estado, nomeEmpresa)
     * @param response objeto HttpServletResponse para redirecionamento ou forward
     * @throws ServletException se ocorrer erro no servlet
     * @throws IOException      se ocorrer erro de I/O
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Define variáveis auxiliares para redirecionamento em caso de erro
        String lista = "listaEnderecos";
        String caminho = "/WEB-INF/CRUD/endereco.jsp";

        try {
            // Cria o DAO responsável pelas operações com a entidade EnderecoEmpresa
            EnderecoEmpresaDAO enderecoDAO = new EnderecoEmpresaDAO();

            // Captura os parâmetros do filtro enviados pela página (usados para filtragem)
            String estado = request.getParameter("estado");
            String nomeEmpresa = request.getParameter("nomeEmpresa");

            // Chama o método do DAO para buscar a lista de endereços no banco com os filtros aplicados
            List<EnderecoEmpresaView> enderecos = enderecoDAO.listarEnderecosFiltrados(estado, nomeEmpresa);

            // Define os atributos que serão acessados na página JSP:
            // - listaEnderecos: lista dos endereços buscados
            // - estado: valor do filtro usado
            // - nomeEmpresa: valor do filtro usado
            request.setAttribute(lista, enderecos);
            request.setAttribute("estado", estado);
            request.setAttribute("nomeEmpresa", nomeEmpresa);

            // Encaminha a requisição e a resposta para a página de exibição
            RequestDispatcher rd = request.getRequestDispatcher(caminho);
            rd.forward(request, response);

        } catch (ConnectionFailedException e) {
            // Define uma mensagem de erro na requisição, que será exibida na página de erro
            request.setAttribute("erro", "Erro ao carregar lista de endereços: " + e.getMessage());
            e.printStackTrace();
            RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            // Define mensagem genérica para erros não previstos
            request.setAttribute("erro", "Erro inesperado ao buscar Endereços: " + e.getMessage());
            e.printStackTrace();
            RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
            rd.forward(request, response);
        }
    }
}
