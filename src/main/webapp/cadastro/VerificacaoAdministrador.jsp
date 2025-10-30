<%--
  Created by IntelliJ IDEA.
  User: brunajesus-ieg
  Date: 29/10/2025
  Time: 20:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="../styles/login.css">
<html>
<head>
    <div class="container-cadastro">
        <h1 class="logo">PurPura</h1>
        <h3>Antes de continuar!</h3>
        <form action="<%=request.getContextPath()%>/autenticar-cadastro" method="post">
        <label for="chave">Insira uma senha de administrador</label> <br>
           <input name = "chave" type="text"> <br>
        <button type="submit">Verificar</button>
        </form>
    </div>
</head>
<body>

</body>
</html>
