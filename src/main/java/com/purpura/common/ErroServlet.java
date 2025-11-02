package com.purpura.common;

import com.purpura.dao.DAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Classe responsável por conter método utilitário para definição de erro
 *
 * @author Bruna de Jesus
 */
public class ErroServlet {
    /**
     * Método auxiliar para reduzir repetição de código ao encaminhar mensagem de erro.
     * Ele atualiza a lista das models na tela e define a mensagem de erro.
     */
    public static void setErro(HttpServletRequest request, HttpServletResponse response, DAO<?> dao, String mensagem, String lista, String caminho)
            throws jakarta.servlet.ServletException, IOException {
        request.setAttribute(lista, dao.findAll());
        request.setAttribute("erro", mensagem);
        request.getRequestDispatcher(caminho).forward(request, response);
    }
}