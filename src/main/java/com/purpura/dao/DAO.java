package com.purpura.dao;

import com.purpura.common.Constants;
import com.purpura.exception.ConnectionFailedException;
import com.purpura.exception.NotFoundException;
import com.purpura.models.Model;
import com.purpura.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class DAO<T extends Model> {
    public void save(T entidade) throws NotFoundException, ConnectionFailedException {
        String sql = "INSERT INTO " + getNomeTabela() +
                " (" + getNomesColunas() + ") VALUES (" + getPlaceholders() + ")";
        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            prepareStatementForSave(stmt, entidade);
            int rows = stmt.executeUpdate();
            if(rows == 0) {
                throw new NotFoundException(getNomeTabela(), entidade.getId());
            }

        } catch (SQLException e)  {
            throw new ConnectionFailedException();
        }
    }

    public void update(T entidade) throws NotFoundException, ConnectionFailedException{
        String sql = "UPDATE " + getNomeTabela() +
                " SET " + getColunasUpdate() +
                " WHERE " + getColunaId() + " = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            prepareStatementForUpdate(stmt, entidade);
            int rows = stmt.executeUpdate();
            if(rows == 0) {
                throw new NotFoundException(getNomeTabela(), entidade.getId());
            }
        } catch (SQLException e) {
            throw new ConnectionFailedException();
        }
    }

    public void delete(int id) throws NotFoundException, ConnectionFailedException{
        String sql = "DELETE FROM " + getNomeTabela() +
                " WHERE " + getColunaId() + " = ?";

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
                stmt.setInt(1, id);
                int linhasDeletadas = stmt.executeUpdate();
                if (linhasDeletadas == 0) {
                    throw new NotFoundException(getNomeTabela(), id);
                }
            } catch (SQLException e){
            throw new ConnectionFailedException();
        }
    }

    public T find(int id) throws NotFoundException, ConnectionFailedException {
        String sql = "SELECT * FROM " + getNomeTabela() +
                " WHERE " + getColunaId() + " = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSet(rs);
                } else {
                    throw new NotFoundException(getNomeTabela(), id);
                }
            }
        } catch (SQLException e) {
            throw new ConnectionFailedException();
        }
    }

    public List<T> findAll() throws NotFoundException, ConnectionFailedException{
        List<T> lista = new ArrayList<>();
        String sql = "SELECT * FROM " + getNomeTabela();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()){
                lista.add(mapResultSet(rs));
            }
        } catch (SQLException e){
            throw new ConnectionFailedException();
        }

        if (lista.isEmpty()) {
            throw new NotFoundException(getNomeTabela(), -1);
        }

        return lista;
    }

    // métodos utilitários
    protected String getPlaceholders() {
        int quantidade = getNomesColunas().split(Constants.COMMA_SEPARATOR_REGEX).length;
        return String.join(", ", Collections.nCopies(quantidade, "?"));
    }
    protected String getColunasUpdate() {
        String[] colunas = getNomesColunas().split(Constants.COMMA_SEPARATOR_REGEX);
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
