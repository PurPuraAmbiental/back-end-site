<%@ page import="com.purpura.model.Empresa" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: brunajesus-ieg
  Date: 13/10/2025
  Time: 09:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1">
<tr>
    <th>Nome</th>
    <th>CNPJ</th>
    <th>Email</th>
    <th>Senha</th>
    <th>ativo</th>
    <th>acoes</th>
</tr>
<%
    List<Empresa> empresa = (List<Empresa>) request.getAttribute("listaEmpresas");
    if (empresa != null) {
        for (Empresa empresa1 : empresa) {
%>
    <tr>
        <td> <%= empresa1.getCNmEmpresa() %></td>
        <td><%= empresa1.getCCnpj()%></td>
        <td><%= empresa1.getCEmail()%></td>
        <td><%= empresa1.getCSenha()%></td>
        <td><%= empresa1.getCAtivo()%></td
        <td> <form action="${pageContext.request.contextPath}/empresa/insert" method="post">
            <input type="hidden" name="cEmail" value="<%= empresa1.getCCnpj()  %>">
            <input type="submit" value="Deletar Registro">
        </form>
            <form action="" method="post">
                <input type="hidden" name="tabelaNome" value="Administrador">
                <input type="hidden" name="id" value="<%= empresa1.getCEmail()  %>">
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
<a href=""></a>
</body>
</html>
