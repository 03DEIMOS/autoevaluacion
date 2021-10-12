package com.utb.autoevaluacion.controller;

import com.utb.autoevaluacion.model.Modelo;
import com.utb.autoevaluacion.model.Pregunta;
import com.utb.autoevaluacion.service.PreguntaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequestMapping("/pregunta")
public class PreguntaController {
    @Autowired
    private PreguntaService preguntaService;

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
        log.info("Ejecutanto metodo [formularioCrearPregunta] ");
        return "comiteCentral\\pregunta\\crear";
    }
    
    @GetMapping("/editar/{preguntaId}")
    public String formularioEditarPregunta(@PathVariable Integer preguntaId, Model model) {
        log.info("Ejecutanto metodo [formularioEditarPregunta] preguntaId:{} ", preguntaId);
        Pregunta pregunta = preguntaService.buscarPregunta(preguntaId);
        model.addAttribute("pregunta", pregunta);
        return "comiteCentral\\pregunta\\editar";
    }
    
    @PostMapping(value = "/crear" )
    public ResponseEntity<?> crearPregunta(@RequestParam String codigo, @RequestParam String pregunta, @RequestParam String tipo, @RequestParam Integer preguntaPadre, @RequestParam String repetir) {
        
        log.info("Ejecutanto metodo [crearPregunta] codigo:{}, pregunta:{}, tipo:{}, preguntaPadre:{}, repetir:{}", codigo, pregunta, tipo, preguntaPadre, repetir);
        HttpStatus status;
        try {
            preguntaService.crearPregunta(codigo, pregunta, tipo, preguntaPadre, repetir);
            status = HttpStatus.CREATED;
        } catch (Exception e) {
            log.error("Ha ocurrido un error: " + e);
            status = HttpStatus.CONFLICT;
        }

        return new ResponseEntity<>(status);
    }
    
    @PutMapping(value = "/editar" )
    public ResponseEntity<?> editarPregunta(@RequestParam Integer preguntaId, @RequestParam String codigo, @RequestParam String pregunta, @RequestParam String tipo, @RequestParam Integer preguntaPadre, @RequestParam String repetir) {
        
        log.info("Ejecutanto metodo [editarPregunta] preguntaId:{}, codigo:{}, pregunta:{}, tipo:{}, preguntaPadre:{}, repetir:{}", preguntaId, codigo, pregunta, tipo, preguntaPadre, repetir);
        HttpStatus status;
        try {
            preguntaService.actualizarPregunta(preguntaId, codigo, pregunta, tipo, preguntaPadre, repetir);
            status = HttpStatus.OK;
        } catch (Exception e) {
            log.error("Ha ocurrido un error: " + e);
            status = HttpStatus.CONFLICT;
        }

        return new ResponseEntity<>(status);
        
    }
}
