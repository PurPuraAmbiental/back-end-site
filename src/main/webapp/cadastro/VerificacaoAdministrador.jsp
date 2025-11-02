<%--
  Created by IntelliJ IDEA.
  User: brunajesus-ieg
  Date: 29/10/2025
  Time: 20:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/login.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/styles/crud.css">

<html>
<head>

</head>
<body>
<div class="container-cadastro">
    <h1 class="logo">PurPura</h1>
    <h3>Antes de continuar!</h3>
    <form action="<%=request.getContextPath()%>/autenticar-cadastro" method="post">
        <label for="chave">Insira uma senha de administrador</label> <br>
        <!-- ================== MENSAGEM DE ERRO ================== -->
        <%-- Verifica erro, se há, exibe-o --%>    <% String erro = (String) request.getAttribute("erro");
        if (erro != null){ %>
        <h5> <%= erro%> </h5>
        <% }%>
        <input name = "chave" type="text" required> <br>
        <button class="add-btn" type="submit">Verificar</button>
    </form>
</div>
</body>
</html>
