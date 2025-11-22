package com.example.sosrota.View;

import com.example.sosrota.Controller.LogInController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class InicialAplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(InicialAplication.class.getResource("/com/example/sosrota/logIn.fxml"));
        Parent root = fxmlLoader.load();
        LogInController controller = fxmlLoader.getController();
        controller.setStage(stage);
        Scene scene = new Scene(root);
        scene.getStylesheets().add(InicialAplication.class.getResource("/com/example/sosrota/All.css").toExternalForm());

        double minWidth = 1000;
        double minHeight = 600;

        stage.setMinWidth(minWidth);
        stage.setMinHeight(minHeight);
        stage.setResizable(false);
        stage.setTitle("SOS-Rota");
        controller.setStage(stage);

        stage.getIcons().add(
                new Image(InicialAplication.class
                        .getResource("/images/Icon.png")
                        .toExternalForm())
        );

        enableWindowDrag(stage, scene);

        stage.setScene(scene);
        stage.show();
    }

    private double xOffset = 0;
    private double yOffset = 0;

    private void enableWindowDrag(Stage stage, Scene scene) {

        scene.setOnMousePressed(event -> {
            if (!stage.isFullScreen()) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });

        scene.setOnMouseDragged(event -> {
            if (!stage.isFullScreen()) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });
    }
}
