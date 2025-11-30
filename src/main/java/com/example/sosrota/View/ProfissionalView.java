package com.example.sosrota.View;

import com.example.sosrota.Controller.ProfissionalController;
import com.example.sosrota.Model.VO.ProfissionalVO;
import static com.example.sosrota.View.InterfaceView.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

public class ProfissionalView {

    private static JFrame main;
    private static JPanel currentView;
    public static ProfissionalController profissional = new ProfissionalController();

    public ProfissionalView(String tela) {
        SwingUtilities.invokeLater(() -> {
            main = new JFrame("Profissional - Sistema");
            main.setSize(700, 500);
            main.setLayout(new BorderLayout());
            main.setLocationRelativeTo(null);
            main.setResizable(false);
            main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            main.setVisible(true);

            try {
                initView(tela);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    // ------------------ CREATE ------------------

    public static JPanel createProfissional() {

        JPanel createView = new JPanel(null);
        createView.setBackground(Color.WHITE);
        createView.setPreferredSize(new Dimension(700, 500));

        JPanel bannerPrincipal = new JPanel(null);
        bannerPrincipal.setBounds(0, 0, 700, 500);
        bannerPrincipal.setBackground(new Color(0x0055CC));

        JLabel mensagemProfissional = mensagem("Criar Profissional", 24, Color.WHITE);
        mensagemProfissional.setBounds(240, 10, 300, 40);
        bannerPrincipal.add(mensagemProfissional);

        JPanel painelCadastro = new JPanel(null);
        painelCadastro.setBounds(20, 55, 650, 400);
        painelCadastro.setBackground(Color.WHITE);
        painelCadastro.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));

        JLabel imagem = carregarImagem("IconProfissional.png", 180, 180);
        imagem.setBounds(420, 30, 180, 180);
        painelCadastro.add(imagem);

        JLabel mensagemNome = mensagem("Nome:", 18, Color.BLACK);
        mensagemNome.setBounds(20, 20, 200, 30);
        painelCadastro.add(mensagemNome);

        JTextField campoNome = new JTextField();
        campoNome.setBackground(new Color(231, 231, 231));
        campoNome.setBounds(20, 55, 300, 30);
        painelCadastro.add(campoNome);

        JLabel mensagemContato = mensagem("Contato:", 18, Color.BLACK);
        mensagemContato.setBounds(20, 90, 200, 30);
        painelCadastro.add(mensagemContato);

        JTextField campoContato = new JTextField();
        campoContato.setBackground(new Color(231, 231, 231));
        campoContato.setBounds(20, 125, 300, 30);
        painelCadastro.add(campoContato);

        JLabel mensagemFuncao = mensagem("Função", 18, Color.BLACK);
        mensagemFuncao.setBounds(20, 180, 200, 30);
        painelCadastro.add(mensagemFuncao);

        JCheckBox funcao1 = checkBonito("Médico");
        JCheckBox funcao2 = checkBonito("Enfermeiro");
        JCheckBox funcao3 = checkBonito("Condutor");

        funcao1.setBounds(20, 220, 150, 30);
        funcao2.setBounds(20, 250, 150, 30);
        funcao3.setBounds(20, 280, 150, 30);

        ActionListener exclusivoFuncao = e -> {
            JCheckBox clicado = (JCheckBox) e.getSource();
            funcao1.setSelected(clicado == funcao1);
            funcao2.setSelected(clicado == funcao2);
            funcao3.setSelected(clicado == funcao3);
        };

        funcao1.addActionListener(exclusivoFuncao);
        funcao2.addActionListener(exclusivoFuncao);
        funcao3.addActionListener(exclusivoFuncao);

        painelCadastro.add(funcao1);
        painelCadastro.add(funcao2);
        painelCadastro.add(funcao3);

        JLabel mensagemStatus = mensagem("Status", 18, Color.BLACK);
        mensagemStatus.setBounds(200, 180, 200, 30);
        painelCadastro.add(mensagemStatus);

        JCheckBox status1 = checkBonito("Ativo");
        JCheckBox status2 = checkBonito("Inativo");

        status1.setBounds(200, 220, 150, 30);
        status2.setBounds(200, 250, 150, 30);

        ActionListener exclusivoStatus = e -> {
            JCheckBox clicado = (JCheckBox) e.getSource();
            status1.setSelected(clicado == status1);
            status2.setSelected(clicado == status2);
        };

        status1.addActionListener(exclusivoStatus);
        status2.addActionListener(exclusivoStatus);

        painelCadastro.add(status1);
        painelCadastro.add(status2);

        JButton botaoSair = criarBotao("Sair", 100, 350, 200, 40);
        botaoSair.setBackground(new Color(115, 115, 115));
        botaoSair.addActionListener(e -> main.dispose());

        JButton botaoSalvar = criarBotao("Salvar", 350, 350, 200, 40);
        botaoSalvar.setBackground(new Color(126, 217, 87));
        botaoSalvar.addActionListener(e -> {
            profissional.cadastrarProfissional(
                    campoNome.getText(),
                    campoContato.getText(),
                    getFuncaoSelecionada(funcao1, funcao2, funcao3),
                    getSelecionado(status1, status2)
            );
            JOptionPane.showMessageDialog(null, "Profissional criado com sucesso");
            main.dispose();
        });

        painelCadastro.add(botaoSair);
        painelCadastro.add(botaoSalvar);
        bannerPrincipal.add(painelCadastro);
        createView.add(bannerPrincipal);

        return createView;
    }

    // ------------------ READER ------------------

    public static JPanel readerProfissional() throws Exception {

        JPanel readerView = new JPanel(null);
        readerView.setBackground(Color.WHITE);
        readerView.setPreferredSize(new Dimension(700, 500));

        JPanel bannerPrincipal = new JPanel(null);
        bannerPrincipal.setBounds(0, 0, 700, 500);
        bannerPrincipal.setBackground(new Color(0x0055CC));

        JLabel mensagemProfissional = mensagem("Visualizar Profissionais", 24, Color.WHITE);
        mensagemProfissional.setBounds(230, 10, 300, 40);
        bannerPrincipal.add(mensagemProfissional);

        JPanel painelReader = new JPanel(null);
        painelReader.setBounds(20, 55, 650, 400);
        painelReader.setBackground(Color.WHITE);
        painelReader.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));

        String[] colunas = { "ID", "Nome", "Contato", "Função", "Status", "Equipe" };

        DefaultTableModel modelo = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        List<ProfissionalVO> lista = profissional.listarProfissionais();

        for (ProfissionalVO p : lista) {
            modelo.addRow(new Object[]{
                    p.getId(),
                    p.getNome(),
                    p.getContato(),
                    p.getFuncao(),
                    p.getAtivo() ? "Ativo" : "Inativo",
                    "Nenhuma"
            });
        }

        JTable tabela = new JTable(modelo);
        tabela.setRowHeight(28);
        tabela.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tabela.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(20, 50, 600, 280);

        painelReader.add(scroll);

        JButton botaoSair = criarBotao("Sair", 225, 340, 200, 40);
        botaoSair.setBackground(new Color(115, 115, 115));
        botaoSair.addActionListener(e -> main.dispose());
        painelReader.add(botaoSair);

        bannerPrincipal.add(painelReader);
        readerView.add(bannerPrincipal);

        return readerView;
    }

