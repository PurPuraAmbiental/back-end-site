package com.purpura.dao;

import com.purpura.model.Telefone;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**Classe DAO para a tabela Telefone
 * @author [Seu nome ou autor do código]*/

/**Classe implementando a interface generica DAO
 * e adicionando a Classe model equivalente: Telefone*/
public class TelefoneDAO extends DAO<Telefone> {

    /**Metodo para retornar o nome da tabela
     * @return uma String com o nome da tabela*/
    @Override
    public String getNomeTabela() {
        return "Telefone";
    }

    /**Metodo para instanciar um objeto
     * @param rs -> ResultSet
     * @return objeto Telefone*/
    @Override
    protected Telefone mapResultSet(ResultSet rs) throws SQLException {
        return new Telefone(
                rs.getInt("nCdTelefone"),
                rs.getInt("nCdEmpresa"),
                rs.getString("fone"),
                rs.getString("Descricao")
        );
    }

    /**Adicionando Metodo para pegar o nome das colunas
     * @return String com os nomes dos atributos da model*/
    @Override
    protected String getNomesColunas() {
        return "nCdTelefone, fone, nCdEmpresa, Descricao";
    }

    /**Adicionando metodo para Inserir conteudo no banco de dados
     * @param stmt -> String com o comando sql
     * @param entidade -> nome da tabela*/
    @Override
    protected void prepareStatementForSave(PreparedStatement stmt, Telefone entidade) throws SQLException {
        stmt.setInt(1, entidade.getnCdEmpresa());
        stmt.setInt(2, entidade.getnCdTelefone());
        stmt.setString(3, entidade.getCFone());
        stmt.setString(4, entidade.getCDescricao());
    }

    /**Adicionando metodo para Atualizar conteudo no banco de dados
     * @param stmt -> String com o comando sql
     * @param entidade -> nome da tabela*/
    @Override
    protected void prepareStatementForUpdate(PreparedStatement stmt, Telefone entidade) throws SQLException {
        prepareStatementForSave(stmt, entidade);
        stmt.setInt(9, entidade.getnCdTelefone());
    }

    /**Adcionando Metodo para buscar a primary key da coluna
     * @return chave primaria da coluna*/
    @Override
    protected String getColunaId() {
        return "nCdTelefone";
    }
}
