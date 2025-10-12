package com.purpura.model;

import java.text.ParseException;
import java.util.Map;

/**Classe Modelo para a tabela Administrador
 * @author Kevin Oliveira*/
public class Administrador implements Model{
    /**Adicionando os atributos da tabela como atributos da classe
     * atributos dados com prefixos da tabela de banco de dados*/
    private String cemail;
    private String csenha;
    private String cnmadministrador;

    /**Adicionando um método Construtor
     * @param cEmail - email do administrador
     * @param cSenha - senha do administrador
     * @param cCnpj - nome do administrador (inserido incorretamente como cCnpj)*/
    public Administrador(String cEmail, String cSenha, String cCnpj) {
        this.cemail = cEmail;
        this.csenha = cSenha;
        this.cnmadministrador = cCnpj;
    }

    /**Adicionando um método construtor que inicializa objetos
     * @param params -> um map com os parâmetros do administrador*/
    public Administrador(Map<String, String> params) throws ParseException {
        if(params.containsKey("cemail")) {
            this.cemail = params.get("cemail");
        }
        this.csenha = params.get("csenha");
        this.cnmadministrador = params.get("cnmadministrador");
    }

    /**Método getCEmail
     * @return email do administrador*/
    public String getCEmail() {return cemail;}
    /**Método getCSenha
     * @return senha do administrador*/
    public String getCSenha() {return csenha;}
    /**Método getCNmAdministrador
     * @return nome do administrador*/
    public String getCNmAdministrador() {return cnmadministrador;}

    /**Método setCEmail
     * @param cEmail -> altera o email do administrador*/
    public void setCEmail(String cEmail) {this.cemail = cEmail;}
    /**Método setCSenha
     * @param cSenha -> altera a senha do administrador*/
    public void setCSenha(String cSenha) {this.csenha = cSenha;}
    /**Método setCNmAdministrador
     * @param cCnpj -> altera o nome do administrador (parametro nomeado como cCnpj)*/
    public void setCNmAdministrador(String cCnpj) {this.cnmadministrador = cCnpj;}

    /**Método toString
     * @return informações sobre os campos da tabela Administrador*/
    @Override
    public String toString(){
        return "Email: " + cemail + "\nSenha: " + csenha + "\nNome: " + cnmadministrador;
    }

    /**Método getId
     * @return retorna o email como identificador da tabela*/
    @Override
    public Object getId(){
        return cemail;
    }
}