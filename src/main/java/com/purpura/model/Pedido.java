package com.purpura.model;

import java.time.LocalDate;
import java.util.Map;

/** Classe Modelo para a tabela Pedido
 *@author Bruna Oliveira
 * */
public class Pedido implements Model{
    /**Adicionando os atributos da tabela como atributos da classe
     * atributos dados com prefixos da tabela de banco de dado*/
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

    /**Adicionando um metodo Construtor
     * @param nValorTotal - valor total do pedido
     * @param cStatus - status atual do pedido
     * @param dPedido - data em que o pedido foi realizado
     * @param cFrequencia - frequência de coleta
     * @param dAgendamentoColeta - data agendada para coleta
     * @param cOservacoes - observações adicionais do pedido
     * @param nCdPedido - atributo identificador dessa instancia de Pedido
     * @param cCnpjRemetente - chave estrangeira da Empresa remetente
     * @param cCnpjDestinatario - chave estrangeira da Empresa destinatária
     * @param nCdEnderecoEmpresaRemetente - chave estrangeira do endereço da Empresa remetente
     * @param nCdEnderecoEmpresaDestinatario - chave estrangeira do endereço da Empresa destinatária
     * */
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

    /**Construtor que inicializa objetos
     *@param params -> um map*/
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

    /**Metodo getNValorTotal
     * @return valor total do pedido*/
    public double getNValorTotal() { return nValorTotal; }

    /**Metodo getNCdPedido
     * @return Primary key*/
    public int getNCdPedido() { return nCdPedido; }

    /**Metodo getDPedido
     * @return data em que o pedido foi realizado*/
    public LocalDate getDPedido() { return dPedido; }

    /**Metodo getCFrequencia
     * @return frequência de coleta*/
    public String getCFrequencia() { return cFrequencia; }

    /**Metodo getDAgendamentoColeta
     * @return data agendada para coleta*/
    public LocalDate getDAgendamentoColeta() { return dAgendamentoColeta; }

    /**Metodo getCObservacoes
     * @return observações adicionais*/
    public String getCObservacoes() { return cOservacoes; }

    /**Metodo getCStatus
     * @return status atual do pedido*/
    public String getCStatus() { return cStatus; }

    /**Metodo getCCnpjRemetente
     * @return CNPJ da empresa remetente*/
    public String getCCnpjRemetente() { return cCnpjRemetente; }

    /**Metodo getCCnpjDestinatario
     * @return CNPJ da empresa destinatária*/
    public String getCCnpjDestinatario() { return cCnpjDestinatario; }

    /**Metodo getNCdEnderecoEmpresaRemetente
     * @return código do endereço da empresa remetente*/
    public int getNCdEnderecoEmpresaRemetente() { return nCdEnderecoEmpresaRemetente; }

    /**Metodo getNCdEnderecoEmpresaDestinatario
     * @return código do endereço da empresa destinatária*/
    public int getNCdEnderecoEmpresaDestinatario() { return nCdEnderecoEmpresaDestinatario; }

    /**Metodo setCFrequencia
     * @param cFrequencia*/
    public void setCFrequencia(String cFrequencia) { this.cFrequencia = cFrequencia; }

    /**Metodo setCObservacoes
     * @param cOservacoes*/
    public void setCObservacoes(String cOservacoes) { this.cOservacoes = cOservacoes; }

    /**Metodo setDAgendamentoColeta
     * @param dAgendamentoColeta*/
    public void setDAgendamentoColeta(LocalDate dAgendamentoColeta) { this.dAgendamentoColeta = dAgendamentoColeta; }

    /**Metodo setCStatus
     * @param cStatus*/
    public void setCStatus(String cStatus) { this.cStatus = cStatus; }

    /**Metodo setDPedido
     * @param dPedido*/
    public void setDPedido(LocalDate dPedido) { this.dPedido = dPedido; }

    /**Metodo setNValorTotal
     * @param nValorTotal*/
    public void setNValorTotal(double nValorTotal) { this.nValorTotal = nValorTotal; }

    /**Metodo toString
     *@return informação dos campos da tabela*/
    @Override
    public String toString(){
        return "Código: " + nCdPedido + "\nValor Total: " + nValorTotal +
                "\nStatus: " + cStatus + "\nData Pedido: " + dPedido +
                "\nFrequência" + cFrequencia + "\nData Agendamento: " + dAgendamentoColeta +
                "\nObservações: " + cOservacoes + "\nCNPJ Remetente: " + cCnpjRemetente +
                "\nCNPJ Destinatário: " + cCnpjDestinatario + "\nEndereço Remetente: " + nCdEnderecoEmpresaRemetente +
                "\nEndereço Destinatário: " + nCdEnderecoEmpresaDestinatario;
    }

    /**Metodo para retornar a tabela
     * @return nome da tabela*/
    @Override
    public Object getId(){ return nCdPedido; }
}
