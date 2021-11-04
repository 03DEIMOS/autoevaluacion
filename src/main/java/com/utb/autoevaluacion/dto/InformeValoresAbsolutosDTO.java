/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.dto;

import com.utb.autoevaluacion.model.Pregunta;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author DEIMOS
 */

@AllArgsConstructor
@Data
public class InformeValoresAbsolutosDTO {

    private Pregunta pregunta;

    private List<String> itemPregunta;
    
    private List<Integer> resultadosAbsolutosSinSubpreguntaContador; 

    private List<List<Integer>> resultadosAbsolutosConSubpreguntaContador;
    
    private Integer totalPersonaQueContestaron;
    
    
    
    
}
