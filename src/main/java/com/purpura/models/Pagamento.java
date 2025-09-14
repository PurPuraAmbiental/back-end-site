package com.purpura.models;

import java.time.LocalDate;
import java.util.Map;

public class Pagamento implements Model{
    private int nCdPagamento;
    private LocalDate dPagamento;
    private String cStatusPagamento;
    private double nValorPago;
    private String cFormaPagamento;
    private String cObservacoes;
    private int nCdPedido;

    //ADICIONANDO METODO CONSTRUTOR
    public Pagamento(int nCdPagamento, LocalDate dPagamento, String cStatusPagamento, double nValorPago, String cFormaPagamento, String cObservacoes, int nCdPedido){
        this.nCdPagamento = nCdPagamento;
        this.dPagamento = dPagamento;
        this.cStatusPagamento = cStatusPagamento;
        this.nValorPago =nValorPago;
        this.cFormaPagamento = cFormaPagamento;
        this.cObservacoes = cObservacoes;
        this.nCdPedido = nCdPedido;
    }

    public Pagamento(Map<String, String> params){
        if(params.containsKey("nCdPagamento")){
            this.nCdPagamento = Integer.parseInt(params.get("nCdPagamento"));
        }
        this.dPagamento = LocalDate.parse(params.get("dPagamento"));
        this.cStatusPagamento = params.get("cStatusPagamento");
        this.nValorPago = Double.parseDouble(params.get("nValorPago"));
        this.cFormaPagamento = params.get("cFormaPagamento");
        this.cObservacoes = params.get("cObservacoes");
        this.nCdPedido = Integer.parseInt(params.get("nCdPedido"));
    }

    public double getNValorPago() {
        return nValorPago;
    }

    public int getNCdPagamento() {
        return nCdPagamento;
    }

    public LocalDate getDPagamento() {
        return dPagamento;
    }

    public String getCFormaPagamento() {
        return cFormaPagamento;
    }

    public String getCObservacoes() {
        return cObservacoes;
    }

    public String getCStatusPagamento() {
        return cStatusPagamento;
    }
    public int getNCdPedido() {return nCdPedido;}

    //ADICIONANDO OS SETTRS NECESSARIOS

    public void setCFormaPagamento(String cFormaPagamento) {
        this.cFormaPagamento = cFormaPagamento;
    }

    public void setCStatusPagamento(String cStatusPagamento) {
        this.cStatusPagamento = cStatusPagamento;
    }

    public void setCObservacoes(String cObservacoes) {
        this.cObservacoes = cObservacoes;
    }

    public void setDPagamento(LocalDate dPagamento) {
        this.dPagamento = dPagamento;
    }

    public void setNValorPago(double nValorPago) {
        this.nValorPago = nValorPago;
    }

    //ADICIONANDO TOSTRING
    public String toString(){
        return "Código : "+nCdPagamento+
                "\nValor Pago: "+nValorPago+
                "\nForma Pagamento: "+cFormaPagamento+
                "\nData Pagamento: "+dPagamento+
                "\nStatus Pagamento: "+cStatusPagamento+
                "\nObservações: "+cObservacoes;
    }

    // métodos
    @Override
    public Object getId(){return nCdPagamento;}
}