/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.controller;

import com.utb.autoevaluacion.dto.InformeCaracteristicasDTO;
import com.utb.autoevaluacion.dto.InformeDetalleCaracteristicaDTO;
import com.utb.autoevaluacion.dto.InformeFactoresDTO;
import com.utb.autoevaluacion.model.Caracteristica;
import com.utb.autoevaluacion.model.Factor;
import com.utb.autoevaluacion.model.Fuente;
import com.utb.autoevaluacion.model.Pregunta;
import com.utb.autoevaluacion.model.Proceso;
import com.utb.autoevaluacion.service.CaracteristicaService;
import com.utb.autoevaluacion.service.FactorService;
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
    private CaracteristicaService caracteristicaService;
    
    @Autowired
    private FactorService factorService;
    
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
    
    @GetMapping("/detallePCaracteristica/{procesoId}/{caracteristicaId}")
    public String informeDetallePorCaracteristica(@PathVariable Integer procesoId, @PathVariable Integer caracteristicaId, Model model) {
        log.info("Ejecutanto metodo [informeDetallePorCaracteristica] procesoId:{}, caracteristicaId:{} ", procesoId, caracteristicaId);
        InformeDetalleCaracteristicaDTO resultado = null;
        Caracteristica caracteristica = null;
        Proceso proceso = null;
        try {
            caracteristica = caracteristicaService.buscarCaracteristica(caracteristicaId);
            proceso = procesoService.buscarProceso(procesoId);
            resultado = informeService.informeDetallePorCaracteristica(procesoId, caracteristicaId);
        } catch (Exception e) {
            log.error("Ha ocurrido un error:{} ", e);
        }
        model.addAttribute("resultado", resultado);
        model.addAttribute("caracteristica", caracteristica);
        model.addAttribute("proceso", proceso);
        return "comitePrograma\\proceso\\informe\\detalleCaracteristicaP";
    }
    
    @GetMapping("/detallePFactor/{procesoId}/{factorId}")
    public String informeDetallePorFactor(@PathVariable Integer procesoId, @PathVariable Integer factorId, Model model) {
        log.info("Ejecutanto metodo [informeDetallePorFactor] procesoId:{}, factorId:{} ", procesoId, factorId);
        InformeCaracteristicasDTO resultado = null;
        Factor factor = null;
        Proceso proceso = null;
        try {
            factor = factorService.buscarFactor(factorId);
            proceso = procesoService.buscarProceso(procesoId);
            resultado = informeService.informeDetallePorFactor(procesoId, factorId);
        } catch (Exception e) {
            log.error("Ha ocurrido un error:{} ", e);
        }
        model.addAttribute("resultado", resultado);
        model.addAttribute("factor", factor);
        model.addAttribute("proceso", proceso);
        return "comitePrograma\\proceso\\informe\\detalleCaracteristicaP";
    }
    
}
