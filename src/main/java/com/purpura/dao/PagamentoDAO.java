package com.purpura.dao;

import com.purpura.models.Pagamento;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PagamentoDAO extends DAO<Pagamento> {
    public String getNomeTabela(){
        return "Pagamento";
    }

    protected Pagamento mapResultSet(ResultSet rs) throws java.sql.SQLException{
        return new Pagamento(
                rs.getInt("nCdPagamento"),
                rs.getDate("dPagamento").toLocalDate(),
                rs.getString("cStatus"),
                rs.getDouble("nValorPago"),
                rs.getString("cFormaPagamento"),
                rs.getString("cObservavoes"),
                rs.getInt("nCdPedido")
                );
    }

    @Override
    protected String getNomesColunas() {
        return "nCdPagamento, dPagamento, cStatus, cFormaPagamento, cObservavoes, nCdPedido, nValorPago";
    }

    protected String getNomeColunas(){
        return "nCdPagamento, dPagamento, cStatus, cFormaPagamento, cObservacoes, nCdPedido";
    }

    protected void prepareStatementForSave(java.sql.PreparedStatement stmt, Pagamento entidade) throws java.sql.SQLException{
        stmt.setInt(1, entidade.getNCdPagamento());
        stmt.setDate(2, Date.valueOf(entidade.getDPagamento()));
        stmt.setString(3, entidade.getCStatusPagamento());
        stmt.setDouble(4, entidade.getNValorPago());
        stmt.setString(5, entidade.getCFormaPagamento());
        stmt.setString(6, entidade.getCObservacoes());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement stmt, Pagamento entidade) throws SQLException {
        stmt.setInt(1, entidade.getNCdPagamento());
        stmt.setDate(2, Date.valueOf(entidade.getDPagamento()));
        stmt.setString(3, entidade.getCStatusPagamento());
        stmt.setDouble(4, entidade.getNValorPago());
        stmt.setString(5, entidade.getCFormaPagamento());
        stmt.setString(6, entidade.getCObservacoes());
    }

    protected String getColunaId(){
        return"nCdPagamento";}

}

