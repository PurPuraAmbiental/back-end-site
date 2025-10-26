package com.purpura.servlet.telefone;

import com.purpura.common.ErroServlet;
import com.purpura.common.Regex;
import com.purpura.dao.EmpresaDAO;
import com.purpura.dao.TelefoneDAO;
import com.purpura.dto.TelefoneView;
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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
/** SERVLET INSERT TELEFONE
 * Tem o objetivo de validar os dados que podem comprometer a inserção
 * no banco de dados e, uma vez validados, adiciona o endereço na lista
 * de TELEFONE do banco.
 *
 * CRUD -> CREATE
 *
 * @author Kevin de Oliveira
 * @author Bruna Oliveira
 **/
@WebServlet(name = "InsertTelefoneServlet", value = "/telefone/insert")
public class InsertTelefoneServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws jakarta.servlet.ServletException, IOException {
        String lista = "listaTelefones";
        String caminho = "/CRUD/telefone.jsp";
        TelefoneDAO dao = new TelefoneDAO();
        try {
            // Captura todos os parâmetros enviados pelo formulário e armazena em um mapa
            Map<String, String> params = new LinkedHashMap<>();
            request.getParameterMap().forEach((key, values) -> params.put(key, values[0]));

            // Recupera o nome da empresa do formulário
            String nomeEmpresa = params.get("cNmEmpresa");

            // Busca a empresa no banco usando o nome para obter o CNPJ
            EmpresaDAO empresaDAO = new EmpresaDAO();
            Empresa empresa = empresaDAO.findByAttribute("cNmEmpresa", nomeEmpresa);

            // Cria o objeto Telefone a partir dos parâmetros do formulário
            Telefone model = new Telefone(params);


            // Recupera a lista atual de telefones para exibir na página, caso haja erro
            List<TelefoneView> telefoneViews = dao.listarComEmpresa();
            request.setAttribute("listaTelefones", telefoneViews);

            // Se a empresa não existir, envia mensagem de erro e mantém a lista de telefones na tela
            if (empresa == null) {
                ErroServlet.setErro(request, response, dao,
                        "Não foi possível cadastrar Telefone! Insira uma empresa cadastrada anteriormente", lista, caminho);
                return;
            }

            // Define o CNPJ da empresa no telefone para manter a referência correta
            model.setCCnpj(empresa.getCCnpj());

            // Valida se o telefone tem formato correto usando Regex
            if (!Regex.validarTelefone(model.getCNrTelefone())) {
                ErroServlet.setErro(request, response, dao,
                        "Não foi possível cadastrar Telefone! Insira um Telefone válido", lista, caminho);
                return;
            }

            // Formata o telefone removendo caracteres não numéricos para salvar apenas os dígitos
            model.setCNrTelefone(model.getCNrTelefone()
                    .replace("(", "")
                    .replace(")", "")
                    .replace("-", "")
                    .replace(" ", ""));

            // Salva o telefone no banco
            dao.save(model);

            // Após salvar, redireciona para a lista de telefones para evitar reenvio do formulário
            response.sendRedirect(request.getContextPath() + "/telefone/list");

        } catch (NumberFormatException | ConnectionFailedException | NotFoundException e) {
            // Se ocorrer qualquer erro de processamento, envia para a página de erro
            ErroServlet.setErro(request, response, dao,
                    "Erro ao inserir Telefone: " + e.getMessage(), lista, caminho);

        }
    }
}
