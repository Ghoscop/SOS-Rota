package com.example.sosrota;

import com.example.sosrota.Controller.ProfissionalController;
import java.util.Scanner;

public class MainModel {

    public static void main(String[] args) {

        ProfissionalController controller = new ProfissionalController();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n========================");
            System.out.println("     MENU PRINCIPAL     ");
            System.out.println("========================");
            System.out.println("[1] Criar profissional");
            System.out.println("[2] Consultar profissional");
            System.out.println("[3] Listar todos os profissionais");
            System.out.println("[4] Excluir cadastro de um profissional");
            System.out.println("[5] Atualizar cadastro de um profissional");
            System.out.println("[0] Sair");
            System.out.print("Escolha: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // limpar buffer

            switch (opcao) {

                // ---------------------------
                // 1. Cadastrar
                // ---------------------------
                case 1:
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();

                    System.out.print("Contato (CPF/Telefone): ");
                    String contato = scanner.nextLine();

                    System.out.print("Especialidade: ");
                    String funcao = scanner.nextLine();

                    System.out.print("Ativo (true/false): ");
                    boolean ativo = scanner.nextBoolean();

                    controller.cadastrarProfissional(nome, contato, funcao, ativo);
                    break;

                // ---------------------------
                // 2. Buscar por ID
                // ---------------------------
                case 2:
                    System.out.print("ID do profissional: ");
                    int id = scanner.nextInt();
                    controller.buscarProfissional(id);
                    break;

                // ---------------------------
                // 3. Listar todos
                // ---------------------------
                case 3:
                    controller.listarProfissionais();
                    break;

                // ---------------------------
                // 4. Excluir um profissional
                // ---------------------------
                case 4:
                    System.out.print("ID do profissional a excluir: ");
                    int idExcluir = scanner.nextInt();
                    controller.excluirProfissional(idExcluir);
                    break;

                // ---------------------------
                // 5. Atualizar um profissional
                // ---------------------------
                case 5:
                    System.out.print("ID do profissional para atualizar: ");
                    int idAtualizar = scanner.nextInt();
                    scanner.nextLine(); // limpar buffer

                    System.out.print("Novo nome: ");
                    String novoNome = scanner.nextLine();

                    System.out.print("Novo contato: ");
                    String novoContato = scanner.nextLine();

                    System.out.print("Nova especialidade: ");
                    String novaFuncao = scanner.nextLine();

                    System.out.print("Ativo (true/false): ");
                    boolean novoAtivo = scanner.nextBoolean();

                    controller.atualizarProfissional(idAtualizar, novoNome, novoContato, novaFuncao, novoAtivo);
                    break;

                // ---------------------------
                // 0. Sair
                // ---------------------------
                case 0:
                    System.out.println("Saindo...");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}
