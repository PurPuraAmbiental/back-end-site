package com.purpura.models;

import java.time.LocalDate;

public class Pedido implements Model{
    private double nValorTotal;
    private String cStatus;
    private LocalDate dPedido;
    private String cFrequencia;
    private LocalDate dAgendamentoColeta;
    private String cOservacoes;
    private final int nCdPedido;

    public Pedido(double nValorTotal, String cStatus, LocalDate dPedido, String cFrequencia,  LocalDate dAgendamentoColeta, String cOservacoes, int nCdPedido){
        this.nValorTotal = nValorTotal;
        this.cStatus = cStatus;
        this.cFrequencia = cFrequencia;
        this.cOservacoes = cOservacoes;
        this.dAgendamentoColeta = dAgendamentoColeta;
        this.nCdPedido = nCdPedido;
        this.dPedido = dPedido;
    }
//MUDEI O NOME DO GET
    public double getNValorTotal() {
        return nValorTotal;
    }

    public int getNCdPedido() {
        return nCdPedido;
    }

    public LocalDate getDPedido() {
        return dPedido;
    }

    public String getCFrequencia() {
        return cFrequencia;
    }

    public LocalDate getDAgendamentoColeta() {
        return dAgendamentoColeta;
    }

    public String getCObservacoes() {
        return cOservacoes;
    }

    public String getCStatus() {
        return cStatus;
    }

    public void setCFrequencia(String cFrequencia) {
        this.cFrequencia = cFrequencia;
    }

    public void setCObservacoes(String cOservacoes) {
        this.cOservacoes = cOservacoes;
    }

    public void setDAgendamentoColeta(LocalDate dAgendamentoColeta) {
        this.dAgendamentoColeta = dAgendamentoColeta;
    }

    public void setCStatus(String cStatus) {
        this.cStatus = cStatus;
    }

    public void setDPedido(LocalDate dPedido) {
        this.dPedido = dPedido;
    }

    public void setNValorTotal(double nValorTotal) {
        this.nValorTotal = nValorTotal;
    }

    @Override
    public String toString(){
        return "Código: " + nCdPedido + "\nValor Total: " + nValorTotal +
                "\nStatus: " + cStatus + "\nData Pedido: " + dPedido +
                "\nFrequência" + cFrequencia + "\nData Agendamento: " + dAgendamentoColeta +
                "\nObservações: " + cOservacoes;
    }

    // métodos
    @Override
    public Object getId(){return nCdPedido;}
}