//package com.example.sosrota.Model.BO;
//
//import com.example.sosrota.Model.DAO.AmbulanciaDAO;
//import com.example.sosrota.Model.VO.AmbulanciaVO;
//
//import java.util.List;
//
//public class AmbulanciaBO {
//    private AmbulanciaDAO ambulanciaDAO = new AmbulanciaDAO();
//
//    public void salvar(AmbulanciaVO ambulanciaVO) throws Exception {
//        // Regras de Negocios / Validações (OBS: Colocar todos os atributos como obrigatorios)
//
//        // Validação do Nome
//        if (ambulanciaVO.getPlaca() == null || ambulanciaVO.getPlaca().trim().isEmpty()) {
//            throw new Exception("Nome é obrigatório.");
//        }
//        // Validação de cada parametro
//
//        // Supondo que passou em tudo, ele cria a ambulancia
//        ambulanciaDAO.create(ambulanciaVO);
//    }
//
//    public AmbulanciaVO buscar(int id) throws Exception {
//        return ambulanciaDAO.reader(id);
//    }
//
//    public List<AmbulanciaVO> buscarTodos() throws Exception {
//        return ambulanciaDAO.readerAll();
//    }
//
//
//    public void atualizar(AmbulanciaVO ambulanciaVO) throws Exception {
//        if (ambulanciaVO.getId() <= 0) {
//            throw new Exception("ID inválido.");
//        }
//        ambulanciaDAO.update(ambulanciaVO);
//    }
//
//    public void excluir(int id) throws Exception {
//        ambulanciaDAO.delete(id);
//    }
//
//}
