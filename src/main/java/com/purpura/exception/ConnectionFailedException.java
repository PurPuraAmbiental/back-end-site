package com.purpura.exception;

public class ConnectionFailedException extends RuntimeException {
    public ConnectionFailedException() {
        super("Erro de conexão com a Internet/Banco de Dados.");
    }
}
