package com.purpura.models;

import java.util.Map;

public class Residuo implements Model{
    // atributos
    private int nCdResiduo;
    private String cNmResiduo;
    private String cTipoUnidade;
    private double nPrecoPadrao;
    private double nVolumePadrao;
    private String cCategoria;
    private String cDescricao;
    private String cCnpj;

    // construtor
    public Residuo(int nCdResiduo, String cNmResiduo, String cTipoUnidade,
                   double nPrecoPadrao, double nVolumePadrao, String cCategoria,
                   String cDescricao, String cCnpj){
        this.nCdResiduo = nCdResiduo;
        this.cNmResiduo = cNmResiduo;
        this.cTipoUnidade = cTipoUnidade;
        this.nPrecoPadrao = nPrecoPadrao;
        this.nVolumePadrao = nVolumePadrao;
        this.cCategoria = cCategoria;
        this.cDescricao = cDescricao;
        this.cCnpj = cCnpj;
    }

    public Residuo(Map<String, String> params) {
        if(params.containsKey("nCdResiduo")) {
            this.nCdResiduo = Integer.parseInt(params.get("nCdResiduo"));
        }
        this.cNmResiduo = params.get("cNmResiduo");
        this.cTipoUnidade = params.get("cTipoUnidade");
        this.nPrecoPadrao = Double.parseDouble(params.get("nPrecoPadrao"));
        this.nVolumePadrao = Double.parseDouble(params.get("nVolumePadrao"));
        this.cCategoria = params.get("cCategoria");
        this.cDescricao = params.get("cDescricao");
        this.cCnpj = params.get("cCnpj");
    }
    // getters
    public int getNCdResiduo() {return nCdResiduo;}
    public String getCNmResiduo() {return cNmResiduo;}
    public String getCTipoUnidade() {return cTipoUnidade;}
    public double getNPrecoPadrao() {return nPrecoPadrao;}
    public double getNVolumePadrao() {return nVolumePadrao;}
    public String getCCategoria() {return cCategoria;}
    public String getCDescricao() {return cDescricao;}
    public String getCCnpj() {return cCnpj;}

    // setters
    public void setCNmResiduo(String cNmResiduo) {this.cNmResiduo = cNmResiduo;}
    public void setCTipoUnidade(String cTipoUnidade) {this.cTipoUnidade = cTipoUnidade;}
    public void setNPrecoPadrao(double nPrecoPadrao) {this.nPrecoPadrao = nPrecoPadrao;}
    public void setNVolumePadrao(double nVolumePadrao) {this.nVolumePadrao = nVolumePadrao;}
    public void setCCategoria(String cCategoria) {this.cCategoria = cCategoria;}
    public void setCDescricao(String cDescricao) {this.cDescricao = cDescricao;}
    public void setCCnpj(String cCnpj) {this.cCnpj = cCnpj;}

    // toString
    @Override
    public String toString(){
        return "Código: " + nCdResiduo + "\nNome: " + cNmResiduo +
                "\nTipo Unidade: " + cTipoUnidade + "\nPreço Padrão: " + nPrecoPadrao +
                "\nVolume Padrão: " + nVolumePadrao + "\nCategoria: " + cCategoria +
                "\nDescrição: " + cDescricao + "\nCNPJ: " + cCnpj;
    }

    // métodos
    @Override
    public Object getId(){return nCdResiduo;}
}