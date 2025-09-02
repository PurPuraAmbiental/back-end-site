package com.purpura.dao;

import com.purpura.models.Arquivo;
import com.purpura.models.Transporte;

public class TransporteDAO extends GenericDAOImpl<Transporte>{
    @Override
    public String getNomeTabela(){
        return "Transporte";
    }

    @Override
    protected Transporte mapResultSet(java.sql.ResultSet rs)throws java.sql.SQLException{
        return new Transporte(
          rs.getInt("nCdTransporte"),
          rs.getString("cNmTransporte"),
                rs.getDate("dRetirada").toLocalDate()
        );
    }

    protected String getNomesColunas(){
        return "nCdTransporte, cNmTransporte, dRetirada";
    }

    @Override
    protected void prepareStatementForSave(java.sql.PreparedStatement stmt, Transporte entidade) throws java.sql.SQLException {
        stmt.setInt(1, entidade.getNCdTransporte());
        stmt.setString(2, entidade.getCNmTransporte());
        stmt.setDate(3, java.sql.Date.valueOf(entidade.getDRetirada()));
    }

    @Override
    protected void prepareStatementForUpdate(java.sql.PreparedStatement stmt, Transporte entidade) throws java.sql.SQLException {
        stmt.setInt(1, entidade.getNCdTransporte());
        stmt.setString(2, entidade.getCNmTransporte());
        stmt.setDate(3, java.sql.Date.valueOf(entidade.getDRetirada()));
    }

    @Override
    protected String getColunaId(){
        return "nCdTransporte";
    }
}
