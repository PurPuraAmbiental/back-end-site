<%@ page import="com.purpura.dto.EnderecoEmpresaView" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/CRUD/crud.css?v=2">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/CRUD/popUp.css?v=2">
    <title>Endereços</title>
</head>
<body>
<div class="main">
    <div class="header">
        <h1>Lista de Endereços</h1>
        <button class="add-btn" onclick="abrirPopupInsert()">Cadastrar Endereço</button>
    </div>
    <div class="table-container">
    <table>
        <thead>
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
            List<EnderecoEmpresaView> listaEmpresa = (List<EnderecoEmpresaView>) request.getAttribute("listaEnderecos");
            if (listaEmpresa != null && !listaEmpresa.isEmpty()) {
                for (EnderecoEmpresaView empresa : listaEmpresa) {
        %>
        <tr>
            <td><%= empresa.cEstado() %></td>
            <td><%= empresa.cCidade() %></td>
            <td><%= empresa.cBairro() %></td>
            <td><%= empresa.cLogradouro() %></td>
            <td><%= empresa.cComplemento() != null ? empresa.cComplemento() : "" %></td>
            <td><%= empresa.iNrEnderecoEmpresa() %></td>
            <td><%= empresa.cCep() %></td>
            <td><%= empresa.cNmEmpresa() %></td>
            <td>
                <button class="add-btn"
                        onclick="UpdateEndereco('<%= empresa.nCdEnderecoEmpresa() %>', '<%= empresa.cBairro() %>', '<%= empresa.cLogradouro() %>',
                                '<%= empresa.cEstado() %>', '<%= empresa.cCidade() %>', '<%= empresa.cCep() %>',
                                '<%= empresa.cComplemento() != null ? empresa.cComplemento() : "" %>',
                                '<%= empresa.iNrEnderecoEmpresa() %>', '<%= empresa.cNmEmpresa() %>')">
                    Editar
                </button>

                <form action="${pageContext.request.contextPath}/endereco-empresa/delete" method="post" style="display:inline;">
                    <input type="hidden" name="nCdEnderecoEmpresa" value="<%= empresa.nCdEnderecoEmpresa() %>">
                    <input class="add-btn" type="submit" value="Excluir">
                </form>
            </td>
        </tr>
        <%  } } else { %>
        <tr><td colspan="9">Nenhum endereço encontrado.</td></tr>
        <% } %>
        </tbody>
    </table>
        </div>
</div>

<jsp:include page="/WEB-INF/popUp's/popUp-endereco.jsp" />

<script>
    function abrirPopupInsert() {
        document.getElementById('popup-insert').style.display = 'flex';
    }
    function fecharPopup(id) {
        document.getElementById(id).style.display = 'none';
    }
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
        document.getElementById('popup-update').style.display = 'flex';
    }
</script>
</body>
</html>
