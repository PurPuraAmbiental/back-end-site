package com.purpura.servlet;

import com.purpura.dao.ChaveAcessoDAO;
import com.purpura.model.ChaveAcesso;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ChaveCadastroServlet", value = "/autenticar-cadastro")
public class ChaveCadastroServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String chaveDigitada = request.getParameter("chave"); // ✅ Corrigido

        ChaveAcessoDAO chaveAcessoDAO = new ChaveAcessoDAO();
        List<ChaveAcesso> chaves = chaveAcessoDAO.findAll();

        boolean chaveValida = false;

        for (ChaveAcesso chaveAcesso : chaves) {
            String chaveBanco = chaveAcesso.getCHash();

            if (BCrypt.checkpw(chaveDigitada, chaveBanco)) {
                chaveValida = true;
                break;
            }
        }

        if (chaveValida) {
            response.sendRedirect(request.getContextPath() + "/cadastro/cadastro.jsp");
        }
    }
}
