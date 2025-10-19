package com.purpura.servlet.enderecoEmpresa;

import com.purpura.dao.EmpresaDAO;
import com.purpura.dao.EnderecoEmpresaDAO;
import com.purpura.dto.EnderecoEmpresaView;
import com.purpura.exception.ConnectionFailedException;
import com.purpura.exception.NotFoundException;
import com.purpura.model.Empresa;
import com.purpura.model.EnderecoEmpresa;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "InsertEnderecoEmpresaServlet", value = "/endereco-empresa/insert")
public class InsertEnderecoEmpresaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws jakarta.servlet.ServletException, IOException {
        try {
            Map<String, String> params = new LinkedHashMap<>();
            request.getParameterMap().forEach((key, values) -> params.put(key, values[0]));

            String nomeEmpresa = params.get("cNmEmpresa");


            EmpresaDAO empresaDAO = new EmpresaDAO();
            Empresa empresa = empresaDAO.findByAttribute("cNmEmpresa", nomeEmpresa);
            params.put("cCnpj", empresa.getCCnpj());
            EnderecoEmpresa model = new EnderecoEmpresa(params);
            EnderecoEmpresaDAO dao = new EnderecoEmpresaDAO();
            dao.save(model);


            List<EnderecoEmpresaView> EnderecoEmpresaView = dao.listarComEmpresa();
            request.setAttribute("listaEnderecos", EnderecoEmpresaView);
            request.getRequestDispatcher("/CRUD/endereco.jsp").forward(request, response);
        } catch (ConnectionFailedException | NotFoundException | NumberFormatException e) {
            request.setAttribute("erro", "Erro ao inserir Endereço: " + e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
            rd.forward(request, response);
        }
    }
}