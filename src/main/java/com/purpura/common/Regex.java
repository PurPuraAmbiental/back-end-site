package com.purpura.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**Metodos de regex para a validação de dados para a entrada do banco
 * @author Bruna Oliveira*/
public class Regex {

    public boolean validarCnpj(String cnpj){
    String regex = Constants.COMMA_SEPARATOR_REGEX;
        Pattern patternt = Pattern.compile(regex);
        Matcher matcher = patternt.matcher(cnpj);
        if (matcher.find()){
            return true;
        }else {
            return false;
        }
    }

    public boolean validarTelefone(String telefone){
        String regex = Constants.TELEFONE_REGEX;
        Pattern patternt = Pattern.compile(regex);
        Matcher matcher = patternt.matcher(telefone);
        if (matcher.find()){
            return true;
        }else {
            return false;
        }
    }

}
