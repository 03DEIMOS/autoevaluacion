/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.auth;

import com.utb.autoevaluacion.model.Usuario;
import com.utb.autoevaluacion.model.Variable;
import com.utb.autoevaluacion.service.UsuarioService;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author ASUS
 */
@Component
public class DbAuthProvider implements AuthenticationProvider {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        try {
            Usuario usuario = usuarioService.buscarUsuarioPorUsuario(username);

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
                    return new UsernamePasswordAuthenticationToken(username, password, Collections.emptyList());
                }
            }

        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(DbAuthProvider.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(DbAuthProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        throw new BadCredentialsException("External system authentication failed");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
    }
}
