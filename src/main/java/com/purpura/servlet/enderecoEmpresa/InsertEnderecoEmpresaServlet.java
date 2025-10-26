package com.purpura.servlet.enderecoEmpresa;
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

/** SERVLET INSERT ENDERECO EMPRESA
 * Tem o objetivo de validar os dados que podem comprometer a inserção
 * no banco de dados e, uma vez validados, adiciona o endereço na lista
 * de endereços do banco.
 *
 * CRUD -> CREATE
 *
 * @author Kevin de Oliveira
 * @author Bruna Oliveira
 **/
@WebServlet(name = "InsertEnderecoEmpresaServlet", value = "/endereco-empresa/insert")
public class InsertEnderecoEmpresaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws jakarta.servlet.ServletException, IOException {
        try {
            // Pega os parâmetros dados pelo usuário
            Map<String, String> params = new LinkedHashMap<>();
            request.getParameterMap().forEach((key, values) -> params.put(key, values[0]));

            // A partir do nome da empresa, procura o CNPJ (FK da tabela endereco_empresa) e armazena no objeto
            String nomeEmpresa = params.get("cNmEmpresa");
            EmpresaDAO empresaDAO = new EmpresaDAO();
            Empresa empresa = empresaDAO.findByAttribute("cNmEmpresa", nomeEmpresa);

            // Cria um objeto da entidade a partir dos parâmetros do formulário
            EnderecoEmpresa model = new EnderecoEmpresa(params);
            EnderecoEmpresaDAO dao = new EnderecoEmpresaDAO();

            // Cria um objeto da view que guarda as informações que serão dadas à interface
            List<EnderecoEmpresaView> listaEnderecos = dao.listarComEmpresa();

            // VERIFICAÇÃO DE DADOS
            // Caso a empresa seja nula, significa que ela não foi cadastrada com antecedência.
            // Então o servlet para e exibe uma mensagem de erro ao usuário.
            if (empresa == null) {
                request.setAttribute("listaEnderecos", listaEnderecos);
                request.setAttribute("erro", "Não foi possível cadastrar o endereço! Insira uma empresa cadastrada anteriormente.");
                request.getRequestDispatcher("/CRUD/endereco.jsp").forward(request, response);
                return;
            }

            // Caso contrário, a model pega a FK correspondente
            model.setCCnpj(empresa.getCCnpj());

            // Uma vez certo, insere no banco de dados
            dao.save(model);

            // Direciona de forma atualizada após a inserção
            listaEnderecos = dao.listarComEmpresa();
            request.setAttribute("listaEnderecos", listaEnderecos);
            request.getRequestDispatcher("/CRUD/endereco.jsp").forward(request, response);

        } catch (ConnectionFailedException e) {
            request.setAttribute("erro", "Falha de conexão com o banco de dados: " + e.getMessage());
            request.getRequestDispatcher("/CRUD/endereco.jsp").forward(request, response);

        } catch (NotFoundException e) {
            request.setAttribute("erro", "Registro não encontrado: " + e.getMessage());
            request.getRequestDispatcher("/CRUD/endereco.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            request.setAttribute("erro", "Erro de formatação numérica nos dados inseridos: " + e.getMessage());
            request.getRequestDispatcher("/CRUD/endereco.jsp").forward(request, response);

        } catch (Exception e) {
            request.setAttribute("erro", "Erro inesperado ao inserir endereço: " + e.getMessage());
            request.getRequestDispatcher("/CRUD/endereco.jsp").forward(request, response);
        }
    }
}
