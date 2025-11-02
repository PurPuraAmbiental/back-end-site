package com.purpura.dao;

import com.purpura.model.ChaveAcesso;

/**
 * DAO responsável pelas operações da entidade ChaveAcesso.
 * Implementa os métodos genéricos da classe DAO.
 * Veja classe abstrata para documentação detalhada dos métodos.
 *
 * Objetivo: deixar somente quem tem uma senha, ja pre definida, conseguir se cadastrar
 * como novo administrador!
 *
 * @author Kevin de Oliveira
 */
public class ChaveAcessoDAO extends DAO<ChaveAcesso>{

    @Override
    public String getNomeTabela(){
        return "ChaveAcesso";
    }

    @Override
    protected ChaveAcesso mapResultSet(java.sql.ResultSet rs)throws java.sql.SQLException{
        return new ChaveAcesso(
                rs.getInt("nCdChaveAcesso"),
                rs.getString("cHash"),
                rs.getString("cAtivo").charAt(0)
        );
    }

    @Override
    protected String getNomesColunas(){
        return "nCdChaveAcesso, cHash, cAtivo";
    }

    @Override
    protected void prepareStatementForSave(java.sql.PreparedStatement stmt, ChaveAcesso entidade) throws java.sql.SQLException {
        stmt.setString(1, entidade.getCHash());
        stmt.setString(2, String.valueOf(entidade.getCAtivo()));
    }

    @Override
    protected void prepareStatementForUpdate(java.sql.PreparedStatement stmt, ChaveAcesso entidade) throws java.sql.SQLException {
        prepareStatementForSave(stmt, entidade);
        stmt.setInt(3, entidade.getNCdChaveAcesso());
    }

    @Override
    protected String getColunaId(){
        return "nCdChaveAcesso";
    }
}