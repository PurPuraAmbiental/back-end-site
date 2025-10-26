<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<!--
    Por motivos de organização essa pagina guarda os formularios dos popUp's

    responsavel -> CREATE e UPDATE do CRUD
-->
<!-- ========================== POP-UP DE INSERÇÃO ========================== -->
<div class="popup-overlay" id="popup-insert" style="display:none;">
    <div class="popup">
        <!-- Botão para fechar o pop-up -->
        <button class="close-btn" onclick="fecharPopup('popup-insert')">×</button>

        <h2>Cadastrar Endereço</h2>

        <!-- Formulário de inserção de novo endereço -->
        <!-- Os dados serão enviados para o servlet InsertEnderecoEmpresaServlet -->
        <form action="${pageContext.request.contextPath}/endereco-empresa/insert" method="post">

            <label>Bairro</label>
            <input type="text" name="cBairro" maxlength="40" required>

            <label>Logradouro</label>
            <input type="text" name="cLogradouro" maxlength="40" required>

            <label>Estado</label>
            <input type="text" name="cEstado" maxlength="2" required>

            <label>Cidade</label>
            <input type="text" name="cCidade" maxlength="40" required>

            <label>CEP</label>
            <input type="text" name="cCep" maxlength="8" required>

            <label>Complemento</label>
            <input type="text" name="cComplemento" maxlength="20">

            <label>Número</label>
            <input type="number" name="iNrEnderecoEmpresa" minlength="1" required>

            <label>Empresa</label>
            <!-- O nome da empresa é usado para buscar o CNPJ correspondente no banco -->
            <input type="text" name="cNmEmpresa" required>

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
        <!-- Os dados são preenchidos automaticamente pelo JavaScript quando o usuário clica em "Editar" -->
        <form id="updateForm" action="${pageContext.request.contextPath}/endereco-empresa/update" method="post">

            <!-- ID oculto do endereço, usado para identificar o registro -->
            <input type="hidden" name="nCdEnderecoEmpresa" id="upd-id">

            <label>Bairro</label>
            <input type="text" name="cBairro" id="upd-bairro" maxlength="40" required>

            <label>Logradouro</label>
            <input type="text" name="cLogradouro" id="upd-logradouro" maxlength="40" required>

            <label>Estado</label>
            <input type="text" name="cEstado" id="upd-estado" maxlength="2" required>

            <label>Cidade</label>
            <input type="text" name="cCidade" id="upd-cidade" maxlength="40" required>

            <label>CEP</label>
            <input type="text" name="cCep" id="upd-cep" maxlength="8" required>

            <label>Complemento</label>
            <input type="text" name="cComplemento" id="upd-complemento" maxlength="20" required>

            <label>Número</label>
            <input type="number" name="iNrEnderecoEmpresa" id="upd-numero" minlength="1" required>

            <label>Empresa</label>
            <input type="text" name="cNmEmpresa" id="upd-empresa">

            <button type="submit">Atualizar</button>
        </form>
    </div>
</div>
