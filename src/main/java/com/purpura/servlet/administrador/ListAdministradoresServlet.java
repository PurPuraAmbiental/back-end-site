package com.purpura.servlet.administrador;

import com.purpura.common.ErroServlet;
import com.purpura.dao.AdministradorDAO;
import com.purpura.exception.ConnectionFailedException;
import com.purpura.exception.NotFoundException;
import com.purpura.model.Administrador;
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
 * Servlet responsável por listar os administradores cadastrados no sistema.
 *
 * Pode receber um parâmetro opcional "nomeAdministrador" para filtrar os resultados.
 * Encaminha as informações obtidas para a página JSP "administrador.jsp".
 *
 *
 * @author
 *      Bruna de Jesus e Kevin de Oliveira
 */
@WebServlet(name = "ListAdministradoresServlet", value = "/administrador/list")
public class ListAdministradoresServlet extends HttpServlet {

    /**
     * Processa requisições GET para exibir a lista de administradores.
     *
     *  Pode receber um parâmetro opcional para filtrar os resultados.
     *
     * @param request  objeto HttpServletRequest contendo parâmetros da requisição (ex: nomeAdministrador)
     * @param response objeto HttpServletResponse para redirecionamento ou forward
     * @throws ServletException se ocorrer erro no servlet
     * @throws IOException      se ocorrer erro de I/O
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AdministradorDAO administradorDAO = new AdministradorDAO();
        String caminho = "/WEB-INF/CRUD/administrador.jsp";
        String lista = "listaAdministradores";
        try {

            // Obtém o parâmetro "nomeAdministrador" enviado pela página (usado para filtragem).
            String nome = request.getParameter("nomeAdministrador");

            // Busca a lista de administradores no banco.
            // Se o parâmetro 'nome' for nulo ou vazio, a listagem será completa.
            // Caso contrário, a listagem será filtrada pelo nome informado.
            List<Administrador> administradores = administradorDAO.listarAdministradoresFiltrados(nome);


            // Define os atributos que serão acessados na página JSP:
            // - listaAdministradores: lista dos administradores buscados
            // - nome: valor do filtro utilizado (para manter o campo preenchido na tela)
            request.setAttribute("listaAdministradores", administradores);
            request.setAttribute("nome", nome);

            // Encaminha a requisição e a resposta para a página de exibição.
            request.getRequestDispatcher("/WEB-INF/CRUD/administrador.jsp").forward(request, response);

        } catch (ConnectionFailedException | NotFoundException e) {
            // Define uma mensagem de erro na requisição, será exibida na página de erro
            e.printStackTrace();
            ErroServlet.setErro(request, response, administradorDAO, e, lista, ERROR_PAGE);
        } catch (Exception e) {
            //Erro genérico (não previsto)
            // Captura qualquer outra exceção inesperada que possa ocorrer no fluxo
            ErroServlet.setErro(request, response, administradorDAO,
                    "Ocorreu um erro inesperado.",
                    lista, ERROR_PAGE);
        }
    }
}