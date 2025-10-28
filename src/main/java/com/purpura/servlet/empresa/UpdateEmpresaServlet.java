package com.purpura.servlet.empresa;

import com.purpura.common.ErroServlet;
import com.purpura.dao.DAO;
import com.purpura.dao.EmpresaDAO;
import com.purpura.exception.ConnectionFailedException;
import com.purpura.exception.NotFoundException;
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
import java.util.Map;

@WebServlet(name = "UpdateEmpresaServlet", value = "/empresa/update")
public class UpdateEmpresaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws jakarta.servlet.ServletException, IOException {
        DAO<Empresa> dao = new EmpresaDAO();
        String caminho = "/CRUD/administrador.jsp";
        String lista = "listaAdministradores";
        try {
            Map<String, String> params = new LinkedHashMap<>();
            request.getParameterMap().forEach((key, values) -> params.put(key, values[0]));

            Empresa model = new Empresa(params);
            if (model.getCSenha().length() < 6){
                ErroServlet.setErro(request, response, dao, "Não foi possivel atualizar Empresa! \n Sua senha deve ter 6 ou mais caracteres validos", lista, caminho);
                return;
            }
            if (params.containsKey("cSenha")) {
                String hash = Criptografia.criptografar(params.get("cSenha"));
                params.put("cSenha", hash);
                model.setCSenha(params.get("cSenha"));
            }
            dao.update(model);
            response.sendRedirect(request.getContextPath() + "/empresa/list");
        } catch (ConnectionFailedException | NotFoundException e) {
            ErroServlet.setErro(request, response, dao, "Erro ao atualizar Empresa: " + e.getMessage(), lista, caminho);
        } catch (ParseException e) {
            ErroServlet.setErro(request, response, dao, "Erro ao processar os parâmetros: " + e.getMessage(), lista, caminho);
        }
    }
}
