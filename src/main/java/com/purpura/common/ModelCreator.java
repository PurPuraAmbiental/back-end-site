package com.purpura.common;

import com.purpura.exception.ModelNotFoundException;
import com.purpura.model.*;

import java.text.ParseException;
import java.util.Map;

public class ModelCreator {
    public static Model createModel(String nomeTabela, Map<String, String> params) throws ParseException {
        switch (nomeTabela){
            case "Empresa" -> {
                return new Empresa(params);
            }
            case "EnderecoEmpresa" -> {
                return new EnderecoEmpresa(params);
            }
            case "Residuo" -> {
                return new Residuo(params);
            }
            case "Transporte" -> {
                return new Transporte(params);
            }
            case "Administrador" -> {
                return new Administrador(params);
            }  case "Telefone" -> {
                return new Telefone(params);
            }
            default -> throw new ModelNotFoundException(nomeTabela);
        }
    }
}
