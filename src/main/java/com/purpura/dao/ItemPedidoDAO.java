package com.purpura.dao;

import com.purpura.models.ItemPedido;

public class ItemPedidoDAO extends GenericDAOImpl<ItemPedido>{
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
        return "nCdItemPedido, nPrecoUnitario, nVolume, nCdResiduo, nCdPedido";
    }

    @Override
    protected void prepareStatementForSave(java.sql.PreparedStatement stmt, ItemPedido entidade) throws java.sql.SQLException{
        stmt.setInt(1, entidade.getNCdItemPedido());
        stmt.setDouble(2, entidade.getNPrecoUnitario());
        stmt.setDouble(3, entidade.getNVolume());
        stmt.setInt(4, entidade.getNCdResiduo());
        stmt.setInt(5, entidade.getNCdPedido());
    }

    @Override
    protected void prepareStatementForUpdate(java.sql.PreparedStatement stmt, ItemPedido entidade) throws java.sql.SQLException{
        stmt.setInt(1, entidade.getNCdItemPedido());
        stmt.setDouble(2, entidade.getNPrecoUnitario());
        stmt.setDouble(3, entidade.getNVolume());
        stmt.setInt(4, entidade.getNCdResiduo());
        stmt.setInt(5, entidade.getNCdPedido());
    }

    @Override
    protected String getColunaId(){
        return "nCdItemPedido";
    }

}
