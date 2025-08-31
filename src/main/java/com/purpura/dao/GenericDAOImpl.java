package com.purpura.dao;

import com.purpura.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class GenericDAOImpl<T> implements GenericDAO<T>{
    @Override
    public boolean save(T entidade) {
        String sql = "INSERT INTO " + getNomeTabela() +
                " (" + getNomesColunas() + ") VALUES (" + getPlaceholders() + ")";
        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            prepareStatementForSave(stmt, entidade);
            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(T entidade) {
        String sql = "UPDATE " + getNomeTabela() +
                " SET " + getColunasUpdate() +
                " WHERE " + getColunaId() + " = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            prepareStatementForUpdate(stmt, entidade);
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id){
        String sql = "DELETE FROM " + getNomeTabela() +
                " WHERE " + getColunaId() + " = ?";

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
                stmt.setInt(1, id);
                int linhasDeletadas = stmt.executeUpdate();
                return linhasDeletadas > 0;
            } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public T find(int id) {
        String sql = "SELECT * FROM " + getNomeTabela() +
                " WHERE " + getColunaId() + " = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<T> findAll() {
        List<T> lista = new ArrayList<>();
        String sql = "SELECT * FROM " + getNomeTabela();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()){
                lista.add(mapResultSet(rs));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return lista;
    }

    // métodos utilitários
    protected String getPlaceholders() {
        int quantidade = getNomesColunas().split("\\s*,\\s*").length;
        return String.join(", ", Collections.nCopies(quantidade, "?"));
    }
    protected String getColunasUpdate() {
        String[] colunas = getNomesColunas().split("\\s*,\\s*");
        String colunaId = getColunaId();

        List<String> updates = new ArrayList<>();
        for (String coluna : colunas) {
            if (!coluna.equalsIgnoreCase(colunaId)) {
                updates.add(coluna + " = ?");
            }
        }
        return String.join(", ", updates);
    }

    public abstract String getNomeTabela(); // retorna nome da entidade
    protected abstract T mapResultSet(ResultSet rs) throws SQLException; // retorna objeto da entidade
    protected abstract String getNomesColunas(); // retorna o nome das colunas
    protected abstract void prepareStatementForSave(PreparedStatement stmt, T entidade) throws SQLException; // prepareStatement pra método save
    protected abstract void prepareStatementForUpdate(PreparedStatement stmt, T entidade) throws SQLException; // prepareStatement pra método update
    protected abstract String getColunaId(); // retorna nome da coluna com id (primary key)
}
