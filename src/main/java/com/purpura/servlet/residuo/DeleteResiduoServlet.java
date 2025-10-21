package com.purpura.servlet.residuo;

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
        try {
            int id = Integer.parseInt(idResiduo);
           ResiduoDAO dao = new ResiduoDAO();
            dao.delete(id);
            List<ResiduoView> residuo = dao.listarComEmpresa();
            request.setAttribute("listaResiduos",  residuo);
            request.getRequestDispatcher("/CRUD/residuos.jsp").forward(request, response);
        } catch (FileNotFoundException e) {
            request.setAttribute("erro", "Erro ao deletar Residuo: " + e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
            rd.forward(request, response);
        }
    }
}
