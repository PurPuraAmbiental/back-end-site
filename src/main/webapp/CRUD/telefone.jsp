<%@ page import="java.util.List" %>
<%@ page import="com.purpura.dto.TelefoneView" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/CRUD/crud.css">
    <title>telefone-crud</title>
</head>
<body>
<div class="main">
    <div class="header">
        <h1>Lista de Telefones</h1>
        <button class="add-btn">Adicionar Telefone</button>
    </div>

    <table>
        <thead>
        <tr>
            <th>Número de telefone</th>
            <th>Nome da Empresa</th>
            <th>Ações</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<TelefoneView> telefones = (List<TelefoneView>) request.getAttribute("listaTelefones");
            if (telefones != null && !telefones.isEmpty()) {
                for (TelefoneView telefone : telefones) {
        %>
        <tr>
            <td><%= telefone.cNrTelefone() %></td>
            <td><%= telefone.cNmEmpresa() %></td>

            <td class="actions">
                <img src="c:\Users\irisrodrigues-ieg\Downloads\pen 1.png" alt="Editar">
                <form action="${pageContext.request.contextPath}/telefone/delete" method="post">
                    <input type="hidden" name="nCdTelefone" value="<%=telefone.nCdTelefone()%>" >
                    <input type="submit" value="Delete">
                </form>
            </td>
        </tr>

        <%
            }
        } else {
        %>
        <tr>
            <td colspan="6">Nenhum resíduo encontrado.</td>
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