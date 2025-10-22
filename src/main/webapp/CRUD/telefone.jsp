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

    <title>telefone-crud</title>
</head>
<body>
<div class="main">
    <div class="header">
        <h1>Lista de Telefones</h1>
        <button class="add-btn" onclick="mostrarPopup()">Adicionar Telefone</button>
        <div class="popup-overlay" id="popup">
            <div class="popup">
                <button class="close-btn" onclick="fecharPopup()">×</button>
                <% String erro = (String) request.getAttribute("erro");%>
                <h2>Cadastrar Telefone</h2>
                <form action="${pageContext.request.contextPath}/telefone/insert" method="post">
                    <label for="cNmEmpresa">Empresa</label>
                    <input type="text" name="cNmEmpresa" id="cNmEmpresa">

                    <label for="cNrTelefone">Telefone</label>
                    <input type="text" name="cNrTelefone" id="cNrTelefone">

                    <label for="cDescricao">Descricao</label>
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
            <th>Número de telefone</th>
            <th>Nome da Empresa</th>
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

            <td class="actions">
                <button class="btn-pequeno" onclick="mostrarPopupUpdate('<%= telefone.nCdTelefone() %>')">Modificar Empresa</button>

                <div class="popup-overlay" id="popup-update-<%= telefone.nCdTelefone() %>" style="display:none;">
                    <div class="popup">
                        <button class="close-btn" onclick="fecharPopupUpdate('<%= telefone.nCdTelefone() %>')">×</button>
                        <h2>Atualizar Endereco </h2>

                        <form action="${pageContext.request.contextPath}/telefone/update" method="post">
                            <label for="cNmEmpresa">Empresa</label>
                            <input type="text" name="cNmEmpresa" id="cNmEmpresa" value="<%= telefone.cNmEmpresa()%>">

                            <label for="cNrTelefone">Telefone</label>
                            <input type="text" name="cNrTelefone" id="cNrTelefone" value="<%= telefone.cNrTelefone()%>">

                           <%-- <label for="cDescricao">Descricao</label>
                            <input type="text" name="cDescricao" id="cDescricao" value="">
                            //FALAR COM O KEVIN --%>

                            <% if (erro != null) { %>
                            <p style="color:red;"><%= erro %></p>
                            <% } %>
                            <button type="submit">Atualizar</button>
                        </form>
                    </div>
                </div>

                <script>
                    function mostrarPopupUpdate(id) {
                        document.getElementById('popup-update-' + id).style.display = 'flex';
                    }

                    function fecharPopupUpdate(od) {
                        document.getElementById('popup-update-' + id).style.display = 'none';
                    }
                </script>


                <form action="${pageContext.request.contextPath}/telefone/delete" method="post">
                    <input type="hidden" name="nCdTelefone" value="<%=telefone.nCdTelefone()%>" >
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