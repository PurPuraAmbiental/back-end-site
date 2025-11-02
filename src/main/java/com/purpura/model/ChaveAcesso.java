package com.purpura.model;

import java.text.ParseException;
import java.util.Map;

/**
 * Modelo que representa a entidade ChaveAcesso.
 * Cada instância corresponde a um registro na tabela "ChaveAcesso".
 *
 * Contém informações sobre a chave de acesso gerada, seu código identificador,
 * o hash associado e o status de ativação.
 *
 * @author Kevin Oliveira
 */
public class ChaveAcesso implements Model {
    /**Adicionando os atributos da tabela como atributos da classe.
     * atributos dados com prefixos da tabela de banco de dado*/

    private int nCdChaveAcesso;
    private String cHash;
    private char cAtivo;

    /**
     * Construtor padrão.
     *
     * @param nCdChaveAcesso código identificador da chave de acesso (chave primária)
     * @param cHash valor hash associado à chave de acesso
     * @param cAtivo indica se a chave está ativa ('S' ou 'N')
     */
    public ChaveAcesso(int nCdChaveAcesso, String cHash, char cAtivo) {
        this.nCdChaveAcesso = nCdChaveAcesso;
        this.cHash = cHash;
        this.cAtivo = cAtivo;
    }

    /**
     * Construtor alternativo que inicializa o objeto a partir de um mapa de parâmetros,
     * permitindo capturar dados de formulários ou requisições HTTP, por exemplo.
     *
     * @param params mapa contendo os campos da chave de acesso
     * @throws ParseException se algum valor não puder ser processado corretamente
     */
    public ChaveAcesso(Map<String, String> params) throws ParseException {
        if (params.containsKey("nCdChaveAcesso")) {
            this.nCdChaveAcesso = Integer.parseInt(params.get("nCdChaveAcesso"));
        }
        this.cHash = params.get("cChave");
        if (params.containsKey("cAtivo") && params.get("cAtivo") != null && !params.get("cAtivo").isEmpty()) {
            this.cAtivo = params.get("cAtivo").charAt(0);
        }
    }

    // Getters e Setters
    /**
     * @return o código identificador da chave de acesso
     */
    public int getNCdChaveAcesso() { return nCdChaveAcesso; }
    /**
     * @return o valor hash da chave de acesso
     */
    public String getCHash() { return cHash;}
    /**
     * @return o status de ativação da chave ('S' para ativo, 'N' para inativo)
     */
    public char getCAtivo() { return cAtivo; }

    /**
     * Define o status de ativação da chave de acesso.
     *
     * @param cAtivo caractere indicando se a chave está ativa
     */
    public void setCAtivo(char cAtivo) {
        this.cAtivo = cAtivo;
    }

    /**
     * Retorna uma representação textual da entidade.
     *
     * @return string contendo os valores dos atributos
     */
    @Override
    public String toString() {
        return "ChaveAcesso{" +
                "nCdChaveAcesso=" + nCdChaveAcesso +
                ", cHash='" + cHash + '\'' +
                ", cAtivo=" + cAtivo +
                '}';
    }

    /**
     * Retorna o identificador único da entidade.
     * Esse valor é utilizado pela DAO genérica para operações de persistência.
     *
     * @return código identificador da chave de acesso
     */
    @Override
    public Object getId() {
        return nCdChaveAcesso;
    }
}
