package com.purpura.dao;

import com.purpura.exception.ConnectionFailedException;
import com.purpura.model.Administrador;
import com.purpura.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO responsável pelas operações da entidade Administrador.
 * Implementa os métodos genéricos da classe DAO.
 * Veja classe abstrata para documentação detalhada dos métodos.
 *
 * @author Kevin de Oliveira
 */
public class AdministradorDAO extends DAO<Administrador> {

    @Override
    public String getNomeTabela(){
        return "Administrador";
    }

    @Override
    protected Administrador mapResultSet(ResultSet rs) throws SQLException{
        return new Administrador(
                rs.getString("cEmail"),
                rs.getString("cSenha"),
                rs.getString("cNmAdministrador")
        );
    }

    @Override
    protected String getNomesColunas(){
        return "cNmAdministrador, cEmail, cSenha";
    }

    @Override
    protected void prepareStatementForSave(PreparedStatement stmt, Administrador entidade) throws SQLException{
        stmt.setString(1, entidade.getCNmAdministrador());
        stmt.setString(2, entidade.getCEmail());
        stmt.setString(3, entidade.getCSenha());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement stmt, Administrador entidade) throws SQLException {
        stmt.setString(1, entidade.getCNmAdministrador());
        stmt.setString(2, entidade.getCSenha());
        stmt.setString(3, entidade.getCEmail());
        System.out.println("motivo do erro: nome: "+entidade.getCNmAdministrador()+" |senha: "+entidade.getCSenha()+" |email"+entidade.getCEmail());
    }

    @Override
    protected String getColunaId(){
        return "cEmail";
    }

    /**
     * Lista admnistradores com filtro opcional pelo nome do administrador.
     *
     * @param nomeAdministrador filtro opcional para o nome do administrador
     * @return lista de Administrador correspondentes aos filtros
     * @throws ConnectionFailedException se a conexão com o banco falhar
     */
    public List<Administrador> listarAdministradoresFiltrados(String nomeAdministrador)
            throws ConnectionFailedException {

        List<Administrador> administradores = new ArrayList<>();
        StringBuilder sql = new StringBuilder("""
        SELECT 
            cEmail,
            cNmAdministrador,
            cSenha
        FROM Administrador
        WHERE 1=1
    """);

        List<Object> params = new ArrayList<>();

        if (nomeAdministrador != null && !nomeAdministrador.isBlank()) {
            sql.append(" AND cNmAdministrador ILIKE ?");
            params.add("%" + nomeAdministrador + "%");
        }

        sql.append(" ORDER BY cNmAdministrador ASC");

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Administrador administrador = new Administrador(
                            rs.getString("cEmail"),
                            rs.getString("cSenha"),
                            rs.getString("cNmAdministrador")
                    );
                    administradores.add(administrador);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new ConnectionFailedException(e);
        }

        return administradores;
    }
}