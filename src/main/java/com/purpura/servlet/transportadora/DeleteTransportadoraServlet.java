package com.purpura.servlet.transportadora;

import com.purpura.dao.DAO;
import com.purpura.dao.TransportadoraDAO;
import com.purpura.exception.ConnectionFailedException;
import com.purpura.exception.NotFoundException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "DeleteTransportadoraServlet", value = "/transportadora/delete")
public class DeleteTransportadoraServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws jakarta.servlet.ServletException, IOException {
        String idParam = request.getParameter("nCdTransporte");
        try {
            int id = Integer.parseInt(idParam);
            TransportadoraDAO dao = new TransportadoraDAO();
            dao.delete(id);
            List<?> transportadora = dao.findAll();
            request.setAttribute("listaTransportadoras", transportadora);
            request.getRequestDispatcher("/CRUD/transportadora.jsp").forward(request, response);
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
