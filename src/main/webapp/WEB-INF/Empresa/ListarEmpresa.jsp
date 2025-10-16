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
<%
    List<Empresa> empresa = (List<Empresa>) request.getAttribute("listaEmpresas");
    if (empresa != null) {
        for (Empresa empresa1 : empresa) {
%>
    <tr>
        <td> <%= empresa1.getCNmEmpresa() %></td>
        <td><%= empresa1.getCCnpj()%></td>
        <td><%= empresa1.getCEmail()%></td>
        <td><%= empresa1.getCTelefone()%></td>
        <td><%= empresa1.getCAtivo()%></td
        <td><%= empresa1.getCSenha()%></td>
        <td><form action="" method="post">
            <input type="hidden" name="cCnpj" value="<%=empresa1.getCSenha()%>">
            <input type="submit" value="Deletar Registro">
        </form></td>
    </tr>
    <%
      }
    } else {
    %>
        <tr><td colspan="3">Nenhum administrador encontrado.</td></tr>
        <%
    }
        %>
</body>
</html>
