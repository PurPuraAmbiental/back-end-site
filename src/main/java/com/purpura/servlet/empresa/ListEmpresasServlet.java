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

        String filtro = request.getParameter("filtro");
        List<Empresa> empresas = null;

        try {
            EmpresaDAO empresaDAO = new EmpresaDAO();

            if ("residuo".equals(filtro)) {
                String possuiResiduo = request.getParameter("possuiResiduo");

                if (possuiResiduo != null && !possuiResiduo.isBlank() && "0".equals(possuiResiduo)) {
                    empresas = empresaDAO.buscarEmpresasSemResiduos();
                }
                else if (possuiResiduo != null && !possuiResiduo.isBlank() && "1".equals(possuiResiduo)) {
                    empresas = empresaDAO.buscarEmpresasComResiduos();
                }
                else {
                    empresas = empresaDAO.findAll();
                }
            }

            else if ("atividade".equals(filtro)) {
                String ativa = request.getParameter("ativa");
                if (ativa != null && !ativa.isBlank()) {
                    empresas = empresaDAO.findAllByAttribute("cAtivo", ativa);
                } else {
                    empresas = empresaDAO.findAll();
                }
            }
            else {
                empresas = empresaDAO.findAll();
            }

            request.setAttribute("listaEmpresas", empresas);
            request.getRequestDispatcher("/CRUD/empresas.jsp").forward(request, response);
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