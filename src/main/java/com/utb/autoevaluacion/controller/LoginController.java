/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.controller;

import com.utb.autoevaluacion.model.Encuesta;
import com.utb.autoevaluacion.model.Persona;
import com.utb.autoevaluacion.model.Usuario;
import com.utb.autoevaluacion.model.Variable;
import com.utb.autoevaluacion.service.EncuestaService;
import com.utb.autoevaluacion.service.PersonaService;
import com.utb.autoevaluacion.service.UsuarioService;
import com.utb.autoevaluacion.service.VariableService;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ASUS
 */
@Slf4j
@Controller
public class LoginController {

    @Autowired
    PersonaService personaService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    EncuestaService encuestaService;

    @Autowired
    VariableService variableService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/admin")
    public String indexAdmin() {
        return "indexAdmin";
    }
    
    @PostMapping("cerrarSesion")
    public String cerrarSesion() {
        log.info("Ejecutando metodo cerrarSesion");
        return "index";
    }

    @PostMapping("/")
    public String validar(@RequestParam String codigo, Model model) {
        log.info("Ejecutando metodo validar codigo:{}", codigo);
        List<Persona> personas = personaService.buscarPersonaPorUsuarioActivaYEsMuestra(codigo);
        if (personas != null && !personas.isEmpty()) {
            for (Persona persona : personas) {
                if (persona.getTerminado().equals("N")) {
                    Encuesta encuesta = encuestaService.obtenerEncuestasDePersona(persona);
                    model.addAttribute("encuesta", encuesta);
                    model.addAttribute("persona", persona);
                    return "fuente\\index";
                }
            }
            log.info("Devuelto al index");
            model.addAttribute("errorLogin", true);
            model.addAttribute("message", "Usuario ya realizó la encuesta");
            return "index";
        } else {
            log.info("Devuelto al index");
            model.addAttribute("errorLogin", true);
            model.addAttribute("message", "Código de usuario no registrado");
            return "index";
        }
    }

    @PostMapping("/admin")
    public String validarAdmin(@RequestParam String codigo, @RequestParam String password, Model model) {
        log.info("Ejecutando metodo validarAdmin codigo:{}", codigo);

        try {
            Usuario usuario = usuarioService.buscarUsuarioPorUsuario(codigo);
            if (usuario != null) {
                byte[] bytesOfMessage = password.getBytes("UTF-8");
                MessageDigest md = MessageDigest.getInstance("MD5");
                byte[] theMD5digest = md.digest(bytesOfMessage);
                // This bytes[] has bytes in decimal format. Convert it to hexadecimal format
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < theMD5digest.length; i++) {
                    sb.append(Integer.toString((theMD5digest[i] & 0xff) + 0x100, 16).substring(1));
                }

                // Get complete hashed password in hex format
                String generatedPassword = sb.toString();
                if (generatedPassword.equals(usuario.getContrasena())) {
                    model.addAttribute("usuario", usuario);
                    String textoIndex = "";
                    Variable variableTextoIndex = variableService.getVariableByLlave("CONTENIDO_PANTALLA_INICIAL");
                    if (variableTextoIndex != null) {
                        textoIndex = variableTextoIndex.getValor();
                    }
                    model.addAttribute("textoIndex", textoIndex);
                    if("Administrador".equals(usuario.getTipo())){
                        return "comiteCentral\\index";
                    }else{
                        return "comitePrograma\\index";
                    }
                    
                }
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

        model.addAttribute("errorLogin", true);
        model.addAttribute("message", "Usuario/Contraseña no válidos");
        return "indexAdmin";

    }

}
