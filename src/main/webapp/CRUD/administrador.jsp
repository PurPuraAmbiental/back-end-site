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
    <!-- ================== CABEÇALHO ================== -->
    <div class="header">
        <h1>Lista de Administradores</h1>
        <div class="botoes-principais">
        <!-- Botão para abrir o pop-up de filtragem de administrador -->
        <button class="add-btn" onclick="filtroAdministrador()"> Filtros </button>
        <!-- Botão para abrir o pop-up de inserção de novo administrador -->
        <button class="add-btn" onclick="abrirPopupInsertAdministrador()">Cadastrar Administrador</button>
        </div>
    </div>

    <br>
    <br>
    <!-- ================== MENSAGEM DE ERRO ================== -->
    <%-- Verifica erro, se há, exibe-o --%>    <% String erro = (String) request.getAttribute("erro");
        if (erro != null){ %>
    <h5> <%= erro%> </h5>
    <% }%>
    <br>
    <!-- ================== TABELA DE ADMINISTRADORES ================== -->
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
            // Recupera a lista de transportadoras enviada pelo servlet
            List<Administrador> administradores = (List<Administrador>) request.getAttribute("listaAdministradores");
            // Verifica se a lista não está vazia
            if (administradores != null && !administradores.isEmpty()) {
                // Percorre cada transportadora da lista e gera uma linha na tabela
                for (Administrador administrador : administradores) {
        %>
        <tr>
            <td><%= administrador.getCNmAdministrador() %></td>
            <td><%= administrador.getCEmail() %></td>
            <td class="senha"><%= administrador.getCSenha() %></td>
            <td class="actions">
                <!-- ================== BOTÃO EDITAR ================== -->
                <!-- Ao clicar, preenche o pop-up de atualização com os dados da empresa -->                 <button class="add-btn"
                        onclick="UpdateAdministrador('<%= administrador.getCNmAdministrador() %>', '<%= administrador.getCEmail() %>', '<%= administrador.getCSenha() %>')">
                    Editar
                </button>

                <!-- ================== BOTÃO EXCLUIR ================== -->
                <!-- Formulário para deletar uma empresa -->
                <form action="<%=request.getContextPath()%>/administrador/delete" method="post" style="display:inline;">
                    <input type="hidden" name="cEmail" value="<%= administrador.getCEmail() %>">
                    <input type="submit" class="add-btn" value="Excluir">
                </form>
            </td>
        </tr>
        <%
            } // fim do for
        } else { //caso não existam administradores cadastradas
        %>
        <tr><td colspan="4">Nenhum administrador encontrado.</td></tr>
        <%
            }
        %>
        </tbody>
    </table>
        </div>
</div>

<!-- ================== POPUPS ================== -->
<!-- Inclui os formulários de inserção, atualização e filtro -->
<jsp:include page="/WEB-INF/popUp's/popUp-administracao.jsp" />

<script>
    // ================== FUNÇÃO PARA ABRIR POPUP DE INSERÇÃO ==================
    function abrirPopupInsertAdministrador() {
        document.getElementById('popup-insert-administrador').style.display = 'flex';
    }

    // ================== FUNÇÃO PARA FECHAR POPUPS (Generico)==================
    function fecharPopup(id) {
        document.getElementById(id).style.display = 'none';
    }

    // ================== FUNÇÃO PARA ABRIR POPUP DE ATUALIZAÇÃO ==================
    function UpdateAdministrador(cNmAministrador, cEmail, cSenha) {
        document.getElementById('update-administrador-cNmAdministrador').value = cNmAministrador;
        document.getElementById('update-administrador-cEmail').value = cEmail;
        document.getElementById('update-administrador-cSenha').value = cSenha;
        document.getElementById('popup-update-administrador').style.display = 'flex';
    }

    //==================== FUNÇÃO PARA ABRIR O POP UP DOS FILTROS =======================
    function filtroAdministrador(){
        document.getElementById('filtroAdministrador').style.display = 'flex';
    }
</script>
</body>
</html>