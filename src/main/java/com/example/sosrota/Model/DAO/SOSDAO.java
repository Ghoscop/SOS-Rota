package com.example.sosrota.Model.DAO;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.example.sosrota.Model.VO.Aresta;
import com.example.sosrota.Model.VO.Bairro;
import com.example.sosrota.Model.VO.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SOSDAO {

    private final Connection conexao = ConexaoDAO.getConnection();

    // --------------------------------------------
    // SQL das tabelas
    // --------------------------------------------
    private final String sqlCriarTabelaUsuario =
            "CREATE TABLE IF NOT EXISTS cadastro_usuario (" +
                    "id SERIAL PRIMARY KEY, " +
                    "nome VARCHAR(100) NOT NULL, " +
                    "sobrenome VARCHAR(100) NOT NULL, " +
                    "email VARCHAR(255) NOT NULL UNIQUE, " +
                    "senha VARCHAR(255) NOT NULL" +
                    ")";

    private final String sqlCriarTabelaBairro =
            "CREATE TABLE IF NOT EXISTS bairro (" +
                    "id SERIAL PRIMARY KEY, " +
                    "nome VARCHAR(100) NOT NULL" +
                    ")";

    private final String sqlCriarTabelaRuas =
            "CREATE TABLE IF NOT EXISTS ruas_conexoes (" +
                    "id SERIAL PRIMARY KEY, " +
                    "bairro_origem_id INT NOT NULL, " +
                    "bairro_destino_id INT NOT NULL, " +
                    "distancia_km NUMERIC(10,2) NOT NULL, " +
                    "FOREIGN KEY (bairro_origem_id) REFERENCES bairro(id), " +
                    "FOREIGN KEY (bairro_destino_id) REFERENCES bairro(id)" +
                    ")";

    private final String sqlCriarTabelaProfissional =
            "CREATE TABLE IF NOT EXISTS profissional (" +
                    "id SERIAL PRIMARY KEY, " +
                    "nome VARCHAR(100) NOT NULL, " +
                    "funcao VARCHAR(100) NOT NULL, " +
                    "contato VARCHAR(100) NOT NULL, " +
                    "ativo BOOLEAN DEFAULT FALSE" +
                    ")";

    // --------------------------------------------
    // CONSTRUTOR
    // --------------------------------------------
    public SOSDAO() throws SQLException {
        if (conexao != null) {
            criarTabela(sqlCriarTabelaUsuario);
            criarTabela(sqlCriarTabelaBairro);
            criarTabela(sqlCriarTabelaRuas);
            criarTabela(sqlCriarTabelaProfissional);
            System.out.println("[DAO] Banco Online");
        }
    }

    public void criarTabela(String sql) {
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao criar tabela: " + e.getMessage());
        }
    }

    // =====================================================
    //            IMPORTAÇÕES DE CSV
    // =====================================================

    // --------------------------------------------
    // Importa Bairros (bairros.csv)
    // --------------------------------------------
    public void importarBairros(File arquivo) {
        String sql = "INSERT INTO bairro (nome) VALUES (?)";

        try (
                BufferedReader br = new BufferedReader(new FileReader(arquivo));
                PreparedStatement stmt = conexao.prepareStatement(sql)
        ) {
            String linha;
            br.readLine(); // cabeçalho

            while ((linha = br.readLine()) != null) {

                String[] dados = linha.split(",");
                stmt.setString(1, dados[1]); // nome_bairro

                stmt.addBatch();
            }

            stmt.executeBatch();
            System.out.println("Importação de BAIRROS concluída!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // --------------------------------------------
    // Importa Ruas e Conexões (ruas_conexoes.csv)
    // --------------------------------------------
    public void importarRuas(File arquivo) {
        String sql = "INSERT INTO ruas_conexoes (bairro_origem_id, bairro_destino_id, distancia_km) " +
                "VALUES (?, ?, ?)";

        try (
                BufferedReader br = new BufferedReader(new FileReader(arquivo));
                PreparedStatement stmt = conexao.prepareStatement(sql)
        ) {
            String linha;
            br.readLine(); // cabeçalho

            while ((linha = br.readLine()) != null) {

                String[] dados = linha.split(",");

                stmt.setInt(1, Integer.parseInt(dados[1])); // origem
                stmt.setInt(2, Integer.parseInt(dados[2])); // destino
                stmt.setDouble(3, Double.parseDouble(dados[3])); // distancia

                stmt.addBatch();
            }

            stmt.executeBatch();
            System.out.println("Importação de RUAS concluída!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Bairro> listarBairros() {
        List<Bairro> lista = new ArrayList<>();

        String sql = "SELECT * FROM bairro";

        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            int pos = 0;

            // Tamanho REAL do Pane
            final double width = 513;
            final double height = 425;

            // Margens internas
            final double marginX = 50;
            final double marginY = 50;

            // >>> AUMENTE ESTE ESPAÇAMENTO PARA ESPALHAR MAIS <<<
            final double distX = 99;   // antes 70 → agora MUITO mais espaçado
            final double distY = 120;   // antes 55 → agora mais confortável

            while (rs.next()) {
                Bairro b = new Bairro();
                b.setId(rs.getInt("id"));
                b.setNome(rs.getString("nome"));

                int row = pos / 6;  // máximo 4 por linha
                int col = pos % 6;

                // Deslocamento alternado (efeito “mapa real”)
                double offsetY = (col % 2 == 0) ? 0 : 22;

                double x = marginX + col * distX;
                double y = marginY + row * distY + offsetY;

                // Mantém dentro do Pane
                if (x > width - 50)  x = width - 50;
                if (y > height - 40) y = height - 40;

                b.setX(x);
                b.setY(y);

                lista.add(b);
                pos++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public List<Aresta> listarRuas() {
        List<Aresta> lista = new ArrayList<>();

        String sql = "SELECT * FROM ruas_conexoes";

        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int origem  = rs.getInt("bairro_origem_id");
                int destino = rs.getInt("bairro_destino_id");
                double dist = rs.getDouble("distancia_km");

                lista.add(new Aresta(origem, destino, dist));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    // --------------------------------------------
    // Função inteligente: detecta o arquivo pelo cabeçalho
    // --------------------------------------------
    public void importarArquivo(File arquivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String header = br.readLine();

            if (header.contains("nome_bairro")) {
                importarBairros(arquivo);
            } else if (header.contains("bairro_origem_id")) {
                importarRuas(arquivo);
            } else {
                System.out.println("⚠ Tipo de arquivo CSV não reconhecido!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // =====================================================
    //                FUNÇÕES DE USUÁRIO
    // =====================================================

    public boolean cadastrarUsuario(User u) {
        String sql = "INSERT INTO cadastro_usuario (nome, sobrenome, email, senha) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getSobrenome());
            stmt.setString(3, u.getEmail());

            String hash = BCrypt.withDefaults()
                    .hashToString(12, u.getSenha().toCharArray());

            stmt.setString(4, hash);

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar: " + e.getMessage());
            return false;
        }
    }

    public User buscarPorEmail(String email) {
        String sql = "SELECT * FROM cadastro_usuario WHERE email = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("sobrenome"),
                        rs.getString("email"),
                        rs.getString("senha")
                );
            }

        } catch (SQLException e) {
            System.out.println("Erro login: " + e.getMessage());
        }

        return null;
    }
}
