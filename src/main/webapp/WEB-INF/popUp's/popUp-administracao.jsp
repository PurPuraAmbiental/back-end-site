<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<!-- INSERT -->
<div class="popup-overlay" id="popup-insert-administrador" style="display:none;">
    <div class="popup">
        <button class="close-btn" onclick="fecharPopup('popup-insert-administrador')">×</button>
        <h2>Cadastrar Administrador</h2>
        <form action="${pageContext.request.contextPath}/administrador/insert" method="post">
            <label>Nome do Administrador</label>
            <input type="text" name="cNmAdministrador" required>

            <label>Email</label>
            <input type="email" name="cEmail" required>

            <label>Senha</label>
            <input type="password" name="cSenha" required>

            <button type="submit">Adicionar</button>
        </form>
    </div>
</div>

<!-- UPDATE -->
<div class="popup-overlay" id="popup-update-administrador" style="display:none;">
    <div class="popup">
        <button class="close-btn" onclick="fecharPopup('popup-update-administrador')">×</button>
        <h2>Atualizar Administrador</h2>
        <form action="${pageContext.request.contextPath}/administrador/update" method="post">
            <label>Nome do Administrador</label>
            <input type="text" name="cNmAdministrador" id="upd-cNmAdministrador" required>

            <label>Email</label>
            <input type="text" name="cEmail" id="upd-cEmail">
            <input type="hidden" name="cEmail" id="upd-cEmail">

            <label>Senha</label>
            <input type="text" name="cSenha" id="upd-cSenha" required>

            <button type="submit">Atualizar</button>
        </form>
    </div>
</div>
