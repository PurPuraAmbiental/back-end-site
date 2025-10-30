package com.purpura.servlet.administrador;
import com.purpura.common.ErroServlet;
import com.purpura.dao.AdministradorDAO;
import com.purpura.dao.DAO;
import com.purpura.exception.ConnectionFailedException;
import com.purpura.model.Administrador;
import com.purpura.util.Criptografia;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "UpdateAdministradorServlet", value = "/administrador/update")
public class UpdateAdministradorServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws jakarta.servlet.ServletException, IOException {
        DAO<Administrador> dao = new AdministradorDAO();
        String caminho = "WEB-INF/CRUD/administrador.jsp";
        String lista = "listaAdministradores";
        try {
            Map<String, String> params = new LinkedHashMap<>();
            request.getParameterMap().forEach((key, values) -> params.put(key, values[0]));
            Administrador model = new Administrador(params);
            model.setCSenha(params.get("cSenha"));
            model.setCNmAdministrador(params.get("cNmAdministrador"));
            //VALIDAÇÃO DE DADOS
            if (model.getCSenha().length() < 6){
                ErroServlet.setErro(request, response, dao, "Não foi possivel atualizar Administrador! Insira uma senha com no minimo 6 caracteres.", lista, caminho);
                return;
            }
            if (params.containsKey("cSenha")) {
                String hash = Criptografia.criptografar(params.get("cSenha"));
                params.put("cSenha", hash);
                model.setCSenha(params.get("cSenha"));
            }



            dao.update(model);
            response.sendRedirect(request.getContextPath() + "/administrador/list");
        } catch (ConnectionFailedException e) {
            ErroServlet.setErro(request, response, dao, "Erro ao atualizar Administrador: " + e.getMessage(), lista, caminho);
             }
         catch (ParseException e) {
            System.out.println(e.getMessage());
             ErroServlet.setErro(request, response, dao, "Erro ao processar os parâmetros: " + e.getMessage(), lista, caminho);
        }
    }
}
