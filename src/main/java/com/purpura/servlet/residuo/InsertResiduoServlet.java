package com.purpura.servlet.residuo;

import com.purpura.common.ErroServlet;
import com.purpura.dao.DAO;
import com.purpura.dao.EmpresaDAO;
import com.purpura.dao.ResiduoDAO;
import com.purpura.dto.ResiduoView;
import com.purpura.exception.ConnectionFailedException;
import com.purpura.exception.NotFoundException;
import com.purpura.model.Empresa;
import com.purpura.model.Residuo;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "InsertResiduoServlet", value = "/residuo/insert")
public class InsertResiduoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws jakarta.servlet.ServletException, IOException {
        ResiduoDAO dao = new ResiduoDAO();
        String lista = "listaResiduo";
        String caminho = "/CRUD/residuos.jsp";
        try {
            Map<String, String> params = new LinkedHashMap<>();
            request.getParameterMap().forEach((key, values) -> params.put(key, values[0]));

            String nomeEmpresa = params.get("cNmEmpresa");

            EmpresaDAO empresaDAO = new EmpresaDAO();
            Empresa empresa = empresaDAO.findByAttribute("cNmEmpresa", nomeEmpresa);
            System.out.println("erro: "+empresa);
            Residuo model = new Residuo(params);
            //VALIDAÇÃO DE DADOS
            if (empresa == null) {
                ErroServlet.setErro(request, response, dao, "Nao foi possivel cadastrar Residuo! Insira uma empresa cadastrada anteriormente" , lista, caminho);
                return;
            } else {
                model.setCCnpj(empresa.getCCnpj());
            }
            dao.save(model);
            response.sendRedirect(request.getContextPath() + "/residuo/list");
        } catch (ConnectionFailedException | NotFoundException | NumberFormatException e) {
            ErroServlet.setErro(request, response, dao, "Erro ao inserir Residuo: "+ e.getMessage() , lista, caminho);
        }
    }
}
