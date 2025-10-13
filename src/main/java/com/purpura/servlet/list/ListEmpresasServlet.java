package com.purpura.servlet.list;

import com.purpura.dao.DAO;
import com.purpura.dao.DAOManager;
import com.purpura.dao.EmpresaDAO;
import com.purpura.exception.ConnectionFailedException;
import com.purpura.exception.NotFoundException;
import com.purpura.model.Empresa;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListEmpresaServlet", value = "/empresa/list")
public class ListEmpresasServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            DAO<Empresa> empresaDAO = new EmpresaDAO();

            List<Empresa> empresas = empresaDAO.findAll();

            request.setAttribute("listaEmpresas", empresas);

            RequestDispatcher rd = request.getRequestDispatcher("/private/empresas.jsp");
            rd.forward(request, response);

        } catch (ConnectionFailedException | NotFoundException e) {
            request.setAttribute("erro", "Erro ao carregar lista de Empresas: " + e.getMessage());
            e.printStackTrace();
            RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            request.setAttribute("erro", "Erro inesperado ao buscar Empresas: " + e.getMessage());
            e.printStackTrace();
            RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
            rd.forward(request, response);
        }
    }
}