package com.purpura.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet responsável por realizar o encerramento da sessão do usuário (logout).
 * Invalida a sessão atual e redireciona para a página de login, garantindo que o usuário
 * seja desconectado do sistema.
 *
 * @author Kevin de Oliveira
 */
@WebServlet(name = "LogoutServlet", value = "/logout")
 public class LogoutServlet extends HttpServlet {
    /**
     * Trata requisições GET para realizar o logout do usuário.
     * Invalida a sessão caso exista. Após o processo, redireciona para a tela de login.
     *
     * @param request  objeto HttpServletRequest que representa a requisição do cliente.
     * @param response objeto HttpServletResponse para resposta ao cliente.
     * @throws ServletException caso ocorra erro de servlet.
     * @throws IOException      caso ocorra erro de I/O.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }
        response.sendRedirect(request.getContextPath() + "/index.html");
    }
}