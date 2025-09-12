package com.purpura.models;

import java.util.Map;

public class ItemPedido implements Model{
    // atributos
    private int nCdItemPedido;
    private double nPrecoUnitario;
    private double nVolume;
    private final int nCdResiduo;
    private final int nCdPedido;

    // construtor
    public ItemPedido(int nCdItemPedido, double nPrecoUnitario, double nVolume,
                      int nCdResiduo, int nCdPedido) {
        this.nCdItemPedido = nCdItemPedido;
        this.nPrecoUnitario = nPrecoUnitario;
        this.nVolume = nVolume;
        this.nCdResiduo = nCdResiduo;
        this.nCdPedido = nCdPedido;
    }

    public ItemPedido(Map<String, String> params) {
        if(params.containsKey("nCdItemPedido")) {
            this.nCdItemPedido = Integer.parseInt(params.get("nCdItemPedido"));
        }
        this.nPrecoUnitario = Double.parseDouble(params.get("nPrecoUnitario"));
        this.nVolume = Double.parseDouble(params.get("nVolume"));
        this.nCdResiduo = Integer.parseInt(params.get("nCdResiduo"));
        this.nCdPedido = Integer.parseInt(params.get("nCdPedido"));
    }

    // getters
    public int getNCdItemPedido() {return nCdItemPedido;}
    public double getNPrecoUnitario() {return nPrecoUnitario;}
    public double getNVolume() {return nVolume;}
    public int getNCdResiduo() {return nCdResiduo;}
    public int getNCdPedido() {return nCdPedido;}

    // setters
    public void setNPrecoUnitario(double nPrecoUnitario){this.nPrecoUnitario = nPrecoUnitario;}
    public void setNVolume(double nVolume){this.nVolume = nVolume;}

    // toString
    @Override
    public String toString(){
        return "Código: " + nCdItemPedido + "\nPreço Unitário: " + nPrecoUnitario +
                "\nVolume: " + nVolume + "\nResíduo: " + nCdResiduo +
                "\nPedido: " + nCdPedido;
    }

    // métodos
    @Override
    public Object getId(){return nCdItemPedido;}
}
