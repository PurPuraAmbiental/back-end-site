<%@ page contentType="text/html;charset=UTF-8"%>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - PurPura</title>
    <link rel="stylesheet" herf="styles/texto.css">
    <link rel="shortcut icon" href="../assets/purpura/icone_purpura.svg">
    <link rel="stylesheet" href="styles/login.css">
</head>
<body class="inter">
    <div class="login-container">
        <h1 class="logo">PurPura</h1>
        <p class="subtitulo">A sua jornada sustentável continua aqui!</p>
        <form id="login-form" action="login-auth" method="post">
            <div class="form-group">
                <label for="email">E-mail</label>
                <input type="text" id="email" name="email" placeholder="nome@exemplo.com" required>
            </div>
            <div class="form-group">
                <label for="password">Senha</label>
                <input type="password" id="password" name="senha" placeholder="Sua senha" required>
                <span id="password-error" class="error-message">6 Digitos mininos</span>
            </div>
            <% String erro = (String) request.getAttribute("erro"); %>
            <% if (erro != null) { %>
            <p style="color:red;"><%= erro %></p>
            <% } %>
            <div class="form-footer">
                <button type="submit" class="btn-login">Entrar</button>
                <a href="cadastro.html" class="link-cadastro">Ainda não tem conta? Cadastre-se</a>
                <a href="#" class="link-senha">Esqueceu a senha?</a>
            </div>
        </form>
    </div>
</body>
</html>
