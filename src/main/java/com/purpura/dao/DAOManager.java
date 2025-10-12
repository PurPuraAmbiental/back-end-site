package com.purpura.dao;

import com.purpura.exception.DAONotFoundException;
import com.purpura.model.Model;

import java.util.Map;

public class DAOManager {

    private static final Map<String, DAO<? extends Model>> DAO_MAP = Map.of(
            "arquivo", new ArquivoDAO(),
            "empresa", new EmpresaDAO(),
            "enderecoempresa", new EnderecoEmpresaDAO(),
            "itempedido", new ItemPedidoDAO(),
            "mensagem", new MensagemDAO(),
            "pagamento", new PagamentoDAO(),
            "pedido", new PedidoDAO(),
            "residuo", new ResiduoDAO(),
            "transporte", new TransporteDAO(),
            "administrador", new AdministradorDAO()
    );

    public static DAO<? extends Model> getDAO(String nomeTabela) {
        DAO<? extends Model> dao = DAO_MAP.get(nomeTabela.toLowerCase());
        if (dao == null) {
            throw new DAONotFoundException("DAO não encontrado para: " + nomeTabela);
        }
        return dao;
    }
}
