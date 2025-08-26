package com.purpura.dao;

import com.purpura.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class GenericDAOImpl<T> implements GenericDAO<T>{
    @Override
    public void deleteById(int id){
        String sql = "DELETE FROM " + getTableName() +
                " WHERE nCd" + getTableName() + " = ?";

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){{
                stmt.setInt(1, id);
                stmt.executeUpdate();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public abstract String getTableName();
}
