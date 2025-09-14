package com.purpura.dao;

import com.purpura.models.EnderecoEmpresa;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EnderecoEmpresaDAO extends DAO<EnderecoEmpresa> {

    // RETORNAR O NOME DA TABELA
    @Override
    public String getNomeTabela() {
        return "EnderecoEmpresa";
    }

    // MAPEAR UMA LINHA DO RESULTSET PARA A CLASSE EnderecoEmpresa
    @Override
    protected EnderecoEmpresa mapResultSet(ResultSet rs) throws SQLException {
        return new EnderecoEmpresa(
                rs.getInt("nCdEnderecoEmpresa"),
                rs.getString("cBairro"),
                rs.getString("cLogradouro"),
                rs.getString("cEstado"),
                rs.getString("cCidade"),
                rs.getString("cComplemento"),
                rs.getString("cCep"),
                rs.getInt("iNrEnderecoEmpresa"),  // CUIDADO: o tipo no construtor está errado
                rs.getString("cCnpj")
        );
    }

    // CORRIGIR AQUI PARA USAR O METODO CERTO
    @Override
    protected String getNomesColunas() {
        return "cBairro, cLogradouro, cEstado, cCidade, cComplemento, cCep, iNrEnderecoEmpresa, cCnpj";
    }

    // PREPARAR O STATEMENT PARA INSERÇÃO
    @Override
    protected void prepareStatementForSave(PreparedStatement stmt, EnderecoEmpresa entidade) throws SQLException {
        stmt.setString(1, entidade.getCBairro());
        stmt.setString(2, entidade.getCLogradouro());
        stmt.setString(3, entidade.getCEstado());
        stmt.setString(4, entidade.getCCidade());
        stmt.setString(5, entidade.getCComplemento());
        stmt.setString(6, entidade.getCCep());
        stmt.setInt(7, entidade.getINrEnderecoEmpresa());  // Supondo que você adicione esse getter
        stmt.setString(8, entidade.getCCnpj());
    }

    // PREPARAR O STATEMENT PARA ATUALIZAÇÃO
    @Override
    protected void prepareStatementForUpdate(PreparedStatement stmt, EnderecoEmpresa entidade) throws SQLException {
        prepareStatementForSave(stmt, entidade);
        stmt.setInt(9, entidade.getNCdEnderecoEmpresa()); // WHERE id = ?
    }

    // COLUNA IDENTIFICADORA
    @Override
    protected String getColunaId() {
        return "nCdEnderecoEmpresa";
    }
}
