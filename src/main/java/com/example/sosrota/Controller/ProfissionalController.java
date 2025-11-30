package com.example.sosrota.Controller;

import com.example.sosrota.Model.BO.ProfissionalBO;
import com.example.sosrota.Model.VO.ProfissionalVO;

import java.util.Collections;
import java.util.List;

public class ProfissionalController {

    private ProfissionalBO profissionalBO = new ProfissionalBO();

    public void cadastrarProfissional(String nome, String contato, String funcao, boolean ativo) {
        try {
            ProfissionalVO p = new ProfissionalVO();
            p.setNome(nome);
            p.setContato(contato);
            p.setFuncao(funcao);
            p.setAtivo(ativo);

            profissionalBO.salvar(p);
            System.out.println("Profissional cadastrado com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao cadastrar: " + e.getMessage());
        }
    }

    public void buscarProfissional(int id) {
        // Nesse caso eu estou imprimindo no console, caso for para aparecer em lista, o tipo da função deve ser alterada e no fim
        // do sistema deve-se colocar um Return
        try {
            ProfissionalVO p = profissionalBO.buscar(id);

            if (p == null) {
                System.out.println("Profissional não encontrado.");
                return;
            }

            System.out.println("----- PROFISSIONAL -----");
            System.out.println("ID: " + p.getId());
            System.out.println("Nome: " + p.getNome());
            System.out.println("Contato: " + p.getContato());
            System.out.println("Função: " + p.getFuncao());
            System.out.println("Ativo: " + p.getAtivo());

        } catch (Exception e) {
            System.out.println("Erro ao buscar: " + e.getMessage());
        }
    }

    public List<ProfissionalVO> listarProfissionais() throws Exception {
        List<ProfissionalVO> lista = profissionalBO.buscarTodos();

        if (lista == null || lista.isEmpty()) {
            System.out.println("Nenhum profissional encontrado.");
            return Collections.emptyList();
        }

        return lista;
    }



    public void excluirProfissional(int id) {
        try {
            profissionalBO.excluir(id);
            System.out.println("Profissional excluído com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
        }
    }


    public void atualizarProfissional(int id, String nome, String contato, String funcao, boolean ativo) {
        try {
            ProfissionalVO p = new ProfissionalVO();

            p.setId(id);
            p.setNome(nome);
            p.setContato(contato);
            p.setFuncao(funcao);
            p.setAtivo(ativo);

            profissionalBO.atualizar(p);
            System.out.println("Profissional atualizado com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao atualizar: " + e.getMessage());
        }
    }
}
