package com.purpura.servlet.enderecoEmpresa;

import com.purpura.common.ErroServlet;
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
        EnderecoEmpresaDAO dao = new EnderecoEmpresaDAO();
        String lista = "listaEnderecos";
        String caminho = "/CRUD/endereco.jsp";
        try {
            int id = Integer.parseInt(idEndereco);
            dao.delete(id);
            response.sendRedirect(request.getContextPath() + "/endereco-empresa/list");
        } catch (ConnectionFailedException | NotFoundException | NumberFormatException e) {
            ErroServlet.setErro(request, response, dao, "Erro ao deletar EnderecoEmpresa: " + e.getMessage(), lista, caminho);
        }
    }
}
