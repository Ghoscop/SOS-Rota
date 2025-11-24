package com.example.sosrota.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class ContactController {
    @FXML
    private Label txtSobreNos, txtLogIn;
    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
        initialize();
    }

    @FXML
    public void initialize() {

        txtSobreNos.setOnMouseClicked(event -> abrirTela("abaoutUs.fxml"));
        txtLogIn.setOnMouseClicked(event -> abrirTela("logIn.fxml"));
    }

    private void abrirTela(String arquivo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sosrota/" + arquivo));
            Parent root = loader.load();
            Stage stageAtual = (Stage) txtLogIn.getScene().getWindow();
            Object controller = loader.getController();

            if (controller instanceof LogInController logCtrl) {
                logCtrl.setStage(stageAtual);
            }
            if (controller instanceof ContactController ctCtrl) {
                ctCtrl.setStage(stageAtual);
            }

            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/com/example/sosrota/All.css").toExternalForm());

            stageAtual.setScene(scene);
            stageAtual.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
