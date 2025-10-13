package com.purpura.servlet.list;

import com.purpura.dao.DAO;
import com.purpura.dao.DAOManager;
import com.purpura.dao.TransporteDAO;
import com.purpura.exception.ConnectionFailedException;
import com.purpura.exception.NotFoundException;
import com.purpura.model.Transporte;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListTransporteServlet", value = "/transporte/list")
public class ListTransportesServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            DAO<Transporte> transporteDAO = new TransporteDAO();

            List<Transporte> transportes = transporteDAO.findAll();

            request.setAttribute("listaTransportes", transportes);

            RequestDispatcher rd = request.getRequestDispatcher("/private/transportes.jsp");
            rd.forward(request, response);

        } catch (ConnectionFailedException | NotFoundException e) {
            request.setAttribute("erro", "Erro ao carregar lista de Transportes: " + e.getMessage());
            e.printStackTrace();
            RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            request.setAttribute("erro", "Erro inesperado ao buscar Transportes: " + e.getMessage());
            e.printStackTrace();
            RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
            rd.forward(request, response);
        }
    }
}