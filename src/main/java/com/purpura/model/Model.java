package com.purpura.model;

/**
 * Interface base para todas as entidades do sistema.
 *
 * Define um contrato mínimo para que as classes de modelo forneçam
 * um identificador único por meio do método {@link #getId()}.
 *
 * Essa interface é utilizada pela camada genérica de persistência
 * ("DAO<T extends Model>"), permitindo que o DAO execute
 * operações de forma padronizada sobre qualquer entidade,
 * independentemente da sua estrutura interna.
 *
 * @author Kevin de Oliveira
 */
public interface Model {
    /**
     * Retorna o identificador único da entidade.
     * @return objeto que representa o ID da entidade
     */
    Object getId();
}
