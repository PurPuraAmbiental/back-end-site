package com.purpura.servlet.transportadora;

import com.purpura.dao.TransportadoraDAO;
import com.purpura.exception.ConnectionFailedException;
import com.purpura.exception.NotFoundException;
import com.purpura.model.Transportadora;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "FindTransportadoraByIdServlet", value = "/transportadora/find")
public class FindTransportadoraByIdServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws jakarta.servlet.ServletException, IOException {
        String idParam = request.getParameter("nCdTransporte");
        try {
            int id = Integer.parseInt(idParam);
            TransportadoraDAO dao = new TransportadoraDAO();
            Transportadora transportadora = dao.findById(id);
            response.setContentType("text/plain;charset=UTF-8");
            response.getWriter().write(transportadora.toString());
        } catch (NumberFormatException e) {
            request.setAttribute("erro", "Parâmetro inválido para nCdTransporte");
            RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
            rd.forward(request, response);
        } catch (ConnectionFailedException | NotFoundException e) {
            request.setAttribute("erro", "Erro ao buscar Transporte: " + e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
            rd.forward(request, response);
        }
    }
}
