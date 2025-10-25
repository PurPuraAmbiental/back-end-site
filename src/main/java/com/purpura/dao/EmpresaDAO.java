package com.purpura.dao;

import com.purpura.exception.ConnectionFailedException;
import com.purpura.exception.NotFoundException;
import com.purpura.model.Empresa;
import com.purpura.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**Classe DAO para a tabela Empresa
 * @author [Seu nome ou autor do código]*/

/**Classe implementando a interface generica DAO
 * e adicionando a Classe model equivalente: Empresa*/
public class EmpresaDAO extends DAO<Empresa> {

    /**Metodo para retornar o nome da tabela
     * @return uma String com o nome da tabela*/
    @Override
    public String getNomeTabela() {
        return "empresa";
    }

    /**Metodo para instanciar um objeto
     * @param rs -> ResultSet
     * @return objeto Empresa*/
    @Override
    protected Empresa mapResultSet(ResultSet rs) throws java.sql.SQLException {
        return new Empresa(
                rs.getString("cNmEmpresa"),
                rs.getString("cSenha"),
                rs.getString("cCnpj"),
                rs.getString("cAtivo").charAt(0),
                rs.getString("cEmail")
        );
    }

    /**Adicionando Metodo para pegar o nome das colunas
     * @return String com os nomes dos atributos da model*/
    @Override
    protected String getNomesColunas() {
        return "cNmEmpresa, cSenha, cCnpj, cAtivo, cEmail";
    }

    /**Adicionando metodo para Inserir conteudo no banco de dados
     * @param stmt -> String com o comando sql
     * @param entidade -> nome da tabela*/
    @Override
    protected void prepareStatementForSave(java.sql.PreparedStatement stmt, Empresa entidade) throws java.sql.SQLException {
        stmt.setString(1, entidade.getCNmEmpresa());
        stmt.setString(2, entidade.getCSenha());
        stmt.setString(3, entidade.getCCnpj());
        stmt.setString(4, String.valueOf(entidade.getCAtivo()));
        stmt.setString(5, entidade.getCEmail());
    }

    /**Adicionando metodo para Atualizar conteudo no banco de dados
     * @param stmt -> String com o comando sql
     * @param entidade -> nome da tabela*/
    @Override
    protected void prepareStatementForUpdate(java.sql.PreparedStatement stmt, Empresa entidade) throws java.sql.SQLException {
        stmt.setString(1, entidade.getCNmEmpresa());
        stmt.setString(2, entidade.getCSenha());
        stmt.setString(3, String.valueOf(entidade.getCAtivo()));
        stmt.setString(4, entidade.getCEmail());
        stmt.setString(5, entidade.getCCnpj());
    }

    public List<Empresa> buscarEmpresasComResiduos(){
        List<Empresa> lista = new ArrayList<>();
        String sql =
                "SELECT e.* " +
                "FROM empresa e " +
                "LEFT JOIN residuo r ON e.ccnpj = r.ccnpj " +
                "GROUP BY e.ccnpj " +
                "HAVING COUNT(r.ncdresiduo) > 0";

        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()){
                lista.add(mapResultSet(rs));
            }
        } catch (SQLException e){
            e.printStackTrace();
            throw new ConnectionFailedException();
        }
        return lista;
    }

    public List<Empresa> buscarEmpresasSemResiduos(){
        List<Empresa> lista = new ArrayList<>();
        String sql =
                "SELECT e.* " +
                        "FROM empresa e " +
                        "LEFT JOIN residuo r ON e.ccnpj = r.ccnpj " +
                        "GROUP BY e.ccnpj " +
                        "HAVING COUNT(r.ncdresiduo) = 0";

        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()){
                lista.add(mapResultSet(rs));
            }
        } catch (SQLException e){
            e.printStackTrace();
            throw new ConnectionFailedException();
        }
        return lista;
    }

    /**Adcionando Metodo para buscar a primary key da coluna
     * @return chave primaria da coluna*/
    @Override
    protected String getColunaId() {
        return "ccnpj";
    }
}
