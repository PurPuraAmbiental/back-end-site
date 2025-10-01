package com.purpura.dao;

import com.purpura.model.ItemPedido;

public class ItemPedidoDAO extends DAO<ItemPedido>{
    @Override
    public String getNomeTabela(){
        return "ItemPedido";
    }

    @Override
    protected ItemPedido mapResultSet(java.sql.ResultSet rs) throws java.sql.SQLException{
        return new ItemPedido(
                rs.getInt("nCdItemPedido"),
                rs.getDouble("nPrecoUnitario"),
                rs.getDouble("nVolume"),
                rs.getInt("nCdResiduo"),
                rs.getInt("nCdPedido")
        );
    }

    @Override
    protected String getNomesColunas(){
        return "nPrecoUnitario, nVolume, nCdResiduo, nCdPedido";
    }

    @Override
    protected void prepareStatementForSave(java.sql.PreparedStatement stmt, ItemPedido entidade) throws java.sql.SQLException{
        stmt.setDouble(1, entidade.getNPrecoUnitario());
        stmt.setDouble(2, entidade.getNVolume());
        stmt.setInt(3, entidade.getNCdResiduo());
        stmt.setInt(4, entidade.getNCdPedido());
    }

    @Override
    protected void prepareStatementForUpdate(java.sql.PreparedStatement stmt, ItemPedido entidade) throws java.sql.SQLException{
        prepareStatementForSave(stmt, entidade);
        stmt.setInt(5, entidade.getNCdItemPedido());
    }

    @Override
    protected String getColunaId(){
        return "nCdItemPedido";
    }

}
