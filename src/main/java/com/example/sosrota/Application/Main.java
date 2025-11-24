package com.example.sosrota.Application;

import com.example.sosrota.View.InicialAplication;
import com.example.sosrota.Model.DAO.SOSDAO;
import javafx.application.Application;

import java.sql.SQLException;

public class Main {
     public static void main(String[] args) throws SQLException {
         Application.launch(InicialAplication.class, args);
     }


}

