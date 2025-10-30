package com.purpura.model;

import java.text.ParseException;
import java.util.Map;

public class ChaveAcesso implements Model{
    private int nCdChaveAcesso;
    private String cHash;
    private char cAtivo;

    public ChaveAcesso(int nCdChaveAcesso, String cHash, char cAtivo) {
        this.nCdChaveAcesso = nCdChaveAcesso;
        this.cHash = cHash;
        this.cAtivo = cAtivo;
    }

    public ChaveAcesso(Map<String, String> params) throws ParseException {
        if(params.containsKey("nCdChaveAcesso")) {
            this.nCdChaveAcesso = Integer.parseInt(params.get("nCdChaveAcesso"));
        }
        this.cHash = params.get("cChave");
        if (params.containsKey("cAtivo") && params.get("cAtivo") != null && !params.get("cAtivo").isEmpty()) {
            this.cAtivo = params.get("cAtivo").charAt(0);
        }
    }

    public int getNCdChaveAcesso() {
        return nCdChaveAcesso;
    }

    public String getCHash() {
        return cHash;
    }

    public char getCAtivo() {
        return cAtivo;
    }

    public void setCAtivo(char cAtivo) {
        this.cAtivo = cAtivo;
    }

    @Override
    public String toString() {
        return "ChaveAcesso{" +
                "nCdChaveAcesso=" + nCdChaveAcesso +
                ", cHash='" + cHash + '\'' +
                ", cAtivo=" + cAtivo +
                '}';
    }

    public Object getId(){
        return nCdChaveAcesso;
    }
}