package com.purpura.exception;

public class ModelNotFoundException extends RuntimeException {
    public ModelNotFoundException(String nomeTabela) {
        super("Nenhum model encontrado para a tabela: " + nomeTabela);
    }
}