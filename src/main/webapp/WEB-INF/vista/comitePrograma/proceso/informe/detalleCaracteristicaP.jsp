<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style type="text/css">

    .inicial td {
        text-align: right;
    }
</style>
<script type="text/javascript">
    $(function () {
        $('.tool').tooltip().click(function(e){
        $(this).tooltip('hide');
        });
    });
</script>
<div class="hero-unit">
    <div class="row">
        <div id="conte" class="span10">
            <legend>Caracter&iacute;stica: ${caracteristica.nombre}</legend>
            <ul class="breadcrumb">
                <li><a href="#informe/informeFactores/${procesoId}">Matriz de Calidad de Factores</a> <span class="divider">/</span></li>
                <li><a href="#informe/informeCaracteristicas/${procesoId}">Matriz de Calidad de Caracter&iacute;sticas</a> <span class="divider">/</span></li>
                <li>
                    <a href="#informe/detallePFactor/${procesoId}/${caracteristica.getFactorId().id}" class="tool" data-placement="top" rel="tooltip" data-original-title="${caracteristica.getFactorId().nombre}">Factor ${caracteristica.getFactorId().codigo}</a> 
                    <span class="divider">/</span>
                </li>
                <li class="active tool" data-placement="top" rel="tooltip" data-original-title="${caracteristica.nombre}">Caracter&iacute;stica ${caracteristica.codigo}</li>

            </ul>
            <br>
            <c:choose>
                <c:when test="${resultado.preguntaReales.size()!= 0}">

                    <table class="table table-striped table-bordered table-condensed inicial">
                        <thead>
                        <th>Pregunta</th>
                        <th>Promedio respuesta</th>
                        </thead>
                        <tbody>
                            <c:set var="indice" value="0"></c:set>
                            <c:forEach items="${resultado.preguntaReales}" var="pregunta" varStatus="iter">
                                <tr>
                                    <td style="text-align: left">   
                                        ${pregunta}
                                    </td>
                                    <td>   
                                        <fmt:formatNumber type="number" maxFractionDigits="1" value="${resultado.promediorespuestas[iter.index]}"/>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <br/>
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Escala</th>
                                <th>Descripci&oacute;n</th>
                                <th>Grado de cumplimiento</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr style="background-color: #89A54E;">
                                <td>
                                    4.5 a 5.0
                                </td>
                                <td>
                                    Se cumple plenamente
                                </td>
                                <td>
                                    90% a 100%
                                </td>
                            </tr>
                            <tr style="background-color: #80699B;">
                                <td>
                                    4.0 a 4.4
                                </td>
                                <td>
                                    Se cumple en alto grado
                                </td>
                                <td>
                                    80% a 89%
                                </td>
                            </tr>
                            <tr style="background-color: #3D96AE;">
                                <td>
                                    3.0 a 3.9
                                </td>
                                <td>
                                    Se cumple en mediano grado
                                </td>
                                <td>
                                    60% a 79%
                                </td>
                            </tr>
                            <tr style="background-color: #DB843D;">
                                <td>
                                    2.0 a 2.9
                                </td>
                                <td>
                                    Se cumple en bajo grado
                                </td>
                                <td>
                                    40% - 59%
                                </td>
                            </tr>
                            <tr style="background-color: #AA4643;">
                                <td>
                                    1.0 a 1.9
                                </td>
                                <td>
                                    No se cumple
                                </td>
                                <td>
                                    0% - 39%
                                </td>
                            </tr>

                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    No Existen Hay datos Registrados en el Sistema.
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>    
