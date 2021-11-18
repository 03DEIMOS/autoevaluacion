/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.dto;

import com.utb.autoevaluacion.model.Caracteristica;
import com.utb.autoevaluacion.model.Factor;
import com.utb.autoevaluacion.model.Pregunta;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class InformeDmaDTO {

    private Pregunta pregunta;

    private List<String> itemPregunta;

    private List<String> fuente;

    private Caracteristica caracteristica;

    private Factor factor;

    private List<Double> DMA;

    private List<List<Double>> DMAList;

    private List<Double> porcentajeCeros;

    private List<List<Double>> porcentajeCerosList;

    public InformeDmaDTO() {
        super();
    }

    public InformeDmaDTO(Pregunta pregunta, List<String> itemPregunta, List<String> fuente, Caracteristica caracteristica, Factor factor, List<Double> DMA, List<Double> porcentajeCeros) {
        this.pregunta = pregunta;
        this.itemPregunta = itemPregunta;
        this.fuente = fuente;
        this.caracteristica = caracteristica;
        this.factor = factor;
        this.DMA = DMA;
        this.porcentajeCeros = porcentajeCeros;
    }

    public InformeDmaDTO(Pregunta pregunta, List<String> itemPregunta, List<String> fuente,
            Caracteristica caracteristica, Factor factor,
            List<Double> DMA, List<List<Double>> DMAList, List<Double> porcentajeCeros,
            List<List<Double>> porcentajeCerosList) {
        this.pregunta = pregunta;
        this.itemPregunta = itemPregunta;
        this.fuente = fuente;
        this.caracteristica = caracteristica;
        this.factor = factor;
        this.DMA = DMA;
        this.DMAList = DMAList;
        this.porcentajeCeros = porcentajeCeros;
        this.porcentajeCerosList = porcentajeCerosList;
    }

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    public List<String> getItemPregunta() {
        return itemPregunta;
    }

    public void setItemPregunta(List<String> itemPregunta) {
        this.itemPregunta = itemPregunta;
    }

    public List<String> getFuente() {
        return fuente;
    }

    public void setFuente(List<String> fuente) {
        this.fuente = fuente;
    }

    public Caracteristica getCaracteristica() {
        return caracteristica;
    }

    public void setCaracteristica(Caracteristica caracteristica) {
        this.caracteristica = caracteristica;
    }

    public Factor getFactor() {
        return factor;
    }

    public void setFactor(Factor factor) {
        this.factor = factor;
    }

    public List<Double> getDMA() {
        return DMA;
    }

    public void setDMA(List<Double> DMA) {
        this.DMA = DMA;
    }

    public List<Double> getPorcentajeCeros() {
        return porcentajeCeros;
    }

    public void setPorcentajeCeros(List<Double> porcentajeCeros) {
        this.porcentajeCeros = porcentajeCeros;
    }

    public List<List<Double>> getDMAList() {
        return DMAList;
    }

    public void setDMAList(List<List<Double>> DMAList) {
        this.DMAList = DMAList;
    }

    public List<List<Double>> getPorcentajeCerosList() {
        return porcentajeCerosList;
    }

    public void setPorcentajeCerosList(List<List<Double>> porcentajeCerosList) {
        this.porcentajeCerosList = porcentajeCerosList;
    }

    @Override
    public String toString() {
        return "InformeDMA{" + "pregunta=" + pregunta + ", itemPregunta=" + itemPregunta + ", fuente=" + fuente + ", caracteristica=" + caracteristica + ", factor=" + factor + ", DMA=" + DMA + ", porcentajeCeros=" + porcentajeCeros + '}';
    }

    
}
