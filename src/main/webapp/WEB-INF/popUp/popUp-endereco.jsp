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
        <form action="${pageContext.request.contextPath}/endereco-empresa/insert"
              method="post"
              onsubmit="const btn = this.querySelector('button[type=submit]'); btn.disabled = true; btn.innerText = 'Adicionando...';">

        <label for="cBairro">Bairro</label>
            <input type="text" name="cBairro" id="cBairro" maxlength="40" required>

            <label for="cLogradouro">Logradouro</label>
            <input type="text" name="cLogradouro" id="cLogradouro" maxlength="40" required>

            <label for="cEstado">Estado</label>
            <select name="cEstado" id="cEstado">
                <option value="">Selecione um estado</option>
                <option value="AC">Acre</option>
                <option value="AL">Alagoas</option>
                <option value="AP">Amapá</option>
                <option value="AM">Amazonas</option>
                <option value="BA">Bahia</option>
                <option value="CE">Ceará</option>
                <option value="DF">Distrito Federal</option>
                <option value="ES">Espírito Santo</option>
                <option value="GO">Goiás</option>
                <option value="MA">Maranhão</option>
                <option value="MT">Mato Grosso</option>
                <option value="MS">Mato Grosso do Sul</option>
                <option value="MG">Minas Gerais</option>
                <option value="PA">Pará</option>
                <option value="PB">Paraíba</option>
                <option value="PR">Paraná</option>
                <option value="PE">Pernambuco</option>
                <option value="PI">Piauí</option>
                <option value="RJ">Rio de Janeiro</option>
                <option value="RN">Rio Grande do Norte</option>
                <option value="RS">Rio Grande do Sul</option>
                <option value="RO">Rondônia</option>
                <option value="RR">Roraima</option>
                <option value="SC">Santa Catarina</option>
                <option value="SP">São Paulo</option>
                <option value="SE">Sergipe</option>
                <option value="TO">Tocantins</option>
            </select>

            <label for="cCidade">Cidade</label>
            <input type="text" name="cCidade" id="cCidade" maxlength="40" required>

            <label for="cCep">CEP</label>
            <input type="text" name="cCep" id="cCep" maxlength="9" required>

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
        <form id="updateForm" action="${pageContext.request.contextPath}/endereco-empresa/update"
              method="post"
              onsubmit="const btn = this.querySelector('button[type=submit]'); btn.disabled = true; btn.innerText = 'Atualizando...';">


        <!-- ID oculto do endereço, usado para identificar o registro -->
            <input type="hidden" name="nCdEnderecoEmpresa" id="upd-nCdEnderecoEmpresa">

            <label for="upd-bairro">Bairro</label>
            <input type="text" name="cBairro" id="upd-bairro" maxlength="40" required>

            <label for="upd-logradouro">Logradouro</label>
            <input type="text" name="cLogradouro" id="upd-logradouro" maxlength="40" required>

            <label for="upd-estado">Estado</label>
            <select name="cEstado" id="upd-estado">
                <option value="">Selecione um estado</option>
                <option value="AC">Acre</option>
                <option value="AL">Alagoas</option>
                <option value="AP">Amapá</option>
                <option value="AM">Amazonas</option>
                <option value="BA">Bahia</option>
                <option value="CE">Ceará</option>
                <option value="DF">Distrito Federal</option>
                <option value="ES">Espírito Santo</option>
                <option value="GO">Goiás</option>
                <option value="MA">Maranhão</option>
                <option value="MT">Mato Grosso</option>
                <option value="MS">Mato Grosso do Sul</option>
                <option value="MG">Minas Gerais</option>
                <option value="PA">Pará</option>
                <option value="PB">Paraíba</option>
                <option value="PR">Paraná</option>
                <option value="PE">Pernambuco</option>
                <option value="PI">Piauí</option>
                <option value="RJ">Rio de Janeiro</option>
                <option value="RN">Rio Grande do Norte</option>
                <option value="RS">Rio Grande do Sul</option>
                <option value="RO">Rondônia</option>
                <option value="RR">Roraima</option>
                <option value="SC">Santa Catarina</option>
                <option value="SP">São Paulo</option>
                <option value="SE">Sergipe</option>
                <option value="TO">Tocantins</option>
            </select>

            <label for="upd-cidade">Cidade</label>
            <input type="text" name="cCidade" id="upd-cidade" maxlength="40" required>

            <label for="upd-cep">CEP</label>
            <input type="text" name="cCep" id="upd-cep" maxlength="9" required>

            <label for="upd-complemento">Complemento</label>
            <input type="text" name="cComplemento" id="upd-complemento" maxlength="20" >

            <label for="upd-numero">Número</label>
            <input type="number" name="iNrEnderecoEmpresa" id="upd-numero" minlength="1" required>

            <label for="upd-empresa">Empresa</label>
            <input type="text" name="cNmEmpresa" id="upd-empresa" required>

            <button type="submit">Atualizar</button>
        </form>
    </div>
