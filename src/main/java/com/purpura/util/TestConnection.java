package com.purpura.util;

import java.sql.*;

/**
 * TestConnection: classe utilitária para testar a conexão com o banco de dados.
 *
 * Esta classe serve apenas para fins de manutenção e verificação da conexão.
 * Não é utilizada em produção ou lógica de negócio da aplicação.
 *
 * @author Kevin de Oliveira
 */
public class TestConnection {
    public boolean testar() {
        try (Connection conn = ConnectionFactory.getConnection()) {
            if (conn != null && !conn.isClosed()) {
                System.out.println("Conexão com o banco estabelecida com sucesso!");
                return true;
            } else {
                System.out.println("Não foi possível estabelecer a conexão.");
            }
            return false;
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco: " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        TestConnection testConnection = new TestConnection();
        System.out.println(testConnection.testar());
    }
}
