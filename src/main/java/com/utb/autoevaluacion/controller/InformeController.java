/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.controller;

import com.utb.autoevaluacion.dto.InformeCaracteristicasDTO;
import com.utb.autoevaluacion.dto.InformeFactoresDTO;
import com.utb.autoevaluacion.model.Fuente;
import com.utb.autoevaluacion.model.Pregunta;
import com.utb.autoevaluacion.model.Proceso;
import com.utb.autoevaluacion.service.FuenteService;
import com.utb.autoevaluacion.service.InformeService;
import com.utb.autoevaluacion.service.PreguntaService;
import com.utb.autoevaluacion.service.ProcesoService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ASUS
 */
@Slf4j
@Controller
@RequestMapping("/informe")
public class InformeController {

    @Autowired
    InformeService informeService;

    @Autowired
    ProcesoService procesoService;

    @Autowired
    private PreguntaService preguntaService;
    
    @Autowired
    private FuenteService fuenteService;

    @GetMapping("/estadoGeneralDelProceso/{procesoId}")
    public String estadoGeneralDelProceso(@PathVariable Integer procesoId, Model model) {
        Proceso proceso = null;
        List<Fuente> fuentes = null;
        List<Object> resultado = null;
        try {
            proceso = procesoService.buscarProceso(procesoId);
            fuentes = fuenteService.buscarFuentesXproceso(procesoId);
            resultado = informeService.estadoGeneralDelProceso(procesoId);
        } catch (Exception e) {
            log.error("Ha ocurrido un error: " + e);
        }
        model.addAttribute("resultado", resultado);
        model.addAttribute("proceso", proceso);
        model.addAttribute("fuentes", fuentes);
        return "comitePrograma\\proceso\\informe\\estadoProceso";
    }

    @GetMapping("/informeDMA/{procesoId}")
    public String informeDMAPorProceso(@PathVariable Integer procesoId, Model model) {

        List<Object> resultado = null;
        try {
            resultado = informeService.informeDMAPorProceso(procesoId);
        } catch (Exception e) {
            log.error("Ha ocurrido un error:{} ", e);
        }
        model.addAttribute("resultado", resultado);
        return "comitePrograma\\proceso\\informe\\dmaProgramas";
    }
    
    @GetMapping("/informeCaracteristicas/{procesoId}")
    public String informeCaracteristicasPorProceso(@PathVariable Integer procesoId, Model model) {

        InformeCaracteristicasDTO resultado = null;
        try {
            resultado = informeService.informeCaracteristicasPorProceso(procesoId);
        } catch (Exception e) {
            log.error("Ha ocurrido un error:{} ", e);
        }
        model.addAttribute("resultado", resultado);
        return "comitePrograma\\proceso\\informe\\matrizCaracteristicasP";
    }
    
    @GetMapping("/informeFactores/{procesoId}")
    public String informeFactoresPorProceso(@PathVariable Integer procesoId, Model model) {

        InformeFactoresDTO resultado = null;
        try {
            resultado = informeService.informeFactoresPorProceso(procesoId);
        } catch (Exception e) {
            log.error("Ha ocurrido un error:{} ", e);
        }
        model.addAttribute("resultado", resultado);
        return "comitePrograma\\proceso\\informe\\matrizFactoresP";
    }

    @GetMapping("/informePreguntas/{procesoId}")
    public String informePreguntasPorProceso(@PathVariable Integer procesoId, Model model) {
        log.info("Ejecutanto metodo [informePreguntasPorProceso] procesoId:{} ", procesoId);
        List<Object> resultado = null;
        try {
            resultado = informeService.informePreguntasPorProceso(procesoId);
        } catch (Exception e) {
            log.error("Ha ocurrido un error:{} ", e);
        }
        model.addAttribute("resultado", resultado);
        return "comitePrograma\\proceso\\informe\\informePreguntas";
    }
    
    @GetMapping("/informePreguntas/proceso/{procesoId}/publico/{fuenteId}")
    public String informePreguntasPorProceso(@PathVariable Integer procesoId, @PathVariable Integer fuenteId, Model model) {
        log.info("Ejecutanto metodo [informePreguntasPorProceso] procesoId:{}, fuenteId:{} ", procesoId, fuenteId);
        List<Object> resultado = null;
        try {
            resultado = informeService.informePreguntasPorProcesoyPublico(procesoId, fuenteId);
        } catch (Exception e) {
            log.error("Ha ocurrido un error:{} ", e);
        }
        model.addAttribute("resultado", resultado);
        return "comitePrograma\\proceso\\informe\\informePreguntas";
    }
}
