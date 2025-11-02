package com.purpura.servlet.residuo;

import com.purpura.common.ErroServlet;
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

/**
 * SERVLET INSERT RESÍDUO
 *
 * Responsável por inserir novos registros de resíduos no sistema.
 * Realiza validações essenciais antes da persistência, garantindo que:
 * - A empresa informada exista e esteja ativa;
 * - Os dados recebidos do formulário sejam consistentes.
 *
 * CRUD -> CREATE
 *
 * @author Kevin
 * @author Bruna
 */
@WebServlet(name = "InsertResiduoServlet", value = "/residuo/insert")
public class InsertResiduoServlet extends HttpServlet {

    /**
     * Método responsável por processar a requisição POST para inserir um novo resíduo.
     *
     * @param request  objeto HttpServletRequest com os parâmetros enviados pelo formulário
     * @param response objeto HttpServletResponse usado para redirecionar ou reenviar o usuário
     * @throws jakarta.servlet.ServletException em caso de erro na execução do servlet
     * @throws IOException                      em caso de falha de entrada/saída
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws jakarta.servlet.ServletException, IOException {

        // Criação do DAO principal
        ResiduoDAO residuoDAO = new ResiduoDAO();

        // Atributos de controle para exibição e encaminhamento
        String lista = "listaResiduos";
        String caminho = "/WEB-INF/CRUD/residuos.jsp";

        // Carrega a lista atual de resíduos para exibição em caso de erro
        List<ResiduoView> residuoViews = residuoDAO.listarComEmpresa();

        try {
            // ==================== CAPTURA DE PARÂMETROS ====================

            // Mapeia todos os parâmetros recebidos do formulário HTML
            Map<String, String> params = new LinkedHashMap<>();
            request.getParameterMap().forEach((key, values) -> params.put(key, values[0]));

            // Obtém o nome da empresa a partir dos parâmetros
            String nomeEmpresa = params.get("cNmEmpresa");

            // Cria o DAO da empresa e busca a empresa correspondente
            EmpresaDAO empresaDAO = new EmpresaDAO();
            Empresa empresa = empresaDAO.findByAttribute("cNmEmpresa", nomeEmpresa);
            System.out.println("erro: " + empresa); // Log para depuração

            // Cria o modelo Residuo com os dados recebidos
            Residuo model = new Residuo(params);

            // Lista de resíduos para visualização (em caso de erro)
            List<ResiduoView> listaResiduos = residuoDAO.listarComEmpresa();

            // ==================== VALIDAÇÕES DE DADOS ====================

            // Verifica se a empresa informada está cadastrada
            if (empresa == null) {
                residuoViewSetErro(request, response, residuoDAO, residuoViews,
                        "Não foi possível cadastrar Resíduo! Insira uma empresa cadastrada anteriormente.",
                        lista, caminho);
                return;
            }

            // Define o CNPJ da empresa (chave estrangeira) no modelo
            model.setCCnpj(empresa.getCCnpj());

            // Verifica se a empresa está ativa no sistema
            if (empresa.getCAtivo() != '1') {
                residuoViewSetErro(request, response, residuoDAO, residuoViews,
                        "Não foi possível cadastrar Resíduo! Insira uma empresa ativa.",
                        lista, caminho);
                return;
            }

            // ==================== INSERÇÃO NO BANCO DE DADOS ====================

            // Salva o registro do resíduo
            residuoDAO.save(model);

            // Redireciona para a listagem de resíduos após sucesso
            response.sendRedirect(request.getContextPath() + "/residuo/list");

        } catch (ConnectionFailedException | NotFoundException | NumberFormatException e) {
            // Tratamento de erros específicos
            residuoViewSetErro(request, response, residuoDAO, residuoViews,
                    "Erro ao inserir Resíduo: " + e.getMessage(),
                    lista, caminho);
        }
    }

    /**
     * Método auxiliar responsável por exibir mensagens de erro e recarregar a lista
     * de resíduos, mantendo o usuário na mesma página JSP com os dados atualizados.
     *
     * @param request        objeto HttpServletRequest
     * @param response       objeto HttpServletResponse
     * @param residuoDAO     DAO utilizado para recarregar a lista de resíduos
     * @param residuoView    lista de resíduos com dados da empresa
     * @param mensagem       mensagem de erro a ser exibida
     * @param lista          nome do atributo da lista no JSP
     * @param caminho        caminho da página JSP de destino
     * @throws jakarta.servlet.ServletException em caso de falha no forward
     * @throws IOException                      em caso de erro de I/O
     */
    public void residuoViewSetErro(HttpServletRequest request, HttpServletResponse response,
                                   ResiduoDAO residuoDAO,
                                   List<ResiduoView> residuoView,
                                   String mensagem, String lista, String caminho)
            throws jakarta.servlet.ServletException, IOException {

        // Recarrega a lista de resíduos vinculados às empresas
        residuoView = residuoDAO.listarComEmpresa();

        // Define atributos da requisição para exibição de erro e lista
        request.setAttribute(lista, residuoView);
        request.setAttribute("erro", mensagem);

        // Encaminha de volta para o JSP mantendo o estado da requisição
        request.getRequestDispatcher(caminho).forward(request, response);
    }
}
