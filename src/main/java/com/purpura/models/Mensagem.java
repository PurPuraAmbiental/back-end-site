package com.purpura.models;

public class Mensagem implements Model{
    // atributos
    private final int nCdMensagem;
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

    // getters
    public int getNCdMensagem() {return nCdMensagem;}
    public String getCConteudo() {return cConteudo;}
    public String getNCdEmpresaRemetente() {return cCnpjRemetente;}
    public String getNCdEmpresaDestinataria() {return cCnpjDestinatario;}

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
