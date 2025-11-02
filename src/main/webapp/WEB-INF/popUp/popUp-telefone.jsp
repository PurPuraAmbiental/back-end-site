<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
    <!--
    Esta página contém os formulários usados nos pop-ups para CRUD de telefone:
    - Inserção
    - Atualização
    - Exclusão
    Também inclui o filtro para busca de empresas.
    O JavaScript utilizado nos botões serve para abrir/fechar pop-ups e alterar o texto dos botões durante o envio do formulário.
    -->

<!-- ==================== POPUP DE INSERÇÃO ==================== -->
<div class="popup-overlay" id="popup-insert-telefone" style="display:none;">
    <div class="popup">
        <!-- Botao para fechar pop up-->
        <button class="close-btn" onclick="fecharPopup('popup-insert-telefone')">×</button>
        <h2>Cadastrar Telefone</h2>

        <!-- restrição no botão para que o usuário não consiga chamar o servlet várias vezes -->
        <form action="${pageContext.request.contextPath}/telefone/insert"
              method="post"
              onsubmit="this.querySelector('button').disabled = true; this.querySelector('button').innerText = 'Adicionando...';">

            <!-- Empresa -->
            <label for="cNmEmpresa">Empresa</label>
            <input type="text" name="cNmEmpresa" id="cNmEmpresa" required>

            <!-- Telefone -->
            <label for="cNrTelefone">Telefone</label>
            <input type="text" name="cNrTelefone" id="cNrTelefone" required>

            <!-- Descrição -->
            <label for="cDescricao">Descrição</label>
            <input type="text" name="cDescricao" id="cDescricao" maxlength="30" required>

            <button type="submit">Adicionar</button>
        </form>
    </div>
</div>

<!-- ==================== POPUP DE ATUALIZAÇÃO ==================== -->
<div class="popup-overlay" id="popup-update-telefone" style="display:none;">
    <div class="popup">
        <!-- Botao para fechar pop up-->
        <button class="close-btn" onclick="fecharPopup('popup-update-telefone')">×</button>
        <h2>Atualizar Telefone</h2>

        <!-- restrição no botão para que o usuário não consiga chamar o servlet várias vezes -->
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
        <!-- Botao para fechar pop up-->
        <button class="close-btn" onclick="fecharPopup('popup-delete-telefone')">×</button>
        <h2>Excluir Telefone</h2>

        <!-- restrição no botão para que o usuário não consiga chamar o servlet várias vezes -->
        <form action="${pageContext.request.contextPath}/telefone/delete" method="post">
            <p>Tem certeza que deseja excluir o telefone <strong id="delete-telefone-nome"></strong>?</p>

            <input type="hidden" name="nCdTelefone" id="delete-telefone-nCdTelefone">

            <div class="botoes-principais">
                <button type="submit" onclick="setTimeout(() => this.disabled = true, 0); this.innerText = 'Excluindo...';">Excluir</button>
                <button type="button" class="btn-cancelar" onclick="fecharPopup('popup-delete-telefone')">
                    Cancelar
                </button>
            </div>
        </form>
    </div>
</div>

<!-- ==================== FILTROS ========================= -->
<div class="filtroPopup-overlay" id="filtroTelefone" style="display:none;">
    <div class="popup" id="filtroPopup">
        <h2>Filtrar Telefone</h2>
        <!-- Botao para fechar pop up-->
        <button class="close-btn" onclick="fecharPopup('filtroTelefone')">×</button>

        <!-- restrição no botão para que o usuário não consiga chamar o servlet várias vezes -->
        <form action="<%=request.getContextPath()%>/telefone/list" method="get">

            <!-- Filtro por empresa -->
            <label for="nomeEmpresa">Insira o nome da empresa</label>
            <input type="text" id="nomeEmpresa" name="nomeEmpresa" placeholder="Digite o nome da empresa" value="<%= request.getParameter("nomeEmpresa") != null ? request.getParameter("nomeEmpresa") : "" %>">

            <br>
            <button type="submit" class="add-btn">Filtrar</button>
        </form>
    </div>
</div>
