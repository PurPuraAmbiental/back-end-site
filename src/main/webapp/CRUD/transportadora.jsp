<%@ page import="java.util.List" %>
<%@ page import="com.purpura.model.Transportadora" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/CRUD/crud.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/CRUD/popUp.css">

    <title>transportadora-crud</title>
</head>
<body>
<div class="main">
    <div class="header">
        <h1>Lista de transportadoras</h1>
        <button class="add-btn" onclick="mostrarPopup()">Adicionar Transportadora</button>
        <div class="popup-overlay" id="popup">
            <div class="popup">
                <button class="close-btn" onclick="fecharPopup()">×</button>
                <% String erro = (String) request.getAttribute("erro");%>
                <h2>Cadastrar Empresa</h2>
                <form action="${pageContext.request.contextPath}/transportadora/insert" method="post">
                    <label for="cNmTransportadora">Nome da Transportadora</label>
                    <input type="text" name="cNmTransportadora" id="cNmTransportadora">

                    <label for="cCnpj">CNPJ</label>
                    <input type="text" name="cCnpj" id="cCnpj">

                    <label for="cEmail">Email</label>
                    <input type="text" name="cEmail" id="cEmail">

                    <label for="cRegiaoAtendida">Regiao de atendimento</label>
                    <input type="text" name="cRegiaoAtendida" id="cRegiaoAtendida">

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
        </script>    </div>

    <table>
        <thead>
        <tr>
            <th>Nome da transportadora</th>
            <th>CNPJ</th>
            <th>Região atendida</th>
            <th>Email</th>
            <th>Ações</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Transportadora> transportadoras = (List<Transportadora>) request.getAttribute("listaTransportadoras");
            if (transportadoras != null && !transportadoras.isEmpty()) {
                for (Transportadora transportadora : transportadoras) {
        %>
        <tr>
            <td><%= transportadora.getCNmTransporte() %></td>
            <td><%= transportadora.getCCnpj() %></td>
            <td><%= transportadora.getCRegiaoAtendida() %></td>
            <td><%= transportadora.getCEmail() %></td>

            <td class="actions" >
                <button class="btn-pequeno" onclick="mostrarPopupUpdate('<%= transportadora.getCCnpj() %>')">Modificar Transportadora</button>
                <div class="popup-overlay" id="popup-update-<%= transportadora.getCCnpj() %>" style="display:none;">
                    <div class="popup">
                        <button class="close-btn" onclick="fecharPopupUpdate('<%= transportadora.getCCnpj() %>')">×</button>
                        <h2>Atualizar Transportadora</h2>
                        <form action="${pageContext.request.contextPath}/transportadora/update" method="post">
                            <label for="cNmTransporte">Nome da Transportadora</label>
                            <input type="text" name="cNmTransporte" id="cNmTransporte"
                                   value="<%= transportadora.getCNmTransporte() %>">

                            <label for="cCnpj">CNPJ</label>
                            <input type="text" id="cCnpj" value="<%= transportadora.getCCnpj() %>" readonly>
                            <input type="hidden" name="cCnpj" value="<%= transportadora.getCCnpj() %>">

                            <label for="cEmail">Email</label>
                            <input type="text" name="cEmail" id="cEmail"
                                   value="<%= transportadora.getCEmail() %>">

                            <label for="cRegiaoAtendida">Região de Atendimento</label>
                            <input type="text" name="cRegiaoAtendida" id="cRegiaoAtendida"
                                   value="<%= transportadora.getCRegiaoAtendida() %>">

                            <button type="submit">Atualizar</button>
                        </form>


                    </div>
                </div>

                <script>
                    function mostrarPopupUpdate(id) {
                        document.getElementById('popup-update-' + id).style.display = 'flex';
                    }

                    function fecharPopupUpdate(id) {
                        document.getElementById('popup-update-' + id).style.display = 'none';
                    }
                </script>
                <form action="${pageContext.request.contextPath}/transportadora/delete" method="post">
                    <input type="hidden" name="nCdTransporte" value="<%=transportadora.getCCnpj()%>">
                    <input type="submit" value="Delete">
                </form>
            </td>
        </tr>

        <%
            }
        } else {
        %>
        <tr>
            <td colspan="6">Nenhuma transportadora encontrada.</td>
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