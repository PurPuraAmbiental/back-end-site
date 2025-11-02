package com.purpura.servlet.transportadora;

import com.purpura.common.ErroServlet;
import com.purpura.dao.DAO;
import com.purpura.dao.TransportadoraDAO;
import com.purpura.exception.ConnectionFailedException;
import com.purpura.exception.NotFoundException;
import com.purpura.model.Transportadora;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.purpura.common.Constants.ERROR_PAGE;

/**
 *  Servlet responsável por realizar a exclusão de uma transportadora
 *  do sistema, com base no CNPJ informado pelo formulário JSP.
 *
 *  Este servlet remove o registro correspondente à transportadora
 *  e redireciona o usuário para a página de listagem após exclusão bem-sucedida.
 *
 *  Caminho: /transportadora/delete
 *
 *  Autores: Bruna de Jesus e Kevin de Oliveira
 */
@WebServlet(name = "DeleteTransportadoraServlet", value = "/transportadora/delete")
public class DeleteTransportadoraServlet extends HttpServlet {

    /**
     * Método responsável por processar a requisição de exclusão de uma transportadora.
     * É acionado quando o usuário envia o formulário com o CNPJ da transportadora
     * que deseja remover.
     *
     * @param request  objeto que contém os dados enviados pelo formulário JSP
     * @param response objeto usado para enviar respostas ao cliente
     * @throws jakarta.servlet.ServletException caso ocorra erro interno no servlet
     * @throws IOException caso haja falha de comunicação com o cliente
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws jakarta.servlet.ServletException, IOException {

        // Nome da lista usada em caso de erro
        String lista = "listaTransportadoras";

        // Cria o DAO responsável pelas operações de banco de dados da transportadora
        DAO<Transportadora> dao = new TransportadoraDAO();

        // Captura o CNPJ informado no formulário
        String idParam = request.getParameter("cCnpj");

        try {
            // Executa a exclusão da transportadora com base no CNPJ informado
            dao.delete(idParam);

            // Redireciona o usuário para a listagem de transportadoras após exclusão bem-sucedida
            response.sendRedirect(request.getContextPath() + "/transportadora/list");

        } catch (NumberFormatException e) {
            e.printStackTrace();
            ErroServlet.setErro(request, response, dao, "Erro ao processar parametros.", lista, ERROR_PAGE);
        } catch (ConnectionFailedException | NotFoundException e) {
            e.printStackTrace();
            ErroServlet.setErro(request, response, dao, "Falha ao conectar ao banco de dados.", lista, ERROR_PAGE);
        } catch (Exception e) {
            e.printStackTrace();
            ErroServlet.setErro(request, response, dao,  "Ocorreu um erro inesperado.", lista, ERROR_PAGE);
        }
    }
}
