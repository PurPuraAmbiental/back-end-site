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
/** SERVLET INSERT TRANSPORTADORA
 * Tem o objetivo de validar os dados que podem comprometer a inserção
 * no banco de dados e, uma vez validados, adiciona o endereço na lista
 * de endereços do banco.
 *
 * CRUD -> CREATE
 *
 * @author Kevin de Oliveira
 * @author Bruna Oliveira
 **/
@WebServlet(name = "InsertTransportadoraServlet", value = "/transportadora/insert")
public class InsertTransportadoraServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws jakarta.servlet.ServletException, IOException {
        String lista = "listaTransportadoras";
        String caminho = "/CRUD/transportadora.jsp";
        DAO<Transportadora> dao = new TransportadoraDAO();
        try {
            // Mapeia os parâmetros recebidos do formulário
            Map<String, String> params = new LinkedHashMap<>();
            request.getParameterMap().forEach((key, values) -> params.put(key, values[0]));

            // Cria modelo e DAO
            Transportadora model = new Transportadora(params);

            List<?> listaTransportadoras = dao.findAll();

            // VALIDAÇÃO DE DADOS
            // em caso verdadeiro para o servlet
            //valida cnpj a partir do regex
            if (!Regex.validarCnpj(model.getCCnpj())) {
                ErroServlet.setErro(request, response, dao, "Não foi possível cadastrar transportadora. Insira um CNPJ válido.", lista, caminho);
                return;
            }
            //valida se a primary key nao é repetida,
            if (dao.findById(model.getCCnpj()) != null) {
                ErroServlet.setErro(request, response, dao, "Não foi possível cadastrar transportadora. CNPJ já cadastrado anteriormente.", lista, caminho);
                return;
            }
            //valida e-mail a partir do regex
            if (!Regex.validarEmail(model.getCEmail())) {
                ErroServlet.setErro(request, response, dao,"Não foi possível cadastrar transportadora. Insira um e-mail válido.", lista, caminho);
                return;
            }


            // Formata o CNPJ antes de salvar, retirando qualquer caractere que atrapalhe na inserção
            model.setCCnpj(model.getCCnpj().replace("/", "").replace(".", "").replace("-", ""));

            // Salva e retorna para a página de listagem
            dao.save(model);
            response.sendRedirect(request.getContextPath() + "/transportadora/list");
        } catch (ConnectionFailedException | NotFoundException | NumberFormatException e) {
            // Captura de erros específicos
            ErroServlet.setErro(request, response, dao,"Erro ao inserir transportadora: " + e.getMessage(), lista, caminho);
        } catch (Exception e) {
            // Captura geral (caso algo inesperado aconteça)
            ErroServlet.setErro(request, response, dao,"Ocorreu um erro inesperado ao cadastrar transportadora." + e.getMessage(), lista, caminho);
        }
    }
}
