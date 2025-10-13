package com.purpura.servlet.administrador;

import com.purpura.dao.AdministradorDAO;
import com.purpura.exception.ConnectionFailedException;
import com.purpura.exception.NotFoundException;
import com.purpura.model.Administrador;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "FindAdministradorByIdServlet", value = "/administrador/find")
public class FindAdministradorByIdServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws jakarta.servlet.ServletException, IOException {
        String cEmail = request.getParameter("cEmail");
        try {
            AdministradorDAO dao = new AdministradorDAO();
            Administrador adm = dao.findById(cEmail);
            response.setContentType("text/plain;charset=UTF-8");
            response.getWriter().write(adm.toString());
        } catch (ConnectionFailedException | NotFoundException e) {
            request.setAttribute("erro", "Erro ao buscar Administrador: " + e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
            rd.forward(request, response);
        }
    }
}
