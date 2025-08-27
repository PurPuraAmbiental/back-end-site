package com.purpura.models;

import java.time.LocalDate;

public class Pedido {
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

    public double getnValorTotal() {
        return nValorTotal;
    }

    public int getnCdPedido() {
        return nCdPedido;
    }

    public LocalDate getdPedido() {
        return dPedido;
    }

    public String getcFrequencia() {
        return cFrequencia;
    }

    public LocalDate getdAgendamentoColeta() {
        return dAgendamentoColeta;
    }

    public String getcOservacoees() {
        return cOservacoes;
    }

    public String getcStatus() {
        return cStatus;
    }

    public void setcFrequencia(String cFrequencia) {
        this.cFrequencia = cFrequencia;
    }

    public void setcOservacoes(String cOservacoes) {
        this.cOservacoes = cOservacoes;
    }

    public void setdAgendamentoColeta(LocalDate dAgendamentoColeta) {
        this.dAgendamentoColeta = dAgendamentoColeta;
    }

    public void setcStatus(String cStatus) {
        this.cStatus = cStatus;
    }

    public void setdPedido(LocalDate dPedido) {
        this.dPedido = dPedido;
    }

    public void setnValorTotal(double nValorTotal) {
        this.nValorTotal = nValorTotal;
    }

    @Override
    public String toString(){
        return "Código: " + nCdPedido + "\nValor Total: " + nValorTotal +
                "\nStatus: " + cStatus + "\nData Pedido: " + dPedido +
                "\nFrequência" + cFrequencia + "\nData Agendamento: " + dAgendamentoColeta +
                "\nObservações: " + cOservacoes;
    }
}