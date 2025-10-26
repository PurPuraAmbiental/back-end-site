<%@ page import="java.util.List" %>
<%@ page import="com.purpura.model.Empresa" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/CRUD/crud.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/CRUD/popUp.css?v=3">
    <title>Empresas - CRUD</title>
</head>
<body>

<div class="main">
    <div class="header">
        <h1>Lista de Empresas</h1>
        <div class="botoes-principais">
        <form action="">
            <select name="filtro">
                <option value="">Todas</option>
                <option value="">Apenas Empresas ativas</option>
                <option value="">Apenas Empresas Inativas</option>
                <option value="">Apenas empresas com residuos</option>
                <option value="">Apenas empresas sem residuos</option>
            </select>
            <button type="submit" class="add-btn">Filtrar</button>
        </form>
        <button class="add-btn" onclick="abrirPopupInsertEmpresa()">Adicionar Empresa</button>
        </div>
        <form action="">
        <label for="">Nome</label>
        <input type="text" placeholder="Digite o Nome da empresa">
        <label for="">CNPJ</label>
        <input type="text" placeholder="Digite o cnpj da empresa">
        <label for="">EMAIL</label>
        <input type="text" placeholder="Digite o email da empresa">
            <button type="submit" class="add-btn"> Pesquisar </button>
        </form>
        <br>
        <% String erro = (String) request.getAttribute("erro");
            if (erro != null){ %>
        <h5> <%= erro%> </h5>
        <% }%>
        <br>
    </div>

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
                List<Empresa> empresas = (List<Empresa>) request.getAttribute("listaEmpresas");
                if (empresas != null && !empresas.isEmpty()) {
                    for (Empresa empresa : empresas) {
            %>
            <tr>
                <td><%= empresa.getCNmEmpresa() %></td>
                <td><%= empresa.getCEmail() %></td>
                <td class="senha"><%= empresa.getCSenha() %></td>
                <td><%= empresa.getCCnpj() %></td>
                <td><%= empresa.getCAtivo() == '1' ? "Ativo" : "Inativo" %></td>

                <td class="actions">
                    <!-- BOTÃO EDITAR -->
                    <button class="add-btn"
                            onclick="UpdateEmpresa('<%= empresa.getCNmEmpresa() %>', '<%= empresa.getCEmail() %>', '<%= empresa.getCSenha() %>', '<%= empresa.getCCnpj() %>', '<%= empresa.getCAtivo() %>')">
                        Editar
                    </button>

                    <!-- BOTÃO EXCLUIR -->
                    <form action="${pageContext.request.contextPath}/empresa/delete" method="post" style="display:inline;">
                        <input type="hidden" name="cCnpj" value="<%= empresa.getCCnpj() %>">
                        <input class="add-btn" type="submit" value="Excluir">
                    </form>
                </td>
            </tr>
            <%  } } else { %>
            <tr><td colspan="6">Nenhuma empresa encontrada.</td></tr>
            <% } %>
            </tbody>
        </table>
    </div>
</div>

<!-- POPUPS -->
<jsp:include page="/WEB-INF/popUp's/popUp-empresa.jsp" />

<script>
    // ABRIR POPUP DE INSERÇÃO
    function abrirPopupInsertEmpresa() {
        document.getElementById('popup-insert-empresa').style.display = 'flex';
    }

    // FECHAR POPUP
    function fecharPopup(id) {
        document.getElementById(id).style.display = 'none';
    }

    // FUNÇÃO DE UPDATE
    function UpdateEmpresa(cNmEmpresa, cEmail, cSenha, cCnpj, cAtivo) {
        document.getElementById('update-empresa-cNmEmpresa').value = cNmEmpresa;
        document.getElementById('update-empresa-cEmail').value = cEmail;
        document.getElementById('update-empresa-cSenha').value = cSenha;
        document.getElementById('update-empresa-cCnpj').value = cCnpj;
        document.getElementById('update-empresa-cAtivo').value = cAtivo;
        document.getElementById('popup-update-empresa').style.display = 'flex';
    }
</script>

</body>
</html>
