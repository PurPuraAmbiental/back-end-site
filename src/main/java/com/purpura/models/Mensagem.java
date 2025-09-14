package com.purpura.models;

import java.util.Map;

public class Mensagem implements Model{
    // atributos
    private int nCdMensagem;
    private final String cConteudo; // atributos final para garantir que mensagem não seja alterada
    private final String cCnpjRemetente; // armazenam o código
    private final String cCnpjDestinatario;

    // construtor
    public Mensagem(int nCdMensagem, String cConteudo, String cCnpjRemetente,
                    String cCnpjDestinatario) {
        this.nCdMensagem = nCdMensagem;
        this.cConteudo = cConteudo;
        this.cCnpjRemetente = cCnpjRemetente;
        this.cCnpjDestinatario = cCnpjDestinatario;
    }

    public Mensagem(Map<String, String> params) {
        if(params.containsKey("nCdMensagem")) {
            this.nCdMensagem = Integer.parseInt(params.get("nCdMensagem"));
        }
        this.cConteudo = params.get("cConteudo");
        this.cCnpjRemetente = params.get("cCnpjRemetente");
        this.cCnpjDestinatario = params.get("cCnpjDestinatario");
    }

    // getters
    public int getNCdMensagem() {return nCdMensagem;}
    public String getCConteudo() {return cConteudo;}
    public String getCCnpjRemetente() {return cCnpjRemetente;}
    public String getCCnpjDestinatario() {return cCnpjDestinatario;}

    // sem setters, pois o conteúdo da mensagem e quem a enviou não podem ser alterados

    // toString
    @Override
    public String toString() {
        return "Código: " + nCdMensagem + "\nConteúdo: " + cConteudo +
                "\nEmpresa Remetente: " + cCnpjRemetente + "\nEmpresa Destinatária: " + cCnpjDestinatario;
    }

    // métodos
    @Override
    public Object getId(){return nCdMensagem;}
}