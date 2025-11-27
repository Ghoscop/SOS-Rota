package com.example.sosrota.Controller;

import com.example.sosrota.Model.BO.BairroBO;
import com.example.sosrota.Model.VO.BairroVO;
import com.example.sosrota.Model.VO.ProfissionalVO;

import java.util.List;

public class BairroController {
    private BairroBO bairroBO = new BairroBO();

    public void cadastrarBairro(String nome) {
        try {
            BairroVO bairroVO = new BairroVO();
            bairroVO.setNome(nome);

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }

    }

    public void buscaBairro(int id){
        // Nesse caso eu estou imprimindo no console, caso for para aparecer em lista, o tipo da função deve ser alterada e no fim
        // do sistema deve-se colocar um Return

        try {
            BairroVO bairroVO = bairroBO.buscar(id);

            if (bairroVO == null) {
                System.out.println("Profissional não encontrado.");
                return;
            }

            System.out.println("----- Bairro -----");
            System.out.println("ID: " + bairroVO.getId());
            System.out.println("Nome: " + bairroVO.getNome());

        } catch (Exception e) {
            System.out.println("Erro ao buscar: " + e.getMessage());
        }

    }

    public void listarBairros() {
        try {
            List<BairroVO> lista = bairroBO.buscarTodos();

            if (lista.isEmpty()) {
                System.out.println("Nenhum profissional encontrado.");
                return;
            }

            System.out.println("===== LISTA DE Bairros Cadastrados =====");
            for (BairroVO bairroVO : lista) {
                System.out.println(
                        " | ID: " + bairroVO.getId() + "\n" +
                        " | Nome: " + bairroVO.getNome() + "\n"                );
                System.out.println("--------------------------");
            }

        } catch (Exception e) {
            System.out.println("Erro ao listar: " + e.getMessage());
        }
    }

    public void excluirBairro(int id) {
        try {
            bairroBO.excluir(id);
            System.out.println("Bairro excluído com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
        }
    }


    public void atualizarBairro(int id, String nome) {
        try {
            BairroVO bairroVO = new BairroVO();

            bairroVO.setId(id);
            bairroVO.setNome(nome);

            bairroBO.atualizar(bairroVO);
            System.out.println("Bairro atualizado com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao atualizar: " + e.getMessage());
        }
    }

}
