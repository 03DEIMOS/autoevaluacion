package com.utb.autoevaluacion.model;

public class Pregunta {
    private Integer id;
    private String codigo;
    private String pregunta;
    private String tipo;
    private Integer pregunta_padre;
    private String repetir;

    public Pregunta() {
    }

    @Override
    public String toString() {
        return "Pregunta{" + "id=" + id + ", codigo=" + codigo + ", pregunta=" + pregunta + ", tipo=" + tipo + ", pregunta_padre=" + pregunta_padre + ", repetir=" + repetir + '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getPregunta_padre() {
        return pregunta_padre;
    }

    public void setPregunta_padre(Integer pregunta_padre) {
        this.pregunta_padre = pregunta_padre;
    }

    public String getRepetir() {
        return repetir;
    }

    public void setRepetir(String repetir) {
        this.repetir = repetir;
    }
    
    
}
