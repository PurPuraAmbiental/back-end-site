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
        List<Transportadora> transportadoras = null;

        try {
            DAO<Transportadora> transportadoraDAO = new TransportadoraDAO();
            String parametroBusca = request.getParameter("parametroBusca");
            if ("nome".equals(filtro) && parametroBusca != null && !parametroBusca.isBlank()) {
                transportadoras = transportadoraDAO.findAllByAttribute("cNmTransportadora", parametroBusca);
            }
            else if ("cnpj".equals(filtro) && parametroBusca != null && !parametroBusca.isBlank()) {
                transportadoras = transportadoraDAO.findAllByAttribute("cCnpj", parametroBusca);
            }
            else if ("regiao".equals(filtro) && parametroBusca != null && !parametroBusca.isBlank()) {
                transportadoras = transportadoraDAO.findAllByAttribute("cRegiaoAtendida", parametroBusca);
            }
            else {
                transportadoras = transportadoraDAO.findAll();
            }

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