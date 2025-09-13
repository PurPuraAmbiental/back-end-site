<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Erro</title>
</head>
<body>
<h1>Ocorreu um erro!</h1>
<p><strong>Mensagem:</strong> ${erro}</p>

<hr>
<h3>Parâmetros enviados:</h3>
<ul>
    <c:forEach var="entry" items="${param}">
        <li>${entry.key} = ${entry.value}</li>
    </c:forEach>
</ul>
</body>
</html>
