<%--
/**
 * Exibir e gerenciar administradores cadastrados no sistema PurPura.
 *
 * - Exibe lista de administradores
 * - Permite filtro por nome
 * - Suporte a inserção, edição e exclusão via pop-ups
 *
 * Autora: Bruna de Jesus
 * Autor: Kevin de Oliveira
 */
--%>

<%@ page import="java.util.List" %>
<%@ page import="com.purpura.model.Administrador" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/CRUD/crud.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/CRUD/popUp.css">
    <title>Administrador</title>
</head>
<body>
<div class="main">
    <div class="header">
        <h1>Lista de Administradores</h1>
        <button class="add-btn" onclick="abrirPopupInsertAdministrador()">Adicionar Administrador</button>
    </div>
    <form action="<%=request.getContextPath()%>/administrador/list" method="get">
        <label for="nomeAdministrador">Insira o nome do Administrador</label>
        <input type="text" name="nomeAdministrador" id="nomeAdministrador" placeholder="Nome do Administrador"
               value="<%= request.getParameter("nomeAdministrador") != null ? request.getParameter("nomeAdministrador") : "" %>">
        <br>
        <button type="submit" class="add-btn">Filtrar</button>
    </form>
    <br>
    <br>
    <%-- Verifica erro, se há, exibe-o --%>
    <% String erro = (String) request.getAttribute("erro");
        if (erro != null){ %>
    <h5> <%= erro%> </h5>
    <% }%>
    <br>
    <div class="table-container">
    <table>
        <thead>
        <tr>
            <th>Nome do Administrador</th>
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
            <td class="senha"><%= administrador.getCSenha() %></td>
            <td class="actions">
                <!-- Botão EDITAR -->
                <button class="add-btn"
                        onclick="UpdateAdministrador('<%= administrador.getCNmAdministrador() %>', '<%= administrador.getCEmail() %>', '<%= administrador.getCSenha() %>')">
                    Editar
                </button>

                <!-- Botão EXCLUIR -->
                <form action="<%=request.getContextPath()%>/administrador/delete" method="post" style="display:inline;">
                    <input type="hidden" name="cEmail" value="<%= administrador.getCEmail() %>">
                    <input type="submit" class="add-btn" value="Excluir">
                </form>
            </td>
        </tr>
        <%
            }
        } else {
        %>
        <tr><td colspan="4">Nenhum administrador encontrado.</td></tr>
        <%
            }
        %>
        </tbody>
    </table>
        </div>
</div>

<jsp:include page="/WEB-INF/popUp's/popUp-administracao.jsp" />

<script>
    // Abrir pop up de inserção
    function abrirPopupInsertAdministrador() {
        document.getElementById('popup-insert-administrador').style.display = 'flex';
    }

    // fechar pop up (genérico)
    function fecharPopup(id) {
        document.getElementById(id).style.display = 'none';
    }

    // Abrir pop up de update e preencher campos
    function UpdateAdministrador(cNmAministrador, cEmail, cSenha) {
        document.getElementById('update-administrador-cNmAdministrador').value = cNmAministrador;
        document.getElementById('update-administrador-cEmail').value = cEmail;
        document.getElementById('update-administrador-cSenha').value = cSenha;
        document.getElementById('popup-update-administrador').style.display = 'flex';
    }
</script>
</body>
</html>