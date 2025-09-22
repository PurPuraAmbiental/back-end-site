package com.purpura.model;

import java.text.ParseException;
import java.util.Map;
/**Classe modelo para a tabela Empresa
 *@author Kevin Oliveira*/
public class Empresa implements Model{
    /**Adicionando os atributos da tabela como atributos da classe
     * atributos dados com prefixo da tabela de banco de dados*/
    private String cNmEmpresa;
    private String cSenha;
    private String cCnpj;
    private char cAtivo;
    private String cEmail;
    private String cTelefone;

    /**Adicionando metodo Construtor
     * @param cCnpj - atributo identificador da tabela
     * @param cAtivo - status da empresa
     * @param cEmail - email da empresa
     * @param cNmEmpresa - nome da empresa
     * @param cSenha - senha do email da empresa
     * @param cTelefone - telefone da empresa*/
    public Empresa(String cNmEmpresa, String cSenha, String cCnpj,
                   char cAtivo, String cEmail, String cTelefone) {
        this.cNmEmpresa = cNmEmpresa;
        this.cSenha = cSenha;
        this.cCnpj = cCnpj;
        this.cAtivo = cAtivo;
        this.cEmail = cEmail;
        this.cTelefone = cTelefone;
    }
    /**contrutor que inicializa objetos
     * @param params -> um map*/
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
    /**Metodo getCNmEmpresa
     * @return primary key da tabela*/
    public String getCNmEmpresa() {return cNmEmpresa;}
    /**Metodo getCSenha
     * @return senha da conta da empresa*/
    public String getCSenha() {return cSenha;}
    /**Metodo getCCnpj
     * @return cnpj da empresa*/
    public String getCCnpj() {return cCnpj;}
    /**Metodo getCAtivo
     * @return se a empresa esta ativa ou nao*/
    public char getCAtivo() {return cAtivo;}
    /**Metodo getCEmail
     * @return o email da empresa*/
    public String getCEmail() {return cEmail;}
    /**Metodo getCTelefone
     * @return telefone da empresa*/
    public String getCTelefone() {return cTelefone;}

    /**Metodo setCNmEmpresa
     * @param cNmEmpresa */
    public void setCNmEmpresa(String cNmEmpresa) {this.cNmEmpresa = cNmEmpresa;}
    /**Metodo setCSenha
     * @param cSenha */
    public void setCSenha(String cSenha) {this.cSenha = cSenha;}
    /**Metodo setCAtivo
     * @param cAtivo */
    public void setCAtivo(char cAtivo) {this.cAtivo = cAtivo;}
    /**Metodo setCEmail
     * @param cEmail */
    public void setCEmail(String cEmail) {this.cEmail = cEmail;}
    /**Metodo setCTelefone
     * @param cTelefone */
    public void setCTelefone(String cTelefone) {this.cTelefone = cTelefone;}

    /**Adicionando o metodo toString
     * @return informações sobre os campos das tabelas*/
    @Override
    public String toString(){
        return "CNPJ: " + cCnpj + "\nNome: " + cNmEmpresa +
                "\nSenha: " + cSenha + "\nAtivo: " + cAtivo +
                "\nEmail: " + cEmail + "\nTelefone: " + cTelefone;
    }

    /**Metodo getID
     * @return retorna o codigo da tabela*/
    @Override
    public Object getId(){return cCnpj;}
}
