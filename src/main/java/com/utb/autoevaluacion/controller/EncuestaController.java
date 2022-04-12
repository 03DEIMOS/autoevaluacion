/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.controller;

import com.utb.autoevaluacion.model.Encuesta;
import com.utb.autoevaluacion.model.ItemPregunta;
import com.utb.autoevaluacion.model.Persona;
import com.utb.autoevaluacion.model.Pregunta;
import com.utb.autoevaluacion.model.ResultadoEvaluacion;
import com.utb.autoevaluacion.service.EncuestaService;
import com.utb.autoevaluacion.service.FuenteService;
import com.utb.autoevaluacion.service.PersonaService;
import com.utb.autoevaluacion.service.PreguntaService;
import com.utb.autoevaluacion.service.ResultadoEvaluacionService;
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

    @Autowired
    private PersonaService personaService;

    @Autowired
    private ResultadoEvaluacionService resultadoEvaluacionService;

    @Autowired
    private PreguntaService preguntaService;

    @Autowired
    private FuenteService fuenteService;

    @GetMapping("/encuestas")
    public String encuestas(Model model) {
        model.addAttribute("listaE", encuestaService.getEncuestas());
        return "comiteCentral\\encuesta\\listar";
    }

    @GetMapping("/crear")
    public String formularioCrearEncuesta(Model model) {
        log.info("Ejecutanto metodo [formularioCrearEncuesta] ");
        model.addAttribute("listaFu", fuenteService.buscarFuentes());
        model.addAttribute("listaP", preguntaService.getPreguntas());
        return "comiteCentral\\encuesta\\crear";
    }

    @GetMapping("/editar/{encuestaId}")
    public String formularioEditarEncuesta(@PathVariable Integer encuestaId, Model model) {
        log.info("Ejecutanto metodo [formularioEditarEncuesta] encuestaId:{} ", encuestaId);
        Encuesta encuesta = encuestaService.buscarEncuesta(encuestaId);
        model.addAttribute("encuesta", encuesta);
        model.addAttribute("listaFu", fuenteService.buscarFuentes());
        model.addAttribute("listaP", preguntaService.getPreguntas());
        return "comiteCentral\\encuesta\\editar";
    }

    @GetMapping("/vistaPrevia/{encuestaId}")
    public String encuestaVistaPrevia(@PathVariable Integer encuestaId, Model model) {
        log.info("Ejecutanto metodo [encuestaVistaPrevia] encuestaId:{} ", encuestaId);
        Encuesta encuesta = encuestaService.buscarEncuesta(encuestaId);
        model.addAttribute("encuesta", encuesta);
        return "comiteCentral\\encuesta\\vistaPrevia";
    }

    @PostMapping(value = "/crear")
    public ResponseEntity<?> crearEncuesta(@RequestParam String codigo, @RequestParam String nombre, @RequestParam String objetivo, @RequestParam String instrucciones, @RequestParam String version, @RequestParam String fecha, @RequestParam Integer fuenteId, @RequestParam Map<String, String> requestParams) {

        log.info("Ejecutanto metodo [crearEncuesta] codigo:{}, nombre:{}, objetivo:{}, instrucciones:{}, version:{}, fecha:{}, fuenteId:{} ", codigo, nombre, objetivo, instrucciones, version, fecha, fuenteId);
        HttpStatus status;
        try {
            List<Pregunta> preguntas = preguntaService.getPreguntas();
            List<Pregunta> aux = new ArrayList<>();
            if (preguntas != null) {
                preguntas.stream().filter((pregunta) -> (requestParams.get("P" + pregunta.getId()).equals("1"))).forEachOrdered((pregunta) -> {
                    aux.add(pregunta);
                });
            }
            encuestaService.crearEncuesta(codigo, nombre, objetivo, instrucciones, version, fecha, fuenteId, aux);
            status = HttpStatus.CREATED;
        } catch (Exception e) {
            log.error("Ha ocurrido un error: " + e);
            status = HttpStatus.CONFLICT;
        }

        return new ResponseEntity<>(status);
    }

    @PostMapping(value = "/enviar")
    public ResponseEntity<?> enviarEncuesta(@RequestParam Map<String, String> allParams) {

        log.info("Ejecutanto metodo [enviarEncuesta] allParams:{}", allParams);
        HttpStatus status;
        try {

            int encuestaId = Integer.parseInt(allParams.get("encuestaId"));
            Encuesta encuesta = encuestaService.buscarEncuesta(encuestaId);

            int personaId = Integer.parseInt(allParams.get("personaId"));
            Persona persona = personaService.buscarPersona(personaId);

            List<Pregunta> preguntas = encuesta.getPreguntaList();
            List<ResultadoEvaluacion> resultadosEvaluacion = new ArrayList<ResultadoEvaluacion>();

            for (Pregunta pregunta : preguntas) {
                List<ItemPregunta> itemsPregunta = pregunta.getItemPreguntas();
                if (!itemsPregunta.isEmpty()) {
                    for (ItemPregunta itemPregunta : itemsPregunta) {
                        ResultadoEvaluacion resultadoEvaluacion = new ResultadoEvaluacion();
                        resultadoEvaluacion.setPersona(persona);
                        resultadoEvaluacion.setPregunta(pregunta);
                        resultadoEvaluacion.setTipoPregunta(pregunta.getTipoPregunta());
                        resultadoEvaluacion.setItemPregunta(itemPregunta);
                        resultadoEvaluacion.setRespuesta(allParams.get("i" + itemPregunta.getId()));
                        resultadosEvaluacion.add(resultadoEvaluacion);
                    }
                } else {
                    ResultadoEvaluacion resultadoEvaluacion = new ResultadoEvaluacion();
                    resultadoEvaluacion.setPersona(persona);
                    resultadoEvaluacion.setPregunta(pregunta);
                    resultadoEvaluacion.setTipoPregunta(pregunta.getTipoPregunta());
                    resultadoEvaluacion.setRespuesta(allParams.get("" + pregunta.getId()));
                    resultadosEvaluacion.add(resultadoEvaluacion);
                }
            }

            resultadoEvaluacionService.asignarResultadoEvaluacion(resultadosEvaluacion);
            status = HttpStatus.OK;
        } catch (Exception e) {
            log.error("Ha ocurrido un error:{}", e);
            status = HttpStatus.CONFLICT;
        }

        return new ResponseEntity<>(status);
    }

    @PutMapping(value = "/editar")
    public ResponseEntity<?> editarEncuesta(@RequestParam Integer encuestaId, @RequestParam String codigo, @RequestParam String nombre, @RequestParam String objetivo, @RequestParam String instrucciones, @RequestParam String version, @RequestParam String fecha, @RequestParam Integer fuenteId, @RequestParam Map<String, String> requestParams) {
        log.info("Ejecutanto metodo [editarEncuesta] encuestaId:{}, codigo:{}, nombre:{}, objetivo:{}, instrucciones:{}, version:{}, fecha:{}, fuenteId:{} ", encuestaId, codigo, nombre, objetivo, instrucciones, version, fecha, fuenteId);
        HttpStatus status;
        try {
            List<Pregunta> preguntas = preguntaService.getPreguntas();
            List<Pregunta> aux = new ArrayList<>();
            if (preguntas != null) {
                preguntas.stream().filter((pregunta) -> (requestParams.get("P" + pregunta.getId()).equals("1"))).forEachOrdered((pregunta) -> {
                    aux.add(pregunta);
                });
            }
            encuestaService.actualizarEncuesta(encuestaId, codigo, nombre, objetivo, instrucciones, version, fecha, fuenteId, aux);
            status = HttpStatus.OK;
        } catch (Exception e) {
            log.error("Ha ocurrido un error: " + e);
            status = HttpStatus.CONFLICT;
        }
        return new ResponseEntity<>(status);
    }

}
