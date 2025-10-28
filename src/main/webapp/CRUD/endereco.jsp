<%--
/**
 * Exibir e gerenciar empresas endereços no sistema PurPura.
 *
 * - Exibe lista de endereços
 * - Permite filtro por estado e nome da empresa dona do endereço
 * - Suporte a inserção, edição e exclusão via pop-ups
 *
 * Autora: Bruna de Jesus
 * Autor: Kevin de Oliveira
 */
--%>

<%@ page import="com.purpura.dto.EnderecoEmpresaView" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">

    <!-- Importação dos arquivos CSS -->
    <!-- O parâmetro ?v=2 serve apenas para forçar atualização do cache no navegador -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/CRUD/crud.css?v=2">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/CRUD/popUp.css?v=2">

    <title>Endereços</title>
</head>

<body>
<div class="main">

    <!-- Cabeçalho da página -->
    <div class="header">
        <h1>Lista de Endereços</h1>

        <!-- Botão que abre o pop-up de cadastro de novo endereço -->
        <button class="add-btn" onclick="abrirPopupInsert()">Cadastrar Endereço</button>
        <br>
        <br>

        <!-- Exibição de mensagem de erro, caso o Servlet tenha enviado alguma -->
        <%
            String erro = (String) request.getAttribute("erro");
            if (erro != null) {
        %>
        <h5><%= erro %></h5>
        <% } %>

        <br>
    </div>
    <div class="filtro">
        <form method="get" action="<%=request.getContextPath()%>/endereco-empresa/list">
            <label for="estado">Selecione o estado desejado</label><br>
            <select name="estado" id="estado">
                <option value="">Selecione um estado</option>
                <option value="AC">Acre</option>
                <option value="AL">Alagoas</option>
                <option value="AP">Amapá</option>
                <option value="AM">Amazonas</option>
                <option value="BA">Bahia</option>
                <option value="CE">Ceará</option>
                <option value="DF">Distrito Federal</option>
                <option value="ES">Espírito Santo</option>
                <option value="GO">Goiás</option>
                <option value="MA">Maranhão</option>
                <option value="MT">Mato Grosso</option>
                <option value="MS">Mato Grosso do Sul</option>
                <option value="MG">Minas Gerais</option>
                <option value="PA">Pará</option>
                <option value="PB">Paraíba</option>
                <option value="PR">Paraná</option>
                <option value="PE">Pernambuco</option>
                <option value="PI">Piauí</option>
                <option value="RJ">Rio de Janeiro</option>
                <option value="RN">Rio Grande do Norte</option>
                <option value="RS">Rio Grande do Sul</option>
                <option value="RO">Rondônia</option>
                <option value="RR">Roraima</option>
                <option value="SC">Santa Catarina</option>
                <option value="SP">São Paulo</option>
                <option value="SE">Sergipe</option>
                <option value="TO">Tocantins</option>
            </select>

            <br><br>

            <label for="nomeEmpresa">Insira o nome da empresa</label><br>
            <input type="text" id="nomeEmpresa" name="nomeEmpresa" placeholder="Digite o nome da empresa">

            <br><br>

            <button type="submit" class="add-btn">Filtrar</button>
        </form>
    </div>

    <!-- Container da tabela -->
    <div class="table-container">
        <table>
            <thead>
            <!-- Cabeçalho das colunas -->
            <tr>
                <th>Estado</th>
                <th>Cidade</th>
                <th>Bairro</th>
                <th>Logradouro</th>
                <th>Complemento</th>
                <th>Número</th>
                <th>CEP</th>
                <th>Empresa</th>
                <th>Ações</th>
            </tr>
            </thead>

            <tbody>
            <%
                // Recupera a lista de endereços enviada pelo servlet
                List<EnderecoEmpresaView> listaEmpresa =
                        (List<EnderecoEmpresaView>) request.getAttribute("listaEnderecos");

                // Verifica se a lista não está vazia
                if (listaEmpresa != null && !listaEmpresa.isEmpty()) {
                    // Percorre todos os endereços e exibe um por linha
                    for (EnderecoEmpresaView empresa : listaEmpresa) {
            %>
            <tr>
                <!-- Cada célula mostra um atributo do endereço -->
                <td><%= empresa.cEstado() %></td>
                <td><%= empresa.cCidade() %></td>
                <td><%= empresa.cBairro() %></td>
                <td><%= empresa.cLogradouro() %></td>
                <td><%= empresa.cComplemento() != null ? empresa.cComplemento() : "" %></td>
                <td><%= empresa.iNrEnderecoEmpresa() %></td>
                <td><%= empresa.cCep() %></td>
                <td><%= empresa.cNmEmpresa() %></td>

                <td>
                    <!-- Botão de edição que preenche o pop-up com os dados -->
                    <button class="add-btn"
                            onclick="UpdateEndereco(
                                    '<%= empresa.nCdEnderecoEmpresa() %>',
                                    '<%= empresa.cBairro() %>',
                                    '<%= empresa.cLogradouro() %>',
                                    '<%= empresa.cEstado() %>',
                                    '<%= empresa.cCidade() %>',
                                    '<%= empresa.cCep() %>',
                                    '<%= empresa.cComplemento() != null ? empresa.cComplemento() : "" %>',
                                    '<%= empresa.iNrEnderecoEmpresa() %>',
                                    '<%= empresa.cNmEmpresa() %>')">
                      ar
                    </button>

                    <!-- Formulário de exclusão com envio via POST -->
                    <form action="${pageContext.request.contextPath}/endereco-empresa/delete"
                          method="post"
                          style="display:inline;">
                        <!-- ID do endereço que será excluído -->
                        <input type="hidden" name="nCdEnderecoEmpresa"
                               value="<%= empresa.nCdEnderecoEmpresa() %>">
                        <input class="add-btn" type="submit" value="Excluir">
                    </form>
                </td>
            </tr>
            <%
                } // fim do for
            } else {
                // Caso não haja endereços cadastrados
            %>
            <tr><td colspan="9">Nenhum endereço encontrado.</td></tr>
            <% } %>
            </tbody>
        </table>
    </div>
</div>

<!-- Inclui o JSP responsável pelos pop-ups -->
<jsp:include page="/WEB-INF/popUp's/popUp-endereco.jsp" />

<!-- Funções JavaScript usadas para controlar os pop-ups -->
<script>
    // Exibe o pop-up de inserção
    function abrirPopupInsert() {
        document.getElementById('popup-insert').style.display = 'flex';
    }

    // Fecha o pop-up informado pelo ID
    function fecharPopup(id) {
        document.getElementById(id).style.display = 'none';
    }

    // Preenche o pop-up de atualização com os dados do endereço clicado
    function UpdateEndereco(id, bairro, logradouro, estado, cidade, cep, complemento, numero, empresa) {
        document.getElementById('upd-id').value = id;
        document.getElementById('upd-bairro').value = bairro;
        document.getElementById('upd-logradouro').value = logradouro;
        document.getElementById('upd-estado').value = estado;
        document.getElementById('upd-cidade').value = cidade;
        document.getElementById('upd-cep').value = cep;
        document.getElementById('upd-complemento').value = complemento;
        document.getElementById('upd-numero').value = numero;
        document.getElementById('upd-empresa').value = empresa;

        // Exibe o pop-up de edição
        document.getElementById('popup-update').style.display = 'flex';
    }
</script>
</body>
</html>
