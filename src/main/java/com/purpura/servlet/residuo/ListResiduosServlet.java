package com.purpura.servlet.residuo;

import com.purpura.dao.ResiduoDAO;
import com.purpura.dto.ResiduoView;
import com.purpura.exception.ConnectionFailedException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListResiduosServlet", value = "/residuo/list")
public class ListResiduosServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            ResiduoDAO residuoDAO = new ResiduoDAO();

            String precoMinStr = request.getParameter("precoMin");
            String precoMaxStr = request.getParameter("precoMax");
            String volumeMinStr = request.getParameter("volumeMin");
            String volumeMaxStr = request.getParameter("volumeMax");
            String unidade = request.getParameter("unidade");

            Double precoMin = (precoMinStr != null && !precoMinStr.isEmpty()) ? Double.parseDouble(precoMinStr) : null;
            Double precoMax = (precoMaxStr != null && !precoMaxStr.isEmpty()) ? Double.parseDouble(precoMaxStr) : null;
            Double volumeMin = (volumeMinStr != null && !volumeMinStr.isEmpty()) ? Double.parseDouble(volumeMinStr) : null;
            Double volumeMax = (volumeMaxStr != null && !volumeMaxStr.isEmpty()) ? Double.parseDouble(volumeMaxStr) : null;
            if (unidade != null && unidade.isBlank()) unidade = null;

            List<ResiduoView> residuos = residuoDAO.listarComEmpresaFiltrado(precoMin, precoMax, volumeMin, volumeMax, unidade);

            request.setAttribute("listaResiduos", residuos);
            request.setAttribute("precoMin", precoMinStr);
            request.setAttribute("precoMax", precoMaxStr);
            request.setAttribute("volumeMin", volumeMinStr);
            request.setAttribute("volumeMax", volumeMaxStr);
            request.setAttribute("unidade", unidade);

            RequestDispatcher rd = request.getRequestDispatcher("/CRUD/residuos.jsp");
            rd.forward(request, response);

        } catch (ConnectionFailedException e) {
            request.setAttribute("erro", "Erro ao carregar lista de resíduos: " + e.getMessage());
            e.printStackTrace();
            RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            request.setAttribute("erro", "Erro inesperado ao buscar Resíduos: " + e.getMessage());
            e.printStackTrace();
            RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
            rd.forward(request, response);
        }
    }
}