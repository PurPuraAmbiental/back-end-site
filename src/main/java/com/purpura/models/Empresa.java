package com.purpura.models;

public class Empresa {
    private int nCdEmpresa;
    private String cNmEmpresa;
    private String cSenha;
    private String cCnpj;
    private char cAtivo;
    private String cEmail;
    private String cTelefone;

    public Empresa(int nCdEmpresa, String cNmEmpresa, String cSenha,
                   String cCnpj, char cAtivo, String cEmail,
                   String cTelefone) {
        this.nCdEmpresa = nCdEmpresa;
        this.cNmEmpresa = cNmEmpresa;
        this.cSenha = cSenha;
        this.cCnpj = cCnpj;
        this.cAtivo = cAtivo;
        this.cEmail = cEmail;
        this.cTelefone = cTelefone;
    }

    public int getNCdEmpresa() {return nCdEmpresa;}
    public String getCNmEmpresa() {return cNmEmpresa;}
    public String getCSenha() {return cSenha;}
    public String getCCnpj() {return cCnpj;}
    public char getCAtivo() {return cAtivo;}
    public String getCEmail() {return cEmail;}
    public String getCTelefone() {return cTelefone;}
}
