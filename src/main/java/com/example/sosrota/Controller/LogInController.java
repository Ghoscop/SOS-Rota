package com.example.sosrota.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class LogInController {

    @FXML
    private TextField tfEmail, tfSenha;
    @FXML
    private Button btnLogIn;
    @FXML
    private ImageView btnVerSenha;
    @FXML
    private Text txtCadastrarSe, txtSobreNos, txtContato;

    @FXML
    public void initialize() {

        btnLogIn.setOnAction(event -> {

        });

        txtContato.setOnMouseClicked(event -> {
            // lógica aqui
        });
        txtSobreNos.setOnMouseClicked(event -> {
            // lógica aqui
        });
        txtCadastrarSe.setOnMouseClicked(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sosrota/signUp.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                scene.getStylesheets().add(getClass().getResource("/com/example/sosrota/All.css").toExternalForm());
                scene.setFill(Color.TRANSPARENT);
                Stage stage = (Stage) btnLogIn.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        btnVerSenha.setOnMouseClicked(event -> {
            // lógica aqui
        });
    }
}
