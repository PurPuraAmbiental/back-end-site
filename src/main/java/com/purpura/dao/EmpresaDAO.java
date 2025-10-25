package com.purpura.dao;

import com.purpura.exception.ConnectionFailedException;
import com.purpura.exception.NotFoundException;
import com.purpura.model.Empresa;
import com.purpura.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO responsável pelas operações da entidade Empresa.
 * Implementa os métodos genéricos da classe DAO.
 * Veja classe abstrata para documentação detalhada dos métodos.
 *
 * @author Kevin de Oliveira
 */
public class EmpresaDAO extends DAO<Empresa> {

    @Override
    public String getNomeTabela() {
        return "empresa";
    }

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

    @Override
    protected String getNomesColunas() {
        return "cNmEmpresa, cSenha, cCnpj, cAtivo, cEmail";
    }

    @Override
    protected void prepareStatementForSave(java.sql.PreparedStatement stmt, Empresa entidade) throws java.sql.SQLException {
        stmt.setString(1, entidade.getCNmEmpresa());
        stmt.setString(2, entidade.getCSenha());
        stmt.setString(3, entidade.getCCnpj());
        stmt.setString(4, String.valueOf(entidade.getCAtivo()));
        stmt.setString(5, entidade.getCEmail());
    }

    protected void prepareStatementForUpdate(java.sql.PreparedStatement stmt, Empresa entidade) throws java.sql.SQLException {
        stmt.setString(1, entidade.getCNmEmpresa());
        stmt.setString(2, entidade.getCSenha());
        stmt.setString(3, String.valueOf(entidade.getCAtivo()));
        stmt.setString(4, entidade.getCEmail());
        stmt.setString(5, entidade.getCCnpj());
    }

    /**
     * Retorna todas as empresas que possuem pelo menos um resíduo cadastrado.
     *
     * @return lista de empresas com resíduos
     * @throws ConnectionFailedException se ocorrer um erro ao conectar ao banco
     */
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

    /**
     * Retorna todas as empresas que não possuem nenhum resíduo cadastrado.
     *
     * @return lista de empresas sem resíduos
     * @throws ConnectionFailedException se ocorrer um erro ao conectar ao banco
     */
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

    @Override
    protected String getColunaId() {
        return "cCnpj";
    }
}