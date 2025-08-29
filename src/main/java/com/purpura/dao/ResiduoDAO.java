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
                "cDescricao, nCdEmpresa)" + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, residuo.getNCdResiduo());
            stmt.setString(2, residuo.getCNmResiduo());
            stmt.setString(3, residuo.getCTipoUnidade());
            stmt.setDouble(4, residuo.getNPrecoPadrao());
            stmt.setDouble(5, residuo.getNVolumePadrao());
            stmt.setString(6, residuo.getCCategoria());
            stmt.setString(7, residuo.getCDescricao());
            stmt.setInt(8, residuo.getNCdEmpresa());

            int linhasInseridas = stmt.executeUpdate();
            return linhasInseridas > 0;

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
