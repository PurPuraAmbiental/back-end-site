package com.purpura.servlet.transportadora;

import com.purpura.common.ErroServlet;
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
import java.util.List;

@WebServlet(name = "DeleteTransportadoraServlet", value = "/transportadora/delete")
public class DeleteTransportadoraServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws jakarta.servlet.ServletException, IOException {
        String lista = "listaTransportadoras";
        String caminho = "/CRUD/transportadora.jsp";
        DAO<Transportadora> dao = new TransportadoraDAO();
        String idParam = request.getParameter("nCdTransporte");
        try {
            int id = Integer.parseInt(idParam);
            dao.delete(id);
            response.sendRedirect(request.getContextPath() + "/transportadora/list");
        } catch (NumberFormatException e) {
            ErroServlet.setErro(request, response, dao,
                    "Parâmetro inválido para nCdTransporte", lista, caminho);
        } catch (ConnectionFailedException | NotFoundException e) {
            ErroServlet.setErro(request, response, dao,
                    "Erro ao deletar Transporte: ", lista, caminho);
        }
    }
}
