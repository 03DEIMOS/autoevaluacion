/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.controller;

import com.utb.autoevaluacion.service.PreguntaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author DEIMOS
 */
@Controller
@RequestMapping("/pregunta")
public class PreguntaController {
     private final PreguntaService preguntaService;

    public PreguntaController(PreguntaService preguntaService) {
        this.preguntaService = preguntaService;
    }

    @GetMapping("/preguntas")
    public String preguntas(Model model) {
        model.addAttribute("listaP", preguntaService.getPregunta());
        return "comiteCentral\\pregunta\\listar";
    } 
}
