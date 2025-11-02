package com.purpura.common;

import com.purpura.dao.DAO;
import com.purpura.exception.PurpuraException;
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

    /**
     * Sobrecarga que aceita Exception para extrair mensagem amigável quando possível.
     */
    public static void setErro(HttpServletRequest request, HttpServletResponse response, DAO<?> dao, Exception ex, String lista, String caminho)
            throws jakarta.servlet.ServletException, IOException {
        String mensagem = (ex instanceof PurpuraException pe) ? pe.getMensagemUsuario() : "Erro ao processar a solicitação.";
        request.setAttribute(lista, dao.findAll());
        request.setAttribute("erro", mensagem);
        request.getRequestDispatcher(caminho).forward(request, response);
    }
}