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

/**
 * DAO responsável pelas operações da entidade Telefone.
 * Implementa os métodos genéricos da classe DAO.
 * Veja classe abstrata para documentação detalhada dos métodos.
 *
 * @author Bruna de Jesus
 */
public class TelefoneDAO extends DAO<Telefone> {

    @Override
    public String getNomeTabela() {
        return "telefone";
    }

    @Override
    protected Telefone mapResultSet(ResultSet rs) throws SQLException {
        return new Telefone(
                rs.getString("nCdTelefone"),
                rs.getInt("ccnpj"),
                rs.getString("cNrTelefone"),
                rs.getString("cDescricao")
        );
    }

    @Override
    protected String getNomesColunas() {
        return "cNrTelefone, cCnpj, cDescricao";
    }

    @Override
    protected void prepareStatementForSave(PreparedStatement stmt, Telefone entidade) throws SQLException {
        stmt.setString(1, entidade.getCNrTelefone());
        stmt.setString(2, entidade.getCCnpj());
        stmt.setString(3, entidade.getCDescricao());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement stmt, Telefone entidade) throws SQLException {
        prepareStatementForSave(stmt, entidade);
        stmt.setInt(4, entidade.getNCdTelefone());
    }

    @Override
    protected String getColunaId() {
        return "nCdTelefone";
    }

    /**
     * Lista todos os telefones com informações da empresa associada.
     *
     * @return lista de TelefoneView, unindo dados de telefone e empresa
     * @throws ConnectionFailedException se houver falha na conexão ou execução da query
     */
    public List<TelefoneView> listarComEmpresa() throws ConnectionFailedException {
        List<TelefoneView> listaView = new ArrayList<>();

         String sql_join = "SELECT " +
                "t.nCdTelefone, t.cNrTelefone, t.cDescricao," +
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
                        rs.getString("cNmEmpresa"),
                        rs.getString("cDescricao")
                );
                listaView.add(view);
            }
        } catch (SQLException e) {
            throw new ConnectionFailedException();
        }
        return listaView;
    }
}