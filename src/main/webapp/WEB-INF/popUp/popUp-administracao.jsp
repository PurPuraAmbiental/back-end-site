<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<!-- ==================== POPUP DE INSERÇÃO ==================== -->
<div class="popup-overlay" id="popup-insert-administrador" style="display:none;">
    <div class="popup">
        <button class="close-btn" onclick="fecharPopup('popup-insert-administrador')">×</button>
        <h2>Cadastrar Administrador</h2>
        <form action="${pageContext.request.contextPath}/administrador/insert"
              method="post"
              onsubmit="const btn = this.querySelector('button[type=submit]'); btn.disabled = true; btn.innerText = 'Adicionando...';">

            <label for="cNmAdministrador">Nome do Administrador</label>
            <input type="text" name="cNmAdministrador" id="cNmAdministrador" maxlength="100" required>

            <label for="cEmail">Email</label>
            <input type="email" name="cEmail" id="cEmail" maxlength="70" required>

            <label for="cSenha">Senha</label>
            <input type="password" name="cSenha" id="cSenha" required>

            <button type="submit">Adicionar</button>
        </form>

    </div>
</div>

<!-- ==================== POPUP DE ATUALIZAÇÃO ==================== -->
<div class="popup-overlay" id="popup-update-administrador" style="display:none;">
    <div class="popup">
        <button class="close-btn" onclick="fecharPopup('popup-update-administrador')">×</button>
        <h2>Atualizar Administrador</h2>
        <form action="${pageContext.request.contextPath}/administrador/update"
              method="post"
              onsubmit="const btn = this.querySelector('button[type=submit]'); btn.disabled = true; btn.innerText = 'Atualizando...';">

            <label for="update-administrador-cNmAdministrador">Nome do Administrador</label>
            <input type="text" name="cNmAdministrador" id="update-administrador-cNmAdministrador" maxlength="100" required>

            <label for="update-administrador-cEmail">Email</label>
            <input type="text" name="cEmail" id="update-administrador-cEmail" style="color: gray;" maxlength="70" readonly>

            <label for="update-administrador-cSenha">Senha</label>
            <input type="text" name="cSenha" id="update-administrador-cSenha" required>

            <button type="submit">Atualizar</button>
        </form>
    </div>
</div>
<!-- ==================== FILTROS ========================= -->
<div class="filtroPopup-overlay" id="filtroAdministrador" style="display:none;">
    <div class="popup" id="filtroPopup" style="height: 240px">
        <button class="close-btn" onclick="fecharPopup('filtroAdministrador')">×</button>
        <form action="${pageContext.request.contextPath}/administrador/list" method="get">
        <h2>Filtrar Administrador</h2>
        <label for="nomeAdministrador">Insira o nome do Administrador</label>
        <input type="text" name="nomeAdministrador" id="nomeAdministrador" placeholder="Nome do Administrador"
               value="<%= request.getParameter("nomeAdministrador") != null ? request.getParameter("nomeAdministrador") : "" %>">
        <br>
        <button type="submit" class="add-btn">Filtrar</button>
        </form>
    </div>
</div>