package com.example.sosrota.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class AboutUsController {

    @FXML private Label txtLogIn, txtContato;
    private Stage stage;


    public void setStage(Stage stage) {
        this.stage = stage;
        initialize();
    }

    @FXML
    public void initialize() {
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
        txtLogIn.setOnMouseClicked(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/sosrota/logIn.fxml"));
                Parent root = loader.load();
                LogInController logInController = loader.getController();
                Stage stage = (Stage) txtLogIn.getScene().getWindow();
                logInController.setStage(stage);
                Scene scene = new Scene(root);
                scene.getStylesheets().add(getClass().getResource("/com/example/sosrota/All.css").toExternalForm());
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
