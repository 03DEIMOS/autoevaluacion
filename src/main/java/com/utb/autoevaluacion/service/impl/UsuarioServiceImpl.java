/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.service.impl;

import com.utb.autoevaluacion.model.Programa;
import com.utb.autoevaluacion.model.Usuario;
import com.utb.autoevaluacion.repository.ProgramaRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utb.autoevaluacion.repository.UsuarioRepository;
import com.utb.autoevaluacion.service.UsuarioService;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;
    
    @Autowired
    ProgramaRepository programaRepository;

    @Override
    public Usuario buscarUsuario(Integer id) {
        Usuario usuario = null;
        Optional<Usuario> usuarioOptional = null;
        try {
            usuarioOptional = usuarioRepository.findById(id);
        } catch (Exception e) {
            log.error("Ha ocurrido un error inesperado: " + e.getMessage(), e);
        }
        if (usuarioOptional.isPresent()) {
            usuario = usuarioOptional.get();
        }
        return usuario;
    }

    @Override
    public Usuario buscarUsuarioPorUsuario(String usuario) {
        Usuario user = null;
        try {
            user = usuarioRepository.findByUsuario(usuario);
        } catch (Exception e) {
            log.error("Ha ocurrido un error inesperado: " + e.getMessage(), e);
        }

        return user;
    }

    @Override
    public List<Usuario> buscarUsuarios() {
        List<Usuario> users = null;
        try {
            users = usuarioRepository.findByContrasenaIsNotNull();
        } catch (Exception e) {
            log.error("Ha ocurrido un error inesperado: " + e.getMessage(), e);
        }

        return users;
    }

    @Override
    public void crearUsuario(String codigo, String identificacion, String nombre, String apellidos, String clave, String email, Integer[] idProgramas) {
        try {
            
            List<Programa> programas = new ArrayList<>();
            Usuario usuario = new Usuario();
            usuario.setUsuario(codigo);
            usuario.setIdentificacion(identificacion);
            usuario.setNombre(nombre);
            usuario.setApellido(apellidos);

            String generatedPassword = textToMD5(clave);

            usuario.setContrasena(generatedPassword);
            usuario.setEmail(email);
            
            for (int i = 0; i < idProgramas.length; i++) {
                programas.add(programaRepository.findById(idProgramas[i]).get()) ;
                
            }
            usuario.setProgramaList(programas);
            usuarioRepository.saveAndFlush(usuario);
        } catch (Exception e) {
            log.error("Ha ocurrido un error al crear el usuario" + e);
            try {
                throw e;
            } catch (Exception ex) {
                Logger.getLogger(UsuarioServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Usuario buscarUsuarioPorId(Integer usuarioId) {
        Usuario user = null;
        Optional<Usuario> usuarioOptional = null;
        try {
            usuarioOptional = usuarioRepository.findById(usuarioId);
            if (usuarioOptional.isPresent()) {
                user = usuarioOptional.get();
            }
        } catch (Exception e) {
            log.error("Ha ocurrido un error inesperado:{}", e);
        }
        return user;
    }

    @Override
    public void actualizarUsuario(Integer usuarioId, String codigo, String identificacion, String nombre, String apellidos, String email, Integer[] idProgramas) {
        try {
            List<Programa> programas = new ArrayList<>();
            Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuarioId);
            if (usuarioOptional.isPresent()) {
                Usuario usuario = usuarioOptional.get();
                usuario.setIdentificacion(identificacion);
                usuario.setNombre(nombre);
                usuario.setApellido(apellidos);
                usuario.setEmail(email);
                for (int i = 0; i < idProgramas.length; i++) {
                    programas.add(programaRepository.findById(idProgramas[i]).get());

                }
                usuario.setProgramaList(programas);
                usuarioRepository.saveAndFlush(usuario);
            } else {
                log.error("usuario no encontrado usuario:{}", usuarioId);
            }
        } catch (Exception e) {
            log.error("Ha ocurrido un error al actualizar el usuario:{}", e);
            throw e;
        }
    }

    @Override
    public void cambiarContrasena(Integer usuarioId, String claveActual, String nuevaClave1, String nuevaClave2) throws Exception {
        try {
            Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuarioId);
            if (usuarioOptional.isPresent()) {
                Usuario usuario = usuarioOptional.get();
                if (textToMD5(claveActual).equals(usuario.getContrasena())) {
                    if (nuevaClave1.equals(nuevaClave2)) {
                        usuario.setContrasena(textToMD5(nuevaClave1));
                        usuarioRepository.saveAndFlush(usuario);
                        return;
                    }
                }
            }
            throw new Exception("No se puedo cambiar la clave");
        } catch (Exception e) {
            log.error("Ha ocurrido un error al cambiar la clave:{}", e);
            throw e;
        }
    }

    private String textToMD5(String plainPassword) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        byte[] bytesOfMessage = plainPassword.getBytes("UTF-8");
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] theMD5digest = md.digest(bytesOfMessage);
        // This bytes[] has bytes in decimal format. Convert it to hexadecimal format
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < theMD5digest.length; i++) {
            sb.append(Integer.toString((theMD5digest[i] & 0xff) + 0x100, 16).substring(1));
        }
        // Get complete hashed password in hex format
        return sb.toString();
    }
}
