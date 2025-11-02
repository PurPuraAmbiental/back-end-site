<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Página não encontrada</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;700&display=swap" rel="stylesheet">
    <link rel="shortcut icon" href="../assets/icone_purpura.svg">
    <style>
        body {
            font-family: 'Inter', sans-serif;
            background-color: #fafafa;
            color: #333;
            text-align: center;
            padding-top: 10%;
            margin: 0;
        }

        h1 {
            font-size: 72px;
            margin-bottom: 0;
            color: #9c27b0;
            font-weight: 700;
        }

        h2 {
            font-size: 28px;
            margin-top: 0;
            color: #6a1b9a;
            font-weight: 500;
        }

        p {
            font-size: 18px;
            color: #666;
            margin: 10px 0 25px;
            line-height: 1.5;
        }

        a {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 24px;
            background-color: #9c27b0;
            color: white;
            text-decoration: none;
            border-radius: 8px;
            transition: all 0.25s ease;
            font-weight: 500;
        }

        a:hover {
            background-color: #7b1fa2;
            transform: scale(1.05);
        }

        .container {
            max-width: 600px;
            margin: 0 auto;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>404</h1>
    <h2>Página não encontrada</h2>
    <p>A página que você tentou acessar não existe.</p>
    <a href="${pageContext.request.contextPath}/">Voltar para o início</a>
</div>
</body>
</html>
