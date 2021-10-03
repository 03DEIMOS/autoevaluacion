/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.controller;

import com.utb.autoevaluacion.service.EncuestaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author DEIMOS
 */
@Controller
@RequestMapping("/encuesta")
public class EncuestaController {
    private final EncuestaService encuestaService;

    public EncuestaController(EncuestaService encuestaService) {
        this.encuestaService = encuestaService;
    }

    @GetMapping("/encuestas")
    public String encuestas(Model model) {
        model.addAttribute("listaE", encuestaService.getEncuestas());
        return "comiteCentral\\encuesta\\listar";
    } 
}
