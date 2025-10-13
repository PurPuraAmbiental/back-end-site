package com.purpura.servlet.list;

import com.purpura.dao.DAOManager;
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
            List<TelefoneView> telefones = telefoneDAO.listarComEmpresa();

            request.setAttribute("listaTelefones", telefones);

            RequestDispatcher rd = request.getRequestDispatcher("/private/telefones.jsp");
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