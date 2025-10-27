package com.purpura.servlet.residuo;

import com.purpura.common.ErroServlet;
import com.purpura.dao.DAO;
import com.purpura.dao.ResiduoDAO;
import com.purpura.dto.ResiduoView;
import com.purpura.exception.ConnectionFailedException;
import com.purpura.exception.NotFoundException;
import com.purpura.model.Residuo;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "DeleteResiduoServlet", value = "/residuo/delete")
public class DeleteResiduoServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws jakarta.servlet.ServletException, IOException {
        String idResiduo = request.getParameter("nCdResiduo");
        ResiduoDAO dao = new ResiduoDAO();
        String lista = "listaResiduos";
        String caminho = "/CRUD/residuos.jsp";
        try {
            int id = Integer.parseInt(idResiduo);
            dao.delete(id);
            response.sendRedirect(request.getContextPath() + "/residuo/list");
        } catch (ConnectionFailedException | NotFoundException | NumberFormatException e) {
            ErroServlet.setErro(request, response, dao,"Erro ao deletar Residuo: " + e.getMessage()  , lista, caminho);
        }
    }
}
