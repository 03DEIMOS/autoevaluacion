/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.controller;

import com.utb.autoevaluacion.model.Fuente;
import com.utb.autoevaluacion.service.FuenteService;
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
 * @author ASUS
 */
@Slf4j
@Controller
@RequestMapping("/fuente")
public class FuenteController {
    
    @Autowired
    private FuenteService fuenteService;
    
    @GetMapping("/fuentes")
    public String fuentes(Model model) {
        model.addAttribute("listaFuentes", fuenteService.buscarFuentes());
        return "comiteCentral\\fuente\\listar";
    }
    
    @GetMapping("/crear")
    public String formularioCrearFuente(Model model) {
        log.info("Ejecutanto metodo [formularioCrearFuente] ");
        return "comiteCentral\\fuente\\crear";
    }

    @GetMapping("/editar/{fuenteId}")
    public String formularioEditarFuente(@PathVariable Integer fuenteId, Model model) {
        log.info("Ejecutanto metodo [formularioEditarFuente] fuenteId:{} ", fuenteId);
        Fuente fuente = fuenteService.buscarFuente(fuenteId);
        model.addAttribute("fuente", fuente);
        return "comiteCentral\\fuente\\editar";
    }
    
    @PostMapping(value = "/crear")
    public ResponseEntity<?> crearFuente(@RequestParam String nombre, @RequestParam String descripcion) {

        log.info("Ejecutanto metodo [crearFuente] nombre:{}, descripcion:{} ", nombre, descripcion);
        HttpStatus status;
        try {
            fuenteService.crearFuente(nombre, descripcion);
            status = HttpStatus.CREATED;
        } catch (Exception e) {
            log.error("Ha ocurrido un error: " , e);
            status = HttpStatus.CONFLICT;
        }

        return new ResponseEntity<>(status);
    }

    @PutMapping(value = "/editar")
    public ResponseEntity<?> editarFuente(@RequestParam Integer fuenteId, @RequestParam String nombre, @RequestParam String descripcion) {

        log.info("Ejecutanto metodo [editarFuente] fuenteId:{}, nombre:{}, descripcion:{} ", fuenteId, nombre, descripcion);
        HttpStatus status;
        try {
            fuenteService.actualizarFuente(fuenteId, nombre, descripcion);
            status = HttpStatus.OK;
        } catch (Exception e) {
            log.error("Ha ocurrido un error: " , e);
            status = HttpStatus.CONFLICT;
        }

        return new ResponseEntity<>(status);

    }
}
