package com.purpura.servlet;

import com.purpura.common.ModelCreator;
import com.purpura.dao.DAO;
import com.purpura.dao.DAOManager;
import com.purpura.exception.ConnectionFailedException;
import com.purpura.exception.DAONotFoundException;
import com.purpura.exception.NotFoundException;
import com.purpura.model.Model;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "UpdateByAttribute", value = "/updateAttribute")
public class UpdateByAttributeServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tabelaNome = request.getParameter("tabelaNome");
        String caminho = request.getParameter("caminho");
        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<String, String> params = new LinkedHashMap<>();

        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue()[0];

            // Ignora os campos de controle
            if (!key.equals("tabelaNome") && !key.equals("atributo") && !key.equals("valor")) {
                params.put(key, value);
            }
        }
        request.setAttribute("params", params);
        String coluna = request.getParameter("atributo");
        String valor = request.getParameter("valor");
        Object object = valor;
        try {
            Model model = ModelCreator.createModel(tabelaNome, params);
            DAO<Model> dao = (DAO<Model>) DAOManager.getDAO(tabelaNome);

            dao.updateByAttribute(model, coluna, object);

            //mandando a atualização para a pagina principal
            List<Model> lista = dao.findAll();
            request.setAttribute("models", lista);
            request.getRequestDispatcher(caminho
            ).forward(request, response);
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
            e.printStackTrace();

        } catch (ParseException e){
            // Erros de conversão de parâmetros
            request.setAttribute("erro", "Erro ao processar os parâmetros: " + e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("erro.jsp");
            rd.forward(request, response);
        }
    }
}
