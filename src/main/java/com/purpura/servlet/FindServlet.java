package com.purpura.servlet;

import com.purpura.dao.DAO;
import com.purpura.dao.DAOManager;
import com.purpura.exception.ConnectionFailedException;
import com.purpura.exception.DAONotFoundException;
import com.purpura.exception.NotFoundException;
import com.purpura.model.Model;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "FindServlet", value="/find")
public class FindServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws jakarta.servlet.ServletException, java.io.IOException{
        String tabelaNome = request.getParameter("tabelaNome");
        int id = Integer.parseInt(request.getParameter("id"));

        try{
            DAO<Model> dao = (DAO<Model>) DAOManager.getDAO(tabelaNome);
            Model model = dao.find(id);

            request.setAttribute("tabela", tabelaNome);
            request.setAttribute("saida", "Registro encontrado com sucesso!");

            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write(model.toString());
        } catch (DAONotFoundException e){
            request.setAttribute("erro", "Tabela não encontrada: " + tabelaNome);
            RequestDispatcher rd = request.getRequestDispatcher("erro.jsp");
            rd.forward(request, response);
        } catch (ConnectionFailedException | NotFoundException e) {
            // Erros de banco
            request.setAttribute("erro", "Erro ao acessar o banco: " + e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("erro.jsp");
            rd.forward(request, response);
        }
    }
}
