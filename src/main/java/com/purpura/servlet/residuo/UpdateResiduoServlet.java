package com.purpura.servlet.residuo;

import com.purpura.common.ErroServlet;
import com.purpura.dao.DAO;
import com.purpura.dao.EmpresaDAO;
import com.purpura.dao.ResiduoDAO;
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
import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "UpdateResiduoServlet", value = "/residuo/update")
public class UpdateResiduoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws jakarta.servlet.ServletException, IOException {
        DAO<Residuo> dao = new ResiduoDAO();
        String lista = "listaResiduo";
        String caminho = "/CRUD/residuos.jsp";
        try {
            Map<String, String> params = new LinkedHashMap<>();
            request.getParameterMap().forEach((key, values) -> params.put(key, values[0]));
            String nomeEmpresa = params.get("cNmEmpresa");
            EmpresaDAO empresaDAO = new EmpresaDAO();
            Empresa empresa = empresaDAO.findByAttribute("cNmEmpresa", nomeEmpresa);
            params.put("cCnpj", empresa.getCCnpj());
            System.out.println(params.get("cNmEmpresa")+" | "+params.get("cCnpj"));



            Residuo model = new Residuo(params);
            model.setCNmResiduo(params.get("cNmResiduo"));
            model.setCCategoria(params.get("cCategoria"));
            model.setCDescricao(params.get("cDescricao"));
            model.setCTipoUnidade(params.get("cNmTipoUnidade"));
            model.setNPrecoPadrao(Double.parseDouble(params.get("nPrecoPadrao")));
            model.setNVolumePadrao(Double.parseDouble(params.get("nVolumePadrao")));
            model.setCTipoUnidade(params.get("cTipoUnidade"));
            //VALIDAÇÃO DE DADOS
            if (empresa == null) {
                ErroServlet.setErro(request, response, dao, "Nao foi possivel cadastrar Residuo! Insira uma empresa cadastrada anteriormente" , lista, caminho);
                return;
            } else {
                model.setCCnpj(empresa.getCCnpj());
            }

            dao.update(model);
            response.sendRedirect(request.getContextPath() + "/residuo/list");
        } catch (NumberFormatException e) {
            ErroServlet.setErro(request, response, dao, "Erro ao atualizar Residuo: " + e.getMessage() , lista, caminho);
        }
    }
}
