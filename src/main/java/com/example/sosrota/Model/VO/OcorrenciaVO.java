package com.example.sosrota.Model.VO;

import java.time.LocalDateTime;

public class OcorrenciaVO {

    private int id;
    private int idBairro;
    private String tipo;
    private String gravidade;
    private LocalDateTime abertura;
    private String status;

    public static final String ABERTA = "Aberta";
    public static final String DESPACHADA = "Despachada";
    public static final String EM_ATENDIMENTO = "Em Atendimento";
    public static final String CONCLUIDA = "Concluída";

    public OcorrenciaVO(int id, int idBairro, String tipo, String gravidade) {
        this.id = id;
        this.idBairro = idBairro;
        this.tipo = tipo;
        this.gravidade = gravidade;
        this.abertura = LocalDateTime.now();
        this.status = ABERTA;
    }

    public OcorrenciaVO(int idBairro, String gravidade) {
        this.id = -1; // se não usar ID manual
        this.idBairro = idBairro;
        this.tipo = "Desconhecido";
        this.gravidade = gravidade;
        this.abertura = LocalDateTime.now();
        this.status = ABERTA;
    }

    public int getId() { return id; }
    public int getBairro() { return idBairro; }
    public String getGravidade() { return gravidade; }
    public LocalDateTime getAbertura() { return abertura; }
    public String getStatus() { return status; }

    public void setStatus(String st) {
        this.status = st;
    }
}


