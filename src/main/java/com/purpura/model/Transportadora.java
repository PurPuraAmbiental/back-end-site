package com.purpura.model;

import java.util.Map;

/** Classe Modelo para a tabela Transporte
 * Representa os transportes cadastrados no sistema,
 * vinculados a pedidos e contendo informações sobre retirada.
 * */
public class Transportadora implements Model{
    /**Adicionando os atributos da tabela como atributos da classe
     * atributos dados com prefixos da tabela de banco de dado*/
    private String cCnpj;
    private String cNmTransportadora;
    private String cEmail;
    private String cRegiaoAtendida;

    /**Adicionando um metodo Construtor
     * @param cCnpj - CNPJ da empresa
     * @param cNmTransportadora - nome do transporte
     * @param cEmail - data de retirada do transporte
     * @param cRegiaoAtendida - chave estrangeira da tabela Pedido
     * */
    public Transportadora(String cCnpj, String cNmTransportadora, String cEmail, String cRegiaoAtendida) {
        this.cCnpj = cCnpj;
        this.cNmTransportadora = cNmTransportadora;
        this.cEmail = cEmail;
        this.cRegiaoAtendida = cRegiaoAtendida;
    }

    /**Construtor que inicializa objetos
     *@param params -> um map*/
    public Transportadora(Map<String, String> params) {
        if(params.containsKey("cCnpj")) {
            this.cCnpj = params.get("cCnpj");
        }
        this.cNmTransportadora = params.get("cNmTransportadora");
        this.cRegiaoAtendida = params.get("cRegiaoAtendida");
        this.cEmail = params.get("cEmail");
    }

    /**Metodo getCCnpj
     * @return Primary key */
    public String getCCnpj(){return cCnpj;}

    /**Metodo getCNmTransporte
     * @return nome do transporte*/
    public String getCNmTransporte(){return cNmTransportadora;}

    /**Metodo getCRegiaoAtendimento
     * @return região de atendimento da transportadora*/
    public String getCRegiaoAtendida(){return cRegiaoAtendida;}

    /**Metodo getCEmail
     * @return email de contato da transportadora*/
    public String getCEmail(){return cEmail;}

    /**Metodo setCNmTransporte
     * @param cNmTransportadora*/
    public void setCNmTransporte(String cNmTransportadora) {this.cNmTransportadora = cNmTransportadora;}

    /**Metodo setDRetirada
     * @param cRegiaoAtendida*/
    public void setcRegiaoAtendida(String cRegiaoAtendida) {this.cRegiaoAtendida = cRegiaoAtendida;}

    public void setcEmail(String cEmail) {this.cEmail = cEmail;}

    /**Metodo toString
     *@return informação dos campos da tabela*/
    @Override
    public String toString(){
        return "CNPJ: " + cCnpj + "\nNome: " + cNmTransportadora +
                "\nRegião Atendida: " +cRegiaoAtendida + "\nEmail: " + cEmail;
    }

    /**Metodo para retornar a tabela
     * @return nome da tabela*/
    @Override
    public Object getId(){return cCnpj;}
}
