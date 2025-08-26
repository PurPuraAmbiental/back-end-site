public class Pedido {
    private double nValorTotal;
    private String cStatus;
    private LocalDate dPedido;
    private String cFrequencia;
    private LocalDate dAgendamentoColeta;
    private String cOservações;
    private Final nCdPedido;

    public Pedido(double nValorTotal, String cStatus, LocalDate dPedido, String cFrequencia,  LocalDate dAgendamentoColeta, String cOservações, Final nCdPedido){
        this.nValorTotal = nValorTotal;
        this.cStatus = cStatus;
        this.cFrequencia = cFrequencia;
        this.cOservações = cOservações;
        this.nCdPedido = nCdPedido;
        this.dAgendamentoColeta = dAgendamentoColeta;
        this.nCdPedido = nCdPedido;

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

    public String getcOservações() {
        return cOservações;
    }

    public String getcStatus() {
        return cStatus;
    }

    public void setcFrequencia(String cFrequencia) {
        this.cFrequencia = cFrequencia;
    }

    public void setcOservações(String cOservações) {
        this.cOservações = cOservações;
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

    public String toString(){
        return ""

    }




}