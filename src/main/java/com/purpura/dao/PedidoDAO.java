package com.purpura.dao;

import com.purpura.dao.DAO;
import com.purpura.models.Pedido;

import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;

public class PedidoDAO extends DAO<Pedido> {
    @Override
    public String getNomeTabela() {
        return "Pedido";
    }
    @Override
    protected Pedido mapResultSet(ResultSet rs) throws java.sql.SQLException {
        return new Pedido(
                rs.getDouble("nValorTotal"),
                rs.getString("cStatus"),
                rs.getDate("dPedido").toLocalDate(),
                rs.getString("cFrequencia"),
                rs.getDate("dAgendamentoColeta").toLocalDate(),
                rs.getString("cObservacoes"),
                rs.getInt("nCdPedido")
                );
    }
    @Override
    protected String getNomesColunas() {
        return "nValorTotal, cStatus, dPedido, cFrequencia, dAgentamentoColeta, cObservacoes";
    }


    @Override
    protected void prepareStatementForSave(java.sql.PreparedStatement stmt, Pedido entidade) throws java.sql.SQLException {
        stmt.setDouble(1, entidade.getNValorTotal());
        stmt.setString(2, entidade.getCStatus());
        stmt.setDate(3, Date.valueOf(entidade.getDPedido()));
        stmt.setString(4, entidade.getCFrequencia());
        stmt.setDate(5, Date.valueOf(entidade.getDAgendamentoColeta()));
        stmt.setString(6, entidade.getCObservacoes());
    }
    @Override
    protected void prepareStatementForUpdate(java.sql.PreparedStatement stmt, Pedido entidade) throws java.sql.SQLException {
        prepareStatementForSave(stmt, entidade);
        stmt.setInt(7, entidade.getNCdPedido());
    }
    @Override
    protected String getColunaId() {
        return "nCdPedido";
    }
}