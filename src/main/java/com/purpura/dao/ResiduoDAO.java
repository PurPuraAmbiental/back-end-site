package com.purpura.dao;

import com.purpura.models.Residuo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResiduoDAO extends GenericDAOImpl<Residuo>{
    @Override
    public String getNomeTabela() {
        return "Residuo";
    }

    @Override
    protected Residuo mapResultSet(ResultSet rs) throws SQLException {
        return new Residuo(
                rs.getInt("nCdResiduo"),
                rs.getString("cNmResiduo"),
                rs.getString("cTipoUnidade"),
                rs.getDouble("nPrecoPadrao"),
                rs.getDouble("nVolumePadrao"),
                rs.getString("cCategoria"),
                rs.getString("cDescricao"),
                rs.getString("cCnpj")
        );
    }

    @Override
    protected String getNomesColunas() {
        return "nCdResiduo, cNmResiduo, cTipoUnidade, " +
                "nPrecoPadrao, nVolumePadrao, cCategoria, " +
                "cDescricao, cCnpj";
    }

    @Override
    protected void prepareStatementForSave(PreparedStatement stmt, Residuo entidade) throws SQLException {
        stmt.setInt(1, entidade.getNCdResiduo());
        stmt.setString(2, entidade.getCNmResiduo());
        stmt.setString(3, entidade.getCTipoUnidade());
        stmt.setDouble(4, entidade.getNPrecoPadrao());
        stmt.setDouble(5, entidade.getNVolumePadrao());
        stmt.setString(6, entidade.getCCategoria());
        stmt.setString(7, entidade.getCDescricao());
        stmt.setString(8, entidade.getCCnpj());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement stmt, Residuo entidade) throws SQLException {
        stmt.setString(1, entidade.getCNmResiduo());
        stmt.setString(2, entidade.getCTipoUnidade());
        stmt.setDouble(3, entidade.getNPrecoPadrao());
        stmt.setDouble(4, entidade.getNVolumePadrao());
        stmt.setString(5, entidade.getCCategoria());
        stmt.setString(6, entidade.getCDescricao());
        stmt.setString(7, entidade.getCCnpj());
        stmt.setInt(8, entidade.getNCdResiduo());
    }

    @Override
    protected String getColunasUpdate() {
        return "cNmResiduo = ?, cTipoUnidade = ?, nPrecoPadrao = ?, " +
                "nVolumePadrao = ?, cCategoria = ?, cDescricao = ?, " +
                "cCnpj = ?";
    }

    @Override
    protected String getColunaId() {
        return "nCdResiduo";
    }
}
