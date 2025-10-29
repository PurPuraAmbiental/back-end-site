<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<!--
Por motivos de organização, esta página guarda os formulários dos pop-ups
Responsável -> CREATE e UPDATE do CRUD de Telefone
-->

<!-- ==================== POPUP DE INSERÇÃO ==================== -->
<div class="popup-overlay" id="popup-insert-telefone" style="display:none;">
    <div class="popup">
        <!-- botao para cancelar o popUp-->
        <button class="close-btn" onclick="fecharPopup('popup-insert-telefone')">×</button>
        <h2>Cadastrar Telefone</h2>

        <form action="${pageContext.request.contextPath}/telefone/insert" method="post">
            <!-- Empresa -->
            <label for="cNmEmpresa">Empresa</label>
            <input type="text" name="cNmEmpresa" id="cNmEmpresa" required>

            <!-- Telefone -->
            <label for="cNrTelefone">Telefone</label>
            <input type="text" name="cNrTelefone" id="cNrTelefone" required>

            <!-- Descrição -->
            <label for="cDescricao">Descrição</label>
            <input type="text" name="cDescricao" id="cDescricao" required>

            <button type="submit">Adicionar</button>
        </form>
    </div>
</div>

<!-- ==================== POPUP DE ATUALIZAÇÃO ==================== -->
<div class="popup-overlay" id="popup-update-telefone" style="display:none;">
    <div class="popup">
        <button class="close-btn" onclick="fecharPopup('popup-update-telefone')">×</button>
        <h2>Atualizar Telefone</h2>

        <form action="${pageContext.request.contextPath}/telefone/update" method="post">
            <!-- Código do Telefone (oculto) -->
            <input type="hidden" name="nCdTelefone" id="update-telefone-nCdTelefone">

            <!-- Empresa -->
            <label for="update-telefone-cNmEmpresa">Empresa</label>
            <input type="text" name="cNmEmpresa" id="update-telefone-cNmEmpresa" required>

            <!-- Telefone -->
            <label for="update-telefone-cNrTelefone">Telefone</label>
            <input type="text" name="cNrTelefone" id="update-telefone-cNrTelefone" required>

            <!-- Descrição -->
            <label for="update-telefone-cDescricao">Descrição</label>
            <input type="text" name="cDescricao" id="update-telefone-cDescricao" required>

            <button type="submit">Atualizar</button>
        </form>
    </div>
</div>

<div class="filtroPopup-overlay" id="filtroTelefone" style="display:none;">
    <div class="popup">
        <button class="close-btn" onclick="fecharPopup('filtroTelefone')">×</button>
        <h2>Atualizar Telefone</h2>
        <form action="<%=request.getContextPath()%>/telefone/list" method="get">
            <input type="text" name="nomeEmpresa" placeholder="Digite o nome da empresa">
            <button type="submit" class="add-btn">Filtrar</button>
        </form>
    </div>
</div>
