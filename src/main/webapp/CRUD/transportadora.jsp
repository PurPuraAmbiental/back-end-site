<%@ page import="java.util.List" %>
<%@ page import="com.purpura.model.Transportadora" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/CRUD/crud.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/CRUD/popUp.css">

    <title>Transportadoras - CRUD</title>
</head>
<body>
<div class="main">
    <div class="header">
        <h1>Lista de Transportadoras</h1>
        <button class="add-btn" onclick="abrirPopupInsertTransportadora()">Adicionar Transportadora</button>
    </div>
    <div class="table-container">
    <table>
        <thead>
        <tr>
            <th>Nome da Transportadora</th>
            <th>CNPJ</th>
            <th>Região Atendida</th>
            <th>Email</th>
            <th>Ações</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Transportadora> transportadoras = (List<Transportadora>) request.getAttribute("listaTransportadoras");
            if (transportadoras != null && !transportadoras.isEmpty()) {
                for (Transportadora t : transportadoras) {
        %>
        <tr>
            <td><%= t.getCNmTransporte() %></td>
            <td><%= t.getCCnpj() %></td>
            <td><%= t.getCRegiaoAtendida() %></td>
            <td><%= t.getCEmail() %></td>
            <td class="actions">

                <!-- BOTÃO EDITAR -->
                <button class="add-btn"
                        onclick="UpdateTransportadora('<%= t.getCCnpj() %>',
                                '<%= t.getCNmTransporte() %>',
                                '<%= t.getCEmail() %>',
                                '<%= t.getCRegiaoAtendida() %>')">
                    Editar
                </button>

                <!-- BOTÃO EXCLUIR -->
                <form action="${pageContext.request.contextPath}/transportadora/delete" method="post" style="display:inline;">
                    <input type="hidden" name="cCnpj" value="<%= t.getCCnpj() %>">
                    <input type="submit" class="add-btn" value="Excluir">
                </form>
            </td>
        </tr>
        <% } } else { %>
        <tr><td colspan="5">Nenhuma transportadora encontrada.</td></tr>
        <% } %>
        </tbody>
    </table>
    </div>
</div>

<!-- POPUPS -->
<jsp:include page="/WEB-INF/popUp's/popUp-transportadora.jsp" />

<script>
    function abrirPopupInsertTransportadora() {
        document.getElementById('popup-insert-transportadora').style.display = 'flex';
    }

    function fecharPopup(id) {
        document.getElementById(id).style.display = 'none';
    }

    function UpdateTransportadora(cCnpj, cNmTransportadora, cEmail, cRegiaoAtendida) {
        document.getElementById('upd-cCnpj').value = cCnpj;
        document.getElementById('upd-cNmTransportadora').value = cNmTransportadora;
        document.getElementById('upd-cEmail').value = cEmail;
        document.getElementById('upd-cRegiaoAtendida').value = cRegiaoAtendida;

        document.getElementById('popup-update-transportadora').style.display = 'flex';
    }
</script>

</body>
</html>
