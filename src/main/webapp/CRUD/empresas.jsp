<%@ page import="com.purpura.model.Empresa" %>
<%@ page import="java.util.List" %>
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
        </tr>
        </thead>
        <tbody>
        <%
            List<Empresa> empresa = (List<Empresa>) request.getAttribute("listaEmpresas");
            if (empresa != null) {
                for (Empresa empresa1 : empresa) {
        %>
        <tr>
            <td> <%= empresa1.getCNmEmpresa() %></td>
            <td><%= empresa1.getCEmail()%></td>
            <td>
                <span class="hidden-password"><td><%= empresa1.getCSenha()%></td></span>
                <img src="c:\Users\irisrodrigues-ieg\Downloads\Vector (6).png" alt="Mostrar" class="toggle-password">
            </td>
            <td><%= empresa1.getCCnpj()%></td>

            <td class="actions">
                <img src="c:\Users\irisrodrigues-ieg\Downloads\pen 1.png" alt="Editar">
                <img src="c:\Users\irisrodrigues-ieg\Downloads\trash 1.png" alt="Excluir">
            </td>
        </tr>
        </tbody>
        <%
            }
        } else {
        %>
        <tr><td colspan="3">Nenhum administrador encontrado.</td></tr>
        <%
            }
        %>
    </table>
</div>

<script src="script.js"></script>

</body>
</html>