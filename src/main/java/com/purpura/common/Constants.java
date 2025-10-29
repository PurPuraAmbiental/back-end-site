package com.purpura.common;

/**
 * Classe responsável por armazenar constantes reutilizáveis em todo o sistema.
 * Inclui expressões regulares e outros padrões comuns.
 *
 * @author Bruna de Jesus
 * @author Kevin de Oliveira
 */
public class Constants {

    // Evita instanciação
    private Constants() {
        throw new UnsupportedOperationException("Classe utilitária - não deve ser instanciada.");
    }

    // Separador por vírgula
    public static final String COMMA_SEPARATOR_REGEX = "\\s*,\\s*";

    // Expressões regulares de validação
    public static final String EMAIL_REGEX = "^[a-zA-Z0-9._]+@[a-zA-Z0-9.-]+";
    public static final String TELEFONE_REGEX = "^\\(?[\\d]{2}\\)?\\s?[\\d]{5}-?[\\d]{4}$";
    public static final String CNPJ_REGEX = "^[0-9]{2}\\.?[0-9]{3}\\.?[0-9]{3}/?[0-9]{4}-?[0-9]{2}$";
    public static final String CEP_REGEX = "^[0-9]{5}-?[0-9]{3}$";
}