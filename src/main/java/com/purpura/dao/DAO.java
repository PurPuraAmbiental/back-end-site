package com.purpura.dao;

import com.purpura.common.Constants;
import com.purpura.exception.ConnectionFailedException;
import com.purpura.exception.NotFoundException;
import com.purpura.model.Model;
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
            System.out.println(sql);
            prepareStatementForSave(stmt, entidade);
            int rows = stmt.executeUpdate();
            if(rows == 0) {
                throw new NotFoundException(getNomeTabela(), entidade.getId());
            }

        } catch (SQLException e)  {
            e.printStackTrace();
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

    public void updateByAttribute(T entidade, String atributo, Object valor) throws NotFoundException, ConnectionFailedException{
        String sql = "UPDATE " + getNomeTabela() +
                " SET " + getNomesColunas() +
                " WHERE " + atributo + " = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            prepareStatementForUpdate(stmt, entidade);

            int numeroColunas = getNomesColunas().split(Constants.COMMA_SEPARATOR_REGEX).length;
            stmt.setObject(numeroColunas + 1, valor);
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

    public void delete(String id) throws NotFoundException, ConnectionFailedException{
        String sql = "DELETE FROM " + getNomeTabela() +
                " WHERE " + getColunaId() + " = ?";

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, id);
            int linhasDeletadas = stmt.executeUpdate();
            if (linhasDeletadas == 0) {
                throw new NotFoundException(getNomeTabela(), id);
            }
        } catch (SQLException e){
            throw new ConnectionFailedException();
        }
    }

    public void deleteByAttribute(String atributo, Object valor) throws NotFoundException, ConnectionFailedException{
        String sql = "DELETE FROM " + getNomeTabela() +
                " WHERE " + atributo + " = ?";

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setObject(1, valor);
            int linhasDeletadas = stmt.executeUpdate();
        } catch (SQLException e){
            throw new ConnectionFailedException();
        }
    }

    public T findById(int id) throws NotFoundException, ConnectionFailedException {
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

    public T findById(String id) throws NotFoundException, ConnectionFailedException {
        String sql = "SELECT * FROM " + getNomeTabela() +
                " WHERE " + getColunaId() + " = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSet(rs);
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new ConnectionFailedException();
        }
    }

    public T findByAttribute(String coluna, Object valor) throws NotFoundException, ConnectionFailedException {
        String sql = "SELECT * FROM " + getNomeTabela() +
                " WHERE " + coluna + " = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, valor);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSet(rs);
                } else {
                    throw new NotFoundException(getNomeTabela(), coluna);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ConnectionFailedException();
        }
    }

    public List<T> findAllByAttribute(String coluna, Object valor)
            throws ConnectionFailedException {
        String sql = "SELECT * FROM " + getNomeTabela() + " WHERE " + coluna + " = ?";
        List<T> resultados = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setObject(1, valor);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    resultados.add(mapResultSet(rs));
                }
            }
            return resultados;

        } catch (SQLException e) {
            e.printStackTrace();
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
            e.printStackTrace();
            throw new ConnectionFailedException();
        }

        if (lista.isEmpty()) {
            throw new NotFoundException(getNomeTabela(), -1);
        }

        return lista;
    }

    public ResultSet find() throws NotFoundException, ConnectionFailedException {
        String sql = "SELECT * FROM " + getNomeTabela();
        ResultSet rs = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            rs = stmt.executeQuery();  // Executa a consulta e obtém o ResultSet
        } catch (SQLException e) {
            throw new ConnectionFailedException();
        }

        // Verifica se o ResultSet está vazio
        try {
            if (rs != null && !rs.next()) {
                throw new NotFoundException(getNomeTabela(), -1);
            }
            // Volta o ponteiro para o início do ResultSet
            if (rs != null) {
                rs.beforeFirst(); // Vai para o começo, pois rs.next() já avançaria para a primeira linha
            }
        } catch (SQLException e) {
            throw new ConnectionFailedException();
        }

        return rs;  // Retorna o ResultSet
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