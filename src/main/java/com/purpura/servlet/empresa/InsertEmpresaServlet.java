package com.purpura.servlet.empresa;

import com.purpura.common.Regex;
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
import java.util.List;
import java.util.Map;

@WebServlet(name = "InsertEmpresaServlet", value = "/empresa/insert")
public class InsertEmpresaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws jakarta.servlet.ServletException, IOException {

            try {
            Map<String, String> params = new LinkedHashMap<>();
            request.getParameterMap().forEach((key, values) -> params.put(key, values[0]));

            Empresa model = new Empresa(params);
            DAO<Empresa> dao = new EmpresaDAO();
            List<?> empresas = dao.findAll();
                //VALIDAÇAO DE DADOS
            request.setAttribute("listaEmpresas",  empresas);
            if (!Regex.validarEmail(model.getCEmail())) {
                request.setAttribute("erro", "Não foi possivel cadastrar Empresa! \n Digite um e-mail valido");
                request.getRequestDispatcher("/CRUD/empresas.jsp").forward(request, response);
                return;  // Impede que o código continue
            }
            if (!Regex.validarCnpj(model.getCCnpj())) {
                request.setAttribute("erro", "Não foi possivel cadastrar Empresa! \n Digite um cnpj valido");
                request.getRequestDispatcher("/CRUD/empresas.jsp").forward(request, response); // Mantém o popup aberto
                return;  // Impede que o código continue
            } else {
                boolean continuar = true;
                for (int i =0; i < model.getCCnpj().length() && continuar; i++){
                    if (model.getCCnpj().charAt(i) == '/' || model.getCCnpj().charAt(i) == '.' || model.getCCnpj().charAt(i) == '-') {
                        continuar = false;
                        model.setcCnpj(model.getCCnpj().replace("/", ""));
                        model.setcCnpj(model.getCCnpj().replace(".", ""));
                        model.setcCnpj(model.getCCnpj().replace("-", ""));
                        System.out.println(model.getCCnpj());
                    }
                }
            }
            if (model.getCSenha().length() < 6){
                request.setAttribute("erro", "Não foi possivel cadastrar Empresa! \n Sua senha deve ter 6 ou mais caracteres validos");
                request.getRequestDispatcher("/CRUD/empresas.jsp").forward(request, response);
                System.out.println(model.getCSenha());
                return;
            }

            if (params.containsKey("cSenha")) {
                    String hash = Criptografia.criptografar(params.get("cSenha"));
                    params.put("cSenha", hash);
                }


            dao.save(model);

            empresas = dao.findAll();
            request.setAttribute("listaEmpresas",  empresas);
            request.getRequestDispatcher("/CRUD/empresas.jsp").forward(request, response);
        } catch (ConnectionFailedException | NotFoundException e) {
            request.setAttribute("erro", "Erro ao inserir Empresa: " + e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
            rd.forward(request, response);
        } catch (ParseException e) {
            request.setAttribute("erro", "Erro ao processar os parâmetros: " + e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
            rd.forward(request, response);
        }
    }
}
