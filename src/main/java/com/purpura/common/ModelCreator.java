package com.purpura.common;

import com.purpura.exception.ModelNotFoundException;
import com.purpura.model.*;

import java.text.ParseException;
import java.util.Map;

public class ModelCreator {
    public static Model createModel(String nomeTabela, Map<String, String> params) throws ParseException {
        switch (nomeTabela){
            case "Arquivo" -> {
                return new Arquivo(params);
            }
            case "Empresa" -> {
                return new Empresa(params);
            }
            case "EnderecoEmpresa" -> {
                return new EnderecoEmpresa(params);
            }
            case "ItemPedido" -> {
                return new ItemPedido(params);
            }
            case "Mensagem" -> {
                return new Mensagem(params);
            }
            case "Pagamento" -> {
                return new Pagamento(params);
            }
            case "Pedido" -> {
                return new Pedido(params);
            }
            case "Residuo" -> {
                return new Residuo(params);
            }
            case "Transporte" -> {
                return new Transporte(params);
            }
            default -> throw new ModelNotFoundException(nomeTabela);
        }
    }
}
