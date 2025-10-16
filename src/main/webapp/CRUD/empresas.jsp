<%@ page import="com.purpura.model.Empresa" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/CRUD/crud.css">
    <title>empresas-crud</title>
</head>
<body>
<div class="main">
    <div class="header">
        <h1>Lista de Empresas</h1>
        <button class="add-btn">Adicionar Empresa</button>
    </div>

    <table>
        <thead>
        <tr>
            <th>Nome</th>
            <th>Email</th>
            <th>Senha</th>
            <th>CNPJ</th>
            <th>Ativo</th>
            <th>Ações</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Empresa> empresas = (List<Empresa>) request.getAttribute("listaEmpresas");
            if (empresas != null && !empresas.isEmpty()) {
                for (Empresa empresa : empresas) {
        %>
        <tr>
            <td><%= empresa.getCNmEmpresa() %></td>
            <td><%= empresa.getCEmail() %></td>
            <td>
                <span class="hidden-password"><%= empresa.getCSenha() %></span>
            </td>
            <td><%= empresa.getCCnpj() %></td>
            <% if (empresa.getCAtivo() == '1') {%>
            <td> Ativo </td>
            <%}else{%>
            <td> Inativo </td> <%}%>

            <td class="actions">
                <img src="c:\Users\irisrodrigues-ieg\Downloads\pen 1.png" alt="Editar">
                <img src="c:\Users\irisrodrigues-ieg\Downloads\trash 1.png" alt="Excluir">
            </td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="6">Nenhuma empresa encontrada.</td>
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