package com.purpura.dao;
import com.purpura.models.Arquivo;
import com.purpura.models.enums.CategoriaArquivo;
import java.sql.ResultSet;


public class ArquivoDAO extends GenericDAOImpl<Arquivo>{
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
                CategoriaArquivo.valueOf(rs.getString("categoriaArquivo")),
                rs.getInt("nCdDono")
        );
    }

    @Override
    protected String getNomesColunas() {
        return "nCdArquivo, cNmArquivo, cTipoArquivo, dArquivo, categoriaArquivo, nCdDono";
    }
    
    @Override
    protected void prepareStatementForSave(java.sql.PreparedStatement stmt, Arquivo entidade) throws java.sql.SQLException {
        stmt.setString(1, entidade.getCNmArquivo());
        stmt.setInt(2, entidade.getNCdArquivo());
        stmt.setString(3, entidade.getCTipoArquivo());
        stmt.setDate(4, java.sql.Date.valueOf(entidade.getDArquivo()));
        stmt.setString(5, entidade.getCategoriaArquivo().name());
        stmt.setInt(6, entidade.getNCdDono());
    }

    @Override
    protected void prepareStatementForUpdate(java.sql.PreparedStatement stmt, Arquivo entidade) throws java.sql.SQLException {
        stmt.setString(1, entidade.getCNmArquivo());
        stmt.setInt(2, entidade.getNCdArquivo());
        stmt.setString(3, entidade.getCTipoArquivo());
        stmt.setDate(4, java.sql.Date.valueOf(entidade.getDArquivo()));
        stmt.setString(5, entidade.getCategoriaArquivo().name());
        stmt.setInt(6, entidade.getNCdDono());
    }

    @Override
    protected String getColunaId(){
        return "nCdArquivo";
    }
}
