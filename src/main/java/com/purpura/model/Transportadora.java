package com.purpura.model;

import java.util.Map;

/**
 * Classe Modelo para a tabela Transportadora.
 * Representa os transportes cadastrados no sistema,
 * vinculados a pedidos e contendo informações sobre retirada.
 */
public class Transportadora implements Model {

    // ================== ATRIBUTOS ==================
    private String cCnpj;
    private String cNmTransportadora;
    private String cEmail;
    private String cRegiaoAtendida;

    // ================== CONSTRUTORES ==================

    /**
     * Construtor principal.
     * @param cCnpj - CNPJ da transportadora
     * @param cNmTransportadora - Nome da transportadora
     * @param cEmail - Email de contato
     * @param cRegiaoAtendida - Região atendida
     */
    public Transportadora(String cCnpj, String cNmTransportadora, String cEmail, String cRegiaoAtendida) {
        this.cCnpj = cCnpj;
        this.cNmTransportadora = cNmTransportadora;
        this.cEmail = cEmail;
        this.cRegiaoAtendida = cRegiaoAtendida;
    }

    /**
     * Construtor que inicializa a partir de um Map (usado em formulários ou servlets)
     * @param params - Mapa com os atributos da transportadora
     */
    public Transportadora(Map<String, String> params) {
        if (params.containsKey("cCnpj")) {
            this.cCnpj = params.get("cCnpj");
        }
        this.cNmTransportadora = params.get("cNmTransportadora");
        this.cRegiaoAtendida = params.get("cRegiaoAtendida");
        this.cEmail = params.get("cEmail");
    }

    // ================== GETTERS ==================
    public String getCCnpj() { return cCnpj; }
    public String getCNmTransportadora() { return cNmTransportadora; }
    public String getCRegiaoAtendida() { return cRegiaoAtendida; }
    public String getCEmail() { return cEmail; }

    // ================== SETTERS ==================
    public void setCCnpj(String cCnpj) { this.cCnpj = cCnpj; }
    public void setCNmTransportadora(String cNmTransportadora) { this.cNmTransportadora = cNmTransportadora; }
    public void setCRegiaoAtendida(String cRegiaoAtendida) { this.cRegiaoAtendida = cRegiaoAtendida; }
    public void setCEmail(String cEmail) { this.cEmail = cEmail; }

    // ================== MÉTODOS AUXILIARES ==================

    /**
     * Retorna uma string com os dados da transportadora
     */
    @Override
    public String toString() {
        return "CNPJ: " + cCnpj +
                "\nNome: " + cNmTransportadora +
                "\nRegião Atendida: " + cRegiaoAtendida +
                "\nEmail: " + cEmail;
    }

    /**
     * Retorna o ID da entidade (CNPJ)
     */
    @Override
    public Object getId() {
        return cCnpj;
    }
}
