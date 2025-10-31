package com.purpura.servlet.transportadora;

import com.purpura.common.ErroServlet;
import com.purpura.common.Regex;
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
        String lista = "listaTransportadoras";
        String caminho = "/WEB-INF/CRUD/transportadora.jsp";
        DAO<Transportadora> dao = new TransportadoraDAO();
        try {
            Map<String, String> params = new LinkedHashMap<>();
            request.getParameterMap().forEach((key, values) -> params.put(key, values[0]));
            Transportadora model = new Transportadora(params);
            model.setCNmTransportadora(params.get("cNmTransportadora"));
            model.setCRegiaoAtendida(params.get("cRegiaoAtendida"));
            if (!Regex.validarEmail(model.getCEmail())) {
                ErroServlet.setErro(request, response, dao,"Não foi possível cadastrar transportadora. Insira um e-mail válido.", lista, caminho);
                return;
            }
            model.setCEmail(params.get("cEmail"));

            dao.update(model);
            response.sendRedirect(request.getContextPath() + "/transportadora/list");
        } catch (ConnectionFailedException | NotFoundException | NumberFormatException e) {
            request.setAttribute("erro", "Erro ao atualizar Transpordora: " + e.getMessage());

        }
    }
}

