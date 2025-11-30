package com.example.sosrota.View;

import javax.swing.*;
import java.awt.*;
import static com.example.sosrota.View.InterfaceView.*;


public class ProfissionalMenu {
    private static JFrame main;
    private static ProfissionalView pView;


    public ProfissionalMenu() {
        SwingUtilities.invokeLater(() -> {
            main = new JFrame("Profissional - Sistema");
            main.setSize(300, 300);
            main.setLayout(new BorderLayout());
            main.setLocationRelativeTo(null);
            main.setResizable(false);
            main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            main.setVisible(true);
            JPanel menu = menu();
            main.add(menu, BorderLayout.CENTER);
            main.revalidate();
            main.repaint();
        });
    }

    public static JPanel menu() {

        JPanel menuView = new JPanel(null);
        menuView.setBackground(Color.WHITE);
        menuView.setPreferredSize(new Dimension(300, 300));

        JPanel bannerPrincipal = new JPanel(null);
        bannerPrincipal.setBounds(0, 0, 300, 300);
        bannerPrincipal.setBackground(new Color(0x0055CC));

        JLabel mensagemProfissional = mensagem("Menu Profissional", 24, Color.WHITE);
        mensagemProfissional.setBounds(40, 2, 250, 40);
        bannerPrincipal.add(mensagemProfissional);

        JPanel painelCadastro = new JPanel(null);
        painelCadastro.setBounds(20, 40, 250, 300);
        painelCadastro.setBackground(Color.WHITE);
        painelCadastro.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));

        // Posição X fixa para centralizar na área de 250px
        int posX = 25;
        int largura = 200;
        int altura = 40;

        // Espaçamento vertical
        int posY = 10;
        int espaco = 15;

        // Botões existentes
        JButton btnCadastrar = criarBotao("Inserir", posX, posY, largura, altura);
        btnCadastrar.setBackground(new Color(126, 217, 87));

        JButton btnVisualizar = criarBotao("Visualizar", posX, posY + (altura + espaco) * 3, largura, altura);
        btnVisualizar.setBackground(new Color(0, 162, 232)); // Azul claro

        JButton btnEditar = criarBotao("Editar", posX, posY + (altura + espaco), largura, altura);
        btnEditar.setBackground(new Color(255, 165, 0));

        JButton btnExcluir = criarBotao("Remover", posX, posY + (altura + espaco) * 2, largura, altura);
        btnExcluir.setBackground(new Color(255, 0, 0));


        btnCadastrar.addActionListener(e -> {
            pView = new ProfissionalView("create");
        });
        btnVisualizar.addActionListener(e -> {
            pView = new ProfissionalView("reader");
        });
        btnEditar.addActionListener(e -> {
            pView = new ProfissionalView("update");
        });
        btnExcluir.addActionListener(e -> {
            pView = new ProfissionalView("delete");
        });

        // Adiciona todos os botões ao painel
        painelCadastro.add(btnCadastrar);
        painelCadastro.add(btnEditar);
        painelCadastro.add(btnExcluir);
        painelCadastro.add(btnVisualizar);


        // ADICIONA PAINÉIS NA TELA
        bannerPrincipal.add(painelCadastro);
        menuView.add(bannerPrincipal);

        return menuView; // devolve o painel completo
    }

    public static void main(String[] args) {
        ProfissionalMenu menu = new ProfissionalMenu();
    }
}
