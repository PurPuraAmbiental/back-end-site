package com.purpura.servlet.transportadora;
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
        try {
            // Mapeia os parâmetros recebidos do formulário
            Map<String, String> params = new LinkedHashMap<>();
            request.getParameterMap().forEach((key, values) -> params.put(key, values[0]));

            // Cria modelo e DAO
            Transportadora model = new Transportadora(params);
            DAO<Transportadora> dao = new TransportadoraDAO();
            List<?> listaTransportadoras = dao.findAll();

            // VALIDAÇÃO DE DADOS
            // em caso verdadeiro para o servlet
            //valida cnpj a partir do regex
            if (!Regex.validarCnpj(model.getCCnpj())) {
                setErro(request, response, "Não foi possível cadastrar transportadora. Insira um CNPJ válido.", listaTransportadoras);
                return;
            }
            //valida se a primary key nao é repetida,
            if (dao.findById(model.getCCnpj()) != null) {
                setErro(request, response, "Não foi possível cadastrar transportadora. CNPJ já cadastrado anteriormente.", listaTransportadoras);
                return;
            }
            //valida e-mail a partir do regex
            if (!Regex.validarEmail(model.getCEmail())) {
                setErro(request, response, "Não foi possível cadastrar transportadora. Insira um e-mail válido.", listaTransportadoras);
                return;
            }

            // Formata o CNPJ antes de salvar, retirando qualquer caractere que atrapalhe na inserção
            model.setCCnpj(model.getCCnpj().replace("/", "").replace(".", "").replace("-", ""));

            // Salva e retorna para a página de listagem
            dao.save(model);
            listaTransportadoras = dao.findAll();
            request.setAttribute("listaTransportadoras", listaTransportadoras);
            request.getRequestDispatcher("/CRUD/transportadora.jsp").forward(request, response);

        } catch (ConnectionFailedException | NotFoundException | NumberFormatException e) {
            // Captura de erros específicos
            request.setAttribute("erro", "Erro ao inserir transportadora: " + e.getMessage());
            request.getRequestDispatcher("/CRUD/transportadora.jsp").forward(request, response);
        } catch (Exception e) {
            // Captura geral (caso algo inesperado aconteça)
            request.setAttribute("erro", "Ocorreu um erro inesperado ao cadastrar transportadora.");
            request.getRequestDispatcher("/CRUD/transportadora.jsp").forward(request, response);
        }
    }

    // Método auxiliar para evitar repetição,
    //em caso de alguma das estruturas de repetição acima for igual a true:
    private void setErro(HttpServletRequest request, HttpServletResponse response, String mensagem, List<?> lista)
            throws jakarta.servlet.ServletException, IOException {
        request.setAttribute("erro", mensagem);
        request.setAttribute("listaTransportadoras", lista);
        request.getRequestDispatcher("/CRUD/transportadora.jsp").forward(request, response);
    }
}
