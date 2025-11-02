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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, jakarta.servlet.ServletException{
        try {
            String chaveDigitada = request.getParameter("chave");

            ChaveAcessoDAO chaveAcessoDAO = new ChaveAcessoDAO();
            List<ChaveAcesso> chaves = chaveAcessoDAO.findAll();

            boolean chaveValida = false;

            for (ChaveAcesso chaveAcesso : chaves) {
                String chaveBanco = chaveAcesso.getCHash();

                if (BCrypt.checkpw(chaveDigitada, chaveBanco) && String.valueOf(chaveAcesso.getCAtivo()).equals("1")) {
                    chaveValida = true;
                }
            }


            if (chaveValida) {
                response.sendRedirect(request.getContextPath() + "/cadastro/cadastro.jsp");
            } else {
                request.setAttribute("erro", "Senha Invalida");
                request.getRequestDispatcher("/cadastro/VerificacaoAdministrador.jsp").forward(request, response);
            }
        }catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("erro", "Ocorreu um erro ao validar a chave de acesso. Tente novamente mais tarde.");
            request.getRequestDispatcher("/erro.jsp").forward(request, response);
        }
    }
}
