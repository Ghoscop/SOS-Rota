package com.example.sosrota.Controller;

import com.example.sosrota.Model.BO.SOSBO;
import com.example.sosrota.Model.DAO.SOSDAO;
import com.example.sosrota.Model.VO.User;
import at.favre.lib.crypto.bcrypt.BCrypt;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.IOException;

public class LogInController {

    @FXML private TextField tfEmail;

    @FXML private PasswordField pfSenha;
    @FXML private TextField tfSenhaVisivel;

    @FXML private Button btnLogIn;
    @FXML private ImageView btnVerSenha;
    @FXML private Label txtSobreNos, txtContato, txtCadastrarSe;

    private Stage stage;
    private SOSDAO dao;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void initialize() {

        try {
            dao = new SOSDAO();
        } catch (Exception ignored) {}

        configurarToggleSenha();

        btnLogIn.setOnAction(event -> fazerLogin());

        txtContato.setOnMouseClicked(event -> abrirTela("contact.fxml"));
        txtSobreNos.setOnMouseClicked(event -> abrirTela("abaoutUs.fxml"));
        txtCadastrarSe.setOnMouseClicked(event -> abrirTela("signUp.fxml"));
    }

    private void configurarToggleSenha() {
        tfSenhaVisivel.setManaged(false);
        tfSenhaVisivel.setVisible(false);

        btnVerSenha.setOnMouseClicked(event -> {

            if (tfSenhaVisivel.isVisible()) {
                // voltar ao normal
                pfSenha.setText(tfSenhaVisivel.getText());
                tfSenhaVisivel.setVisible(false);
                tfSenhaVisivel.setManaged(false);

                pfSenha.setVisible(true);
                pfSenha.setManaged(true);

            } else {
                // mostrar senha
                tfSenhaVisivel.setText(pfSenha.getText());
                pfSenha.setVisible(false);
                pfSenha.setManaged(false);

                tfSenhaVisivel.setVisible(true);
                tfSenhaVisivel.setManaged(true);
            }
        });
    }

    private void fazerLogin() {

        String email = tfEmail.getText();
        String senha = pfSenha.isVisible() ? pfSenha.getText() : tfSenhaVisivel.getText();

        if (email.isBlank() || senha.isBlank()) {
            mostrarMensagem("Preencha os campos!");
            //Pop-up
            mostrarAlerta("Erro", "Campos obrigatórios! Preencha todos os campos!", Alert.AlertType.WARNING);
            return;
        }

        // LOGIN ADMIN (.env)
        if (SOSBO.loginValidoADM(email, senha)) {
            abrirTela("adm.fxml");
            mostrarAlerta("Login ADM efetuado", "Bem Vindo, ADM!", Alert.AlertType.WARNING);
            return;
        }

        // LOGIN NORMAL
        User usuario = dao.buscarPorEmail(email);

        if (usuario == null) {
            mostrarMensagem("Email não encontrado!");
            mostrarAlerta("Erro", "Email não encontrado", Alert.AlertType.WARNING);
            return;
        }

        BCrypt.Result result = BCrypt.verifyer().verify(
                senha.toCharArray(),
                usuario.getSenha()
        );

        if (result.verified) {
            abrirTela("user.fxml");
        } else {
            mostrarMensagem("Senha incorreta!");
            mostrarAlerta("Erro", "Senha incorreta!", Alert.AlertType.WARNING);
        }
    }

    private void abrirTela(String arquivo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sosrota/" + arquivo));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/com/example/sosrota/All.css").toExternalForm());

            stage = (Stage) tfEmail.getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void mostrarMensagem(String msg) {
        System.out.println("[LOGIN] " + msg);
    }

    private void mostrarAlerta(String titulo, String mensagem, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}

