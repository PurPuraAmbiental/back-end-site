package com.purpura.servlet.transporte;

import com.purpura.dao.DAO;
import com.purpura.dao.TransporteDAO;
import com.purpura.exception.ConnectionFailedException;
import com.purpura.exception.NotFoundException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "DeleteTransporteServlet", value = "/transporte/delete")
public class DeleteTransporteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws jakarta.servlet.ServletException, IOException {
        String idParam = request.getParameter("nCdTransporte");
        try {
            int id = Integer.parseInt(idParam);
            DAO<?> dao = new TransporteDAO();
            dao.delete(id);
            response.sendRedirect(request.getContextPath() + "/transporte/list");
        } catch (NumberFormatException e) {
            request.setAttribute("erro", "Parâmetro inválido para nCdTransporte");
            RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
            rd.forward(request, response);
        } catch (ConnectionFailedException | NotFoundException e) {
            request.setAttribute("erro", "Erro ao deletar Transporte: " + e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
            rd.forward(request, response);
        }
    }
}
