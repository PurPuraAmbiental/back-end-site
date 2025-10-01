package com.purpura.model;

import java.util.Map;

/**Classe Modelo para a tabela ItemPedido
 * @author Kevin de Oliveira*/
public class ItemPedido implements Model{
    /**Adicionando os atributos da tabela como atributos da classe
     * atributos dados com prefixos da tabela de banco de dados*/
    private int nCdItemPedido;
    private double nPrecoUnitario;
    private double nVolume;
    private final int nCdResiduo;
    private final int nCdPedido;

    /**Adicionando um método Construtor
     * @param nCdItemPedido - primary key da tabela ItemPedido
     * @param nPrecoUnitario - preço unitário do item
     * @param nVolume - volume do item
     * @param nCdResiduo - foreign key com a tabela Resíduo
     * @param nCdPedido - foreign key com a tabela Pedido*/
    public ItemPedido(int nCdItemPedido, double nPrecoUnitario, double nVolume,
                      int nCdResiduo, int nCdPedido) {
        this.nCdItemPedido = nCdItemPedido;
        this.nPrecoUnitario = nPrecoUnitario;
        this.nVolume = nVolume;
        this.nCdResiduo = nCdResiduo;
        this.nCdPedido = nCdPedido;
    }

    /**Adicionando um método construtor que inicializa objetos
     * @param params -> um map com os parâmetros do item do pedido*/
    public ItemPedido(Map<String, String> params) {
        if(params.containsKey("nCdItemPedido")) {
            this.nCdItemPedido = Integer.parseInt(params.get("nCdItemPedido"));
        }
        this.nPrecoUnitario = Double.parseDouble(params.get("nPrecoUnitario"));
        this.nVolume = Double.parseDouble(params.get("nVolume"));
        this.nCdResiduo = Integer.parseInt(params.get("nCdResiduo"));
        this.nCdPedido = Integer.parseInt(params.get("nCdPedido"));
    }

    /**Método getNCdItemPedido
     * @return primary key de ItemPedido*/
    public int getNCdItemPedido() {return nCdItemPedido;}
    /**Método getNPrecoUnitario
     * @return preço unitário do item*/
    public double getNPrecoUnitario() {return nPrecoUnitario;}
    /**Método getNVolume
     * @return volume do item*/
    public double getNVolume() {return nVolume;}
    /**Método getNCdResiduo
     * @return código do resíduo vinculado*/
    public int getNCdResiduo() {return nCdResiduo;}
    /**Método getNCdPedido
     * @return código do pedido vinculado*/
    public int getNCdPedido() {return nCdPedido;}

    /**Método setNPrecoUnitario
     * @param nPrecoUnitario -> altera o preço unitário do item*/
    public void setNPrecoUnitario(double nPrecoUnitario){this.nPrecoUnitario = nPrecoUnitario;}
    /**Método setNVolume
     * @param nVolume -> altera o volume do item*/
    public void setNVolume(double nVolume){this.nVolume = nVolume;}

    /**Método toString
     * @return informações sobre os campos da tabela ItemPedido*/
    @Override
    public String toString(){
        return "Código: " + nCdItemPedido + "\nPreço Unitário: " + nPrecoUnitario +
                "\nVolume: " + nVolume + "\nResíduo: " + nCdResiduo +
                "\nPedido: " + nCdPedido;
    }

    /**Método getId
     * @return retorna o código do item do pedido*/
    @Override
    public Object getId(){return nCdItemPedido;}
}
