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
        return "dPagamento, cStatusPagamento, cFormaPagamento, cObservacoes, nCdPedido, nValorPago";
    }

    protected void prepareStatementForSave(java.sql.PreparedStatement stmt, Pagamento entidade) throws java.sql.SQLException{
        stmt.setDate(1, Date.valueOf(entidade.getDPagamento()));
        stmt.setString(2, entidade.getCStatusPagamento());
        stmt.setString(3, entidade.getCFormaPagamento());
        stmt.setString(4, entidade.getCObservacoes());
        stmt.setInt(5, entidade.getNCdPedido());
        stmt.setDouble(6, entidade.getNValorPago());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement stmt, Pagamento entidade) throws SQLException {
        prepareStatementForSave(stmt, entidade);
        stmt.setInt(7, entidade.getNCdPagamento());
    }

    protected String getColunaId(){
        return"nCdPagamento";}

}

