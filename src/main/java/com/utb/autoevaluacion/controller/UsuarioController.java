/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.controller;

import com.utb.autoevaluacion.model.Usuario;
import com.utb.autoevaluacion.service.ProgramaService;
import com.utb.autoevaluacion.service.UsuarioService;
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
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private ProgramaService programaService;
    
    @GetMapping("/usuarios")
    public String usuarios(Model model) {
        model.addAttribute("usuarios", usuarioService.buscarUsuarios());
        return "comiteCentral\\usuario\\listar";

    }
    
    @GetMapping("/crear")
    public String crearUsuario(Model model) {
        log.info("Ejecutanto metodo [crearUsuario]");
        model.addAttribute("programas", programaService.getProgramas());
        return "comiteCentral\\usuario\\crear";

    }
    
    @GetMapping("/editar/{usuarioId}")
    public String editarUsuario(@PathVariable Integer usuarioId, Model model) {
        log.info("Ejecutanto metodo [editarUsuario] usuarioId:{}",usuarioId);
        Usuario usuario = usuarioService.buscarUsuarioPorId(usuarioId);
        model.addAttribute("usuario", usuario);
        model.addAttribute("programas", programaService.getProgramas());
        return "comiteCentral\\usuario\\editar";

    }
    
    @GetMapping("/contrasena/{usuarioId}")
    public String cambiarContrasenaUsuario(@PathVariable Integer usuarioId, Model model) {
        log.info("Ejecutanto metodo [cambiarContrasena] usuarioId:{}",usuarioId);
        Usuario usuario = usuarioService.buscarUsuarioPorId(usuarioId);
        model.addAttribute("usuario", usuario);
        return "comiteCentral\\usuario\\contrasena";

    }
    
    @PostMapping(value = "/crear")
    public ResponseEntity<?> crearUsuario(@RequestParam String codigo, @RequestParam String identificacion, @RequestParam String nombre,
            @RequestParam String apellidos, @RequestParam String clave, @RequestParam String email, @RequestParam Integer[] programas) {

        log.info("Ejecutanto metodo [crearUsuario] codigo:{}, identificacion:{}, nombre:{}, apellidos:{}, clave:{}, email:{}, programas:{}",
                codigo, identificacion, nombre, apellidos, clave, email, programas);
        HttpStatus status;
        try {
            usuarioService.crearUsuario(codigo, identificacion, nombre, apellidos, clave, email, programas);
            status = HttpStatus.CREATED;
        } catch (Exception e) {
            log.error("Ha ocurrido un error: " + e);
            status = HttpStatus.CONFLICT;
        }

        return new ResponseEntity<>(status);
    }
    
    @PutMapping(value = "/editar")
    public ResponseEntity<?> editarUsuario(@RequestParam Integer usuarioId, @RequestParam String codigo, @RequestParam String identificacion, 
            @RequestParam String nombre, @RequestParam String apellidos, @RequestParam String email, @RequestParam Integer[] programas) {
        log.info("Ejecutanto metodo [editarUsuario] usuarioId:{}, codigo:{}, identificacion:{}, nombre:{}, apellidos:{}, email:{}, programas:{} ", 
                usuarioId, codigo, identificacion, nombre, apellidos, email, programas);
        HttpStatus status;
        try {
            usuarioService.actualizarUsuario(usuarioId, codigo, identificacion, nombre, apellidos, email, programas);
            status = HttpStatus.OK;
        } catch (Exception e) {
            log.error("Ha ocurrido un error:{} " , e);
            status = HttpStatus.CONFLICT;
        }
        return new ResponseEntity<>(status);
    }
    
    @PutMapping(value = "/contrasena")
    public ResponseEntity<?> cambiarContrasena(@RequestParam Integer usuarioId, @RequestParam String claveActual, @RequestParam String nuevaClave1, @RequestParam String nuevaClave2) {
        log.info("Ejecutanto metodo [cambiarContrasena] usuarioId:{}, claveActual:{}, nuevaClave1:{}, nuevaClave2:{}", 
                usuarioId, claveActual, nuevaClave1, nuevaClave2);
        HttpStatus status;
        try {
            usuarioService.cambiarContrasena(usuarioId, claveActual, nuevaClave1, nuevaClave2);
            status = HttpStatus.OK;
        } catch (Exception e) {
            log.error("Ha ocurrido un error:{} " , e);
            status = HttpStatus.CONFLICT;
        }
        return new ResponseEntity<>(status);
    }
    
}
