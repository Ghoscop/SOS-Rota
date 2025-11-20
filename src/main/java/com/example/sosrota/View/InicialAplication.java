package com.example.sosrota.View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class InicialAplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(InicialAplication.class.getResource("/com/example/sosrota/logIn.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(InicialAplication.class.getResource("/com/example/sosrota/All.css").toExternalForm());

        // --- CONFIGURAÇÕES DE TAMANHO PADRÃO ---
        double minWidth = 1000;
        double minHeight = 600;

        stage.setMinWidth(minWidth);
        stage.setMinHeight(minHeight);
        stage.setResizable(false);

        stage.setTitle("SOS-Rota");

        // ÍCONE CORRIGIDO
        stage.getIcons().add(
                new Image(InicialAplication.class
                        .getResource("/images/Icon.png")
                        .toExternalForm())
        );

        // --- MOVIMENTO DA JANELA QUANDO NÃO FULLSCREEN ---
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
