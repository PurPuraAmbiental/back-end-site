package com.purpura.model;

import java.util.Map;

/**
 * Modelo que representa a entidade EnderecoEmpresa.
 * Cada instância corresponde a um registro na tabela "EnderecoEmpresa".
 *
 *
 * Autor: Bruna de Jesus
 */
public class EnderecoEmpresa implements Model {
    /**Adicionando os atributos da tabela como atributos da classe.
     * atributos dados com prefixos da tabela de banco de dado*/

    private int nCdEnderecoEmpresa;
    private String cBairro;
    private String cLogradouro;
    private String cEstado;
    private String cCidade;
    private String cCep;
    private String cComplemento;
    private int iNrEnderecoEmpresa;
    private String cCnpj;

    /**
     * Construtor padrão.
     *
     * @param nCdEnderecoEmpresa código identificador do endereço (chave primária)
     * @param cBairro bairro da empresa
     * @param cLogradouro logradouro da empresa
     * @param cEstado estado da empresa
     * @param cCidade cidade da empresa
     * @param cComplemento complemento do endereço
     * @param cCep CEP do endereço
     * @param iNrEnderecoEmpresa número do endereço
     * @param cCnpj CNPJ da empresa (chave estrangeira)
     */
    public EnderecoEmpresa(int nCdEnderecoEmpresa, String cBairro, String cLogradouro, String cEstado, String cCidade, String cComplemento, String cCep, int iNrEnderecoEmpresa, String cCnpj) {
        this.nCdEnderecoEmpresa = nCdEnderecoEmpresa;
        this.cBairro = cBairro;
        this.cLogradouro = cLogradouro;
        this.cEstado = cEstado;
        this.cCidade = cCidade;
        this.cComplemento = cComplemento;
        this.cCep = cCep;
        this.iNrEnderecoEmpresa = iNrEnderecoEmpresa;
        this.cCnpj = cCnpj;
    }

    /**
     * Construtor alternativo que inicializa o objeto a partir de um mapa de parâmetros,
     * permitindo capturar dados de formulários ou requisições HTTP.
     *
     * @param params mapa contendo os campos do endereço da empresa
     */
    public EnderecoEmpresa(Map<String, String> params) {
        if (params.containsKey("nCdEnderecoEmpresa")) {
            this.nCdEnderecoEmpresa = Integer.parseInt(params.get("nCdEnderecoEmpresa"));
        }
        this.cBairro = params.get("cBairro");
        this.cLogradouro = params.get("cLogradouro");
        this.cEstado = params.get("cEstado");
        this.cCidade = params.get("cCidade");
        this.cComplemento = params.get("cComplemento");
        this.cCep = params.get("cCep");
        this.iNrEnderecoEmpresa = Integer.parseInt(params.get("iNrEnderecoEmpresa"));
        this.cCnpj = params.get("cCnpj");
    }

    // Getters e Setters

    public int getNCdEnderecoEmpresa() { return nCdEnderecoEmpresa; }
    public void setNCdEnderecoEmpresa(int nCdEnderecoEmpresa) { this.nCdEnderecoEmpresa = nCdEnderecoEmpresa; }

    public String getCBairro() { return cBairro; }
    public void setCBairro(String cBairro) { this.cBairro = cBairro; }

    public String getCLogradouro() { return cLogradouro; }
    public void setCLogradouro(String cLogradouro) { this.cLogradouro = cLogradouro; }

    public String getCEstado() { return cEstado; }
    public void setCEstado(String cEstado) { this.cEstado = cEstado; }

    public String getCCidade() { return cCidade; }
    public void setCCidade(String cCidade) { this.cCidade = cCidade; }

    public String getCCep() { return cCep; }
    public void setCCep(String cCep) { this.cCep = cCep; }

    public String getCComplemento() { return cComplemento; }
    public void setCComplemento(String cComplemento) { this.cComplemento = cComplemento; }

    public int getINrEnderecoEmpresa() { return iNrEnderecoEmpresa; }
    public void setINrEnderecoEmpresa(int iNrEnderecoEmpresa) { this.iNrEnderecoEmpresa = iNrEnderecoEmpresa; }

    public String getCCnpj() { return cCnpj; }
    public void setCCnpj(String cCnpj) { this.cCnpj = cCnpj; }

    /**
     * Retorna uma representação textual da entidade.
     *
     * @return string contendo os valores dos atributos do endereço
     */
    @Override
    public String toString() {
        return "\nCódigo: " + nCdEnderecoEmpresa +
                "\nLogradouro: " + cLogradouro +
                "\nNúmero: " + iNrEnderecoEmpresa +
                "\nCidade: " + cCidade +
                "\nBairro: " + cBairro +
                "\nEstado: " + cEstado +
                "\nComplemento: " + cComplemento +
                "\nCEP: " + cCep;
    }

    /**
     * Retorna o identificador único da entidade.
     * Esse valor é utilizado pela DAO genérica para operações de persistência.
     *
     * @return código do endereço da empresa
     */
    @Override
    public Object getId() { return nCdEnderecoEmpresa; }
}
