package com.purpura.model;

import java.text.ParseException;
import java.util.Map;
import java.util.StringTokenizer;

/**Classe modelo para a tabela Telefone
 *@author Bruna Oliveira*/
public class Telefone implements Model{
    /**Adicionando os atributos da tabela como atributos da classe
     * atributos dados com prefixo da tabela de banco de dados*/
    private int nCdTelefone;
    private String cfone;
    private String cdescricao;
    private int nCdEmpresa;

    /**Metodo Construtor
     * @param nCdEmpresa - código identificador da empresa
     * @param nCdTelefone - código identificador do telefone
     * @param fone - número de telefone
     * @param descricao - descrição do telefone*/
    public Telefone(int nCdEmpresa, int nCdTelefone, String fone, String descricao){
        this.nCdEmpresa = nCdEmpresa;
        this.nCdTelefone = nCdTelefone;
        this.cfone = fone;
        this.cdescricao = descricao;
    }

    /**Construtor que inicializa objetos com parâmetros de um map
     * @param params -> um map de parâmetros para inicialização*/
    public Telefone(Map<String, String> params) throws ParseException {
        if(params.containsKey("nCdTelefone")) {
            this.cfone = params.get("nCdTelefone");
        }
        this.cfone = params.get("fone");
        this.cdescricao = params.get("descricao");
        if(params.containsKey("nCdEmpresa")) {
            this.nCdEmpresa = Integer.parseInt(params.get("nCdEmpresa"));
        }
    }

    /**Metodo getnCdEmpresa
     * @return código da empresa associada ao telefone*/
    public int getnCdEmpresa() {
        return nCdEmpresa;
    }

    /**Metodo getnCdTelefone
     * @return código do telefone*/
    public int getnCdTelefone() {
        return nCdTelefone;
    }

    /**Metodo getCDescricao
     * @return descrição do telefone */
    public String getCDescricao() {
        return cdescricao;
    }

    /**Metodo getCFone
     * @return número de telefone*/
    public String getCFone() {
        return cfone;
    }

    /**Metodo setFone
     * @param fone - número do telefone*/
    public void setFone(String fone) {
        this.cfone = fone;
    }

    /**Metodo setDescricao
     * @param descricao - descrição do telefone*/
    public void setDescricao(String descricao) {
        this.cdescricao = descricao;
    }

    /**Metodo toString
     * @return informações sobre o telefone e sua descrição*/
    public String toString(){
        return "Telefone: "+this.getnCdTelefone()+"\nDescricao: "+this.cdescricao;
    }

    /**Metodo getID
     * @return código único do telefone*/
    @Override
    public Object getId() {
        return nCdTelefone;
    }
}
