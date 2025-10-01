package com.purpura.model;

import java.time.LocalDate;
import java.util.Map;

/** Classe Modelo para a tabela Transporte
 * Representa os transportes cadastrados no sistema,
 * vinculados a pedidos e contendo informações sobre retirada.
 * */
public class Transporte implements Model{
    /**Adicionando os atributos da tabela como atributos da classe
     * atributos dados com prefixos da tabela de banco de dado*/
    private int nCdTransporte;
    private String cNmTransporte;
    private LocalDate dRetirada;
    private final int nCdPedido;

    /**Adicionando um metodo Construtor
     * @param nCdTransporte - atributo identificador dessa instancia de Transporte
     * @param cNmTransporte - nome do transporte
     * @param dRetirada - data de retirada do transporte
     * @param nCdPedido - chave estrangeira da tabela Pedido
     * */
    public Transporte(int nCdTransporte, String cNmTransporte, LocalDate dRetirada, int nCdPedido) {
        this.nCdTransporte = nCdTransporte;
        this.cNmTransporte = cNmTransporte;
        this.dRetirada = dRetirada;
        this.nCdPedido = nCdPedido;
    }

    /**Construtor que inicializa objetos
     *@param params -> um map*/
    public Transporte(Map<String, String> params) {
        if(params.containsKey("nCdTransporte")) {
            this.nCdTransporte = Integer.parseInt(params.get("nCdTransporte"));
        }
        this.cNmTransporte = params.get("cNmTransporte");
        this.dRetirada = LocalDate.parse(params.get("dRetirada"));
        this.nCdPedido = Integer.parseInt(params.get("nCdPedido"));
    }

    /**Metodo getNCdTransporte
     * @return Primary key */
    public int getNCdTransporte(){return nCdTransporte;}

    /**Metodo getCNmTransporte
     * @return nome do transporte*/
    public String getCNmTransporte(){return cNmTransporte;}

    /**Metodo getDRetirada
     * @return data da retirada*/
    public LocalDate getDRetirada(){return dRetirada;}

    /**Metodo getNCdPedido
     * @return código do pedido vinculado*/
    public int getNCdPedido(){return nCdPedido;}

    /**Metodo setCNmTransporte
     * @param cNmTransporte*/
    public void setCNmTransporte(String cNmTransporte) {this.cNmTransporte = cNmTransporte;}

    /**Metodo setDRetirada
     * @param dRetirada*/
    public void setDRetirada(LocalDate dRetirada) {this.dRetirada = dRetirada;}

    /**Metodo toString
     *@return informação dos campos da tabela*/
    @Override
    public String toString(){
        return "Código: " + nCdTransporte + "\nNome: " + cNmTransporte +
                "\nData Retirada: " + dRetirada + "\nPedido: " + nCdPedido;
    }

    /**Metodo para retornar a tabela
     * @return nome da tabela*/
    @Override
    public Object getId(){return nCdTransporte;}
}
