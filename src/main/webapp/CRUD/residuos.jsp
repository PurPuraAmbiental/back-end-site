<%@ page import="com.purpura.dto.ResiduoView" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/CRUD/crud.css">
    <title>residuos-crud</title>
</head>
<body>
<div class="main">
    <div class="header">
        <h1>Lista de Resíduos</h1>
        <button class="add-btn">Adicionar Resíduo</button>
    </div>

    <table>
        <thead>
        <tr>
            <th>Nome</th>
            <th>Unidade de Medida</th>
            <th>Preço</th>
            <th>Volume</th>
            <th>Categoria</th>
            <th>Nome da Empresa</th>
            <th>Ações</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<ResiduoView> residuos = (List<ResiduoView>) request.getAttribute("listaResiduos");
            if (residuos != null && !residuos.isEmpty()) {
                for (ResiduoView residuo : residuos) {
        %>
        <tr>
            <td><%= residuo.cNmResiduo() %></td>
            <td><%= residuo.cTipoUnidade() %></td>
            <td><%= residuo.nPrecoPadrao() %></td>
            <td><%= residuo.nVolumePadrao() %></td>
            <td><%= residuo.cCategoria() %></td>
            <td><%= residuo.cNmEmpresa() %></td>

            <td class="actions">
                <img src="c:\Users\irisrodrigues-ieg\Downloads\pen 1.png" alt="Editar">
                <form action="${pageContext.request.contextPath}/residuo/delete" method="post">
                    <input type="hidden" name="nCdResiduo" value="<%=residuo.nCdResiduo()%>" >
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