<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<!-- POPUP INSERT -->
<div class="popup-overlay" id="popup-insert-transportadora" style="display:none;">
    <div class="popup">
        <button class="close-btn" onclick="fecharPopup('popup-insert-transportadora')">×</button>
        <h2>Cadastrar Transportadora</h2>

        <form action="${pageContext.request.contextPath}/transportadora/insert" method="post">
            <label for="cNmTransportadora">Nome da Transportadora</label>
            <input type="text" name="cNmTransportadora" id="cNmTransportadora" required>

            <label for="cCnpj">CNPJ</label>
            <input type="text" name="cCnpj" id="cCnpj" required>

            <label for="cEmail">Email</label>
            <input type="text" name="cEmail" id="cEmail" required>

            <label for="cRegiaoAtendida">Região Atendida</label>
            <input type="text" name="cRegiaoAtendida" id="cRegiaoAtendida" required>

            <button type="submit">Adicionar</button>
        </form>
    </div>
</div>

<!-- POPUP UPDATE -->
<div class="popup-overlay" id="popup-update-transportadora" style="display:none;">
    <div class="popup">
        <button class="close-btn" onclick="fecharPopup('popup-update-transportadora')">×</button>
        <h2>Atualizar Transportadora</h2>

        <form action="${pageContext.request.contextPath}/transportadora/update" method="post">
            <label for="upd-cNmTransportadora">Nome da Transportadora</label>
            <input type="text" name="cNmTransportadora" id="update-transportadora-cNmTransportadora" required>

            <label for="upd-cCnpj">CNPJ</label>
            <input type="text" name="cCnpj" id="update-transportadora-cCnpj" readonly>

            <label for="upd-cEmail">Email</label>
            <input type="text" name="cEmail" id="update-transportadora-cEmail" required>

            <label for="upd-cRegiaoAtendida">Região Atendida</label>
            <input type="text" name="cRegiaoAtendida" id="update-transportadora-cRegiaoAtendida" required>

            <button type="submit">Atualizar</button>
        </form>
    </div>
</div>

