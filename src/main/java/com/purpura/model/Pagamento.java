package com.purpura.model;

import java.time.LocalDate;
import java.util.Map;

/**Classe Modelo para a tabela Pagamento
 * @author Kevin de Oliveira*/
public class Pagamento implements Model{
    /**Adicionando os atributos da tabela como atributos da classe
     * atributos dados com prefixos da tabela de banco de dados*/
    private int nCdPagamento;
    private LocalDate dPagamento;
    private String cStatusPagamento;
    private double nValorPago;
    private String cFormaPagamento;
    private String cObservacoes;
    private int nCdPedido;

    /**Adicionando um método Construtor
     * @param nCdPagamento - primary key da tabela Pagamento
     * @param dPagamento - data do pagamento
     * @param cStatusPagamento - status do pagamento
     * @param nValorPago - valor pago
     * @param cFormaPagamento - forma utilizada para pagamento
     * @param cObservacoes - observações adicionais
     * @param nCdPedido - foreign key com a tabela Pedido*/
    public Pagamento(int nCdPagamento, LocalDate dPagamento, String cStatusPagamento, double nValorPago, String cFormaPagamento, String cObservacoes, int nCdPedido){
        this.nCdPagamento = nCdPagamento;
        this.dPagamento = dPagamento;
        this.cStatusPagamento = cStatusPagamento;
        this.nValorPago = nValorPago;
        this.cFormaPagamento = cFormaPagamento;
        this.cObservacoes = cObservacoes;
        this.nCdPedido = nCdPedido;
    }

    /**Adicionando um método construtor que inicializa objetos
     * @param params -> um map com os parâmetros do pagamento*/
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

    /**Método getNValorPago
     * @return valor pago*/
    public double getNValorPago() {
        return nValorPago;
    }
    /**Método getNCdPagamento
     * @return primary key de Pagamento*/
    public int getNCdPagamento() {
        return nCdPagamento;
    }
    /**Método getDPagamento
     * @return data do pagamento*/
    public LocalDate getDPagamento() {
        return dPagamento;
    }
    /**Método getCFormaPagamento
     * @return forma de pagamento utilizada*/
    public String getCFormaPagamento() {
        return cFormaPagamento;
    }
    /**Método getCObservacoes
     * @return observações adicionais*/
    public String getCObservacoes() {
        return cObservacoes;
    }
    /**Método getCStatusPagamento
     * @return status do pagamento*/
    public String getCStatusPagamento() {
        return cStatusPagamento;
    }
    /**Método getNCdPedido
     * @return código do pedido vinculado*/
    public int getNCdPedido() {return nCdPedido;}

    /**Método setCFormaPagamento
     * @param cFormaPagamento -> altera a forma de pagamento*/
    public void setCFormaPagamento(String cFormaPagamento) {
        this.cFormaPagamento = cFormaPagamento;
    }
    /**Método setCStatusPagamento
     * @param cStatusPagamento -> altera o status do pagamento*/
    public void setCStatusPagamento(String cStatusPagamento) {
        this.cStatusPagamento = cStatusPagamento;
    }
    /**Método setCObservacoes
     * @param cObservacoes -> altera as observações do pagamento*/
    public void setCObservacoes(String cObservacoes) {
        this.cObservacoes = cObservacoes;
    }
    /**Método setDPagamento
     * @param dPagamento -> altera a data do pagamento*/
    public void setDPagamento(LocalDate dPagamento) {
        this.dPagamento = dPagamento;
    }
    /**Método setNValorPago
     * @param nValorPago -> altera o valor pago*/
    public void setNValorPago(double nValorPago) {
        this.nValorPago = nValorPago;
    }

    /**Método toString
     * @return informações sobre os campos da tabela Pagamento*/
    @Override
    public String toString(){
        return "Código : "+nCdPagamento+
                "\nValor Pago: "+nValorPago+
                "\nForma Pagamento: "+cFormaPagamento+
                "\nData Pagamento: "+dPagamento+
                "\nStatus Pagamento: "+cStatusPagamento+
                "\nObservações: "+cObservacoes;
    }

    /**Método getId
     * @return retorna o código do pagamento*/
    @Override
    public Object getId(){return nCdPagamento;}
}
