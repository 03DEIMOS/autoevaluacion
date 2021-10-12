package com.utb.autoevaluacion.controller;

import com.utb.autoevaluacion.model.Factor;
import com.utb.autoevaluacion.model.Modelo;
import com.utb.autoevaluacion.service.FactorService;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequestMapping("/factor")
public class FactorController {

    @Autowired
    private FactorService factorService;

    public FactorController(FactorService factorService) {
        this.factorService = factorService;
    }

    @GetMapping("/factores/{modeloId}")
    public String factores(@PathVariable Integer modeloId, Model model) {
        model.addAttribute("listaF", factorService.getFactoresByModelo(modeloId));
        model.addAttribute("modeloId", modeloId);
        return "comiteCentral\\factor\\listar";

    }

    @GetMapping("/crear/{modeloId}")
    public String formularioCrearFactor(@PathVariable Integer modeloId, Model model) {
        log.info("Ejecutanto metodo [formularioCrearFactor] modeloId:{} ", modeloId);
        model.addAttribute("modeloId", modeloId);
        return "comiteCentral\\factor\\crear";
    }

    @GetMapping("/editar/{factorId}")
    public String formularioEditarFactor(@PathVariable Integer factorId, Model model) {
        log.info("Ejecutanto metodo [formularioEditarFactor] factorId:{} ", factorId);
        Factor factor = factorService.buscarFactor(factorId);
        model.addAttribute("factor", factor);
        return "comiteCentral\\factor\\editar";
    }

    @PostMapping(value = "/crear")
    public ResponseEntity<?> crearFactor(@RequestParam String codigo, @RequestParam String nombre, @RequestParam Integer modeloId) {

        log.info("Ejecutanto metodo [crearFactor] codigo:{}, nombre:{}, modeloId:{} ", codigo, nombre, modeloId);
        HttpStatus status;
        try {
            factorService.crearFactor(codigo, nombre, modeloId);
            status = HttpStatus.CREATED;
        } catch (Exception e) {
            log.error("Ha ocurrido un error: " + e);
            status = HttpStatus.CONFLICT;
        }

        return new ResponseEntity<>(status);
    }

    @PutMapping(value = "/editar")
    public ResponseEntity<?> editarFactor(@RequestParam Integer factorId, @RequestParam String codigo, @RequestParam String nombre, @RequestParam Integer modeloId) {
        log.info("Ejecutanto metodo [editarFactor] factorId:{}, codigo:{}, nombre:{}, modeloId:{} ", factorId, codigo, nombre, modeloId);
        HttpStatus status;
        try {
            factorService.actualizarFactor(factorId, codigo, nombre, modeloId);
            status = HttpStatus.OK;
        } catch (Exception e) {
            log.error("Ha ocurrido un error: " + e);
            status = HttpStatus.CONFLICT;
        }
        return new ResponseEntity<>(status);
    }

}
