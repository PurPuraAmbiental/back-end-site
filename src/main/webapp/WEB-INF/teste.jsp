<%@ page import="com.purpura.dao.AdministradorDAO" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.util.List" %>
<%@ page import="com.purpura.model.Administrador" %><%--
  Created by IntelliJ IDEA.
  User: brunajesus-ieg
  Date: 10/10/2025
  Time: 13:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

        <%String senha = (String) request.getAttribute("senha");
          String email = (String) request.getAttribute("email");
            AdministradorDAO administradorDAO = new AdministradorDAO();
            ResultSet rs = administradorDAO.find();
            while (rs.next()){ %>
            <h1>senha: <%= senha%></h1>
            <h1>email: <%= email%></h1>
           <%}%>
</body>
</html>
