package com.purpura.dto;

/**
 * DTO que representa uma visão de um endereço de uma empresa.
 *
 * @param nCdEnderecoEmpresa código serial de um endereço
 * @param cBairro bairro do endereço
 * @param cLogradouro logradouro do endereço
 * @param cEstado estado do endereço
 * @param cCidade cidade do endereço
 * @param cCep CEP do endereço
 * @param cComplemento complemento do número do endereço
 * @param iNrEnderecoEmpresa número da empresa no logradouro
 * @param cNmEmpresa nome da empresa detentora do endereço
 * @param cCnpj CNPJ da empresa detentora do endereço
 */
public record EnderecoEmpresaView(
        int nCdEnderecoEmpresa,
        String cBairro,
        String cLogradouro,
        String cEstado,
        String cCidade,
        String cCep,
        String cComplemento,
        int iNrEnderecoEmpresa,
        String cNmEmpresa,
        String cCnpj
) {}