package com.example.sosrota.Controller;

import com.example.sosrota.Model.DAO.SOSDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class ADMController {

    @FXML
    private Button btnImportarDados, btnRegistrarAmbulancia, btnRegistrarProfissional, btnRegistrarEquipe, btnSeeData, btnExcluirAmbulancia, btnExcluirProfissional, btnExcluirEquipe, btnExcluirTudo, btnExitADM;

    @FXML
    private Label txtLogIn;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void initialize() {

        btnImportarDados.setOnAction(event -> {

            FileChooser chooser = new FileChooser();
            chooser.setTitle("Selecione o arquivo CSV");

            File arquivo = chooser.showOpenDialog(null);

            if (arquivo != null) {
                try {
                    SOSDAO dao = new SOSDAO();
                    dao.importarArquivo(arquivo);  // <-- AGORA É AUTOMÁTICO
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
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

        txtLogIn.setOnMouseClicked(event -> abrirTela("logIn.fxml"));

        btnExitADM.setOnAction(event -> {
            abrirTela("logIn.fxml");
        });
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