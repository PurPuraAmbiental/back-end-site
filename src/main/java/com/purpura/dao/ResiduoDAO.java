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

/**
 * DAO responsável pelas operações da entidade Residuo.
 * Implementa os métodos genéricos da classe DAO.
 * Veja classe abstrata para documentação detalhada dos métodos.
 *
 * @author Kevin de Oliveira
 */
public class ResiduoDAO extends DAO<Residuo> {

    @Override
    public String getNomeTabela() {
        return "residuo";
    }

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

    @Override
    protected String getNomesColunas() {
        return "cNmResiduo, cTipoUnidade, " +
                "nPrecoPadrao, nVolumePadrao, cCategoria, " +
                "cDescricao, cCnpj";
    }

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

    @Override
    protected void prepareStatementForUpdate(PreparedStatement stmt, Residuo entidade) throws SQLException {
        prepareStatementForSave(stmt, entidade);
        stmt.setInt(8, entidade.getNCdResiduo());
    }

    @Override
    protected String getColunaId() {
        return "nCdResiduo";
    }

    /**
     * Lista todos os resíduos com informações da empresa associada.
     *
     * @return lista de ResiduoView, unindo dados de resíduo e empresa
     * @throws ConnectionFailedException se houver falha na conexão ou execução da query
     */
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

    /**
     * Lista empresas com filtro opcional pelo nome do administrador.
     *
     * @param precoMin preço mínimo usado no filtro
     * @param precoMax preço máximo usado no filtro
     * @param volumeMin volume mínimo usado no filtro
     * @param volumeMax volume máximo usado no filtro
     * @param unidade filtro opcional por tipo de unidade de medida
     * @return lista de Residuo correspondentes aos filtros
     * @throws ConnectionFailedException se a conexão com o banco falhar
     */
    public List<ResiduoView> listarComEmpresaFiltrado(
            Double precoMin,
            Double precoMax,
            Double volumeMin,
            Double volumeMax,
            String unidade
    ) throws ConnectionFailedException {

        List<ResiduoView> lista = new ArrayList<>();

        StringBuilder sql = new StringBuilder(
                "SELECT r.nCdResiduo, r.cNmResiduo, r.cTipoUnidade, r.nPrecoPadrao, " +
                        "r.nVolumePadrao, r.cCategoria, r.cDescricao, e.cNmEmpresa, e.cCnpj " +
                        "FROM residuo r " +
                        "JOIN empresa e ON r.cCnpj = e.cCnpj " +
                        "WHERE 1=1"
        );

        List<Object> params = new ArrayList<>();

        // Monta a query conforme os filtros
        if (precoMin != null) {
            sql.append(" AND r.nPrecoPadrao >= ?");
            params.add(precoMin);
        }
        if (precoMax != null) {
            sql.append(" AND r.nPrecoPadrao <= ?");
            params.add(precoMax);
        }
        if (volumeMin != null) {
            sql.append(" AND r.nVolumePadrao >= ?");
            params.add(volumeMin);
        }
        if (volumeMax != null) {
            sql.append(" AND r.nVolumePadrao <= ?");
            params.add(volumeMax);
        }
        if (unidade != null && !unidade.isBlank()) {
            sql.append(" AND r.cTipoUnidade = ?");
            params.add(unidade);
        }

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            // Atribui os parâmetros dinamicamente
            for (int i = 0; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = stmt.executeQuery()) {
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
                    lista.add(view);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new ConnectionFailedException();
        }

        return lista;
    }
}