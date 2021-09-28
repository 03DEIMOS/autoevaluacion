package com.utb.autoevaluacion.model;

import java.util.Date;


public class Modelo {

    private Integer id;
    private String nombre;
    private String descripcion;
    private Integer fechaactualizacion;
    private Date fechacreacion;
    private String observaciones;

    public Modelo() {
    }

    @Override
    public String toString() {
        return "Modelo{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", fechaactualizacion=" + fechaactualizacion + ", fechacreacion=" + fechacreacion + ", observaciones=" + observaciones + '}';
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getFechaactualizacion() {
        return fechaactualizacion;
    }

    public void setFechaactualizacion(Integer fechaactualizacion) {
        this.fechaactualizacion = fechaactualizacion;
    }

    public Date getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(Date fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
}
