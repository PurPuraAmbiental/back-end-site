package com.purpura.servlet.transportadora;

import com.purpura.common.ErroServlet;
import com.purpura.common.Regex;
import com.purpura.dao.DAO;
import com.purpura.dao.TransportadoraDAO;
import com.purpura.exception.ConnectionFailedException;
import com.purpura.exception.NotFoundException;
import com.purpura.model.Transportadora;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.purpura.common.Constants.ERROR_PAGE;

/**
 * Servlet responsável por inserir uma nova Transportadora no sistema.
 *
 * Realiza validações de CNPJ e e-mail utilizando expressões regulares (Regex),
 * garante que o CNPJ não esteja duplicado e, após validação, insere o registro no banco.
 *
 * =============== ESTA CLASSE FAZ USO DE REGEX ===============================
 *
 * CRUD -> CREATE
 *
 * @author Kevin de Oliveira
 * @author Bruna Oliveira
 */
@WebServlet(name = "InsertTransportadoraServlet", value = "/transportadora/insert")
public class InsertTransportadoraServlet extends HttpServlet {

    /**
     * Processa a requisição POST para inserir uma nova Transportadora.
     *
     * @param request  objeto HttpServletRequest contendo os parâmetros do formulário
     * @param response objeto HttpServletResponse usado para redirecionamento ou forward
     * @throws jakarta.servlet.ServletException se ocorrer erro no servlet
     * @throws IOException                      se ocorrer erro de entrada/saída
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws jakarta.servlet.ServletException, IOException {

        // Nome da lista e caminho do JSP que serão utilizados em caso de erro
        String lista = "listaTransportadoras";
        String caminho = "/WEB-INF/CRUD/transportadora.jsp";

        // Criação do DAO para manipulação dos dados da transportadora
        DAO<Transportadora> dao = new TransportadoraDAO();

        try {
            // ==================== CAPTURA DOS PARÂMETROS ====================

            // Mapeia os parâmetros recebidos do formulário para um mapa de chave-valor
            Map<String, String> params = new LinkedHashMap<>();
            request.getParameterMap().forEach((key, values) -> params.put(key, values[0]));

            // Cria o objeto de modelo Transportadora a partir dos parâmetros enviados
            Transportadora model = new Transportadora(params);

            // Lista todas as transportadoras cadastradas (para exibição em caso de erro)
            List<?> listaTransportadoras = dao.findAll();

            // ==================== VALIDAÇÕES DOS DADOS ====================

            // Valida o CNPJ usando expressão regular
            if (!Regex.validarCnpj(model.getCCnpj())) {
                ErroServlet.setErro(request, response, dao,
                        "Não foi possível cadastrar transportadora. Insira um CNPJ válido.",
                        lista, caminho);
                return;
            }

            // Verifica se já existe uma transportadora cadastrada com o mesmo CNPJ
            if (dao.findById(model.getCCnpj()) != null) {
                ErroServlet.setErro(request, response, dao,
                        "Não foi possível cadastrar transportadora. CNPJ já cadastrado anteriormente.",
                        lista, caminho);
                return;
            }

            // Verifica se o e-mail já está cadastrado no sistema
            if (dao.findByAttribute("cEmail", model.getCEmail()) != null) {
                ErroServlet.setErro(request, response, dao,
                        "Esse e-mail já foi cadastrado! Digite um e-mail válido",
                        lista, caminho);
                return;
            }

            // Valida o formato do e-mail informado
            if (!Regex.validarEmail(model.getCEmail())) {
                ErroServlet.setErro(request, response, dao,
                        "Não foi possível cadastrar transportadora. Insira um e-mail válido.",
                        lista, caminho);
                return;
            }

            // ==================== FORMATAÇÃO DOS DADOS ====================

            // Remove caracteres especiais do CNPJ antes de salvar no banco
            model.setCCnpj(model.getCCnpj()
                    .replace("/", "")
                    .replace(".", "")
                    .replace("-", ""));

            // ==================== SALVAMENTO NO BANCO ====================

            // Insere a transportadora no banco de dados
            dao.save(model);

            // Redireciona para a lista de transportadoras após o sucesso da inserção
            response.sendRedirect(request.getContextPath() + "/transportadora/list");

        } catch (ConnectionFailedException | NotFoundException e) {
            // Captura erros relacionados ao banco de dados (ex: falha na conexão ou registro não encontrado)
            // e mostra mensagem de erro personalizada.

            e.printStackTrace();

            ErroServlet.setErro(request, response, dao, "Falha ao conectar ao banco de dados.", lista, ERROR_PAGE);
        } catch (Exception e) {
            // Captura qualquer outro erro inesperado que possa ocorrer durante o processo
            ErroServlet.setErro(request, response, dao, "Ocorreu um erro inesperado", lista, ERROR_PAGE);
        }
    }
}
