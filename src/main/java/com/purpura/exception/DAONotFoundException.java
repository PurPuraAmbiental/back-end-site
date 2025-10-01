package com.purpura.exception;

public class DAONotFoundException extends RuntimeException {
    public DAONotFoundException(String nomeTabela) {
        super("Nenhum DAO encontrado para a tabela: " + nomeTabela);
    }
}
