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

import java.io.IOException;
import java.util.List;


@WebServlet(name = "FindAllServlet", value = "/findAll")
public class FindAllServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String tabelaNome = request.getParameter("tabelaNome");
        try{
            DAO<Model> dao = (DAO<Model>) DAOManager.getDAO(tabelaNome);

           List<Model> models = dao.findAll();
            //alteração tinha 30
           request.setAttribute("models", models);
            request.setAttribute("tabela", tabelaNome);
            request.setAttribute("saida", "Registro encontrado com sucesso!");

            request.getRequestDispatcher("WEB-INF/ListarAdm.jsp").forward(request, response);

            response.setStatus(HttpServletResponse.SC_OK);
//            for(Model model : models){
//                response.getWriter().write(model.toString() + "\n--------------------\n");
//            }
        } catch (DAONotFoundException e){
            request.setAttribute("erro", "Tabela não encontrada: " + tabelaNome);
            RequestDispatcher rd = request.getRequestDispatcher("erro.jsp");
            rd.forward(request, response);
            e.printStackTrace();
        } catch (ConnectionFailedException | NotFoundException e) {
            // Erros de banco
            request.setAttribute("erro", "Erro ao acessar o banco: " + e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("erro.jsp");
            rd.forward(request, response);
            e.printStackTrace();
        }
    }
}
