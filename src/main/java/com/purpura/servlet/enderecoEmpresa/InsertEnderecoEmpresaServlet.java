package com.purpura.servlet.enderecoEmpresa;

import com.purpura.common.ErroServlet;
import com.purpura.common.Regex;
import com.purpura.dao.EmpresaDAO;
import com.purpura.dao.EnderecoEmpresaDAO;
import com.purpura.dto.EnderecoEmpresaView;
import com.purpura.exception.ConnectionFailedException;
import com.purpura.exception.NotFoundException;
import com.purpura.model.Empresa;
import com.purpura.model.EnderecoEmpresa;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Servlet responsável por inserir um novo Endereço de Empresa no sistema.
 *
 * Valida os dados enviados pelo formulário, garantindo que a empresa exista,
 * esteja ativa e que o CEP seja válido. Após a validação, insere o registro
 * no banco de dados e redireciona o usuário para a listagem atualizada.
 *
 * =============== ESTA CLASSE FAZ USO DE REGEX ===============================
 *
 * CRUD -> CREATE
 *
 * @author Kevin
 * @author Bruna
 */
@WebServlet(name = "InsertEnderecoEmpresaServlet", value = "/endereco-empresa/insert")
public class InsertEnderecoEmpresaServlet extends HttpServlet {

    /**
     * Processa a requisição POST para inserir um novo Endereço de Empresa.
     *
     * @param request  objeto HttpServletRequest contendo os parâmetros do formulário
     * @param response objeto HttpServletResponse usado para redirecionamento ou forward
     * @throws jakarta.servlet.ServletException se ocorrer erro no servlet
     * @throws IOException                      se ocorrer erro de I/O
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws jakarta.servlet.ServletException, IOException {

        // Define o nome da lista e o caminho do JSP para uso em caso de erro
        String lista = "listaEnderecos";
        String caminho = "/WEB-INF/CRUD/endereco.jsp";

        // Cria o DAO responsável por manipular registros de endereço de empresa
        EnderecoEmpresaDAO dao = new EnderecoEmpresaDAO();

        try {
            // ==================== CAPTURA DOS PARÂMETROS ====================

            // Mapeia os parâmetros recebidos do formulário para um Map<String, String>
            Map<String, String> params = new LinkedHashMap<>();
            request.getParameterMap().forEach((key, values) -> params.put(key, values[0]));

            // Obtém o nome da empresa digitado no formulário
            String nomeEmpresa = params.get("cNmEmpresa");

            // Busca a empresa no banco com base no nome informado
            EmpresaDAO empresaDAO = new EmpresaDAO();
            Empresa empresa = empresaDAO.findByAttribute("cNmEmpresa", nomeEmpresa);

            // Cria o objeto de modelo EnderecoEmpresa com os dados recebidos
            EnderecoEmpresa model = new EnderecoEmpresa(params);

            // Carrega a lista de endereços para exibição na página (em caso de erro)
            List<EnderecoEmpresaView> listaEnderecos = dao.listarComEmpresa();

            // ==================== VALIDAÇÕES DOS DADOS ====================

            // Verifica se a empresa existe no banco
            if (empresa == null) {
                EnderecoEmpresaViewSetErro(request, response, dao, listaEnderecos,
                        "Não foi possível cadastrar o endereço! Insira uma empresa cadastrada anteriormente.",
                        lista, caminho);
                return;
            }

            // Verifica se a empresa está ativa
            if (empresa.getCAtivo() != '1') {
                EnderecoEmpresaViewSetErro(request, response, dao, listaEnderecos,
                        "Não foi possível cadastrar endereço! Insira uma empresa ativa.",
                        lista, caminho);
                return;
            }

            // Define o CNPJ da empresa no endereço (chave estrangeira)
            model.setCCnpj(empresa.getCCnpj());

            // Valida o formato do CEP utilizando Regex
            if (!Regex.validarCEP(model.getCCep())) {
                EnderecoEmpresaViewSetErro(request, response, dao, listaEnderecos,
                        "Não foi possível cadastrar o endereço! Insira um CEP válido.",
                        lista, caminho);
                return;
            }

            // Remove caracteres especiais do CEP antes de salvar
            model.setCCep(model.getCCep().replace("-", ""));
            System.out.println(model.getCCep()); // Log para depuração

            // ==================== SALVAMENTO NO BANCO ====================

            // Salva o endereço da empresa no banco de dados
            dao.save(model);

            // Redireciona para a lista de endereços após a inserção bem-sucedida
            response.sendRedirect(request.getContextPath() + "/endereco-empresa/list");

        } catch (ConnectionFailedException e) {
            // Trata erros relacionados à conexão com o banco
            ErroServlet.setErro(request, response, dao,
                    "Falha de conexão com o banco de dados: " + e.getMessage(),
                    lista, caminho);

        } catch (NotFoundException e) {
            // Trata casos em que registros relacionados não foram encontrados
            ErroServlet.setErro(request, response, dao,
                    "Registro não encontrado: " + e.getMessage(),
                    lista, caminho);

        } catch (NumberFormatException e) {
            // Trata erros de conversão numérica (por exemplo, campos incorretos)
            ErroServlet.setErro(request, response, dao,
                    "Erro de formatação numérica nos dados inseridos: " + e.getMessage(),
                    lista, caminho);

        } catch (Exception e) {
            // Captura qualquer outro erro inesperado que possa ocorrer
            ErroServlet.setErro(request, response, dao,
                    "Erro inesperado ao inserir endereço: " + e.getMessage(),
                    lista, caminho);
        }
    }

    /**
     * Método auxiliar responsável por exibir mensagens de erro e reenviar o usuário
     * para a página JSP de endereços, mantendo os dados da lista atualizados.
     *
     * @param request             requisição HTTP
     * @param response            resposta HTTP
     * @param enderecoEmpresaDAO  DAO utilizado para recarregar a lista de endereços
     * @param enderecoEmpresaView lista de endereços com dados de empresa
     * @param mensagem            mensagem de erro a ser exibida
     * @param lista               nome do atributo da lista no JSP
     * @param caminho             caminho da página JSP de destino
     * @throws jakarta.servlet.ServletException se ocorrer erro no forward
     * @throws IOException                      se ocorrer erro de I/O
     */
    public void EnderecoEmpresaViewSetErro(HttpServletRequest request, HttpServletResponse response,
                                           EnderecoEmpresaDAO enderecoEmpresaDAO,
                                           List<EnderecoEmpresaView> enderecoEmpresaView,
                                           String mensagem, String lista, String caminho)
            throws jakarta.servlet.ServletException, IOException {

        // Recarrega a lista de endereços vinculados às empresas
        enderecoEmpresaView = enderecoEmpresaDAO.listarComEmpresa();

        // Define os atributos de erro e lista de endereços na requisição
        request.setAttribute(lista, enderecoEmpresaView);
        request.setAttribute("erro", mensagem);

        // Encaminha de volta para a página de endereços mantendo o estado da requisição
        request.getRequestDispatcher(caminho).forward(request, response);
    }
}
