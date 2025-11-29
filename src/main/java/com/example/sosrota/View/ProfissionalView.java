package com.example.sosrota.View;

import com.example.sosrota.Controller.ProfissionalController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ItemListener;

public class ProfissionalView {

    private static JFrame main;        // janela principal
    private static JPanel currentView; // painel atual

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
        return null; // nenhum selecionado
    }

    public static boolean getSelecionado(JCheckBox ativo, JCheckBox inativo) {
        return ativo.isSelected(); // ativo = true, inativo = false
    }



    public static JButton criarBotao(String texto, int x, int y, int largura, int altura) {
        JButton botao = new JButton(texto);
        botao.setPreferredSize(new Dimension(largura, altura));
        botao.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        botao.setBounds(x, y, largura, altura);
        botao.setFocusPainted(false);
        return botao;
    }

    public static JButton criarBotao(JLabel imagem, int x, int y, int largura, int altura) {
        JButton botao = new JButton();

        botao.setLayout(null); // permite colocar a imagem dentro do botão
        botao.setBounds(x, y, largura, altura);
        botao.setPreferredSize(new Dimension(largura, altura));

        // posiciona a imagem centralizada dentro do botão
        imagem.setBounds(
                (largura - imagem.getPreferredSize().width) / 2,
                (altura - imagem.getPreferredSize().height) / 2,
                imagem.getPreferredSize().width,
                imagem.getPreferredSize().height
        );

        botao.add(imagem);

        botao.setFocusPainted(false);

        return botao;
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
                JOptionPane.showMessageDialog(dialog, "ID inválido! Digite um número.");
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



    public static JLabel carregarImagem(String nomeArquivo, int largura, int altura) {

        // Carrega imagem da pasta resources/images
        ImageIcon icon = new ImageIcon(
                ProfissionalView.class.getResource("/images/" + nomeArquivo)
        );

        // Redimensiona a imagem
        Image img = icon.getImage().getScaledInstance(largura, altura, Image.SCALE_SMOOTH);

        // Retorna JLabel já pronto
        return new JLabel(new ImageIcon(img));
    }


    public static JPanel createProfissional() {

        JPanel createView = new JPanel();
        createView.setLayout(null);
        createView.setBackground(Color.WHITE);
        createView.setPreferredSize(new Dimension(700, 500));

        // ------------------ BANNER (Fundo azul da tela toda) ------------------
        JPanel bannerPrincipal = new JPanel();
        bannerPrincipal.setLayout(null);
        bannerPrincipal.setBounds(0, 0, 700, 500);  // ocupa a tela toda
        bannerPrincipal.setBackground(new Color(0x0055CC));  // azul

        // Título dentro do banner
        JLabel mensagemProfissional = mensagem("Criar Profissional", 24, Color.WHITE);
        mensagemProfissional.setBounds(240, 10, 300, 40);
        bannerPrincipal.add(mensagemProfissional);

        // ------------------ PAINEL INTERNO (branco com bordas) ------------------
        JPanel painelCadastro = new JPanel();
        painelCadastro.setLayout(null);
        painelCadastro.setBounds(20, 55, 650, 400);  // painel menor
        painelCadastro.setBackground(Color.WHITE);

        // Borda opcional (fica mais bonito visualmente)
        painelCadastro.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));

        // Icon Profissional
        JLabel imagem = carregarImagem("IconProfissional.png", 180, 180);
        imagem.setBounds(420, 30, 180, 180);
        painelCadastro.add(imagem);


        // Campo Nome
        JLabel mensagemNome = mensagem("Nome:", 18, Color.BLACK);
        mensagemNome.setBounds(20, 20, 200, 30);
        painelCadastro.add(mensagemNome);

        JTextField campoNome = new JTextField();
        campoNome.setBackground(new Color(231, 231, 231));
        campoNome.setBounds(20, 55, 300, 30);
        painelCadastro.add(campoNome);

        // Campo Contato
        JLabel mensagemContato = mensagem("Contato:", 18, Color.BLACK);
        mensagemContato.setBounds(20, 90, 200, 30);
        painelCadastro.add(mensagemContato);

        JTextField campoContato = new JTextField();
        campoContato.setBackground(new Color(231, 231, 231));
        campoContato.setBounds(20, 125, 300, 30);
        painelCadastro.add(campoContato);

        // Campo Função
        JLabel mensagemFuncao = mensagem("Função", 18, Color.BLACK);
        mensagemFuncao.setBounds(20, 180, 200, 30);
        painelCadastro.add(mensagemFuncao);

        // CHECKBOX BONITOS
        JCheckBox funcao1 = checkBonito("Médico");
        JCheckBox funcao2 = checkBonito("Enfermeiro");
        JCheckBox funcao3 = checkBonito("Condutor");

        // POSIÇÕES
        funcao1.setBounds(20, 220, 150, 30);
        funcao2.setBounds(20, 250, 150, 30);
        funcao3.setBounds(20, 280, 150, 30);

        // SISTEMA DE EXCLUSIVIDADE
        ItemListener exclusivoFuncao = e -> {
            JCheckBox clicado = (JCheckBox) e.getSource();

            if (clicado.isSelected()) {
                funcao1.setSelected(clicado == funcao1);
                funcao2.setSelected(clicado == funcao2);
                funcao3.setSelected(clicado == funcao3);
            }
        };

        funcao1.addItemListener(exclusivoFuncao);
        funcao2.addItemListener(exclusivoFuncao);
        funcao3.addItemListener(exclusivoFuncao);

        // ADICIONA AO PAINEL
        painelCadastro.add(funcao1);
        painelCadastro.add(funcao2);
        painelCadastro.add(funcao3);

        // Campo Status
        JLabel mensagemStatus = mensagem("Status", 18, Color.BLACK);
        mensagemStatus.setBounds(200, 180, 200, 30);
        painelCadastro.add(mensagemStatus);

        // CHECKBOX BONITOS
        JCheckBox status1 = checkBonito("Ativo");
        JCheckBox status2 = checkBonito("Inativo");

        // POSIÇÕES
        status1.setBounds(200, 220, 150, 30);
        status2.setBounds(200, 250, 150, 30);

        // SISTEMA DE EXCLUSIVIDADE
        ItemListener exclusivoStatus = e -> {
            JCheckBox clicado = (JCheckBox) e.getSource();

            if (clicado.isSelected()) {
                status1.setSelected(clicado == status1);
                status2.setSelected(clicado == status2);
            }
        };

        status1.addItemListener(exclusivoStatus);
        status2.addItemListener(exclusivoStatus);

        // ADICIONA AO PAINEL
        painelCadastro.add(status1);
        painelCadastro.add(status2);

        // Botão de Sair e Salvar
        JButton botaoSair = criarBotao("Sair", 100, 350, 200, 40);
        JButton botaoSalvar = criarBotao("Salvar", 350, 350, 200, 40);

        botaoSair.addActionListener(e -> {
            main.dispose();
        });

        botaoSalvar.addActionListener(e -> {
            ProfissionalController create = new ProfissionalController();
            create.cadastrarProfissional(
                    campoNome.getText(),
                    campoContato.getText(),
                    getFuncaoSelecionada(funcao1, funcao3, funcao3),
                    getSelecionado(status1, status2)
            );
        });

        botaoSair.setBackground(new Color(115, 115, 115));
        botaoSalvar.setBackground(new Color(126, 217, 87));

        painelCadastro.add(botaoSair);
        painelCadastro.add(botaoSalvar);

        // Adiciona o painel dentro do banner
        bannerPrincipal.add(painelCadastro);

        // Adiciona o banner como fundo da tela
        createView.add(bannerPrincipal);

        return createView;
    }


    public static JPanel readerProfissional() {
        JPanel readerView = new JPanel();
        readerView.setLayout(null);
        readerView.setBackground(Color.WHITE);
        readerView.setPreferredSize(new Dimension(700, 500));

        JPanel bannerPrincipal = new JPanel();
        bannerPrincipal.setLayout(null);
        bannerPrincipal.setBounds(0, 0, 700, 500);
        bannerPrincipal.setBackground(new Color(0x0055CC));

        JLabel mensagemProfissional = mensagem("Visualizar Profissionais", 24, Color.WHITE);
        mensagemProfissional.setBounds(230, 10, 300, 40);
        bannerPrincipal.add(mensagemProfissional);

        JPanel painelReader = new JPanel();
        painelReader.setLayout(null);
        painelReader.setBounds(20, 55, 650, 400);
        painelReader.setBackground(Color.WHITE);

        painelReader.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));

        // Busca por ID
        // Mensagem Busca
        JLabel mensagemBusca = mensagem("Buscar por ID", 18, Color.BLACK);
        mensagemBusca.setBounds(20, 5, 125, 30);
        painelReader.add(mensagemBusca);

        JTextField campoID = new JTextField();
        campoID.setBackground(new Color(231, 231, 231));
        campoID.setBounds(150, 8, 50, 30);
        painelReader.add(campoID);

        // Botão de busca
        JLabel imagem = carregarImagem("Lupa.png", 25, 25);
        JButton botaoBusca = criarBotao(imagem, 210, 7, 30, 30);
        botaoBusca.setBackground(new Color(3, 101, 215));
        painelReader.add(botaoBusca);

        String[] colunas = { "ID", "Nome", "Contato", "Função", "Status", "Equipe" };

        DefaultTableModel modelo = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        modelo.addRow(new Object[]{1,  "João Silva",        "99999-1111", "Médico",       "Ativo",   "Nenhuma"});
        modelo.addRow(new Object[]{2,  "Maria Santos",      "98888-2222", "Enfermeira",   "Ativo",   "Nenhuma"});
        modelo.addRow(new Object[]{3,  "Pedro Andrade",     "97777-3333", "Condutor",     "Inativo", "Nenhuma"});
        modelo.addRow(new Object[]{4,  "Julia Pereira",     "96666-4444", "Médica",       "Ativo",   "Nenhuma"});
        modelo.addRow(new Object[]{5,  "Carlos Henrique",   "95555-5555", "Condutor",     "Ativo",   "Nenhuma"});
        modelo.addRow(new Object[]{6,  "Fernanda Costa",    "94444-6666", "Enfermeira",   "Ativo",   "Nenhuma"});
        modelo.addRow(new Object[]{7,  "Ricardo Mendes",    "93333-7777", "Médico",       "Inativo", "Afastado"});
        modelo.addRow(new Object[]{8,  "Patricia Lima",     "92222-8888", "Condutora",    "Ativo",   "Nenhuma"});
        modelo.addRow(new Object[]{9,  "Gabriel Souza",     "91111-9999", "Condutor",     "Ativo",   "Nenhuma"});
        modelo.addRow(new Object[]{10, "Larissa Rocha",     "90000-0001", "Enfermeira",   "Inativo", "Férias"});
        modelo.addRow(new Object[]{11, "Rafael Oliveira",   "90000-0002", "Médico",       "Ativo",   "Nenhuma"});
        modelo.addRow(new Object[]{12, "Bruna Carvalho",    "90000-0003", "Enfermeira",   "Ativo",   "Nenhuma"});
        modelo.addRow(new Object[]{13, "André Fernandes",   "90000-0004", "Condutor",     "Inativo", "Demissão"});
        modelo.addRow(new Object[]{14, "Sofia Martins",     "90000-0005", "Médica",       "Ativo",   "Nenhuma"});
        modelo.addRow(new Object[]{15, "Thiago Ribeiro",    "90000-0006", "Condutor",     "Ativo",   "Nenhuma"});

        // ---------------- TABELA ----------------
        JTable tabela = new JTable(modelo);
        tabela.setRowHeight(28);
        tabela.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tabela.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));

        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        // ---------------- SCROLL ----------------
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(20, 50, 600, 280);

        painelReader.add(scroll);

        // Botão de Sair e Salvar
        JButton botaoSair = criarBotao("Sair", 225, 340, 200, 40);
        botaoSair.setBackground(new Color(115, 115, 115));
        painelReader.add(botaoSair);

        bannerPrincipal.add(painelReader);
        readerView.add(bannerPrincipal);
        return readerView;
    }

    public static JPanel updateProfissional() {

        JPanel updateView = new JPanel();
        updateView.setLayout(null);
        updateView.setBackground(Color.WHITE);
        updateView.setPreferredSize(new Dimension(700, 500));

        // ------------------ BANNER (Fundo azul da tela toda) ------------------
        JPanel bannerPrincipal = new JPanel();
        bannerPrincipal.setLayout(null);
        bannerPrincipal.setBounds(0, 0, 700, 500);  // ocupa a tela toda
        bannerPrincipal.setBackground(new Color(0x0055CC));  // azul

        // Título dentro do banner
        JLabel mensagemProfissional = mensagem("Atualizar Profissional", 24, Color.WHITE);
        mensagemProfissional.setBounds(230, 10, 300, 40);
        bannerPrincipal.add(mensagemProfissional);

        // ------------------ PAINEL INTERNO (branco com bordas) ------------------
        JPanel painelCadastro = new JPanel();
        painelCadastro.setLayout(null);
        painelCadastro.setBounds(20, 55, 650, 400);  // painel menor
        painelCadastro.setBackground(Color.WHITE);

        // Borda opcional (fica mais bonito visualmente)
        painelCadastro.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));

        // Icon Profissional
        ImageIcon icon = new ImageIcon(
                ProfissionalView.class.getResource("/images/IconProfissional.png")
        );
        Image img = icon.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
        JLabel imagem = new JLabel(new ImageIcon(img));
        imagem.setBounds(420, 30, 180, 180);
        painelCadastro.add(imagem);


        // Campo Nome
        JLabel mensagemNome = mensagem("Novo Nome:", 18, Color.BLACK);
        mensagemNome.setBounds(20, 20, 200, 30);
        painelCadastro.add(mensagemNome);

        JTextField campoNome = new JTextField();
        campoNome.setBackground(new Color(231, 231, 231));
        campoNome.setBounds(20, 55, 300, 30);
        painelCadastro.add(campoNome);

        // Campo Contato
        JLabel mensagemContato = mensagem("Novo Contato:", 18, Color.BLACK);
        mensagemContato.setBounds(20, 90, 200, 30);
        painelCadastro.add(mensagemContato);

        JTextField campoContato = new JTextField();
        campoContato.setBackground(new Color(231, 231, 231));
        campoContato.setBounds(20, 125, 300, 30);
        painelCadastro.add(campoContato);

        // Campo Função
        JLabel mensagemFuncao = mensagem("Nova Função", 18, Color.BLACK);
        mensagemFuncao.setBounds(20, 180, 200, 30);
        painelCadastro.add(mensagemFuncao);

        // CHECKBOX BONITOS
        JCheckBox funcao1 = checkBonito("Médico");
        JCheckBox funcao2 = checkBonito("Enfermeiro");
        JCheckBox funcao3 = checkBonito("Condutor");

        // POSIÇÕES
        funcao1.setBounds(20, 220, 150, 30);
        funcao2.setBounds(20, 250, 150, 30);
        funcao3.setBounds(20, 280, 150, 30);

        // SISTEMA DE EXCLUSIVIDADE
        ItemListener exclusivoFuncao = e -> {
            JCheckBox clicado = (JCheckBox) e.getSource();

            if (clicado.isSelected()) {
                funcao1.setSelected(clicado == funcao1);
                funcao2.setSelected(clicado == funcao2);
                funcao3.setSelected(clicado == funcao3);
            }
        };

        funcao1.addItemListener(exclusivoFuncao);
        funcao2.addItemListener(exclusivoFuncao);
        funcao3.addItemListener(exclusivoFuncao);

        // ADICIONA AO PAINEL
        painelCadastro.add(funcao1);
        painelCadastro.add(funcao2);
        painelCadastro.add(funcao3);

        // Campo Status
        JLabel mensagemStatus = mensagem("Status", 18, Color.BLACK);
        mensagemStatus.setBounds(200, 180, 200, 30);
        painelCadastro.add(mensagemStatus);

        // CHECKBOX BONITOS
        JCheckBox status1 = checkBonito("Ativo");
        JCheckBox status2 = checkBonito("Inativo");

        // POSIÇÕES
        status1.setBounds(200, 220, 150, 30);
        status2.setBounds(200, 250, 150, 30);

        // SISTEMA DE EXCLUSIVIDADE
        ItemListener exclusivoStatus = e -> {
            JCheckBox clicado = (JCheckBox) e.getSource();

            if (clicado.isSelected()) {
                status1.setSelected(clicado == status1);
                status2.setSelected(clicado == status2);
            }
        };

        status1.addItemListener(exclusivoStatus);
        status2.addItemListener(exclusivoStatus);

        // ADICIONA AO PAINEL
        painelCadastro.add(status1);
        painelCadastro.add(status2);

        // Botão de Sair e Salvar
        JButton botaoSair = criarBotao("Sair", 100, 350, 200, 40);
        JButton botaoSalvar = criarBotao("Atualizar", 350, 350, 200, 40);

        botaoSair.setBackground(new Color(115, 115, 115));
        botaoSalvar.setBackground(new Color(57, 152, 255));

        botaoSair.addActionListener(e -> {
            main.dispose();
        });

        botaoSalvar.addActionListener(e -> {

            // 1. Solicita o ID via popup
            int id = solicitarIdProfissional();

            if (id == -1) {
                JOptionPane.showMessageDialog(null, "Atualização cancelada!");
                return;
            }

            // 2. Coleta função e status
            String funcao = getFuncaoSelecionada(funcao1, funcao2, funcao3);
            boolean status = getSelecionado(status1, status2);

            // 3. Chama o controller
            ProfissionalController update = new ProfissionalController();
            update.atualizarProfissional(
                    id,
                    campoNome.getText(),
                    campoContato.getText(),
                    funcao,
                    status
            );

            JOptionPane.showMessageDialog(null, "Profissional atualizado com sucesso!");
        });


        painelCadastro.add(botaoSair);
        painelCadastro.add(botaoSalvar);

        // Adiciona o painel dentro do banner
        bannerPrincipal.add(painelCadastro);

        // Adiciona o banner como fundo da tela
        updateView.add(bannerPrincipal);

        return updateView;
    }

    public static JPanel deleteProfissional() {
        JPanel deleteProfissional = new JPanel();
        deleteProfissional.add(new JLabel("TELA DELETE"));
        return deleteProfissional;
    }


    // ---------- TROCA DE TELAS ----------
    public static void initView(String tela) {

        if (main == null) return;

        if (currentView != null) {
            main.remove(currentView);
        }

        switch (tela.toLowerCase()) {
            case "create":
                currentView = createProfissional();
                break;
            case "reader":
                currentView = readerProfissional();
                break;
            case "update":
                currentView = updateProfissional();
                break;
            case "delete":
                currentView = deleteProfissional();
                break;
            default:
                currentView = new JPanel();
                currentView.add(new JLabel("Tela não encontrada: " + tela));
        }

        main.add(currentView, BorderLayout.CENTER);
        main.revalidate();
        main.repaint();
    }

    // ---------- MAIN ----------
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            main = new JFrame("Profissional - Sistema");
            main.setSize(700, 500);
            main.setLayout(new BorderLayout());
            main.setLocationRelativeTo(null);
            main.setResizable(false);
            main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            main.setVisible(true);

            initView("update"); // tela inicial
        });
    }
}
