/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.controller;

import com.utb.autoevaluacion.service.PreguntaService;
import com.utb.autoevaluacion.service.TipoPreguntaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author DEIMOS
 */
@Slf4j
@Controller
@RequestMapping("/pregunta")
public class PreguntaController {
    @Autowired
    private PreguntaService preguntaService;
    @Autowired
    private TipoPreguntaService tipoPreguntaService;

    public PreguntaController(PreguntaService preguntaService) {
        this.preguntaService = preguntaService;
    }

    @GetMapping("/preguntas")
    public String preguntas(Model model) {
        model.addAttribute("listaP", preguntaService.getPreguntas());
        return "comiteCentral\\pregunta\\listar";
    } 
    
    @GetMapping("/crear")
    public String formularioCrearPregunta(Model model) {
        log.info("Ejecutanto metodo [formularioCrearPregunta]");
        model.addAttribute("listaTipoP", tipoPreguntaService.buscarTipoPreguntas());
        return "comiteCentral\\pregunta\\crear";
    }
    
}
