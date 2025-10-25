<%@ page import="com.purpura.model.Administrador" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" type="image/png" href="c:\Users\irisrodrigues-ieg\Downloads\Logo.png">
    <link rel="stylesheet" href="crud.css">
    <title>CRUD</title>
</head>
<body>
<!-- aba lateral -->
<div class="sidebar">
    <h2>CRUD OPERATIONS</h2>
    <div class="profile">
        <img src="https://png.pngtree.com/png-vector/20190704/ourmid/pngtree-administration-icon-in-trendy-style-isolated-background-png-image_1538647.jpg" alt="foto do admin">
        <h3><%= ((Administrador) session.getAttribute("usuario")).getCNmAdministrador() %></h3>
        <p>Admin</p>
    </div>
    <div class="menu">
        <a href= "<%= request.getContextPath() %>/empresa/list" target="conteudo-principal" class="active"> Empresas</a>
        <a href="<%=request.getContextPath() %>/residuo/list" target="conteudo-principal">Resíduos</a>
        <a href="<%=request.getContextPath() %>/endereco-empresa/list" target="conteudo-principal">Endereço</a>
        <a href="<%=request.getContextPath() %>/telefone/list" target="conteudo-principal">Telefone</a>
        <a href="<%=request.getContextPath()%>/transportadora/list" target="conteudo-principal">Transportadora</a>
        <a href="<%=request.getContextPath()%>/administrador/list" target="conteudo-principal">Administrador</a>
    </div>
    <a href="/index.html" class="logout">Logout</a>
</div>

<main class="content">
    <iframe name="conteudo-principal"></iframe>
</main>

</body>
</html>
