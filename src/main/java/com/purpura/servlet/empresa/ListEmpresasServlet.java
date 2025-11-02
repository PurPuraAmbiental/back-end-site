package com.purpura.servlet.empresa;

import com.purpura.common.ErroServlet;
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
        EmpresaDAO empresaDAO = new EmpresaDAO();
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
            ErroServlet.setErro(request, response, empresaDAO, "Erro interno. Tente de novo mais tarde." + e.getMessage(), "listaEmpresa", "/WEB-INF/CRUD/empresa.jsp");
        }
    }
}