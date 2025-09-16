package com.purpura.servlet;

import com.purpura.common.ModelCreator;
import com.purpura.dao.DAO;
import com.purpura.dao.DAOManager;
import com.purpura.exception.ConnectionFailedException;
import com.purpura.exception.DAONotFoundException;
import com.purpura.exception.NotFoundException;
import com.purpura.model.Model;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet(name = "UpdateServlet", value="/update")
public class UpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws jakarta.servlet.ServletException, java.io.IOException {
        // verifica nome da tabela no qual os dados serão inseridos
        String tabelaNome = request.getParameter("tabelaNome");

        // cria um map que guarda os atributos
        Map<String, String> params = new LinkedHashMap<>();
        request.setAttribute("params", params); // seta o map params como atributo da requisição

        request.getParameterMap().forEach((key, values) -> {
            if (!key.equals("tabelaNome")) {
                params.put(key, values[0]);
            }
        }); // adiciona os valores ao map, exceto o nome da tabela que não faz parte do model

        try{
            Model model = ModelCreator.createModel(tabelaNome, params);
            DAO<Model> dao = (DAO<Model>) DAOManager.getDAO(tabelaNome);

            dao.update(model);

            request.setAttribute("tabela", tabelaNome);
            request.setAttribute("saida", "Registro alterado com sucesso!");

            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("Registro atualizado com sucesso na tabela " + tabelaNome);
        } catch (DAONotFoundException e) {
            // Nenhum DAO encontrado para a tabela
            request.setAttribute("erro", e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("erro.jsp");
            rd.forward(request, response);

        } catch (ConnectionFailedException | NotFoundException e) {
            // Erros de banco
            request.setAttribute("erro", "Erro ao acessar o banco: " + e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("erro.jsp");
            rd.forward(request, response);

        } catch (ParseException e) {
            // Erros de conversão de parâmetros
            request.setAttribute("erro", "Erro ao processar os parâmetros: " + e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("erro.jsp");
            rd.forward(request, response);
        }
    }
}
