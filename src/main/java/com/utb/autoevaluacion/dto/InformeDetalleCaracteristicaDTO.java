/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class InformeDetalleCaracteristicaDTO {

    List<Double> promediorespuestas;
    List<String> preguntaReales;

    public List<Double> getPromediorespuestas() {
        return promediorespuestas;
    }

    public void setPromediorespuestas(List<Double> promediorespuestas) {
        this.promediorespuestas = promediorespuestas;
    }

    public List<String> getPreguntaReales() {
        return preguntaReales;
    }

    public void setPreguntaReales(List<String> preguntaReales) {
        this.preguntaReales = preguntaReales;
    }

    public InformeDetalleCaracteristicaDTO(List<Double> promediorespuestas, List<String> preguntaReales) {
        this.promediorespuestas = promediorespuestas;
        this.preguntaReales = preguntaReales;
    }

    public InformeDetalleCaracteristicaDTO() {
    }
    
    
}
