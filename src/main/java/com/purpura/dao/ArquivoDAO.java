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
                rs.getDate("dUpload").toLocalDate(),
                rs.getString("cCnpj"),
                rs.getInt("nCdResiduo")
        );
    }

    @Override
    protected String getNomesColunas() {
        return "cNmArquivo, cTipoArquivo, dUpload, cCnpj, nCdResiduo";
    }
    
    @Override
    protected void prepareStatementForSave(java.sql.PreparedStatement stmt, Arquivo entidade) throws java.sql.SQLException {
        stmt.setString(1, entidade.getCNmArquivo());
        stmt.setString(2, entidade.getCTipoArquivo());
        stmt.setDate(3, java.sql.Date.valueOf(entidade.getDUpload()));
        stmt.setString(4, entidade.getCCnpj());
        stmt.setInt(5, entidade.getNCdResiduo());
    }

    @Override
    protected void prepareStatementForUpdate(java.sql.PreparedStatement stmt, Arquivo entidade) throws java.sql.SQLException {
        prepareStatementForSave(stmt, entidade);
        stmt.setInt(6, entidade.getNCdArquivo());
    }

    @Override
    protected String getColunaId(){
        return "nCdArquivo";
    }
}
