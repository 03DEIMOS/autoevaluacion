package com.utb.autoevaluacion.controller;

import com.utb.autoevaluacion.model.Caracteristica;
import com.utb.autoevaluacion.model.OportunidadMejora;
import com.utb.autoevaluacion.model.PlanMejoramiento;
import com.utb.autoevaluacion.model.Proceso;
import com.utb.autoevaluacion.model.Seguimiento;
import com.utb.autoevaluacion.model.TipoAccion;
import com.utb.autoevaluacion.service.CaracteristicaService;
import com.utb.autoevaluacion.service.FactorService;
import com.utb.autoevaluacion.service.OportunidadMejoraService;
import com.utb.autoevaluacion.service.PlanMejoramientoService;
import com.utb.autoevaluacion.service.ProcesoService;
import com.utb.autoevaluacion.service.SeguimientoService;
import com.utb.autoevaluacion.service.TipoAccionService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author DEIMOS
 */
@Slf4j
@Controller
@RequestMapping("/oportunidadMejora")

public class OportunidadMejoraController {

    @Autowired
    private PlanMejoramientoService planMejoramientoService;

    @Autowired
    private CaracteristicaService caracteristicaService;

    @Autowired
    private FactorService factorService;

    @Autowired
    private TipoAccionService tipoAccionService;

    @Autowired
    private SeguimientoService seguimientoService;

    @Autowired
    private OportunidadMejoraService oportunidadMejoraService;

