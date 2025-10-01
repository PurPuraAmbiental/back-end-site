package com.purpura.exception;

public class NotFoundException extends PurpuraException {
    public NotFoundException(String nomeTabela, Object id) {
        super(String.format("Não encontrou na tabela %s o id %s", nomeTabela, id));
    }
}
