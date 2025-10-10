package com.purpura.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet()
public class InsertPageServlet extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String email =  request.getParameter("email");
        String senha =  request.getParameter("senha");
        request.setAttribute("email", email);
        request.setAttribute("senha", senha);
        request.getRequestDispatcher("WEB-INF/teste.jsp").forward(request, response);

    }
}
