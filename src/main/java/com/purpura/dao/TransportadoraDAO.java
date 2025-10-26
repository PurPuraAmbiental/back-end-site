package com.purpura.dao;

import com.purpura.model.Transportadora;

/**
 * DAO responsável pelas operações da entidade Transportadora.
 * Implementa os métodos genéricos da classe DAO.
 * Veja classe abstrata para documentação detalhada dos métodos.
 *
 * @author Letícia Reis
 */
public class TransportadoraDAO extends DAO<Transportadora>{

    @Override
    public String getNomeTabela(){
        return "Transportadora";
    }

    @Override
    protected Transportadora mapResultSet(java.sql.ResultSet rs)throws java.sql.SQLException{
        return new Transportadora(
                rs.getString("cCnpj"),
                rs.getString("cNmTransportadora"),
                rs.getString("cEmail"),
                rs.getString("cRegiaoAtendida")
        );
    }

    @Override
    protected String getNomesColunas(){
        return "cCnpj, cNmTransportadora, cRegiaoAtendida, cEmail";
    }

    @Override
    protected void prepareStatementForSave(java.sql.PreparedStatement stmt, Transportadora entidade) throws java.sql.SQLException {
        stmt.setString(1, entidade.getCCnpj());
        stmt.setString(2, entidade.getCNmTransportadora());
        stmt.setString(3, entidade.getCRegiaoAtendida());
        stmt.setString(4, entidade.getCEmail());
    }

    @Override
    protected void prepareStatementForUpdate(java.sql.PreparedStatement stmt, Transportadora entidade) throws java.sql.SQLException {
        stmt.setString(1, entidade.getCNmTransportadora());
        stmt.setString(2, entidade.getCRegiaoAtendida());
        stmt.setString(3, entidade.getCEmail());
        stmt.setString(4, entidade.getCCnpj());
    }

    @Override
    protected String getColunaId(){
        return "cCnpj";
    }
}