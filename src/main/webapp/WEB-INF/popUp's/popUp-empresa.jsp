<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<!-- INSERT -->
<div class="popup-overlay" id="popup-insert-empresa" style="display:none;">
    <div class="popup">
        <button class="close-btn" onclick="fecharPopup('popup-insert-empresa')">×</button>
        <h2>Cadastrar Empresa</h2>
        <form action="${pageContext.request.contextPath}/empresa/insert" method="post">
            <label>Nome da Empresa</label>
            <input type="text" name="cNmEmpresa" id="cNmEmpresa" required>

            <label>Email</label>
            <input type="text" name="cEmail" id="cEmail" required>

            <label>Senha</label>
            <input type="password" name="cSenha" id="cSenha" required>

            <label>CNPJ</label>
            <input type="text" name="cCnpj" id="cCnpj" required>

            <label>Ativo</label>
            <select name="cAtivo" id="cAtivo" required>
                <option value="1">Ativo</option>
                <option value="0">Inativo</option>
            </select>

            <button type="submit">Adicionar</button>
        </form>
    </div>
</div>

<!-- UPDATE -->
<div class="popup-overlay" id="popup-update-empresa" style="display:none;">
    <div class="popup">
        <button class="close-btn" onclick="fecharPopup('popup-update-empresa')">×</button>
        <h2>Atualizar Empresa</h2>
        <form action="${pageContext.request.contextPath}/empresa/update" method="post">
            <label>Nome da Empresa</label>
            <input type="text" name="cNmEmpresa" id="update-empresa-cNmEmpresa" required>

            <label>Email</label>
            <input type="email" name="cEmail" id="update-empresa-cEmail" readonly>

            <label>Senha</label>
            <input type="password" name="cSenha" id="update-empresa-cSenha" required>

            <label>CNPJ</label>
            <input type="text" name="cCnpj" id="update-empresa-cCnpj" readonly>

            <label>Ativo</label>
            <select name="cAtivo" id="update-empresa-cAtivo" required>
                <option value="1">Ativo</option>
                <option value="0">Inativo</option>
            </select>

            <button type="submit">Atualizar</button>
        </form>
    </div>
</div>
