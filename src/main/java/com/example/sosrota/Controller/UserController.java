package com.example.sosrota.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class UserController {

    @FXML private Label txtSair;

    @FXML private ChoiceBox cbOcorrencias;

    @FXML private Pane pnGrafo;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void initialize(){
        cbOcorrencias.getItems().add("Leve");
        cbOcorrencias.getItems().add("Medio");
        cbOcorrencias.getItems().add("Grave");
    }

}
