<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;700&display=swap" rel="stylesheet">
    <link rel="shortcut icon" href="../assets/icone_purpura.svg">
    <title>Erro</title>
    <style>
        body {
            font-family: 'Inter', Arial, sans-serif;
            margin: 2rem;
            background-color: #fafafa;
            color: #333;
            text-align: center;
        }

        .card {
            display: inline-block;
            max-width: 640px;
            border: 1px solid #e0e0e0;
            padding: 2rem;
            border-radius: 12px;
            background: #f9f2fb;
            box-shadow: 0 2px 6px rgba(0, 0, 0, 0.08);
        }

        .title {
            font-size: 1.5rem;
            margin-bottom: 1rem;
            color: #9c27b0;
            font-weight: 600;
        }

        .msg {
            font-size: 1rem;
            margin: 1rem 0;
            color: #555;
        }

        .cid {
            color: #777;
            font-size: 0.9rem;
            margin-top: 1rem;
        }

        a {
            display: inline-block;
            margin-top: 1.5rem;
            padding: 10px 20px;
            background-color: #9c27b0;
            color: white;
            text-decoration: none;
            border-radius: 6px;
            transition: background-color 0.3s ease;
        }

        a:hover {
            background-color: #7b1fa2;
        }
    </style>
</head>
<body>
<div class="card">
    <div class="title">Ocorreu um erro ao processar a solicitação</div>

    <!-- Mensagem para o usuario -->
    <div class="msg">
        ${requestScope.erro != null ? requestScope.erro : "Ocorreu um erro inesperado."}
    </div>

    <!-- Código de erro técnico -->
    <div class="cid">
        Código de referência: ${requestScope.cid != null ? requestScope.cid : "N/A"}
    </div>

    <a href="javascript:history.back()">Voltar</a>
</div>
</body>
</html>