package com.purpura.servlet.residuo;

import com.purpura.dao.DAO;
import com.purpura.dao.ResiduoDAO;
import com.purpura.exception.ConnectionFailedException;
import com.purpura.exception.NotFoundException;
import com.purpura.model.Residuo;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet(name = "InsertResiduoServlet", value = "/residuo/insert")
public class InsertResiduoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws jakarta.servlet.ServletException, IOException {
        try {
            Map<String, String> params = new LinkedHashMap<>();
            request.getParameterMap().forEach((key, values) -> params.put(key, values[0]));
            Residuo model = new Residuo(params);
            DAO<Residuo> dao = new ResiduoDAO();
            dao.save(model);
            response.sendRedirect(request.getContextPath() + "/residuo/list");
        } catch (ConnectionFailedException | NotFoundException | NumberFormatException e) {
            request.setAttribute("erro", "Erro ao inserir Residuo: " + e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
            rd.forward(request, response);
        }
    }
}
