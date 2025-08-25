package com.purpura.models;

import java.time.LocalDate;

public class Transporte {
    // atributos
    private final int nCdTransporte;
    private String cNmTransporte;
    private LocalDate dRetirada;

    // construtor
    public Transporte(int nCdTransporte, String cNmTransporte, LocalDate dRetirada) {
        this.nCdTransporte = nCdTransporte;
        this.cNmTransporte = cNmTransporte;
        this.dRetirada = dRetirada;
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
}
