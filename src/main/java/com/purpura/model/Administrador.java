package com.purpura.model;

import java.text.ParseException;
import java.util.Map;

public class Administrador implements Model{
    // atributos
    private String cEmail;
    private String cSenha;
    private String cNmAdministrador;

    // construtores
    public Administrador(String cEmail, String cSenha, String cCnpj) {
        this.cEmail = cEmail;
        this.cSenha = cSenha;
        this.cNmAdministrador = cCnpj;
    }

    public Administrador(Map<String, String> params) throws ParseException {
        if(params.containsKey("cEmail")) {
            this.cEmail = params.get("cEmail");
        }
        this.cSenha = params.get("cSenha");
        this.cNmAdministrador = params.get("cNmAdministrador");
    }

    // getters
    public String getCEmail() {return cEmail;}
    public String getCSenha() {return cSenha;}
    public String getCNmAdministrador() {return cNmAdministrador;}

    // setters
    public void setCEmail(String cEmail) {this.cEmail = cEmail;}
    public void setCSenha(String cSenha) {this.cSenha = cSenha;}
    public void setCNmAdministrador(String cCnpj) {this.cNmAdministrador = cCnpj;}

    // toString
    @Override
    public String toString(){
        return "Email: " + cEmail + "\nSenha: " + cSenha + "\nNome: " + cNmAdministrador;
    }

    // métodos
    @Override
    public Object getId(){
        return cEmail;
    }
}
