package com.purpura.servlet.telefone;

import com.purpura.dao.TelefoneDAO;
import com.purpura.dto.TelefoneView;
import com.purpura.exception.ConnectionFailedException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListTelefonesServlet", value = "/telefone/list")
public class ListTelefonesServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            TelefoneDAO telefoneDAO = new TelefoneDAO();

            String nomeEmpresa = request.getParameter("nomeEmpresa");

            List<TelefoneView> telefones = telefoneDAO.listarTelefonesFiltrados(nomeEmpresa);

            request.setAttribute("listaTelefones", telefones);
            request.setAttribute("nomeEmpresa", nomeEmpresa);

            RequestDispatcher rd = request.getRequestDispatcher("/CRUD/telefone.jsp");
            rd.forward(request, response);

        } catch (ConnectionFailedException e) {
            request.setAttribute("erro", "Erro ao carregar lista de telefones: " + e.getMessage());
            e.printStackTrace();
            RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            request.setAttribute("erro", "Erro inesperado ao buscar Telefones: " + e.getMessage());
            e.printStackTrace();
            RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
            rd.forward(request, response);
        }
    }
}