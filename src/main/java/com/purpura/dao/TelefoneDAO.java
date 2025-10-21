package com.purpura.dao;

import com.purpura.dto.TelefoneView;
import com.purpura.exception.ConnectionFailedException;
import com.purpura.model.Telefone;
import com.purpura.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**Classe DAO para a tabela Telefone
 * @author [Seu nome ou autor do código]*/

/**Classe implementando a interface generica DAO
 * e adicionando a Classe model equivalente: Telefone*/
public class TelefoneDAO extends DAO<Telefone> {

    /**Metodo para retornar o nome da tabela
     * @return uma String com o nome da tabela*/
    @Override
    public String getNomeTabela() {
        return "telefone";
    }

    /**Metodo para instanciar um objeto
     * @param rs -> ResultSet
     * @return objeto Telefone*/
    @Override
    protected Telefone mapResultSet(ResultSet rs) throws SQLException {
        return new Telefone(
                rs.getString("nCdTelefone"),
                rs.getInt("ccnpj"),
                rs.getString("cNrTelefone"),
                rs.getString("cDescricao")
        );
    }

    /**Adicionando Metodo para pegar o nome das colunas
     * @return String com os nomes dos atributos da model*/
    @Override
    protected String getNomesColunas() {
        return "cNrTelefone, cCnpj, cDescricao";
    }

    /**Adicionando metodo para Inserir conteudo no banco de dados
     * @param stmt -> String com o comando sql
     * @param entidade -> nome da tabela*/
    @Override
    protected void prepareStatementForSave(PreparedStatement stmt, Telefone entidade) throws SQLException {
        stmt.setString(1, entidade.getcNrTelefone());
        stmt.setString(2, entidade.getCcnpj());
        stmt.setString(3, entidade.getCDescricao());
    }


    /**Adicionando metodo para Atualizar conteudo no banco de dados
     * @param stmt -> String com o comando sql
     * @param entidade -> nome da tabela*/
    @Override
    protected void prepareStatementForUpdate(PreparedStatement stmt, Telefone entidade) throws SQLException {
        prepareStatementForSave(stmt, entidade);
        stmt.setInt(4, entidade.getnCdTelefone());
    }

    /**Adcionando Metodo para buscar a primary key da coluna
     * @return chave primaria da coluna*/
    @Override
    protected String getColunaId() {
        return "nCdTelefone";
    }

    public List<TelefoneView> listarComEmpresa() throws ConnectionFailedException {
        List<TelefoneView> listaView = new ArrayList<>();

         String sql_join = "SELECT " +
                "t.nCdTelefone, t.cNrTelefone, " +
                "t.cCnpj, e.cNmEmpresa " +
                "FROM Telefone t " +
                "INNER JOIN Empresa e ON t.cCnpj = e.cCnpj";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql_join);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                TelefoneView view = new TelefoneView(
                        rs.getInt("nCdTelefone"),
                        rs.getString("cNrTelefone"),
                        rs.getString("cNmEmpresa")
                );
                listaView.add(view);
            }
        } catch (SQLException e) {
            throw new ConnectionFailedException();
        }
        return listaView;
    }
}
