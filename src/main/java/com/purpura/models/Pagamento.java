public class Pagamento {
    private int nCdPagamento;
    private LocalDate dPagamento;
    private String cStatusPagamento;
    private double nValorPago;
    private String cFormaPagamento;
    private String cObservacoes;

    //ADICIONANDO METODO CONSTRUTOR
    public Pagamento(int nCdPagamento, LocalDate dPagamento, String cStatusPagamento, double nValorPago, String cFormaPagamento, String cObservacoes){
        this.nCdPagamento = nCdPagamento;
        this.dPagamento = dPagamento;
        this.cStatusPagamento = cStatusPagamento;
        this.nValorPago =nValorPago;
        this.cFormaPagamento = cFormaPagamento;
        this.cObservacoes = cObservacoes;
    }

    public double getnValorPago() {
        return nValorPago;
    }

    public int getnCdPagamento() {
        return nCdPagamento;
    }

    public LocalDate getdPagamento() {
        return dPagamento;
    }

    public String getcFormaPagamento() {
        return cFormaPagamento;
    }

    public String getcObservacoes() {
        return cObservacoes;
    }

    public String getcStatusPagamento() {
        return cStatusPagamento;
    }

    //ADICIONANDO OS SETTRS NECESSARIOS

    public void setcFormaPagamento(String cFormaPagamento) {
        this.cFormaPagamento = cFormaPagamento;
    }

    public void setcStatusPagamento(String cStatusPagamento) {
        this.cStatusPagamento = cStatusPagamento;
    }

    public void setcObservacoes(String cObservacoes) {
        this.cObservacoes = cObservacoes;
    }

    public void setdPagamento(LocalDate dPagamento) {
        this.dPagamento = dPagamento;
    }

    public void setnValorPago(double nValorPago) {
        this.nValorPago = nValorPago;
    }

    //ADICIONANDO TOSTRING
    public String toString(){
        return "codigo de pagamento: "+this.getnCdPagamento()+
                "\nvalor pago: "+this.getnValorPago()+
                "\nforma de pagamento: "+this.getcFormaPagamento()+
                "\ndata de pagamento: "this.getdPagamento()+
                "\nstatus do pagamento: "this.getcStatusPagamento()+
                "\nobservacoes: "this.getcObservacoes()
    }
}