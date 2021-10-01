package com.utb.autoevaluacion.model;

public class Factor {
    
    private Integer id;
    private String codigo;
    private String nombre;
    private Integer modelo_id;

    public Factor() {
    }

    @Override
    public String toString() {
        return "Factor{" + "id=" + id + ", codigo=" + codigo + ", nombre=" + nombre + ", modelo_id=" + modelo_id + '}';
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

    public Integer getModelo_id() {
        return modelo_id;
    }

    public void setModelo_id(Integer modelo_id) {
        this.modelo_id = modelo_id;
    }


    
    
}
