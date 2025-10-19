package com.purpura.dto;

/**
 * DTO que representa uma visão de um resíduo.
 *
 * @param nCdResiduo código identificador do resíduo
 * @param cNmResiduo nome do resíduo
 * @param cTipoUnidade unidade de medida do resíduo
 * @param nPrecoPadrao preço padrão associado ao resíduo
 * @param nVolumePadrao volume padrão associado ao resíduo
 * @param cCategoria categoria do resíduo
 * @param cDescricao descrição detalhada do resíduo
 * @param cNmEmpresa nome da empresa dona do resíduo
 * @param cCnpj código da empresa dona do resíduo
 */
public record ResiduoView(
        int nCdResiduo,
        String cNmResiduo,
        String cTipoUnidade,
        double nPrecoPadrao,
        double nVolumePadrao,
        String cCategoria,
        String cDescricao,
        String cNmEmpresa,
        String cCnpj
) {}