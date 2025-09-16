package com.purpura.model;

import java.text.ParseException;
import java.util.Map;

public class Empresa implements Model{
    // atributos
    private String cNmEmpresa;
    private String cSenha;
    private String cCnpj;
    private char cAtivo;
    private String cEmail;
    private String cTelefone;

    // construtor
    public Empresa(String cNmEmpresa, String cSenha, String cCnpj,
                   char cAtivo, String cEmail, String cTelefone) {
        this.cNmEmpresa = cNmEmpresa;
        this.cSenha = cSenha;
        this.cCnpj = cCnpj;
        this.cAtivo = cAtivo;
        this.cEmail = cEmail;
        this.cTelefone = cTelefone;
    }

    public Empresa(Map<String, String> params) throws ParseException {
        if(params.containsKey("cCnpj")) {
            this.cCnpj = params.get("cCnpj");
        }
        this.cNmEmpresa = params.get("cNmEmpresa");
        this.cSenha = params.get("cSenha");
        if (params.containsKey("cAtivo") && params.get("cAtivo") != null && !params.get("cAtivo").isEmpty()) {
            this.cAtivo = params.get("cAtivo").charAt(0);
        }
        this.cEmail = params.get("cEmail");
        this.cTelefone = params.get("cTelefone");
    }

    // getters
    public String getCNmEmpresa() {return cNmEmpresa;}
    public String getCSenha() {return cSenha;}
    public String getCCnpj() {return cCnpj;}
    public char getCAtivo() {return cAtivo;}
    public String getCEmail() {return cEmail;}
    public String getCTelefone() {return cTelefone;}

    // setters
    public void setCNmEmpresa(String cNmEmpresa) {this.cNmEmpresa = cNmEmpresa;}
    public void setCSenha(String cSenha) {this.cSenha = cSenha;}
    public void setCAtivo(char cAtivo) {this.cAtivo = cAtivo;}
    public void setCEmail(String cEmail) {this.cEmail = cEmail;}
    public void setCTelefone(String cTelefone) {this.cTelefone = cTelefone;}

    // toString
    @Override
    public String toString(){
        return "CNPJ: " + cCnpj + "\nNome: " + cNmEmpresa +
                "\nSenha: " + cSenha + "\nAtivo: " + cAtivo +
                "\nEmail: " + cEmail + "\nTelefone: " + cTelefone;
    }

    // métodos
    @Override
    public Object getId(){return cCnpj;}
}
