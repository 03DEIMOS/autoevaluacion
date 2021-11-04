/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.controller;

import com.utb.autoevaluacion.model.ItemTipoPregunta;
import com.utb.autoevaluacion.model.TipoPregunta;
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
 * @author ASUS
 */
@Slf4j
@Controller
@RequestMapping("/tipopregunta")
public class TipoPreguntaController {

    @Autowired
    private TipoPreguntaService tipoPreguntaService;

    @GetMapping("/tipospregunta")
    public String tiposPregunta(Model model) {
        model.addAttribute("listaTipoP", tipoPreguntaService.buscarTipoPreguntas());
        return "comiteCentral\\pregunta\\tipopregunta\\listar";

    }

    @GetMapping("/crear")
    public String formularioCrearTipoPregunta(Model model) {
        log.info("Ejecutanto metodo [formularioCrearTipoPregunta]");
        return "comiteCentral\\pregunta\\tipopregunta\\crear";
    }

    @GetMapping("/editar/{tipoPreguntaId}")
    public String formularioEditarTipoPregunta(@PathVariable Integer tipoPreguntaId, Model model) {
        log.info("Ejecutanto metodo [formularioEditarTipoPregunta]");
        TipoPregunta tipoPregunta = tipoPreguntaService.buscarTipoPregunta(tipoPreguntaId);
        model.addAttribute("tipoPregunta", tipoPregunta);
        return "comiteCentral\\pregunta\\tipopregunta\\editar";
    }

    @PostMapping(value = "/crear")
    public ResponseEntity<?> crearTipoPregunta(@RequestParam String tipo, @RequestParam String descripcion, @RequestParam(value = "respuesta[]") String[] respuestas, @RequestParam(value = "valor[]") Integer[] valores) {

        log.info("Ejecutanto metodo [crearTipoPregunta] tipo:{}, descripcion:{}, respuestas:{}, valores:{} ", tipo, descripcion, respuestas, valores);
        HttpStatus status;
        try {
            TipoPregunta tipoPregunta = new TipoPregunta();
            tipoPregunta.setDescripcion(descripcion);
            tipoPregunta.setTipo(tipo);

            List<ItemTipoPregunta> listItemTipoPregunta = new ArrayList<ItemTipoPregunta>();

            for (int i = 0; i < respuestas.length; i++) {
                ItemTipoPregunta itemTipoPregunta = new ItemTipoPregunta();
                itemTipoPregunta.setRespuesta(respuestas[i]);
                itemTipoPregunta.setValor(valores[i]);
                listItemTipoPregunta.add(itemTipoPregunta);
            }
            tipoPregunta.setItemTipoPreguntaList(listItemTipoPregunta);
            tipoPreguntaService.crearTipoPregunta(tipoPregunta);
            status = HttpStatus.CREATED;
        } catch (Exception e) {
            log.error("Ha ocurrido un error: " + e);
            status = HttpStatus.CONFLICT;
        }

        return new ResponseEntity<>(status);
    }

    @PutMapping(value = "/editar")
    public ResponseEntity<?> editarTipoPregunta(@RequestParam Integer tipoPreguntaid, @RequestParam String tipo, @RequestParam String descripcion, @RequestParam(value = "respuesta[]") String[] respuestas, @RequestParam(value = "valor[]") Integer[] valores) {
        log.info("Ejecutanto metodo [editarTipoPregunta] tipoPreguntaid:{}, tipo:{}, descripcion:{}, respuestas:{}, valores:{} ", tipoPreguntaid, tipo, descripcion, respuestas, valores);
        HttpStatus status;
        try {
            TipoPregunta tipoPregunta = tipoPreguntaService.buscarTipoPregunta(tipoPreguntaid);
            tipoPregunta.setDescripcion(descripcion);
            tipoPregunta.setTipo(tipo);
            tipoPregunta.getItemTipoPreguntaList().clear();
          

            for (int i = 0; i < respuestas.length; i++) {
                ItemTipoPregunta itemTipoPregunta = new ItemTipoPregunta();
                itemTipoPregunta.setRespuesta(respuestas[i]);
                itemTipoPregunta.setValor(valores[i]);
                tipoPregunta.getItemTipoPreguntaList().add(itemTipoPregunta);
            }
            tipoPreguntaService.actualizarTipoPregunta(tipoPregunta);
            status = HttpStatus.OK;
        } catch (Exception e) {
            log.error("Ha ocurrido un error: " + e);
            status = HttpStatus.CONFLICT;
        }
     
        return new ResponseEntity<>(status);
    }
}
