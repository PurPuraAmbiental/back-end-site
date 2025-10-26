package com.purpura.servlet.transportadora;

import com.purpura.dao.DAO;
import com.purpura.dao.TransportadoraDAO;
import com.purpura.exception.ConnectionFailedException;
import com.purpura.exception.NotFoundException;
import com.purpura.model.Administrador;
import com.purpura.model.Transportadora;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "UpdateTransportadoraServlet", value = "/transportadora/update")
public class UpdateTransportadoraServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws jakarta.servlet.ServletException, IOException {
        try {
            Map<String, String> params = new LinkedHashMap<>();
            request.getParameterMap().forEach((key, values) -> params.put(key, values[0]));
            Transportadora model = new Transportadora(params);
            model.setCNmTransportadora(params.get("cNmTransporte"));
            model.setCRegiaoAtendida(params.get("cRegiaoAtendida"));
            model.setCEmail(params.get("cEmail"));
            DAO<Transportadora> dao = new TransportadoraDAO();
            dao.update(model);
            List<Transportadora> transportadoras = dao.findAll();
            request.setAttribute("listaTransportadoras", transportadoras);
            request.getRequestDispatcher("/CRUD/transportadora.jsp").forward(request, response);
        } catch (ConnectionFailedException | NotFoundException | NumberFormatException e) {
            request.setAttribute("erro", "Erro ao atualizar Transporte: " + e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
            rd.forward(request, response);
        }
    }
}

