<%--
/**
 * Exibir e gerenciar empresas cadastradas no sistema PurPura.
 *
 * - Exibe lista de empresas
 * - Permite filtro por nome da empresa, CNPJ, email, status e empresa com ou sem resíduo(s)
 * - Suporte a inserção, edição e exclusão via pop-ups
 *
 * Autora: Bruna de Jesus
 */
--%>

<%@ page import="java.util.List" %>
<%@ page import="com.purpura.model.Empresa" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/styles/crud.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/styles/popUp.css?v=3">
    
    <title>Empresas</title>
</head>
<body>

<div class="main">
    <!-- ================== CABEÇALHO ================== -->
    <div class="header">
        <h1>Lista de Empresas</h1>
        <div class="botoes-principais">
            <!-- Botão para abrir o pop-up de filtragem de transportadora -->
            <button class="add-btn" onclick="filtroEmpresa()">Filtros </button>
            <!-- Botão para abrir o pop-up de inserção de nova transportadora -->
            <button class="add-btn" onclick="abrirPopupInsertEmpresa()">Cadastrar Empresa</button>
        </div>
        <br>
    </div>
    <br>
    <!-- ================== MENSAGEM DE ERRO ================== -->
    <%-- Verifica erro, se há, exibe-o --%>
    <% String erro = (String) request.getAttribute("erro");
        if (erro != null){ %>
    <h5> <%= erro%> </h5>
    <% }%>
    <br>
    <!-- ================== TABELA DE EMPRESAS ================== -->
    <div class="table-container">
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
                // Recupera a lista de transportadoras enviada pelo servlet
                List<Empresa> empresas = (List<Empresa>) request.getAttribute("listaEmpresas");
                // Verifica se a lista não está vazia
                if (empresas != null && !empresas.isEmpty()) {
                    // Percorre cada transportadora da lista e gera uma linha na tabela
                    for (Empresa empresa : empresas) {
            %>
            <tr>
                <!-- Mostra o nome da empresa -->
                <td><%= empresa.getCNmEmpresa() %></td>
                <!-- Mostra o e-mail da empresa -->
                <td><%= empresa.getCEmail() %></td>
                <!-- Mostra a senha criptografada da empresa -->
                <td class="senha"><%= empresa.getCSenha() %></td>
                <!-- Mostra o CNPJ -->
                <td><%= empresa.getCCnpj() %></td>
                <!-- Mostra o status da empresa -->
                <td><%= empresa.getCAtivo() == '1' ? "Ativo" : "Inativo" %></td>
                <!-- Coluna de ações (editar e excluir) -->
                <td class="actions">
                    <!-- ================== BOTÃO EDITAR ================== -->
                    <!-- Ao clicar, preenche o pop-up de atualização com os dados da empresa -->
<%--                    <button class="add-btn"--%>
<%--                            onclick="UpdateEmpresa('<%= empresa.getCNmEmpresa() %>',--%>
<%--                                    '<%= empresa.getCEmail() %>',--%>
<%--                                    '<%= empresa.getCSenha() %>',--%>
<%--                                    '<%= empresa.getCCnpj() %>',--%>
<%--                                    '<%= empresa.getCAtivo() %>')">--%>
<%--                        Editar--%>
<%--                    </button>--%>
                    <form action="<%= request.getContextPath()%>/empresa/update" method="post">
                        <input type="hidden" name="cCnpj" value="<%= empresa.getCCnpj() %>">
                        <input type="hidden" name="method" value="1">
                        <button class="add-btn" TYPE="submit" >editar</button>
                    </form>

                    <!-- ================== BOTÃO EXCLUIR ================== -->
                    <!-- Formulário para deletar uma empresa -->
                    <form action="${pageContext.request.contextPath}/empresa/delete" method="post" style="display:inline;">
                        <input type="hidden" name="cCnpj" value="<%= empresa.getCCnpj() %>">
                        <input class="add-btn" type="submit" value="Excluir">
                    </form>
                </td>
            </tr>
            <%  } // fim do for
             } else { //caso não existam empresas cadastradas %>
            <tr><td colspan="6">Nenhuma empresa encontrada.</td></tr>
            <% } %>
            </tbody>
        </table>
    </div>
</div>
<% if (request.getAttribute("popup-alterar") != null) { %>
<div class="popup">
    <form method="post">
        <label for="a">AAA</label>
        <input id="a" name="cNmEmpresa" type="text" value="${empresa.CNmEmpresa}">

        <label for="b">BBB</label>
        <input id="b" name="cEmail" type="text" value="${empresa.CEmail}">

        <label for="c">CCC</label>
        <input id="c" name="cSenha" type="text" value="${empresa.CSenha}">

        <label for="d">DDD</label>
        <input id="d" name="cCnpj" type="text" value="${empresa.CCnpj}">

        <label for="e">EEE</label>
        <input id="e" name="cAtivo" type="text" value="${empresa.CAtivo}">

        <button type="submit">Editar</button>
    </form>
</div>

<% } %>
<!-- ================== POPUPS ================== -->
<!-- Inclui os formulários de inserção e atualização e filtro -->
<jsp:include page="/WEB-INF/popUp/popUp-empresa.jsp" />

<script>
    // ================== FUNÇÃO PARA ABRIR POPUP DE INSERÇÃO ==================
    function abrirPopupInsertEmpresa() {
        document.getElementById('popup-insert-empresa').style.display = 'flex';
    }

    // ================== FUNÇÃO PARA FECHAR POPUPS (generico)==================
    function fecharPopup(id) {
        document.getElementById(id).style.display = 'none';
    }

    // ================== FUNÇÃO PARA ABRIR POPUP DE ATUALIZAÇÃO ==================
    function UpdateEmpresa(cNmEmpresa, cEmail, cSenha, cCnpj, cAtivo) {
        document.getElementById('update-empresa-cNmEmpresa').value = cNmEmpresa;
        document.getElementById('update-empresa-cEmail').value = cEmail;
        document.getElementById('update-empresa-cSenha').value = "";
        document.getElementById('update-empresa-cCnpj').value = cCnpj;
        document.getElementById('update-empresa-cAtivo').value = cAtivo;
        // Exibe o pop-up de atualização
        document.getElementById('popup-update-empresa').style.display = 'flex';
    }

    //==================== FUNÇÃO PARA ABRIR O POP UP DOS FILTROS =======================
    function filtroEmpresa(){
        document.getElementById('filtroEmpresa').style.display = 'flex';
    }
</script>

</body>
</html>
