package com.purpura.dao;
import com.purpura.model.Transportadora;
/**Classe DAO para a tabela Transporte
 * @author Leticia Reis*/

/**Classe implementando a interface generica DAO
 * e adicionando a Classe model equivalente: Transporte*/
public class TransportadoraDAO extends DAO<Transportadora>{
    /**Implementando os metodos abstratos*/

    /**Metodo para retornar o nome da tabela
     * @return uma String com o nome da tabela*/
    @Override
    public String getNomeTabela(){
        return "Transportadora";
    }
    /**Metodo para instanciar um objeto
     * @param rs -> ResultSet
     * @return objeto Transporte*/
    @Override
    protected Transportadora mapResultSet(java.sql.ResultSet rs)throws java.sql.SQLException{
        return new Transportadora(
          rs.getString("cCnpj"),
          rs.getString("cNmTransportadora"),
                rs.getString("cEmail"),
                rs.getString("cRegiaoAtendida")



        );
    }
    /**Adicionando Metodo para pegar o nome das colunas
     * @return String com os nomes dos atributos da model*/
    protected String getNomesColunas(){
        return "cCnpj, cNmTransportadora, cRegiaoAtendida, cEmail";
    }

    /**Adicionando metodo para Inserir conteudo no banco de dados
     * @param stmt -> String com o comando sql
     * @param entidade -> nome da tabela*/
    @Override
    protected void prepareStatementForSave(java.sql.PreparedStatement stmt, Transportadora entidade) throws java.sql.SQLException {
        stmt.setString(1, entidade.getCCnpj());
        stmt.setString(2, entidade.getCNmTransporte());
        stmt.setString(3, entidade.getCRegiaoAtendida());
        stmt.setString(4, entidade.getCEmail());
    }

    /**Adicionando metodo para Atualizar conteudo no banco de dados
     * @param stmt -> String com o comando sql
     * @param entidade -> nome da tabela*/
    @Override
    protected void prepareStatementForUpdate(java.sql.PreparedStatement stmt, Transportadora entidade) throws java.sql.SQLException {
        stmt.setString(1, entidade.getCNmTransporte());
        stmt.setString(2, entidade.getCRegiaoAtendida());
        stmt.setString(3, entidade.getCEmail());
        stmt.setString(4, entidade.getCCnpj());
    }
    /**Adcionando Metodo para buscar a primary key da coluna
     * @return chave primaria da coluna*/
    @Override
    protected String getColunaId(){
        return "cCnpj";
    }
}
