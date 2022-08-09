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
import com.utb.autoevaluacion.service.UtilitarioService;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
        model.addAttribute("listaC", Collections.emptyList());
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

    @RequestMapping(path = "/descargarReporteSeguimiento/{planMejoramientoId}", method = RequestMethod.GET)
    public ResponseEntity<Resource> descargarReporteSeguimiento(@PathVariable Integer planMejoramientoId) throws IOException {
        log.info("Ejecutanto metodo [descargarReporteSeguimiento] planMejoramientoId:{} ", planMejoramientoId);
        List<OportunidadMejora> oportunidadesMejora = oportunidadMejoraService.getOportunidadMejoraByPlanMejoramiento(planMejoramientoId);
        for (OportunidadMejora oportunidadMejora : oportunidadesMejora) {
            List<Seguimiento> seguimientoByOportunidadMejora = seguimientoService.getSeguimientoByOportunidadMejora(oportunidadMejora.getIdHallazgo());
            oportunidadMejora.setSeguimientos(seguimientoByOportunidadMejora);
        }

        byte[] excelReport = OportunidadMejoraController.buildExcelReport(oportunidadesMejora);

        try {
            HttpHeaders header = new HttpHeaders();
            header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reporteSeguimientos.xls");
            header.add("Cache-Control", "no-cache, no-store, must-revalidate");
            header.add("Pragma", "no-cache");
            header.add("Expires", "0");

            ByteArrayResource resource = new ByteArrayResource(excelReport);

            return ResponseEntity.ok()
                    .headers(header)
                    .contentLength(excelReport.length)
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .body(resource);
        } catch (Exception ex) {
            log.error("ha ocurrido un error:{}", ex);
            return ResponseEntity.badRequest().body(null);
        }

    }

    @PostMapping(value = "/crear")
    public ResponseEntity<?> crearOportunidadMejora(@RequestParam String oportunidadMejoramiento, @RequestParam Integer planMejoramientoId,
            @RequestParam Integer caracteristicaId, @RequestParam String eje, @RequestParam String lineaAccion, 
            @RequestParam String tipo, @RequestParam String responsable, @RequestParam String fechaInicio, @RequestParam String fechaFinal,
            @RequestParam String recurso, @RequestParam String indicador, @RequestParam String meta, @RequestParam String lineaBase) {
        
        /*
         <div class="control-group">
                        <label for="estadoId" class="control-label">Estado</label>
                        <div class="controls">
                            <select id="estadoId" name="estadoId" class="{required:true}">
                                <option></option>
                                <c:forEach items="${tiposAccion}" var="tipoAccion" varStatus="iter">
                                    <option value="${tipoAccion.id}">${tipoAccion.tipo}</option>
                                </c:forEach>
                            </select>     
                        </div>
                    </div>
        */
        
        log.info("Ejecutanto metodo [crearOportunidadMejora] "
                + "oportunidadMejoramiento:{}, planMejoramientoId:{}, caracteristicaId:{}, ejeEstrategico:{}, lineaAccion:{}, tipo:{}, responsable:{}, "
                + "fechaInicio:{}, fechaFinal:{}, recurso:{}, indicador:{}, meta:{}, lineaBase:{}",
                oportunidadMejoramiento, planMejoramientoId, caracteristicaId, eje, lineaAccion, tipo, responsable, fechaInicio, fechaFinal,
                recurso, indicador, meta, lineaBase);
        HttpStatus status;
        try {
            TipoAccion estadoAbierta = tipoAccionService.getTipoAccionByTipo("Abierta");
            oportunidadMejoraService.crearOportunidadMejora(oportunidadMejoramiento, planMejoramientoId, caracteristicaId, eje, lineaAccion, estadoAbierta.getId(), tipo,
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

    private static byte[] buildExcelReport(List<OportunidadMejora> oportunidadesMejora)
            throws FileNotFoundException, IOException {
        log.info("Ejecutando buildExcelReport(): oportunidadesMejora[" + oportunidadesMejora + "]");
        int numberRow = 3;
        InputStream excelFile = new FileInputStream(new ClassPathResource("reporteSeguimientos.xls").getFile());
        //new FileInputStream(new File("config/plantillas/reporteSeguimientos.xls"));
        HSSFWorkbook workbook = new HSSFWorkbook(excelFile);
        Sheet sheet = workbook.getSheetAt(0);

        for (OportunidadMejora item : oportunidadesMejora) {
            Row newRow = sheet.createRow(++numberRow);

            Cell cellFactor = newRow.createCell(0);
            cellFactor.setCellValue(item.getCaracteristicaId().getFactorId().getNombre());

            Cell cellCaracteristica = newRow.createCell(1);
            cellCaracteristica.setCellValue(item.getCaracteristicaId().getNombre());

            Cell cellOportunidadMejora = newRow.createCell(2);
            cellOportunidadMejora.setCellValue(item.getHallazgo());

            Cell cellEjeEstrategico = newRow.createCell(3);
            cellEjeEstrategico.setCellValue(item.getEje());

            Cell cellLineaAccion = newRow.createCell(4);
            cellLineaAccion.setCellValue(item.getLineaAccion());

            Cell cellEstado = newRow.createCell(5);
            cellEstado.setCellValue(item.getEstadoId().getTipo());

            Cell cellTipo = newRow.createCell(6);
            cellTipo.setCellValue(item.getTipo());

            Cell cellResponsable = newRow.createCell(7);
            cellResponsable.setCellValue(item.getResponsable());

            Cell cellFechaInicio = newRow.createCell(8);
            cellFechaInicio.setCellValue(item.getFechaInicio());

            Cell cellFechaFinal = newRow.createCell(9);
            cellFechaFinal.setCellValue(item.getFechaFin());

            Cell cellRecurso = newRow.createCell(10);
            cellRecurso.setCellValue(item.getRecurso());

            Cell cellIndicador = newRow.createCell(11);
            cellIndicador.setCellValue(item.getIndicador());

            Cell cellMeta = newRow.createCell(12);
            cellMeta.setCellValue(item.getMeta());

            Cell cellLineaBase = newRow.createCell(13);
            cellLineaBase.setCellValue(item.getLineaBase());
        }
        numberRow = 3;
        Sheet sheet2 = workbook.getSheetAt(1);
        for (OportunidadMejora oportunidadMejora : oportunidadesMejora) {
            for (Seguimiento seguimiento : oportunidadMejora.getSeguimientos()) {
                Row newRow = sheet2.createRow(++numberRow);
                Cell cellFactor = newRow.createCell(0);
                cellFactor.setCellValue(oportunidadMejora.getCaracteristicaId().getFactorId().getNombre());

                Cell cellCaracteristica = newRow.createCell(1);
                cellCaracteristica.setCellValue(oportunidadMejora.getCaracteristicaId().getNombre());

                Cell cellOportunidadMejora = newRow.createCell(2);
                cellOportunidadMejora.setCellValue(oportunidadMejora.getHallazgo());

                Cell cellFechaRealizada = newRow.createCell(3);
                cellFechaRealizada.setCellValue(seguimiento.getFechaRealizado());

                Cell cellAvances = newRow.createCell(4);
                cellAvances.setCellValue(seguimiento.getAvances());
                
                Cell cellEstado = newRow.createCell(5);
                cellEstado.setCellValue(seguimiento.getEstado());
            }

        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        workbook.write(bos);
        byte[] fileWithError = bos.toByteArray();

        log.info("Resultado buildExcelReport() ", Arrays.toString(fileWithError));
        return fileWithError;
    }
}
