<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" language="JavaScript">
    $(document).ready(function () {
    $(".printEnlace").click(function () {
    $('#conte').jqprint();
    return false;
    });
    });
    $(function () {
    var chart;
    $(document).ready(function () {
    chart = new Highcharts.Chart({
    chart: {
    renderTo: 'container',
            type: 'column'
    },
            title: {
            text: 'Muestra seleccionada y evaluada'
            },
            xAxis: {
            categories: [

    <c:forEach items="${resultado.get(0)}" var="muestraInformeDTO" varStatus="iter">
        <c:forEach items="${resultado.get(1)}" var="muestraInformeDTO2" varStatus="iter2">
            <c:if test="${muestraInformeDTO.fuente==muestraInformeDTO2.fuente}">
                <c:choose>
                    <c:when test="${fn:length(resultado.get(0)) > (iter.index + 1)}">
                        <c:out escapeXml="false" value="'${muestraInformeDTO2.fuente}',"></c:out>
                    </c:when>
                    <c:otherwise>
                        <c:out escapeXml="false" value="'${muestraInformeDTO2.fuente}'"></c:out>
                    </c:otherwise>
                </c:choose>
            </c:if>
        </c:forEach>
    </c:forEach>

            ]
            },
            yAxis: {
            min: 0,
                    title: {
                    text: 'Número de personas'
                    }
            },
            plotOptions: {
            column: {
            pointPadding: 0.2,
                    borderWidth: 0,
                    dataLabels: {
                    enabled: true,
                            color: "#4572A7",
                            style: {
                            fontWeight: 'bold'
                            },
                            formatter: function () {
                            return this.y + '';
                            }
                    }
            }
            },
            series: [{
            name: 'Seleccionados',
                    data: [
    <c:forEach items="${resultado.get(0)}" var="muestraInformeDTO" varStatus="iter">
        <c:forEach items="${resultado.get(1)}" var="muestraInformeDTO2" varStatus="iter2">
            <c:if test="${muestraInformeDTO.fuente==muestraInformeDTO2.fuente}">
                <c:choose>
                    <c:when test="${fn:length(resultado.get(0)) > (iter.index + 1)}">
                        <c:out value="${muestraInformeDTO.cantidad},"></c:out>
                    </c:when>
                    <c:otherwise><c:out value="${muestraInformeDTO.cantidad}"></c:out></c:otherwise>
                </c:choose>
            </c:if>
        </c:forEach>
    </c:forEach>
                    ]

            }, {
            name: 'Evaluados',
                    data: [
    <c:forEach items="${resultado.get(0)}" var="muestraInformeDTO" varStatus="iter">
        <c:forEach items="${resultado.get(1)}" var="muestraInformeDTO2" varStatus="iter2">
            <c:if test="${muestraInformeDTO.fuente==muestraInformeDTO2.fuente}">
                <c:choose>
                    <c:when test="${fn:length(resultado.get(0)) > (iter.index + 1)}">
                        <c:out value="${muestraInformeDTO2.cantidad},"></c:out>
                    </c:when>
                    <c:otherwise><c:out value="${muestraInformeDTO2.cantidad}"></c:out></c:otherwise>
                </c:choose>
            </c:if>
        </c:forEach>
    </c:forEach>

                    ],
                    color: '#89A54E'

            }],
            tooltip: {
            formatter: function () {
            return '<b>' + this.x + '</b>: ' + this.y + ' personas';
            }
            }

    });
    });
    });
