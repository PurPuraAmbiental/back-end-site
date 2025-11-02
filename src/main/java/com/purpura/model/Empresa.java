package com.purpura.model;

import java.text.ParseException;
import java.util.Map;

/**
 * Modelo que representa a entidade Empresa.
 * Cada instância corresponde a um registro na tabela "Empresa".
 *
 * Autor: Kevin Oliveira
 */
public class Empresa implements Model {
    /**Adicionando os atributos da tabela como atributos da classe.
     * atributos dados com prefixos da tabela de banco de dado*/

    private String cNmEmpresa;
    private String cSenha;
    private String cCnpj;
    private char cAtivo;
    private String cEmail;

    /**
     * Construtor padrão.
     *
     * @param cNmEmpresa nome da empresa
     * @param cSenha senha da conta da empresa
     * @param cCnpj CNPJ da empresa (chave primária)
     * @param cAtivo indica se a empresa está ativa ('S' ou 'N')
     * @param cEmail email da empresa
     */
    public Empresa(String cNmEmpresa, String cSenha, String cCnpj,
                   char cAtivo, String cEmail) {
        this.cNmEmpresa = cNmEmpresa;
        this.cSenha = cSenha;
        this.cCnpj = cCnpj;
        this.cAtivo = cAtivo;
        this.cEmail = cEmail;
    }

    /**
     * Construtor alternativo que inicializa o objeto a partir de um mapa de parâmetros,
     * permitindo capturar dados de formulários ou requisições HTTP.
     *
     * @param params mapa contendo os campos da empresa
     * @throws ParseException se algum valor não puder ser processado corretamente
     */
    public Empresa(Map<String, String> params) throws ParseException {
        if (params.containsKey("cCnpj")) {
            this.cCnpj = params.get("cCnpj");
        }
        this.cNmEmpresa = params.get("cNmEmpresa");
        this.cSenha = params.get("cSenha");
        if (params.containsKey("cAtivo") && params.get("cAtivo") != null && !params.get("cAtivo").isEmpty()) {
            this.cAtivo = params.get("cAtivo").charAt(0);
        }
        this.cEmail = params.get("cEmail");
    }

    // Getters e Setters
    public String getCNmEmpresa() { return cNmEmpresa; }
    public void setCNmEmpresa(String cNmEmpresa) { this.cNmEmpresa = cNmEmpresa; }

    public String getCSenha() { return cSenha; }
    public void setCSenha(String cSenha) { this.cSenha = cSenha; }

    public String getCCnpj() { return cCnpj; }
    public void setCCnpj(String cCnpj) { this.cCnpj = cCnpj; }

    public char getCAtivo() { return cAtivo; }
    public void setCAtivo(char cAtivo) { this.cAtivo = cAtivo; }

    public String getCEmail() { return cEmail; }
    public void setCEmail(String cEmail) { this.cEmail = cEmail; }


    /**
     * Retorna uma representação textual da entidade.
     *
     * @return string contendo os valores dos atributos da empresa
     */
    @Override
    public String toString() {
        return "CNPJ: " + cCnpj + "\nNome: " + cNmEmpresa +
                "\nSenha: " + cSenha + "\nAtivo: " + cAtivo +
                "\nEmail: " + cEmail;
    }

    /**
     * Retorna o identificador único da entidade.
     * Esse valor é utilizado pela DAO genérica para operações de persistência.
     *
     * @return CNPJ da empresa
     */
    @Override
    public Object getId() { return cCnpj; }
}
