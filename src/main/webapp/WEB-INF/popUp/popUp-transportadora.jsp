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

        <form action="${pageContext.request.contextPath}/transportadora/insert"
              method="post"
              onsubmit="const btn = this.querySelector('button[type=submit]'); btn.disabled = true; btn.innerText = 'Adicionando...';">

            <!-- Nome -->
            <label for="cNmTransportadora">Nome da Transportadora</label>
            <input type="text" name="cNmTransportadora" id="cNmTransportadora" maxlength="50" required>

            <!-- CNPJ -->
            <label for="cCnpj">CNPJ</label>
            <input type="text" name="cCnpj" id="cCnpj" required>

            <!-- E-mail -->
            <label for="cEmail">E-mail</label>
            <input type="email" name="cEmail" id="cEmail" maxlength="50" required>

            <!-- Região Atendida -->
            <label for="cRegiaoAtendida">Região Atendida</label>
            <input type="text" name="cRegiaoAtendida" id="cRegiaoAtendida" maxlength="50" required>

            <button type="submit">Adicionar</button>
        </form>

    </div>
</div>

<!-- ==================== POPUP DE ATUALIZAÇÃO ==================== -->
<div class="popup-overlay" id="popup-update-transportadora" style="display:none;">
    <div class="popup">
        <button class="close-btn" onclick="fecharPopup('popup-update-transportadora')">×</button>
        <h2>Atualizar Transportadora</h2>

        <form action="${pageContext.request.contextPath}/transportadora/update"
              method="post"
              onsubmit="const btn = this.querySelector('button[type=submit]'); btn.disabled = true; btn.innerText = 'Atualizando...';">

            <!-- Nome -->
            <label for="update-transportadora-cNmTransportadora">Nome da Transportadora</label>
            <input type="text" name="cNmTransportadora" id="update-transportadora-cNmTransportadora" maxlength="50" required>

            <!-- CNPJ -->
            <label for="update-transportadora-cCnpj">CNPJ</label>
            <input type="text" name="cCnpj" id="update-transportadora-cCnpj" style="color: gray" readonly>

            <!-- E-mail -->
            <label for="update-transportadora-cEmail">E-mail</label>
            <input type="email" name="cEmail" id="update-transportadora-cEmail" maxlength="50" required>

            <!-- Região Atendida -->
            <label for="update-transportadora-cRegiaoAtendida">Região Atendida</label>
            <input type="text" name="cRegiaoAtendida" id="update-transportadora-cRegiaoAtendida" maxlength="50" required>

            <button type="submit">Atualizar</button>
        </form>
    </div>
</div>
    <!-- ==================== FILTROS ========================= -->
<div class="filtroPopup-overlay" id="filtroTransportadora" style="display:none;">
    <div class="popup" id="filtroPopup">
        <h2>Filtrar Transportadora</h2>
        <button class="close-btn" onclick="fecharPopup('filtroTransportadora')">×</button>
        <form action="<%=request.getContextPath()%>/transportadora/list" method="get">
            <label for="nomeTransportadora">Insira o nome da transportadora</label>
            <input type="text" id="nomeTransportadora" name="nomeTransportadora" placeholder="Digite o nome da transportadora">
            <br>
            <label for="regiao">Insira a região</label>
            <input type="text" id="regiao" name="regiao" placeholder="Ache transportadora pertinho de você">
            <br>
            <button type="submit" class="add-btn"> Filtrar </button>
        </form>
    </div>
</div>