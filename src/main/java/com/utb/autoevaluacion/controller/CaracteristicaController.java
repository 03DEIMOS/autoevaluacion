package com.utb.autoevaluacion.controller;

import com.utb.autoevaluacion.model.Caracteristica;
import com.utb.autoevaluacion.service.CaracteristicaService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequestMapping("/caracteristica")
public class CaracteristicaController {

    @Autowired
    private CaracteristicaService caracteristicaService;

    @Autowired
    private FactorService factorService;

    @GetMapping("/caracteristicas/{modeloId}")
    public String caracteristicas(@PathVariable Integer modeloId, Model model) {
        model.addAttribute("listaC", caracteristicaService.getCaracteristicasByModelo(modeloId));
        return "comiteCentral\\caracteristica\\listar";

    }

    @GetMapping("/crear/{modeloId}")
    public String formularioCrearCaracteristica(@PathVariable Integer modeloId, Model model) {
        log.info("Ejecutanto metodo [formularioCrearCaracteristica] modeloId:{} ", modeloId);
        model.addAttribute("modeloId", modeloId);
        model.addAttribute("listaF", factorService.getFactoresByModelo(modeloId));
        return "comiteCentral\\caracteristica\\crear";
    }

    @GetMapping("/editar/{caracteristicaId}/{modeloId}")
    public String formularioEditarCaracteristica(@PathVariable Integer caracteristicaId, @PathVariable Integer modeloId, Model model) {
        log.info("Ejecutanto metodo [formularioEditarCaracteristica] caracteristicaId:{}, modeloId:{} ", caracteristicaId, modeloId);
        Caracteristica caracteristica = caracteristicaService.buscarCaracteristica(caracteristicaId);
        model.addAttribute("listaF", factorService.getFactoresByModelo(modeloId));
        model.addAttribute("caracteristica", caracteristica);
        model.addAttribute("modeloId", modeloId);
        return "comiteCentral\\caracteristica\\editar";
    }

    @PostMapping(value = "/crear")
    public ResponseEntity<?> crearCaracteristica(@RequestParam String codigo, @RequestParam String nombre, @RequestParam Integer factorId) {

        log.info("Ejecutanto metodo [crearCaracteristica] codigo:{}, nombre:{}, factorId:{} ", codigo, nombre, factorId);
        HttpStatus status;
        try {
            caracteristicaService.crearCaracteristica(codigo, nombre, factorId);
            status = HttpStatus.CREATED;
        } catch (Exception e) {
            log.error("Ha ocurrido un error: " + e);
            status = HttpStatus.CONFLICT;
        }
        return new ResponseEntity<>(status);
    }

    @PutMapping(value = "/editar")
    public ResponseEntity<?> editarCaracteristica(@RequestParam Integer caracteristicaId, @RequestParam String codigo, @RequestParam String nombre, @RequestParam Integer factorId) {
        log.info("Ejecutanto metodo [editarCaracteristica] caracteristicaId:{}, codigo:{}, nombre:{}, factorId:{} ", caracteristicaId, codigo, nombre, factorId);
        HttpStatus status;
        try {
            caracteristicaService.actualizarCaracteristica(caracteristicaId, codigo, nombre, factorId);
            status = HttpStatus.OK;
        } catch (Exception e) {
            log.error("Ha ocurrido un error: " + e);
            status = HttpStatus.CONFLICT;
        }
        return new ResponseEntity<>(status);
    }
}
