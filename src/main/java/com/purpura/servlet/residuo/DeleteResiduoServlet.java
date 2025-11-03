package com.purpura.servlet.residuo;

import com.purpura.common.ErroServlet;
import com.purpura.dao.ResiduoDAO;
import com.purpura.exception.ConnectionFailedException;
import com.purpura.exception.NotFoundException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.purpura.common.Constants.ERROR_PAGE;

/**
 *  Servlet responsável por realizar a exclusão de um resíduo do sistema,
 *  com base no identificador (ID) informado pelo formulário JSP.
 *
 *  Este servlet atua sobre a tabela de resíduos, removendo o registro
 *  correspondente ao código recebido e redirecionando o usuário para
 *  a listagem após exclusão bem-sucedida.
 *
 *  Caminho: /residuo/delete
 *
 *  Autores: Bruna de Jesus e Kevin de Oliveira
 */
@WebServlet(name = "DeleteResiduoServlet", value = "/residuo/delete")
public class DeleteResiduoServlet extends HttpServlet {

    /**
     * Método responsável por processar a requisição de exclusão de um resíduo.
     * É acionado quando o usuário envia um formulário com o código (ID)
     * do resíduo que deseja excluir.
     *
     * @param request  objeto que contém os dados enviados pelo formulário JSP
     * @param response objeto utilizado para enviar respostas ao cliente
     * @throws jakarta.servlet.ServletException caso ocorra erro interno no servlet
     * @throws IOException caso haja falha de comunicação com o cliente
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws jakarta.servlet.ServletException, IOException {

        // Captura o código do resíduo enviado pelo formulário
        String idResiduo = request.getParameter("nCdResiduo");

        // Cria o DAO responsável pelas operações de banco de dados com resíduos
        ResiduoDAO dao = new ResiduoDAO();

        // Caminho e nome da lista usados em caso de erro
        String lista = "listaResiduos";
        String caminho = "/CRUD/residuos.jsp";

        try {
            // Converte o ID recebido de String para inteiro
            int id = Integer.parseInt(idResiduo);

            // Executa a exclusão do resíduo no banco de dados
            dao.delete(id);

            // Redireciona o usuário para a listagem de resíduos após exclusão bem-sucedida
            response.sendRedirect(request.getContextPath() + "/residuo/list");

        } catch (ConnectionFailedException | NotFoundException e) {
            e.printStackTrace();
            ErroServlet.setErro(request, response, dao, e, "listaNome", ERROR_PAGE);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            ErroServlet.setErro(request, response, dao,
                    "Erro ao processar parâmetros.",
                    lista, ERROR_PAGE);
        } catch (Exception e) {
            //Erro genérico (não previsto)
            // Captura qualquer outra exceção inesperada que possa ocorrer no fluxo
            ErroServlet.setErro(request, response, dao,
                    "Ocorreu um erro inesperado.",
                    lista, ERROR_PAGE);
        }
    }
}
