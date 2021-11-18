/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utb.autoevaluacion.service.impl;

import com.utb.autoevaluacion.dto.InformeCaracteristicasDTO;
import com.utb.autoevaluacion.dto.InformeDmaDTO;
import com.utb.autoevaluacion.dto.InformeFactoresDTO;
import com.utb.autoevaluacion.dto.InformeValoresAbsolutosDTO;
import com.utb.autoevaluacion.dto.MuestraInformeDTO;
import com.utb.autoevaluacion.model.Caracteristica;
import com.utb.autoevaluacion.model.Factor;
import com.utb.autoevaluacion.model.Fuente;
import com.utb.autoevaluacion.model.ItemPregunta;
import com.utb.autoevaluacion.model.ItemTipoPregunta;
import com.utb.autoevaluacion.model.Modelo;
import com.utb.autoevaluacion.model.Persona;
import com.utb.autoevaluacion.model.Pregunta;
import com.utb.autoevaluacion.model.Proceso;
import com.utb.autoevaluacion.model.ResultadoEvaluacion;
import com.utb.autoevaluacion.service.CaracteristicaService;
import com.utb.autoevaluacion.service.FactorService;
import com.utb.autoevaluacion.service.FuenteService;
import com.utb.autoevaluacion.service.InformeService;
import com.utb.autoevaluacion.service.PersonaService;
import com.utb.autoevaluacion.service.PreguntaService;
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
    FactorService factorService;

    @Autowired
    ResultadoEvaluacionService resultadoEvaluacionService;

    @Autowired
    PreguntaService preguntaService;

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
                    Integer primerMasAlto = 5;
                    Integer segundoMasAlto = 4;

                    InformeDmaDTO infDMAResult = new InformeDmaDTO(pregunta,
                            new ArrayList<String>(),// de itempregunta
                            new ArrayList<String>(), // de fuente
                            caracteristica,//Caracteristica
                            caracteristica.getFactorId(),//Factor
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
                                    } else if (respuestas.getRespuesta().equals(segundoMasAlto.toString())) {
                                        cuatros++;
                                    } else if (respuestas.getRespuesta().equals(primerMasAlto.toString())) {
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
                                } else if (respuestas.getRespuesta().equals(segundoMasAlto.toString())) {
                                    cuatros++;
                                } else if (respuestas.getRespuesta().equals(primerMasAlto.toString())) {
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

    @Override
    public List<Object> informePreguntasPorProceso(Integer procesoId) {

        List<Object> informePreguntasPorProceso = new ArrayList();
        List<Pregunta> preguntas = preguntaService.getPreguntasPorProceso(procesoId);
        for (Pregunta pregunta : preguntas) {

            InformeValoresAbsolutosDTO informeValoresAbsolutosResult = new InformeValoresAbsolutosDTO(pregunta,
                    new ArrayList<String>(),// de itempregunta
                    new ArrayList<Integer>(), // de Respuestas (Contador) cuando no tiene subpreguntas
                    new ArrayList<List<Integer>>(),// Contador de Respuestas para cuando SI tiene subpreguntas
                    0); //Total personas que contestaron la pregunta

            List<ItemTipoPregunta> tiposPregunta = pregunta.getTipoPregunta().getItemTipoPreguntaList();

            if (pregunta.getItemPreguntas().size() > 0) {
                List<ItemPregunta> itemPreguntas = pregunta.getItemPreguntas();
                int i = 0;
                for (ItemPregunta itemPregunta : itemPreguntas) {
                    // Si la pregunta tiene subpreguntas
                    if (i == 0) {
                        informeValoresAbsolutosResult.setTotalPersonaQueContestaron(resultadoEvaluacionService.buscarTotalPersonasContestaronPreguntaItemPregunta(procesoId, pregunta.getId(), itemPregunta.getId()));
                        i++;
                    }
                    //Resultado del contador de las respuestas de los ítems de la pregunta
                    List<Integer> resultadoList = new ArrayList();

                    //Setea los ítems para esa pregunta
                    informeValoresAbsolutosResult.getItemPregunta().add(itemPregunta.getItemPregunta());
                    List<ResultadoEvaluacion> rs = null;
                    for (ItemTipoPregunta itemTipoPregunta : tiposPregunta) {
                        rs = resultadoEvaluacionService.buscarPorProcesoItemPreguntaRespuesta(procesoId, itemPregunta.getId(), itemTipoPregunta.getValor());
                        resultadoList.add(rs.size());
                    }
                    //Agrega los valores del contador a la lista
                    informeValoresAbsolutosResult.getResultadosAbsolutosConSubpreguntaContador().add(resultadoList);
                }
            } else {
                List<ResultadoEvaluacion> rs = null;
                informeValoresAbsolutosResult.setTotalPersonaQueContestaron(resultadoEvaluacionService.buscarTotalPersonasContestaronPregunta(procesoId, pregunta.getId()));
                for (ItemTipoPregunta itemTipoPregunta : tiposPregunta) {
                    rs = resultadoEvaluacionService.buscarPorProcesoPreguntaRespuesta(procesoId, pregunta.getId(), itemTipoPregunta.getValor());
                    informeValoresAbsolutosResult.getResultadosAbsolutosSinSubpreguntaContador().add(rs.size());
                }
            }
            informePreguntasPorProceso.add(informeValoresAbsolutosResult);
        }

        return informePreguntasPorProceso;
    }

    @Override
    public List<Object> informePreguntasPorProcesoyPublico(Integer procesoId, Integer publico) {
        List<Object> informePreguntasPorProceso = new ArrayList();
        List<Pregunta> preguntas = preguntaService.getPreguntasPorProcesoyPublico(procesoId, publico);
        for (Pregunta pregunta : preguntas) {

            InformeValoresAbsolutosDTO informeValoresAbsolutosResult = new InformeValoresAbsolutosDTO(pregunta,
                    new ArrayList<String>(),// de itempregunta
                    new ArrayList<Integer>(), // de Respuestas (Contador) cuando no tiene subpreguntas
                    new ArrayList<List<Integer>>(),// Contador de Respuestas para cuando SI tiene subpreguntas
                    0); //Total personas que contestaron la pregunta

            List<ItemTipoPregunta> tiposPregunta = pregunta.getTipoPregunta().getItemTipoPreguntaList();

            if (pregunta.getItemPreguntas().size() > 0) {
                List<ItemPregunta> itemPreguntas = pregunta.getItemPreguntas();
                int i = 0;
                for (ItemPregunta itemPregunta : itemPreguntas) {
                    // Si la pregunta tiene subpreguntas
                    if (i == 0) {
                        informeValoresAbsolutosResult.setTotalPersonaQueContestaron(resultadoEvaluacionService.buscarTotalPersonasContestaronPreguntaItemPreguntaFuente(procesoId, pregunta.getId(), itemPregunta.getId(), publico));
                        i++;
                    }
                    //Resultado del contador de las respuestas de los ítems de la pregunta
                    List<Integer> resultadoList = new ArrayList();

                    //Setea los ítems para esa pregunta
                    informeValoresAbsolutosResult.getItemPregunta().add(itemPregunta.getItemPregunta());
                    List<ResultadoEvaluacion> rs = null;
                    for (ItemTipoPregunta itemTipoPregunta : tiposPregunta) {
                        rs = resultadoEvaluacionService.buscarPorProcesoItemPreguntaRespuestaFuente(procesoId, itemPregunta.getId(), itemTipoPregunta.getValor(), publico);
                        resultadoList.add(rs.size());
                    }
                    //Agrega los valores del contador a la lista
                    informeValoresAbsolutosResult.getResultadosAbsolutosConSubpreguntaContador().add(resultadoList);
                }
            } else {
                List<ResultadoEvaluacion> rs = null;
                informeValoresAbsolutosResult.setTotalPersonaQueContestaron(resultadoEvaluacionService.buscarTotalPersonasContestaronPreguntaFuente(procesoId, pregunta.getId(), publico));
                for (ItemTipoPregunta itemTipoPregunta : tiposPregunta) {
                    rs = resultadoEvaluacionService.buscarPorProcesoPreguntaRespuestaFuente(procesoId, pregunta.getId(), itemTipoPregunta.getValor(), publico);
                    informeValoresAbsolutosResult.getResultadosAbsolutosSinSubpreguntaContador().add(rs.size());
                }
            }
            informePreguntasPorProceso.add(informeValoresAbsolutosResult);
        }

        return informePreguntasPorProceso;
    }

    @Override
    public InformeCaracteristicasDTO informeCaracteristicasPorProceso(Integer procesoId) {
        Proceso proceso = procesoService.buscarProceso(procesoId);
        Modelo modelo = proceso.getModeloId();
        List<Caracteristica> caracteristicas = caracteristicaService.buscarPorModeloYConPreguntasAsociadas(modelo);
        float cumplimiento[] = new float[caracteristicas.size()];
        float cumplimientoXcaracteristica;
        for (int a = 0; a < caracteristicas.size(); a++) {
            if (caracteristicas.get(a).getPreguntaList().size() > 0) {
                List<Pregunta> preguntas = caracteristicas.get(a).getPreguntaList();

                float promedioPregunta, suma, numP;

                //declaramos promedio de respuestas para cada preguntas
                List<Double> promediorespuestas = new ArrayList<Double>();
                cumplimientoXcaracteristica = 0;
                for (Pregunta pregunta : preguntas) {
                    List<Fuente> fuentes = fuenteService.buscarFuentesXmodeloXpregunta(modelo.getId(), pregunta.getId());

                    if (pregunta.getItemPreguntas().size() > 0) {
                        for (int i = 0; i < pregunta.getItemPreguntas().size(); i++) {// Si la pregunta tiene subpreguntas
                            float promediorespuestapreguntaxfuente[] = new float[fuentes.size()];
                            for (int j = 0; j < fuentes.size(); j++) {
                                suma = 0;
                                numP = 0;
                                List<ResultadoEvaluacion> rs = null;
                                rs = resultadoEvaluacionService.buscarPorProcesoItemPreguntaFuente(proceso.getId(),
                                        pregunta.getItemPreguntas().get(i).getId(), fuentes.get(j).getId());

                                for (ResultadoEvaluacion respuesta : rs) {
                                    if (!respuesta.getRespuesta().equals("0")) {
                                        suma += Integer.parseInt(respuesta.getRespuesta());
                                        numP++;
                                    }
                                }

                                if (suma > 0) {
                                    promediorespuestapreguntaxfuente[j] = (float) suma / numP;
                                }
                            }
                            //calculamos el promedio de respuesta por pregunta usando los promedios por encuesta
                            float promedioPreguntaAux = 0;
                            int fuentesQueHanRespondido = 0;
                            int cantidadFuentes = fuentes.size();
                            for (int m = 0; m < cantidadFuentes; m++) {
                                if (promediorespuestapreguntaxfuente[m] > 0.0) {
                                    fuentesQueHanRespondido++;
                                }
                                promedioPreguntaAux += promediorespuestapreguntaxfuente[m];
                            }
                            promedioPregunta = (float) promedioPreguntaAux / fuentesQueHanRespondido;
                            promediorespuestas.add((Double) Math.rint(promedioPregunta * 10) / 10);
                        }
                    } else {
                        float promediorespuestapreguntaxfuente[] = new float[fuentes.size()];
                        for (int j = 0; j < fuentes.size(); j++) {
                            suma = 0;
                            numP = 0;
                            List<ResultadoEvaluacion> rs = null;
                            rs = resultadoEvaluacionService.buscarPorProcesoPreguntaFuente(proceso.getId(), pregunta.getId(), fuentes.get(j).getId());
                            for (ResultadoEvaluacion respuesta : rs) {
                                if (!respuesta.getRespuesta().equals("0")) {
                                    suma += Integer.parseInt(respuesta.getRespuesta());
                                    numP++;
                                }
                            }
                            if (suma > 0) {
                                promediorespuestapreguntaxfuente[j] = (float) suma / numP;
                            }
                        }
                        //calculamos el promedio de respuesta por pregunta usando los promedios por encuesta
                        float promedioPreguntaAux = 0;
                        int fuentesQueHanRespondido = 0;
                        int cantidadFuentes = fuentes.size();
                        for (int m = 0; m < cantidadFuentes; m++) {
                            if (promediorespuestapreguntaxfuente[m] > 0.0) {
                                fuentesQueHanRespondido++;
                            }
                            promedioPreguntaAux += promediorespuestapreguntaxfuente[m];
                        }
                        if (fuentesQueHanRespondido > 0) {
                            promedioPregunta = (float) promedioPreguntaAux / fuentesQueHanRespondido;
                        } else {
                            promedioPregunta = (float) 0.0;
                        }
                        promediorespuestas.add((Double) Math.rint(promedioPregunta * 10) / 10);
                    }
                }

                int numPre = 0;
                for (int q = 0; q < promediorespuestas.size(); q++) {
                    if (promediorespuestas.get(q) > 0.0) {
                        cumplimientoXcaracteristica += promediorespuestas.get(q);
                        numPre++;
                    }
                }
                if (numPre > 0) {
                    cumplimiento[a] = (float) cumplimientoXcaracteristica / numPre;
                } else {
                    cumplimiento[a] = (float) 0.0;
                }
            }
        }

        InformeCaracteristicasDTO informeCaracteristicasDTO = new InformeCaracteristicasDTO(caracteristicas, cumplimiento); //Total personas que contestaron la pregunta

        return informeCaracteristicasDTO;
    }

    @Override
    public InformeFactoresDTO informeFactoresPorProceso(Integer procesoId) {
        Proceso proceso = procesoService.buscarProceso(procesoId);
        Modelo modelo = proceso.getModeloId();
        List<Factor> factores = factorService.buscarPorModeloYConPreguntasAsociadas(modelo.getId());
        float cumplimiento[] = new float[factores.size()];
        float cumplimientoXfactor;
        for (int f = 0; f < factores.size(); f++) {
            List<Caracteristica> caracteristicas = caracteristicaService.getCaracteristicasByFactor(factores.get(f).getId());
            float cumplimientoC[] = new float[caracteristicas.size()];
            float cumplimientoXcaracteristica;
            for (int a = 0; a < caracteristicas.size(); a++) {
                if (caracteristicas.get(a).getPreguntaList().size() > 0) {
                    List<Pregunta> preguntas = caracteristicas.get(a).getPreguntaList();

                    float promedioPregunta, suma, numP;

                    //declaramos promedio de respuestas para cada preguntas
                    List<Double> promediorespuestas = new ArrayList<Double>();
                    cumplimientoXcaracteristica = 0;
                    for (Pregunta pregunta : preguntas) {
                        List<Fuente> fuentes = fuenteService.buscarFuentesXmodeloXpregunta(modelo.getId(), pregunta.getId());

                        if (pregunta.getItemPreguntas().size() > 0) {
                            for (int i = 0; i < pregunta.getItemPreguntas().size(); i++) {// Si la pregunta tiene subpreguntas
                                float promediorespuestapreguntaxfuente[] = new float[fuentes.size()];
                                for (int j = 0; j < fuentes.size(); j++) {
                                    suma = 0;
                                    numP = 0;
                                    List<ResultadoEvaluacion> rs = null;
                                    rs = resultadoEvaluacionService.buscarPorProcesoItemPreguntaFuente(proceso.getId(),
                                            pregunta.getItemPreguntas().get(i).getId(), fuentes.get(j).getId());

                                    for (ResultadoEvaluacion respuesta : rs) {
                                        if (!respuesta.getRespuesta().equals("0")) {
                                            suma += Integer.parseInt(respuesta.getRespuesta());
                                            numP++;
                                        }
                                    }

                                    if (suma > 0) {
                                        promediorespuestapreguntaxfuente[j] = (float) suma / numP;
                                    }
                                }
                                //calculamos el promedio de respuesta por pregunta usando los promedios por encuesta
                                float promedioPreguntaAux = 0;
                                int fuentesQueHanRespondido = 0;
                                int cantidadFuentes = fuentes.size();
                                for (int m = 0; m < cantidadFuentes; m++) {
                                    if (promediorespuestapreguntaxfuente[m] > 0.0) {
                                        fuentesQueHanRespondido++;
                                    }
                                    promedioPreguntaAux += promediorespuestapreguntaxfuente[m];
                                }
                                promedioPregunta = (float) promedioPreguntaAux / fuentesQueHanRespondido;
                                promediorespuestas.add((Double) Math.rint(promedioPregunta * 10) / 10);
                            }
                        } else {
                            float promediorespuestapreguntaxfuente[] = new float[fuentes.size()];
                            for (int j = 0; j < fuentes.size(); j++) {
                                suma = 0;
                                numP = 0;
                                List<ResultadoEvaluacion> rs = null;
                                rs = resultadoEvaluacionService.buscarPorProcesoPreguntaFuente(proceso.getId(), pregunta.getId(), fuentes.get(j).getId());
                                for (ResultadoEvaluacion respuesta : rs) {
                                    if (!respuesta.getRespuesta().equals("0")) {
                                        suma += Integer.parseInt(respuesta.getRespuesta());
                                        numP++;
                                    }
                                }
                                if (suma > 0) {
                                    promediorespuestapreguntaxfuente[j] = (float) suma / numP;
                                }
                            }
                            //calculamos el promedio de respuesta por pregunta usando los promedios por encuesta
                            float promedioPreguntaAux = 0;
                            int fuentesQueHanRespondido = 0;
                            int cantidadFuentes = fuentes.size();
                            for (int m = 0; m < cantidadFuentes; m++) {
                                if (promediorespuestapreguntaxfuente[m] > 0.0) {
                                    fuentesQueHanRespondido++;
                                }
                                promedioPreguntaAux += promediorespuestapreguntaxfuente[m];
                            }
                            if (fuentesQueHanRespondido > 0) {
                                promedioPregunta = (float) promedioPreguntaAux / fuentesQueHanRespondido;
                            } else {
                                promedioPregunta = (float) 0.0;
                            }
                            promediorespuestas.add((Double) Math.rint(promedioPregunta * 10) / 10);
                        }
                    }

                    int numPre = 0;
                    for (int q = 0; q < promediorespuestas.size(); q++) {
                        if (promediorespuestas.get(q) > 0.0) {
                            cumplimientoXcaracteristica += promediorespuestas.get(q);
                            numPre++;
                        }
                    }
                    if (numPre > 0) {
                        cumplimientoC[a] = (float) cumplimientoXcaracteristica / numPre;
                    } else {
                        cumplimientoC[a] = (float) 0.0;
                    }
                }
            }

            int numCar = 0;
            cumplimientoXfactor = (float) 0.0;
            for (int c = 0; c < caracteristicas.size(); c++) {
                if (cumplimientoC[c] > 0.0) {
                    cumplimientoXfactor += cumplimientoC[c];
                    numCar++;
                }
            }
            if (numCar > 0) {
                cumplimiento[f] = (float) cumplimientoXfactor / numCar;
            } else {
                cumplimiento[f] = (float) 0.0;
            }           
        }

        InformeFactoresDTO informeFactoresDTO = new InformeFactoresDTO(factores, cumplimiento);

        return informeFactoresDTO;
    }
}//End class
