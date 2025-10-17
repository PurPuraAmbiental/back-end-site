package com.purpura.servlet.administrador;

import com.purpura.dao.AdministradorDAO;
import com.purpura.dao.DAO;
import com.purpura.exception.ConnectionFailedException;
import com.purpura.exception.NotFoundException;
import com.purpura.model.Administrador;
import com.purpura.util.Criptografia;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet(name = "InsertAdministradorServlet", value = "/administrador/insert")
public class InsertAdministradorServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws jakarta.servlet.ServletException, IOException {
        try {
            Map<String, String> params = new LinkedHashMap<>();
            request.getParameterMap().forEach((key, values) -> params.put(key, values[0]));
            if (params.containsKey("cSenha")) {
                String hash = Criptografia.criptografar(params.get("cSenha"));
                params.put("cSenha", hash);
            }
            Administrador model = new Administrador(params);
            DAO<Administrador> dao = new AdministradorDAO();
            dao.save(model);
            response.sendRedirect(request.getContextPath() + "/administrador/list");
        } catch (ConnectionFailedException | NotFoundException e) {
            request.setAttribute("erro", "Erro ao inserir Administrador: " + e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
            rd.forward(request, response);
        } catch (ParseException e) {
            request.setAttribute("erro", "Erro ao processar os parâmetros: " + e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
            rd.forward(request, response);
        }
    }
}
