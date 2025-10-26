package com.purpura.servlet.transportadora;

import com.purpura.common.Regex;
import com.purpura.dao.DAO;
import com.purpura.dao.TransportadoraDAO;
import com.purpura.exception.ConnectionFailedException;
import com.purpura.exception.NotFoundException;
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

@WebServlet(name = "InsertTransportadoraServlet", value = "/transportadora/insert")
public class InsertTransportadoraServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws jakarta.servlet.ServletException, IOException {
        try {
            Map<String, String> params = new LinkedHashMap<>();
            request.getParameterMap().forEach((key, values) -> params.put(key, values[0]));
            Transportadora model = new Transportadora(params);
            DAO<Transportadora> dao = new TransportadoraDAO();
            List<?> transportadora = dao.findAll();
            System.out.println(transportadora);
            Transportadora existente = dao.findById(model.getCCnpj());
            if(!Regex.validarCnpj(model.getCCnpj())){
                request.setAttribute("erro", "Nao foi possivel cadastrar transportadora. Insira um CNPJ valido.");
                request.setAttribute("listaTransportadoras", transportadora);
                request.getRequestDispatcher("/CRUD/transportadora.jsp").forward(request, response);
                return;
            } else if (existente != null) {
                request.setAttribute("erro", "Nao foi possivel cadastrar transportadora. CNPJ já cadastrado anteriormente.");
                request.setAttribute("listaTransportadoras", transportadora);
                request.getRequestDispatcher("/CRUD/transportadora.jsp").forward(request, response);
                return;
            } else if (!Regex.validarEmail(model.getCEmail())) {
                request.setAttribute("erro", "Não foi possivel cadastrar transportadora. Insira um E-mail valido.");
                request.setAttribute("listaTransportadoras", transportadora);
                request.getRequestDispatcher("/CRUD/transportadora.jsp").forward(request, response);
                return;
            }
            transportadora = dao.findAll();
            model.setCCnpj(model.getCCnpj().replace("/", "").replace(".", "").replace("-", ""));
            dao.save(model);
            request.setAttribute("listaTransportadoras", transportadora);
            request.getRequestDispatcher("/CRUD/transportadora.jsp").forward(request, response);
        } catch (ConnectionFailedException | NotFoundException | NumberFormatException e) {
            request.setAttribute("erro", "Erro ao inserir Transporte: " + e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
            rd.forward(request, response);
        }
    }
}