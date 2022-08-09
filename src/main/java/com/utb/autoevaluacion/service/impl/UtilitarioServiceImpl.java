/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.service.impl;

import com.utb.autoevaluacion.model.Fuente;
import com.utb.autoevaluacion.model.OportunidadMejora;
import com.utb.autoevaluacion.model.Persona;
import com.utb.autoevaluacion.model.Proceso;
import com.utb.autoevaluacion.model.Seguimiento;
import com.utb.autoevaluacion.model.Usuario;
import com.utb.autoevaluacion.repository.FuenteRepository;
import com.utb.autoevaluacion.repository.PersonaRepository;
import com.utb.autoevaluacion.repository.ProcesoRepository;
import com.utb.autoevaluacion.repository.UsuarioRepository;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.utb.autoevaluacion.service.UtilitarioService;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

@Service
@Slf4j
public class UtilitarioServiceImpl implements UtilitarioService {

    @Autowired
    PersonaRepository personaRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ProcesoRepository procesoRepository;

    @Autowired
    FuenteRepository fuenteRepository;

    @Override
    public String getUploadedFile(byte[] file, Integer fuenteId, Integer procesoId) throws IOException {
        log.debug("Ejecutando metodo getUploadedFile fuenteId:{}, procesoId:{} ", fuenteId, procesoId);
        InputStream is = new ByteArrayInputStream(file);

        Workbook workbook = WorkbookFactory.create(is);

        // Getting the Sheet at index zero
        Sheet sheet = workbook.getSheetAt(0);

        // Create a DataFormatter to format and get each cell's value as String
        DataFormatter dataFormatter = new DataFormatter();
        List<Persona> personas = new ArrayList<Persona>();

        for (Row row : sheet) {
            if (row.getRowNum() != 0 && row != null) {
                String codigo = "";
                String identificacion = "";
                String nombre = "";
                String apellido = "";
                String email = "";
                String variable1 = "";
                String variable2 = "";
                for (Cell cell : row) {
                    String cellValue = dataFormatter.formatCellValue(cell);
                    switch (cell.getColumnIndex()) {
                        case 0:
                            codigo = cellValue;
                            break;
                        case 1:
                            identificacion = cellValue;
                            break;
                        case 2:
                            nombre = cellValue;
                            break;
                        case 3:
                            apellido = cellValue;
                            break;
                        case 4:
                            email = cellValue;
                            break;
                        case 5:
                            variable1 = cellValue;
                            break;
                        case 6:
                            variable2 = cellValue;
                            break;
                    }
                }
                /*Si no está diligenciado alguno de los campos: codigo, identificacion, nombre, apellido, email no carga dicho registro*/
                if (codigo != null && !("").equals(codigo.trim())
                        && identificacion != null && !("").equals(identificacion.trim())
                        && nombre != null && !("").equals(nombre.trim())
                        && apellido != null && !("").equals(apellido.trim())
                        && email != null && !("").equals(email.trim())) {

                    Proceso proceso = procesoRepository.findById(procesoId).get();
                    Fuente fuente = fuenteRepository.findById(fuenteId).get();

                    personas.add(new Persona(variable1, variable2, proceso, fuente, "A", "N", "S",
                            new Usuario(codigo, identificacion, nombre, apellido, email, "A", true)));

                }

            }
        }
        log.info("Personas:{} ", personas.toString());
        for (Persona persona : personas) {
            Usuario usuarioBd = usuarioRepository.findByUsuario(persona.getUsuarioId().getUsuario());
            if (usuarioBd == null) {
                Usuario idUsuario = usuarioRepository.saveAndFlush(persona.getUsuarioId());
                persona.setUsuarioId(idUsuario);
                try{
                    personaRepository.save(persona);
                }catch(Exception e){
                    log.error("Ha ocurrido un error creando a la persona error:{} ",  e);
                }

            } else {
                log.info("Usuario ya existe y no se creara usuario.codigo :{}", usuarioBd.getUsuario());
                //personaDAO.inactivarPersona(persona.getFuente().getId(), persona.getProceso().getId(),usuarioBd.getId());
                Persona personaAux = personaRepository.buscarPersonaPorProcesoFuenteYUsuarioActiva(procesoId, fuenteId, usuarioBd.getId());
                if (personaAux == null) {
                    persona.setUsuarioId(usuarioBd);
                    personaRepository.save(persona);
                }
            }
        }

        return "";
    }

    @Override
    public String uploadFile(MultipartFile archivo, String directorioCarga, String claseArchivo, String idEntidad) {
        String ruta = directorioCarga + claseArchivo;
        try {
            Path copyLocation = Paths.get(directorioCarga + claseArchivo + File.separator + claseArchivo + "_" + idEntidad + "_" + StringUtils.cleanPath(archivo.getOriginalFilename()));
            Files.copy(archivo.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            log.error("Ha ocurrido un error al tratar de subir el archivo " + archivo.getOriginalFilename()
                    + " a la ruta" + ruta, e);
        }
        return ruta + File.separator + archivo.getOriginalFilename();
    }

}
