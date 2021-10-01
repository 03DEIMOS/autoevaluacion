package com.utb.autoevaluacion.model;

public class Caracteristica {
    private Integer id;
    private String codigo;
    private String nombre;
    private Integer factor_id;

    public Caracteristica() {
    }

    @Override
    public String toString() {
        return "Caracteristica{" + "id=" + id + ", codigo=" + codigo + ", nombre=" + nombre + ", factor_id=" + factor_id + '}';
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getFactor_id() {
        return factor_id;
    }

    public void setFactor_id(Integer factor_id) {
        this.factor_id = factor_id;
    }
    
    
}
