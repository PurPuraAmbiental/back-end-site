package com.purpura.model;

import java.text.ParseException;
import java.util.Map;
import java.util.StringTokenizer;

public class Telefone implements Model{
    private int nCdTelefone;
    private String fone;
    private String descricao;
    private int nCdEmpresa;

    public Telefone(int nCdEmpresa, int nCdTelefone, String fone, String descricao){
        this.nCdEmpresa = nCdEmpresa;
        this.nCdTelefone = nCdTelefone;
        this.fone = fone;
        this.descricao = descricao;
    }

    public Telefone(Map<String, String> params) throws ParseException {
        if(params.containsKey("nCdTelefone")) {
            this.fone = params.get("nCdTelefone");
        }
        this.fone = params.get("fone");
        this.descricao = params.get("descricao");
        if(params.containsKey("nCdEmpresa")) {
            this.nCdEmpresa = Integer.parseInt(params.get("nCdEmpresa"));
        }
    }

    public int getnCdEmpresa() {
        return nCdEmpresa;
    }

    public int getnCdTelefone() {
        return nCdTelefone;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String toString(){
        return "Telefone: "+this.getnCdTelefone()+"\nDescricao: "+this.descricao;
    }

    @Override
    public Object getId() {
        return nCdTelefone;
    }
}
