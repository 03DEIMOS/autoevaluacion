/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.dto;

import java.util.Objects;

public class MuestraInformeDTO {

    String fuente;

    Integer cantidad;

    public MuestraInformeDTO() {
    }
    
    public MuestraInformeDTO(String fuente, Integer cantidad) {
        this.fuente = fuente;
        this.cantidad = cantidad;
    }
    
    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.fuente);
        hash = 53 * hash + Objects.hashCode(this.cantidad);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MuestraInformeDTO other = (MuestraInformeDTO) obj;
        if (!Objects.equals(this.fuente, other.fuente)) {
            return false;
        }
        if (!Objects.equals(this.cantidad, other.cantidad)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MuestraInformeDTO{" + "fuente=" + fuente + ", cantidad=" + cantidad + '}';
    }
}
