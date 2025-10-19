package com.purpura.servlet.empresa;

import com.purpura.common.Regex;
import com.purpura.dao.DAO;
import com.purpura.dao.EmpresaDAO;
import com.purpura.exception.ConnectionFailedException;
import com.purpura.exception.NotFoundException;
import com.purpura.model.Empresa;
import com.purpura.util.Criptografia;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "InsertEmpresaServlet", value = "/empresa/insert")
public class InsertEmpresaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws jakarta.servlet.ServletException, IOException {
        String email = request.getParameter("cEmail");
        String cnpj = request.getParameter("cCnpj");
        if (!Regex.validarEmail(email)) {
            request.setAttribute("erro", "Email inválido!");
            request.getRequestDispatcher("/CRUD/empresas.jsp").forward(request, response);
            return;  // Impede que o código continue
        }

        // Validação de CNPJ
        if (!Regex.validarSenha(cnpj)) {
            request.setAttribute("erro", "CNPJ inválido!");
            request.getRequestDispatcher("/CRUD/empresas.jsp").forward(request, response); // Mantém o popup aberto
            return;  // Impede que o código continue
        }

        try {
            Map<String, String> params = new LinkedHashMap<>();
            request.getParameterMap().forEach((key, values) -> params.put(key, values[0]));
            if (params.containsKey("cSenha")) {
                String hash = Criptografia.criptografar(params.get("cSenha"));
                params.put("cSenha", hash);
            }
            Empresa model = new Empresa(params);
            DAO<Empresa> dao = new EmpresaDAO();
            dao.save(model);

            List<?> empresas = dao.findAll();
            request.setAttribute("listaEmpresas",  empresas);
            request.getRequestDispatcher("/CRUD/empresas.jsp").forward(request, response);
        } catch (ConnectionFailedException | NotFoundException e) {
            request.setAttribute("erro", "Erro ao inserir Empresa: " + e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
            rd.forward(request, response);
        } catch (ParseException e) {
            request.setAttribute("erro", "Erro ao processar os parâmetros: " + e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
            rd.forward(request, response);
        }
    }
}
