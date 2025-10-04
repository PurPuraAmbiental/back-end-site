package com.purpura.servlet;

import com.purpura.dao.DAO;
import com.purpura.dao.DAOManager;
import com.purpura.exception.ConnectionFailedException;
import com.purpura.exception.DAONotFoundException;
import com.purpura.model.Administrador;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        try{
            DAO<Administrador> administradorDAO =
                    (DAO<Administrador>) DAOManager.getDAO("Administrador");

            Administrador administrador = administradorDAO.findByAttribute("email", email);

            if (administrador != null && administrador.getCSenha().equals(senha)) {
                HttpSession session = request.getSession();
                session.setAttribute("usuario", administrador);

                // Redireciona para a página do crud
                response.sendRedirect(request.getContextPath() + "/private.html");
            } else {
                // Login inválido
                request.setAttribute("erro", "E-mail ou senha incorretos.");
                RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                rd.forward(request, response);
            }
        } catch (DAONotFoundException e){
            e.printStackTrace();
        } catch (ConnectionFailedException e) {
            e.printStackTrace();
        }
    }
}
