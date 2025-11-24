package com.example.sosrota.Controller;

import com.example.sosrota.Model.DAO.SOSDAO;
import com.example.sosrota.Model.VO.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class SingUpController {

    @FXML private TextField tfNome, tfSobrenome, tfEmail;

    @FXML private PasswordField pfSenha;
    @FXML private PasswordField pfConfirmarSenha;

    @FXML private TextField tfSenhaVisivel;
    @FXML private TextField tfConfirmarSenhaVisivel;

    @FXML private ImageView btnVerSenha;      // ícone da primeira senha
    @FXML private ImageView btnVerSenha1;     // ícone da confirmação

    @FXML private Button btnCriarConta;
    @FXML private Label txtLogIn, txtSobreNos, txtContato;

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
        configurarToggleConfirmarSenha();

        btnCriarConta.setOnAction(event -> cadastrarUsuario());

        txtContato.setOnMouseClicked(event -> abrirTela("contact.fxml"));
        txtSobreNos.setOnMouseClicked(event -> abrirTela("abaoutUs.fxml"));
        txtLogIn.setOnMouseClicked(event -> abrirTela("logIn.fxml"));
    }

    private void configurarToggleSenha() {
        tfSenhaVisivel.setManaged(false);
        tfSenhaVisivel.setVisible(false);

        btnVerSenha.setOnMouseClicked(event -> {
            if (tfSenhaVisivel.isVisible()) {
                pfSenha.setText(tfSenhaVisivel.getText());
                tfSenhaVisivel.setVisible(false);
                tfSenhaVisivel.setManaged(false);
                pfSenha.setVisible(true);
                pfSenha.setManaged(true);
            } else {
                tfSenhaVisivel.setText(pfSenha.getText());
                pfSenha.setVisible(false);
                pfSenha.setManaged(false);
                tfSenhaVisivel.setVisible(true);
                tfSenhaVisivel.setManaged(true);
            }
        });
    }

    private void configurarToggleConfirmarSenha() {
        tfConfirmarSenhaVisivel.setManaged(false);
        tfConfirmarSenhaVisivel.setVisible(false);

        btnVerSenha1.setOnMouseClicked(event -> {
            if (tfConfirmarSenhaVisivel.isVisible()) {
                pfConfirmarSenha.setText(tfConfirmarSenhaVisivel.getText());
                tfConfirmarSenhaVisivel.setVisible(false);
                tfConfirmarSenhaVisivel.setManaged(false);
                pfConfirmarSenha.setVisible(true);
                pfConfirmarSenha.setManaged(true);
            } else {
                tfConfirmarSenhaVisivel.setText(pfConfirmarSenha.getText());
                pfConfirmarSenha.setVisible(false);
                pfConfirmarSenha.setManaged(false);
                tfConfirmarSenhaVisivel.setVisible(true);
                tfConfirmarSenhaVisivel.setManaged(true);
            }
        });
    }

    private void cadastrarUsuario() {

        String nome = tfNome.getText();
        String sobrenome = tfSobrenome.getText();
        String email = tfEmail.getText();

        String senha = pfSenha.isVisible() ? pfSenha.getText() : tfSenhaVisivel.getText();
        String confirmar = pfConfirmarSenha.isVisible() ? pfConfirmarSenha.getText() : tfConfirmarSenhaVisivel.getText();

        if (nome.isBlank() || sobrenome.isBlank() || email.isBlank() || senha.isBlank()) {
            mostrarMensagem("Preencha todos os campos!");
            mostrarAlerta("Erro", "Preencha todos os campos!", Alert.AlertType.WARNING);
            return;
        }

        if (!senha.equals(confirmar)) {
            mostrarMensagem("As senhas não coincidem!");
            mostrarAlerta("Erro", "As senhas não coincidem!", Alert.AlertType.WARNING);
            return;
        }

        User usuario = new User();
        usuario.setNome(nome);
        usuario.setSobrenome(sobrenome);
        usuario.setEmail(email);
        usuario.setSenha(senha);

        boolean ok = dao.cadastrarUsuario(usuario);

        if (ok) {
            mostrarMensagem("Conta criada com sucesso!");
            mostrarAlerta("Sucesso", "Conta criada com sucesso!", Alert.AlertType.INFORMATION);
            abrirTela("logIn.fxml");
        } else {
            mostrarMensagem("Erro ao criar conta! Email já existe?");
            mostrarAlerta("Erro", "Erro ao criar conta! Email já existe?", Alert.AlertType.WARNING);
        }
    }

    private void mostrarMensagem(String msg) {
        System.out.println("[CADASTRO] " + msg);
    }

    private void abrirTela(String arquivo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sosrota/" + arquivo));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/com/example/sosrota/All.css").toExternalForm());

            stage = (Stage) tfNome.getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void mostrarAlerta(String titulo, String mensagem, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

}
