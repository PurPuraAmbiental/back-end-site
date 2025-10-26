package com.purpura.servlet.telefone;

import com.purpura.common.ErroServlet;
import com.purpura.dao.DAO;
import com.purpura.dao.TelefoneDAO;
import com.purpura.dto.TelefoneView;
import com.purpura.exception.ConnectionFailedException;
import com.purpura.exception.NotFoundException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "DeleteTelefoneServlet", value = "/telefone/delete")
public class DeleteTelefoneServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws jakarta.servlet.ServletException, IOException {
        String idStr = request.getParameter("nCdTelefone");
        String lista = "listaTelefones";
        String caminho = "/CRUD/telefone.jsp";
        TelefoneDAO dao = new TelefoneDAO();
        try {
            int id = Integer.parseInt(idStr);

            dao.delete(id);
            response.sendRedirect(request.getContextPath() + "/telefone/list");
        } catch (ConnectionFailedException | NotFoundException | NumberFormatException e) {
            request.setAttribute("erro", "Erro ao deletar Telefone: " + e.getMessage());
            ErroServlet.setErro(request, response, dao,
                    "Não foi possível cadastrar Telefone! Insira um Telefone válido", lista, caminho);

        }
    }
}