    // ------------------ UPDATE ------------------

    public static JPanel updateProfissional() {
        int id = solicitarIdProfissional();

        if (id == -1) {
            JOptionPane.showMessageDialog(null, "Atualização cancelada!");
            main.dispose();
        }

        JPanel updateView = new JPanel(null);
        updateView.setBackground(Color.WHITE);
        updateView.setPreferredSize(new Dimension(700, 500));

        JPanel bannerPrincipal = new JPanel(null);
        bannerPrincipal.setBounds(0, 0, 700, 500);
        bannerPrincipal.setBackground(new Color(0x0055CC));

        JLabel mensagemProfissional = mensagem("Atualizar Profissional", 24, Color.WHITE);
        mensagemProfissional.setBounds(230, 10, 300, 40);
        bannerPrincipal.add(mensagemProfissional);

        JPanel painelCadastro = new JPanel(null);
        painelCadastro.setBounds(20, 55, 650, 400);
        painelCadastro.setBackground(Color.WHITE);
        painelCadastro.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));

        JLabel imagem = carregarImagem("IconProfissional.png", 180, 180);
        imagem.setBounds(420, 30, 180, 180);
        painelCadastro.add(imagem);

        JLabel mensagemNome = mensagem("Novo Nome:", 18, Color.BLACK);
        mensagemNome.setBounds(20, 20, 200, 30);
        painelCadastro.add(mensagemNome);

        JTextField campoNome = new JTextField();
        campoNome.setBounds(20, 55, 300, 30);
        campoNome.setBackground(new Color(231, 231, 231));
        painelCadastro.add(campoNome);

        JLabel mensagemContato = mensagem("Novo Contato:", 18, Color.BLACK);
        mensagemContato.setBounds(20, 90, 200, 30);
        painelCadastro.add(mensagemContato);

        JTextField campoContato = new JTextField();
        campoContato.setBounds(20, 125, 300, 30);
        campoContato.setBackground(new Color(231, 231, 231));
        painelCadastro.add(campoContato);

        JLabel mensagemFuncao = mensagem("Nova Função", 18, Color.BLACK);
        mensagemFuncao.setBounds(20, 180, 200, 30);
        painelCadastro.add(mensagemFuncao);

        JCheckBox funcao1 = checkBonito("Médico");
        JCheckBox funcao2 = checkBonito("Enfermeiro");
        JCheckBox funcao3 = checkBonito("Condutor");

        funcao1.setBounds(20, 220, 150, 30);
        funcao2.setBounds(20, 250, 150, 30);
        funcao3.setBounds(20, 280, 150, 30);

        ActionListener exclusivoFuncao = e -> {
            JCheckBox clicado = (JCheckBox) e.getSource();
            funcao1.setSelected(clicado == funcao1);
            funcao2.setSelected(clicado == funcao2);
            funcao3.setSelected(clicado == funcao3);
        };

        funcao1.addActionListener(exclusivoFuncao);
        funcao2.addActionListener(exclusivoFuncao);
        funcao3.addActionListener(exclusivoFuncao);

        painelCadastro.add(funcao1);
        painelCadastro.add(funcao2);
        painelCadastro.add(funcao3);

        JLabel mensagemStatus = mensagem("Status", 18, Color.BLACK);
        mensagemStatus.setBounds(200, 180, 200, 30);
        painelCadastro.add(mensagemStatus);

        JCheckBox status1 = checkBonito("Ativo");
        JCheckBox status2 = checkBonito("Inativo");

        status1.setBounds(200, 220, 150, 30);
        status2.setBounds(200, 250, 150, 30);

        ItemListener exclusivoStatus = e -> {
            JCheckBox clicado = (JCheckBox) e.getSource();
            status1.setSelected(clicado == status1);
            status2.setSelected(clicado == status2);
        };

        status1.addItemListener(exclusivoStatus);
        status2.addItemListener(exclusivoStatus);

        painelCadastro.add(status1);
        painelCadastro.add(status2);

        JButton botaoSair = criarBotao("Sair", 100, 350, 200, 40);
        botaoSair.setBackground(new Color(115, 115, 115));
        botaoSair.addActionListener(e -> main.dispose());

        JButton botaoSalvar = criarBotao("Atualizar", 350, 350, 200, 40);
        botaoSalvar.setBackground(new Color(57, 152, 255));

        botaoSalvar.addActionListener(e -> {

            String funcao = getFuncaoSelecionada(funcao1, funcao2, funcao3);
            boolean status = getSelecionado(status1, status2);

            ProfissionalController update = new ProfissionalController();
            update.atualizarProfissional(
                    id,
                    campoNome.getText(),
                    campoContato.getText(),
                    funcao,
                    status
            );

            JOptionPane.showMessageDialog(null, "Profissional atualizado com sucesso!");
            main.dispose();
        });

        painelCadastro.add(botaoSair);
        painelCadastro.add(botaoSalvar);
        bannerPrincipal.add(painelCadastro);
        updateView.add(bannerPrincipal);

        return updateView;
    }

    // ------------------ DELETE ------------------

    public static JPanel deleteProfissional() throws Exception{

        JPanel deleteView = new JPanel(null);
        deleteView.setBackground(Color.WHITE);
        deleteView.setPreferredSize(new Dimension(700, 500));

        JPanel bannerPrincipal = new JPanel(null);
        bannerPrincipal.setBounds(0, 0, 700, 500);
        bannerPrincipal.setBackground(new Color(0x0055CC));

        JLabel mensagemProfissional = mensagem("Excluir Profissionais", 24, Color.WHITE);
        mensagemProfissional.setBounds(230, 10, 300, 40);
        bannerPrincipal.add(mensagemProfissional);

        JPanel painelDelete = new JPanel(null);
        painelDelete.setBounds(20, 55, 650, 400);
        painelDelete.setBackground(Color.WHITE);
        painelDelete.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));

        String[] colunas = { "Selecionar", "ID", "Nome", "Contato", "Função", "Status", "Equipe" };

        DefaultTableModel modelo = new DefaultTableModel(colunas, 0) {
            @Override
            public Class<?> getColumnClass(int column) {
                return column == 0 ? Boolean.class : String.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0;
            }
        };

        List<ProfissionalVO> lista = profissional.listarProfissionais();

        for (ProfissionalVO p : lista) {
            modelo.addRow(new Object[]{
                    false,
                    p.getId(),
                    p.getNome(),
                    p.getContato(),
                    p.getFuncao(),
                    p.getAtivo() ? "Ativo" : "Inativo",
                    "Nenhuma"
            });
        }

        JTable tabela = new JTable(modelo);
        tabela.setRowHeight(28);
        tabela.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tabela.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(20, 50, 600, 280);

        painelDelete.add(scroll);

        JButton botaoSair = criarBotao("Sair", 100, 350, 200, 40);
        botaoSair.setBackground(new Color(115, 115, 115));
        botaoSair.addActionListener(e -> main.dispose());

        JButton botaoExcluir = criarBotao("Excluir", 350, 350, 200, 40);
        botaoExcluir.setBackground(new Color(255, 0, 0));

        botaoExcluir.addActionListener(e -> {
            DefaultTableModel m = (DefaultTableModel) tabela.getModel();
            List<Integer> idsMarcados = new ArrayList<>();

            for (int i = 0; i < m.getRowCount(); i++) {
                boolean marcado = (Boolean) m.getValueAt(i, 0);

                if (marcado) {
                    int id = (Integer) m.getValueAt(i, 1);
                    idsMarcados.add(id);
                }
            }
            if (idsMarcados.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nenhum profissional selecionado.");
                return;
            }
            for (int id : idsMarcados) {
                profissional.excluirProfissional(id);
            }
            for (int i = m.getRowCount() - 1; i >= 0; i--) {
                boolean marcado = (Boolean) m.getValueAt(i, 0);
                if (marcado) {
                    m.removeRow(i);
                }
            }
            JOptionPane.showMessageDialog(null, "Profissionais excluídos com sucesso.");

        });

        painelDelete.add(botaoSair);
        painelDelete.add(botaoExcluir);

        bannerPrincipal.add(painelDelete);
        deleteView.add(bannerPrincipal);

        return deleteView;
    }


    // ------------------ TROCA DE TELAS ------------------

    public static void initView(String tela) throws Exception {
        if (main == null) return;
        if (currentView != null) main.remove(currentView);

        switch (tela.toLowerCase()) {
            case "create": currentView = createProfissional(); break;
            case "reader": currentView = readerProfissional(); break;
            case "update": currentView = updateProfissional(); break;
            case "delete": currentView = deleteProfissional(); break;
            default:
                currentView = new JPanel();
                currentView.add(new JLabel("Tela não encontrada: " + tela));
        }

        main.add(currentView, BorderLayout.CENTER);
        main.revalidate();
        main.repaint();
    }
}
