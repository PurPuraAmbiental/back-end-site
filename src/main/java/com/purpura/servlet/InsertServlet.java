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
import java.util.List;
import java.util.Map;

@WebServlet(name = "InsertServlet", value="/insert")
public class InsertServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws jakarta.servlet.ServletException, java.io.IOException {
        // verifica nome da tabela no qual os dados serão inseridos
        String tabelaNome = request.getParameter("tabelaNome");
        //ta certinho tabelaNome = administrador

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

            dao.save(model);

            request.setAttribute("tabela", tabelaNome);
            request.setAttribute("saida", "Registro inserido com sucesso!");

            List<Model> lista = dao.findAll();
            request.setAttribute("models", lista);
            request.getRequestDispatcher("WEB-INF/ListarAdm.jsp").forward(request, response);
//            if(tabelaNome.equals("Administrador")){
//                response.sendRedirect(request.getContextPath() + "/index.html");
//            } else {
//                response.setStatus(HttpServletResponse.SC_OK);
//                response.getWriter().write("Registro inserido com sucesso na tabela " + tabelaNome);
//            }

        } catch (DAONotFoundException e) {
            // TODO: Criar uma classe chamada `ErrorRedirect` que tenha um método static que receba o request, o response, uma mensagem de erro
            // Nenhum DAO encontrado para a tabela
            request.setAttribute("erro", "Tabela não encontrada: " + tabelaNome);
            RequestDispatcher rd = request.getRequestDispatcher("erro.jsp");
            rd.forward(request, response);

        } catch (ConnectionFailedException | NotFoundException e) {
            // Erros de banco
            request.setAttribute("erro", "Erro ao acessar o banco: " + e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("erro.jsp");
            rd.forward(request, response);
            e.printStackTrace();

        } catch (ParseException e) {
            // Erros de conversão de parâmetros
            request.setAttribute("erro", "Erro ao processar os parâmetros: " + e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("erro.jsp");
            rd.forward(request, response);
        }
    }
}
