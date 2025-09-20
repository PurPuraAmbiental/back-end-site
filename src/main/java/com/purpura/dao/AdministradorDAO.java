package com.purpura.dao;

import com.purpura.model.Administrador;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdministradorDAO extends DAO<Administrador>{
    @Override
    public String getNomeTabela(){return "Administrador";}

    @Override
    protected Administrador mapResultSet(ResultSet rs) throws SQLException{
        return new Administrador(
                rs.getString("cEmail"),
                rs.getString("cSenha"),
                rs.getString("cNmAdministrador")
        );
    }

    @Override
    protected String getNomesColunas(){return "cEmail, cSenha, cNmAdministrador";}

    @Override
    protected void prepareStatementForSave(PreparedStatement stmt, Administrador entidade) throws SQLException{
        stmt.setString(1, entidade.getCEmail());
        stmt.setString(2, entidade.getCSenha());
        stmt.setString(3, entidade.getCNmAdministrador());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement stmt, Administrador entidade) throws SQLException{
        stmt.setString(1, entidade.getCSenha());
        stmt.setString(2, entidade.getCNmAdministrador());
        stmt.setString(3, entidade.getCEmail());
    }

    @Override
    protected String getColunaId(){return "cEmail";}
}
