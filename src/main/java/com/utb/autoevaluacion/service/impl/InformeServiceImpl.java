/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.service.impl;

import com.utb.autoevaluacion.dto.InformeDmaDTO;
import com.utb.autoevaluacion.dto.MuestraInformeDTO;
import com.utb.autoevaluacion.model.Caracteristica;
import com.utb.autoevaluacion.model.Fuente;
import com.utb.autoevaluacion.model.Modelo;
import com.utb.autoevaluacion.model.Persona;
import com.utb.autoevaluacion.model.Pregunta;
import com.utb.autoevaluacion.model.Proceso;
import com.utb.autoevaluacion.model.ResultadoEvaluacion;
import com.utb.autoevaluacion.repository.PersonaRepository;
import com.utb.autoevaluacion.service.CaracteristicaService;
import com.utb.autoevaluacion.service.FuenteService;
import com.utb.autoevaluacion.service.InformeService;
import com.utb.autoevaluacion.service.PersonaService;
import com.utb.autoevaluacion.service.ProcesoService;
import com.utb.autoevaluacion.service.ResultadoEvaluacionService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Slf4j
@Service
public class InformeServiceImpl implements InformeService {

    @Autowired
    PersonaService personaService;

    @Autowired
    FuenteService fuenteService;

    @Autowired
    ProcesoService procesoService;

    @Autowired
    CaracteristicaService caracteristicaService;
    
    @Autowired
    ResultadoEvaluacionService resultadoEvaluacionService;

    @Override
    public List<Object> estadoGeneralDelProceso(Integer procesoId) {
        //Devuelve todas las personas que estan en la muestra de un proceso - estado:activo
        List<Persona> totalMuestra = personaService.muestraPorProceso(procesoId);
        //Devuelve total personas que hicieron la encuesta estado = 'A' AND es_muestra='S' AND terminado='S'
        Integer totalMuestraEncuestaTerminadaCant = personaService.personasEncuestaTerminadaPorProceso(procesoId).size();

        Integer totalMuestraCant = totalMuestra.size();

        List<Object> lista = new ArrayList();
        Map<String, Integer> muestraEncuestadaPorFuente = new HashMap<String, Integer>();
        Set<MuestraInformeDTO> muestraInformeCompleta = new HashSet();
        Set<MuestraInformeDTO> muestraInformeTerminada = new HashSet();

        //Fuentes por proceso
        List<Fuente> fuentes = fuenteService.buscarFuentesXproceso(procesoId);
        for (Fuente fuente : fuentes) {
            Integer cant = 0;
            cant = personaService.cantidadTotalMuestraPorProcesoFuente(procesoId, fuente.getId());
            muestraInformeCompleta.add(new MuestraInformeDTO(fuente.getNombre(), cant));

            Integer cant1 = 0;
            cant1 = personaService.cantidadMuestraEncuestaTerminadaPorProcesoFuente(procesoId, fuente.getId());
            muestraInformeTerminada.add(new MuestraInformeDTO(fuente.getNombre(), cant1));
        }
        muestraEncuestadaPorFuente.put("TotalMuestraPorProceso", totalMuestraCant);
        muestraEncuestadaPorFuente.put("TotalMuestraEncuestaTerminada", totalMuestraEncuestaTerminadaCant);

        lista.add(muestraInformeCompleta);//Muestra agrupada por fuente y cantidad
        lista.add(muestraInformeTerminada);//Muestra agrupada por fuente y cantidad ConEncuestaTerminada
        lista.add(muestraEncuestadaPorFuente);//total muestra

        return lista;
    }

