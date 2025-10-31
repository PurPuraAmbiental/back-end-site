package com.purpura.servlet.enderecoEmpresa;

import com.purpura.common.ErroServlet;
import com.purpura.dao.DAO;
import com.purpura.dao.EmpresaDAO;
import com.purpura.dao.EnderecoEmpresaDAO;
import com.purpura.exception.ConnectionFailedException;
import com.purpura.exception.NotFoundException;
import com.purpura.model.Empresa;
import com.purpura.model.EnderecoEmpresa;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet(name = "UpdateEnderecoEmpresaServlet", value = "/endereco-empresa/update")
public class UpdateEnderecoEmpresaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws jakarta.servlet.ServletException, IOException {
        DAO<EnderecoEmpresa> dao = new EnderecoEmpresaDAO();
        String lista = "listaEnderecos";
        String caminho = "/WEB-INF/CRUD/endereco.jsp";
        try {
            Map<String, String> params = new LinkedHashMap<>();
            request.getParameterMap().forEach((key, values) -> params.put(key, values[0]));

            String nomeEmpresa = params.get("cNmEmpresa");
            EmpresaDAO empresaDAO = new EmpresaDAO();
            Empresa empresa = empresaDAO.findByAttribute("cNmEmpresa", nomeEmpresa);
            params.put("cCnpj", empresa.getCCnpj());
            System.out.println(params.get("cNmEmpresa")+" | "+params.get("cCnpj"));

            EnderecoEmpresa model = new EnderecoEmpresa(params);
            //VALIDAÇÃO DE DADOS
            if (empresa == null) {
                ErroServlet.setErro(request, response, dao, "Não foi possível cadastrar o endereço! Insira uma empresa cadastrada anteriormente.", lista, caminho);
                return;
            }
            model.setCBairro(params.get("cBairro"));
            model.setCLogradouro(params.get("cLogradouro"));
            model.setCEstado(params.get("cEstado"));
            model.setCCidade(params.get("cCidade"));
            model.setCCep(params.get("cCep"));
            model.setCComplemento(params.get("cComplemento"));
            model.setINrEnderecoEmpresa(Integer.parseInt(params.get("iNrEnderecoEmpresa")));
            model.setCCnpj(params.get("cCnpj"));
            model.setNCdEnderecoEmpresa(Integer.parseInt(params.get("nCdEnderecoEmpresa")));


            dao.update(model);
            response.sendRedirect(request.getContextPath() + "/endereco-empresa/list");
        } catch (ConnectionFailedException e) {
            request.setAttribute("erro", "Erro ao atualizar EnderecoEmpresa: " + e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
            rd.forward(request, response);
        }
    }
}
