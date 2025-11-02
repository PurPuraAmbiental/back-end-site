package com.purpura.servlet.empresa;

import com.purpura.common.ErroServlet;
import com.purpura.dao.*;
import com.purpura.exception.ConnectionFailedException;
import com.purpura.exception.NotFoundException;
import com.purpura.model.Empresa;
import com.purpura.model.EnderecoEmpresa;
import com.purpura.model.Residuo;
import com.purpura.model.Telefone;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import static com.purpura.common.Constants.ERROR_PAGE;

/**
 *  Servlet responsável por realizar a exclusão de uma empresa do sistema,
 *  removendo também os registros associados em tabelas dependentes, como
 *  endereço, telefone e resíduos.
 *
 *  Este servlet garante a integridade referencial ao apagar primeiro as
 *  entidades relacionadas à empresa, antes de excluir o registro principal.
 *
 *  Caminho: /empresa/delete
 *
 *  Autores: Bruna de Jesus e Kevin de Oliveira
 */
@WebServlet(name = "DeleteEmpresaServlet", value = "/empresa/delete")
public class DeleteEmpresaServlet extends HttpServlet {

    /**
     * Método responsável por processar a requisição de exclusão de uma empresa.
     * É acionado quando o usuário envia um formulário com o CNPJ da empresa
     * que deseja remover do sistema.
     *
     * @param request  objeto que contém os dados enviados pelo formulário (JSP)
     * @param response objeto usado para enviar respostas ao cliente (redirecionamento ou erro)
     * @throws jakarta.servlet.ServletException se ocorrer erro interno no servlet
     * @throws IOException se houver falha de comunicação com o cliente
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws jakarta.servlet.ServletException, IOException {

        // Captura o CNPJ informado no formulário
        String ccnpj = request.getParameter("cCnpj");

        // Cria o DAO principal responsável pelas operações de empresa
        DAO<Empresa> dao = new EmpresaDAO();

        // Caminho e identificador da lista usados em caso de erro
        String caminho = "/CRUD/empresa.jsp";
        String lista = "listaEmpresas";

        try {
            // Antes de excluir a empresa, é necessário apagar registros
            // em tabelas que dependem da chave primária (CNPJ)

            // Exclui o endereço associado à empresa
            DAO<EnderecoEmpresa> enderecoEmpresaDAO = new EnderecoEmpresaDAO();
            enderecoEmpresaDAO.deleteByAttribute("cCnpj", ccnpj);

            // Exclui os resíduos vinculados à empresa
            DAO<Residuo> residuoDAO = new ResiduoDAO();
            residuoDAO.deleteByAttribute("cCnpj", ccnpj);

            // Exclui os telefones cadastrados para a empresa
            DAO<Telefone> telefoneDAO = new TelefoneDAO();
            telefoneDAO.deleteByAttribute("cCnpj", ccnpj);

            // Agora, com as dependências removidas, apaga o registro principal da empresa
            dao.delete(ccnpj);

            // Redireciona para a página de listagem de empresas após exclusão bem-sucedida
            response.sendRedirect(request.getContextPath() + "/empresa/list");

        } catch (ConnectionFailedException | NotFoundException e) {
            e.printStackTrace();
            ErroServlet.setErro(request, response, dao, e, "listaNome", ERROR_PAGE);
        }
    }
}
