package com.purpura.model;

import java.util.Map;

/** Classe Modelo para a tabela Residuo
 * Representa os resíduos cadastrados no sistema
 * contendo informações de identificação, categoria e valores padrão.
 * */
public class Residuo implements Model{
    /**Adicionando os atributos da tabela como atributos da classe
     * atributos dados com prefixos da tabela de banco de dado*/
    private int nCdResiduo;
    private String cNmResiduo;
    private String cTipoUnidade;
    private double nPrecoPadrao;
    private double nVolumePadrao;
    private String cCategoria;
    private String cDescricao;
    private String cCnpj;

    /**Adicionando um metodo Construtor
     * @param nCdResiduo - atributo identificador dessa instancia de Resíduo
     * @param cNmResiduo - nome do resíduo
     * @param cTipoUnidade - unidade de medida do resíduo
     * @param nPrecoPadrao - preço padrão associado ao resíduo
     * @param nVolumePadrao - volume padrão associado ao resíduo
     * @param cCategoria - categoria do resíduo
     * @param cDescricao - descrição detalhada do resíduo
     * @param cCnpj - chave estrangeira da tabela Empresa
     * */
    public Residuo(int nCdResiduo, String cNmResiduo, String cTipoUnidade,
                   double nPrecoPadrao, double nVolumePadrao, String cCategoria,
                   String cDescricao, String cCnpj){
        this.nCdResiduo = nCdResiduo;
        this.cNmResiduo = cNmResiduo;
        this.cTipoUnidade = cTipoUnidade;
        this.nPrecoPadrao = nPrecoPadrao;
        this.nVolumePadrao = nVolumePadrao;
        this.cCategoria = cCategoria;
        this.cDescricao = cDescricao;
        this.cCnpj = cCnpj;
    }

    /**Construtor que inicializa objetos
     *@param params -> um map*/
    public Residuo(Map<String, String> params) {
        if(params.containsKey("nCdResiduo")) {
            this.nCdResiduo = Integer.parseInt(params.get("nCdResiduo"));
        }
        this.cNmResiduo = params.get("cNmResiduo");
        this.cTipoUnidade = params.get("cTipoUnidade");
        this.nPrecoPadrao = Double.parseDouble(params.get("nPrecoPadrao"));
        this.nVolumePadrao = Double.parseDouble(params.get("nVolumePadrao"));
        this.cCategoria = params.get("cCategoria");
        this.cDescricao = params.get("cDescricao");
        this.cCnpj = params.get("cCnpj");
    }

    /**Metodo getNCdResiduo
     * @return Primary key */
    public int getNCdResiduo() {return nCdResiduo;}

    /**Metodo getCNmResiduo
     * @return nome do resíduo*/
    public String getCNmResiduo() {return cNmResiduo;}

    /**Metodo getCTipoUnidade
     * @return tipo de unidade do resíduo*/
    public String getCTipoUnidade() {return cTipoUnidade;}

    /**Metodo getNPrecoPadrao
     * @return preço padrão do resíduo*/
    public double getNPrecoPadrao() {return nPrecoPadrao;}

    /**Metodo getNVolumePadrao
     * @return volume padrão do resíduo*/
    public double getNVolumePadrao() {return nVolumePadrao;}

    /**Metodo getCCategoria
     * @return categoria do resíduo*/
    public String getCCategoria() {return cCategoria;}

    /**Metodo getCDescricao
     * @return descrição do resíduo*/
    public String getCDescricao() {return cDescricao;}

    /**Metodo getCCnpj
     * @return CNPJ da empresa vinculada*/
    public String getCCnpj() {return cCnpj;}

    /**Metodo setCNmResiduo
     * @param cNmResiduo*/
    public void setCNmResiduo(String cNmResiduo) {this.cNmResiduo = cNmResiduo;}

    /**Metodo setCTipoUnidade
     * @param cTipoUnidade*/
    public void setCTipoUnidade(String cTipoUnidade) {this.cTipoUnidade = cTipoUnidade;}

    /**Metodo setNPrecoPadrao
     * @param nPrecoPadrao*/
    public void setNPrecoPadrao(double nPrecoPadrao) {this.nPrecoPadrao = nPrecoPadrao;}

    /**Metodo setNVolumePadrao
     * @param nVolumePadrao*/
    public void setNVolumePadrao(double nVolumePadrao) {this.nVolumePadrao = nVolumePadrao;}

    /**Metodo setCCategoria
     * @param cCategoria*/
    public void setCCategoria(String cCategoria) {this.cCategoria = cCategoria;}

    /**Metodo setCDescricao
     * @param cDescricao*/
    public void setCDescricao(String cDescricao) {this.cDescricao = cDescricao;}

    /**Metodo setCCnpj
     * @param cCnpj*/
    public void setCCnpj(String cCnpj) {this.cCnpj = cCnpj;}

    /**Metodo toString
     *@return informação dos campos da tabela*/
    @Override
    public String toString(){
        return "Código: " + nCdResiduo + "\nNome: " + cNmResiduo +
                "\nTipo Unidade: " + cTipoUnidade + "\nPreço Padrão: " + nPrecoPadrao +
                "\nVolume Padrão: " + nVolumePadrao + "\nCategoria: " + cCategoria +
                "\nDescrição: " + cDescricao + "\nCNPJ: " + cCnpj;
    }

    /**Metodo para retornar a tabela
     * @return nome da tabela*/
    @Override
    public Object getId(){return nCdResiduo;}
}
