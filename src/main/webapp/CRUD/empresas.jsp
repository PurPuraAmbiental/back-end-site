<%@ page import="com.purpura.model.Empresa" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<% boolean mostrarPopUp = false;%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/CRUD/crud.css">
    <title>empresas-crud</title>
</head>
<style>
    body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
    }

    .popup-overlay {
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background: rgba(0, 0, 0, 0.4);
        display: none;
        justify-content: center;
        align-items: center;
        z-index: 999;
    }

    .popup {
        position: relative;
        background: #ffffff;
        padding: 30px 40px;
        border-radius: 10px;
        width: 400px;
        box-shadow: 0 10px 30px rgba(0, 0, 0, 0.2);
        border-top: 5px solid #9c27b0; /* destaque roxo */
    }

    .popup h2 {
        margin-top: 0;
        font-size: 22px;
        color: #333;
    }

    .popup label {
        display: block;
        text-align: left;
        margin: 10px 0 4px;
        font-weight: bold;
        color: #444;
    }

    .popup input,
    .popup select {
        width: 100%;
        padding: 8px 10px;
        margin-bottom: 12px;
        border: 1px solid #ccc;
        border-radius: 6px;
        font-size: 14px;
    }

    .popup button[type="submit"] {
        background-color: #9c27b0;
        color: white;
        border: none;
        padding: 10px 20px;
        border-radius: 6px;
        cursor: pointer;
        font-weight: bold;
    }

    .popup button[type="submit"]:hover {
        background-color: #9c27b0;
    }

    /* Botão X de fechar */
    .close-btn {
        position: absolute;
        top: 12px;
        right: 15px;
        background: none;
        border: none;
        font-size: 20px;
        font-weight: bold;
        color: #999;
        cursor: pointer;
    }

    .close-btn:hover {
        color: #333;
    }
</style>

<body>
<div class="main">
    <div class="header">
        <h1>Lista de Empresas</h1>
        <button class="add-btn" onclick="mostrarPopup()">Adicionar Empresa</button>
    </div>
    <div class="popup-overlay" id="popup">
        <div class="popup">
            <button class="close-btn" onclick="fecharPopup()">×</button>
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
                <img src="c:\Users\irisrodrigues-ieg\Downloads\pen 1.png" alt="Editar">
                <form action="${pageContext.request.contextPath}/empresa/delete" method="post">
                 <input type="hidden" name="ccnpj" value="<%=empresa.getCCnpj()%>" >
                 <input type="submit" value="Delete">
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