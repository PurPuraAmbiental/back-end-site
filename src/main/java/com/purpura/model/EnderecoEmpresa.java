package com.purpura.model;

import java.util.Map;
/**Classe Modelo para a tabela EnderecoEmpresa
 * @author Bruna Oliveira*/
public  class EnderecoEmpresa implements Model{
    /**Adicionando os atributos da tabela como atributos da classe
     * atributos dados com prefixos da tabela de banco de dados*/
    private int nCdEnderecoEmpresa;
    private String cBairro;
    private String cLogradouro;
    private String cEstado;
    private String cCidade;
    private String cCep;
    private String cComplemento;
    private int iNrEnderecoEmpresa;
    private String cCnpj;

    /**Adicionando um metodo Construtor
     * @param cCnpj - foring key com a tabela Empresa
     * @param cBairro - bairro da empresa
     * @param cCep - cep da empresa
     * @param cCidade - cidade da empresa
     * @param cComplemento - complemento da empresa
     * @param cEstado - estado da empresa
     * @param cLogradouro - logradouro da empresa
     * @param iNrEnderecoEmpresa
     * @param nCdEnderecoEmpresa - primary key da tabela EnderecoEmpresa*/
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

    /**Adicionando um metodo construtor que inicializa objetos
     * @param params -> um map*/
    public EnderecoEmpresa(Map<String, String> params){
        if(params.containsKey("nCdEnderecoEmpresa")){
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

    /**Metodo getCBairro
     * @return nome do bairro*/
    public String getCBairro() {return cBairro;}
    /**Metodo getNCdEnderecoEmpresa
     * @return primary Key de enderecoEmpresa*/
    public int getNCdEnderecoEmpresa() {return nCdEnderecoEmpresa;}
    /**Metodo getCCep
     * @return cep da empresa*/
    public String getCCep() {return cCep;}
    /**Metodo getCCidade
     * @return nome da cidade cadastrada*/
    public String getCCidade() {return cCidade;}
    /**Metodo getCEstado
     * @return nome do estado cadastrado*/
    public String getCEstado() {return cEstado;}
    /**Metodo getCComplemento
     * @return complemento cadastrado*/
    public String getCComplemento() {return cComplemento;}
    /**Metodo getCLogradouro
     * @return logradouro cadastrado */
    public String getCLogradouro() {return cLogradouro;}
    /**Metodo getCCnpj
     * @return cnpj cadastrado*/
    public String getCCnpj() {return cCnpj;}
    /**Metodo getINrEnderecoEmpresa
     * @return */
    public int getINrEnderecoEmpresa() {return iNrEnderecoEmpresa;}

    /**Metodo setCBairro
     * @param bairro -> altera o bairro da empresa*/
    public void setCBairro(String bairro) {this.cBairro = bairro;}
    /**Metodo setCCep
     * @param cCep  -> altera o cpf da empresa*/
    public void setCCep(String cCep) {
        this.cCep = cCep;
    }
    /**Metodo setCCidade
     * @param cCidade -> aletra a cidade cadastrata*/
    public void setCCidade(String cCidade) {
        this.cCidade = cCidade;
    }
    /**Metodo serCComplemento
     * @param cComplemento -> altera o complemento*/
    public void setCComplemento(String cComplemento) {
        this.cComplemento = cComplemento;
    }
    /**Metodo setCEstado
     * @param cEstado -> altera o estado cadastrado*/
    public void setCEstado(String cEstado) {
        this.cEstado = cEstado;
    }
    /**Metodo setClogradouro
     * @param clogradouro */
    public void setCLogradouro(String clogradouro) {
        this.cLogradouro = clogradouro;
    }

    public void setNCdEnderecoEmpresa(int nCdEnderecoEmpresa) {
        this.nCdEnderecoEmpresa = nCdEnderecoEmpresa;
    }

    public void setINrEnderecoEmpresa(int iNrEnderecoEmpresa) {
        this.iNrEnderecoEmpresa = iNrEnderecoEmpresa;
    }

    public void setCCnpj(String cCnpj) {
        this.cCnpj = cCnpj;
    }



    /**Metodo toString
     * @return informações sobre os campos da tabela Endereco Empresa*/
    @Override
    public String toString(){
        return "\nCódigo: "+nCdEnderecoEmpresa+
                "\nLogradouro: "+cLogradouro+
                "\nNúmero: "+iNrEnderecoEmpresa+
                "\nCidade: "+cCidade+
                "\nBairro: "+cBairro+
                "\nEstado: "+ cEstado+
                "\nComplemento: " + cComplemento+
                "\nCEP: "+ cCep;
    }

    /**Metodo getID
     * @return retorna o codigo da tabela*/
    @Override
    public Object getId(){return nCdEnderecoEmpresa;}
}