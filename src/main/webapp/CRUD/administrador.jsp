<%@ page import="java.util.List" %>
<%@ page import="com.purpura.model.Administrador" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/CRUD/crud.css">
    <title>administrador-crud</title>
</head>
<body>
<div class="main">
    <div class="header">
        <h1>Lista de Administradores</h1>
        <button class="add-btn">Adicionar Administrador</button>
    </div>

    <table>
        <thead>
        <tr>
            <th>Nome do administrador</th>
            <th>Email</th>
            <th>Senha</th>
            <th>Ações</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Administrador> administradores = (List<Administrador>) request.getAttribute("listaAdministradores");
            if (administradores != null && !administradores.isEmpty()) {
                for (Administrador administrador : administradores) {
        %>
        <tr>
            <td><%= administrador.getCNmAdministrador() %></td>
            <td><%= administrador.getCEmail() %></td>
            <td><%= administrador.getCSenha() %></td>

            <td class="actions">
                <img src="c:\Users\irisrodrigues-ieg\Downloads\pen 1.png" alt="Editar">
                <form action="${pageContext.request.contextPath}/administrador/delete" method="post">
                    <input type="hidden" name="cEmail" value="<%=administrador.getCEmail()%>">
                    <input type="submit" value="Delete">
                </form>
            </td>
        </tr>

        <%
            }
        } else {
        %>
        <tr>
            <td colspan="6">Nenhum administrador encontrado.</td>
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