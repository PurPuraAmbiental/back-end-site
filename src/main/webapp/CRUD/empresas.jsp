<%@ page import="com.purpura.model.Empresa" %>
<%@ page import="java.util.List" %>
<%@ page import="com.purpura.common.Regex" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<% boolean mostrarPopUp = false;%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/CRUD/crud.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/CRUD/popUp.css">

    <title>empresas-crud</title>
</head>
<body>
<div class="main">
    <div class="header">
        <h1>Lista de Empresas</h1>
        <button class="add-btn" onclick="mostrarPopup()">Adicionar Empresa</button>
        <div class="popup-overlay" id="popup">
            <div class="popup">
                <button class="close-btn" onclick="fecharPopup()">×</button>
                <% String erro = (String) request.getAttribute("erro");%>
                <h2>Cadastrar Empresa</h2>
                <form action="${pageContext.request.contextPath}/empresa/insert" method="post">
                    <label for="cNmEmpresa">Nome da empresa</label>
                    <input type="text" name="cNmEmpresa" id="cNmEmpresa">

                    <label for="cEmail">Email</label>
                    <input type="text" name="cEmail" id="cEmail">

                    <label for="cSenha">Senha</label>
                    <input type="text" name="cSenha" id="cSenha">

                    <label for="cCnpj">CNPJ</label>
                    <input type="text" name="cCnpj" id="cCnpj">

                    <label for="cAtivo">Ativo</label>
                    <select name="cAtivo" id="cAtivo">
                        <option value="1">Ativo</option>
                        <option value="0">Não Ativo</option>
                    </select>
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
            <td>
                <span class="hidden-password"><%= empresa.getCSenha() %></span>
            </td>
            <td><%= empresa.getCCnpj() %></td>
            <% if (empresa.getCAtivo() == '1') {%>
            <td> Ativo </td>
            <%}else{%>
            <td> Inativo </td> <%}%>

            <td class="actions">
                    <!-- POPUP DE UPDATE -->
                <button class="btn-pequeno" onclick="mostrarPopupUpdate('<%= empresa.getCCnpj() %>')">Modificar Empresa</button>

                <div class="popup-overlay" id="popup-update-<%= empresa.getCCnpj() %>" style="display:none;">
                    <div class="popup">
                        <button class="close-btn" onclick="fecharPopupUpdate('<%= empresa.getCCnpj() %>')">×</button>
                        <h2>Atualizar Empresa</h2>

                        <form action="${pageContext.request.contextPath}/empresa/update" method="post">
                            <label for="cNmEmpresa">Nome da empresa</label>
                            <input type="text" name="cNmEmpresa" value="<%= empresa.getCNmEmpresa() %>">

                            <label for="cEmail">Email</label>
                            <input type="text" name="cEmail" value="<%= empresa.getCEmail() %>">

                            <label for="cSenha">Senha</label>
                            <input type="text" name="cSenha" value="<%= empresa.getCSenha() %>">

                            <label for="cCnpj">CNPJ</label>
                            <input type="text" name="cCnpj" value="<%= empresa.getCCnpj() %>" readonly>

                            <label for="cAtivo">Ativo</label>
                            <select name="cAtivo">
                                <option value="1" <% if (empresa.getCAtivo() == '1') { %> selected <% } %>>Ativo</option>
                                <option value="0" <% if (empresa.getCAtivo() == '0') { %> selected <% } %>>Não Ativo</option>
                            </select>

                            <button type="submit">Atualizar</button>
                        </form>
                    </div>
                </div>

                <script>
                    function mostrarPopupUpdate(ccnpj) {
                        document.getElementById('popup-update-' + cnpj).style.display = 'flex';
                    }

                    function fecharPopupUpdate(ccnpj) {
                        document.getElementById('popup-update-' + cnpj).style.display = 'none';
                    }
                </script>



                <form action="${pageContext.request.contextPath}/empresa/delete" method="post">
                 <input type="hidden" name="ccnpj" value="<%=empresa.getCCnpj()%>" >
              <%--   <button class="btn-pequeno" onclick="mostrarPopupUpdate('<%= empresa.getCCnpj() %>')">Deletar Empresa</button>--%>
                 <input class="btn-pequeno" type="submit" value="Deletar">
                </form>
            </td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="6">Nenhuma empresa encontrada.</td>
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