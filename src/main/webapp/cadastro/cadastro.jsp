<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>PurPura - Cadastro</title>
    <link rel="stylesheet" href="../styles/login.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600;700;800&display=swap" rel="stylesheet">
    <link rel="shortcut icon" type="image/png" href="../assets/purpura/icone_purpura.svg">
</head>
<body>
    <div class="container-cadastro">
        <h1 class="logo">PurPura</h1>
        <p class="subtitulo">A sua jornada sustentável começa aqui!</p>
        <form action="${pageContext.request.contextPath}/administrador/insert" method="post" id="form-cadastro">
            <div class="form-group">
                <input type="hidden" name="tabelaNome" value="Administrador">
                <label for="nome">Nome Completo</label>
                <input type="text" id="nome" name="cNmAdministrador" placeholder="Seu nome" required>
            </div>
            <div class="form-group">
                <label for="email">E-mail</label>
                <input type="email" id="email" name="cEmail" placeholder="nome@exemplo.com" required>
            </div>
            <div class="form-group">
                <label for="senha">Senha</label>
                <input type="password" id="senha" name="cSenha" placeholder="Sua senha" required>
            </div>
            <div class="form-group">
                <label for="confirmar-senha">Confirmar Senha</label>
                <input type="password" id="confirmar-senha" placeholder="Confirme sua senha" required>
            </div>
            <div class="form-footer">
                <input type="hidden" name="origem" value="crud">
                <button type="submit" class="btn-cadastro">Cadastrar</button>
                <a href="login.jsp" class="link-login">Já tem uma conta? Faça login</a>
            </div>
        </form>
    </div>
</body>
</html>