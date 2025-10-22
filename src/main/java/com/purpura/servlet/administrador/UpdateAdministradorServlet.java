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
import java.util.List;
import java.util.Map;

@WebServlet(name = "UpdateAdministradorServlet", value = "/administrador/update")
public class UpdateAdministradorServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws jakarta.servlet.ServletException, IOException {
        String cEmail = request.getParameter("cEmail");
        String cSenha = request.getParameter("cSenha");
        String cnmadministrador = request.getParameter("cnmadministrador");
        try {
            Map<String, String> params = new LinkedHashMap<>();
            request.getParameterMap().forEach((key, values) -> params.put(key, values[0]));
            if (params.containsKey("cSenha")) {
                String hash = Criptografia.criptografar(params.get("cSenha"));
                params.put("cSenha", hash);
            }
            Administrador model = new Administrador(params);
            model.setCSenha(params.get("cSenha"));
            model.setCNmAdministrador(params.get("cNmAdministrador"));
            DAO<Administrador> dao = new AdministradorDAO();
            dao.update(model);
            List<Administrador> adm = dao.findAll();
            request.setAttribute("listaAdministradores", adm);
            response.sendRedirect(request.getContextPath() + "/administrador/list");
        } catch (ConnectionFailedException e) {
            System.out.println(e.getMessage());
            request.setAttribute("erro", "Erro ao atualizar Administrador: " + e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
            rd.forward(request, response); }
         catch (ParseException e) {
            System.out.println(e.getMessage());
            request.setAttribute("erro", "Erro ao processar os parâmetros: " + e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
            rd.forward(request, response);
        }
    }
}
