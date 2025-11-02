<%@ page import="java.util.List" %>
<%@ page import="com.purpura.dto.TelefoneView" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>

<!-- TELEFONE.JSP
Página responsável por listar todos os telefones das empresas cadastradas.
Permite também cadastrar, editar e excluir registros através dos pop-ups.

obs: Assim como em outras telas do CRUD, foi utilizada uma DTO (TelefoneView)
no lugar da model para facilitar a exibição dos dados.

função principal: CRUD -> READ
mas também possibilita o usuário realizar ações de: INSERT, UPDATE e DELETE.

autor(a): Bruna Oliveira
autor(a): Kevin de Oliveira
-->

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Importação dos arquivos CSS -->
    <!-- O parâmetro ?v=2 pode ser utilizado para forçar atualização de cache, caso necessário -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/styles/crud.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/styles/popUp.css">

    <title>Telefones</title>
</head>

<body>
<div class="main">

    <!-- Cabeçalho da página -->
    <div class="header">
        <h1>Lista de Telefones</h1>
        <div class="botoes-principais">
        <!-- Botão que abre o pop-up para cadastrar um novo telefone -->
        <button class="add-btn" onclick="filtroTelefone()">Filtros </button>
        <button class="add-btn" onclick="abrirPopupInsertTelefone()">Adicionar Telefone</button>
        </div>
    </div>

    <br>
    <!-- Exibição de mensagens de erro enviadas pelo Servlet -->
    <%
        String erro = (String) request.getAttribute("erro");
        if (erro != null) {
    %>
    <h5><%= erro %></h5>
    <% } %>

    <br>

    <!-- Container da tabela -->
    <div class="table-container">
        <table>
            <thead>
            <!-- Cabeçalho das colunas -->
            <tr>
                <th>Telefone</th>
                <th>Empresa</th>
                <th>Descrição</th>
                <th>Ações</th>
            </tr>
            </thead>

            <tbody>
            <%
                // Recupera a lista de telefones enviada pelo servlet
                List<TelefoneView> telefones = (List<TelefoneView>) request.getAttribute("listaTelefones");

                // Verifica se há registros
                if (telefones != null && !telefones.isEmpty()) {
                    // Percorre todos os telefones e exibe um por linha
                    for (TelefoneView telefone : telefones) {
            %>
            <tr>
                <!-- Exibição dos dados do telefone -->
                <td><%= telefone.cNrTelefone() %></td>
                <td><%= telefone.cNmEmpresa() %></td>
                <td><%= telefone.cDescricao() %></td>

                <td class="actions">
                    <!-- Botão de edição: preenche o pop-up com os dados do telefone -->
                    <button class="add-btn"
                            onclick="UpdateTelefone(
                                    '<%= telefone.nCdTelefone() %>',
                                    '<%= telefone.cNmEmpresa() %>',
                                    '<%= telefone.cNrTelefone() %>',
                                    '<%= telefone.cDescricao() %>')">
                        Editar
                    </button>

                    <!-- Formulário para exclusão de telefone -->
                    <button class="add-btn"
                            onclick="DeleteTelefone('<%= telefone.nCdTelefone() %>', '<%= telefone.cNrTelefone() %>')">
                        Excluir
                    </button>
                </td>
            </tr>
            <%
                } // fim do for
            } else {
                // Caso não existam telefones cadastrados
            %>
            <tr><td colspan="4">Nenhum telefone encontrado.</td></tr>
            <% } %>
            </tbody>
        </table>
    </div>
</div>

<!-- Inclusão do JSP responsável pelos pop-ups -->
<jsp:include page="/WEB-INF/popUp/popUp-telefone.jsp" />

<!-- Funções JavaScript para manipulação dos pop-ups -->
<script>
    // Exibe o pop-up de inserção de novo telefone
    function abrirPopupInsertTelefone() {
        document.getElementById('popup-insert-telefone').style.display = 'flex';
    }

    // Fecha o pop-up informado pelo ID
    function fecharPopup(id) {
        document.getElementById(id).style.display = 'none';
    }

    // Preenche o pop-up de atualização com os dados do telefone selecionado
    function UpdateTelefone(nCdTelefone, cNmEmpresa, cNrTelefone, cDescricao) {
        document.getElementById('update-telefone-nCdTelefone').value = nCdTelefone;
        document.getElementById('update-telefone-cNmEmpresa').value = cNmEmpresa;
        document.getElementById('update-telefone-cNrTelefone').value = cNrTelefone;
        document.getElementById('update-telefone-cDescricao').value = cDescricao;

        // Exibe o pop-up de edição
        document.getElementById('popup-update-telefone').style.display = 'flex';
    }
    // ================== FUNÇÃO PARA ABRIR POPUP DE DELETE ==================
    function DeleteTelefone(nCdTelefone, cNrTelefone) {
        document.getElementById('delete-telefone-nCdTelefone').value = nCdTelefone;
        document.getElementById('delete-telefone-nome').innerText = cNrTelefone;
        document.getElementById('popup-delete-telefone').style.display = 'flex';
    }
    //FUNÇÃO PARA ABRIR O POP UP DOS FILTROS
    function filtroTelefone(){
        document.getElementById('filtroTelefone').style.display = 'flex';
    }
</script>
</body>
</html>