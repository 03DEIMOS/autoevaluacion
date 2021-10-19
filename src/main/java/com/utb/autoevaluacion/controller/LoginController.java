/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.controller;

import com.utb.autoevaluacion.model.Encuesta;
import com.utb.autoevaluacion.model.Persona;
import com.utb.autoevaluacion.service.EncuestaService;
import com.utb.autoevaluacion.service.PersonaService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ASUS
 */
@Slf4j
@Controller
public class LoginController {

    @Autowired
    PersonaService personaService;

    @Autowired
    EncuestaService encuestaService;
    
    @GetMapping("/")
    public String index() {
        return "index";
    }
    
    @PostMapping("cerrarSesion")
    public String cerrarSesion() {
        log.info("Ejecutando metodo cerrarSesion");
        return "index";
    }

    @PostMapping("/")
    public String validar(@RequestParam String codigo, Model model) {
         log.info("Ejecutando metodo validar codigo:{}", codigo);
        List<Persona> personas = personaService.buscarPersonaPorUsuarioActivaYEsMuestra(codigo);
        if (personas!=null && !personas.isEmpty()) {
            Persona persona = personas.get(0);
            Encuesta encuesta = encuestaService.obtenerEncuestasDePersona(persona);
            model.addAttribute("encuesta", encuesta);
            model.addAttribute("persona", persona);
            return "fuente\\index";
        } else {
            log.info("Devuelto al index");
            model.addAttribute("errorLogin", true);
            return "index";
        }
    }
}
