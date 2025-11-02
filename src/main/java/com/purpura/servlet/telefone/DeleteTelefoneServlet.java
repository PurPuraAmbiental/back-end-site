package com.purpura.servlet.telefone;

import com.purpura.common.ErroServlet;
import com.purpura.dao.TelefoneDAO;
import com.purpura.exception.ConnectionFailedException;
import com.purpura.exception.NotFoundException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 *  Servlet responsável por realizar a exclusão de um telefone cadastrado
 *  no sistema, com base no identificador (ID) informado pelo formulário JSP.
 *
 *  Este servlet remove o registro correspondente à chave primária informada
 *  e redireciona o usuário para a página de listagem de telefones.
 *
 *  Caminho: /telefone/delete
 *
 *  Autores: Bruna de Jesus e Kevin de Oliveira
 */
@WebServlet(name = "DeleteTelefoneServlet", value = "/telefone/delete")
public class DeleteTelefoneServlet extends HttpServlet {

    /**
     * Método responsável por processar a exclusão de um telefone.
     * É acionado quando o usuário envia o formulário com o código (ID)
     * do telefone que deseja remover do sistema.
     *
     * @param request  objeto que contém os dados enviados pelo formulário JSP
     * @param response objeto usado para enviar respostas ao cliente
     * @throws jakarta.servlet.ServletException caso ocorra erro interno no servlet
     * @throws IOException caso ocorra falha na comunicação com o cliente
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws jakarta.servlet.ServletException, IOException {

        // Captura o código do telefone enviado pelo formulário
        String idStr = request.getParameter("nCdTelefone");

        // Define variáveis auxiliares para controle de redirecionamento e exibição de erro
        String lista = "listaTelefones";
        String caminho = "/WEB-INF/CRUD/telefone.jsp";

        // Cria o DAO responsável pelas operações de telefone no banco de dados
        TelefoneDAO dao = new TelefoneDAO();

        try {
            // Converte o ID recebido (String) em inteiro
            int id = Integer.parseInt(idStr);

            // Executa a exclusão do telefone com base no ID informado
            dao.delete(id);

            // Redireciona o usuário para a página de listagem após exclusão bem-sucedida
            response.sendRedirect(request.getContextPath() + "/telefone/list");

        } catch (ConnectionFailedException e) {
            //Erro de conexão com o banco de dados
            // Ocorre quando há falha na comunicação com o servidor de banco (ex: inatividade, erro de rede)
            ErroServlet.setErro(request, response, dao,
                    "Falha na conexão com o banco de dados. Tente novamente mais tarde.",
                    lista, caminho);

        } catch (NotFoundException e) {
            //Telefone não encontrado
            // Lançado quando o ID informado não existe na tabela de telefones
            ErroServlet.setErro(request, response, dao,
                    "Telefone não encontrado. Verifique o código informado.",
                    lista, caminho);

        } catch (NumberFormatException e) {
            //Erro de formatação numérica
            // Indica que o valor informado não pôde ser convertido para inteiro
            // (por exemplo: campo vazio ou com caracteres inválidos)
            ErroServlet.setErro(request, response, dao,
                    "Código inválido. Informe um número válido para exclusão.",
                    lista, caminho);

        } catch (Exception e) {
            //Erro genérico (não previsto)
            // Captura qualquer outro tipo de falha inesperada durante a execução
            ErroServlet.setErro(request, response, dao,
                    "Ocorreu um erro inesperado ao deletar o telefone: " + e.getMessage(),
                    lista, caminho);
        }
    }
}
