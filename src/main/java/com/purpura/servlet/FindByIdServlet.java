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

@WebServlet(name = "FindByIdServlet", value="/findId")
public class FindByIdServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws jakarta.servlet.ServletException, java.io.IOException{
        String tabelaNome = request.getParameter("tabelaNome");
        String pk = request.getParameter("id");
        try{
            DAO<Model> dao = (DAO<Model>) DAOManager.getDAO(tabelaNome);
            Model model = null;
            try {
                int id = Integer.parseInt(pk);
                model = dao.findById(id);
            } catch (NumberFormatException e){
                model = dao.findById(pk);
            }

            if (model == null) {
                throw new NotFoundException(tabelaNome, pk);
            }

            request.setAttribute("tabela", tabelaNome);
            request.setAttribute("saida", "Registro encontrado com sucesso!");

            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write(model.toString());
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
