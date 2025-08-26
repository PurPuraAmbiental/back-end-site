package com.purpura.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    // Lê as variáveis de ambiente do sistema
    private static final String URL = System.getenv("DB_URL");
    private static final String USER = System.getenv("DB_USER");
    private static final String PASSWORD = System.getenv("DB_PASSWORD");

    // Retorna uma conexão válida
    public static Connection getConnection() throws SQLException {
        if (URL == null || USER == null || PASSWORD == null) {
            throw new IllegalStateException(
                    "Variáveis de ambiente DB_URL, DB_USER ou DB_PASSWORD não foram configuradas."
            );
        }

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
