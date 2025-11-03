package com.purpura.servlet.enderecoEmpresa;

import com.purpura.common.ErroServlet;
import com.purpura.dao.EmpresaDAO;
import com.purpura.dao.EnderecoEmpresaDAO;
import com.purpura.dto.EnderecoEmpresaView;
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
import java.util.List;
import java.util.Map;

import static com.purpura.common.Constants.ERROR_PAGE;

/**
 * Servlet responsável por atualizar os dados de um Endereço de Empresa existente.
 *
 * Recebe os dados via POST, valida a empresa e seus atributos,
 * e atualiza o registro no banco de dados.
 *
 * @author
 *     Bruna de Jesus e Kevin de Oliveira
 */
@WebServlet(name = "UpdateEnderecoEmpresaServlet", value = "/endereco-empresa/update")
public class UpdateEnderecoEmpresaServlet extends HttpServlet {

    /**
     * Processa o POST para atualizar um Endereço de Empresa.
     *
     * @param request  objeto HttpServletRequest contendo os parâmetros do formulário
     * @param response objeto HttpServletResponse para redirecionamento ou forward
     * @throws jakarta.servlet.ServletException se ocorrer erro no servlet
     * @throws IOException                      se ocorrer erro de I/O
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws jakarta.servlet.ServletException, IOException {

        // Em caso de erro, essas variáveis são usadas para repassar informações de contexto
        EnderecoEmpresaDAO dao = new EnderecoEmpresaDAO();
        String lista = "listaEnderecos";
        String caminho = "/WEB-INF/CRUD/endereco.jsp";

        try {
            // Cria um mapa contendo os parâmetros enviados pelo formulário
            Map<String, String> params = new LinkedHashMap<>();
            request.getParameterMap().forEach((key, values) -> params.put(key, values[0]));

            // Recupera a empresa pelo nome
            String nomeEmpresa = params.get("cNmEmpresa");
            EmpresaDAO empresaDAO = new EmpresaDAO();
            Empresa empresa = empresaDAO.findByAttribute("cNmEmpresa", nomeEmpresa);
            params.put("cCnpj", empresa.getCCnpj());
            System.out.println(params.get("cNmEmpresa")+" | "+params.get("cCnpj"));

            // Cria um objeto 'EnderecoEmpresa' a partir dos parâmetros recebidos
            EnderecoEmpresa model = new EnderecoEmpresa(params);
            List<EnderecoEmpresaView> listaEnderecos = dao.listarComEmpresa();

            // ==================== VALIDAÇÕES DE DADOS ====================

            // Valida se a empresa existe
            if (empresa != null) {
                EnderecoEmpresaViewSetErro(request, response, dao, listaEnderecos,
                        "Não foi possível atualizar o endereço! Insira uma empresa cadastrada anteriormente.",
                        lista, caminho);
                return;
            }

            // Valida se a empresa está ativa
            if (empresa.getCAtivo() != '1'){
                EnderecoEmpresaViewSetErro(request, response, dao, listaEnderecos,
                        "Nao foi possivel atualizar endereco! Insira uma empresa ativa",
                        lista, caminho);
                return;
            }

            // Atualiza os atributos do modelo com os novos valores do formulário
            model.setCBairro(params.get("cBairro"));
            model.setCLogradouro(params.get("cLogradouro"));
            model.setCEstado(params.get("cEstado"));
            model.setCCidade(params.get("cCidade"));
            model.setCCep(params.get("cCep"));
            model.setCComplemento(params.get("cComplemento"));
            model.setINrEnderecoEmpresa(Integer.parseInt(params.get("iNrEnderecoEmpresa")));
            model.setCCnpj(params.get("cCnpj"));
            model.setNCdEnderecoEmpresa(Integer.parseInt(params.get("nCdEnderecoEmpresa")));

            // Atualiza o registro no banco de dados
            dao.update(model);

            // Após atualização bem-sucedida, redireciona para a listagem de endereços de empresa
            response.sendRedirect(request.getContextPath() + "/endereco-empresa/list");

        } catch (ConnectionFailedException | NotFoundException e) {
            // Trata erros de conexão com o banco e mostra mensagem personalizada
            e.printStackTrace();
            ErroServlet.setErro(request, response, dao, e, lista, ERROR_PAGE);
        } catch (Exception e) {
            ErroServlet.setErro(request, response, dao,
                    "Ocorreu um erro inesperado.",
                    lista, ERROR_PAGE);
        }
    }

    /**
     * Método auxiliar para setar erro e repassar a lista de endereços de empresa
     * para a view quando ocorre validação ou erro de atualização.
     *
     * @param request  objeto HttpServletRequest
     * @param response objeto HttpServletResponse
     * @param enderecoEmpresaDAO DAO de EnderecoEmpresa
     * @param enderecoEmpresaView lista de EnderecoEmpresaView
     * @param mensagem mensagem de erro a ser exibida
     * @param lista nome do atributo da lista na requisição
     * @param caminho caminho da página JSP
     * @throws jakarta.servlet.ServletException se ocorrer erro no servlet
     * @throws IOException                      se ocorrer erro de I/O
     */
    public void EnderecoEmpresaViewSetErro(HttpServletRequest request, HttpServletResponse response,
                                           EnderecoEmpresaDAO enderecoEmpresaDAO,
                                           List<EnderecoEmpresaView> enderecoEmpresaView,
                                           String mensagem, String lista, String caminho)
            throws jakarta.servlet.ServletException, IOException {

        enderecoEmpresaView = enderecoEmpresaDAO.listarComEmpresa();
        request.setAttribute(lista, enderecoEmpresaView);
        request.setAttribute("erro", mensagem);
        request.getRequestDispatcher(caminho).forward(request, response);
    }
}
