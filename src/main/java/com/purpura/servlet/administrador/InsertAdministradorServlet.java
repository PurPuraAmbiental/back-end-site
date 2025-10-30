package com.purpura.servlet.administrador;

import com.purpura.common.ErroServlet;
import com.purpura.common.Regex;
import com.purpura.dao.AdministradorDAO;
import com.purpura.dao.DAO;
import com.purpura.exception.ConnectionFailedException;
import com.purpura.exception.NotFoundException;
import com.purpura.model.Administrador;
import com.purpura.model.Empresa;
import com.purpura.util.Criptografia;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "InsertAdministradorServlet", value = "/administrador/insert")
public class InsertAdministradorServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws jakarta.servlet.ServletException, IOException {
        DAO<Administrador> dao = new AdministradorDAO();
        String caminho = "WEB-INF/CRUD/administrador.jsp";
        String lista = "listaAdministradores";
        try {
            Map<String, String> params = new LinkedHashMap<>();
            request.getParameterMap().forEach((key, values) -> params.put(key, values[0]));
            Administrador model = new Administrador(params);

            if (!Regex.validarEmail(model.getCEmail())){
                ErroServlet.setErro(request, response, dao, "Insira um E-mail valido", lista, caminho);
                return;
            }
            else if (dao.findById(model.getCEmail()) != null) {
                ErroServlet.setErro(request, response, dao, "E-mail ja cadastrado anteriormente" , lista, caminho );
                return;
            }
            else if (model.getCSenha().length() < 6) {
                ErroServlet.setErro(request, response, dao,
                        "A senha deve possuir no minimo 6 caracteres", lista, caminho);
                return;
            }
            else {
                if (params.containsKey("cSenha")) {
                    String hash = Criptografia.criptografar(params.get("cSenha"));
                    params.put("cSenha", hash);
                    model.setCSenha(params.get("cSenha"));
                }
            }
            dao.save(model);

            String origem = null;
            origem = request.getParameter("origem");

            if(origem != null){
                response.sendRedirect(request.getContextPath());
            }
            else{
                response.sendRedirect(request.getContextPath() + "/administrador/list");
            }

        } catch (ConnectionFailedException | NotFoundException e) {
            ErroServlet.setErro(request, response, dao,"Erro ao inserir Administrador: " + e.getMessage(), lista, caminho);
        } catch (ParseException e) {
            ErroServlet.setErro(request, response, dao, "Erro ao processar os parâmetros: " + e.getMessage() , lista, caminho);
        }
    }
}
