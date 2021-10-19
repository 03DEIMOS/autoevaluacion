/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.service.impl;

import com.utb.autoevaluacion.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utb.autoevaluacion.repository.UsuarioRepository;
import com.utb.autoevaluacion.service.UsuarioService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

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

}
