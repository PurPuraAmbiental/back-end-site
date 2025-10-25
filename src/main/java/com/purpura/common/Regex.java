package com.purpura.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**Metodos de regex para a validação de dados para a entrada do banco
 * @author Bruna Oliveira*/
public class Regex {

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
}
