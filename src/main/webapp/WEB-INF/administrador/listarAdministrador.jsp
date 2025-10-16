<%@ page import="com.purpura.dao.AdministradorDAO" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.util.List" %>
<%@ page import="com.purpura.model.Administrador" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.purpura.model.Model" %><%--
  Created by IntelliJ IDEA.
  User: brunajesus-ieg
  Date: 10/10/2025
  Time: 22:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%--%>
<%--    // Mock de administradores--%>
<%--    List<Administrador> administrador = new ArrayList<>();--%>
<%--    administrador.add(new Administrador("admin1", "123"));--%>
<%--    administrador.add(new Administrador("admin2", "456"));--%>
<%--%>--%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1">
    <tr>
        <th>id</th>
        <th>Login</th>
        <th>Senha</th>
        <th>Ações</th>
    </tr>
    <%
        List<Administrador> administrador = (List<Administrador>) request.getAttribute("models");
        if (administrador != null) {
            for (Administrador adm : administrador) {
    %>
    <tr>
        <td><%= adm.getCNmAdministrador() %></td>
        <td><%= adm.getCEmail()      %></td>
        <td><%= adm.getCSenha()      %></td>
        <td> <form action="delete" method="post">
            <input type="hidden" name="cEmail" value="<%= adm.getCEmail()  %>">
            <input type="hidden" name="tabelaNome" value="Administrador">
            <input type="hidden" name="caminho" value="WEB-INF/Administrador/ListarAdministrador.jsp">
            <input type="submit" value="Deletar Registro">

        </form>
            <form action="findId" method="post">
                <input type="hidden" name="tabelaNome" value="Administrador">
                <input type="hidden" name="id" value="<%= adm.getCEmail()  %>">
                <input type="hidden" name="caminho" value="WEB-INF/Administrador/UpdateAdministrador.jsp">
                <input type="submit" value="Modificar Registro">
            </form>

        </td>
    </tr>
    <%
        }
    } else {
    %>
    <tr><td colspan="3">Nenhum administrador encontrado.</td></tr>
    <%
        }
    %>

</table>
<br>
<a href="PopUpAdministrador.html">
    adicionar item
</a>
</body>
</html>