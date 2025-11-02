package com.purpura.servlet.enderecoEmpresa;

import com.purpura.common.ErroServlet;
import com.purpura.dao.EnderecoEmpresaDAO;
import com.purpura.exception.ConnectionFailedException;
import com.purpura.exception.NotFoundException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 *  Servlet responsável por realizar a exclusão de um endereço de empresa
 *  do sistema, com base no identificador (ID) informado no formulário JSP.
 *
 *  Este servlet atua sobre a tabela de endereços de empresas,
 *  removendo o registro correspondente ao ID recebido.
 *
 *  Caminho: /endereco-empresa/delete
 *
 *  Autores: Bruna de Jesus e Kevin de Oliveira
 */
@WebServlet(name = "DeleteEnderecoEmpresaServlet", value = "/endereco-empresa/delete")
public class DeleteEnderecoEmpresaServlet extends HttpServlet {

    /**
     * Método responsável por processar a exclusão de um endereço de empresa.
     * É acionado quando o usuário envia um formulário com o código (ID)
     * do endereço que deseja excluir.
     *
     * @param request  objeto que contém os dados enviados pelo formulário JSP
     * @param response objeto utilizado para enviar respostas ao cliente
     * @throws jakarta.servlet.ServletException caso ocorra erro interno no servlet
     * @throws IOException caso haja falha de comunicação com o cliente
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws jakarta.servlet.ServletException, IOException {

        // Captura o identificador do endereço, sendo um hidden no formulario
        String idEndereco = request.getParameter("nCdEnderecoEmpresa");

        // Cria o DAO responsável pelas operações de endereço de empresa
        EnderecoEmpresaDAO dao = new EnderecoEmpresaDAO();

        // Define variáveis auxiliares para redirecionamento em caso de erro
        String lista = "listaEnderecos";
        String caminho = "/CRUD/endereco.jsp";

        try {
            // Converte o ID recebido como String para inteiro
            int id = Integer.parseInt(idEndereco);

            // Executa a exclusão do endereço com base no ID informado
            dao.delete(id);

            // Redireciona o usuário para a listagem de endereços após exclusão bem-sucedida
            response.sendRedirect(request.getContextPath() + "/endereco-empresa/list");

        } catch (ConnectionFailedException e) {
            //Erro de conexão com o banco de dados
            // Ocorre quando há falha ao estabelecer comunicação com o banco (por exemplo, servidor fora do ar)
            ErroServlet.setErro(request, response, dao,
                    "Falha na conexão com o banco de dados. Tente novamente mais tarde.",
                    lista, caminho);

        } catch (NotFoundException e) {
            //Endereço não encontrado
            // Lançado quando o ID informado não corresponde a nenhum registro existente no banco
            ErroServlet.setErro(request, response, dao,
                    "Endereço não encontrado. Verifique o código informado.",
                    lista, caminho);

        } catch (NumberFormatException e) {
            //Erro de formatação numérica
            // Indica que o valor recebido do formulário não pôde ser convertido para inteiro
            // (por exemplo, campo vazio ou caracteres inválidos)
            ErroServlet.setErro(request, response, dao,
                    "Código de endereço inválido. Informe um número válido.",
                    lista, caminho);

        } catch (Exception e) {
            //Erro genérico (não previsto)
            // Captura qualquer outra exceção inesperada que possa ocorrer no fluxo
            ErroServlet.setErro(request, response, dao,
                    "Ocorreu um erro inesperado: " + e.getMessage(),
                    lista, caminho);
        }
    }
}
