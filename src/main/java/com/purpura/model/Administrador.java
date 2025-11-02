package com.purpura.model;

import java.text.ParseException;
import java.util.Map;

/**
 * Modelo que representa a entidade Administrador.
 * Cada instância corresponde a um registro na tabela "Administrador".
 *
 * @author Kevin Oliveira
 */
public class Administrador implements Model {
    /**Adicionando os atributos da tabela como atributos da classe.
     * atributos dados com prefixos da tabela de banco de dado*/

    private String cEmail;
    private String cSenha;
    private String cNmAdministrador;

    /**
     * Construtor padrão.
     *
     * @param cEmail email do administrador (chave primária)
     * @param cSenha senha do administrador
     * @param cNmAdministrador nome do administrador
     */
    public Administrador(String cEmail, String cSenha, String cNmAdministrador) {
        this.cEmail = cEmail;
        this.cSenha = cSenha;
        this.cNmAdministrador = cNmAdministrador;
    }

    /**
     * Construtor alternativo que inicializa o objeto a partir de um mapa de parâmetros, captura
     * dados de formularios, por exemplo.
     *
     * @param params mapa contendo os campos do administrador
     * @throws ParseException se algum valor não puder ser processado corretamente
     */
    public Administrador(Map<String, String> params) throws ParseException {
        this.cEmail = params.get("cEmail");
        this.cSenha = params.get("cSenha");
        this.cNmAdministrador = params.get("cNmAdministrador");
    }

    // Getters e Setters
    public String getCEmail() { return cEmail; }
    public void setCEmail(String cEmail) { this.cEmail = cEmail; }

    public String getCSenha() { return cSenha; }
    public void setCSenha(String cSenha) { this.cSenha = cSenha; }

    public String getCNmAdministrador() { return cNmAdministrador; }
    public void setCNmAdministrador(String cNmAdministrador) { this.cNmAdministrador = cNmAdministrador; }

    @Override
    public String toString() {
        return "Email: " + cEmail + "\nSenha: " + cSenha + "\nNome: " + cNmAdministrador;
    }

    /**
     * Retorna o identificador único da entidade.
     * Esse valor é utilizado pela DAO genérica para operações de persistência.
     */
    @Override
    public Object getId() {
        return cEmail;
    }
}
