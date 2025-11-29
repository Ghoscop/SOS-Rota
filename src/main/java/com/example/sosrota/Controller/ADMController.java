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

public class ADMController {

    @FXML
    private Button btnImportarDados,btnRegistrarAmbulancia, btnRegistrarProfissional, btnRegistrarEquipe, btnSeeData, btnExcluirAmbulancia, btnExcluirProfissional, btnExcluirEquipe, btnExcluirTudo, btnExitADM;

    @FXML private Label txtLogIn;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    @FXML
    public void initialize() {

        btnImportarDados.setOnAction(event -> {

        });
        btnRegistrarAmbulancia.setOnAction(event -> {

        });
        btnRegistrarProfissional.setOnAction(event -> {

        });
        btnRegistrarEquipe.setOnAction(event -> {

        });
        btnSeeData.setOnAction(event -> {

        });
        btnExcluirAmbulancia.setOnAction(event -> {

        });
        btnExcluirProfissional.setOnAction(event -> {

        });
        btnExcluirEquipe.setOnAction(event -> {

        });
        btnExcluirTudo.setOnAction(event -> {

        });
        btnExitADM.setOnAction(event -> {

        });
    }
}
