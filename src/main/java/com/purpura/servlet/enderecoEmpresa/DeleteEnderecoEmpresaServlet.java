package com.purpura.servlet.enderecoEmpresa;

import com.purpura.dao.DAO;
import com.purpura.dao.EmpresaDAO;
import com.purpura.dao.EnderecoEmpresaDAO;
import com.purpura.dto.EnderecoEmpresaView;
import com.purpura.exception.ConnectionFailedException;
import com.purpura.exception.NotFoundException;
import com.purpura.model.Empresa;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "DeleteEnderecoEmpresaServlet", value = "/endereco-empresa/delete")
public class DeleteEnderecoEmpresaServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws jakarta.servlet.ServletException, IOException {
        String idEndereco = request.getParameter("nCdEnderecoEmpresa");
        try {
            int id = Integer.parseInt(idEndereco);
            EnderecoEmpresaDAO dao = new EnderecoEmpresaDAO();
            dao.delete(id);
            List<EnderecoEmpresaView> endereco = dao.listarComEmpresa();
            request.setAttribute("listaEnderecos", endereco);
            request.getRequestDispatcher("/CRUD/endereco.jsp").forward(request, response);
        } catch (ConnectionFailedException | NotFoundException | NumberFormatException e) {
            request.setAttribute("erro", "Erro ao deletar EnderecoEmpresa: " + e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
            rd.forward(request, response);
        }
    }
}
