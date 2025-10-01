package com.purpura.model;

import java.util.Map;

/**Classe Modelo para a tabela Mensagem
 * @author Kevin de Oliveira*/
public class Mensagem implements Model{
    /**Adicionando os atributos da tabela como atributos da classe
     * atributos dados com prefixos da tabela de banco de dados*/
    private int nCdMensagem;
    private final String cConteudo; // atributo final para garantir que a mensagem não seja alterada
    private final String cCnpjRemetente; // código da empresa remetente
    private final String cCnpjDestinatario; // código da empresa destinatária

    /**Adicionando um método Construtor
     * @param nCdMensagem - primary key da tabela Mensagem
     * @param cConteudo - conteúdo da mensagem
     * @param cCnpjRemetente - CNPJ da empresa remetente
     * @param cCnpjDestinatario - CNPJ da empresa destinatária*/
    public Mensagem(int nCdMensagem, String cConteudo, String cCnpjRemetente,
                    String cCnpjDestinatario) {
        this.nCdMensagem = nCdMensagem;
        this.cConteudo = cConteudo;
        this.cCnpjRemetente = cCnpjRemetente;
        this.cCnpjDestinatario = cCnpjDestinatario;
    }

    /**Adicionando um método construtor que inicializa objetos
     * @param params -> um map com os parâmetros da mensagem*/
    public Mensagem(Map<String, String> params) {
        if(params.containsKey("nCdMensagem")) {
            this.nCdMensagem = Integer.parseInt(params.get("nCdMensagem"));
        }
        this.cConteudo = params.get("cConteudo");
        this.cCnpjRemetente = params.get("cCnpjRemetente");
        this.cCnpjDestinatario = params.get("cCnpjDestinatario");
    }

    /**Método getNCdMensagem
     * @return primary key de Mensagem*/
    public int getNCdMensagem() {return nCdMensagem;}
    /**Método getCConteudo
     * @return conteúdo da mensagem*/
    public String getCConteudo() {return cConteudo;}
    /**Método getCCnpjRemetente
     * @return CNPJ da empresa remetente*/
    public String getCCnpjRemetente() {return cCnpjRemetente;}
    /**Método getCCnpjDestinatario
     * @return CNPJ da empresa destinatária*/
    public String getCCnpjDestinatario() {return cCnpjDestinatario;}

    /**Não existem setters, pois o conteúdo da mensagem e as informações de envio
     * não podem ser alterados após a criação*/

    /**Método toString
     * @return informações sobre os campos da tabela Mensagem*/
    @Override
    public String toString() {
        return "Código: " + nCdMensagem + "\nConteúdo: " + cConteudo +
                "\nEmpresa Remetente: " + cCnpjRemetente + "\nEmpresa Destinatária: " + cCnpjDestinatario;
    }

    /**Método getId
     * @return retorna o código da mensagem*/
    @Override
    public Object getId(){return nCdMensagem;}
}
