<%@ page import="com.purpura.dto.ResiduoView" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/CRUD/crud.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/CRUD/popUp.css">

    <title>residuos-crud</title>
</head>
<body>
<div class="main">
    <div class="header">
        <h1>Lista de Resíduos</h1>
        <button class="add-btn" onclick="mostrarPopup()">Adicionar Residuo</button>
        <div class="popup-overlay" id="popup">
            <div class="popup">
                <button class="close-btn" onclick="fecharPopup()">×</button>
                <% String erro = (String) request.getAttribute("erro");%>
                <h2>Cadastrar Empresa</h2>
                <form action="${pageContext.request.contextPath}/residuo/insert" method="post">
                    <label for="cNmResiduo">Tipo do residuo</label>
                    <input type="text" name="cNmResiduo" id="cNmResiduo">

                    <label for="cTipoUnidade">Unidade de medida</label>
                    <select name="cTipoUnidade" id="cTipoUnidade">
                        <option value="kg">Kilogrmas (kg)</option>
                        <option value="t">Tonelada (t)</option>
                        <option value="kg/m³">Quilograma por metro cubico (kg/m³)</option>
                        <option value="g">gramas (g)</option>
                    </select>

                    <label for="nPrecoPadrao">Preço</label>
                    <input type="text" name="nPrecoPadrao" id="nPrecoPadrao">

                    <label for="nVolumePadrao">Volume</label>
                    <input type="text" name="nVolumePadrao" id="nVolumePadrao">

                    <label for="cCategoria">Categoria</label>
                    <input type="text" name="cCategoria" id="cCategoria">

                    <label for="cNmEmpresa">Empresa</label>
                    <input type="text" name="cNmEmpresa" id="cNmEmpresa">

                    <label for="cDescricao">Descrição</label>
                    <input type="text" name="cDescricao" id="cDescricao">

                    <% if (erro != null) { %>
                    <p style="color:red;"><%= erro %></p>
                    <% } %>
                    <button type="submit">Adicionar</button>
                </form>
            </div>
        </div>

        <script>
            function mostrarPopup() {
                document.getElementById('popup').style.display = 'flex';
            }

            function fecharPopup() {
                document.getElementById('popup').style.display = 'none';
            }
        </script>
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
            <td><%= residuo.nCdResiduo()%></td>

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