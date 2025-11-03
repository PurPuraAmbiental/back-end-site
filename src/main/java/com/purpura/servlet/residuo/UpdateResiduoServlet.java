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
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.purpura.common.Constants.ERROR_PAGE;

@WebServlet(name = "UpdateResiduoServlet", value = "/residuo/update")
public class UpdateResiduoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws jakarta.servlet.ServletException, IOException {
        DAO<Residuo> dao = new ResiduoDAO();
        String lista = "listaResiduos";
        String caminho = "/WEB-INF/CRUD/residuos.jsp";

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
            ResiduoDAO residuoDAO = new ResiduoDAO();
            List<ResiduoView> listaResiduos = residuoDAO.listarComEmpresa();
            if (empresa != null) {
                residuoViewSetErro(request, response, residuoDAO, listaResiduos, "Nao foi possivel atualizar Residuo! Insira uma empresa cadastrada anteriormente" , lista, caminho);
                return;
            } else {
                model.setCCnpj(empresa.getCCnpj());
            }
            if (empresa.getCAtivo() != '1') {
                residuoViewSetErro(request, response, residuoDAO, listaResiduos, "Nao foi possivel atualizar Residuo! Insira uma empresa ativa" , lista, caminho);
                return;
            }

            dao.update(model);
            response.sendRedirect(request.getContextPath() + "/residuo/list");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            ErroServlet.setErro(request, response, dao, "Erro ao processar parametros.", lista, ERROR_PAGE);
        } catch (ConnectionFailedException | NotFoundException e) {
            e.printStackTrace();
            ErroServlet.setErro(request, response, dao, e, lista, ERROR_PAGE);
        } catch (Exception e) {
            //Erro genérico (não previsto)
            // Captura qualquer outra exceção inesperada que possa ocorrer no fluxo
            ErroServlet.setErro(request, response, dao,
                    "Ocorreu um erro inesperado.",
                    lista, ERROR_PAGE);
        }
    }
    public void residuoViewSetErro(HttpServletRequest request, HttpServletResponse response, ResiduoDAO residuoDAO, List<ResiduoView> residuoView, String mensagem, String lista, String caminho)
            throws jakarta.servlet.ServletException, IOException {
        residuoView = residuoDAO.listarComEmpresa();
        request.setAttribute(lista, residuoView);
        request.setAttribute("erro", mensagem);
        request.getRequestDispatcher(caminho).forward(request, response);
    }
}
