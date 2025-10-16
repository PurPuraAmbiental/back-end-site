package com.purpura.servlet.enderecoEmpresa;

import com.purpura.dao.DAO;
import com.purpura.dao.EnderecoEmpresaDAO;
import com.purpura.exception.ConnectionFailedException;
import com.purpura.exception.NotFoundException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "DeleteEnderecoEmpresaServlet", value = "/endereco-empresa/delete")
public class DeleteEnderecoEmpresaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws jakarta.servlet.ServletException, IOException {
        String idStr = request.getParameter("nCdEnderecoEmpresa");
        try {
            int id = Integer.parseInt(idStr);
            DAO<?> dao = new EnderecoEmpresaDAO();
            dao.delete(id);
            response.sendRedirect(request.getContextPath() + "/endereco-empresa/list");
        } catch (ConnectionFailedException | NotFoundException | NumberFormatException e) {
            request.setAttribute("erro", "Erro ao deletar EnderecoEmpresa: " + e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
            rd.forward(request, response);
        }
    }
}
