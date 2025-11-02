<%@ page import="com.purpura.model.Administrador" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Ícone do site -->
    <link rel="icon" type="image/png" href="c:\Users\irisrodrigues-ieg\Downloads\Logo.png">
    <!-- Arquivo CSS para estilizar a página CRUD -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/styles/crud.css">
    <title>CRUD</title>
</head>
<body>

<!-- Botão hambúrguer para abrir/fechar a sidebar -->
<a href="#sidebar" class="hamburger">
    <div></div>
    <div></div>
    <div></div>
</a>

<!-- Barra lateral de navegação -->
<div class="sidebar" id="sidebar">
    <!-- Botão para fechar a sidebar -->
    <a href="#" class="close">×</a>
    <h2>Área de Controle</h2>

    <!-- Informações do administrador logado -->
    <div class="profile">
        <img src="https://png.pngtree.com/png-vector/20190704/ourmid/pngtree-administration-icon-in-trendy-style-isolated-background-png-image_1538647.jpg" alt="foto do admin">
        <h3><%= ((Administrador) session.getAttribute("usuario")).getCNmAdministrador() %></h3>
        <p>Admin</p>
    </div>

    <!-- Menu de navegação -->
    <div class="menu">
        <a href="<%= request.getContextPath() %>/empresa/list" target="conteudo-principal" class="active">Empresas</a>
        <a href="<%=request.getContextPath() %>/residuo/list" target="conteudo-principal">Resíduos</a>
        <a href="<%=request.getContextPath() %>/endereco-empresa/list" target="conteudo-principal">Endereços</a>
        <a href="<%=request.getContextPath() %>/telefone/list" target="conteudo-principal">Telefones</a>
        <a href="<%=request.getContextPath()%>/transportadora/list" target="conteudo-principal">Transportadoras</a>
        <a href="<%=request.getContextPath()%>/administrador/list" target="conteudo-principal">Administradores</a>
    </div>

    <!-- Link para logout -->
    <a href="<%=request.getContextPath()%>/logout" class="logout">Logout</a>
</div>

<!-- Área principal de conteúdo -->
<main class="content">
    <!-- Iframe que carrega as páginas de CRUD quando os links são clicados -->
    <iframe name="conteudo-principal"></iframe>
</main>
</body>
</html>
