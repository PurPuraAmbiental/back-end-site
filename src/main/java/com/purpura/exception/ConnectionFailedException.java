package com.purpura.exception;

public class ConnectionFailedException extends PurpuraException {
    public ConnectionFailedException(Throwable cause) {
        super(
            "Falha de conexão com a base de dados.",
            "Não foi possível conectar ao serviço. Tente novamente mais tarde.",
            "DB_CONNECTION_FAILED",
            cause
        );
    }

    public ConnectionFailedException() {
        this(null);
    }
}
