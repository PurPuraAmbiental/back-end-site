package com.purpura.models;

public class Mensagem {
    // atributos
    private final int nCdMensagem;
    private final String cConteudo; // atributos final para garantir que mensagem não seja alterada
    private final int nCdEmpresaRemetente; // armazenam o código
    private final int nCdEmpresaDestinataria;

    // construtor
    public Mensagem(int nCdMensagem, String cConteudo, int nCdEmpresaRemetente,
                    int nCdEmpresaDestinataria) {
        this.nCdMensagem = nCdMensagem;
        this.cConteudo = cConteudo;
        this.nCdEmpresaRemetente = nCdEmpresaRemetente;
        this.nCdEmpresaDestinataria = nCdEmpresaDestinataria;
    }

    // getters
    public int getNCdMensagem() {return nCdMensagem;}
    public String getCConteudo() {return cConteudo;}
    public int getNCdEmpresaRemetente() {return nCdEmpresaRemetente;}
    public int getNCdEmpresaDestinataria() {return nCdEmpresaDestinataria;}

    // sem setters, pois o conteúdo da mensagem e quem a enviou não podem ser alterados

    // toString
    @Override
    public String toString() {
        return "Código: " + nCdMensagem + "\nConteúdo: " + cConteudo +
                "\nEmpresa Remetente: " + nCdEmpresaRemetente + "\nEmpresa Destinatária: " + nCdEmpresaDestinataria;
    }
}
