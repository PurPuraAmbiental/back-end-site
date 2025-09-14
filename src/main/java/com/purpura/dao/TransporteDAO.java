package com.purpura.dao;
import com.purpura.models.Transporte;

public class TransporteDAO extends DAO<Transporte>{
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
        return "cNmTransporte, dRetirada";
    }

    @Override
    protected void prepareStatementForSave(java.sql.PreparedStatement stmt, Transporte entidade) throws java.sql.SQLException {
        stmt.setString(1, entidade.getCNmTransporte());
        stmt.setDate(2, java.sql.Date.valueOf(entidade.getDRetirada()));
    }

    @Override
    protected void prepareStatementForUpdate(java.sql.PreparedStatement stmt, Transporte entidade) throws java.sql.SQLException {
        prepareStatementForSave(stmt, entidade);
        stmt.setInt(3, entidade.getNCdTransporte());
    }

    @Override
    protected String getColunaId(){
        return "nCdTransporte";
    }
}
