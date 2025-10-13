package com.purpura.servlet.empresa;

import com.purpura.dao.EmpresaDAO;
import com.purpura.exception.ConnectionFailedException;
import com.purpura.exception.NotFoundException;
import com.purpura.model.Empresa;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "FindEmpresaByIdServlet", value = "/empresa/find")
public class FindEmpresaByIdServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws jakarta.servlet.ServletException, IOException {
        String cCnpj = request.getParameter("cCnpj");
        try {
            EmpresaDAO dao = new EmpresaDAO();
            Empresa empresa = dao.findById(cCnpj);
            response.setContentType("text/plain;charset=UTF-8");
            response.getWriter().write(empresa.toString());
        } catch (ConnectionFailedException | NotFoundException e) {
            request.setAttribute("erro", "Erro ao buscar Empresa: " + e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
            rd.forward(request, response);
        }
    }
}
