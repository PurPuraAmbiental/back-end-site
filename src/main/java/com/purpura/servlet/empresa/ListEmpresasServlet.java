package com.purpura.servlet.empresa;

import com.purpura.dao.EmpresaDAO;
import com.purpura.exception.ConnectionFailedException;
import com.purpura.exception.NotFoundException;
import com.purpura.model.Empresa;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListEmpresaServlet", value = "/empresa/list")
public class ListEmpresasServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            EmpresaDAO empresaDAO = new EmpresaDAO();

            // Captura os parâmetros do filtro
            String nome = request.getParameter("nome");
            String email = request.getParameter("email");
            String cnpj = request.getParameter("cnpj");

            String ativoParam = request.getParameter("ativo");
            Character ativo = (ativoParam != null && !ativoParam.isBlank()) ? ativoParam.charAt(0) : null;

            String temResiduoParam = request.getParameter("temResiduo");
            Boolean temResiduo = (temResiduoParam != null && !temResiduoParam.isBlank())
                    ? "1".equals(temResiduoParam)
                    : null;

            // Chama o método do DAO com todos os filtros
            List<Empresa> empresas = empresaDAO.listarEmpresasFiltradas(
                    nome, email, cnpj, ativo, temResiduo
            );

            // Passa a lista para a JSP
            request.setAttribute("listaEmpresas", empresas);
            request.getRequestDispatcher("/WEB-INF/CRUD/empresa.jsp").forward(request, response);
        } catch (ConnectionFailedException | NotFoundException e) {
            request.setAttribute("erro", "Erro ao carregar lista de Empresas: " + e.getMessage());
            e.printStackTrace();
            RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            request.setAttribute("erro", "Erro inesperado ao buscar Empresas: " + e.getMessage());
            e.printStackTrace();
            RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
            rd.forward(request, response);
        }
    }
}