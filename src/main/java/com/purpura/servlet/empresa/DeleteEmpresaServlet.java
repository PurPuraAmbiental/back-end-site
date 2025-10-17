package com.purpura.servlet.empresa;

import com.purpura.dao.DAO;
import com.purpura.dao.EmpresaDAO;
import com.purpura.exception.ConnectionFailedException;
import com.purpura.exception.NotFoundException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "DeleteEmpresaServlet", value = "/empresa/delete")
public class DeleteEmpresaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws jakarta.servlet.ServletException, IOException {
        String ccnpj = request.getParameter("ccnpj");
        try {
            DAO<?> dao = new EmpresaDAO();
            dao.delete(ccnpj);
            List<?> empresas = dao.findAll();
            request.setAttribute("listaEmpresas",  empresas);
            response.sendRedirect(request.getContextPath() + "/CRUD/empresa.jsp");
            //   request.getRequestDispatcher("/CRUD/empresa.jsp").forward(request, response);
        } catch (ConnectionFailedException | NotFoundException e) {
            request.setAttribute("erro", "Erro ao deletar Empresa: " + e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
            rd.forward(request, response);
        }
    }
}
