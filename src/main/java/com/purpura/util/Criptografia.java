package com.purpura.util;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Criptografia: responsável por gerar hashes seguros para senhas.
 *
 * Utiliza o algoritmo BCrypt para transformar senhas em valores criptografados,
 * garantindo que não sejam armazenadas em texto puro e aumentando a segurança da aplicação.
 *
 * Autor: Kevin de Oliveira
 */
public class Criptografia {
    private static final int cost = 10;

    /**
     * Criptografa uma senha em texto puro usando BCrypt.
     *
     * @param senhaLimpa a senha em texto puro
     * @return hash seguro gerado pelo BCrypt
     */
    public static String criptografar(String senhaLimpa){
        String hash = BCrypt.hashpw(senhaLimpa, BCrypt.gensalt(cost));
        return hash;
    }
}
