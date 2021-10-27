/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.controller;

import com.utb.autoevaluacion.service.UtilitarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Slf4j
@RequestMapping(value = "/utilitario")
@CrossOrigin
public class UtiliarioController {
    
    @Autowired
    UtilitarioService utilitarioService;
    
    @RequestMapping(path = "/downloadFile", method = RequestMethod.GET)
    public ResponseEntity<Resource> donwloadFile(@RequestParam("archivo") String archivo) throws IOException {

        HttpHeaders headers = new HttpHeaders();

        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + archivo + ".xlsx");

        File file = new ClassPathResource(archivo + ".xlsx").getFile();

        Path path = Paths.get(file.getAbsolutePath());

        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

        return ResponseEntity.ok().headers(headers).contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream")).body(resource);

    }
    
    @RequestMapping(path = "/uploadFile", method = RequestMethod.POST)
	public String getUploadedFile(@RequestParam("files[]") MultipartFile file, @RequestParam("procesoId") Integer procesoId,
			@RequestParam("fuenteId") Integer fuenteId) throws IOException {

		byte[] fileDecoded = file.getBytes();
		utilitarioService.getUploadedFile(fileDecoded, fuenteId, procesoId);

		return "";
	}
}

