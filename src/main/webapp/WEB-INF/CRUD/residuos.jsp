<%--
/**
 * Exibir e gerenciar resíduos cadastrados no sistema PurPura.
 *
 * Funcionalidades:
 * - Exibe lista completa de resíduos cadastrados no sistema
 * - Permite filtragem por nome, unidade, categoria, preço, volume e empresa vinculada
 * - Suporte a inserção, edição e exclusão de resíduos via pop-ups
 * - Exibe mensagens de erro retornadas pelo servlet
 * - Integra-se aos formulários JSP de pop-ups para cadastro, atualização e filtros
 * - Exibe informações detalhadas como unidade de medida, valor padrão, categoria e descrição
 *
 * Autora: Bruna de Jesus
 * Autor: Kevin de Oliveira
 */
--%>

<%@ page import="com.purpura.dto.ResiduoView" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/styles/crud.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/styles/popUp.css">
    <title>Resíduos - CRUD</title>
</head>

<body>
<div class="main">
    <!-- ================== CABEÇALHO ================== -->
    <div class="header">
        <h1>Lista de Resíduos</h1>
        <div class="botoes-principais">
            <!-- Botão para abrir o pop-up de filtragem de resíduos -->
            <button class="add-btn" onclick="filtroResiduo()">Filtros</button>
            <!-- Botão para abrir o pop-up de inserção de novo resíduo -->
            <button class="add-btn" onclick="abrirPopupInsertResiduo()">Adicionar Resíduo</button>
        </div>
    </div>

    <br>

    <!-- ================== MENSAGEM DE ERRO ================== -->
    <%-- Exibe mensagens de erro enviadas pelo servlet, se existirem --%>
    <%
        String erro = (String) request.getAttribute("erro");
        if (erro != null) {
    %>
    <h5><%= erro %></h5>
    <%
            request.removeAttribute("erro");
        }
    %>

    <br>

    <!-- ================== TABELA DE RESÍDUOS ================== -->
    <div class="table-container">
        <table>
            <thead>
            <tr>
                <th>Nome</th>
                <th>Unidade</th>
                <th>Preço</th>
                <th>Volume</th>
                <th>Categoria</th>
                <th>Empresa</th>
                <th>Descrição</th>
                <th>Ações</th>
            </tr>
            </thead>
            <tbody>
            <%
                // Recupera a lista de resíduos enviada pelo servlet
                List<ResiduoView> residuos = (List<ResiduoView>) request.getAttribute("listaResiduos");

                // Verifica se a lista contém elementos
                if (residuos != null && !residuos.isEmpty()) {
                    // Percorre cada resíduo e gera uma linha da tabela
                    for (ResiduoView residuo : residuos) {
            %>
            <tr>
                <!-- Exibe os dados principais do resíduo -->
                <td><%= residuo.cNmResiduo() %></td>
                <td><%= residuo.cTipoUnidade() %></td>
                <td><%= residuo.nPrecoPadrao() %></td>
                <td><%= residuo.nVolumePadrao() %></td>
                <td><%= residuo.cCategoria() %></td>
                <td><%= residuo.cNmEmpresa() %></td>
                <td><%= residuo.cDescricao() %></td>

                <!-- ================== AÇÕES (Editar / Excluir) ================== -->
                <td class="actions">
                    <!-- Botão para abrir pop-up de edição preenchido com os dados -->
                    <button class="add-btn"
                            onclick="UpdateResiduo(
                                    '<%= residuo.nCdResiduo() %>',
                                    '<%= residuo.cNmResiduo() %>',
                                    '<%= residuo.cTipoUnidade() %>',
                                    '<%= residuo.nPrecoPadrao() %>',
                                    '<%= residuo.nVolumePadrao() %>',
                                    '<%= residuo.cCategoria() %>',
                                    '<%= residuo.cNmEmpresa() %>',
                                    '<%= residuo.cDescricao() %>'
                                    )">
                        Editar
                    </button>

                    <!-- Botão para abrir pop-up de exclusão -->
                    <button class="add-btn"
                            onclick="DeleteResiduo('<%= residuo.nCdResiduo() %>', '<%= residuo.cNmResiduo() %>')">
                        Excluir
                    </button>
                </td>
            </tr>
            <%
                } // fim do for
            } else { // Caso não existam resíduos cadastrados
            %>
            <tr><td colspan="8">Nenhum resíduo encontrado.</td></tr>
            <%
                }
            %>
            </tbody>
        </table>
    </div>
</div>

<!-- ================== POPUPS ================== -->
<!-- Inclui os formulários de inserção, atualização e filtro -->
<jsp:include page="/WEB-INF/popUp/popUp-residuo.jsp" />

<script>
    // ================== FUNÇÃO PARA ABRIR POPUP DE INSERÇÃO ==================
    function abrirPopupInsertResiduo() {
        document.getElementById('popup-insert-residuo').style.display = 'flex';
    }

    // ================== FUNÇÃO PARA FECHAR POPUPS (Genérica) ==================
    function fecharPopup(id) {
        document.getElementById(id).style.display = 'none';
    }

    // ================== FUNÇÃO PARA ABRIR POPUP DE ATUALIZAÇÃO ==================
    function UpdateResiduo(nCdResiduo, cNmResiduo, cTipoUnidade, nPrecoPadrao, nVolumePadrao, cCategoria, cNmEmpresa, cDescricao) {
        // Preenche os campos do formulário de atualização com os valores atuais
        document.getElementById('update-residuo-nCdResiduo').value = nCdResiduo;
        document.getElementById('update-residuo-cNmResiduo').value = cNmResiduo;
        document.getElementById('update-residuo-cTipoUnidade').value = cTipoUnidade;
        document.getElementById('update-residuo-nPrecoPadrao').value = nPrecoPadrao;
        document.getElementById('update-residuo-nVolumePadrao').value = nVolumePadrao;
        document.getElementById('update-residuo-cCategoria').value = cCategoria;
        document.getElementById('update-residuo-cNmEmpresa').value = cNmEmpresa;
        document.getElementById('update-residuo-cDescricao').value = cDescricao;

        // Exibe o pop-up de atualização
        document.getElementById('popup-update-residuo').style.display = 'flex';
    }

    // ================== FUNÇÃO PARA ABRIR POPUP DE DELETE ==================
    function DeleteResiduo(nCdResiduo, cNmResiduo) {
        document.getElementById('delete-residuo-nCdResiduo').value = nCdResiduo;
        document.getElementById('delete-residuo-nome').innerText = cNmResiduo;
        document.getElementById('popup-delete-residuo').style.display = 'flex';
    }

    // ================== FUNÇÃO PARA ABRIR POPUP DE FILTROS ==================
    function filtroResiduo() {
        document.getElementById('filtroResiduo').style.display = 'flex';
    }
</script>
</body>
</html>
