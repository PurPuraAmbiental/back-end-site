package com.purpura.dao;

import com.purpura.dto.EnderecoEmpresaView;
import com.purpura.exception.ConnectionFailedException;
import com.purpura.model.EnderecoEmpresa;
import com.purpura.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO responsável pelas operações da entidade EnderecoEmpresa.
 * Implementa os métodos genéricos da classe DAO.
 * Veja classe abstrata para documentação detalhada dos métodos.
 *
 * @author Bruna de Jesus
 */
public class EnderecoEmpresaDAO extends DAO<EnderecoEmpresa> {

    @Override
    public String getNomeTabela() {
        return "EnderecoEmpresa";
    }

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
                rs.getInt("iNrEnderecoEmpresa"),
                rs.getString("cCnpj")
        );
    }

    @Override
    protected String getNomesColunas() {
        return "cBairro, cLogradouro, cEstado, cCidade, cComplemento, cCep, iNrEnderecoEmpresa, cCnpj";
    }

    @Override
    protected void prepareStatementForSave(PreparedStatement stmt, EnderecoEmpresa entidade) throws SQLException {
        stmt.setString(1, entidade.getCBairro());
        stmt.setString(2, entidade.getCLogradouro());
        stmt.setString(3, entidade.getCEstado());
        stmt.setString(4, entidade.getCCidade());
        stmt.setString(5, entidade.getCComplemento());
        stmt.setString(6, entidade.getCCep());
        stmt.setInt(7, entidade.getINrEnderecoEmpresa());
        stmt.setString(8, entidade.getCCnpj());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement stmt, EnderecoEmpresa entidade) throws SQLException {
        prepareStatementForSave(stmt, entidade);
        stmt.setInt(9, entidade.getNCdEnderecoEmpresa());
    }

    @Override
    protected String getColunaId() {
        return "nCdEnderecoEmpresa";
    }

    /**
     * Lista todos os endereços com informações da empresa associada.
     *
     * @return lista de EnderecoEmpresaView, unindo dados de endereço e empresa
     * @throws ConnectionFailedException se houver falha na conexão ou execução da query
     */
    public List<EnderecoEmpresaView> listarComEmpresa() throws ConnectionFailedException {
        List<EnderecoEmpresaView> listaView = new ArrayList<>();

        String sql_join = "SELECT " +
                "a.nCdEnderecoEmpresa, a.cBairro, a.cLogradouro, a.cEstado, a.cCidade, " +
                "a.cCep, a.cComplemento, a.iNrEnderecoEmpresa, " +
                "e.cNmEmpresa, a.cCnpj " +
                "FROM EnderecoEmpresa a " +
                "INNER JOIN Empresa e ON a.cCnpj = e.cCnpj";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql_join);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                EnderecoEmpresaView view = new EnderecoEmpresaView(
                        rs.getInt("nCdEnderecoEmpresa"),
                        rs.getString("cBairro"),
                        rs.getString("cLogradouro"),
                        rs.getString("cEstado"),
                        rs.getString("cCidade"),
                        rs.getString("cCep"),
                        rs.getString("cComplemento"),
                        rs.getInt("iNrEnderecoEmpresa"),
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