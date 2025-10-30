<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<!-- ==================== POPUP DE INSERÇÃO ==================== -->
<div class="popup-overlay" id="popup-insert-administrador" style="display:none;">
    <div class="popup">
        <button class="close-btn" onclick="fecharPopup('popup-insert-administrador')">×</button>
        <h2>Cadastrar Administrador</h2>
        <form action="${pageContext.request.contextPath}/administrador/insert" method="post">
            <label>Nome do Administrador</label>
            <input type="text" name="cNmAdministrador" maxlength="" required>

            <label>Email</label>
            <input type="text" name="cEmail" required>

            <label>Senha</label>
            <input type="password" name="cSenha" required>

            <button type="submit">Adicionar</button>
        </form>
    </div>
</div>

<!-- ==================== POPUP DE ATUALIZAÇÃO ==================== -->
<div class="popup-overlay" id="popup-update-administrador" style="display:none;">
    <div class="popup">
        <button class="close-btn" onclick="fecharPopup('popup-update-administrador')">×</button>
        <h2>Atualizar Administrador</h2>
        <form action="${pageContext.request.contextPath}/administrador/update" method="post">
            <label>Nome do Administrador</label>
            <input type="text" name="cNmAdministrador" id="update-administrador-cNmAdministrador" maxlength="20" required>

            <label>Email</label>
            <input type="text" name="cEmail" id="update-administrador-cEmail" maxlength="70" readonly>
            <input type="hidden" name="cEmail" >

            <label>Senha</label>
            <input type="text" name="cSenha" id="update-administrador-cSenha" required>

            <button type="submit">Atualizar</button>
        </form>
    </div>
</div>
<!-- ==================== FILTROS ========================= -->
<div class="filtroPopup-overlay" id="filtroAdministrador" style="display:none;">
    <div class="popup" id="filtroPopup" style="height: 240px">
        <h2>Filtrar Administrador</h2>
        <label for="nomeAdministrador">Insira o nome do Administrador</label>
        <input type="text" name="nomeAdministrador" id="nomeAdministrador" placeholder="Nome do Administrador"
               value="<%= request.getParameter("nomeAdministrador") != null ? request.getParameter("nomeAdministrador") : "" %>">
        <br>
        <button type="submit" class="add-btn">Filtrar</button>
        <button class="close-btn" onclick="fecharPopup('filtroAdministrador')">×</button>

    </div>
</div>