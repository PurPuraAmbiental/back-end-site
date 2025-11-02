package com.purpura.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe utilitária para validação de dados usando expressões regulares.
 *
 * =========================== ESSA CLASSE FAZ USO DE REGEX ============================
 *  Esta classe não deve ser instanciada.
 *
 * Autor: Letícia Reis
 */
public class Regex {

    /**
     * Construtor privado para evitar instanciação da classe utilitária.
     */
    private Regex() {
        throw new UnsupportedOperationException("Classe utilitária - não deve ser instanciada.");
    }

    // ----- MÉTODOS DE VALIDAÇÃO -----

    /**
     * Valida se a string fornecida é um CNPJ válido.
     *
     * @param cnpj o CNPJ a ser validado
     * @return true se o CNPJ for válido, false caso contrário
     */
    public static boolean validarCnpj(String cnpj){
        String regex = Constants.CNPJ_REGEX;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cnpj);
        return matcher.find();
    }

    /**
     * Valida se a string fornecida é um telefone válido.
     *
     * @param telefone o telefone a ser validado
     * @return true se o telefone for válido, false caso contrário
     */
    public static boolean validarTelefone(String telefone){
        String regex = Constants.TELEFONE_REGEX;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(telefone);
        return matcher.find();
    }

    /**
     * Valida se a string fornecida é um email válido.
     *
     * @param email o email a ser validado
     * @return true se o email for válido, false caso contrário
     */
    public static boolean validarEmail(String email){
        String regex = Constants.EMAIL_REGEX;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }

    /**
     * Valida se a string fornecida é um CEP válido.
     *
     * @param cep o CEP a ser validado
     * @return true se o CEP for válido, false caso contrário
     */
    public static boolean validarCEP(String cep){
        String regex = Constants.CEP_REGEX;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cep);
        return matcher.find();
    }
}
