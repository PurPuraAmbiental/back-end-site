<%@ page import="com.purpura.dto.EndecoEmpresaView" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/CRUD/crud.css">
    <title>endereço-crud</title>
</head>
<body>
<div class="main">
    <div class="header">
        <h1>Lista de Endereços</h1>
        <button class="add-btn">Adicionar Endereço</button>
    </div>

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
            <th>Nome da Empresa</th>
            <th>Ações</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<EndecoEmpresaView> enderecos = (List<EndecoEmpresaView>) request.getAttribute("listaEnderecos");
            if (enderecos != null && !enderecos.isEmpty()) {
                for (EndecoEmpresaView endereco : enderecos) {
        %>
        <tr>
            <td><%= endereco.cEstado() %></td>
            <td><%= endereco.cCidade() %></td>
            <td><%= endereco.cBairro() %></td>
            <td><%= endereco.cLogradouro() %></td>

            <%if (endereco.cComplemento() != null) {%>
            <td><%= endereco.cComplemento() %></td>
            <%}else {%>
            <td> </td>
            <%}%>

            <td><%= endereco.iNrEnderecoEmpresa() %></td>
            <td><%= endereco.cCep() %></td>
            <td><%= endereco.cNmEmpresa() %></td>

            <td class="actions">
                <img src="c:\Users\irisrodrigues-ieg\Downloads\pen 1.png" alt="Editar">
                <form action="${pageContext.request.contextPath}/endereco-empresa/delete" method="post">
                    <input type="hidden" name="nCdResiduo" value="<%=endereco.nCdEnderecoEmpresa()%>" >
                    <input type="submit" value="Delete">
                </form>
            </td>
        </tr>

        <%
            }
        } else {
        %>
        <tr>
            <td colspan="6">Nenhum endereço encontrado.</td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>

<script src="script.js"></script>

</body>
</html>