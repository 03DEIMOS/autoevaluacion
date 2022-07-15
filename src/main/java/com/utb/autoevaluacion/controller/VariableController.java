/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.controller;

import com.utb.autoevaluacion.model.Variable;
import com.utb.autoevaluacion.service.VariableService;
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
@RequestMapping("/parametro")
public class VariableController {
    
    @Autowired
    private VariableService variableService;

    @GetMapping("/parametros")
    public String modelos(Model model) {
        model.addAttribute("listaV", variableService.getVariables());
        return "comiteCentral\\variables\\listar";
    }

    @GetMapping("/crear")
    public String formularioCrearVariable(Model model) {
        log.info("Ejecutanto metodo [formularioCrearVariable] ");
        return "comiteCentral\\variables\\crear";
    }

    @GetMapping("/editar/{variableId}")
    public String formularioEditarVariable(@PathVariable Integer variableId, Model model) {
        log.info("Ejecutanto metodo [formularioEditarVariable] variableId:{} ", variableId);
        Variable variable = variableService.getVariableById(variableId);
        model.addAttribute("variable", variable);
        return "comiteCentral\\variables\\editar";
    }
    
    @PostMapping(value = "/crear")
    public ResponseEntity<?> crearVariable(@RequestParam String llave, @RequestParam String valor) {

        log.info("Ejecutanto metodo [crearVariable] llave:{}, valor:{} ", llave, valor);
        HttpStatus status;
        try {
            variableService.crearVariable(llave, valor);
            status = HttpStatus.CREATED;
        } catch (Exception e) {
            log.error("Ha ocurrido un error:{} " , e);
            status = HttpStatus.CONFLICT;
        }

        return new ResponseEntity<>(status);
    }

    @PutMapping(value = "/editar")
    public ResponseEntity<?> editarVariable(@RequestParam Integer variableId, @RequestParam String llave, @RequestParam String valor) {

        log.info("Ejecutanto metodo [editarVariable] variableId:{}, llave:{}, valor:{} ", variableId, llave, valor);
        HttpStatus status;
        try {
            variableService.actualizarVariable(variableId, llave, valor);
            status = HttpStatus.OK;
        } catch (Exception e) {
            log.error("Ha ocurrido un error:{} ",  e);
            status = HttpStatus.CONFLICT;
        }

        return new ResponseEntity<>(status);

    }
    
}
