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

@WebServlet(name = "DeleteAdministradorServlet", value = "/administrador/delete")
public class DeleteAdministradorServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws jakarta.servlet.ServletException, IOException {
        String cEmail = request.getParameter("cEmail");
        DAO<?> dao = new AdministradorDAO();
        String caminho = "/CRUD/administrador.jsp";
        String lista = "listaAdministradores";
        try {
            dao.delete(cEmail);
            response.sendRedirect(request.getContextPath() + "/administrador/list");
        } catch (ConnectionFailedException | NotFoundException e) {
            e.printStackTrace();
            ErroServlet.setErro(request, response, dao, "Erro ao deletar Administrador: " + e.getMessage(), lista, caminho);
        }
    }
}