    @GetMapping("/oportunidadesMejora/{planMejoramientoId}")
    public String oportunidadesMejora(@PathVariable Integer planMejoramientoId, Model model) {
        model.addAttribute("listaO", oportunidadMejoraService.getOportunidadMejoraByPlanMejoramiento(planMejoramientoId));
        model.addAttribute("planMejoramientoId", planMejoramientoId);
        return "comitePrograma\\proceso\\planMejoramiento\\oportunidadMejora\\listar";

    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<?> eliminarOportunidadMejora(@RequestParam Integer oportunidadMejoramientoId) {

        log.info("Ejecutanto metodo [eliminarOportunidadMejora] oportunidadMejoramientoId:{}", oportunidadMejoramientoId);
        HttpStatus status;
        try {
            oportunidadMejoraService.eliminarOportunidadMejora(oportunidadMejoramientoId);
            status = HttpStatus.CREATED;
        } catch (Exception e) {
            log.error("Ha ocurrido un error:{} ", e);
            status = HttpStatus.CONFLICT;
        }
        return new ResponseEntity<>(status);
    }

    @GetMapping("/crear/{planMejoramientoId}")
    public String formularioCrearOportunidadMejora(@PathVariable Integer planMejoramientoId, Model model) {
        log.info("Ejecutanto método [formularioCrearOportunidadMejora] planMejoramientoId:{} ", planMejoramientoId);
        model.addAttribute("planMejoramientoId", planMejoramientoId);
        model.addAttribute("listaF", factorService.getFactores());
        model.addAttribute("listaC", caracteristicaService.getCaracteristicas());
        model.addAttribute("tiposAccion", tipoAccionService.getTiposAccion());
        return "comitePrograma\\proceso\\planMejoramiento\\oportunidadMejora\\crear";
    }

    @GetMapping("/clonar/{planMejoramientoId}")
    public String formularioClonarOportunidadMejora(@PathVariable Integer planMejoramientoId, Model model) {
        log.info("Ejecutanto método [formularioClonarOportunidadMejora] planMejoramientoId:{} ", planMejoramientoId);
        model.addAttribute("planMejoramientoId", planMejoramientoId);
        model.addAttribute("planesMejoramiento", planMejoramientoService.buscarPlanesMejoramiento());
        return "comitePrograma\\proceso\\planMejoramiento\\oportunidadMejora\\clonar";
    }

    @GetMapping("/copiarOportunidades/{planMejoramientoId}")
    public String formularioCopiarOportunidadMejora(@PathVariable Integer planMejoramientoId, Model model) {
        log.info("Ejecutanto método [formularioCopiarOportunidadMejora] planMejoramientoId:{} ", planMejoramientoId);
        model.addAttribute("planMejoramientoId", planMejoramientoId);
        model.addAttribute("planesMejoramiento", planMejoramientoService.buscarPlanesMejoramientoInstitucionales());
        return "comitePrograma\\proceso\\planMejoramiento\\oportunidadMejora\\copiarInstitucionales";
    }

    @GetMapping("/editar/{idHallazgo}")
    public String formularioEditarOportunidadMejora(@PathVariable Integer idHallazgo, Model model) {
        log.info("Ejecutanto metodo [formularioEditarOportunidadMejora] idHallazgo:{} ", idHallazgo);
        OportunidadMejora oportunidadMejora = oportunidadMejoraService.buscarOportunidadMejora(idHallazgo);
        model.addAttribute("listaF", factorService.getFactores());
        model.addAttribute("listaC", caracteristicaService.getCaracteristicasByFactor(oportunidadMejora.getCaracteristicaId().getFactorId().getId()));
        model.addAttribute("planMejoramientoId", oportunidadMejora.getPlanMejoramientoId().getId());
        model.addAttribute("tiposAccion", tipoAccionService.getTiposAccion());
        model.addAttribute("oportunidadMejora", oportunidadMejora);
        return "comitePrograma\\proceso\\planMejoramiento\\oportunidadMejora\\editar";
    }

    @GetMapping("/reporteCuantitativo/{planMejoramientoId}")
    public String reporteCuantitativo(@PathVariable Integer planMejoramientoId, Model model) {
        log.info("Ejecutanto metodo [reporteCuantitativo] planMejoramientoId:{} ", planMejoramientoId);
        model.addAttribute("planMejoramientoId", planMejoramientoId);
        List<TipoAccion> tiposAccion = tipoAccionService.getTiposAccion();
        Integer cantidad[] = new Integer[tiposAccion.size()];
        for (int i = 0; i < tiposAccion.size(); i++) {
            cantidad[i] = oportunidadMejoraService.getOportunidadMejoraByPlanMejoramientoAndStatus(planMejoramientoId, tiposAccion.get(i)).size();

        }
        model.addAttribute("cantidad", cantidad);
        model.addAttribute("tiposAccion", tiposAccion);
        return "comitePrograma\\proceso\\informe\\reporteCuantitativo";
    }

    @GetMapping("/reporteSeguimiento/{planMejoramientoId}")
    public String reporteSeguimiento(@PathVariable Integer planMejoramientoId, Model model) {
        log.info("Ejecutanto metodo [reporteSeguimiento] planMejoramientoId:{} ", planMejoramientoId);
        model.addAttribute("planMejoramiento", planMejoramientoService.buscarPlanMejoramiento(planMejoramientoId));
        List<OportunidadMejora> oportunidadesMejoraByPlanMejoramiento = oportunidadMejoraService.getOportunidadMejoraByPlanMejoramiento(planMejoramientoId);
        for (OportunidadMejora oportunidadMejora : oportunidadesMejoraByPlanMejoramiento) {
            List<Seguimiento> seguimientoByOportunidadMejora = seguimientoService.getSeguimientoByOportunidadMejora(oportunidadMejora.getIdHallazgo());
            oportunidadMejora.setSeguimientos(seguimientoByOportunidadMejora);
        }
        model.addAttribute("oportunidadesMejora", oportunidadesMejoraByPlanMejoramiento);
        return "comitePrograma\\proceso\\informe\\reporteSeguimiento";
    }

    @PostMapping(value = "/crear")
    public ResponseEntity<?> crearOportunidadMejora(@RequestParam String oportunidadMejoramiento, @RequestParam Integer planMejoramientoId,
            @RequestParam Integer caracteristicaId, @RequestParam String eje, @RequestParam String lineaAccion, @RequestParam Integer estadoId,
            @RequestParam String tipo, @RequestParam String responsable, @RequestParam String fechaInicio, @RequestParam String fechaFinal,
            @RequestParam String recurso, @RequestParam String indicador, @RequestParam String meta, @RequestParam String lineaBase) {

        log.info("Ejecutanto metodo [crearOportunidadMejora] "
                + "oportunidadMejoramiento:{}, planMejoramientoId:{}, caracteristicaId:{}, ejeEstrategico:{}, lineaAccion:{}, estadoId:{}, tipo:{}, responsable:{}, "
                + "fechaInicio:{}, fechaFinal:{}, recurso:{}, indicador:{}, meta:{}, lineaBase:{}",
                oportunidadMejoramiento, planMejoramientoId, caracteristicaId, eje, lineaAccion, estadoId, tipo, responsable, fechaInicio, fechaFinal,
                recurso, indicador, meta, lineaBase);
        HttpStatus status;
        try {

            oportunidadMejoraService.crearOportunidadMejora(oportunidadMejoramiento, planMejoramientoId, caracteristicaId, eje, lineaAccion, estadoId, tipo,
                    responsable, fechaInicio, fechaFinal, recurso, indicador, meta, lineaBase);
            status = HttpStatus.CREATED;
        } catch (Exception e) {
            log.error("Ha ocurrido un error:{} ", e);
            status = HttpStatus.CONFLICT;
        }
        return new ResponseEntity<>(status);
    }

    @PostMapping(value = "/clonar")
    public ResponseEntity<?> clonarOportunidadesMejora(@RequestParam Integer planMejoramientoOrigen, @RequestParam Integer planMejoramientoDestino) {
        log.info("Ejecutanto metodo [clonarOportunidadesMejora] "
                + "planMejoramientoOrigen:{}, planMejoramientoDestino:{}", planMejoramientoOrigen, planMejoramientoDestino);
        HttpStatus status;
        try {
            List<OportunidadMejora> oportunidadMejoraOrigen = oportunidadMejoraService.getOportunidadMejoraByPlanMejoramiento(planMejoramientoOrigen);
            for (OportunidadMejora oportunidadMejora : oportunidadMejoraOrigen) {
                oportunidadMejoraService.crearOportunidadMejora(
                        oportunidadMejora.getHallazgo(),
                        planMejoramientoDestino,
                        oportunidadMejora.getCaracteristicaId().getId(),
                        oportunidadMejora.getEje(),
                        oportunidadMejora.getLineaAccion(),
                        oportunidadMejora.getEstadoId().getId(),
                        oportunidadMejora.getTipo(),
                        oportunidadMejora.getResponsable(),
                        oportunidadMejora.getFechaInicio(),
                        oportunidadMejora.getFechaFin(),
                        oportunidadMejora.getRecurso(),
                        oportunidadMejora.getIndicador(),
                        oportunidadMejora.getMeta(),
                        oportunidadMejora.getLineaBase()
                );
            }
            status = HttpStatus.CREATED;
        } catch (Exception e) {
            log.error("Ha ocurrido un error:{} ", e);
            status = HttpStatus.CONFLICT;
        }
        return new ResponseEntity<>(status);
    }

    @PostMapping(value = "/copiarInstitucionales")
    public ResponseEntity<?> copiarInstitucionales(@RequestParam Integer planMejoramientoOrigen, @RequestParam Integer planMejoramientoDestino, @RequestParam Integer factorId) {
        log.info("Ejecutanto metodo [copiarInstitucionales] "
                + "planMejoramientoOrigen:{}, planMejoramientoDestino:{}, factorId:{}", planMejoramientoOrigen, planMejoramientoDestino, factorId);
        HttpStatus status;
        try {
            List<OportunidadMejora> oportunidadMejoraOrigen = oportunidadMejoraService.getOportunidadMejoraByPlanMejoramientoAndFactor(planMejoramientoOrigen, factorId);
            for (OportunidadMejora oportunidadMejora : oportunidadMejoraOrigen) {
                oportunidadMejoraService.crearOportunidadMejora(
                        oportunidadMejora.getHallazgo(),
                        planMejoramientoDestino,
                        oportunidadMejora.getCaracteristicaId().getId(),
                        oportunidadMejora.getEje(),
                        oportunidadMejora.getLineaAccion(),
                        oportunidadMejora.getEstadoId().getId(),
                        oportunidadMejora.getTipo(),
                        oportunidadMejora.getResponsable(),
                        oportunidadMejora.getFechaInicio(),
                        oportunidadMejora.getFechaFin(),
                        oportunidadMejora.getRecurso(),
                        oportunidadMejora.getIndicador(),
                        oportunidadMejora.getMeta(),
                        oportunidadMejora.getLineaBase()
                );
            }
            status = HttpStatus.CREATED;
        } catch (Exception e) {
            log.error("Ha ocurrido un error:{} ", e);
            status = HttpStatus.CONFLICT;
        }
        return new ResponseEntity<>(status);
    }

    @PutMapping(value = "/editar")
    public ResponseEntity<?> editarOportunidadMejora(@RequestParam Integer hallazgoId, @RequestParam String oportunidadMejoramiento, @RequestParam Integer planMejoramientoId,
            @RequestParam Integer caracteristicaId, @RequestParam String eje, @RequestParam String lineaAccion, @RequestParam Integer estadoId, @RequestParam String tipo,
            @RequestParam String responsable, @RequestParam String fechaInicio, @RequestParam String fechaFinal,
            @RequestParam String recurso, @RequestParam String indicador, @RequestParam String meta, @RequestParam String lineaBase) {
        log.info("Ejecutanto metodo [editarOportunidadMejora] "
                + "hallazgoId:{}, oportunidadMejoramiento:{}, planMejoramientoId:{}, caracteristicaId:{}, eje:{}, lineaAccion:{}, estadoId:{}, tipo:{}, responsable:{}, "
                + "fechaInicio:{}, fechaFinal:{}, recurso:{}, indicador:{}, meta:{}, lineaBase:{} ",
                hallazgoId, oportunidadMejoramiento, planMejoramientoId, caracteristicaId, eje, lineaAccion, estadoId, tipo, responsable, fechaInicio, fechaFinal,
                recurso, indicador, meta, lineaBase);
        HttpStatus status;
        try {
            oportunidadMejoraService.actualizarOportunidadMejora(hallazgoId, oportunidadMejoramiento, planMejoramientoId, caracteristicaId, eje,
                    lineaAccion, estadoId, tipo, responsable, fechaInicio, fechaFinal, recurso, indicador, meta, lineaBase);
            status = HttpStatus.OK;
        } catch (Exception e) {
            log.error("Ha ocurrido un error:{} ", e);
            status = HttpStatus.CONFLICT;
        }
        return new ResponseEntity<>(status);
    }

}
