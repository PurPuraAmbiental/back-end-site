package com.purpura.model;

import java.time.LocalDate;
import java.util.Map;

public class Transporte implements Model{
    // atributos
    private int nCdTransporte;
    private String cNmTransporte;
    private LocalDate dRetirada;
    private final int nCdPedido;

    // construtor
    public Transporte(int nCdTransporte, String cNmTransporte, LocalDate dRetirada, int nCdPedido) {
        this.nCdTransporte = nCdTransporte;
        this.cNmTransporte = cNmTransporte;
        this.dRetirada = dRetirada;
        this.nCdPedido = nCdPedido;
    }

    public Transporte(Map<String, String> params) {
        if(params.containsKey("nCdTransporte")) {
            this.nCdTransporte = Integer.parseInt(params.get("nCdTransporte"));
        }
        this.cNmTransporte = params.get("cNmTransporte");
        this.dRetirada = LocalDate.parse(params.get("dRetirada"));
        this.nCdPedido = Integer.parseInt(params.get("nCdPedido"));
    }

    // getters
    public int getNCdTransporte(){return nCdTransporte;}
    public String getCNmTransporte(){return cNmTransporte;}
    public LocalDate getDRetirada(){return dRetirada;}
    public int getNCdPedido(){return nCdPedido;}

    // setters
    public void setCNmTransporte(String cNmTransporte) {this.cNmTransporte = cNmTransporte;}
    public void setDRetirada(LocalDate dRetirada) {this.dRetirada = dRetirada;}

    // toString
    @Override
    public String toString(){
        return "Código: " + nCdTransporte + "\nNome: " + cNmTransporte +
                "\nData Retirada: " + dRetirada + "\nPedido: " + nCdPedido;
    }

    // métodos
    @Override
    public Object getId(){return nCdTransporte;}
}