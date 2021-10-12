/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.controller;

import com.utb.autoevaluacion.model.Encuesta;
import com.utb.autoevaluacion.service.EncuestaService;
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

/**
 *
 * @author DEIMOS
 */
@Slf4j
@Controller
@RequestMapping("/encuesta")
public class EncuestaController {

    @Autowired
    private EncuestaService encuestaService;

    public EncuestaController(EncuestaService encuestaService) {
        this.encuestaService = encuestaService;
    }

    @GetMapping("/encuestas")
    public String encuestas(Model model) {
        model.addAttribute("listaE", encuestaService.getEncuestas());
        return "comiteCentral\\encuesta\\listar";
    }
    
    @GetMapping("/crear")
    public String formularioCrearEncuesta(Model model) {
        log.info("Ejecutanto metodo [formularioCrearEncuesta] ");
        return "comiteCentral\\encuesta\\crear";
    }

    @GetMapping("/editar/{encuestaId}")
    public String formularioEditarEncuesta(@PathVariable Integer encuestaId, Model model) {
        log.info("Ejecutanto metodo [formularioEditarEncuesta] encuestaId:{} ", encuestaId);
        Encuesta encuesta = encuestaService.buscarEncuesta(encuestaId);
        model.addAttribute("encuesta", encuesta);
        return "comiteCentral\\encuesta\\editar";
    }

    @PostMapping(value = "/crear")
    public ResponseEntity<?> crearEncuesta(@RequestParam String codigo, @RequestParam String nombre, @RequestParam String objetivo, @RequestParam String instrucciones, @RequestParam String version, @RequestParam String fecha) {

        log.info("Ejecutanto metodo [crearEncuesta] codigo:{}, nombre:{}, objetivo:{}, instrucciones:{}, version:{}, fecha:{} ", codigo, nombre, objetivo, instrucciones, version, fecha);
        HttpStatus status;
        try {
            encuestaService.crearEncuesta(codigo, nombre, objetivo, instrucciones, version, fecha);
            status = HttpStatus.CREATED;
        } catch (Exception e) {
            log.error("Ha ocurrido un error: " + e);
            status = HttpStatus.CONFLICT;
        }

        return new ResponseEntity<>(status);
    }

    @PutMapping(value = "/editar")
    public ResponseEntity<?> editarEncuesta(@RequestParam Integer encuestaId, @RequestParam String codigo, @RequestParam String nombre, @RequestParam String objetivo, @RequestParam String instrucciones, @RequestParam String version, @RequestParam String fecha) {
        log.info("Ejecutanto metodo [editarModelo] encuestaId:{}, codigo:{}, nombre:{}, objetivo:{}, instrucciones:{}, version:{}, fecha:{} ", encuestaId, codigo, nombre, objetivo, instrucciones, version, fecha);
        HttpStatus status;
        try {
            encuestaService.actualizarEncuesta(encuestaId, codigo, nombre, objetivo, instrucciones, version, fecha);
            status = HttpStatus.OK;
        } catch (Exception e) {
            log.error("Ha ocurrido un error: " + e);
            status = HttpStatus.CONFLICT;
        }
        return new ResponseEntity<>(status);
    }

}
