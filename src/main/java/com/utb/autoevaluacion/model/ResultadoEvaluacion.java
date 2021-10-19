/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "resultadoevaluacion")
public class ResultadoEvaluacion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String respuesta;
    
    @ManyToOne
    @JoinColumn(name = "pregunta_id")
    private Pregunta pregunta;
    
    @ManyToOne
    @JoinColumn(name = "item_pregunta_id")
    private ItemPregunta itemPregunta;
    
    @ManyToOne
    @JoinColumn(name = "tipo_pregunta_id")
    private TipoPregunta tipoPregunta;
    
    @ManyToOne
    @JoinColumn(name = "persona_id")
    private Persona persona;

    
    
    
}
