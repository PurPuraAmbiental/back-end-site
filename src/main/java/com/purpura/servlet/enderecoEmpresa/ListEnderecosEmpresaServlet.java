package com.purpura.servlet.enderecoEmpresa;

import com.purpura.dao.EnderecoEmpresaDAO;
import com.purpura.dto.EnderecoEmpresaView;
import com.purpura.exception.ConnectionFailedException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListEnderecosEmpresaServlet", value = "/endereco-empresa/list")
public class ListEnderecosEmpresaServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            EnderecoEmpresaDAO enderecoDAO = new EnderecoEmpresaDAO();

            String estado = request.getParameter("estado");
            String nomeEmpresa = request.getParameter("nomeEmpresa");

            List<EnderecoEmpresaView> enderecos = enderecoDAO.listarEnderecosFiltrados(estado, nomeEmpresa);

            request.setAttribute("listaEnderecos", enderecos);
            request.setAttribute("estado", estado);
            request.setAttribute("nomeEmpresa", nomeEmpresa);

            RequestDispatcher rd = request.getRequestDispatcher("/CRUD/endereco.jsp");
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