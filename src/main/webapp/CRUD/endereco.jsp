<%@ page import="com.purpura.dto.EnderecoEmpresaView" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/CRUD/crud.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/CRUD/popUp.css?v=2">

    <title>endereço-crud</title>
</head>
<body>
<div class="main">
    <div class="header">
        <h1>Lista de Empresas</h1>
        <button class="add-btn" onclick="mostrarPopup()">Cadastrar Endereco</button>
        <div class="popup-overlay" id="popup">
            <div class="popup">
                <button class="close-btn" onclick="fecharPopup()">×</button>
                <% String erro = (String) request.getAttribute("erro");%>
                <h2>Cadastrar Endereço</h2>
                <form action="${pageContext.request.contextPath}/endereco-empresa/insert" method="post">
                    <label for="cBairro">Bairro</label>
                    <input type="text" name="cBairro" id="cBairro">

                    <label for="cLogradouro">Logradouro</label>
                    <input type="text" name="cLogradouro" id="cLogradouro">

                    <label for="cEstado">Estado</label>
                    <input type="text" name="cEstado" id="cEstado">

                    <label for="cCidade">Cidade</label>
                    <input type="text" name="cCidade" id="cCidade">

                    <label for="cCep">Cep</label>
                    <input type="text" name="cCep" id="cCep">

                    <label for="cComplemento">Complemento</label>
                    <input type="text" name="cComplemento" id="cComplemento">

                    <label for="iNrEnderecoEmpresa">Numero </label>
                    <input type="text" name="iNrEnderecoEmpresa" id="iNrEnderecoEmpresa">

                    <label for="cNmEmpresa">Empresa Responsavel </label>
                    <input type="text" name="cNmEmpresa" id="cNmEmpresa">

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
            <th>Estado</th>
            <th>Cidade</th>
            <th>Bairro</th>
            <th>Logradouro</th>
            <th>Complemento</th>
            <th>Número</th>
            <th>CEP</th>
            <th>Nome da Empresa</th>
            <th>Ações</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<EnderecoEmpresaView> enderecos = (List<EnderecoEmpresaView>) request.getAttribute("listaEnderecos");
            if (enderecos != null && !enderecos.isEmpty()) {
                for (EnderecoEmpresaView endereco : enderecos) {
        %>
        <tr>
            <td><%= endereco.cEstado() %></td>
            <td><%= endereco.cCidade() %></td>
            <td><%= endereco.cBairro() %></td>
            <td><%= endereco.cLogradouro() %></td>

            <%if (endereco.cComplemento() != null) {%>
            <td><%= endereco.cComplemento() %></td>
            <%}else {%>
            <td> </td>
            <%}%>

            <td><%= endereco.iNrEnderecoEmpresa() %></td>
            <td><%= endereco.cCep() %></td>
            <td><%= endereco.cNmEmpresa() %></td>

            <td class="actions">
                <img src="c:\Users\irisrodrigues-ieg\Downloads\pen 1.png" alt="Editar">
                <form action="${pageContext.request.contextPath}/endereco-empresa/delete" method="post">
                    <input type="hidden" name="nCdEnderecoEmpresa" value="<%=endereco.nCdEnderecoEmpresa()%>" >
                    <input type="submit" value="Delete">
                </form>
            </td>
        </tr>

        <%
            }
        } else {
        %>
        <tr>
            <td colspan="6">Nenhum endereço encontrado.</td>
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