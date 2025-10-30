package com.purpura.dao;

import com.purpura.exception.ConnectionFailedException;
import com.purpura.model.ChaveAcesso;
import com.purpura.model.Transportadora;
import com.purpura.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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