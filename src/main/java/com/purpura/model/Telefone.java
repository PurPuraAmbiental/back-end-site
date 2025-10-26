package com.purpura.model;

import java.util.Map;

/** Classe modelo para a tabela Telefone */
public class Telefone implements Model {

    // ==== Atributos ====
    // Código identificador do telefone
    private int nCdTelefone;
    // Número do telefone
    private String cNrTelefone;
    // Descrição do telefone
    private String cdescricao;
    // Código identificador da empresa associada
    private String ccnpj;

    // ==== Construtores ====
    // Construtor principal que recebe todos os atributos diretamente
    public Telefone(String ccnpj, int nCdTelefone, String fone, String descricao){
        this.ccnpj = ccnpj;
        this.nCdTelefone = nCdTelefone;
        this.cNrTelefone = fone;
        this.cdescricao = descricao;
    }

    // Construtor que inicializa o objeto a partir de um Map de parâmetros
    // Verifica se cada valor existe e não está vazio antes de atribuir
    public Telefone(Map<String, String> params) {
        String telefone = params.get("cNrTelefone");
        this.cNrTelefone = (telefone != null && !telefone.trim().isEmpty()) ? telefone.trim() : null;

        String descricao = params.get("cDescricao");
        this.cdescricao = (descricao != null && !descricao.trim().isEmpty()) ? descricao.trim() : null;

        String cnpj = params.get("cCnpj");
        this.ccnpj = (cnpj != null && !cnpj.trim().isEmpty()) ? cnpj.trim() : null;
    }

    // ==== Getters ====
    // Métodos para acessar os atributos da classe
    public String getCCnpj() { return ccnpj; }
    public int getNCdTelefone() { return nCdTelefone; }
    public String getCDescricao() { return cdescricao; }
    public String getCNrTelefone() { return cNrTelefone; }

    // ==== Setters ====
    // Métodos para modificar os atributos da classe
    public void setCCnpj(String ccnpj) { this.ccnpj = ccnpj; }
    public void setNCdTelefone(int nCdTelefone) { this.nCdTelefone = nCdTelefone; }
    public void setCDescricao(String descricao) { this.cdescricao = descricao; }
    public void setCNrTelefone(String cNrTelefone) { this.cNrTelefone = cNrTelefone; }

    // ==== Outros métodos ====
    // Retorna o ID único do telefone, usado como identificador
    @Override
    public Object getId() {
        return nCdTelefone;
    }

    // Retorna uma representação legível do objeto, mostrando número e descrição do telefone
    @Override
    public String toString(){
        return "Telefone: " + this.getCNrTelefone() + "\nDescricao: " + this.cdescricao;
    }
}
