package com.example.sosrota.View;

import javax.swing.*;
import java.awt.*;

public class InterfaceView {
    public static JLabel mensagem(String texto, int tamanhoFonte, Color cor) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Segoe UI", Font.BOLD, tamanhoFonte));
        label.setForeground(cor);
        return label;
    }

    public static JCheckBox checkBonito(String texto) {
        JCheckBox c = new JCheckBox(texto);
        c.setFont(new Font("Arial", Font.BOLD, 16));
        c.setBackground(Color.WHITE);
        c.setFocusPainted(false);
        c.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 5));
        return c;
    }

    public static String getFuncaoSelecionada(JCheckBox f1, JCheckBox f2, JCheckBox f3) {
        if (f1.isSelected()) return f1.getText();
        if (f2.isSelected()) return f2.getText();
        if (f3.isSelected()) return f3.getText();
        return null;
    }

    public static boolean getSelecionado(JCheckBox ativo, JCheckBox inativo) {
        return ativo.isSelected();
    }

    public static JButton criarBotao(String texto, int x, int y, int largura, int altura) {
        JButton botao = new JButton(texto);
        botao.setPreferredSize(new Dimension(largura, altura));
        botao.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        botao.setBounds(x, y, largura, altura);
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        return botao;
    }

    public static JLabel carregarImagem(String nomeArquivo, int largura, int altura) {
        ImageIcon icon = new ImageIcon(
                ProfissionalView.class.getResource("/images/" + nomeArquivo)
        );

        Image img = icon.getImage().getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
        return new JLabel(new ImageIcon(img));
    }
    public static int solicitarIdProfissional() {
        JDialog dialog = new JDialog((Frame) null, "Buscar Profissional", true);
        dialog.setSize(300, 160);
        dialog.setLayout(null);
        dialog.setLocationRelativeTo(null);
        dialog.setResizable(false);

        JLabel lbl = new JLabel("Digite o ID do Profissional:");
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lbl.setBounds(20, 10, 260, 30);
        dialog.add(lbl);

        JTextField campoId = new JTextField();
        campoId.setBounds(20, 45, 240, 30);
        dialog.add(campoId);

        final int[] resultado = { -1 };

        JButton btnConfirmar = new JButton("OK");
        btnConfirmar.setBounds(20, 85, 100, 30);
        btnConfirmar.addActionListener(e -> {
            try {
                resultado[0] = Integer.parseInt(campoId.getText().trim());
                dialog.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "ID inválido!");
            }
        });
        dialog.add(btnConfirmar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(160, 85, 100, 30);
        btnCancelar.addActionListener(e -> {
            resultado[0] = -1;
            dialog.dispose();
        });
        dialog.add(btnCancelar);

        dialog.setVisible(true);
        return resultado[0];
    }
}
