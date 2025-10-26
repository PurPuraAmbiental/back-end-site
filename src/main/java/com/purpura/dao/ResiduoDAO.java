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

    public List<ResiduoView> listarComEmpresaFiltrado(
            Double precoMin, Double precoMax,
            Double volumeMin, Double volumeMax,
            String unidade
    ) throws ConnectionFailedException {

        String sql = """
        SELECT 
            r.ncdresiduo,
            r.cnmresiduo,
            r.ctipounidade,
            r.nprecopadrao,
            r.nvolumepadrao,
            r.ccategoria,
            r.cdescricao,
            e.nome AS cnmempresa,
            e.ccnpj
        FROM residuos r
        JOIN empresa e ON r.ccnpj = e.ccnpj
        WHERE
          (? IS NULL OR r.nprecopadrao >= ?) AND
          (? IS NULL OR r.nprecopadrao <= ?) AND
          (? IS NULL OR r.nvolumepadrao >= ?) AND
          (? IS NULL OR r.nvolumepadrao <= ?) AND
          (? IS NULL OR r.ctipounidade = ?)
    """;

        List<ResiduoView> lista = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setObject(1, precoMin);
            stmt.setObject(2, precoMin);
            stmt.setObject(3, precoMax);
            stmt.setObject(4, precoMax);
            stmt.setObject(5, volumeMin);
            stmt.setObject(6, volumeMin);
            stmt.setObject(7, volumeMax);
            stmt.setObject(8, volumeMax);
            stmt.setObject(9, unidade);
            stmt.setObject(10, unidade);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ResiduoView residuo = new ResiduoView(
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
                lista.add(residuo);
            }

        } catch (SQLException e) {
            throw new ConnectionFailedException();
        }

        return lista;
    }

}