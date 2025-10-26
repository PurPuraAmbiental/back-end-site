package com.purpura.dao;

import com.purpura.common.Constants;
import com.purpura.exception.ConnectionFailedException;
import com.purpura.exception.NotFoundException;
import com.purpura.model.Model;
import com.purpura.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Classe abstrata DAO genérica responsável pelas operações básicas envolvendo banco de dados
 * de entidades que implementam a interface Model.
 *
 * @param <T> Tipo da entidade que estende Model
 *
 * @author Kevin de Oliveira
 * @author Bruna de Jesus
 */
public abstract class DAO<T extends Model> {

    /**
     * Salva a entidade no banco de dados.
     *
     * @param entidade Objeto da entidade a ser salvo
     * @throws NotFoundException Se nenhuma linha foi inserida
     * @throws ConnectionFailedException Se houver falha na conexão ou execução SQL
     */
    public void save(T entidade) throws NotFoundException, ConnectionFailedException {
        String sql = "INSERT INTO " + getNomeTabela() +
                " (" + getNomesColunas() + ") VALUES (" + getPlaceholders() + ")";
        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            System.out.println(sql);
            prepareStatementForSave(stmt, entidade);
            int rows = stmt.executeUpdate();
            if(rows == 0) {
                throw new NotFoundException(getNomeTabela(), entidade.getId());
            }

        } catch (SQLException e)  {
            System.out.println("achou");
            e.printStackTrace();
            throw new ConnectionFailedException();
        }
    }

    /**
     * Atualiza a entidade no banco de dados.
     *
     * @param entidade Objeto da entidade a ser atualizado
     * @throws NotFoundException Se nenhuma linha foi atualizada
     * @throws ConnectionFailedException Se houver falha na conexão ou execução SQL
     */
    public void update(T entidade) throws NotFoundException, ConnectionFailedException{
        String sql = "UPDATE " + getNomeTabela() +
                " SET " + getColunasUpdate() +
                " WHERE " + getColunaId() + " = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            prepareStatementForUpdate(stmt, entidade);
            int rows = stmt.executeUpdate();
            if(rows == 0) {
                throw new NotFoundException(getNomeTabela(), entidade.getId());
            }
        } catch (SQLException e) {
            throw new ConnectionFailedException();
        }
    }

    /**
     * Deleta um item do banco de dados a partir de id numérico
     *
     * @param id Identificador numérico do item a ser apagado
     * @throws NotFoundException Se nenhuma linha foi deletada
     * @throws ConnectionFailedException Se houver falha na conexão ou execução SQL
     */
    public void delete(int id) throws NotFoundException, ConnectionFailedException{
        String sql = "DELETE FROM " + getNomeTabela() +
                " WHERE " + getColunaId() + " = ?";

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, id);
            int linhasDeletadas = stmt.executeUpdate();
            if (linhasDeletadas == 0) {
                throw new NotFoundException(getNomeTabela(), id);
            }
        } catch (SQLException e){
            throw new ConnectionFailedException();
        }
    }

    /**
     * Deleta um item do banco de dados a partir de id textual
     *
     * @param id Identificador textual do item a ser apagado
     * @throws NotFoundException Se nenhuma linha foi deletada
     * @throws ConnectionFailedException Se houver falha na conexão ou execução SQL
     */
    public void delete(String id) throws NotFoundException, ConnectionFailedException{
        String sql = "DELETE FROM " + getNomeTabela() +
                " WHERE " + getColunaId() + " = ?";

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, id);
            int linhasDeletadas = stmt.executeUpdate();
            if (linhasDeletadas == 0) {
                throw new NotFoundException(getNomeTabela(), id);
            }
        } catch (SQLException e){
            throw new ConnectionFailedException();
        }
    }

    /**
     * Deleta registros com base em um atributo específico.
     *
     * @param atributo Nome da coluna usada como referência
     * @param valor Valor da coluna de referência
     * @throws ConnectionFailedException Se houver falha na conexão ou execução SQL
     */
    public void deleteByAttribute(String atributo, Object valor) throws NotFoundException, ConnectionFailedException{
        String sql = "DELETE FROM " + getNomeTabela() +
                " WHERE " + atributo + " = ?";

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setObject(1, valor);
            int linhasDeletadas = stmt.executeUpdate();
        } catch (SQLException e){
            throw new ConnectionFailedException();
        }
    }

    /**
     * Busca uma entidade pelo id numérico.
     *
     * @param id Chave primária da entidade
     * @return Entidade encontrada
     * @throws NotFoundException Se nenhuma entidade for encontrada
     * @throws ConnectionFailedException Se houver falha na conexão ou execução SQL
     */
    public T findById(int id) throws NotFoundException, ConnectionFailedException {
        String sql = "SELECT * FROM " + getNomeTabela() +
                " WHERE " + getColunaId() + " = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSet(rs);
                } else {
                    throw new NotFoundException(getNomeTabela(), id);
                }
            }
        } catch (SQLException e) {
            throw new ConnectionFailedException();
        }
    }

    /**
     * Busca uma entidade pelo id textual.
     *
     * @param id Chave primária da entidade
     * @return Entidade encontrada
     * @throws NotFoundException Se nenhuma entidade for encontrada
     * @throws ConnectionFailedException Se houver falha na conexão ou execução SQL
     */
    public T findById(String id) throws NotFoundException, ConnectionFailedException {
        String sql = "SELECT * FROM " + getNomeTabela() +
                " WHERE " + getColunaId() + " = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSet(rs);
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new ConnectionFailedException();
        }
    }

    /**
     * Busca a primeira entidade que corresponda a um atributo específico.
     *
     * @param coluna Nome da coluna usada como filtro
     * @param valor Valor da coluna
     * @return Entidade encontrada
     * @throws NotFoundException Se nenhuma entidade for encontrada
     * @throws ConnectionFailedException Se houver falha na conexão ou execução SQL
     */
    public T findByAttribute(String coluna, Object valor) throws NotFoundException, ConnectionFailedException {
        String sql = "SELECT * FROM " + getNomeTabela() +
                " WHERE " + coluna + " = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setObject(1, valor);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSet(rs);
                } else {
                   return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("eita");
            throw new ConnectionFailedException();
        }
    }

    /**
     * Busca todas as entidades que correspondam a um atributo específico.
     *
     * @param coluna Nome da coluna usada como filtro
     * @param valor Valor da coluna
     * @return Lista de entidades encontradas
     * @throws ConnectionFailedException Se houver falha na conexão ou execução SQL
     */
    public List<T> findAllByAttribute(String coluna, Object valor)
            throws ConnectionFailedException {
        String sql = "SELECT * FROM " + getNomeTabela() + " WHERE " + coluna + " = ?";
        List<T> resultados = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setObject(1, valor);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    resultados.add(mapResultSet(rs));
                }
            }
            return resultados;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new ConnectionFailedException();
        }
    }

    /**
     * Busca todas as entidades da tabela.
     *
     * @return Lista de entidades
     * @throws NotFoundException Se não houver registros
     * @throws ConnectionFailedException Se houver falha na conexão ou execução SQL
     */
    public List<T> findAll() throws NotFoundException, ConnectionFailedException{
        List<T> lista = new ArrayList<>();
        String sql = "SELECT * FROM " + getNomeTabela();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()){
                lista.add(mapResultSet(rs));
            }
        } catch (SQLException e){
            e.printStackTrace();
            throw new ConnectionFailedException();
        }

        if (lista.isEmpty()) {
            throw new NotFoundException(getNomeTabela(), -1);
        }

        return lista;
    }

    // ----- MÉTODOS UTILITÁRIOS -----

    /**
     * Gera um String composta de um "?" para cada coluna da tabela
     *
     * @return String contendo um "?" para cada coluna da tabela.
     */
    protected String getPlaceholders() {
        int quantidade = getNomesColunas().split(Constants.COMMA_SEPARATOR_REGEX).length;
        return String.join(", ", Collections.nCopies(quantidade, "?"));
    }

    /**
     * Gera a string usada na cláusula SET do UPDATE, ignorando a coluna de ID.
     *
     * @return String no formato "coluna1 = ?, coluna2 = ?, ...", pronta para o PreparedStatement.
     */
    protected String getColunasUpdate() {
        String[] colunas = getNomesColunas().split(Constants.COMMA_SEPARATOR_REGEX);
        String colunaId = getColunaId();

        List<String> updates = new ArrayList<>();
        for (String coluna : colunas) {
            if (!coluna.equalsIgnoreCase(colunaId)) {
                updates.add(coluna + " = ?");
            }
        }
        return String.join(", ", updates);
    }

    /**
     * Retorna o nome da tabela associada ao DAO.
     *
     * @return Nome da tabela no banco de dados.
     */
    public abstract String getNomeTabela();

    /**
     * Mapeia um ResultSet para um objeto da entidade correspondente.
     *
     * @param rs ResultSet retornado pela query SQL
     * @return Objeto da entidade T preenchido com os dados do ResultSet
     * @throws SQLException Se ocorrer algum erro ao acessar os dados do ResultSet
     */
    protected abstract T mapResultSet(ResultSet rs) throws SQLException;

    /**
     * Retorna os nomes das colunas da tabela, separados por vírgula.
     *
     * @return String contendo os nomes das colunas
     */
    protected abstract String getNomesColunas();

    /**
     * Prepara um PreparedStatement para salvar a entidade no banco.
     *
     * @param stmt PreparedStatement a ser preparado
     * @param entidade Entidade que será salva
     * @throws SQLException Se ocorrer algum erro ao setar os parâmetros do PreparedStatement
     */
    protected abstract void prepareStatementForSave(PreparedStatement stmt, T entidade) throws SQLException; // prepareStatement pra método save

    /**
     * Prepara um PreparedStatement para atualizar a entidade no banco.
     *
     * @param stmt PreparedStatement a ser preparado
     * @param entidade Entidade que será atualizada
     * @throws SQLException Se ocorrer algum erro ao setar os parâmetros do PreparedStatement
     */
    protected abstract void prepareStatementForUpdate(PreparedStatement stmt, T entidade) throws SQLException; // prepareStatement pra método update

    /**
     * Retorna o nome da coluna que funciona como ID (primary key) da tabela.
     *
     * @return Nome da coluna ID
     */
    protected abstract String getColunaId(); // retorna nome da coluna com id (primary key)
}