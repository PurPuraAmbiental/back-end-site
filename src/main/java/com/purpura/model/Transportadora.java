package com.purpura.model;

import java.util.Map;

/**
 * Modelo que representa a entidade Transportadora.
 * Cada instância corresponde a um registro na tabela "Transportadora".
 * Contém informações de identificação, contato e região atendida.
 *
 * Autor: Bruna de Jesus
 */
public class Transportadora implements Model {

    // Atributos que representam as colunas da tabela "Transportadora"
    private String cCnpj;
    private String cNmTransportadora;
    private String cEmail;
    private String cRegiaoAtendida;

    /**
     * Construtor principal.
     *
     * @param cCnpj CNPJ da transportadora
     * @param cNmTransportadora nome da transportadora
     * @param cEmail email de contato
     * @param cRegiaoAtendida região atendida
     */
    public Transportadora(String cCnpj, String cNmTransportadora, String cEmail, String cRegiaoAtendida) {
        this.cCnpj = cCnpj;
        this.cNmTransportadora = cNmTransportadora;
        this.cEmail = cEmail;
        this.cRegiaoAtendida = cRegiaoAtendida;
    }

    /**
     * Construtor que inicializa o objeto a partir de um mapa de parâmetros,
     * permitindo capturar dados de formulários ou requisições HTTP.
     *
     * @param params mapa contendo os campos da transportadora
     */
    public Transportadora(Map<String, String> params) {
        if (params.containsKey("cCnpj")) {
            this.cCnpj = params.get("cCnpj");
        }
        this.cNmTransportadora = params.get("cNmTransportadora");
        this.cRegiaoAtendida = params.get("cRegiaoAtendida");
        this.cEmail = params.get("cEmail");
    }

    // Getters e Setters
    public String getCCnpj() { return cCnpj; }
    public void setCCnpj(String cCnpj) { this.cCnpj = cCnpj; }

    public String getCNmTransportadora() { return cNmTransportadora; }
    public void setCNmTransportadora(String cNmTransportadora) { this.cNmTransportadora = cNmTransportadora; }

    public String getCRegiaoAtendida() { return cRegiaoAtendida; }
    public void setCRegiaoAtendida(String cRegiaoAtendida) { this.cRegiaoAtendida = cRegiaoAtendida; }

    public String getCEmail() { return cEmail; }
    public void setCEmail(String cEmail) { this.cEmail = cEmail; }

    /**
     * Retorna uma representação textual da entidade.
     *
     * @return string contendo os valores dos atributos da transportadora
     */
    @Override
    public String toString() {
        return "CNPJ: " + cCnpj +
                "\nNome: " + cNmTransportadora +
                "\nRegião Atendida: " + cRegiaoAtendida +
                "\nEmail: " + cEmail;
    }

    /**
     * Retorna o identificador único da entidade.
     *
     * @return CNPJ da transportadora
     */
    @Override
    public Object getId() {
        return cCnpj;
    }
}
