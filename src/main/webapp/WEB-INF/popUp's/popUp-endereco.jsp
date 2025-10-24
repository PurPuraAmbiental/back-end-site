<%--
  Created by IntelliJ IDEA.
  User: brunajesus-ieg
  Date: 23/10/2025
  Time: 22:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
        <!-- INSERT -->
        <div class="popup-overlay" id="popup-insert" style="display:none;">
            <div class="popup">
                <button class="close-btn" onclick="fecharPopup('popup-insert')">×</button>
                <h2>Cadastrar Endereço</h2>
                <form action="${pageContext.request.contextPath}/endereco-empresa/insert" method="post">
                    <label>Bairro</label>
                    <input type="text" name="cBairro">
                    <label>Logradouro</label>
                    <input type="text" name="cLogradouro">
                    <label>Estado</label>
                    <input type="text" name="cEstado">
                    <label>Cidade</label>
                    <input type="text" name="cCidade">
                    <label>CEP</label>
                    <input type="text" name="cCep">
                    <label>Complemento</label>
                    <input type="text" name="cComplemento">
                    <label>Número</label>
                    <input type="text" name="iNrEnderecoEmpresa">
                    <label>Empresa</label>
                    <input type="text" name="cNmEmpresa">
                    <button type="submit">Adicionar</button>
                </form>
            </div>
        </div>

        <!-- UPDATE -->
        <div class="popup-overlay" id="popup-update" style="display:none;">
            <div class="popup">
                <button class="close-btn" onclick="fecharPopup('popup-update')">×</button>
                <h2>Atualizar Endereço</h2>
                <form id="updateForm" action="${pageContext.request.contextPath}/endereco-empresa/update" method="post">
                    <input type="hidden" name="nCdEnderecoEmpresa" id="upd-id">
                    <label>Bairro</label>
                    <input type="text" name="cBairro" id="upd-bairro">
                    <label>Logradouro</label>
                    <input type="text" name="cLogradouro" id="upd-logradouro">
                    <label>Estado</label>
                    <input type="text" name="cEstado" id="upd-estado">
                    <label>Cidade</label>
                    <input type="text" name="cCidade" id="upd-cidade">
                    <label>CEP</label>
                    <input type="text" name="cCep" id="upd-cep">
                    <label>Complemento</label>
                    <input type="text" name="cComplemento" id="upd-complemento">
                    <label>Número</label>
                    <input type="text" name="iNrEnderecoEmpresa" id="upd-numero">
                    <label>Empresa</label>
                    <input type="text" name="cNmEmpresa" id="upd-empresa">
                    <button type="submit">Atualizar</button>
                </form>
            </div>
        </div>

