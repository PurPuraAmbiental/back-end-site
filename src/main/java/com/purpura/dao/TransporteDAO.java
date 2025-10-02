package com.purpura.dao;
import com.purpura.model.Transporte;
/**Classe DAO para a tabela Transporte
 * @author Leticia Reis*/

/**Classe implementando a interface generica DAO
 * e adicionando a Classe model equivalente: Transporte*/
public class TransporteDAO extends DAO<Transporte>{
    /**Implementando os metodos abstratos*/

    /**Metodo para retornar o nome da tabela
     * @return uma String com o nome da tabela*/
    @Override
    public String getNomeTabela(){
        return "Transporte";
    }
    /**Metodo para instanciar um objeto
     * @param rs -> ResultSet
     * @return objeto Transporte*/
    @Override
    protected Transporte mapResultSet(java.sql.ResultSet rs)throws java.sql.SQLException{
        return new Transporte(
          rs.getInt("nCdTransporte"),
          rs.getString("cNmTransporte"),
                rs.getDate("dRetirada").toLocalDate(),
                rs.getInt("nCdPedido")
        );
    }
    /**Adicionando Metodo para pegar o nome das colunas
     * @return String com os nomes dos atributos da model*/
    protected String getNomesColunas(){
        return "cNmTransporte, dRetirada, nCdPedido";
    }

    /**Adicionando metodo para Inserir conteudo no banco de dados
     * @param stmt -> String com o comando sql
     * @param entidade -> nome da tabela*/
    @Override
    protected void prepareStatementForSave(java.sql.PreparedStatement stmt, Transporte entidade) throws java.sql.SQLException {
        stmt.setString(1, entidade.getCNmTransporte());
        stmt.setDate(2, java.sql.Date.valueOf(entidade.getDRetirada()));
        stmt.setInt(3, entidade.getNCdPedido());
    }
    /**Adicionando metodo para Atualizar conteudo no banco de dados
     * @param stmt -> String com o comando sql
     * @param entidade -> nome da tabela*/
    @Override
    protected void prepareStatementForUpdate(java.sql.PreparedStatement stmt, Transporte entidade) throws java.sql.SQLException {
        prepareStatementForSave(stmt, entidade);
        stmt.setInt(4, entidade.getNCdTransporte());
    }
    /**Adcionando Metodo para buscar a primary key da coluna
     * @return chave primaria da coluna*/
    @Override
    protected String getColunaId(){
        return "nCdTransporte";
    }
}
