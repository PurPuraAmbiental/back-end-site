package com.purpura.model;

import java.text.ParseException;
import java.util.Map;
import java.util.StringTokenizer;

public class Telefone implements Model{
    private int nCdTelefone;
    private String cfone;
    private String cdescricao;
    private int nCdEmpresa;

    public Telefone(int nCdEmpresa, int nCdTelefone, String fone, String descricao){
        this.nCdEmpresa = nCdEmpresa;
        this.nCdTelefone = nCdTelefone;
        this.cfone = fone;
        this.cdescricao = descricao;
    }

    public Telefone(Map<String, String> params) throws ParseException {
        if(params.containsKey("nCdTelefone")) {
            this.cfone = params.get("nCdTelefone");
        }
        this.cfone = params.get("fone");
        this.cdescricao = params.get("descricao");
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

    public String getCDescricao() {
        return cdescricao;
    }

    public String getCFone() {
        return cfone;
    }

    public void setFone(String fone) {
        this.cfone = fone;
    }

    public void setDescricao(String descricao) {
        this.cdescricao = descricao;
    }
    public String toString(){
        return "Telefone: "+this.getnCdTelefone()+"\nDescricao: "+this.cdescricao;
    }

    @Override
    public Object getId() {
        return nCdTelefone;
    }
}
