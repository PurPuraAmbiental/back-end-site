<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<!--
Por motivos de organização, esta página guarda os formulários dos pop-ups
Responsável -> CREATE e UPDATE do CRUD
-->
<!-- ==================== POPUP DE INSERÇÃO ==================== -->
<div class="popup-overlay" id="popup-insert-empresa" style="display:none;">
    <div class="popup">
        <button class="close-btn" onclick="fecharPopup('popup-insert-empresa')">×</button>
        <h2>Cadastrar Empresa</h2>
        <form action="${pageContext.request.contextPath}/empresa/insert" method="post">
            <label>Nome da Empresa</label>
            <input type="text" name="cNmEmpresa" id="cNmEmpresa" maxlength="30" placeholder="Insira o nome da empresa" required>

            <label>Email</label>
            <input type="text" name="cEmail" id="cEmail" maxlength="70" placeholder="Ex: Joao@gmail.com" required>

            <label>Senha</label>
            <input type="password" name="cSenha" id="cSenha" maxlength="70" placeholder="Digite até 6 caracteres" required>

            <label>CNPJ</label>
            <input type="text" name="cCnpj" id="cCnpj" maxlength="18" placeholder="Insira o cnpj da empresa" required>

            <label>Ativo</label>
            <select name="cAtivo" id="cAtivo" required>
                <option value="1">Ativo</option>
                <option value="0">Inativo</option>
            </select>

            <button type="submit">Adicionar</button>
        </form>
    </div>
</div>

<!-- ==================== POPUP DE ATUALIZAÇÃO ==================== -->
<div class="popup-overlay" id="popup-update-empresa" style="display:none;">
    <div class="popup">
        <button class="close-btn" onclick="fecharPopup('popup-update-empresa')">×</button>
        <h2>Atualizar Empresa</h2>
        <form action="${pageContext.request.contextPath}/empresa/update" method="post">
            <label>Nome da Empresa</label>
            <input type="text" name="cNmEmpresa" id="update-empresa-cNmEmpresa" maxlength="30" required>

            <label>Email</label>
            <input type="email" name="cEmail" id="update-empresa-cEmail" style="color: gray" readonly>

            <label>Senha</label>
            <input type="password" name="cSenha" id="update-empresa-cSenha" maxlength="70" required>

            <label>CNPJ</label>
            <input type="text" name="cCnpj" id="update-empresa-cCnpj" style="color: gray" maxlength="18" readonly>

            <label>Ativo</label>
            <select name="cAtivo" id="update-empresa-cAtivo" required>
                <option value="1">Ativo</option>
                <option value="0">Inativo</option>
            </select>

            <button type="submit">Atualizar</button>
        </form>
    </div>
</div>
<!-- ==================== POPUP DE EXCLUSÃO ==================== -->
<div class="popup-overlay" id="popup-delete-empresa" style="display:none;">
    <div class="popup">
        <button class="close-btn" onclick="fecharPopup('popup-delete-empresa')">×</button>
        <h2>Excluir Empresa</h2>

        <form action="${pageContext.request.contextPath}/empresa/delete" method="post">
            <p>Tem certeza que deseja excluir a empresa <strong id="delete-empresa-nome"></strong>?</p>

            <input type="hidden" name="cCnpj" id="delete-empresa-cCnpj">

            <div class="botoes-principais">
                <button type="submit"  onclick="setTimeout(() => this.disabled = true, 0); this.innerText = 'Excluindo...';" >Excluir</button>
                <button type="button" class="btn-cancelar" onclick="fecharPopup('popup-delete-empresa')">
                    Cancelar
                </button>
            </div>
        </form>
    </div>
</div>



<!-- ==================== FILTROS ========================= -->
<div class="filtroPopup-overlay" id="filtroEmpresa" style="display:none;">
    <div class="popup" id="filtroPopup">
            <h2>Filtrar Empresa</h2>
            <button class="close-btn" onclick="fecharPopup('filtroEmpresa')">×</button>
            <form action="${pageContext.request.contextPath}/empresa/list" method="get">
                <label for="nome">Nome:</label>
                <input type="text" name="nome" class="input-filtro" placeholder="Nome da empresa" value="<%= request.getParameter("nome") != null ? request.getParameter("nome") : "" %>">

                <label for="cnpj">CNPJ:</label>
                <input type="text" name="cnpj" class="input-filtro" placeholder="CNPJ" value="<%= request.getParameter("cnpj") != null ? request.getParameter("cnpj") : "" %>">

                <label for="email">Email:</label>
                <input type="text" name="email" class="input-filtro" placeholder="Digite o email" value="<%= request.getParameter("email") != null ? request.getParameter("email") : "" %>">
                <br>
                <label for="ativo">Status:</label>
                <select name="ativo">
                    <option value="" <%= request.getParameter("ativo") == null ? "selected" : "" %>>Todos</option>
                    <option value="1" <%= "1".equals(request.getParameter("ativo")) ? "selected" : "" %>>Ativas</option>
                    <option value="0" <%= "0".equals(request.getParameter("ativo")) ? "selected" : "" %>>Inativas</option>
                </select>
                <br>
                <label for="temResiduo">Resíduos:</label>
                <select name="temResiduo">
                    <option value="" <%= request.getParameter("temResiduo") == null ? "selected" : "" %>>Todos</option>
                    <option value="1" <%= "1".equals(request.getParameter("temResiduo")) ? "selected" : "" %>>Pelo menos um resíduo</option>
                </select>
                <button type="submit" class="add-btn">Filtrar</button>
            </form>
    </div>
</div>
