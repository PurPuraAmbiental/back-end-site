package com.purpura.model;

import java.text.ParseException;
import java.util.Map;

/**Classe modelo para a tabela Telefone
 *@author Bruna Oliveira*/
public class Telefone implements Model{
    /**Adicionando os atributos da tabela como atributos da classe
     * atributos dados com prefixo da tabela de banco de dados*/
    private int nCdTelefone;
    private String cNrTelefone;
    private String cdescricao;
    private String ccnpj;

    /**Metodo Construtor
     * @param ccnpj - código identificador da empresa
     * @param nCdTelefone - código identificador do telefone
     * @param fone - número de telefone
     * @param descricao - descrição do telefone*/
    public Telefone(String ccnpj, int nCdTelefone, String fone, String descricao){
        this.ccnpj = ccnpj;
        this.nCdTelefone = nCdTelefone;
        this.cNrTelefone = fone;
        this.cdescricao = descricao;
    }

    /**Construtor que inicializa objetos com parâmetros de um map
     * @param params -> um map de parâmetros para inicialização*/
    public Telefone(Map<String, String> params) {
        String telefone = params.get("cNrTelefone");
        if (telefone != null && !telefone.trim().isEmpty()) {
            this.cNrTelefone = telefone.trim();
        } else {
            this.cNrTelefone = null;
        }
        String descricao = params.get("cDescricao");
        if (descricao != null && !descricao.trim().isEmpty()) {
            this.cdescricao = descricao.trim();
        } else {
            this.cdescricao = null;
        }
        String cnpj = params.get("cCnpj");
        if (cnpj != null && !cnpj.trim().isEmpty()) {
            this.ccnpj = cnpj.trim();
        } else {
            this.ccnpj = null;
        }
    }


    /**Metodo getNCdEmpresa
     * @return código da empresa associada ao telefone*/
    public String getCCnpj() {
        return ccnpj;
    }

    /**Metodo getNCdTelefone
     * @return código do telefone*/
    public int getNCdTelefone() {
        return nCdTelefone;
    }

    /**Metodo getCDescricao
     * @return descrição do telefone */
    public String getCDescricao() {
        return cdescricao;
    }

    /**Metodo getCNrTelefone
     * @return número de telefone*/
    public String getCNrTelefone() {
        return cNrTelefone;
    }

    /**Metodo setFone
     * @param fone - número do telefone*/
    public void setcNrTelefone(String fone) {
        this.cNrTelefone = fone;
    }

    /**Metodo setDescricao
     * @param descricao - descrição do telefone*/
    public void setCDescricao(String descricao) {
        this.cdescricao = descricao;
    }

    public void setNCdTelefone(int nCdTelefone) {
        this.nCdTelefone = nCdTelefone;
    }

    public void setCNrTelefone(String cNrTelefone) {
        this.cNrTelefone = cNrTelefone;
    }




    /**Metodo toString
     * @return informações sobre o telefone e sua descrição*/
    public String toString(){
        return "Telefone: "+this.getNCdTelefone()+"\nDescricao: "+this.cdescricao;
    }

    /**Metodo getID
     * @return código único do telefone*/
    @Override
    public Object getId() {
        return nCdTelefone;
    }
}
