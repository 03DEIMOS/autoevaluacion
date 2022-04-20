package com.utb.autoevaluacion.controller;

import com.utb.autoevaluacion.model.Factor;
import com.utb.autoevaluacion.model.Modelo;
import com.utb.autoevaluacion.model.Proceso;
import com.utb.autoevaluacion.model.Programa;
import com.utb.autoevaluacion.service.FactorService;
import com.utb.autoevaluacion.service.FuenteService;
import com.utb.autoevaluacion.service.ModeloService;
import com.utb.autoevaluacion.service.ProcesoService;
import com.utb.autoevaluacion.service.ProgramaService;
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

@Slf4j
@Controller
@RequestMapping("/proceso")
public class ProcesoController {

    @Autowired
    ProcesoService procesoService;

    @Autowired
    FuenteService fuenteService;
    
    @Autowired
    private ModeloService modeloService;
    
    @Autowired
    private ProgramaService programaService;

    @GetMapping("/procesos")
    public String procesos(Model model) {
        model.addAttribute("listProcesos", procesoService.buscarProcesos());
        return "comiteCentral\\control\\controlPanel";
    }

    @GetMapping("/entrar/{id}")
    public String entrarProceso(@PathVariable Integer id, Model model) {
        List<Proceso> procesos = new ArrayList<Proceso>();
        Proceso p = procesoService.buscarProceso(id);
        procesos.add(p);
        model.addAttribute("listProceso", procesos);
        return "comitePrograma\\proceso\\detalle";
    }

    @GetMapping("/muestra/{procesoId}")
    public String muestra(@PathVariable Integer procesoId, Model model) {
        Proceso proceso = procesoService.buscarProceso(procesoId);
        model.addAttribute("proceso", proceso);
        model.addAttribute("fuentes", fuenteService.buscarFuentesXproceso(procesoId));
        return "comitePrograma\\muestra\\listarMuestra";
    }
    
    @GetMapping("/crear")
    public String formularioCrearModelo(Model model) {
        log.info("Ejecutanto metodo [formularioCrearModelo]");
        model.addAttribute("modelos", modeloService.getModelos());
        model.addAttribute("programas", programaService.getProgramas());
        return "comitePrograma\\proceso\\crear";
    }
    
    
    @PostMapping(value = "/crear")
    public ResponseEntity<?> crearProceso(@RequestParam Integer programaId, @RequestParam String descripcion, @RequestParam Integer modeloId) {

        log.info("Ejecutanto metodo crearProceso programaId:{}, descripcion:{}, modeloId:{} " , programaId, descripcion, modeloId);
        HttpStatus status;
        try {
            Proceso proceso = new Proceso();
            proceso.setDescripcion(descripcion);
            Modelo m = modeloService.buscarModelo(modeloId);
            Programa programa = programaService.buscarPrograma(programaId);
            proceso.setModeloId(m);
            proceso.setProgramaId(programa);
            proceso.setFechaCierre("--");
            proceso.setFechaInicio("En Configuración");
            proceso.setEstado("En Configuración");
            procesoService.crearProceso(proceso);
            status = HttpStatus.CREATED;
        } catch (Exception e) {
            log.error("Ha ocurrido un error: " + e);
            status = HttpStatus.CONFLICT;
        }
        return new ResponseEntity<>(status);
    }
}
