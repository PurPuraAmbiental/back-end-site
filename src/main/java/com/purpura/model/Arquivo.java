package com.purpura.model;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Map;
/** Classe Modelo para o tabela Arquivo
 *@author Kevin Oliveira
 * */
public class Arquivo implements Model{
    /**Adicionando os atributos da tabela como atributos da classe
     * atributos dados com prefixos da tabela de banco de dado*/
    private int nCdArquivo;
    public String cNmArquivo;
    public String cTipoArquivo;
    public final LocalDate dUpload;
    public final String cCnpj;
    public final int nCdResiduo;


    /**Adicionando um metodo Construtor
     * @param nCdArquivo - atributo identificador dessa instancia de Arquivo
     * @param cNmArquivo - nome do arquivo
     * @param cTipoArquivo - Tipo do conteudo do arquivo
     * @param dUpload - Data do upload do arquivo
     * @param cCnpj - Foring key da tabela Empresa
     * @param nCdResiduo - Foring key da tabela Residuo
     * */
    public Arquivo(int nCdArquivo, String cNmArquivo, String cTipoArquivo,
                   LocalDate dUpload, String cCnpj, int nCdResiduo) {
        this.nCdArquivo = nCdArquivo;
        this.cNmArquivo = cNmArquivo;
        this.cTipoArquivo = cTipoArquivo;
        this.dUpload = dUpload;
        this.cCnpj = cCnpj;
        this.nCdResiduo = nCdResiduo;
    }

    /**Construtor que iniciariza objetos
     *@param params -> um map*/
    public Arquivo(Map<String, String> params) throws ParseException{
        if(params.containsKey("nCdArquivo")){
            this.nCdArquivo = Integer.parseInt(params.get("nCdArquivo"));
        }
        this.cNmArquivo = params.get("cNmArquivo");
        this.cTipoArquivo = params.get("cTipoArquivo");
        this.dUpload = LocalDate.parse(params.get("dUpload"));
        this.cCnpj = params.get("cCnpj");
        this.nCdResiduo = Integer.parseInt(params.get("nCdResiduo"));
    }

    /**Metodo GetNCdArquivo
     * @return Primary key */
    public int getNCdArquivo(){return nCdArquivo;}
    /**Metodo GetCNmArquivo
     * @return nome do arquivo*/
    public String getCNmArquivo(){return cNmArquivo;}
    /**Metodo GetCTipoArquivo
     * @return tipo de arquivo*/
    public String getCTipoArquivo(){return cTipoArquivo;}
    /**Metodo GetDUpload
     * @return - Data do upload*/
    public LocalDate getDUpload(){return dUpload;}
    /**Metodo getCCnpj
     * @return cnpj da Empresa*/
    public String getCCnpj(){return cCnpj;}
    /**Metodo getNCdResiduo
     *@return codigo da tabela residuo*/
    public int  getNCdResiduo(){return nCdResiduo;}

    /**Metodo setCNmArquivo
     * @param cNmArquivo*/
    public void setCNmArquivo(String cNmArquivo){this.cNmArquivo = cNmArquivo;}
    /**Metodo setCTipoArquivo
     * @param cTipoArquivo*/
    public void setCTipoArquivo(String cTipoArquivo){this.cTipoArquivo = cTipoArquivo;}

    /**Metodo toString
     *@return informação dos campos das tabelas*/
    @Override
    public String toString(){
        return "Código: " + nCdArquivo + "\nNome: " + cNmArquivo +
                "\nTipo: " + cTipoArquivo + "\nData: " + dUpload +
                "\nCNPJ: " + cCnpj + "\nResíduo: " + nCdResiduo;
    }

    /**Metodo para retornar a tabela
     * @return nome da tabela*/
    @Override
    public Object getId(){return nCdArquivo;}
}
