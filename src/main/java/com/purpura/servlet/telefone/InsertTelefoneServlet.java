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
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Servlet responsável por inserir um novo Telefone no sistema.
 *
 * Realiza a validação dos dados enviados via formulário (empresa, número e formato do telefone),
 * garantindo que não haja duplicidade e que a empresa esteja ativa.
 * Após a validação, insere o telefone no banco de dados.
 *
 * =============== ESTA CLASSE FAZ USO DE REGEX ===============================
 *
 * CRUD -> CREATE
 *
 * @author Kevin de Oliveira
 * @author Bruna Oliveira
 */
@WebServlet(name = "InsertTelefoneServlet", value = "/telefone/insert")
public class InsertTelefoneServlet extends HttpServlet {

    /**
     * Processa o POST para inserir um Telefone.
     *
     * @param request  objeto HttpServletRequest contendo os parâmetros do formulário
     * @param response objeto HttpServletResponse para redirecionamento ou forward
     * @throws jakarta.servlet.ServletException se ocorrer erro no servlet
     * @throws IOException                      se ocorrer erro de I/O
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws jakarta.servlet.ServletException, IOException {

        // Define o nome da lista e o caminho da página JSP que será usada em caso de erro
        String lista = "listaTelefones";
        String caminho = "/WEB-INF/CRUD/telefone.jsp";

        // Cria o DAO responsável pelas operações com telefone
        TelefoneDAO telefoneDAO = new TelefoneDAO();

        // Carrega a lista de telefones para exibição na página (caso ocorra erro)
        List<TelefoneView> telefoneViews = telefoneDAO.listarComEmpresa();

        try {
            // Captura todos os parâmetros enviados pelo formulário e armazena em um mapa
            // O request.getParameterMap() retorna Map<String, String[]> — pegamos apenas o primeiro valor
            Map<String, String> params = new LinkedHashMap<>();
            request.getParameterMap().forEach((key, values) -> params.put(key, values[0]));

            // Recupera o nome da empresa digitado no formulário
            String nomeEmpresa = params.get("cNmEmpresa");

            // Busca a empresa no banco de dados a partir do nome informado
            EmpresaDAO empresaDAO = new EmpresaDAO();
            Empresa empresa = empresaDAO.findByAttribute("cNmEmpresa", nomeEmpresa);

            // Cria um objeto 'Telefone' a partir dos parâmetros do formulário
            Telefone model = new Telefone(params);

            // Mantém a lista de telefones na requisição (necessário caso haja erro de validação)
            request.setAttribute("listaTelefones", telefoneViews);

            // ==================== VALIDAÇÕES DOS DADOS ====================

            // Verifica se a empresa existe no banco
            if (empresa == null) {
                telefoneViewSetErro(request, response, telefoneDAO, telefoneViews,
                        "Não foi possível cadastrar Telefone! Insira uma empresa cadastrada anteriormente",
                        lista, caminho);
                return; // Interrompe o fluxo
            }

            // Verifica se a empresa está ativa
            if (empresa.getCAtivo() != '1') {
                telefoneViewSetErro(request, response, telefoneDAO, telefoneViews,
                        "Não foi possível cadastrar Telefone! Insira uma empresa ativa",
                        lista, caminho);
                return;
            }

            // Verifica se já existe um telefone cadastrado com o mesmo número
            if (telefoneDAO.findByAttribute("cNrTelefone", model.getCNrTelefone()) != null) {
                telefoneViewSetErro(request, response, telefoneDAO, telefoneViews,
                        "Esse telefone já foi cadastrado! Digite um telefone válido",
                        lista, caminho);
                return;
            }

            // Define o CNPJ da empresa no telefone (mantém a relação empresa-telefone)
            model.setCCnpj(empresa.getCCnpj());

            // Valida se o formato do número de telefone está correto usando Regex
            if (!Regex.validarTelefone(model.getCNrTelefone())) {
                telefoneViewSetErro(request, response, telefoneDAO, telefoneViews,
                        "Não foi possível cadastrar Telefone! Insira um Telefone válido",
                        lista, caminho);
                return;
            }

            // Remove caracteres especiais do número de telefone antes de salvar
            model.setCNrTelefone(model.getCNrTelefone()
                    .replace("(", "")
                    .replace(")", "")
                    .replace("-", "")
                    .replace(" ", ""));

            // ==================== SALVAMENTO NO BANCO ====================

            // Insere o novo telefone no banco de dados
            telefoneDAO.save(model);

            // Após salvar, redireciona o usuário para a lista de telefones
            // Isso evita o reenvio do formulário (problema de "F5" duplicar registros)
            response.sendRedirect(request.getContextPath() + "/telefone/list");

        } catch (NumberFormatException | ConnectionFailedException | NotFoundException e) {
            // Captura erros de formato ou falhas na conexão com o banco de dados
            telefoneViewSetErro(request, response, telefoneDAO, telefoneViews,
                    "Erro ao inserir Telefone: " + e.getMessage(), lista, caminho);
        }
    }

    /**
     * Método auxiliar para configurar os atributos de erro e encaminhar a requisição
     * de volta à página de telefone.jsp com as mensagens apropriadas.
     *
     * @param request   requisição HTTP
     * @param response  resposta HTTP
     * @param telefoneDAO DAO utilizado para listar os telefones
     * @param telefoneView lista de visualização de telefones
     * @param mensagem  mensagem de erro a ser exibida
     * @param lista     nome do atributo da lista no JSP
     * @param caminho   caminho da página JSP de destino
     * @throws jakarta.servlet.ServletException se ocorrer erro no forward
     * @throws IOException                      se ocorrer erro de I/O
     */
    public void telefoneViewSetErro(HttpServletRequest request, HttpServletResponse response,
                                    TelefoneDAO telefoneDAO, List<TelefoneView> telefoneView,
                                    String mensagem, String lista, String caminho)
            throws jakarta.servlet.ServletException, IOException {

        // Recarrega a lista de telefones com empresas para exibição
        telefoneView = telefoneDAO.listarComEmpresa();

        // Define os atributos de lista e mensagem de erro na requisição
        request.setAttribute(lista, telefoneView);
        request.setAttribute("erro", mensagem);

        // Encaminha de volta para a página telefone.jsp mantendo o estado da requisição
        request.getRequestDispatcher(caminho).forward(request, response);
    }
}
