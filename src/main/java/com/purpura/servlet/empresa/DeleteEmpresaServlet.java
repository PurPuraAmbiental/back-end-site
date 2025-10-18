package com.purpura.servlet.empresa;

import com.purpura.dao.*;
import com.purpura.model.Empresa;
import com.purpura.model.EnderecoEmpresa;
import com.purpura.model.Residuo;
import com.purpura.model.Telefone;
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
            //apaga os registro das tabelas fracas que dependem da sua primary key
            DAO<EnderecoEmpresa> enderecoEmpresaDAO = new EnderecoEmpresaDAO();
            enderecoEmpresaDAO.deleteByAttribute("ccnpj", ccnpj);

            DAO< Residuo> residuoDAO = new ResiduoDAO();
            residuoDAO.deleteByAttribute("ccnpj", ccnpj);

            DAO<Telefone> telefoneDAO = new TelefoneDAO();
            telefoneDAO.deleteByAttribute("ccnpj", ccnpj);

            //apaga o registro agora sem interferencia das suas dependentes
            DAO<Empresa> dao = new EmpresaDAO();
            dao.delete(ccnpj);
            List<?> empresas = dao.findAll();
            request.setAttribute("listaEmpresas",  empresas);
            request.getRequestDispatcher("/CRUD/empresas.jsp").forward(request, response);
            //   request.getRequestDispatcher("/CRUD/empresa.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            request.setAttribute("erro", "Erro ao deletar Empresa: " + e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
            rd.forward(request, response);
        }
    }
}
