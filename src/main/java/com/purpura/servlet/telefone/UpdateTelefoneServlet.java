package com.purpura.servlet.telefone;

import com.purpura.common.ErroServlet;
import com.purpura.common.Regex;
import com.purpura.dao.DAO;
import com.purpura.dao.EmpresaDAO;
import com.purpura.dao.TelefoneDAO;
import com.purpura.exception.ConnectionFailedException;
import com.purpura.exception.NotFoundException;
import com.purpura.model.Empresa;
import com.purpura.model.Telefone;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet(name = "UpdateTelefoneServlet", value = "/telefone/update")
public class UpdateTelefoneServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws jakarta.servlet.ServletException, IOException {
        DAO<Telefone> dao = new TelefoneDAO();
        String lista = "listaTelefones";
        String caminho = "/CRUD/telefone.jsp";
        try {
            Map<String, String> params = new LinkedHashMap<>();
            request.getParameterMap().forEach((key, values) -> params.put(key, values[0]));
            String nomeEmpresa = params.get("cNmEmpresa");
            EmpresaDAO empresaDAO = new EmpresaDAO();
            Empresa empresa = empresaDAO.findByAttribute("cNmEmpresa", nomeEmpresa);
            params.put("cCnpj", empresa.getCCnpj());

            Telefone model = new Telefone(params);
            //VALIDAÇAO DE ERRO;
            if (!Regex.validarTelefone(model.getCNrTelefone())) {
                ErroServlet.setErro(request, response, dao,
                        "Não foi possível cadastrar Telefone! Insira um Telefone válido", lista, caminho);
                return;
            }
            if (empresa == null) {
                ErroServlet.setErro(request, response, dao,
                        "Não foi possível cadastrar Telefone! Insira uma empresa cadastrada anteriormente", lista, caminho);
                return;
            }
            model.setNCdTelefone(Integer.parseInt(params.get("nCdTelefone")));
            model.setCNrTelefone(params.get("cNrTelefone"));
            model.setCDescricao(params.get("cDescricao"));

            dao.update(model);
            response.sendRedirect(request.getContextPath() + "/telefone/list");
        } catch (ConnectionFailedException | NotFoundException  e) {
            request.setAttribute("erro", "Erro ao atualizar Telefone: " + e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
            rd.forward(request, response);
        }
    }
}
