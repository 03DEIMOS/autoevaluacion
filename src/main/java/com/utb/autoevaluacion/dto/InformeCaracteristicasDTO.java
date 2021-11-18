/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.dto;

import com.utb.autoevaluacion.model.Caracteristica;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class InformeCaracteristicasDTO {

    private List<Caracteristica> caracteristicas;
    
    private float[] cumplimiento;

    public List<Caracteristica> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(List<Caracteristica> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public float[] getCumplimiento() {
        return cumplimiento;
    }

    public void setCumplimiento(float[] cumplimiento) {
        this.cumplimiento = cumplimiento;
    }

    public InformeCaracteristicasDTO(List<Caracteristica> caracteristicas, float[] cumplimiento) {
        this.caracteristicas = caracteristicas;
        this.cumplimiento = cumplimiento;
    }

    public InformeCaracteristicasDTO() {
    }
}
