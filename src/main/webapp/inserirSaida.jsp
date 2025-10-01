<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Inserção Concluída</title>
</head>
<body>
<h1>Registro inserido com sucesso!</h1>
<p><strong>Tabela:</strong> ${tabela}</p>
<p><strong>Mensagem:</strong> ${saida}</p>

<hr>
<h3>Parâmetros enviados:</h3>
<ul>
  <c:forEach var="entry" items="${param}">
    <li>${entry.key} = ${entry.value}</li>
  </c:forEach>
</ul>
</body>
</html>
