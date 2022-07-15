/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.controller;

import com.utb.autoevaluacion.model.TipoAccion;
import com.utb.autoevaluacion.service.TipoAccionService;
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
@RequestMapping("/tipoAccion")
public class TipoAccionController {

    @Autowired
    private TipoAccionService tipoAccionService;

    @GetMapping("/listar")
    public String tiposAccion(Model model) {
        model.addAttribute("listaTipoAccion", tipoAccionService.getTiposAccion());
        return "comiteCentral\\tipoAccion\\listar";

    }

    @GetMapping("/crear")
    public String formularioCrearTipoAccion(Model model) {
        log.info("Ejecutanto metodo [formularioCrearTipoAccion]");
        return "comiteCentral\\tipoAccion\\crear";
    }

    @GetMapping("/editar/{tipoAccionId}")
    public String formularioEditarTipoAccion(@PathVariable Integer tipoAccionId, Model model) {
        log.info("Ejecutanto metodo [formularioEditarTipoAccion]");
        TipoAccion tipoAccion = tipoAccionService.getTipoAccionById(tipoAccionId);
        model.addAttribute("tipoAccion", tipoAccion);
        return "comiteCentral\\tipoAccion\\editar";
    }

    @PostMapping(value = "/crear")
    public ResponseEntity<?> crearTipoAccion(@RequestParam String tipo, @RequestParam String descripcion) {

        log.info("Ejecutanto metodo [crearTipoAccion] tipo:{}, descripcion:{} ", tipo, descripcion);
        HttpStatus status;
        try {
            TipoAccion tipoAccion = new TipoAccion();
            tipoAccion.setDescripcion(descripcion);
            tipoAccion.setTipo(tipo);
            tipoAccionService.crearTipoAccion(tipoAccion);
            status = HttpStatus.CREATED;
        } catch (Exception e) {
            log.error("Ha ocurrido un error:{} " , e);
            status = HttpStatus.CONFLICT;
        }

        return new ResponseEntity<>(status);
    }

    @PutMapping(value = "/editar")
    public ResponseEntity<?> editarTipoAccion(@RequestParam Integer tipoAccionId, @RequestParam String tipo, @RequestParam String descripcion) {
        log.info("Ejecutanto metodo [editarTipoAccion] tipoAccionId:{}, tipo:{}, descripcion:{}", tipoAccionId, tipo, descripcion);
        HttpStatus status;
        try {
            TipoAccion tipoAccion = tipoAccionService.getTipoAccionById(tipoAccionId);
            tipoAccion.setDescripcion(descripcion);
            tipoAccion.setTipo(tipo);
            tipoAccionService.actualizarTipoAccion(tipoAccion);
            status = HttpStatus.OK;
        } catch (Exception e) {
            log.error("Ha ocurrido un error:{} " , e);
            status = HttpStatus.CONFLICT;
        }
     
        return new ResponseEntity<>(status);
    }
}
