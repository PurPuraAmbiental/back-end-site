package com.purpura.models;

import com.purpura.models.enums.CategoriaArquivo;

import java.time.LocalDate;

public class Arquivo {
    // atributos
    private final int nCdArquivo;
    public String cNmArquivo;
    public String cTipoArquivo;
    public final LocalDate dArquivo;
    public CategoriaArquivo categoriaArquivo;
    public int nCdDono;

    // construtor
    public Arquivo(int nCdArquivo, String cNmArquivo, String cTipoArquivo,
                   LocalDate dArquivo, CategoriaArquivo categoriaArquivo, int nCdEmpresa) {
        this.nCdArquivo = nCdArquivo;
        this.cNmArquivo = cNmArquivo;
        this.cTipoArquivo = cTipoArquivo;
        this.dArquivo = dArquivo;
        this.categoriaArquivo = categoriaArquivo;
        this.nCdDono = nCdEmpresa;
    }

    // getters
    public int getNCdArquivo(){return nCdArquivo;}
    public String getCNmArquivo(){return cNmArquivo;}
    public String getCTipoArquivo(){return cTipoArquivo;}
    public LocalDate getDArquivo(){return dArquivo;}
    public CategoriaArquivo getCategoriaArquivo(){return categoriaArquivo;}
    public int getNCdDono(){return nCdDono;}

    // setters
    public void setCNmArquivo(String cNmArquivo){this.cNmArquivo = cNmArquivo;}
    public void setCTipoArquivo(String cTipoArquivo){this.cTipoArquivo = cTipoArquivo;}
    public void setCategoriaArquivo(CategoriaArquivo categoriaArquivo){this.categoriaArquivo = categoriaArquivo;}
    public void setNCdDono(int nCdDono){this.nCdDono = nCdDono;}

    // toString
    @Override
    public String toString(){
        return "Código: " + nCdArquivo + "\nNome: " + cNmArquivo +
                "\nTipo: " + cTipoArquivo + "\nData: " + dArquivo +
                "\nCategoria: " + categoriaArquivo + "\nDono: " + nCdDono;
    }
}
