<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<!--
    Esta página contém os formulários usados nos pop-ups para CRUD de residuos:
    - Inserção
    - Atualização
    - Exclusão
    Também inclui o filtro para busca de empresas.
    O JavaScript utilizado nos botões serve para abrir/fechar pop-ups e alterar o texto dos botões durante o envio do formulário.
-->

<!-- ==================== POPUP DE INSERÇÃO ==================== -->
<div class="popup-overlay" id="popup-insert-residuo" style="display:none;">
    <div class="popup">
        <!-- Botao para fechar pop up-->
        <button class="close-btn" onclick="fecharPopup('popup-insert-residuo')">×</button>
        <h2>Cadastrar Resíduo</h2>

        <!-- restrição no botão para que o usuário não consiga chamar o servlet várias vezes -->
        <form action="${pageContext.request.contextPath}/residuo/insert"
              method="post"
              onsubmit="const btn = this.querySelector('button[type=submit]'); btn.disabled = true; btn.innerText = 'Adicionando...';">

            <!-- Tipo do Resíduo -->
            <label for="cNmResiduo">Tipo do Resíduo</label>
            <input type="text" name="cNmResiduo" id="cNmResiduo" maxlength="30" required>

            <!-- Unidade de Medida -->
            <label for="cTipoUnidade">Unidade de Medida</label>
            <select name="cTipoUnidade" id="cTipoUnidade" required>
                <option value="kg">Kilograma (kg)</option>
                <option value="t">Tonelada (t)</option>
                <option value="kg/m³">Kg/m³</option>
                <option value="g">Gramas (g)</option>
            </select>

            <!-- Preço -->
            <label for="nPrecoPadrao">Preço</label>
            <input type="number" name="nPrecoPadrao" id="nPrecoPadrao" minlength="0.1" step="0.01" required>

            <!-- Volume -->
            <label for="nVolumePadrao">Volume</label>
            <input type="number" name="nVolumePadrao" id="nVolumePadrao" minlength="0.1" step="0.01" required>

            <!-- Categoria -->
            <label for="cCategoria">Categoria</label>
            <input type="text" name="cCategoria" id="cCategoria" maxlength="30" required>

            <!-- Empresa -->
            <label for="cNmEmpresa">Empresa</label>
            <input type="text" name="cNmEmpresa" id="cNmEmpresa" maxlength="30" required>

            <!-- Descrição -->
            <label for="cDescricao">Descrição</label>
            <input type="text" name="cDescricao" id="cDescricao" required>

            <button type="submit">Adicionar</button>
        </form>
    </div>
</div>

<!-- ==================== POPUP DE ATUALIZAÇÃO ==================== -->
<div class="popup-overlay" id="popup-update-residuo" style="display:none;">
    <div class="popup">
        <!-- Botao para fechar pop up-->
        <button class="close-btn" onclick="fecharPopup('popup-update-residuo')">×</button>
        <h2>Atualizar Resíduo</h2>

        <!-- restrição no botão para que o usuário não consiga chamar o servlet várias vezes -->
        <form action="${pageContext.request.contextPath}/residuo/update"
              method="post"
              onsubmit="const btn = this.querySelector('button[type=submit]'); btn.disabled = true; btn.innerText = 'Atualizando...';">

            <!-- Código do Resíduo (oculto) -->
            <input type="hidden" name="nCdResiduo" id="update-residuo-nCdResiduo">

            <!-- Tipo do Resíduo -->
            <label for="update-residuo-cNmResiduo">Tipo do Resíduo</label>
            <input type="text" name="cNmResiduo" id="update-residuo-cNmResiduo" maxlength="30" required>

            <!-- Unidade de Medida -->
            <label for="update-residuo-cTipoUnidade">Unidade de Medida</label>
            <select name="cTipoUnidade" id="update-residuo-cTipoUnidade" required>
                <option value="kg">Kilograma (kg)</option>
                <option value="t">Tonelada (t)</option>
                <option value="kg/m³">Kg/m³</option>
                <option value="g">Gramas (g)</option>
            </select>

            <!-- Preço -->
            <label for="update-residuo-nPrecoPadrao">Preço</label>
            <input type="text" name="nPrecoPadrao" id="update-residuo-nPrecoPadrao" minlength="0.1" step="0.01" required>

            <!-- Volume -->
            <label for="update-residuo-nVolumePadrao">Volume</label>
            <input type="text" name="nVolumePadrao" id="update-residuo-nVolumePadrao" minlength="0.1" step="0.01" required>

            <!-- Categoria -->
            <label for="update-residuo-cCategoria">Categoria</label>
            <input type="text" name="cCategoria" id="update-residuo-cCategoria" maxlength="30" required>

            <!-- Empresa -->
            <label for="update-residuo-cNmEmpresa">Empresa</label>
            <input type="text" name="cNmEmpresa" id="update-residuo-cNmEmpresa" maxlength="30" required>

            <!-- Descrição -->
            <label for="update-residuo-cDescricao">Descrição</label>
            <input type="text" name="cDescricao" id="update-residuo-cDescricao" required>

            <button type="submit">Atualizar</button>
        </form>
    </div>
