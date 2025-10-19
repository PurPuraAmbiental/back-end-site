package com.purpura.servlet.administrador;

import com.purpura.dao.AdministradorDAO;
import com.purpura.dao.DAO;
import com.purpura.exception.ConnectionFailedException;
import com.purpura.exception.NotFoundException;
import com.purpura.model.Administrador;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListAdministradorServlet", value = "/administrador/list")
public class ListAdministradoresServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String filtro = request.getParameter("filtro");
        List<Administrador> administradores = null;
        try {
            DAO<Administrador> administradorDAO = new AdministradorDAO();
            String parametroBusca = request.getParameter("parametroBusca");
            if ("nome".equals(filtro) && parametroBusca != null && !parametroBusca.isBlank()) {
                administradores = administradorDAO.findAllByAttribute("cNmAdministrador", parametroBusca);
            }
            else if ("email".equals(filtro) && parametroBusca != null && !parametroBusca.isBlank()) {
                administradores = administradorDAO.findAllByAttribute("cEmail", parametroBusca);
            }
            else {
                administradores = administradorDAO.findAll();
            }

            request.setAttribute("listaAdministradores", administradores);

            RequestDispatcher rd = request.getRequestDispatcher("/CRUD/administrador.jsp");
            rd.forward(request, response);

        } catch (ConnectionFailedException | NotFoundException e) {
            request.setAttribute("erro", "Erro ao carregar lista de Administradores: " + e.getMessage());
            e.printStackTrace();
            RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            request.setAttribute("erro", "Erro inesperado ao buscar Administradores: " + e.getMessage());
            e.printStackTrace();
            RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
            rd.forward(request, response);
        }
    }
}