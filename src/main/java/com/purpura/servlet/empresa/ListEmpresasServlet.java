package com.purpura.servlet.empresa;

import com.purpura.common.ErroServlet;
import com.purpura.dao.EmpresaDAO;
import com.purpura.exception.ConnectionFailedException;
import com.purpura.exception.NotFoundException;
import com.purpura.model.Empresa;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * Servlet responsável por listar as empresas cadastradas no sistema.
 *
 * Pode receber parâmetros opcionais ("nome", "email", "cnpj", "ativo", "temResiduo") para filtrar os resultados.
 * Encaminha as informações obtidas para a página JSP "empresa.jsp".
 *
 *
 * @author
 *      Bruna de Jesus e Kevin de Oliveira
 */
@WebServlet(name = "ListEmpresaServlet", value = "/empresa/list")
public class ListEmpresasServlet extends HttpServlet {

    /**
     * Processa requisições GET para exibir a lista de empresas.
     *
     * Pode receber parâmetros opcionais para filtrar os resultados.
     *
     * @param request  objeto HttpServletRequest contendo parâmetros da requisição (ex: nome, email, cnpj)
     * @param response objeto HttpServletResponse para redirecionamento ou forward
     * @throws ServletException se ocorrer erro no servlet
     * @throws IOException      se ocorrer erro de I/O
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EmpresaDAO empresaDAO = new EmpresaDAO();

        // Definindo variáveis para nome do atributo e caminho da JSP
        String lista = "listaEmpresa";
        String caminho = "/WEB-INF/CRUD/empresa.jsp";

        try {
            // Captura os parâmetros do filtro enviados pela página (usados para filtragem)
            String nome = request.getParameter("nome");
            String email = request.getParameter("email");
            String cnpj = request.getParameter("cnpj");

            String ativoParam = request.getParameter("ativo");
            Character ativo = (ativoParam != null && !ativoParam.isBlank()) ? ativoParam.charAt(0) : null;

            String temResiduoParam = request.getParameter("temResiduo");
            Boolean temResiduo = (temResiduoParam != null && !temResiduoParam.isBlank())
                    ? "1".equals(temResiduoParam)
                    : null;

            // Chama o método do DAO para buscar a lista de empresas no banco com os filtros aplicados
            List<Empresa> empresas = empresaDAO.listarEmpresasFiltradas(
                    nome, email, cnpj, ativo, temResiduo
            );

            // Define os atributos que serão acessados na página JSP:
            // - listaEmpresas: lista das empresas buscadas
            request.setAttribute("listaEmpresas", empresas);
            request.getRequestDispatcher("/WEB-INF/CRUD/empresa.jsp").forward(request, response);

            // Encaminha a requisição e a resposta para a página de exibição
            request.getRequestDispatcher(caminho).forward(request, response);

        } catch (ConnectionFailedException | NotFoundException e) {
            // Define uma mensagem de erro na requisição, que será exibida na página de erro
            e.printStackTrace();
            ErroServlet.setErro(request, response, empresaDAO, "Erro interno. Tente de novo mais tarde: " + e.getMessage(), lista, caminho);

        } catch (Exception e) {
            // Define mensagem genérica para erros não previstos
            e.printStackTrace();
            ErroServlet.setErro(request, response, empresaDAO, "Erro interno. Tente de novo mais tarde." + e.getMessage(), lista, caminho);
        }
    }
}