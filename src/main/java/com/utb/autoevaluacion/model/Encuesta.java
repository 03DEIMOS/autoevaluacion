package com.utb.autoevaluacion.model;

import java.util.Date;

public class Encuesta {
    private Integer id;
    private String codigo;
    private String nombre;
    private String objetivo;
    private String instrucciones;
    private String version;
    private Date fecha;

    public Encuesta() {
    }

    @Override
    public String toString() {
        return "Encuesta{" + "id=" + id + ", codigo=" + codigo + ", nombre=" + nombre + ", objetivo=" + objetivo + ", instrucciones=" + instrucciones + ", version=" + version + ", fecha=" + fecha + '}';
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

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(String instrucciones) {
        this.instrucciones = instrucciones;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    
    
}
