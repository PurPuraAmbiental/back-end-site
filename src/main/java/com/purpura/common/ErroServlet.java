package com.purpura.common;

import com.purpura.dao.DAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ErroServlet {
    public static void setErro(HttpServletRequest request, HttpServletResponse response, DAO<?> dao, String mensagem, String lista, String caminho)
            throws jakarta.servlet.ServletException, IOException {
        request.setAttribute(lista, dao.findAll());
        request.setAttribute("erro", mensagem);
        request.getRequestDispatcher(caminho).forward(request, response);
    }
}
