package com.purpura.dao;

import com.purpura.model.Administrador;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**Classe DAO para a tabela Administrador
 * @author [Seu nome ou autor do código]*/

/**Classe implementando a interface generica DAO
 * e adicionando a Classe model equivalente: Administrador*/
public class AdministradorDAO extends DAO<Administrador> {

    /**Metodo para retornar o nome da tabela
     * @return uma String com o nome da tabela*/
    @Override
    public String getNomeTabela(){
        return "Administrador";
    }

    /**Metodo para instanciar um objeto
     * @param rs -> ResultSet
     * @return objeto Administrador*/
    @Override
    protected Administrador mapResultSet(ResultSet rs) throws SQLException{
        return new Administrador(
                rs.getString("cEmail"),
                rs.getString("cSenha"),
                rs.getString("cNmAdministrador")
        );
    }

    /**Adicionando Metodo para pegar o nome das colunas
     * @return String com os nomes dos atributos da model*/
    @Override
    protected String getNomesColunas(){
        return "cNmAdministrador, cEmail, cSenha";
    }

    /**Adicionando metodo para Inserir conteudo no banco de dados
     * @param stmt -> String com o comando sql
     * @param entidade -> nome da tabela*/
    @Override
    protected void prepareStatementForSave(PreparedStatement stmt, Administrador entidade) throws SQLException{
        stmt.setString(1, entidade.getCNmAdministrador());
        stmt.setString(2, entidade.getCEmail());
        stmt.setString(3, entidade.getCSenha());
    }

    /**Adicionando metodo para Atualizar conteudo no banco de dados
     * @param stmt -> String com o comando sql
     * @param entidade -> nome da tabela*/
    @Override
    protected void prepareStatementForUpdate(PreparedStatement stmt, Administrador entidade) throws SQLException {
        stmt.setString(1, entidade.getCNmAdministrador());
        stmt.setString(2, entidade.getCSenha());
        stmt.setString(3, entidade.getCEmail());
        System.out.println("motivo do erro: nome: "+entidade.getCNmAdministrador()+" |senha: "+entidade.getCSenha()+" |email"+entidade.getCEmail());
    }

    /**Adcionando Metodo para buscar a primary key da coluna
     * @return chave primaria da coluna*/
    @Override
    protected String getColunaId(){
        return "cEmail";
    }
}
