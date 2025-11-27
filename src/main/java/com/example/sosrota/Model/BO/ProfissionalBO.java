//package com.example.sosrota.Model.BO;
//
//
//import com.example.sosrota.Model.DAO.ProfissionalDAO;
//import com.example.sosrota.Model.VO.ProfissionalVO;
//
//import java.util.List;
//
//public class ProfissionalBO {
//    private ProfissionalDAO profissionalDAO = new ProfissionalDAO();
//
//    public void salvar(ProfissionalVO profissionalVO) throws Exception {
//        // Regras de Negocios / Validações (OBS: Colocar todos os atributos como obrigatorios)
//
//        // Validação do Nome
//        if (profissionalVO.getNome() == null || profissionalVO.getNome().trim().isEmpty()) {
//            throw new Exception("Nome é obrigatório.");
//        }
//        // Validação do Nome
//        // Validação do contato
//        // Validação do funcao
//        // Validação do ativo
//
//        // Supondo que passou em tudo, ele cria o user
//        profissionalDAO.create(profissionalVO);
//    }
//
//    public ProfissionalVO buscar(int id) throws Exception {
//        return profissionalDAO.reader(id);
//    }
//
//    public List<ProfissionalVO> buscarTodos() throws Exception {
//        return profissionalDAO.readerAll();
//    }
//
//
//    public void atualizar(ProfissionalVO profissionalVO) throws Exception {
//        if (profissionalVO.getId() <= 0) {
//            throw new Exception("ID inválido.");
//        }
//        profissionalDAO.update(profissionalVO);
//    }
//
//    public void excluir(int id) throws Exception {
//        profissionalDAO.delete(id);
//    }
//
//}
