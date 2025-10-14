package com.purpura.dao;

import com.purpura.exception.DAONotFoundException;
import com.purpura.model.Model;

import java.util.Map;

public class DAOManager {

    private static final Map<String, DAO<? extends Model>> DAO_MAP = Map.of(
            "empresa", new EmpresaDAO(),
            "enderecoempresa", new EnderecoEmpresaDAO(),
            "residuo", new ResiduoDAO(),
            "transporte", new TransporteDAO(),
            "administrador", new AdministradorDAO(),
            "Telefone", new TelefoneDAO()
    );

    public static DAO<? extends Model> getDAO(String nomeTabela) {
        DAO<? extends Model> dao = DAO_MAP.get(nomeTabela.toLowerCase());
        if (dao == null) {
            throw new DAONotFoundException("DAO não encontrado para: " + nomeTabela);
        }
        return dao;
    }
}
