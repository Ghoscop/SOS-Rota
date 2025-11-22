package com.example.sosrota.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import javafx.stage.Stage;

import java.io.IOException;

public class LogInController {

    @FXML
    private TextField tfEmail, tfSenha;
    @FXML
    private Button btnLogIn;
    @FXML
    private ImageView btnVerSenha;
    @FXML private Label txtSobreNos, txtContato, txtCadastrarSe;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    @FXML
    public void initialize() {

        btnLogIn.setOnAction(event -> {

        });

        txtContato.setOnMouseClicked(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sosrota/contact.fxml"));
                Parent root = loader.load();
                ContactController contactController = loader.getController();
                stage = (Stage) txtContato.getScene().getWindow();
                contactController.setStage(stage);
                Scene scene = new Scene(root);
                scene.getStylesheets().add(getClass().getResource("/com/example/sosrota/All.css").toExternalForm());
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        txtSobreNos.setOnMouseClicked(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sosrota/abaoutUs.fxml"));
                Parent root = loader.load();
                AboutUsController aboutUsController = loader.getController();
                stage = (Stage) txtSobreNos.getScene().getWindow();
                aboutUsController.setStage(stage);
                Scene scene = new Scene(root);
                scene.getStylesheets().add(getClass().getResource("/com/example/sosrota/All.css").toExternalForm());
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        txtCadastrarSe.setOnMouseClicked(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sosrota/signUp.fxml"));
                Parent root = loader.load();
                SingUpController singUpController = loader.getController();
                stage = (Stage) txtCadastrarSe.getScene().getWindow();
                singUpController.setStage(stage);
                Scene scene = new Scene(root);
                scene.getStylesheets().add(getClass().getResource("/com/example/sosrota/All.css").toExternalForm());
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
