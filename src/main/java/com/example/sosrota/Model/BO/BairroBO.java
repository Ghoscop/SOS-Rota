package com.example.sosrota.Model.BO;

import com.example.sosrota.Model.DAO.BairroDAO;
import com.example.sosrota.Model.VO.BairroVO;

import java.util.List;

public class BairroBO {
    private BairroDAO bairroDAO = new BairroDAO();

    public void salvar(BairroVO bairroVO) throws Exception {
        // Regras de Negocios / Validações (OBS: Colocar todos os atributos como obrigatorios)

        bairroDAO.create(bairroVO);
    }

    public BairroVO buscar(int id) throws Exception {
        return bairroDAO.reader(id);
    }

    public List<BairroVO> buscarTodos() throws Exception {
        return bairroDAO.readerAll();
    }

    public void atualizar(BairroVO bairroVO) throws Exception {
        // Regras de Negocios / Validações
        // Atualizar nome etc...

        bairroDAO.update(bairroVO);
    }

    public void excluir(int id) throws Exception {
        bairroDAO.delete(id);
    }

}
