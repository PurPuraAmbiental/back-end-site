package com.purpura.models;

import java.time.LocalDate;
import java.util.Map;

public class Transporte implements Model{
    // atributos
    private int nCdTransporte;
    private String cNmTransporte;
    private LocalDate dRetirada;

    // construtor
    public Transporte(int nCdTransporte, String cNmTransporte, LocalDate dRetirada) {
        this.nCdTransporte = nCdTransporte;
        this.cNmTransporte = cNmTransporte;
        this.dRetirada = dRetirada;
    }

    public Transporte(Map<String, String> params) {
        if(params.containsKey("nCdTransporte")) {
            this.nCdTransporte = Integer.parseInt(params.get("nCdTransporte"));
        }
        this.cNmTransporte = params.get("cNmTransporte");
        this.dRetirada = LocalDate.parse(params.get("dRetirada"));
    }

    // getters
    public int getNCdTransporte(){return nCdTransporte;}
    public String getCNmTransporte(){return cNmTransporte;}
    public LocalDate getDRetirada(){return dRetirada;}

    // setters
    public void setCNmTransporte(String cNmTransporte) {this.cNmTransporte = cNmTransporte;}
    public void setDRetirada(LocalDate dRetirada) {this.dRetirada = dRetirada;}

    // toString
    @Override
    public String toString(){
        return "Código: " + nCdTransporte + "\nNome: " + cNmTransporte +
                "\nData Retirada: " + dRetirada;
    }

    // métodos
    @Override
    public Object getId(){return nCdTransporte;}
}