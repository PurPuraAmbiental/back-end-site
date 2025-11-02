package com.purpura.model;

import java.util.Map;

/**
 * Modelo que representa a entidade Telefone.
 * Cada instância corresponde a um registro na tabela "Telefone".
 * Contém informações do número, descrição e empresa associada.
 *
 * Autor: Bruna de Jesus
 */
public class Telefone implements Model {

    // Atributos que representam as colunas da tabela "Telefone"
    private int nCdTelefone;
    private String cNrTelefone;
    private String cdescricao;
    private String ccnpj;

    /**
     * Construtor principal.
     *
     * @param ccnpj CNPJ da empresa associada
     * @param nCdTelefone código identificador do telefone
     * @param fone número do telefone
     * @param descricao descrição do telefone
     */
    public Telefone(String ccnpj, int nCdTelefone, String fone, String descricao) {
        this.ccnpj = ccnpj;
        this.nCdTelefone = nCdTelefone;
        this.cNrTelefone = fone;
        this.cdescricao = descricao;
    }

    /**
     * Construtor que inicializa o objeto a partir de um mapa de parâmetros,
     * permitindo capturar dados de formulários ou requisições HTTP.
     *
     * @param params mapa contendo os campos do telefone
     */
    public Telefone(Map<String, String> params) {
        String telefone = params.get("cNrTelefone");
        this.cNrTelefone = (telefone != null && !telefone.trim().isEmpty()) ? telefone.trim() : null;

        String descricao = params.get("cDescricao");
        this.cdescricao = (descricao != null && !descricao.trim().isEmpty()) ? descricao.trim() : null;

        String cnpj = params.get("cCnpj");
        this.ccnpj = (cnpj != null && !cnpj.trim().isEmpty()) ? cnpj.trim() : null;
    }

    // Getters e Setters
    public int getNCdTelefone() { return nCdTelefone; }
    public void setNCdTelefone(int nCdTelefone) { this.nCdTelefone = nCdTelefone; }

    public String getCNrTelefone() { return cNrTelefone; }
    public void setCNrTelefone(String cNrTelefone) { this.cNrTelefone = cNrTelefone; }

    public String getCDescricao() { return cdescricao; }
    public void setCDescricao(String cdescricao) { this.cdescricao = cdescricao; }

    public String getCCnpj() { return ccnpj; }
    public void setCCnpj(String ccnpj) { this.ccnpj = ccnpj; }

    /**
     * Retorna o identificador único da entidade.
     *
     * @return código do telefone
     */
    @Override
    public Object getId() {
        return nCdTelefone;
    }

    /**
     * Retorna uma representação textual da entidade.
     *
     * @return número e descrição do telefone
     */
    @Override
    public String toString() {
        return "Telefone: " + this.getCNrTelefone() + "\nDescrição: " + this.cdescricao;
    }
}
