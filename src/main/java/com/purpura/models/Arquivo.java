package com.purpura.models;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Map;

public class Arquivo implements Model{
    // atributos
    private int nCdArquivo;
    public String cNmArquivo;
    public String cTipoArquivo;
    public final LocalDate dUpload;
    public final String cCnpj;
    public final int nCdResiduo;


    // construtores
    public Arquivo(int nCdArquivo, String cNmArquivo, String cTipoArquivo,
                   LocalDate dUpload, String cCnpj, int nCdResiduo) {
        this.nCdArquivo = nCdArquivo;
        this.cNmArquivo = cNmArquivo;
        this.cTipoArquivo = cTipoArquivo;
        this.dUpload = dUpload;
        this.cCnpj = cCnpj;
        this.nCdResiduo = nCdResiduo;
    }

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

    // getters
    public int getNCdArquivo(){return nCdArquivo;}
    public String getCNmArquivo(){return cNmArquivo;}
    public String getCTipoArquivo(){return cTipoArquivo;}
    public LocalDate getDUpload(){return dUpload;}
    public String getCCnpj(){return cCnpj;}
    public int  getNCdResiduo(){return nCdResiduo;}

    // setters
    public void setCNmArquivo(String cNmArquivo){this.cNmArquivo = cNmArquivo;}
    public void setCTipoArquivo(String cTipoArquivo){this.cTipoArquivo = cTipoArquivo;}

    // toString
    @Override
    public String toString(){
        return "Código: " + nCdArquivo + "\nNome: " + cNmArquivo +
                "\nTipo: " + cTipoArquivo + "\nData: " + dUpload +
                "\nCNPJ: " + cCnpj + "\nResíduo: " + nCdResiduo;
    }

    // métodos
    @Override
    public Object getId(){return nCdArquivo;}
}
