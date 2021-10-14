/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.controller;

import com.utb.autoevaluacion.model.Factor;
import com.utb.autoevaluacion.model.ItemPregunta;
import com.utb.autoevaluacion.model.Pregunta;
import com.utb.autoevaluacion.model.TipoPregunta;
import com.utb.autoevaluacion.service.PreguntaService;
import com.utb.autoevaluacion.service.TipoPreguntaService;
import java.util.ArrayList;
import java.util.List;
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
@RequestMapping("/pregunta")
public class PreguntaController {

    @Autowired
    private PreguntaService preguntaService;
    @Autowired
    private TipoPreguntaService tipoPreguntaService;

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
        log.info("Ejecutanto metodo [formularioCrearPregunta]");
        model.addAttribute("listaTipoP", tipoPreguntaService.buscarTipoPreguntas());
        return "comiteCentral\\pregunta\\crear";
    }

    @GetMapping("/editar/{preguntaId}")
    public String formularioEditarPregunta(@PathVariable Integer preguntaId, Model model) {
        log.info("Ejecutanto metodo [formularioEditarPregunta] preguntaId:{} ", preguntaId);
        Pregunta pregunta = preguntaService.buscarPregunta(preguntaId);
        model.addAttribute("pregunta", pregunta);
        model.addAttribute("listaTipoP", tipoPreguntaService.buscarTipoPreguntas());
        model.addAttribute("sencilla", pregunta.getItemPreguntas().isEmpty());
        return "comiteCentral\\pregunta\\editar";
    }

    @PostMapping(value = "/vistaPrevia")
    public String vistaPrevia(@RequestParam String pregunta, @RequestParam Integer tipoId, @RequestParam boolean sencilla, @RequestParam(value = "subpregunta[]") String[] subpreguntas, Model model) {
        log.info("Ejecutanto metodo [vistaPrevia] pregunta:{}, tipoId:{}, sencilla:{}, subpreguntas:{} ", pregunta, tipoId, sencilla, subpreguntas);
        try {
            TipoPregunta tipoPregunta = tipoPreguntaService.buscarTipoPregunta(tipoId);
            Pregunta p = new Pregunta();
            p.setPregunta(pregunta);
            p.setTipoPregunta(tipoPregunta);
            List<ItemPregunta> itemPreguntas = new ArrayList<ItemPregunta>();
            for (String subpregunta : subpreguntas) {
                ItemPregunta itemPregunta = new ItemPregunta();
                itemPregunta.setItemPregunta(subpregunta);
                itemPreguntas.add(itemPregunta);
            }
            p.setItemPreguntas(itemPreguntas);
            model.addAttribute("pregunta", p);
            model.addAttribute("sencilla", sencilla);
        } catch (Exception e) {
            log.error("Ha ocurrido un error: " + e);
        }

        return "comiteCentral\\pregunta\\vistaPrevia";
    }

    @PostMapping(value = "/vistaPreviaEditar")
    public String vistaPreviaEditar(@RequestParam Integer preguntaId, Model model) {
        log.info("Ejecutanto metodo [vistaPrevia] pregunta:{}", preguntaId);
        try {
            Pregunta p = preguntaService.buscarPregunta(preguntaId);
            model.addAttribute("pregunta", p);
        } catch (Exception e) {
            log.error("Ha ocurrido un error: " + e);
        }

        return "comiteCentral\\pregunta\\vistaPrevia";
    }

    @PostMapping(value = "/crear")
    public ResponseEntity<?> crear(@RequestParam String pregunta, @RequestParam Integer tipoId, @RequestParam boolean sencilla, @RequestParam(value = "subpregunta[]") String[] subpreguntas, Model model) {
        log.info("Ejecutanto metodo [vistaPrevia] pregunta:{}, tipoId:{}, sencilla:{}, subpreguntas:{} ", pregunta, tipoId, sencilla, subpreguntas);
        HttpStatus status;
        try {
            TipoPregunta tipoPregunta = tipoPreguntaService.buscarTipoPregunta(tipoId);
            Pregunta p = new Pregunta();
            p.setPregunta(pregunta);
            p.setTipoPregunta(tipoPregunta);
            List<ItemPregunta> itemPreguntas = new ArrayList<ItemPregunta>();
            for (String subpregunta : subpreguntas) {
                ItemPregunta itemPregunta = new ItemPregunta();
                itemPregunta.setItemPregunta(subpregunta);
                itemPreguntas.add(itemPregunta);
            }
            p.setItemPreguntas(itemPreguntas);
            preguntaService.crearPregunta(p);
            status = HttpStatus.CREATED;
        } catch (Exception e) {
            log.error("Ha ocurrido un error: " + e);
            status = HttpStatus.CONFLICT;
        }
        return new ResponseEntity<>(status);
    }

    @PutMapping(value = "/editar")
    public ResponseEntity<?> editar(@RequestParam Integer preguntaId, @RequestParam String pregunta, @RequestParam Integer tipoId, @RequestParam boolean sencilla, @RequestParam(value = "subpregunta[]") String[] subpreguntas, Model model) {
        log.info("Ejecutanto metodo [vistaPrevia] pregunta:{}, tipoId:{}, sencilla:{}, subpreguntas:{} ", pregunta, tipoId, sencilla, subpreguntas);
        HttpStatus status;
        try {
            TipoPregunta tipoPregunta = tipoPreguntaService.buscarTipoPregunta(tipoId);
            Pregunta p = preguntaService.buscarPregunta(preguntaId);
            p.setPregunta(pregunta);
            p.setTipoPregunta(tipoPregunta);
            p.getItemPreguntas().clear();
            for (String subpregunta : subpreguntas) {
                ItemPregunta itemPregunta = new ItemPregunta();
                itemPregunta.setItemPregunta(subpregunta);
                p.getItemPreguntas().add(itemPregunta);
            }
            preguntaService.actualizarPregunta(p);
            status = HttpStatus.OK;
        } catch (Exception e) {
            log.error("Ha ocurrido un error: " + e);
            status = HttpStatus.CONFLICT;
        }
        return new ResponseEntity<>(status);
    }

}
