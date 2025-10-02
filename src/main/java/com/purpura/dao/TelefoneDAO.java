package com.purpura.dao;

import com.purpura.model.Telefone;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TelefoneDAO extends DAO<Telefone> {
    @Override
    public String getNomeTabela() {
        return "Telefone";
    }
    protected Telefone mapResultSet(ResultSet rs) throws SQLException{
        return new Telefone(
                rs.getInt("nCdTelefone"),
                rs.getInt("nCdEmpresa"),
                rs.getString("fone"),
                rs.getString("Descricao")
        );
    }

    @Override
    protected String getNomesColunas() {
        return "nCdTelefone, fone, nCdEmpresa, Descricao";
    }

    @Override
    protected void prepareStatementForSave(PreparedStatement stmt, Telefone entidade) throws SQLException {
        stmt.setInt(1, entidade.getnCdEmpresa());
        stmt.setInt(2, entidade.getnCdTelefone());
        stmt.setString(3, entidade.getCFone());
        stmt.setString(4, entidade.getCDescricao());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement stmt, Telefone entidade) throws SQLException {
        prepareStatementForSave(stmt, entidade);
        stmt.setInt(9, entidade.getnCdTelefone());
    }

    @Override
    protected String getColunaId() {
        return "nCdTelefone";
    }
}
