package com.purpura.dto;

/**
 * DTO que representa uma visão de um telefone.
 *
 * @param nCdTelefone número serial do telefone
 * @param cNrTelefone número de telefone
 * @param cNmEmpresa nome da empresa dona do telefone
 */
public record TelefoneView(
        int nCdTelefone,
        String cNrTelefone,
        String cNmEmpresa,
        String cDescricao
) {}