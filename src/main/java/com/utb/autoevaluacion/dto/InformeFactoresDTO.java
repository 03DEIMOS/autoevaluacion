/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.dto;

import com.utb.autoevaluacion.model.Factor;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class InformeFactoresDTO {

    private List<Factor> factores;

    private float[] cumplimientoF;

    public List<Factor> getFactores() {
        return factores;
    }

    public void setFactores(List<Factor> factores) {
        this.factores = factores;
    }

    public float[] getCumplimientoF() {
        return cumplimientoF;
    }

    public void setCumplimientoF(float[] cumplimientoF) {
        this.cumplimientoF = cumplimientoF;
    }

    public InformeFactoresDTO(List<Factor> factores, float[] cumplimientoF) {
        this.factores = factores;
        this.cumplimientoF = cumplimientoF;
    }

    public InformeFactoresDTO() {
    }

}
