package com.example.sosrota.Controller;

import com.example.sosrota.Model.VO.Bairro;
import com.example.sosrota.Model.VO.Aresta;
import com.example.sosrota.Model.VO.Graph;
import com.example.sosrota.Model.DAO.SOSDAO;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class UserController {

    @FXML
    private Label txtSair;

    @FXML
    private ChoiceBox cbOcorrencias;

    @FXML
    private Pane pnGrafo;

    private Stage stage;

    private Graph grafo;


    private List<Integer> basesAmbulancia = List.of(1, 4, 7);

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    public void initialize() throws SQLException {
        cbOcorrencias.getItems().addAll("Leve","Medio","Grave");

        txtSair.setOnMouseClicked(event -> abrirTela("logIn.fxml"));

        carregarDadosBanco();
        desenharGrafo();
        configurarCliqueNoMapa();
    }

    // ----------------------------------------------------------
    // CARREGAR DADOS DO BANCO
    // ----------------------------------------------------------

    private void carregarDadosBanco() throws SQLException {
        SOSDAO dao = new SOSDAO();

        List<Bairro> bairros = dao.listarBairros();
        List<Aresta> ruas    = dao.listarRuas();

        grafo = new Graph();

        for (Bairro b : bairros)
            grafo.addBairro(b);

        for (Aresta r : ruas)
            grafo.addAresta(r.origem, r.destino, r.peso);
    }
    // ----------------------------------------------------------
    // DESENHAR GRAFO
    // ----------------------------------------------------------

    private void desenharGrafo() {
        pnGrafo.getChildren().clear();

        // Ruas
        for (var entry : grafo.getAdj().entrySet()) {
            Bairro b1 = grafo.getBairro(entry.getKey());
            for (Aresta ar : entry.getValue()) {
                Bairro b2 = grafo.getBairro(ar.destino);

                Line l = new Line(b1.getX(), b1.getY(), b2.getX(), b2.getY());
                l.setStroke(Color.GRAY);
                l.setStrokeWidth(2);

                pnGrafo.getChildren().add(l);
            }
        }

        // Bairros
        for (Bairro b : grafo.getBairros().values()) {
            Circle c = new Circle(b.getX(), b.getY(), 7);
            c.setFill(Color.DODGERBLUE);

            Label lbl = new Label(b.getNome());
            lbl.setLayoutX(b.getX() + 8);
            lbl.setLayoutY(b.getY() - 12);

            pnGrafo.getChildren().addAll(c, lbl);
        }

        // Ambulâncias
        for (int id : basesAmbulancia) {
            Bairro b = grafo.getBairro(id);
            Circle c = new Circle(b.getX(), b.getY(), 10);
            c.setFill(Color.GREEN);
            pnGrafo.getChildren().add(c);
        }
    }
    // ----------------------------------------------------------
    // CLIQUE DO USUÁRIO NO MAPA
    // ----------------------------------------------------------

    private void configurarCliqueNoMapa() {

        pnGrafo.setOnMouseClicked(e -> {

            Bairro destino = acharBairroMaisProximo(e.getX(), e.getY());
            if (destino == null) return;

            int amb = escolherAmbulancia(destino.getId());
            List<Integer> caminho = dijkstra(amb, destino.getId());

            desenharRota(caminho);
        });
    }
    // ----------------------------------------------------------
    // LÓGICA DO CLIQUE
    // ----------------------------------------------------------

    private Bairro acharBairroMaisProximo(double x, double y) {
        double menorDist = Double.MAX_VALUE;
        Bairro melhor = null;

        for (Bairro b : grafo.getBairros().values()) {
            double d = Math.hypot(b.getX() - x, b.getY() - y);
            if (d < menorDist) {
                menorDist = d;
                melhor = b;
            }
        }
        return melhor;
    }

    private int escolherAmbulancia(int destino) {

        double menor = Double.MAX_VALUE;
        int melhorID = basesAmbulancia.get(0);

        for (int amb : basesAmbulancia) {

            List<Integer> caminho = dijkstra(amb, destino);
            double dist = calcularDistanciaDaRota(caminho);

            if (dist < menor) {
                menor = dist;
                melhorID = amb;
            }
        }
        return melhorID;
    }


    private double calcularDistanciaDaRota(List<Integer> caminho) {
        double total = 0;

        for (int i = 0; i < caminho.size() - 1; i++) {
            int a = caminho.get(i);
            int b = caminho.get(i + 1);

            for (Aresta ar : grafo.getAdj().get(a)) {
                if (ar.destino == b) {
                    total += ar.peso;
                    break;
                }
            }
        }
        return total;
    }
    // ----------------------------------------------------------
    // DIJKSTRA
    // ----------------------------------------------------------

    private List<Integer> dijkstra(int origem, int destino) {

        Map<Integer, Double> dist = new HashMap<>();
        Map<Integer, Integer> pai = new HashMap<>();

        PriorityQueue<int[]> pq =
                new PriorityQueue<>(Comparator.comparingDouble(a -> a[1]));

        for (Integer b : grafo.getBairros().keySet())
            dist.put(b, Double.MAX_VALUE);

        dist.put(origem, 0.0);
        pq.add(new int[]{origem, 0});

        while (!pq.isEmpty()) {
            int[] atual = pq.poll();
            int u = atual[0];

            if (u == destino) break;

            for (Aresta a : grafo.getAdj().get(u)) {

                double novaDist = dist.get(u) + a.peso;

                if (novaDist < dist.get(a.destino)) {
                    dist.put(a.destino, novaDist);
                    pai.put(a.destino, u);
                    pq.add(new int[]{a.destino, (int) novaDist});
                }
            }
        }

        List<Integer> caminho = new ArrayList<>();
        Integer atual = destino;

        while (atual != null) {
            caminho.add(atual);
            atual = pai.get(atual);
        }

        Collections.reverse(caminho);
        return caminho;
    }


    // ----------------------------------------------------------
    // DESENHAR ROTA
    // ----------------------------------------------------------

    private void desenharRota(List<Integer> caminho) {

        pnGrafo.getChildren().removeIf(n -> "rota".equals(n.getUserData()));

        for (int i = 0; i < caminho.size() - 1; i++) {

            Bairro b1 = grafo.getBairro(caminho.get(i));
            Bairro b2 = grafo.getBairro(caminho.get(i + 1));

            Line line = new Line(b1.getX(), b1.getY(), b2.getX(), b2.getY());
            line.setStroke(Color.YELLOW);
            line.setStrokeWidth(6);
            line.setUserData("rota");

            pnGrafo.getChildren().add(line);
        }
    }

    private void abrirTela(String arquivo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/com/example/sosrota/" + arquivo));
            Parent root = loader.load();
            Stage stageAtual = (Stage) txtSair.getScene().getWindow();

            Object controller = loader.getController();

            if (controller instanceof LogInController logCtrl)
                logCtrl.setStage(stageAtual);

            if (controller instanceof ContactController ctCtrl)
                ctCtrl.setStage(stageAtual);

            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource(
                    "/com/example/sosrota/All.css").toExternalForm());

            stageAtual.setScene(scene);
            stageAtual.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
