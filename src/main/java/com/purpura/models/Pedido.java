package com.purpura.models;

import java.time.LocalDate;
import java.util.Map;

public class Pedido implements Model{
    private double nValorTotal;
    private String cStatus;
    private LocalDate dPedido;
    private String cFrequencia;
    private LocalDate dAgendamentoColeta;
    private String cOservacoes;
    private int nCdPedido;
    private final String cCnpjRemetente;
    private final String cCnpjDestinatario;
    private final int nCdEnderecoEmpresaRemetente;
    private final int nCdEnderecoEmpresaDestinatario;

    public Pedido(double nValorTotal, String cStatus, LocalDate dPedido, String cFrequencia,  LocalDate dAgendamentoColeta, String cOservacoes, int nCdPedido,
                  String cCnpjRemetente, String cCnpjDestinatario, int nCdEnderecoEmpresaRemetente, int nCdEnderecoEmpresaDestinatario){
        this.nValorTotal = nValorTotal;
        this.cStatus = cStatus;
        this.cFrequencia = cFrequencia;
        this.cOservacoes = cOservacoes;
        this.dAgendamentoColeta = dAgendamentoColeta;
        this.nCdPedido = nCdPedido;
        this.dPedido = dPedido;
        this.cCnpjRemetente = cCnpjRemetente;
        this.cCnpjDestinatario = cCnpjDestinatario;
        this.nCdEnderecoEmpresaRemetente = nCdEnderecoEmpresaRemetente;
        this.nCdEnderecoEmpresaDestinatario = nCdEnderecoEmpresaDestinatario;
    }

    public Pedido(Map<String, String> params){
        if(params.containsKey("nCdPedido")){
            this.nCdPedido = Integer.parseInt(params.get("nCdPedido"));
        }
        this.nValorTotal = Double.parseDouble(params.get("nValorTotal"));
        this.cStatus = params.get("cStatus");
        this.dPedido = LocalDate.parse(params.get("dPedido"));
        this.cFrequencia = params.get("cFrequencia");
        this.dAgendamentoColeta = LocalDate.parse(params.get("dAgendamentoColeta"));
        this.cOservacoes = params.get("cObservacoes");
        this.cCnpjRemetente = params.get("cCnpjRemetente");
        this.cCnpjDestinatario = params.get("cCnpjDestinatario");
        this.nCdEnderecoEmpresaRemetente = Integer.parseInt(params.get("nCdEnderecoEmpresaRemetente"));
        this.nCdEnderecoEmpresaDestinatario = Integer.parseInt(params.get("nCdEnderecoEmpresaDestinatario"));
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
    public String getCCnpjRemetente() {return cCnpjRemetente;}
    public String getCCnpjDestinatario() {return cCnpjDestinatario;}
    public int getNCdEnderecoEmpresaRemetente() {return nCdEnderecoEmpresaRemetente;}
    public int getNCdEnderecoEmpresaDestinatario() {return nCdEnderecoEmpresaDestinatario;}

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
                "\nObservações: " + cOservacoes + "\nCNPJ Remetente: " + cCnpjRemetente +
                "\nCNPJ Destinatário: " + cCnpjDestinatario + "\nEndereço Remetente: " + nCdEnderecoEmpresaRemetente +
                "\nEndereço Destinatário: " + nCdEnderecoEmpresaDestinatario;
    }

    // métodos
    @Override
    public Object getId(){return nCdPedido;}
}