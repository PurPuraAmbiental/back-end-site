package com.purpura.util;

import java.sql.*;

public class TestConnection {
    public static void main(String[] args) {
        try (Connection conn = ConnectionFactory.getConnection()) {
            if (conn != null && !conn.isClosed()) {
                System.out.println("Conexão com o banco estabelecida com sucesso!");
            } else {
                System.out.println(" Não foi possível estabelecer a conexão.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco: " + e.getMessage());
        }
    }
}