package com.example.sosrota.Model.DAO;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.example.sosrota.Model.VO.User;

import java.sql.*;

public class SOSDAO {

    private final Connection conexao = ConexaoDAO.getConnection();

    private final String sqlCriarTabelaCadastroUsuario =
            "CREATE TABLE IF NOT EXISTS public.cadastro_usuario (" +
                    "id SERIAL PRIMARY KEY," +
                    "nome VARCHAR(100) NOT NULL," +
                    "sobrenome VARCHAR(100) NOT NULL," +
                    "email VARCHAR(255) NOT NULL UNIQUE," +
                    "senha VARCHAR(255) NOT NULL" +
                    ")";

    public SOSDAO() throws SQLException {
        if (conexao != null) {
            criarTabela(sqlCriarTabelaCadastroUsuario);
            System.out.println("[DAO] Banco Online!");
        }
    }

    public void criarTabela(String sql) {
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao criar tabela: " + e.getMessage());
        }
    }

    // ------------------------------------------
    // SALVAR USUÁRIO
    // ------------------------------------------
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
