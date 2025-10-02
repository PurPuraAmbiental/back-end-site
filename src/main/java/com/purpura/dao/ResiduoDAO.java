package com.purpura.dao;

import com.purpura.model.Residuo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**Classe DAO para a tabela Residuo
 * @author [Seu nome ou autor do código]*/

/**Classe implementando a interface generica DAO
 * e adicionando a Classe model equivalente: Residuo*/
public class ResiduoDAO extends DAO<Residuo> {

    /**Metodo para retornar o nome da tabela
     * @return uma String com o nome da tabela*/
    @Override
    public String getNomeTabela() {
        return "Residuo";
    }

    /**Metodo para instanciar um objeto
     * @param rs -> ResultSet
     * @return objeto Residuo*/
    @Override
    protected Residuo mapResultSet(ResultSet rs) throws SQLException {
        return new Residuo(
                rs.getInt("nCdResiduo"),
                rs.getString("cNmResiduo"),
                rs.getString("cTipoUnidade"),
                rs.getDouble("nPrecoPadrao"),
                rs.getDouble("nVolumePadrao"),
                rs.getString("cCategoria"),
                rs.getString("cDescricao"),
                rs.getString("cCnpj")
        );
    }

    /**Adicionando Metodo para pegar o nome das colunas
     * @return String com os nomes dos atributos da model*/
    @Override
    protected String getNomesColunas() {
        return "cNmResiduo, cTipoUnidade, " +
                "nPrecoPadrao, nVolumePadrao, cCategoria, " +
                "cDescricao, cCnpj";
    }

    /**Adicionando metodo para Inserir conteudo no banco de dados
     * @param stmt -> String com o comando sql
     * @param entidade -> nome da tabela*/
    @Override
    protected void prepareStatementForSave(PreparedStatement stmt, Residuo entidade) throws SQLException {
        stmt.setString(1, entidade.getCNmResiduo());
        stmt.setString(2, entidade.getCTipoUnidade());
        stmt.setDouble(3, entidade.getNPrecoPadrao());
        stmt.setDouble(4, entidade.getNVolumePadrao());
        stmt.setString(5, entidade.getCCategoria());
        stmt.setString(6, entidade.getCDescricao());
        stmt.setString(7, entidade.getCCnpj());
    }

    /**Adicionando metodo para Atualizar conteudo no banco de dados
     * @param stmt -> String com o comando sql
     * @param entidade -> nome da tabela*/
    @Override
    protected void prepareStatementForUpdate(PreparedStatement stmt, Residuo entidade) throws SQLException {
        prepareStatementForSave(stmt, entidade);
        stmt.setInt(8, entidade.getNCdResiduo());
    }

    /**Adcionando Metodo para buscar a primary key da coluna
     * @return chave primaria da coluna*/
    @Override
    protected String getColunaId() {
        return "nCdResiduo";
    }
}
