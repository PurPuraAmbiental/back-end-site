package com.purpura.model;

import java.util.Map;

/**
 * Modelo que representa a entidade Residuo.
 * Cada instância corresponde a um registro na tabela "Residuo".
 * Contém informações de identificação, categoria, valores e empresa vinculada.
 *
 * Autor: Bruna de Jesus
 */
public class Residuo implements Model {

    /** Atributos que representam as colunas da tabela "Residuo". */
    private int nCdResiduo;
    private String cNmResiduo;
    private String cTipoUnidade;
    private double nPrecoPadrao;
    private double nVolumePadrao;
    private String cCategoria;
    private String cDescricao;
    private String cCnpj;

    /**
     * Construtor padrão.
     *
     * @param nCdResiduo código identificador do resíduo (chave primária)
     * @param cNmResiduo nome do resíduo
     * @param cTipoUnidade tipo de unidade de medida do resíduo
     * @param nPrecoPadrao preço padrão associado ao resíduo
     * @param nVolumePadrao volume padrão associado ao resíduo
     * @param cCategoria categoria do resíduo
     * @param cDescricao descrição detalhada do resíduo
     * @param cCnpj CNPJ da empresa vinculada (chave estrangeira)
     */
    public Residuo(int nCdResiduo, String cNmResiduo, String cTipoUnidade,
                   double nPrecoPadrao, double nVolumePadrao, String cCategoria,
                   String cDescricao, String cCnpj) {
        this.nCdResiduo = nCdResiduo;
        this.cNmResiduo = cNmResiduo;
        this.cTipoUnidade = cTipoUnidade;
        this.nPrecoPadrao = nPrecoPadrao;
        this.nVolumePadrao = nVolumePadrao;
        this.cCategoria = cCategoria;
        this.cDescricao = cDescricao;
        this.cCnpj = cCnpj;
    }

    /**
     * Construtor alternativo que inicializa o objeto a partir de um mapa de parâmetros,
     * permitindo capturar dados de formulários ou requisições HTTP.
     *
     * @param params mapa contendo os campos do resíduo
     */
    public Residuo(Map<String, String> params) {
        if (params.containsKey("nCdResiduo")) {
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

    // Getters e Setters

    public int getNCdResiduo() { return nCdResiduo; }
    public void setNCdResiduo(int nCdResiduo) { this.nCdResiduo = nCdResiduo; }

    public String getCNmResiduo() { return cNmResiduo; }
    public void setCNmResiduo(String cNmResiduo) { this.cNmResiduo = cNmResiduo; }

    public String getCTipoUnidade() { return cTipoUnidade; }
    public void setCTipoUnidade(String cTipoUnidade) { this.cTipoUnidade = cTipoUnidade; }

    public double getNPrecoPadrao() { return nPrecoPadrao; }
    public void setNPrecoPadrao(double nPrecoPadrao) { this.nPrecoPadrao = nPrecoPadrao; }

    public double getNVolumePadrao() { return nVolumePadrao; }
    public void setNVolumePadrao(double nVolumePadrao) { this.nVolumePadrao = nVolumePadrao; }

    public String getCCategoria() { return cCategoria; }
    public void setCCategoria(String cCategoria) { this.cCategoria = cCategoria; }

    public String getCDescricao() { return cDescricao; }
    public void setCDescricao(String cDescricao) { this.cDescricao = cDescricao; }

    public String getCCnpj() { return cCnpj; }
    public void setCCnpj(String cCnpj) { this.cCnpj = cCnpj; }

    /**
     * Retorna uma representação textual da entidade.
     *
     * @return string contendo os valores dos atributos do resíduo
     */
    @Override
    public String toString() {
        return "\nCódigo: " + nCdResiduo +
                "\nNome: " + cNmResiduo +
                "\nTipo de Unidade: " + cTipoUnidade +
                "\nPreço Padrão: " + nPrecoPadrao +
                "\nVolume Padrão: " + nVolumePadrao +
                "\nCategoria: " + cCategoria +
                "\nDescrição: " + cDescricao +
                "\nCNPJ: " + cCnpj;
    }

    /**
     * Retorna o identificador único da entidade.
     * Esse valor é utilizado pela DAO genérica para operações de persistência.
     *
     * @return código do resíduo
     */
    @Override
    public Object getId() { return nCdResiduo; }
}
