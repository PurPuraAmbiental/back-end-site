package com.purpura.servlet.list;

import com.purpura.dao.DAOManager;
import com.purpura.dao.EnderecoEmpresaDAO;
import com.purpura.dto.EndecoEmpresaView;
import com.purpura.exception.ConnectionFailedException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListEnderecosEmpresaServlet", value = "/list/endereco-empresa")
public class ListEnderecosEmpresaServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            EnderecoEmpresaDAO enderecoDAO = (EnderecoEmpresaDAO) DAOManager.getDAO("EnderecoEmpresa");
            List<EndecoEmpresaView> enderecos = enderecoDAO.listarComEmpresa();

            request.setAttribute("listaEnderecos", enderecos);

            RequestDispatcher rd = request.getRequestDispatcher("/private/enderecos.jsp");
            rd.forward(request, response);

        } catch (ConnectionFailedException e) {
            request.setAttribute("erro", "Erro ao carregar lista de endereços: " + e.getMessage());
            e.printStackTrace();
            RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            request.setAttribute("erro", "Erro inesperado ao buscar Endereços: " + e.getMessage());
            e.printStackTrace();
            RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
            rd.forward(request, response);
        }
    }
}