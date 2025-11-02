package com.purpura.servlet;

import com.purpura.common.Regex;
import com.purpura.dao.AdministradorDAO;
import com.purpura.dao.DAO;
import com.purpura.exception.ConnectionFailedException;
import com.purpura.exception.NotFoundException;
import com.purpura.model.Administrador;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;

/**
 * Servlet responsável por autenticar o login de administradores.
 * Recebe e-mail e senha via POST, valida os dados, verifica no banco de dados
 * e cria uma sessão caso o login seja bem-sucedido.
 *
 * @author Kevin de Olivera
 *
 */
@WebServlet(name = "LoginServlet", value = "/login-auth")
public class LoginServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Captura os dados enviados pelo formulário
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        // Flag que indica se o login foi bem-sucedido
        boolean loginSucesso = false;

        // Valida o formato do e-mail e o tamanho mínimo da senha
        if (!Regex.validarEmail(email) || senha.length() < 6) {
            request.setAttribute("erro", "E-mail ou senha com formato inválido");
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
            return; // Encerra a execução se os dados forem inválidos
        }

        try {
            // Cria o DAO para acessar os dados do administrador
            DAO<Administrador> administradorDAO = new AdministradorDAO();

            // Busca o administrador pelo e-mail
            Administrador administrador = administradorDAO.findByAttribute("cEmail", email);

            // Se o administrador existir, verifica a senha usando BCrypt
            if (administrador != null) {
                String hashArmazenado = administrador.getCSenha();

                if (BCrypt.checkpw(senha, hashArmazenado)) {
                    loginSucesso = true;

                    // Cria ou recupera a sessão e armazena o usuário autenticado
                    HttpSession session = request.getSession();
                    session.setAttribute("usuario", administrador);

                    // Encaminha para a página do CRUD
                    request.getRequestDispatcher("/WEB-INF/CRUD/crud.jsp").forward(request, response);
                    return; // Interrompe o fluxo para evitar encaminhar para erro
                }
            }

            // Caso o login falhe, retorna à tela de login com mensagem de erro
            if (!loginSucesso) {
                request.setAttribute("erro", "E-mail ou senha incorretos.");
                RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                rd.forward(request, response);
            }

        } catch (ConnectionFailedException e) {
            // Caso haja problema de conexão com o banco de dados
            request.setAttribute("erro", "Erro de conexão com o servidor. Tente novamente mais tarde.");
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);

        } catch (NotFoundException e) {
            // Caso o e-mail informado não seja encontrado
            request.setAttribute("erro", "E-mail ou senha incorretos.");
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        }
    }
}
