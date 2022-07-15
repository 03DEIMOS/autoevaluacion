package com.utb.autoevaluacion.controller;

import com.utb.autoevaluacion.model.Caracteristica;
import com.utb.autoevaluacion.model.Pregunta;
import com.utb.autoevaluacion.service.CaracteristicaService;
import com.utb.autoevaluacion.service.FactorService;
import com.utb.autoevaluacion.service.PreguntaService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    
    @Autowired
    private PreguntaService preguntaService;
    
    @GetMapping("/caracteristicas/{modeloId}")
    public String caracteristicas(@PathVariable Integer modeloId, Model model) {
        model.addAttribute("listaC", caracteristicaService.getCaracteristicasByModelo(modeloId));
        return "comiteCentral\\caracteristica\\listar";

    }
    
    @GetMapping("/caracteristicasByFactor/{factorId}")
    public String caracteristicasByFactor(@PathVariable Integer factorId, Model model) {
        model.addAttribute("listaC", caracteristicaService.getCaracteristicasByFactor(factorId));
        return "comiteCentral\\caracteristica\\listarByFactor";

    }

    @GetMapping("/crear/{modeloId}")
    public String formularioCrearCaracteristica(@PathVariable Integer modeloId, Model model) {
        log.info("Ejecutanto metodo [formularioCrearCaracteristica] modeloId:{} ", modeloId);
        model.addAttribute("modeloId", modeloId);
        model.addAttribute("listaF", factorService.getFactoresByModelo(modeloId));
        model.addAttribute("listaP", preguntaService.getPreguntas());
        return "comiteCentral\\caracteristica\\crear";
    }

    @GetMapping("/editar/{caracteristicaId}/{modeloId}")
    public String formularioEditarCaracteristica(@PathVariable Integer caracteristicaId, @PathVariable Integer modeloId, Model model) {
        log.info("Ejecutanto metodo [formularioEditarCaracteristica] caracteristicaId:{}, modeloId:{} ", caracteristicaId, modeloId);
        Caracteristica caracteristica = caracteristicaService.buscarCaracteristica(caracteristicaId);
        model.addAttribute("listaF", factorService.getFactoresByModelo(modeloId));
        model.addAttribute("caracteristica", caracteristica);
        model.addAttribute("listaP", preguntaService.getPreguntas());
        model.addAttribute("modeloId", modeloId);
        return "comiteCentral\\caracteristica\\editar";
    }

    @PostMapping(value = "/crear")
    public ResponseEntity<?> crearCaracteristica(@RequestParam String codigo, @RequestParam String nombre, @RequestParam Integer factorId, @RequestParam Map<String,String> requestParams) {

        log.info("Ejecutanto metodo [crearCaracteristica] codigo:{}, nombre:{}, factorId:{} ", codigo, nombre, factorId);
        HttpStatus status;
        try {
            List<Pregunta> preguntas = preguntaService.getPreguntas();
            List<Pregunta> aux = new ArrayList<>();
            if (preguntas != null) {
                preguntas.stream().filter((pregunta) -> (requestParams.get("P" + pregunta.getId()).equals("1"))).forEachOrdered((pregunta) -> {
                    aux.add(pregunta);
                });
            }
            caracteristicaService.crearCaracteristica(codigo, nombre, factorId, aux);
            status = HttpStatus.CREATED;
        } catch (Exception e) {
            log.error("Ha ocurrido un error: " + e);
            status = HttpStatus.CONFLICT;
        }
        return new ResponseEntity<>(status);
    }

    @PutMapping(value = "/editar")
    public ResponseEntity<?> editarCaracteristica(@RequestParam Integer caracteristicaId, @RequestParam String codigo, @RequestParam String nombre, @RequestParam Integer factorId, @RequestParam Map<String,String> requestParams) {
        log.info("Ejecutanto metodo [editarCaracteristica] caracteristicaId:{}, codigo:{}, nombre:{}, factorId:{} ", caracteristicaId, codigo, nombre, factorId);
        HttpStatus status;
        try {
            List<Pregunta> preguntas = preguntaService.getPreguntas();
            List<Pregunta> aux = new ArrayList<>();
            if (preguntas != null) {
                preguntas.stream().filter((pregunta) -> (requestParams.get("P" + pregunta.getId()).equals("1"))).forEachOrdered((pregunta) -> {
                    aux.add(pregunta);
                });
            }
            caracteristicaService.actualizarCaracteristica(caracteristicaId, codigo, nombre, factorId, aux);
            status = HttpStatus.OK;
        } catch (Exception e) {
            log.error("Ha ocurrido un error: " + e);
            status = HttpStatus.CONFLICT;
        }
        return new ResponseEntity<>(status);
    }
}
