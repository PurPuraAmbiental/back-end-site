package com.purpura.models;

import java.util.Map;

public  class EnderecoEmpresa implements Model{
    //ADICIONANDO ATRIBUTOS DO MODELO LOGICO
    private int nCdEnderecoEmpresa;
    private String cBairro;
    private String cLogradouro;
    private String cEstado;
    private String cCidade;
    private String cCep;
    private String cComplemento;
    private int iNrEnderecoEmpresa;
    private String cCnpj;

    //ADICIONANDO METODO CONSTRUTOR
    public EnderecoEmpresa(int nCdEnderecoEmpresa, String cBairro, String cLogradouro, String cEstado, String cCidade, String cComplemento, String cCep, int iNrEnderecoEmpresa, String cCnpj) {
        this.nCdEnderecoEmpresa = nCdEnderecoEmpresa;
        this.cBairro = cBairro;
        this.cLogradouro = cLogradouro;
        this.cEstado = cEstado;
        this.cCidade = cCidade;
        this.cComplemento = cComplemento;
        this.cCep = cCep;
        this.iNrEnderecoEmpresa = iNrEnderecoEmpresa;
        this.cCnpj = cCnpj;
    }

    public EnderecoEmpresa(Map<String, String> params){
        if(params.containsKey("nCdEnderecoEmpresa")){
            this.nCdEnderecoEmpresa = Integer.parseInt(params.get("nCdEnderecoEmpresa"));
        }
        this.cBairro = params.get("cBairro");
        this.cLogradouro = params.get("cLogradouro");
        this.cEstado = params.get("cEstado");
        this.cCidade = params.get("cCidade");
        this.cComplemento = params.get("cComplemento");
        this.cCep = params.get("cCep");
        this.iNrEnderecoEmpresa = Integer.parseInt(params.get("iNrEnderecoEmpresa"));
        this.cCnpj = params.get("cCnpj");
    }

    //ADICIONANDO GETTERS
    public String getCBairro() {
        return cBairro;
    }

    public int getNCdEnderecoEmpresa() {
        return nCdEnderecoEmpresa;
    }

    public String getCCep() {
        return cCep;
    }

    public String getCCidade() {
        return cCidade;
    }

    public String getCEstado() {
        return cEstado;
    }

    public String getCComplemento() {
        return cComplemento;
    }

    public String getCLogradouro() {
        return cLogradouro;
    }

    public String getCCnpj() {return cCnpj;}
    public int getINrEnderecoEmpresa() {return iNrEnderecoEmpresa;}

    //ADICIONANDO OS SETTERS NECESSARIOS
    public void setCBairro(String bairro) {
        this.cBairro = bairro;
    }

    public void setCCep(String cCep) {
        this.cCep = cCep;
    }

    public void setCCidade(String cCidade) {
        this.cCidade = cCidade;
    }

    public void setCComplemento(String cComplemento) {
        this.cComplemento = cComplemento;
    }

    public void setCEstado(String cEstado) {
        this.cEstado = cEstado;
    }

    public void setClogradouro(String clogradouro) {
        this.cLogradouro = clogradouro;
    }

    //ADICIONANDO METODO TO STRING
    @Override
    public String toString(){
        return "\nCódigo: "+nCdEnderecoEmpresa+
                "\nLogradouro: "+cLogradouro+
                "\nNúmero: "+iNrEnderecoEmpresa+
                "\nCidade: "+cCidade+
                "\nBairro: "+cBairro+
                "\nEstado: "+ cEstado+
                "\nComplemento: " + cComplemento+
                "\nCEP: "+ cCep;
    }

    // métodos
    @Override
    public Object getId(){return nCdEnderecoEmpresa;}
}