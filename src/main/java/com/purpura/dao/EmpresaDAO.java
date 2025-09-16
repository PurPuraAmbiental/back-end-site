package com.purpura.dao;

import com.purpura.model.Empresa;

import java.sql.ResultSet;

public class EmpresaDAO extends DAO<Empresa> {
    @Override
    public String getNomeTabela() {
        return "Empresa";
    }

    @Override
    protected Empresa mapResultSet(ResultSet rs) throws java.sql.SQLException {
        return new Empresa(
                rs.getString("cNmEmpresa"),
                rs.getString("cSenha"),
                rs.getString("cCnpj"),
                rs.getString("cAtivo").charAt(0),
                rs.getString("cEmail"),
                rs.getString("cTelefone")
        );
    }

    @Override
    protected String getNomesColunas() {
        return "cNmEmpresa, cSenha, cCnpj, cAtivo, cEmail, cTelefone";
    }

    @Override
    protected void prepareStatementForSave(java.sql.PreparedStatement stmt, Empresa entidade) throws java.sql.SQLException {
        stmt.setString(1, entidade.getCNmEmpresa());
        stmt.setString(2, entidade.getCSenha());
        stmt.setString(3, entidade.getCCnpj());
        stmt.setString(4, String.valueOf(entidade.getCAtivo()));
        stmt.setString(5, entidade.getCEmail());
        stmt.setString(6, entidade.getCTelefone());
    }

    @Override
    protected void prepareStatementForUpdate(java.sql.PreparedStatement stmt, Empresa entidade) throws java.sql.SQLException {
        stmt.setString(1, entidade.getCNmEmpresa());
        stmt.setString(2, entidade.getCSenha());
        stmt.setString(3, String.valueOf(entidade.getCAtivo()));
        stmt.setString(4, entidade.getCEmail());
        stmt.setString(5, entidade.getCTelefone());
        stmt.setString(6, entidade.getCCnpj());
    }

    @Override
    protected String getColunaId() {
        return "cCnpj";
    }
}