    @Override
    public List<Object> informeDMAPorProceso(Integer procesoId) {

        List<Object> lista = new ArrayList();
        List<Object> informeDMA = new ArrayList();
        Proceso proceso = procesoService.buscarProceso(procesoId);
        Modelo modelo = proceso.getModeloId();
        List<Caracteristica> caracteristicas = caracteristicaService.buscarPorModeloYConPreguntasAsociadas(modelo);
           for (Caracteristica caracteristica : caracteristicas) {
            if (caracteristica.getPreguntaList().size() > 0) {
                List<Pregunta> preguntas = caracteristica.getPreguntaList();
                for (Pregunta pregunta : preguntas) {
                    List<Fuente> fuentes = fuenteService.buscarFuentesXmodeloXpregunta(modelo.getId(), pregunta.getId());
                    String primerMasAlto = pregunta.getTipoPregunta().getItemTipoPreguntaList().get(0).getValor();
                    String segundoMasAlto = pregunta.getTipoPregunta().getItemTipoPreguntaList().get(1).getValor();

                    InformeDmaDTO infDMAResult = new InformeDmaDTO(pregunta,
                            new ArrayList<String>(),// de itempregunta
                            new ArrayList<String>(), // de fuente
                            caracteristica.getNombre(),//Caracteristica
                            caracteristica.getFactorId().getNombre(),//Factor
                            new ArrayList<Double>(), // de dma para cuando NO tiene subpreguntas
                            new ArrayList<List<Double>>(), //de dma para cuando SI tiene subpreguntas
                            new ArrayList<Double>(), // de ceros para cuando NO tiene subpreguntas
                            new ArrayList<List<Double>>()); //de ceros para cuando SI tiene subpreguntas

                    if (pregunta.getItemPreguntas().size() > 0) {
                        for (int i = 0; i < pregunta.getItemPreguntas().size(); i++) {// Si la pregunta tiene subpreguntas
                            List<Double> DMAList = new ArrayList();
                            List<Double> cerosList = new ArrayList();

                            infDMAResult.getItemPregunta().add(pregunta.getItemPreguntas().get(i).getItemPregunta());

                            for (Fuente fuente : fuentes) {
                                List<ResultadoEvaluacion> rs = null;
                                rs = resultadoEvaluacionService.buscarPorProcesoItemPreguntaFuente(proceso.getId(),
                                        pregunta.getItemPreguntas().get(i).getId(), fuente.getId());
                                int cuatros = 0;
                                int cincos = 0;
                                int ceros = 0;
                                for (ResultadoEvaluacion respuestas : rs) {
                                    if (respuestas.getRespuesta().equals("0")) {
                                        ceros++;
                                    } else if (respuestas.getRespuesta().equals(segundoMasAlto)) {
                                        cuatros++;
                                    } else if (respuestas.getRespuesta().equals(primerMasAlto)) {
                                        cincos++;
                                    }
                                }
                                if (rs.isEmpty()) {
                                    DMAList.add(-1.0);
                                    cerosList.add(-1.0);
                                } else {
                                    double dma = (double) ((cincos + cuatros) * 100) / rs.size();
                                    double cerosPorcentaje = (double) ((ceros) * 100) / rs.size();
                                    DMAList.add(dma);
                                    cerosList.add(cerosPorcentaje);

                                }
                            }
                            infDMAResult.getDMAList().add(DMAList);
                            infDMAResult.getPorcentajeCerosList().add(cerosList);
                        }
                        for (Fuente fuente : fuentes) {
                            infDMAResult.getFuente().add(fuente.getNombre());
                        }
                        informeDMA.add(infDMAResult);
                    } else {
                        for (Fuente fuente : fuentes) {
                            List<ResultadoEvaluacion> rs = null;
                            rs = resultadoEvaluacionService.buscarPorProcesoPreguntaFuente(proceso.getId(),
                                    pregunta.getId(), fuente.getId());
                            int cuatros = 0, cincos = 0, ceros = 0;
                            for (ResultadoEvaluacion respuestas : rs) {
                                if (respuestas.getRespuesta().equals("0")) {
                                    ceros++;
                                } else if (respuestas.getRespuesta().equals(segundoMasAlto)) {
                                    cuatros++;
                                } else if (respuestas.getRespuesta().equals(primerMasAlto)) {
                                    cincos++;
                                }
                            }
                            if (rs.isEmpty()) {
                                infDMAResult.getDMA().add(-1.0);
                                infDMAResult.getPorcentajeCeros().add(-1.0);
                                infDMAResult.getFuente().add(fuente.getNombre());
                            } else {
                                double dma = (double) ((cincos + cuatros) * 100) / rs.size();
                                double cerosPorcentaje = (double) ((ceros) * 100) / rs.size();
                                infDMAResult.getDMA().add(dma);
                                infDMAResult.getPorcentajeCeros().add(cerosPorcentaje);
                                infDMAResult.getFuente().add(fuente.getNombre());

                            }
                        }
                        informeDMA.add(infDMAResult);
                    }
                }

            }
        }
        
        return informeDMA;
    }
}
