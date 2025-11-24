package com.example.sosrota.Model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.example.sosrota.Model.BO.EnvConfig;


// Conectatr BD postgree
public class ConexaoDAO {

    private static String URL;
    private static String USER;
    private static String PASSWORD;

    // Assim ele carrega as variaveis ao iniciar a classe
    static {
        URL = EnvConfig.get("DB_URL");
        USER = EnvConfig.get("DB_USER");
        PASSWORD = EnvConfig.get("DB_PASSWORD");
    }

    public static Connection getConnection() throws SQLException {
        try {
        return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco de dados");
        }
    }
}
