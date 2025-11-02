package com.purpura.dao;

import com.purpura.exception.ConnectionFailedException;
import com.purpura.model.Transportadora;
import com.purpura.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO responsável pelas operações da entidade Transportadora.
 * Implementa os métodos genéricos da classe DAO.
 * Veja classe abstrata para documentação detalhada dos métodos.
 *
 * @author Letícia Reis
 */
public class TransportadoraDAO extends DAO<Transportadora>{

    @Override
    public String getNomeTabela(){
        return "Transportadora";
    }

    @Override
    protected Transportadora mapResultSet(java.sql.ResultSet rs)throws java.sql.SQLException{
        return new Transportadora(
                rs.getString("cCnpj"),
                rs.getString("cNmTransportadora"),
                rs.getString("cEmail"),
                rs.getString("cRegiaoAtendida")
        );
    }

    @Override
    protected String getNomesColunas(){
        return "cCnpj, cNmTransportadora, cRegiaoAtendida, cEmail";
    }

    @Override
    protected void prepareStatementForSave(java.sql.PreparedStatement stmt, Transportadora entidade) throws java.sql.SQLException {
        stmt.setString(1, entidade.getCCnpj());
        stmt.setString(2, entidade.getCNmTransportadora());
        stmt.setString(3, entidade.getCRegiaoAtendida());
        stmt.setString(4, entidade.getCEmail());
    }

    @Override
    protected void prepareStatementForUpdate(java.sql.PreparedStatement stmt, Transportadora entidade) throws java.sql.SQLException {
        stmt.setString(1, entidade.getCNmTransportadora());
        stmt.setString(2, entidade.getCRegiaoAtendida());
        stmt.setString(3, entidade.getCEmail());
        stmt.setString(4, entidade.getCCnpj());
    }

    @Override
    protected String getColunaId(){
        return "cCnpj";
    }

    /**
     * Lista transportadoras com filtro opcional pelo nome da transportadora.
     *
     * @param nomeTransportadora filtro opcional para o nome da transportadora
     * @return lista de Transportadora correspondentes aos filtros
     * @throws ConnectionFailedException se a conexão com o banco falhar
     */
    public List<Transportadora> listarTransportadorasFiltradas(String nomeTransportadora, String regiao)
            throws ConnectionFailedException {

        List<Transportadora> transportadoras = new ArrayList<>();
        StringBuilder sql = new StringBuilder("""
        SELECT 
            cCnpj,
            cNmTransportadora,
            cEmail,
            cRegiaoAtendida
        FROM Transportadora
        WHERE 1=1
    """);

        List<Object> params = new ArrayList<>();

        if (nomeTransportadora != null && !nomeTransportadora.isBlank()) {
            sql.append(" AND cNmTransportadora ILIKE ?");
            params.add("%" + nomeTransportadora + "%");
        }
        if (regiao != null && !regiao.isBlank()) {
            sql.append(" AND cRegiaoAtendida ILIKE ?");
            params.add("%" + regiao + "%");
        }

        sql.append(" ORDER BY cNmTransportadora ASC");

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Transportadora transportadora = new Transportadora(
                            rs.getString("cCnpj"),
                            rs.getString("cNmTransportadora"),
                            rs.getString("cEmail"),
                            rs.getString("cRegiaoAtendida")
                    );
                    transportadoras.add(transportadora);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new ConnectionFailedException(e);
        }

        return transportadoras;
    }
}