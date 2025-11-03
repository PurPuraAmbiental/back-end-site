package com.purpura.servlet.telefone;

import com.purpura.common.ErroServlet;
import com.purpura.dao.TelefoneDAO;
import com.purpura.dto.TelefoneView;
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
 * Servlet responsável por listar os telefones das empresas cadastradas no sistema.
 *
 * Pode receber parâmetro opcional "nomeEmpresa" para filtrar os resultados.
 * Encaminha as informações obtidas para a página JSP "telefone.jsp".
 *
 *
 * @author
 *      Bruna de Jesus e Kevin de Oliveira
 */
@WebServlet(name = "ListTelefonesServlet", value = "/telefone/list")
public class ListTelefonesServlet extends HttpServlet {

    /**
     * Processa requisições GET para exibir a lista de telefones das empresas.
     *
     * Pode receber parâmetros opcionais para filtrar os resultados.
     *
     * @param request  objeto HttpServletRequest contendo parâmetros da requisição (ex: nomeEmpresa)
     * @param response objeto HttpServletResponse para redirecionamento ou forward
     * @throws ServletException se ocorrer erro no servlet
     * @throws IOException      se ocorrer erro de I/O
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Define variáveis auxiliares para redirecionamento em caso de erro
        String lista = "listaTelefones";
        String caminho = "/WEB-INF/CRUD/telefone.jsp";

        // Cria o DAO responsável pelas operações com a entidade Telefone
        TelefoneDAO telefoneDAO = new TelefoneDAO();

        try {

            // Captura os parâmetros do filtro enviados pela página (usados para filtragem)
            String nomeEmpresa = request.getParameter("nomeEmpresa");

            // Chama o método do DAO para buscar a lista de telefones no banco com os filtros aplicados
            List<TelefoneView> telefones = telefoneDAO.listarTelefonesFiltrados(nomeEmpresa);

            // Define os atributos que serão acessados na página JSP:
            // - listaTelefones: lista dos telefones buscados
            // - nomeEmpresa: valor do filtro usado
            request.setAttribute(lista, telefones);
            request.setAttribute("nomeEmpresa", nomeEmpresa);

            // Encaminha a requisição e a resposta para a página de exibição
            RequestDispatcher rd = request.getRequestDispatcher(caminho);
            rd.forward(request, response);

        } catch (ConnectionFailedException | NotFoundException e) {
            // Define uma mensagem de erro na requisição, que será exibida na página de erro
            e.printStackTrace();
            ErroServlet.setErro(request, response, telefoneDAO, e, lista, ERROR_PAGE);
        } catch (Exception e) {
            //Erro genérico (não previsto)
            // Captura qualquer outra exceção inesperada que possa ocorrer no fluxo
            ErroServlet.setErro(request, response, telefoneDAO,
                    "Ocorreu um erro inesperado.",
                    lista, ERROR_PAGE);
        }
    }
}
