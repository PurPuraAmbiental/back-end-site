package com.purpura.dao;

import com.purpura.models.Residuo;
import com.purpura.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ResiduoDAO extends GenericDAOImpl<Residuo> implements GenericDAO<Residuo>{
    @Override
    public boolean save(Residuo residuo) {
        String sql = "INSERT INTO Residuo(nCdResiduo, cNmResiduo, cTipoUnidade, " +
                "nPrecoPadrao, nVolumePadrao, cCategoria, " +
                "cDescricao, cCnpj)" + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, residuo.getNCdResiduo());
            stmt.setString(2, residuo.getCNmResiduo());
            stmt.setString(3, residuo.getCTipoUnidade());
            stmt.setDouble(4, residuo.getNPrecoPadrao());
            stmt.setDouble(5, residuo.getNVolumePadrao());
            stmt.setString(6, residuo.getCCategoria());
            stmt.setString(7, residuo.getCDescricao());
            stmt.setString(8, residuo.getCCnpj());

            int linhasInseridas = stmt.executeUpdate();
            return linhasInseridas > 0;

        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Residuo residuo){
        String sql = "UPDATE Residuo SET cNmResiduo = ?, cTipoUnidade = ?, " +
                "nPrecoPadrao = ?, nVolumePadrao = ?, cCategoria = ?, " +
                "cDescricao = ?, cCnpj = ? " +
                "WHERE nCdResiduo = ?";

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, residuo.getCNmResiduo());
            stmt.setString(2, residuo.getCTipoUnidade());
            stmt.setDouble(3, residuo.getNPrecoPadrao());
            stmt.setDouble(4, residuo.getNVolumePadrao());
            stmt.setString(5, residuo.getCCategoria());
            stmt.setString(6, residuo.getCDescricao());
            stmt.setString(7, residuo.getCCnpj());
            stmt.setInt(8, residuo.getNCdResiduo());

            int linhasModificadas = stmt.executeUpdate();
            return linhasModificadas > 0;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
