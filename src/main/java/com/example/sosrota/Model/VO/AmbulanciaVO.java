package com.example.sosrota.Model.VO;

public class AmbulanciaVO {
    private int id;
    private String placa;
    private String tipo;
    private Boolean status;

    // Novos atributos incorporados
    private int bairroBaseID;
    private boolean ocupada;
    private Integer ocorrenciaID;
    private Long timestampDespacho;

    // Construtor opcional de inicialização
    public AmbulanciaVO(int id, String placa, Boolean status) {
        this.id = id;
        this.placa = placa;
        this.status = status;
    }

    public AmbulanciaVO(int bairroBaseID, String tipo) {
        this.bairroBaseID = bairroBaseID;
        this.tipo = tipo;
        this.ocupada = false;
        this.ocorrenciaID = null;
        this.timestampDespacho = null;
    }

    public AmbulanciaVO() {}

    // ------------------------ GETTERS e SETTERS ------------------------

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBase() {
        return bairroBaseID;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public int getBairroBaseID() {
        return bairroBaseID;
    }

    public void setBairroBaseID(int bairroBaseID) {
        this.bairroBaseID = bairroBaseID;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }

    public Integer getOcorrenciaID() {
        return ocorrenciaID;
    }

    public void setOcorrenciaID(Integer ocorrenciaID) {
        this.ocorrenciaID = ocorrenciaID;
    }

    public Long getTimestampDespacho() {
        return timestampDespacho;
    }

    public void setTimestampDespacho(Long timestampDespacho) {
        this.timestampDespacho = timestampDespacho;
    }
}
