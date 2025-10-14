package com.purpura.servlet.telefone;

import com.purpura.dao.TelefoneDAO;
import com.purpura.exception.ConnectionFailedException;
import com.purpura.exception.NotFoundException;
import com.purpura.model.Telefone;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "FindTelefoneByIdServlet", value = "/telefone/find")
public class FindTelefoneByIdServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws jakarta.servlet.ServletException, IOException {
        String idStr = request.getParameter("nCdTelefone");
        try {
            int id = Integer.parseInt(idStr);
            TelefoneDAO dao = new TelefoneDAO();
            Telefone telefone = dao.findById(id);
            response.setContentType("text/plain;charset=UTF-8");
            response.getWriter().write(telefone.toString());
        } catch (ConnectionFailedException | NotFoundException | NumberFormatException e) {
            request.setAttribute("erro", "Erro ao buscar Telefone: " + e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
            rd.forward(request, response);
        }
    }
}
