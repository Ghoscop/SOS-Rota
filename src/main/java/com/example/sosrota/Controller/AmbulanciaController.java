package com.example.sosrota.Controller;

import com.example.sosrota.Model.BO.AmbulanciaBO;
import com.example.sosrota.Model.VO.AmbulanciaVO;

import java.util.List;

public class AmbulanciaController {

    private AmbulanciaBO ambulanciaBO = new AmbulanciaBO();

    public void cadastrarAmbulancia(String placa, String tipo, boolean status) {
        try {
            AmbulanciaVO ambulanciaVO = new AmbulanciaVO();
            ambulanciaVO.setPlaca(placa);
            ambulanciaVO.setTipo(tipo);
            ambulanciaVO.setStatus(status);

            ambulanciaBO.salvar(ambulanciaVO);
            System.out.println("Ambulancia cadastrada com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao cadastrar: " + e.getMessage());
        }
    }

    public void buscarAmbulancia(int id) {
        // Nesse caso eu estou imprimindo no console, caso for para aparecer em lista, o tipo da função deve ser alterada e no fim
        // do sistema deve-se colocar um Return
        try {
            AmbulanciaVO ambulanciaVO = ambulanciaBO.buscar(id);

            if (ambulanciaVO == null) {
                System.out.println("Ambulancia não encontrado.");
                return;
            }

            System.out.println("----- AMBULÂNCIA -----");
            System.out.println("ID: " + ambulanciaVO.getId());
            System.out.println("Placa: " + ambulanciaVO.getPlaca());
            System.out.println("Tipo: " + ambulanciaVO.getTipo());
            System.out.println("Status: " + ambulanciaVO.getStatus());

        } catch (Exception e) {
            System.out.println("Erro ao buscar: " + e.getMessage());
        }
    }

    public void listarAmbulancias() {
        try {
            List<AmbulanciaVO> lista = ambulanciaBO.buscarTodos();

            if (lista.isEmpty()) {
                System.out.println("Nenhum Ambulancias encontrado.");
                return;
            }

            System.out.println("===== LISTA DE AMBULÂNCIA =====");
            for (AmbulanciaVO ambulanciaVO : lista) {
                System.out.println(
                        " | ID: " + ambulanciaVO.getId() + "\n" +
                                " | Placa: " + ambulanciaVO.getPlaca() + "\n" +
                                " | Tipo: " + ambulanciaVO.getTipo() + "\n" +
                                " | Status: " + ambulanciaVO.getStatus() + "\n"
                );
                System.out.println("--------------------------");
            }

        } catch (Exception e) {
            System.out.println("Erro ao listar: " + e.getMessage());
        }
    }


    public void excluirAmbulancia(int id) {
        try {
            ambulanciaBO.excluir(id);
            System.out.println("Ambulancia excluída com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
        }
    }


    public void atualizarAmbulancia(int id, String placa, String tipo, boolean status) {
        try {
            AmbulanciaVO ambulanciaVO = new AmbulanciaVO();

            ambulanciaVO.setId(id);
            ambulanciaVO.setPlaca(placa);
            ambulanciaVO.setTipo(tipo);
            ambulanciaVO.setStatus(status);

            ambulanciaBO.atualizar(ambulanciaVO);
            System.out.println("Ambulância atualizada com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao atualizar: " + e.getMessage());
        }
    }

}
