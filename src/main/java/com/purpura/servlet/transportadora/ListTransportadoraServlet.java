package com.purpura.servlet.transportadora;

import com.purpura.dao.DAO;
import com.purpura.dao.TransportadoraDAO;
import com.purpura.exception.ConnectionFailedException;
import com.purpura.exception.NotFoundException;
import com.purpura.model.Transportadora;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListTransportadoraServlet", value = "/transportadora/list")
public class ListTransportadoraServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String filtro = request.getParameter("filtro");

        try {
            TransportadoraDAO transportadoraDAO = new TransportadoraDAO();

            String nomeTransportadora = request.getParameter("nomeTransportadora");
            String regiao = request.getParameter("regiao");

            List<Transportadora> transportadoras = transportadoraDAO.listarTransportadorasFiltradas(nomeTransportadora, regiao);

            request.setAttribute("listaTransportadoras", transportadoras);
            request.setAttribute("regiao", regiao);

            request.setAttribute("listaTransportadoras", transportadoras);

            RequestDispatcher rd = request.getRequestDispatcher("/CRUD/transportadora.jsp");
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