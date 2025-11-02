package com.purpura.exception;

public class NotFoundException extends PurpuraException {
    public NotFoundException(String nomeTabela, Object id) {
        super(
            String.format("Registro não encontrado: tabela=%s id=%s", nomeTabela, id),
            "Registro não encontrado.",
            "NOT_FOUND"
        );
    }
}
