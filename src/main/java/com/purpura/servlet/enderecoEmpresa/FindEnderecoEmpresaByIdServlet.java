package com.purpura.servlet.enderecoEmpresa;

import com.purpura.dao.EnderecoEmpresaDAO;
import com.purpura.exception.ConnectionFailedException;
import com.purpura.exception.NotFoundException;
import com.purpura.model.EnderecoEmpresa;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "FindEnderecoEmpresaByIdServlet", value = "/endereco-empresa/find")
public class FindEnderecoEmpresaByIdServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws jakarta.servlet.ServletException, IOException {
        String idStr = request.getParameter("nCdEnderecoEmpresa");
        try {
            int id = Integer.parseInt(idStr);
            EnderecoEmpresaDAO dao = new EnderecoEmpresaDAO();
            EnderecoEmpresa endereco = dao.findById(id);
            response.setContentType("text/plain;charset=UTF-8");
            response.getWriter().write(endereco.toString());
        } catch (ConnectionFailedException | NotFoundException | NumberFormatException e) {
            request.setAttribute("erro", "Erro ao buscar EnderecoEmpresa: " + e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
            rd.forward(request, response);
        }
    }
}
