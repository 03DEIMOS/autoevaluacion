/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;


public interface UtilitarioService {
    
    String getUploadedFile(byte[] file, Integer fuenteId, Integer procesoId) throws IOException;
    
    String uploadFile(MultipartFile archivo, String directorioCarga, String claseArchivo, String idEntidad);
}
