package com.purpura.dao;
import com.purpura.models.Arquivo;
import java.sql.ResultSet;


public class ArquivoDAO extends DAO<Arquivo>{
    @Override
    public String getNomeTabela(){
        return "Arquivo";
    }

    @Override
    protected Arquivo mapResultSet(ResultSet rs) throws java.sql.SQLException{
                    return new Arquivo(
                rs.getInt("nCdArquivo"),
                rs.getString("cNmArquivo"),
                rs.getString("cTipoArquivo"),
                rs.getDate("dArquivo").toLocalDate(),
                rs.getString("cCnpj"),
                rs.getInt("nCdResiduo")
        );
    }

    @Override
    protected String getNomesColunas() {
        return "nCdArquivo, cNmArquivo, cTipoArquivo, dArquivo, cCnpj, nCdResiduo";
    }
    
    @Override
    protected void prepareStatementForSave(java.sql.PreparedStatement stmt, Arquivo entidade) throws java.sql.SQLException {
        stmt.setString(1, entidade.getCNmArquivo());
        stmt.setInt(2, entidade.getNCdArquivo());
        stmt.setString(3, entidade.getCTipoArquivo());
        stmt.setDate(4, java.sql.Date.valueOf(entidade.getDArquivo()));
        stmt.setString(5, entidade.cCnpj);
        stmt.setInt(6, entidade.getNCdResiduo());
    }

    @Override
    protected void prepareStatementForUpdate(java.sql.PreparedStatement stmt, Arquivo entidade) throws java.sql.SQLException {
        stmt.setString(1, entidade.getCNmArquivo());
        stmt.setInt(2, entidade.getNCdArquivo());
        stmt.setString(3, entidade.getCTipoArquivo());
        stmt.setDate(4, java.sql.Date.valueOf(entidade.getDArquivo()));
        stmt.setString(5, entidade.getCCnpj());
        stmt.setInt(6, entidade.getNCdResiduo());
    }

    @Override
    protected String getColunaId(){
        return "nCdArquivo";
    }
}
