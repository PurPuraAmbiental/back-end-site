package com.purpura.dao;

import com.purpura.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class GenericDAOImpl<T> implements GenericDAO<T>{
    @Override
    public boolean delete(int id){
        String sql = "DELETE FROM " + getTableName() +
                " WHERE nCd" + getTableName() + " = ?";

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
    public List<T> findAll() {
        List<T> lista = new ArrayList<>();
        String sql = "SELECT * FROM " + getTableName();

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

    public abstract String getTableName();
    protected abstract T mapResultSet(ResultSet rs) throws SQLException;
}
