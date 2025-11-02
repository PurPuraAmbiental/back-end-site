<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<!--
Por motivos de organização, esta página guarda os formulários dos pop-ups
Responsável -> CREATE e UPDATE do CRUD de Telefone
-->

<!-- ==================== POPUP DE INSERÇÃO ==================== -->
<div class="popup-overlay" id="popup-insert-telefone" style="display:none;">
    <div class="popup">
        <!-- botao para cancelar o popUp-->
        <button class="close-btn" onclick="fecharPopup('popup-insert-telefone')">×</button>
        <h2>Cadastrar Telefone</h2>

        <form action="${pageContext.request.contextPath}/telefone/insert"
              method="post"
              onsubmit="this.querySelector('button').disabled = true; this.querySelector('button').innerText = 'Adicionando...';">
            <label for="cNmEmpresa">Empresa</label>
            <input type="text" name="cNmEmpresa" id="cNmEmpresa" required>

            <label for="cNrTelefone">Telefone</label>
            <input type="text" name="cNrTelefone" id="cNrTelefone" required>

            <label for="cDescricao">Descrição</label>
            <input type="text" name="cDescricao" id="cDescricao" maxlength="30" required>

            <button type="submit">Adicionar</button>
        </form>
    </div>
</div>

<!-- ==================== POPUP DE ATUALIZAÇÃO ==================== -->
<div class="popup-overlay" id="popup-update-telefone" style="display:none;">
    <div class="popup">
        <button class="close-btn" onclick="fecharPopup('popup-update-telefone')">×</button>
        <h2>Atualizar Telefone</h2>

        <form action="${pageContext.request.contextPath}/telefone/update"
              method="post"
              onsubmit="const btn = this.querySelector('button[type=submit]'); btn.disabled = true; btn.innerText = 'Atualizando...';">

            <!-- Código do Telefone (oculto) -->
            <input type="hidden" name="nCdTelefone" id="update-telefone-nCdTelefone">

            <!-- Empresa -->
            <label for="update-telefone-cNmEmpresa">Empresa</label>
            <input type="text" name="cNmEmpresa" id="update-telefone-cNmEmpresa" required>

            <!-- Telefone -->
            <label for="update-telefone-cNrTelefone">Telefone</label>
            <input type="text" name="cNrTelefone" id="update-telefone-cNrTelefone" required>

            <!-- Descrição -->
            <label for="update-telefone-cDescricao">Descrição</label>
            <input type="text" name="cDescricao" id="update-telefone-cDescricao" maxlength="30" required>

            <button type="submit">Atualizar</button>
        </form>
    </div>
</div>
<!-- ==================== POPUP DE EXCLUSÃO ==================== -->
<div class="popup-overlay" id="popup-delete-telefone" style="display:none;">
    <div class="popup">
        <button class="close-btn" onclick="fecharPopup('popup-delete-telefone')">×</button>
        <h2>Excluir Telefone</h2>

        <form action="${pageContext.request.contextPath}/telefone/delete" method="post">
            <p>Tem certeza que deseja excluir a telefone <strong id="delete-telefone-nome"></strong>?</p>

            <input type="hidden" name="nCdTelefone" id="delete-telefone-nCdTelefone">

            <div class="botoes-principais">
                <button type="submit"  onclick="setTimeout(() => this.disabled = true, 0); this.innerText = 'Excluindo...';" >Excluir</button>
                <button type="button" class="btn-cancelar" onclick="fecharPopup('popup-delete-telefone')">
                    Cancelar
                </button>
            </div>
        </form>
    </div>
</div>
<div class="filtroPopup-overlay" id="filtroTelefone" style="display:none;">
    <div class="popup">
        <button class="close-btn" onclick="fecharPopup('filtroTelefone')">×</button>
        <h2>Atualizar Telefone</h2>
        <form action="<%=request.getContextPath()%>/telefone/list" method="get">
            <input type="text" name="nomeEmpresa" placeholder="Digite o nome da empresa"value="<%= request.getParameter("nomeEmpresa") != null ? request.getParameter("nomeEmpresa") : "" %>">
            <button type="submit" class="add-btn">Filtrar</button>
        </form>
    </div>
</div>
