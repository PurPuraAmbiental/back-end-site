<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<!-- POPUP INSERT -->
<div class="popup-overlay" id="popup-insert-telefone" style="display:none;">
    <div class="popup">
        <button class="close-btn" onclick="fecharPopup('popup-insert-telefone')">×</button>
        <h2>Cadastrar Telefone</h2>

        <form action="${pageContext.request.contextPath}/telefone/insert" method="post">
            <label>Empresa</label>
            <input type="text" name="cNmEmpresa" id="cNmEmpresa" required>

            <label>Telefone</label>
            <input type="text" name="cNrTelefone" id="cNrTelefone" required>

            <label>Descrição</label>
            <input type="text" name="cDescricao" id="cDescricao" required>

            <button type="submit">Adicionar</button>
        </form>
    </div>
</div>

<!-- POPUP UPDATE -->
<div class="popup-overlay" id="popup-update-telefone" style="display:none;">
    <div class="popup">
        <button class="close-btn" onclick="fecharPopup('popup-update-telefone')">×</button>
        <h2>Atualizar Telefone</h2>

        <form action="${pageContext.request.contextPath}/telefone/update" method="post">
            <input type="hidden" name="nCdTelefone" id="nCdTelefone">

            <label>Empresa</label>
            <input type="text" name="cNmEmpresa" id="cNmEmpresa" required>

            <label>Telefone</label>
            <input type="text" name="cNrTelefone" id="cNrTelefone" required>

            <label>Descrição</label>
            <input type="text" name="cDescricao" id="cDescricao" required>

            <button type="submit">Atualizar</button>
        </form>
    </div>
</div>
