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

        // Nome da lista e caminho do JSP usados em caso de erro
        String lista = "listaTransportadoras";
        String caminho = "/WEB-INF/CRUD/transportadora.jsp";

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
            //Erro de formatação de parâmetro
            // Ocorre caso o valor fornecido não seja um formato válido (ex: vazio ou caracteres inválidos)
            ErroServlet.setErro(request, response, dao,
                    "Parâmetro inválido para nCdTransporte", lista, caminho);

        } catch (ConnectionFailedException e) {
            //Erro de conexão com o banco de dados
            // Indica que houve falha ao tentar conectar-se ao banco
            ErroServlet.setErro(request, response, dao,
                    "Falha na conexão com o banco de dados. Tente novamente mais tarde.", lista, caminho);

        } catch (NotFoundException e) {
            //Transportadora não encontrada
            // Lançado quando o CNPJ informado não corresponde a nenhum registro existente
            ErroServlet.setErro(request, response, dao,
                    "Transportadora não encontrada. Verifique o CNPJ informado.", lista, caminho);

        } catch (Exception e) {
            //Erro genérico (não previsto)
            // Captura quaisquer outros tipos de exceções inesperadas
            ErroServlet.setErro(request, response, dao,
                    "Ocorreu um erro inesperado ao deletar a transportadora: " + e.getMessage(),
                    lista, caminho);
        }
    }
}
