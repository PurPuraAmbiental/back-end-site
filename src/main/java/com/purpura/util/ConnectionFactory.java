package com.purpura.util;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * ConnectionFactory: O guardião das conexões com o banco de dados.
 *
 * Esta classe centraliza a criação de conexões com PostgreSQL, garantindo que
 * todas as partes da aplicação acessem o banco de forma segura e consistente.
 *
 * Funcionalidades principais:
 * 1. Carrega as credenciais do banco (URL, usuário e senha) a partir das
 *    variáveis de ambiente ou de um arquivo .env, caso exista.
 *
 * 2. Valida a presença de todas as informações essenciais, lançando
 *    erros claros caso faltem.
 *
 * 3. Registra automaticamente o driver PostgreSQL.
 *
 *
 *
 * Autor: Kevin de Oliveira
 */
public class ConnectionFactory {

    private static final String URL;
    private static final String USER;
    private static final String PASSWORD;

    static {
        // Tenta carregar as variáveis do sistema (pro Render funcionar)
        Dotenv dotenv = null;
        try {
            dotenv = Dotenv.configure().ignoreIfMissing().load();
        } catch (Exception ignored) {
        }
        //pega as variaveis de ambiente
        URL = System.getenv("DB_URL") != null ? System.getenv("DB_URL") : (dotenv != null ? dotenv.get("DB_URL") : null);
        USER = System.getenv("DB_USER") != null ? System.getenv("DB_USER") : (dotenv != null ? dotenv.get("DB_USER") : null);
        PASSWORD = System.getenv("DB_PASSWORD") != null ? System.getenv("DB_PASSWORD") : (dotenv != null ? dotenv.get("DB_PASSWORD") : null);

        if (URL == null || USER == null || PASSWORD == null) {
            throw new IllegalStateException(
                    "Variáveis DB_URL, DB_USER ou DB_PASSWORD não foram configuradas."
            );
        }

        try {
            // Força o registro do driver PostgreSQL
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Erro ao carregar driver PostgreSQL", e);
        }
    }

    /**
     * Retorna uma nova conexão com o banco de dados.
     *
     * @return Connection pronta para uso
     * @throws SQLException caso haja problema ao conectar
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