</div>
<!-- ==================== POPUP DE EXCLUSÃO ==================== -->
<div class="popup-overlay" id="popup-delete-endereco-empresa" style="display:none;">
    <div class="popup">
        <button class="close-btn" onclick="fecharPopup('popup-delete-endereco-empresa')">×</button>
        <h2>Excluir Endereco</h2>

        <form action="${pageContext.request.contextPath}/endereco-empresa/delete" method="post">
            <p>Tem certeza que deseja excluir o endereco <strong id="delete-endereco-empresa-nome"></strong>?</p>

            <input type="hidden" name="nCdEnderecoEmpresa" id="delete-endereco-empresa-nCdEndereco">

            <div class="botoes-principais">
                <button type="submit"  onclick="setTimeout(() => this.disabled = true, 0); this.innerText = 'Excluindo...';" >Excluir</button>
                <button type="button" class="btn-cancelar" onclick="fecharPopup('popup-delete-endereco-empresa')">
                    Cancelar
                </button>
            </div>
        </form>
    </div>
</div>

<div class="filtroPopup-overlay" id="filtroEnderecoEmpresa" style="display:none;">
    <div class="popup" id="filtroPopup">
        <div class="filtro">
            <h2>Filtrar Endereço</h2>
            <button class="close-btn" onclick="fecharPopup('filtroEnderecoEmpresa')">×</button>
            <form method="get" action="<%=request.getContextPath()%>/endereco-empresa/list">
                <label for="estado">Selecione o estado desejado</label><br>
                <select name="estado" id="estado">
                    <option value="" <%= request.getParameter("estado") == null || request.getParameter("estado").isEmpty() ? "selected" : "" %>>Selecione um estado</option>
                    <option value="AC" <%= "AC".equals(request.getParameter("estado")) ? "selected" : "" %>>Acre</option>
                    <option value="AL" <%= "AL".equals(request.getParameter("estado")) ? "selected" : "" %>>Alagoas</option>
                    <option value="AP" <%= "AP".equals(request.getParameter("estado")) ? "selected" : "" %>>Amapá</option>
                    <option value="AM" <%= "AM".equals(request.getParameter("estado")) ? "selected" : "" %>>Amazonas</option>
                    <option value="BA" <%= "BA".equals(request.getParameter("estado")) ? "selected" : "" %>>Bahia</option>
                    <option value="CE" <%= "CE".equals(request.getParameter("estado")) ? "selected" : "" %>>Ceará</option>
                    <option value="DF" <%= "DF".equals(request.getParameter("estado")) ? "selected" : "" %>>Distrito Federal</option>
                    <option value="ES" <%= "ES".equals(request.getParameter("estado")) ? "selected" : "" %>>Espírito Santo</option>
                    <option value="GO" <%= "GO".equals(request.getParameter("estado")) ? "selected" : "" %>>Goiás</option>
                    <option value="MA" <%= "MA".equals(request.getParameter("estado")) ? "selected" : "" %>>Maranhão</option>
                    <option value="MT" <%= "MT".equals(request.getParameter("estado")) ? "selected" : "" %>>Mato Grosso</option>
                    <option value="MS" <%= "MS".equals(request.getParameter("estado")) ? "selected" : "" %>>Mato Grosso do Sul</option>
                    <option value="MG" <%= "MG".equals(request.getParameter("estado")) ? "selected" : "" %>>Minas Gerais</option>
                    <option value="PA" <%= "PA".equals(request.getParameter("estado")) ? "selected" : "" %>>Pará</option>
                    <option value="PB" <%= "PB".equals(request.getParameter("estado")) ? "selected" : "" %>>Paraíba</option>
                    <option value="PR" <%= "PR".equals(request.getParameter("estado")) ? "selected" : "" %>>Paraná</option>
                    <option value="PE" <%= "PE".equals(request.getParameter("estado")) ? "selected" : "" %>>Pernambuco</option>
                    <option value="PI" <%= "PI".equals(request.getParameter("estado")) ? "selected" : "" %>>Piauí</option>
                    <option value="RJ" <%= "RJ".equals(request.getParameter("estado")) ? "selected" : "" %>>Rio de Janeiro</option>
                    <option value="RN" <%= "RN".equals(request.getParameter("estado")) ? "selected" : "" %>>Rio Grande do Norte</option>
                    <option value="RS" <%= "RS".equals(request.getParameter("estado")) ? "selected" : "" %>>Rio Grande do Sul</option>
                    <option value="RO" <%= "RO".equals(request.getParameter("estado")) ? "selected" : "" %>>Rondônia</option>
                    <option value="RR" <%= "RR".equals(request.getParameter("estado")) ? "selected" : "" %>>Roraima</option>
                    <option value="SC" <%= "SC".equals(request.getParameter("estado")) ? "selected" : "" %>>Santa Catarina</option>
                    <option value="SP" <%= "SP".equals(request.getParameter("estado")) ? "selected" : "" %>>São Paulo</option>
                    <option value="SE" <%= "SE".equals(request.getParameter("estado")) ? "selected" : "" %>>Sergipe</option>
                    <option value="TO" <%= "TO".equals(request.getParameter("estado")) ? "selected" : "" %>>Tocantins</option>
                </select>


                <label for="nomeEmpresa">Insira o nome da empresa</label><br>
                <input type="text" id="nomeEmpresa" name="nomeEmpresa" placeholder="Digite o nome da empresa" value="<%= request.getParameter("nomeEmpresa") != null ? request.getParameter("nomeEmpresa") : "" %>">


                <button type="submit" class="add-btn">Filtrar</button>
            </form>
            <br>
            <br>
        </div>
    </div>
</div>