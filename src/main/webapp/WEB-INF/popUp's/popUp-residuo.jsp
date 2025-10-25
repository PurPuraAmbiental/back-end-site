<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<!-- POPUP INSERT -->
<div class="popup-overlay" id="popup-insert-residuo" style="display:none;">
    <div class="popup">
        <button class="close-btn" onclick="fecharPopup('popup-insert-residuo')">×</button>
        <h2>Cadastrar Resíduo</h2>

        <form action="${pageContext.request.contextPath}/residuo/insert" method="post">
            <label>Tipo do Resíduo</label>
            <input type="text" name="cNmResiduo" id="cNmResiduo" required>

            <label>Unidade de Medida</label>
            <select name="cTipoUnidade" id="cTipoUnidade" required>
                <option value="kg">Kilograma (kg)</option>
                <option value="t">Tonelada (t)</option>
                <option value="kg/m³">Kg/m³</option>
                <option value="g">Gramas (g)</option>
            </select>

            <label>Preço</label>
            <input type="text" name="nPrecoPadrao" id="nPrecoPadrao" required>

            <label>Volume</label>
            <input type="text" name="nVolumePadrao" id="nVolumePadrao" required>

            <label>Categoria</label>
            <input type="text" name="cCategoria" id="cCategoria" required>

            <label>Empresa</label>
            <input type="text" name="cNmEmpresa" id="cNmEmpresa" required>

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
            <input type="hidden" name="nCdResiduo" id="nCdResiduo">

            <label>Tipo do Resíduo</label>
            <input type="text" name="cNmResiduo" id="cNmResiduo" required>

            <label>Unidade de Medida</label>
            <select name="cTipoUnidade" id="cTipoUnidade" required>
                <option value="kg">Kilograma (kg)</option>
                <option value="t">Tonelada (t)</option>
                <option value="kg/m³">Kg/m³</option>
                <option value="g">Gramas (g)</option>
            </select>

            <label>Preço</label>
            <input type="text" name="nPrecoPadrao" id="nPrecoPadrao" required>

            <label>Volume</label>
            <input type="text" name="nVolumePadrao" id="nVolumePadrao" required>

            <label>Categoria</label>
            <input type="text" name="cCategoria" id="cCategoria" required>

            <label>Empresa</label>
            <input type="text" name="cNmEmpresa" id="cNmEmpresa" required>

            <label>Descrição</label>
            <input type="text" name="cDescricao" id="cDescricao" required>

            <button type="submit">Atualizar</button>
        </form>
    </div>
</div>
