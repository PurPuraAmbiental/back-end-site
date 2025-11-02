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

/**
 * Servlet responsável por validar a chave de acesso usada no cadastro de administradores.
 * O objetivo é garantir que apenas usuários com uma chave válida (armazenada e ativa no banco)
 * possam acessar a página de cadastro.
 *
 * @author kevin de Oliveira
 */
@WebServlet(name = "ChaveCadastroServlet", value = "/autenticar-cadastro")
public class ChaveCadastroServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, jakarta.servlet.ServletException {

        try {
            // Obtém o valor da chave digitada pelo usuário no formulário
            String chaveDigitada = request.getParameter("chave");

            // Cria o DAO responsável por acessar as chaves armazenadas no banco
            ChaveAcessoDAO chaveAcessoDAO = new ChaveAcessoDAO();

            // Recupera todas as chaves cadastradas (poderia haver mais de uma ativa)
            List<ChaveAcesso> chaves = chaveAcessoDAO.findAll();

            // Flag que indica se alguma chave válida foi encontrada
            boolean chaveValida = false;

            // Percorre todas as chaves do banco
            for (ChaveAcesso chaveAcesso : chaves) {
                // Recupera o hash da chave armazenada no banco
                String chaveBanco = chaveAcesso.getCHash();

                /*
                 * Verifica se a chave digitada pelo usuário corresponde ao hash da chave do banco.
                 * O BCrypt.checkpw() compara a senha em texto puro com o hash e retorna true se bater.
                 * Além disso, verifica se a chave está marcada como ativa (CAtivo == 1).
                 */
                if (BCrypt.checkpw(chaveDigitada, chaveBanco)
                        && String.valueOf(chaveAcesso.getCAtivo()).equals("1")) {
                    chaveValida = true;
                }
            }

            /*
             * Se a chave for válida:
             * - Redireciona o usuário para a página de cadastro (cadastro.jsp).
             *
             * Caso contrário:
             * - Adiciona uma mensagem de erro ao request e volta para a tela de verificação.
             */
            if (chaveValida) {
                response.sendRedirect(request.getContextPath() + "/cadastro/cadastro.jsp");
            } else {
                request.setAttribute("erro", "Senha inválida");
                request.getRequestDispatcher("/cadastro/VerificacaoAdministrador.jsp").forward(request, response);
            }

        } catch (Exception e) {
            // Em caso de erro inesperado, imprime o stack trace (útil para depuração)
            e.printStackTrace();

            // Define uma mensagem de erro genérica e encaminha o usuário para uma página de erro
            request.setAttribute("erro", "Ocorreu um erro ao validar a chave de acesso. Tente novamente mais tarde.");
            request.getRequestDispatcher("/erro.jsp").forward(request, response);
        }
    }
}
