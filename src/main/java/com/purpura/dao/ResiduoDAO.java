package com.purpura.dao;

import com.purpura.dto.ResiduoView;
import com.purpura.exception.ConnectionFailedException;
import com.purpura.model.Residuo;
import com.purpura.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**Classe DAO para a tabela Residuo
 * @author [Seu nome ou autor do código]*/

/**Classe implementando a interface generica DAO
 * e adicionando a Classe model equivalente: Residuo*/
public class ResiduoDAO extends DAO<Residuo> {

    /**Metodo para retornar o nome da tabela
     * @return uma String com o nome da tabela*/
    @Override
    public String getNomeTabela() {
        return "residuo";
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
       // stmt.setInt(8, entidade.getNCdResiduo());
    }

    /**Adicionando metodo para Atualizar conteudo no banco de dados
     * @param stmt -> String com o comando sql
     * @param entidade -> nome da tabela*/
    @Override
    protected void prepareStatementForUpdate(PreparedStatement stmt, Residuo entidade) throws SQLException {
        prepareStatementForSave(stmt, entidade);
        stmt.setInt(8, entidade.getNCdResiduo());
        System.out.println("Entidade:");
        System.out.println("CNmResiduo: " + entidade.getCNmResiduo());
        System.out.println("CTipoUnidade: " + entidade.getCTipoUnidade());
        System.out.println("NPrecoPadrao: " + entidade.getNPrecoPadrao());
        System.out.println("NVolumePadrao: " + entidade.getNVolumePadrao());
        System.out.println("CCategoria: " + entidade.getCCategoria());
        System.out.println("CDescricao: " + entidade.getCDescricao());
        System.out.println("CCnpj: " + entidade.getCCnpj());

    }

    /**Adcionando Metodo para buscar a primary key da coluna
     * @return chave primaria da coluna*/
    @Override
    protected String getColunaId() {
        return "nCdResiduo";
    }

    public List<ResiduoView> listarComEmpresa() throws ConnectionFailedException {
        List<ResiduoView> listaView = new ArrayList<>();

        final String sql_join = "SELECT " +
                "r.nCdResiduo, r.cNmResiduo, r.cTipoUnidade, r.nPrecoPadrao, r.nVolumePadrao, " +
                "r.cCategoria, r.cDescricao, " +
                "e.cNmEmpresa, r.cCnpj " +
                "FROM Residuo r " +
                "INNER JOIN Empresa e ON r.cCnpj = e.cCnpj";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql_join);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ResiduoView view = new ResiduoView(
                        rs.getInt("nCdResiduo"),
                        rs.getString("cNmResiduo"),
                        rs.getString("cTipoUnidade"),
                        rs.getDouble("nPrecoPadrao"),
                        rs.getDouble("nVolumePadrao"),
                        rs.getString("cCategoria"),
                        rs.getString("cDescricao"),
                        rs.getString("cNmEmpresa"),
                        rs.getString("cCnpj")
                );
                listaView.add(view);
            }
        } catch (SQLException e) {
            throw new ConnectionFailedException();
        }
        return listaView;
    }
}
