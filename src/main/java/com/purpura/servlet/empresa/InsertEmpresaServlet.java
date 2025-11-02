package com.purpura.servlet.empresa;

import com.purpura.common.ErroServlet;
import com.purpura.common.Regex;
import com.purpura.dao.DAO;
import com.purpura.dao.EmpresaDAO;
import com.purpura.exception.ConnectionFailedException;
import com.purpura.exception.NotFoundException;
import com.purpura.model.Empresa;
import com.purpura.util.Criptografia;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet(name = "InsertEmpresaServlet", value = "/empresa/insert")
public class InsertEmpresaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws jakarta.servlet.ServletException, IOException {
        String caminho = "/WEB-INF/CRUD/empresa.jsp";
        String lista = "listaEmpresas";
        DAO<Empresa> dao = new EmpresaDAO();
        try {
            Map<String, String> params = new LinkedHashMap<>();
            request.getParameterMap().forEach((key, values) -> params.put(key, values[0]));
            Empresa model = new Empresa(params);
            //VALIDAÇAO DE DADOS
            if (!Regex.validarEmail(model.getCEmail())) {
                ErroServlet.setErro(request, response, dao, "Não foi possivel cadastrar Empresa! Digite um e-mail valido", lista, caminho);
            }
            if (!Regex.validarCnpj(model.getCCnpj())) {
                ErroServlet.setErro(request, response, dao, "Não foi possivel cadastrar Empresa! Digite um cnpj valido", lista, caminho);
                return;  // Impede que o código continue
            }
            else {
                model.setCCnpj(model.getCCnpj().replace("/", "").replace(".", "").replace("-", ""));
                System.out.println(model.getCCnpj());
            }
            if (dao.findById(model.getCCnpj()) != null) {
                ErroServlet.setErro(request, response, dao, "Esse cnpj ja foi cadastrado! Digite um cnpj valido", lista, caminho);
                return;
            }
            if (dao.findByAttribute("cEmail", model.getCEmail()) != null) {
                ErroServlet.setErro(request, response, dao, "Esse email ja foi cadastrado! Digite um e-mail valido", lista, caminho);
                return;
            }
            if (model.getCSenha().length() < 6){
                ErroServlet.setErro(request, response, dao, "Não foi possivel cadastrar Empresa! \n Sua senha deve ter 6 ou mais caracteres validos", lista, caminho);
                return;
            }
            if (params.containsKey("cSenha")) {
                String hash = Criptografia.criptografar(params.get("cSenha"));
                params.put("cSenha", hash);
                model.setCSenha(params.get("cSenha"));
            }
            dao.save(model);
            response.sendRedirect(request.getContextPath() + "/empresa/list");
        } catch (ConnectionFailedException | NotFoundException e) {
            ErroServlet.setErro(request, response, dao, "Erro ao inserir Empresa: " + e.getMessage(), lista, caminho);
        } catch (ParseException e) {
            ErroServlet.setErro(request, response, dao, "Erro ao processar os parâmetros: " + e.getMessage(), lista, caminho);
        }
    }
}
