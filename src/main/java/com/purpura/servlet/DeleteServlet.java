package com.purpura.servlet;

import com.purpura.dao.DAO;
import com.purpura.dao.DAOManager;
import com.purpura.exception.ConnectionFailedException;
import com.purpura.exception.DAONotFoundException;
import com.purpura.exception.NotFoundException;
import com.purpura.model.Model;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "DeleteServlet", value="/delete")
public class DeleteServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws jakarta.servlet.ServletException, java.io.IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String tabelaNome = request.getParameter("tabelaNome");

        try {
            DAO<Model> dao = (DAO<Model>) DAOManager.getDAO(tabelaNome);
            dao.delete(id);

            request.setAttribute("tabela", tabelaNome);
            request.setAttribute("saida", "Registro deletado com sucesso!");

            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("Registro deletado com sucesso na tabela " + tabelaNome);
        } catch (DAONotFoundException e) {
            request.setAttribute("erro", "Tabela não encontrada: " + tabelaNome);
            RequestDispatcher rd = request.getRequestDispatcher("erro.jsp");
            rd.forward(request, response);
        } catch (ConnectionFailedException | NotFoundException e) {
            // Erros de banco
            request.setAttribute("erro", "Erro ao acessar o banco: " + e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("erro.jsp");
            rd.forward(request, response);
            e.printStackTrace();
        }
    }
}
