<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<!-- POPUP INSERT -->
<div class="popup-overlay" id="popup-insert-residuo" style="display:none;">
    <div class="popup">
        <button class="close-btn" onclick="fecharPopup('popup-insert-residuo')">×</button>
        <h2>Cadastrar Resíduo</h2>

        <form action="${pageContext.request.contextPath}/residuo/insert" method="post">
            <label>Tipo do Resíduo</label>
            <input type="text" name="cNmResiduo" id="cNmResiduo" maxlength="30" required>

            <label>Unidade de Medida</label>
            <select name="cTipoUnidade" id="cTipoUnidade" required>
                <option value="kg">Kilograma (kg)</option>
                <option value="t">Tonelada (t)</option>
                <option value="kg/m³">Kg/m³</option>
                <option value="g">Gramas (g)</option>
            </select>

            <label>Preço</label>
            <input type="number" name="nPrecoPadrao" id="nPrecoPadrao" minlength="0.1" step="0.01" required>

            <label>Volume</label>
            <input type="number" name="nVolumePadrao" id="nVolumePadrao" minlength="0.1" step="0.01" required>

            <label>Categoria</label>
            <input type="text" name="cCategoria" id="cCategoria" maxlength="30" required>

            <label>Empresa</label>
            <input type="text" name="cNmEmpresa" id="cNmEmpresa" maxlength="30" required>

            <label>Descrição</label>
            <input type="text" name="cDescricao" id="cDescricao" required>

            <button type="submit">Adicionar</button>
        </form>
    </div>
</div>

<!-- POPUP UPDATE -->
<div class="popup-overlay" id="popup-update-residuo" style="display:none;">
    <div class="popup">
        <button class="close-btn" onclick="fecharPopup('popup-update-residuo')">×</button>
        <h2>Atualizar Resíduo</h2>

        <form action="${pageContext.request.contextPath}/residuo/update" method="post">
            <input type="hidden" name="nCdResiduo" id="update-residuo-nCdResiduo">

            <label>Tipo do Resíduo</label>
            <input type="text" name="cNmResiduo" id="update-residuo-cNmResiduo" maxlength="30" required>

            <label>Unidade de Medida</label>
            <select name="cTipoUnidade" id="update-residuo-cTipoUnidade" required>
                <option value="kg">Kilograma (kg)</option>
                <option value="t">Tonelada (t)</option>
                <option value="kg/m³">Kg/m³</option>
                <option value="g">Gramas (g)</option>
            </select>

            <label>Preço</label>
            <input type="text" name="nPrecoPadrao" id="update-residuo-nPrecoPadrao" minlength="0.1" step="0.01" required>

            <label>Volume</label>
            <input type="text" name="nVolumePadrao" id="update-residuo-nVolumePadrao" minlength="0.1" step="0.01" required>

            <label>Categoria</label>
            <input type="text" name="cCategoria" id="update-residuo-cCategoria" maxlength="30" required>

            <label>Empresa</label>
            <input type="text" name="cNmEmpresa" id="update-residuo-cNmEmpresa" maxlength="30" required>

            <label>Descrição</label>
            <input type="text" name="cDescricao" id="update-residuo-cDescricao" required>

            <button type="submit">Atualizar</button>
        </form>
    </div>
</div>
