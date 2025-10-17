package com.purpura.servlet;

import com.purpura.dao.DAO;
import com.purpura.dao.DAOManager;
import com.purpura.exception.ConnectionFailedException;
import com.purpura.exception.DAONotFoundException;
import com.purpura.exception.NotFoundException;
import com.purpura.model.Administrador;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/login-auth")
public class LoginServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        boolean loginSucesso = false;

        try{
            DAO<Administrador> administradorDAO =
                    (DAO<Administrador>) DAOManager.getDAO("Administrador");

            Administrador administrador = administradorDAO.findByAttribute("cEmail", email);

            if (administrador != null) {
                String hashArmazenado = administrador.getCSenha();

                if(BCrypt.checkpw(senha, hashArmazenado)){
                    loginSucesso = true;
                    HttpSession session = request.getSession();
                    session.setAttribute("usuario", administrador);

                    // Redireciona para a página do crud
                    response.sendRedirect(request.getContextPath() + "/CRUD/crud.jsp");
                }
            }

            if (!loginSucesso) {
                request.setAttribute("erro", "E-mail ou senha incorretos.");
                RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                rd.forward(request, response);
            }

        } catch (DAONotFoundException e){
            request.setAttribute("erro", "Erro de conexão");
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        } catch (ConnectionFailedException e) {
            request.setAttribute("erro", "Erro de conexão");
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        } catch (NotFoundException e) {
            request.setAttribute("erro", "E-mail ou senha incorretos.");
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        }
    }
}
