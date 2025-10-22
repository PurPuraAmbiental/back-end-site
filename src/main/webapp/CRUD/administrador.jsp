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
    <title>administrador-crud</title>
</head>
<body>
<div class="main">
    <div class="header">
        <h1>Lista de Administradores</h1>
        <button class="add-btn" onclick="mostrarPopup()">Adicionar Residuo</button>
        <div class="popup-overlay" id="popup">
            <div class="popup">
                <button class="close-btn" onclick="fecharPopup()">×</button>
                <% String erro = (String) request.getAttribute("erro");%>
                <h2>Cadastrar Empresa</h2>
                <form action="${pageContext.request.contextPath}/administrador/insert" method="post">
                    <label for="cNmAdministrador">Nome do Administrador</label>
                    <input type="text" name="cNmAdministrador" id="cNmAdministrador">

                    <label for="cEmail">Email</label>
                    <input type="text" name="cEmail" id="cEmail">

                    <label for="cSenha">Senha</label>
                    <input type="text" name="cSenha" id="cSenha">

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
            <th>Nome do administrador</th>
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
                    String nome = administrador.getCNmAdministrador();
                    String email = administrador.getCEmail();
                    String senha = administrador.getCSenha();
        %>
        <tr>
            <td><%= administrador.getCNmAdministrador() %></td>
            <td><%= administrador.getCEmail() %></td>
            <td><%= administrador.getCSenha() %></td>

            <td class="actions">
                <button class="btn-pequeno" onclick="mostrarPopupUpdate('<%= administrador.getCEmail() %>')">Modificar Empresa</button>

                <div class="popup-overlay" id="popup-update-<%= administrador.getCEmail() %>" style="display:none;">
                    <div class="popup">
                        <button class="close-btn" onclick="fecharPopupUpdate('<%= administrador.getCEmail() %>')">×</button>
                        <h2>Atualizar Empresa</h2>
                        <form action="${pageContext.request.contextPath}/administrador/update" method="post">
                            <label for="cNmAdministrador">Nome do Administrador</label>
                            <input type="text" name="cNmAdministrador" id="cNmAdministrador"
                                   value="<%= nome %>">

                            <label for="cEmail">Email</label>
                            <input type="hidden" name="cEmail" value="<%= email %>">


                            <label for="cSenha">Senha</label>
                            <input type="text" name="cSenha" id="cSenha"
                                   value="<%= senha %>">

                            <button type="submit">Atualizar</button>
                        </form>


                        </form>
                    </div>
                </div>

                <script>
                    function mostrarPopupUpdate(cEmail) {
                        document.getElementById('popup-update-' + cEmail).style.display = 'flex';
                    }

                    function fecharPopupUpdate(cEmail) {
                        document.getElementById('popup-update-' + cEmail).style.display = 'none';
                    }
                </script>

                <form action="${pageContext.request.contextPath}/administrador/delete" method="post">
                    <input type="hidden" name="cEmail" value="<%=administrador.getCEmail()%>">
                    <input type="submit" value="Delete">
                </form>
            </td>
        </tr>

        <%
            }
        } else {
        %>
        <tr>
            <td colspan="6">Nenhum administrador encontrado.</td>
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