</div>

<!-- ==================== POPUP DE EXCLUSÃO ==================== -->
<div class="popup-overlay" id="popup-delete-residuo" style="display:none;">
    <div class="popup">
        <!-- Botao para fechar pop up-->
        <button class="close-btn" onclick="fecharPopup('popup-delete-residuo')">×</button>
        <h2>Excluir Resíduo</h2>

        <!-- restrição no botão para que o usuário não consiga chamar o servlet várias vezes -->
        <form action="${pageContext.request.contextPath}/residuo/delete" method="post">
            <p>Tem certeza que deseja excluir o resíduo <strong id="delete-residuo-nome"></strong>?</p>

            <input type="hidden" name="nCdResiduo" id="delete-residuo-nCdResiduo">

            <div class="botoes-principais">
                <button type="submit" onclick="setTimeout(() => this.disabled = true, 0); this.innerText = 'Excluindo...';">Excluir</button>
                <button type="button" class="btn-cancelar" onclick="fecharPopup('popup-delete-residuo')">
                    Cancelar
                </button>
            </div>
        </form>
    </div>
</div>

<!-- ==================== FILTROS ========================= -->
<div class="filtroPopup-overlay" id="filtroResiduo" style="display:none;">
    <div class="popup" id="filtroPopup">
        <h2>Filtrar Resíduo</h2>
        <!-- Botao para fechar pop up-->
        <button class="close-btn" onclick="fecharPopup('filtroResiduo')">×</button>

        <!-- restrição no botão para que o usuário não consiga chamar o servlet várias vezes -->
        <form action="<%=request.getContextPath()%>/residuo/list" method="get">

            <!-- Filtro por preço mínimo -->
            <label for="precoMin">Preço Mínimo</label><br>
            <input type="number" step="0.01" min="0" name="precoMin" id="precoMin"
                   placeholder="Digite o preço mínimo" value="<%= request.getParameter("precoMin") != null ? request.getParameter("precoMin") : "" %>"><br>

            <!-- Filtro por preço máximo -->
            <label for="precoMax">Preço Máximo</label><br>
            <input type="number" step="0.01" min="0" name="precoMax" id="precoMax"
                   placeholder="Digite o preço máximo" value="<%= request.getParameter("precoMax") != null ? request.getParameter("precoMax") : "" %>"><br>

            <!-- Filtro por volume mínimo -->
            <label for="volumeMin">Volume Mínimo</label><br>
            <input type="number" step="0.01" min="0" name="volumeMin" id="volumeMin"
                   placeholder="Digite o volume mínimo" value="<%= request.getParameter("volumeMin") != null ? request.getParameter("volumeMin") : "" %>"><br>

            <!-- Filtro por volume máximo -->
            <label for="volumeMax">Volume Máximo</label><br>
            <input type="number" step="0.01" min="0" name="volumeMax" id="volumeMax"
                   placeholder="Digite o volume máximo" value="<%= request.getParameter("volumeMax") != null ? request.getParameter("volumeMax") : "" %>"><br>

            <!-- Filtro por unidade de medida -->
            <label for="cTipoUnidade">Selecione a unidade de medida</label><br>
            <select name="unidade" id="cTipoUnidade" value="<%= request.getParameter("unidade") != null ? request.getParameter("unidade") : "" %>">
                <option value="">-- Todas --</option>
                <option value="kg">Kilograma (kg)</option>
                <option value="t">Tonelada (t)</option>
                <option value="kg/m³">Kg/m³</option>
                <option value="g">Gramas (g)</option>
            </select><br>

            <button type="submit" class="add-btn">Filtrar</button>
        </form>
    </div>
</div>
