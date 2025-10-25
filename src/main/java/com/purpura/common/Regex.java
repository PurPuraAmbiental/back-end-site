package com.purpura.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe utilitária para validação de dados usando expressões regulares.
 * Fornece métodos estáticos para validação de CNPJ, telefone, email e senha.
 *
 * @author Bruna de Jesus
 */
public class Regex {

    // Evita instanciação
    private Regex() {
        throw new UnsupportedOperationException("Classe utilitária - não deve ser instanciada.");
    }

    // ----- MÉTODOS DE VALIDAÇÃO -----

    public static boolean validarCnpj(String cnpj){
        String regex = Constants.CNPJ_REGEX;
        Pattern patternt = Pattern.compile(regex);
        Matcher matcher = patternt.matcher(cnpj);
        return matcher.find();
    }

    public boolean validarTelefone(String telefone){
        String regex = Constants.TELEFONE_REGEX;
        Pattern patternt = Pattern.compile(regex);
        Matcher matcher = patternt.matcher(telefone);
        return matcher.find();
    }

    public static boolean validarEmail(String email){
        String regex = Constants.EMAIL_REGEX;
        Pattern patternt = Pattern.compile(regex);
        Matcher matcher = patternt.matcher(email);
        return matcher.find();
    }

    public static boolean validarSenha(String senha){
        String regex = Constants.SENHA_REGEX;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(senha);
        return matcher.find();
    }
}