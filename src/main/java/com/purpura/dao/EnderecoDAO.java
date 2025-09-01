public class EnderecoDAO extends GenericDAOImpl<Endereco> {
    //RETONAR O NOME DA ENTIDADE:
    public  String getNomeTabela(){
        return "Endereco";
    }

    //TRANSFORMAR UMA LINHA DE INFORMAÇÕES DO BANCO EM UMA INSTANCIA DE CLASSE:
    protected Endereco mapResultSet(ResultSet rs) throws java.sql.SQLException{
        return new Endereco(
                rs.getString("ncdEnderecoEmpresa"),
                rs.getString("cBairro"),
                rs.getString("cLogradouro"),
                rs.getString("cEstado"),
                rs.getString("cCidade"),
                rs.getString("cCep"),
                rs.getString("cComplemento"),
                rs.getString("iNrEnderecoEmpresa"),
                rs.getString("cCnpj")
        );
    }

    //RETORNAR OS NOMES DAS COLUNAS
    protected String getNomeColunas(){
        return "nCdEnderecoEmpresa, cBairro, cLogradouro, cEstado, cCidade, cCep, cComplemento, iNrEnderecoEmpresa, cCnpj"
    }

    //METODO PARA -----
    protected void prepareStatementForSave(java.sql.PreparedStatement stmt, Empresa entidade) throws java.sql.SQLException{
        stmt.setString(1, entidade.getCnmEmpresa);
        stmt.setString(2, entidade.getCbairro);
        stmt.setString(3, entidade.cLogradouro);
        stmt.setString(4, entidade.cEstado);
        stmt.setString(5, entidade.cCidade);
        stmt.setString(6, entidade.cComplemento);
        stmt.setString(7, entidade.cCep);
        stmt.setString(8, entidade.iNrEnderecoEmpresa);
        stmt.setString(9, entidade.cCnpj);
    }

    protected void prepareStatementForUpdate(java.sql.PreparedStatement stmt, Empresa entidade) throws java.sql.SQLException {
        stmt.setString(1, entidade.getCnmEmpresa);
        stmt.setString(2, entidade.getCbairro);
        stmt.setString(3, entidade.cLogradouro);
        stmt.setString(4, entidade.cEstado);
        stmt.setString(5, entidade.cCidade);
        stmt.setString(6, entidade.cComplemento);
        stmt.setString(7, entidade.cCep);
        stmt.setString(8, entidade.iNrEnderecoEmpresa);
        stmt.setString(9, entidade.cCnpj);
    }

    //RETORNA ATRIBUTO IDENTIFICADOR
    protected String getColunaId(){
        return "nCdEnderecoEmpresa"
    }


    }