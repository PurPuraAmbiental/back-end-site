package com.purpura.dao;

import com.purpura.models.Model;

import java.util.List;

public class DAOManager {
    private static final List<DAO<? extends Model>> DAOS = List.of(
            new ArquivoDAO(),
            new EmpresaDAO(),
            new EnderecoEmpresaDAO(),
            new ItemPedidoDAO(),
            new MensagemDAO(),
            new PagamentoDAO(),
            new PedidoDAO(),
            new ResiduoDAO(),
            new TransporteDAO()
    );

    public static DAO<? extends Model> getDAO(String nome) {
        for (DAO<? extends Model> dao : DAOS) {
            if (dao.getNomeTabela().equals(nome)) {
                return dao;
            }
        }
        return null;
    }
}