package com.purpura.exception;

public abstract class PurpuraException extends RuntimeException {
    private final String mensagemUsuario;
    private final String codigoErro;

    // construtor completo
    protected PurpuraException(String message, String mensagemUsuario, String codigoErro, Throwable cause) {
        super(message, cause);
        this.mensagemUsuario = mensagemUsuario != null ? mensagemUsuario : message;
        this.codigoErro = codigoErro;
    }

    // Construtor sem cause
    protected PurpuraException(String message, String mensagemUsuario, String codigoErro) {
        this(message, mensagemUsuario, codigoErro, null);
    }

    public String getMensagemUsuario() { return mensagemUsuario; }
    public String getCodigoErro() { return codigoErro; }
}