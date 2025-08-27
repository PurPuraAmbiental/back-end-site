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
    public void deleteById(int id){
        String sql = "DELETE FROM " + getTableName() +
                " WHERE nCd" + getTableName() + " = ?";

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
                stmt.setInt(1, id);
                stmt.executeUpdate();
            } catch (SQLException e){
            e.printStackTrace();
        }
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
    public abstract T mapResultSet(ResultSet rs) throws SQLException;
}
