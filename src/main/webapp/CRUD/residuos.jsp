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
        <div class="botoes-principais">
        <button class="add-btn" onclick="abrirPopupInsertResiduo()">Adicionar Resíduo</button>
        </div>
    </div>
    <div>
        <form action="<%=request.getContextPath()%>/residuo/list" method="get">
            <label for="precoMin">Preço Mínimo</label><br>
            <input type="number" step="0.01" min="0" name="precoMin" id="precoMin"
                   placeholder="Digite o preço mínimo"><br>

            <label for="precoMax">Preço Máximo</label><br>
            <input type="number" step="0.01" min="0" name="precoMax" id="precoMax"
                   placeholder="Digite o preço máximo"><br>

            <label for="volumeMin">Volume Mínimo</label><br>
            <input type="number" step="0.01" min="0" name="volumeMin" id="volumeMin"
                   placeholder="Digite o volume mínimo"><br>

            <label for="volumeMax">Volume Máximo</label><br>
            <input type="number" step="0.01" min="0" name="volumeMax" id="volumeMax"
                   placeholder="Digite o volume máximo"><br>

            <label for="cTipoUnidade">Selecione a unidade de medida</label><br>
            <select name="unidade" id="cTipoUnidade">
                <option value="">-- Todas --</option>
                <option value="kg">Kilograma (kg)</option>
                <option value="t">Tonelada (t)</option>
                <option value="kg/m³">Kg/m³</option>
                <option value="g">Gramas (g)</option>
            </select><br>

            <button type="submit" class="add-btn">Filtrar</button>
        </form>
    </div>


    <br>
    <% String erro = (String) request.getAttribute("erro");
        if (erro != null){ %>
    <h5> <%= erro%> </h5>
    <% }%>
    <br>
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
        document.getElementById('update-residuo-nCdResiduo').value = nCdResiduo;
        document.getElementById('update-residuo-cNmResiduo').value = cNmResiduo;
        document.getElementById('update-residuo-cTipoUnidade').value = cTipoUnidade;
        document.getElementById('update-residuo-nPrecoPadrao').value = nPrecoPadrao;
        document.getElementById('update-residuo-nVolumePadrao').value = nVolumePadrao;
        document.getElementById('update-residuo-cCategoria').value = cCategoria;
        document.getElementById('update-residuo-cNmEmpresa').value = cNmEmpresa;
        document.getElementById('update-residuo-cDescricao').value = cDescricao;

        document.getElementById('popup-update-residuo').style.display = 'flex';
    }
</script>
</body>
</html>
