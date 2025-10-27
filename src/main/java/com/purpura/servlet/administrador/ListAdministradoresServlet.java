package com.purpura.servlet.administrador;

import com.purpura.dao.AdministradorDAO;
import com.purpura.dao.DAO;
import com.purpura.exception.ConnectionFailedException;
import com.purpura.exception.NotFoundException;
import com.purpura.model.Administrador;
import com.purpura.model.Transportadora;
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

        try {
            AdministradorDAO administradorDAO = new AdministradorDAO();

            String nome = request.getParameter("nomeAdministrador");
            List<Administrador> administradores = administradorDAO.listarAdministradoresFiltrados(nome);

            request.setAttribute("listaAdministradores", administradores);
            request.setAttribute("nome", nome);

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