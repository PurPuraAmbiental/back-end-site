package com.purpura.common;

public class Constants {
    public static final String COMMA_SEPARATOR_REGEX = "\\s*,\\s*";
    public static final String EMAIL_REGEX = "^[a-zA-Z0-9._]+@[a-zA-Z0-9.-]+";
    public static final String TELEFONE_REGEX = "([\\d]{2})\\s?[\\d]{5}-[\\d]{4}";
    public static final String CNPJ_REGEX = "[0-9]{2}\\.?[0-9]{3}\\.?[0-9]{3]/?[0-9]{4}-?[0-9]{2}";
    public static final String SENHA_REGEX = "[\\w]{6,}";
}

