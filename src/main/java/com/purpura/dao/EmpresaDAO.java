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

    @Override
    protected String getColunaId() {
        return "cCnpj";
    }

    /**
     * Lista empresas com filtro opcional pelo nome do administrador.
     *
     * @param nome filtro opcional para o nome da empresa
     * @param email filtro opcional para o email da empresa
     * @param cnpj filtro opcional para o cnpj da empresa
     * @param ativo filtro opcional pela atividade da empresa
     * @param temResiduo filtro opcional por posse ou falta de resíduo
     * @return lista de Empresa correspondentes aos filtros
     * @throws ConnectionFailedException se a conexão com o banco falhar
     */
    public List<Empresa> listarEmpresasFiltradas(
            String nome,
            String email,
            String cnpj,
            Character ativo,
            Boolean temResiduo
    ) throws ConnectionFailedException {

        List<Empresa> lista = new ArrayList<>();

        StringBuilder sql = new StringBuilder(
                "SELET e.cNmEmpresa, e.cSenha, e.cCnpj, e.cAtivo, e.cEmail, " +
                        "COUNT(r.nCdResiduo) AS totalResiduos " +
                        "FROM empresa e " +
                        "LEFT JOIN residuo r ON e.cCnpj = r.cCnpj " +
                        "WHERE 1=1"
        );

        List<Object> params = new ArrayList<>();

        if (nome != null && !nome.isBlank()) {
            sql.append(" AND e.cNmEmpresa ILIKE ?");
            params.add("%" + nome + "%");
        }
        if (email != null && !email.isBlank()) {
            sql.append(" AND e.cEmail ILIKE ?");
            params.add("%" + email + "%");
        }
        if (cnpj != null && !cnpj.isBlank()) {
            sql.append(" AND e.cCnpj = ?");
            params.add(cnpj);
        }
        if (ativo != null) {
            sql.append(" AND e.cAtivo = ?");
            params.add(ativo);
        }

        // Agrupa todas as colunas da empresa
        sql.append(" GROUP BY e.cNmEmpresa, e.cSenha, e.cCnpj, e.cAtivo, e.cEmail");

        // Filtro de resíduos, COUNT de apenas os resíduos não nulos
        if (Boolean.TRUE.equals(temResiduo)) {
            sql.append(" HAVING COUNT(r.nCdResiduo) > 0");
        } else if (Boolean.FALSE.equals(temResiduo)) {
            sql.append(" HAVING COUNT(r.nCdResiduo) = 0");
        }

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Empresa empresa = new Empresa(
                            rs.getString("cNmEmpresa"),
                            rs.getString("cSenha"),
                            rs.getString("cCnpj"),
                            rs.getString("cAtivo").charAt(0),
                            rs.getString("cEmail")
                    );
                    lista.add(empresa);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new ConnectionFailedException(e);
        }

        return lista;
    }
}