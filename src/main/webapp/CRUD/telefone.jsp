<%@ page import="java.util.List" %>
<%@ page import="com.purpura.dto.TelefoneView" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/CRUD/crud.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/CRUD/popUp.css">
    <title>Telefones - CRUD</title>
</head>
<body>
<div class="main">
    <div class="header">
        <h1>Lista de Telefones</h1>
        <button class="add-btn" onclick="abrirPopupInsertTelefone()">Adicionar Telefone</button>
    </div>
    <div class="table-container">
    <table>
        <thead>
        <tr>
            <th>Telefone</th>
            <th>Empresa</th>
            <th>Descrição</th>
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
            <td><%= telefone.cDescricao() %></td>

            <td class="actions">
                <!-- BOTÃO EDITAR -->
                <button class="add-btn"
                        onclick="UpdateTelefone('<%= telefone.nCdTelefone() %>',
                                '<%= telefone.cNmEmpresa() %>',
                                '<%= telefone.cNrTelefone() %>',
                                '<%= telefone.cDescricao() %>')">
                    Editar
                </button>

                <!-- BOTÃO EXCLUIR -->
                <form action="${pageContext.request.contextPath}/telefone/delete" method="post" style="display:inline;">
                    <input type="hidden" name="nCdTelefone" value="<%= telefone.nCdTelefone() %>">
                    <input type="submit" class="add-btn" value="Excluir">
                </form>
            </td>
        </tr>
        <% } } else { %>
        <tr><td colspan="4">Nenhum telefone encontrado.</td></tr>
        <% } %>
        </tbody>
    </table>
        </div>
</div>

<!-- POPUPS -->
<jsp:include page="/WEB-INF/popUp's/popUp-telefone.jsp" />

<script>
    function abrirPopupInsertTelefone() {
        document.getElementById('popup-insert-telefone').style.display = 'flex';
    }

    function fecharPopup(id) {
        document.getElementById(id).style.display = 'none';
    }

    // FUNÇÃO UPDATE (com nomes iguais aos atributos)
    function UpdateTelefone(nCdTelefone, cNmEmpresa, cNrTelefone, cDescricao) {
        document.getElementById('nCdTelefone').value = nCdTelefone;
        document.getElementById('cNmEmpresa').value = cNmEmpresa;
        document.getElementById('cNrTelefone').value = cNrTelefone;
        document.getElementById('cDescricao').value = cDescricao;

        document.getElementById('popup-update-telefone').style.display = 'flex';
    }
</script>
</body>
</html>
