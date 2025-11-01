<%@ page import="java.util.List" %>
<%@ page import="com.purpura.model.Transportadora" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- CSS da tabela e layout principal -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/styles/crud.css">
    <!-- CSS específico dos pop-ups -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/styles/popUp.css">

    <title>Transportadoras - CRUD</title>
</head>
<body>

<div class="main">
    <!-- ================== CABEÇALHO ================== -->
    <div class="header">
        <h1>Lista de Transportadoras</h1>

        <div class="botoes-principais">
        <!-- Botão para abrir o pop-up de filtragem de transportadora -->
        <button class="add-btn" onclick="filtroTransportadora()"> Filtros </button>
        <!-- Botão para abrir o pop-up de inserção de nova transportadora -->
        <button class="add-btn" onclick="abrirPopupInsertTransportadora()">Cadastrar Transportadora</button>
        </div>
    </div>
    <br>
    <!-- ================== MENSAGEM DE ERRO ================== -->
    <%
        // Recupera a mensagem de erro enviada pelo servlet, se houver
        String erro = (String) request.getAttribute("erro");
        if (erro != null) {%>
    <h5><%= erro %></h5>
    <% } %>

    <br>

    <!-- ================== TABELA DE TRANSPORTADORAS ================== -->
    <div class="table-container">
        <table>
            <thead>
            <tr>
                <th>Nome da Transportadora</th>
                <th>CNPJ</th>
                <th>Região Atendida</th>
                <th>Email</th>
                <th>Ações</th>
            </tr>
            </thead>
            <tbody>
            <%
                // Recupera a lista de transportadoras enviada pelo servlet
                List<Transportadora> transportadoras = (List<Transportadora>) request.getAttribute("listaTransportadoras");

                // Verifica se a lista não está vazia
                if (transportadoras != null && !transportadoras.isEmpty()) {
                    // Percorre cada transportadora da lista e gera uma linha na tabela
                    for (Transportadora t : transportadoras) {
            %>
            <tr>
                <!-- Mostra o nome da transportadora -->
                <td><%= t.getCNmTransportadora() %></td>
                <!-- Mostra o CNPJ -->
                <td><%= t.getCCnpj() %></td>
                <!-- Mostra a região atendida -->
                <td><%= t.getCRegiaoAtendida() %></td>
                <!-- Mostra o email -->
                <td><%= t.getCEmail() %></td>
                <!-- Coluna de ações (editar e excluir) -->
                <td class="actions">

                    <!-- ================== BOTÃO EDITAR ================== -->
                    <!-- Ao clicar, preenche o pop-up de atualização com os dados da transportadora -->
                    <button class="add-btn"
                            onclick="UpdateTransportadora(
                                    '<%= t.getCCnpj() %>',          // CNPJ (identificador)
                                    '<%= t.getCNmTransportadora() %>', // Nome
                                    '<%= t.getCEmail() %>',        // Email
                                    '<%= t.getCRegiaoAtendida() %>'// Região atendida
                                    )">
                        Editar
                    </button>

                    <!-- ================== BOTÃO EXCLUIR ================== -->
                    <!-- Formulário para deletar a transportadora -->
                    <form action="${pageContext.request.contextPath}/transportadora/delete" method="post" style="display:inline;">
                        <!-- CNPJ é enviado como hidden para identificar o registro -->
                        <input type="hidden" name="cCnpj" value="<%= t.getCCnpj() %>">
                        <input type="submit" class="add-btn" value="Excluir">
                    </form>
                </td>
            </tr>
            <%      } // fim do for
            } else { // caso não existam transportadoras cadastradas
            %>
            <tr>
                <td colspan="5">Nenhuma transportadora encontrada.</td>
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>
</div>

<!-- ================== POPUPS ================== -->
<!-- Inclui os formulários de inserção e atualização -->
<jsp:include page="/WEB-INF/popUp/popUp-transportadora.jsp" />

<script>
    // ================== FUNÇÃO PARA ABRIR POPUP DE INSERÇÃO ==================
    function abrirPopupInsertTransportadora() {
        // Exibe o pop-up de inserção
        document.getElementById('popup-insert-transportadora').style.display = 'flex';
    }

    // ================== FUNÇÃO PARA FECHAR POPUPS ==================
    function fecharPopup(id) {
        // Oculta o pop-up com o ID fornecido
        document.getElementById(id).style.display = 'none';
    }

    // ================== FUNÇÃO PARA ABRIR POPUP DE ATUALIZAÇÃO ==================
    function UpdateTransportadora(cCnpj, cNmTransportadora, cEmail, cRegiaoAtendida) {
        // Preenche os campos do formulário de atualização com os dados selecionados
        document.getElementById('update-transportadora-cCnpj').value = cCnpj;
        document.getElementById('update-transportadora-cNmTransportadora').value = cNmTransportadora;
        document.getElementById('update-transportadora-cEmail').value = cEmail;
        document.getElementById('update-transportadora-cRegiaoAtendida').value = cRegiaoAtendida;

        // Exibe o pop-up de atualização
        document.getElementById('popup-update-transportadora').style.display = 'flex';
    }

    //FUNÇÃO PARA ABRIR O POP UP DOS FILTROS
    function filtroTransportadora(){
        document.getElementById('filtroTransportadora').style.display = 'flex';
    }
</script>

</body>
</html>
