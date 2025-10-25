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
    <title>Resíduos - CRUD</title>
</head>
<body>
<div class="main">
    <div class="header">
        <h1>Lista de Resíduos</h1>
        <button class="add-btn" onclick="abrirPopupInsertResiduo()">Adicionar Resíduo</button>
    </div>
    <div class="table-container">
    <table>
        <thead>
        <tr>
            <th>Nome</th>
            <th>Unidade</th>
            <th>Preço</th>
            <th>Volume</th>
            <th>Categoria</th>
            <th>Empresa</th>
            <th>Descrição</th>
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
            <td><%= residuo.cDescricao() %></td>

            <td class="actions">
                <!-- BOTÃO EDITAR -->
                <button class="add-btn"
                        onclick="UpdateResiduo('<%= residuo.nCdResiduo() %>',
                                '<%= residuo.cNmResiduo() %>',
                                '<%= residuo.cTipoUnidade() %>',
                                '<%= residuo.nPrecoPadrao() %>',
                                '<%= residuo.nVolumePadrao() %>',
                                '<%= residuo.cCategoria() %>',
                                '<%= residuo.cNmEmpresa() %>',
                                '<%= residuo.cDescricao() %>')">
                    Editar
                </button>

                <!-- BOTÃO DELETAR -->
                <form action="${pageContext.request.contextPath}/residuo/delete" method="post" style="display:inline;">
                    <input type="hidden" name="nCdResiduo" value="<%= residuo.nCdResiduo() %>">
                    <input type="submit" class="add-btn" value="Excluir">
                </form>
            </td>
        </tr>
        <% } } else { %>
        <tr><td colspan="8">Nenhum resíduo encontrado.</td></tr>
        <% } %>
        </tbody>
    </table>
    </div>
</div>

<!-- POPUPS -->
<jsp:include page="/WEB-INF/popUp's/popUp-residuo.jsp" />

<script>
    function abrirPopupInsertResiduo() {
        document.getElementById('popup-insert-residuo').style.display = 'flex';
    }

    function fecharPopup(id) {
        document.getElementById(id).style.display = 'none';
    }

    // FUNÇÃO DE UPDATE (mesmo nome dos atributos)
    function UpdateResiduo(nCdResiduo, cNmResiduo, cTipoUnidade, nPrecoPadrao, nVolumePadrao, cCategoria, cNmEmpresa, cDescricao) {
        document.getElementById('nCdResiduo').value = nCdResiduo;
        document.getElementById('cNmResiduo').value = cNmResiduo;
        document.getElementById('cTipoUnidade').value = cTipoUnidade;
        document.getElementById('nPrecoPadrao').value = nPrecoPadrao;
        document.getElementById('nVolumePadrao').value = nVolumePadrao;
        document.getElementById('cCategoria').value = cCategoria;
        document.getElementById('cNmEmpresa').value = cNmEmpresa;
        document.getElementById('cDescricao').value = cDescricao;

        document.getElementById('popup-update-residuo').style.display = 'flex';
    }
</script>
</body>
</html>
