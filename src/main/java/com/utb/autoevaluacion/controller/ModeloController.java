package com.utb.autoevaluacion.controller;

import com.utb.autoevaluacion.model.Modelo;
import com.utb.autoevaluacion.service.ModeloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequestMapping("/modelo")
public class ModeloController {
   
   @Autowired 
   private ModeloService modeloService;

    public ModeloController(ModeloService modeloService) {
        this.modeloService = modeloService;
    }

    @GetMapping("/modelos")
    public String modelos(Model model) {
        model.addAttribute("listaM", modeloService.getModelos());
        return "comiteCentral\\modelo\\listar";
        
    } 
    
    @GetMapping("/entrar/{id}" )
    public String entrarModelo(@PathVariable Integer id, Model model) {
        model.addAttribute("modelo", modeloService.getModeloById(id));
        return "comiteCentral\\inicio";
        
    } 
    
    @PostMapping(value = "/crear" )
    public ResponseEntity<?> crearModelo(@RequestParam String nombre, @RequestParam String descripcion) {
        
        log.info("Ejecutanto metodo [crearModelo] nombre:{}, descripcion:{} ", nombre, descripcion);
        HttpStatus status;
        try {
            modeloService.crearModelo(nombre, descripcion);
            status = HttpStatus.CREATED;
        } catch (Exception e) {
            log.error("Ha ocurrido un error: " + e);
            status = HttpStatus.CONFLICT;
        }

        return new ResponseEntity<>(status);
        
    } 
    
    
    @PostMapping("/editar/{id}" )
    public String editarModelo(@PathVariable Integer id,Model model) {
        //model.addAttribute("modelo", modeloService.createModelo(modelo));
        return "comiteCentral\\modelo\\listar";
        
    } 
}
