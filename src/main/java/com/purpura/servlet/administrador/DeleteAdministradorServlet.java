package com.purpura.servlet.administrador;

import com.purpura.common.ErroServlet;
import com.purpura.dao.AdministradorDAO;
import com.purpura.dao.DAO;
import com.purpura.exception.ConnectionFailedException;
import com.purpura.exception.NotFoundException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import static com.purpura.common.Constants.ERROR_PAGE;

/**
 *      Servlet responsável por realizar a exclusão de um administrador
 *      do sistema, com base no e-mail informado no formulário.
 *
 *  Autores: Bruna de Jesus e Kevin de Oliveira
 *  */
@WebServlet(name = "DeleteAdministradorServlet", value = "/administrador/delete")
public class DeleteAdministradorServlet extends HttpServlet {

    /**
     * Este método é chamado quando o usuário solicita a exclusão
     * de um administrador cadastrado no sistema.
     *
     * @param request  objeto que contém os dados enviados pela página JSP
     * @param response objeto usado para enviar respostas ao cliente
     * @throws jakarta.servlet.ServletException caso ocorra erro interno do servlet
     * @throws IOException caso ocorra falha na comunicação com o cliente
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws jakarta.servlet.ServletException, IOException {

        // Captura o e-mail do administrador enviado pelo formulário
        String cEmail = request.getParameter("cEmail");

        // Cria o DAO responsável pelas operações com administradores
        DAO<?> dao = new AdministradorDAO();

        // Em caso de erro a pagina sera atualizada com essas informações, junto ao erro
        //sendo parametros do metodo
        String caminho = "/WEB-INF/CRUD/administrador.jsp";
        String lista = "listaAdministradores";

        try {
            // Executa a exclusão do administrador com base no e-mai
            dao.delete(cEmail);

            // Caso dê certo, redireciona para o servlet de listar
            response.sendRedirect(request.getContextPath() + "/administrador/list");
        } catch (ConnectionFailedException | NotFoundException e) {
            e.printStackTrace();
            ErroServlet.setErro(request, response, dao, e, lista, ERROR_PAGE);
        }
    }
}
