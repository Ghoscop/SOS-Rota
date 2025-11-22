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
    }
}