</script>
<div class="hero-unit">
    <a  class="span10 printEnlace" style="text-align: right; margin-left: 0px; text-align: right; cursor: pointer"><i class="icon-print"></i> Imprimir</a>  
    <div class="row">
        <div id="conte" class="span10">
            <fieldset>
                <legend>
                    Estado del proceso
                </legend>
                <p>Informes: </p>
                <div>
                    <div class="btn-group">
                        <a class="btn btn-primary" href="#informe/informeDMA/${proceso.id}"><i class="icon-bar-chart"></i> Informe DMA</a>
                        <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" >
                            <c:choose>
                                <c:when test="${fn:length(fuentes)!= 0}">
                                    <c:forEach items="${fuentes}" var="fuente" varStatus="iter">
                                        <li><a href="#informe/informeDMA/${proceso.id}/publico/${fuente.id}">${fuente.nombre}</a></li>
                                    </c:forEach>
                                </c:when>
                            </c:choose>
                           </ul>
                    </div>
                    <a href="#informe/informeCaracteristicas/${proceso.id}" class="btn btn-primary"><i class="icon-bar-chart">  </i>Matriz de calidad por caracter&iacute;sticas</a>
                    <a href="#informe/informeFactores/${proceso.id}" class="btn btn-primary"><i class="icon-bar-chart">  </i>Matriz de calidad por factores</a>
                    <div class="btn-group">
                        <a class="btn btn-primary" href="#informe/informePreguntas/${proceso.id}"><i class="icon-random"></i> Informe por preguntas</a>
                        <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" >
                            <c:choose>
                                <c:when test="${fn:length(fuentes)!= 0}">
                                    <c:forEach items="${fuentes}" var="fuente" varStatus="iter">
                                        <li><a href="#informe/informePreguntas/proceso/${proceso.id}/publico/${fuente.id}">${fuente.nombre}</a></li>
                                    </c:forEach>
                                </c:when>
                            </c:choose>
                           </ul>
                    </div>

                    

                </div>
                <br>
                <p>Estado general del proceso:</p>
                <table class="table table-striped table-bordered table-condensed">
                    <thead>
                    <th>Número total de muestra</th>
                    <th>Número de personas que han evaluado las encuestas</th>
                    <th>Porcentaje de personas que han evaluado las encuestas</th>
                    <th>Número de personas que faltan por evaluar las encuestas</th>
                    <th>Porcentaje de personas que faltan por evaluar las encuestas</th>
                    </thead>
                    <tbody>
                        <tr>  
                            <td>   
                                <c:out value="${resultado.get(2).TotalMuestraPorProceso}"/>
                            </td>
                            <td>   
                                <c:out value="${resultado.get(2).TotalMuestraEncuestaTerminada}"/>
                            </td>
                            <td>   
                                <fmt:formatNumber type="number" maxFractionDigits="2" value="${resultado.get(2).TotalMuestraEncuestaTerminada*100/resultado.get(2).TotalMuestraPorProceso}"/>%
                            </td>
                            <td>   
                                <c:out value="${resultado.get(2).TotalMuestraPorProceso-resultado.get(2).TotalMuestraEncuestaTerminada}"/>
                            </td>
                            <td>   
                                <fmt:formatNumber type="number" maxFractionDigits="2" value="${100-(resultado.get(2).TotalMuestraEncuestaTerminada*100/resultado.get(2).TotalMuestraPorProceso)}"/>%
                            </td>
                        </tr>


                    </tbody>
                </table><br>
                <p>Estado por fuente del proceso:</p>
                <table class="table table-striped table-bordered table-condensed">
                    <thead>
                    <th>Fuente</th>
                    <th>Número total de muestra</th>
                    <th>Número de personas que han evaluado las encuestas</th>
                    <th>Porcentaje de personas que han evaluado las encuestas</th>
                    <th>Número de personas que faltan por evaluar las encuestas</th>
                    <th>Porcentaje de personas que faltan por evaluar las encuestas</th>
                    </thead>
                    <tbody>

                        <c:choose>
                            <c:when test="${fn:length(resultado.get(0))!= 0}">
                                <c:forEach items="${resultado.get(0)}" var="muestraInformeDTO" varStatus="iter">
                                    <tr>
                                        <td>
                                            ${muestraInformeDTO.fuente}
                                        </td>
                                        <td>
                                            ${muestraInformeDTO.cantidad}
                                        </td>
                                        <td>
                                            <c:forEach items="${resultado.get(1)}" var="muestraInformeDTO2" varStatus="iter2">
                                                <c:if test="${muestraInformeDTO.fuente==muestraInformeDTO2.fuente}">
                                                    ${muestraInformeDTO2.cantidad}
                                                </c:if>
                                            </c:forEach>
                                        </td>
                                        <td>
                                            <c:forEach items="${resultado.get(1)}" var="muestraInformeDTO2" varStatus="iter2">
                                                <c:if test="${muestraInformeDTO.fuente==muestraInformeDTO2.fuente}">
                                                    <c:choose>
                                                        <c:when test="${muestraInformeDTO2.cantidad!=0}">
                                                            <fmt:formatNumber type="number" maxFractionDigits="2" value="${muestraInformeDTO2.cantidad*100/muestraInformeDTO.cantidad}"/>%
                                                        </c:when>
                                                        <c:otherwise>
                                                            0%
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:if>
                                            </c:forEach>
                                        </td>
                                        <td>
                                            <c:forEach items="${resultado.get(1)}" var="muestraInformeDTO2" varStatus="iter2">
                                                <c:if test="${muestraInformeDTO.fuente==muestraInformeDTO2.fuente}">
                                                    <c:out value="${muestraInformeDTO.cantidad-muestraInformeDTO2.cantidad}"/>
                                                </c:if>
                                            </c:forEach>
                                        </td>
                                        <td>
                                            <c:forEach items="${resultado.get(1)}" var="muestraInformeDTO2" varStatus="iter2">
                                                <c:if test="${muestraInformeDTO.fuente==muestraInformeDTO2.fuente}">
                                                    <c:choose>
                                                        <c:when test="${muestraInformeDTO2.cantidad!=0}">
                                                            <fmt:formatNumber type="number" maxFractionDigits="2" value="${100-(muestraInformeDTO2.cantidad*100/muestraInformeDTO.cantidad)}"/>%
                                                        </c:when>
                                                        <c:otherwise>
                                                            0%
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:if>
                                            </c:forEach>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:when>
                        </c:choose>
                    <div id="container" style="height: 500px; margin: 0 auto" class="span10"></div>             
                    <br>
                    </tbody>
                </table>
            </fieldset>
        </div>
    </div>
</div>                        
