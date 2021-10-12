/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.service;

import com.utb.autoevaluacion.model.TipoPregunta;
import java.util.List;

public interface TipoPreguntaService {

    TipoPregunta buscarTipoPregunta(Integer id);

    List<TipoPregunta> buscarTipoPreguntas();

    void crearTipoPregunta(TipoPregunta tipoPregunta);

    void eliminarTipoPregunta(Integer id);

    void actualizarTipoPregunta(TipoPregunta tipoPregunta);

}
