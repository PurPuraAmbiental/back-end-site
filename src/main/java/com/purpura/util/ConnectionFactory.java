package com.purpura.util;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final Dotenv dotenv = Dotenv.load();

    // Lê as variáveis de ambiente do sistema
    private static String URL = dotenv.get("DB_URL");
    private static final String USER = dotenv.get("DB_USER");
    private static final String PASSWORD = dotenv.get("DB_PASSWORD");

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
