<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<!--
    Por motivos de organização, esta página guarda os formulários dos pop-ups
    Responsável -> CREATE e UPDATE do CRUD
-->

<!-- ==================== POPUP DE INSERÇÃO ==================== -->
<div class="popup-overlay" id="popup-insert-transportadora" style="display:none;">
    <div class="popup">
        <button class="close-btn" onclick="fecharPopup('popup-insert-transportadora')">×</button>
        <h2>Cadastrar Transportadora</h2>

        <form action="${pageContext.request.contextPath}/transportadora/insert" method="post">
            <!-- Nome -->
            <label for="cNmTransportadora">Nome da Transportadora</label>
            <input type="text" name="cNmTransportadora" id="cNmTransportadora" required>

            <!-- CNPJ -->
            <label for="cCnpj">CNPJ</label>
            <input type="text" name="cCnpj" id="cCnpj" required>

            <!-- E-mail -->
            <label for="cEmail">E-mail</label>
            <input type="email" name="cEmail" id="cEmail" required>

            <!-- Região Atendida -->
            <label for="cRegiaoAtendida">Região Atendida</label>
            <input type="text" name="cRegiaoAtendida" id="cRegiaoAtendida" required>

            <button type="submit">Adicionar</button>
        </form>
    </div>
</div>

<!-- ==================== POPUP DE ATUALIZAÇÃO ==================== -->
<div class="popup-overlay" id="popup-update-transportadora" style="display:none;">
    <div class="popup">
        <button class="close-btn" onclick="fecharPopup('popup-update-transportadora')">×</button>
        <h2>Atualizar Transportadora</h2>

        <form action="${pageContext.request.contextPath}/transportadora/update" method="post">
            <!-- Nome -->
            <label for="update-transportadora-cNmTransportadora">Nome da Transportadora</label>
            <input type="text" name="cNmTransportadora" id="update-transportadora-cNmTransportadora" required>

            <!-- CNPJ -->
            <label for="update-transportadora-cCnpj">CNPJ</label>
            <input type="text" name="cCnpj" id="update-transportadora-cCnpj" readonly>

            <!-- E-mail -->
            <label for="update-transportadora-cEmail">E-mail</label>
            <input type="email" name="cEmail" id="update-transportadora-cEmail" required>

            <!-- Região Atendida -->
            <label for="update-transportadora-cRegiaoAtendida">Região Atendida</label>
            <input type="text" name="cRegiaoAtendida" id="update-transportadora-cRegiaoAtendida" required>

            <button type="submit">Atualizar</button>
        </form>
    </div>
</div>
