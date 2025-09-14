package com.purpura.dao;

import com.purpura.models.Mensagem;

public class MensagemDAO extends DAO<Mensagem> {
    @Override
    public String getNomeTabela(){
        return "Mensagem";
    }

    @Override
    protected Mensagem mapResultSet(java.sql.ResultSet rs) throws java.sql.SQLException {
        return new Mensagem(
                rs.getInt("nCdMensagem"),
                rs.getString("cConteudo"),
                rs.getString("cCnpjRemetente"),
                rs.getString("cCnpjDestinatario")
        );
    }

    @Override
    protected String getNomesColunas(){
        return "cConteudo, cCnpjRemetente, cCnpjDestinatario";
    }

    @Override
    protected void prepareStatementForSave(java.sql.PreparedStatement stmt, Mensagem entidade) throws java.sql.SQLException {
        stmt.setString(1, entidade.getCConteudo());
        stmt.setString(2, entidade.getCCnpjRemetente());
        stmt.setString(3, entidade.getCCnpjDestinatario());
    }

    @Override
    protected void prepareStatementForUpdate(java.sql.PreparedStatement stmt, Mensagem entidade) throws java.sql.SQLException {
        prepareStatementForSave(stmt, entidade);
        stmt.setInt(4, entidade.getNCdMensagem());
    }

    @Override
    protected String getColunaId(){
        return "nCdMensagem";
    }
}
