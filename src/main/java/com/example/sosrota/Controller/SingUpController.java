package com.example.sosrota.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class SingUpController {

    @FXML private TextField tfNomeCompleto, tfEmail, tfSenha, tfConfirmarSenha;
    @FXML private Button btnCriarConta;
    @FXML private ImageView btnVerSenha, btnVerSenha1;
    @FXML private Text txtLogIn, txtSobreNos, txtContato;

    @FXML
    public void initialize() {
        btnCriarConta.setOnAction(event -> {
            System.out.println("Criar conta clicado!");
            // Aqui vai lógica de cadastro
        });

        txtContato.setOnMouseClicked(event -> {
            System.out.println("Contato clicado!");
        });

        txtSobreNos.setOnMouseClicked(event -> {
            System.out.println("Sobre nós clicado!");
        });

        txtLogIn.setOnMouseClicked(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sosrota/logIn.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                scene.getStylesheets().add(getClass().getResource("/com/example/sosrota/All.css").toExternalForm());
                Stage stage = (Stage) txtLogIn.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        btnVerSenha.setOnMouseClicked(event -> {
            System.out.println("Ver senha clicado!");
            // lógica para mostrar/esconder senha
        });
    }
}
