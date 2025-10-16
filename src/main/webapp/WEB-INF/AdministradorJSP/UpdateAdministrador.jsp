<%@ page import="com.purpura.model.Model" %>
<%@ page import="com.purpura.model.Administrador" %><%--
  Created by IntelliJ IDEA.
  User: brunajesus-ieg
  Date: 12/10/2025
  Time: 02:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="updateAttribute" method="post">
    <%
        Administrador administrador = (Administrador) request.getAttribute("model");
        String nome = administrador.getCNmAdministrador();
        String email = administrador.getCEmail();
        String senha = administrador.getCSenha();
    %>
    <input type="hidden" name="tabelaNome" value="Administrador">
    <input type="hidden" name="atributo" value="cEmail">
    <input type="hidden" name="valor" value="<%=email%>">
    <input type="hidden" name="caminho" value="WEB-INF/Administrador/ListarAdm.jsp">
    <label>Nome: </label><br>
    <input type="text" name="cNmAdministrador" value="<%=nome%>"><br>

    <label>Email: </label><br>
    <input type="text" name="cEmail" value="<%=email%>"><br>

    <label>Senha: </label><br>
    <input type="text" name="cSenha" value="<%=senha%>"><br>

    <input type="submit" name="senha" value="Atualizar!">
</form>
</body>
</html>