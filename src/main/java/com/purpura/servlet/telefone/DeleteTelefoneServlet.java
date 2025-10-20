package com.purpura.servlet.telefone;

import com.purpura.dao.DAO;
import com.purpura.dao.TelefoneDAO;
import com.purpura.exception.ConnectionFailedException;
import com.purpura.exception.NotFoundException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "DeleteTelefoneServlet", value = "/telefone/delete")
public class DeleteTelefoneServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws jakarta.servlet.ServletException, IOException {
        String idStr = request.getParameter("nCdTelefone");
        try {
            int id = Integer.parseInt(idStr);
            DAO<?> dao = new TelefoneDAO();
            dao.delete(id);
            response.sendRedirect(request.getContextPath() + "/CRUD/telefone.jsp");
        } catch (ConnectionFailedException | NotFoundException | NumberFormatException e) {
            request.setAttribute("erro", "Erro ao deletar Telefone: " + e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
            rd.forward(request, response);
        }
    }
}
