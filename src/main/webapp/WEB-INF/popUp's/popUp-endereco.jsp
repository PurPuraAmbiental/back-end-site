<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<!--
Por motivos de organização, esta página guarda os formulários dos pop-ups
Responsável -> CREATE e UPDATE do CRUD
-->

<!-- ========================== POP-UP DE INSERÇÃO ========================== -->
<div class="popup-overlay" id="popup-insert" style="display:none;">
    <div class="popup">
        <!-- Botão para fechar o pop-up -->
        <button class="close-btn" onclick="fecharPopup('popup-insert')">×</button>

        <h2>Cadastrar Endereço</h2>

        <!-- Formulário de inserção de novo endereço -->
        <form action="${pageContext.request.contextPath}/endereco-empresa/insert" method="post">

            <label for="cBairro">Bairro</label>
            <input type="text" name="cBairro" id="cBairro" maxlength="40" required>

            <label for="cLogradouro">Logradouro</label>
            <input type="text" name="cLogradouro" id="cLogradouro" maxlength="40" required>

            <label for="cEstado">Estado</label>
            <input type="text" name="cEstado" id="cEstado" maxlength="2" required>

            <label for="cCidade">Cidade</label>
            <input type="text" name="cCidade" id="cCidade" maxlength="40" required>

            <label for="cCep">CEP</label>
            <input type="text" name="cCep" id="cCep" maxlength="8" required>

            <label for="cComplemento">Complemento</label>
            <input type="text" name="cComplemento" id="cComplemento" maxlength="20">

            <label for="iNrEnderecoEmpresa">Número</label>
            <input type="number" name="iNrEnderecoEmpresa" id="iNrEnderecoEmpresa" minlength="1" required>

            <label for="cNmEmpresa">Empresa</label>
            <input type="text" name="cNmEmpresa" id="cNmEmpresa" required>

            <button type="submit">Adicionar</button>
        </form>
    </div>
</div>

<!-- ========================== POP-UP DE ATUALIZAÇÃO ========================== -->
<div class="popup-overlay" id="popup-update" style="display:none;">
    <div class="popup">
        <!-- Botão para fechar o pop-up -->
        <button class="close-btn" onclick="fecharPopup('popup-update')">×</button>

        <h2>Atualizar Endereço</h2>

        <!-- Formulário de atualização -->
        <form id="updateForm" action="${pageContext.request.contextPath}/endereco-empresa/update" method="post">

            <!-- ID oculto do endereço, usado para identificar o registro -->
            <input type="hidden" name="nCdEnderecoEmpresa" id="upd-id">

            <label for="upd-bairro">Bairro</label>
            <input type="text" name="cBairro" id="upd-bairro" maxlength="40" required>

            <label for="upd-logradouro">Logradouro</label>
            <input type="text" name="cLogradouro" id="upd-logradouro" maxlength="40" required>

            <label for="upd-estado">Estado</label>
            <input type="text" name="cEstado" id="upd-estado" maxlength="2" required>

            <label for="upd-cidade">Cidade</label>
            <input type="text" name="cCidade" id="upd-cidade" maxlength="40" required>

            <label for="upd-cep">CEP</label>
            <input type="text" name="cCep" id="upd-cep" maxlength="8" required>

            <label for="upd-complemento">Complemento</label>
            <input type="text" name="cComplemento" id="upd-complemento" maxlength="20" required>

            <label for="upd-numero">Número</label>
            <input type="number" name="iNrEnderecoEmpresa" id="upd-numero" minlength="1" required>

            <label for="upd-empresa">Empresa</label>
            <input type="text" name="cNmEmpresa" id="upd-empresa" required>

            <button type="submit">Atualizar</button>
        </form>
    </div>
</div>
