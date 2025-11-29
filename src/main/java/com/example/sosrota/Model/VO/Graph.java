package com.example.sosrota.Model.VO;

import java.util.*;

public class Graph {

    private Map<Integer, Bairro> bairros = new HashMap<>();
    private Map<Integer, List<Aresta>> adj = new HashMap<>();

    public void addBairro(Bairro b) {
        bairros.put(b.getId(), b);
        adj.putIfAbsent(b.getId(), new ArrayList<>());
    }

    public void addAresta(int origem, int destino, double peso) {
        adj.putIfAbsent(origem, new ArrayList<>());
        adj.get(origem).add(new Aresta(origem, destino, peso));
    }

    public Map<Integer, Bairro> getBairros() {
        return bairros;
    }

    public Map<Integer, List<Aresta>> getAdj() {
        return adj;
    }

    public Bairro getBairro(int id) {
        return bairros.get(id);
    }
}
