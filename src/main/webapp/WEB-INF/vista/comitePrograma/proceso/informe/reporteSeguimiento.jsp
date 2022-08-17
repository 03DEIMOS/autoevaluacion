<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript">

    $(function () {
        $("#descargarReporteSeguimiento").click(function () {
            fetch('/autoevaluacion/oportunidadMejora/descargarReporteSeguimiento/'+${planMejoramiento.id})
                    .then(resp => resp.blob())
                    .then(blob => {
                        const url = window.URL.createObjectURL(blob);
                        const a = document.createElement('a');
                        a.style.display = 'none';
                        a.href = url;
                        // the filename you want
                        a.download = 'reporteSeguimientos.xls';
                        document.body.appendChild(a);
                        a.click();
                        window.URL.revokeObjectURL(url);
                    })
                    .catch(() => alert('Ha ocurrido un error intentando descargar el formato de población!'));
        });
    });
</script>


<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    <body style="padding-top: 0px;">
        <br>
        <div class="hero-unit">
            <div style="margin-left: -30px;">
                <div id="conte" class="span12" style="text-align: justify">
                    <a id="descargarReporteSeguimiento" target="_blank"><i class="icon-download-alt"></i> Descargar Reporte en Excel</a>
                    <table class="table table-bordered table-striped">
                        <tbody>
                            <tr>
                                <td colspan="2" style="text-align: center;">REPORTE DE SEGUIMIENTOS</td>
                            </tr>
                            <tr>
                                <td><strong>Plan de mejoramiento:</strong></td>
                                <td>${planMejoramiento.plan}</td>
                            </tr>
                        </tbody>
                    </table>
                    <c:choose>
                        <c:when test="${fn:length(oportunidadesMejora)!= 0}">
                            <table id="tablaX" class="table table-striped table-bordered table-condensed">
                                <c:forEach items="${oportunidadesMejora}" var="oportunidadMejora" varStatus="iter">
                                    <thead>
                                    <th>Factor</th>
                                    <th>Caracteristica</th>
                                    <th>Oportunidad de mejoramiento</th>
                                    <th>Eje estrategico del PDI</th>
                                    <th>L&iacute;neas de Acci&oacute;n del PDI</th>    
                                    <th>Estado</th>    
                                    <th>Tipo</th>    
                                    <th>Reponsable</th>    
                                    <th>Fecha inicio</th>    
                                    <th>Fecha fin</th>    
                                    </thead>
                                    <tbody>

                                        <tr>
                                            <td>   
                                                <c:out value="${oportunidadMejora.caracteristicaId.factorId.nombre}"/>
                                            </td>
                                            <td>   
                                                <c:out value="${oportunidadMejora.caracteristicaId.nombre}"/>
                                            </td>
                                            <td>   
                                                <c:out value="${oportunidadMejora.hallazgo}"/>
                                            </td>
                                            <td>   
                                                <c:out value="${oportunidadMejora.eje}"/>
                                            </td>
                                            <td>   
                                                <c:out value="${oportunidadMejora.lineaAccion}"/>
                                            </td>
                                            <td>   
                                                <c:out value="${oportunidadMejora.estadoId.tipo}"/>
                                            </td>
                                            <td>   
                                                <c:out value="${oportunidadMejora.tipo}"/>
                                            </td>
                                            <td>   
                                                <c:out value="${oportunidadMejora.responsable}"/>
                                            </td>
                                            <td>   
                                                <c:out value="${oportunidadMejora.fechaInicio}"/>
                                            </td>
                                            <td>   
                                                <c:out value="${oportunidadMejora.fechaFin}"/>
                                            </td>                                           
                                        </tr>
                                        <tr>
                                            <td colspan="10" style="text-align: center;">   
                                                SEGUIMIENTOS
                                            </td>   
                                            <c:choose>
                                                <c:when test="${fn:length(oportunidadMejora.seguimientos)!= 0}">
                                                <tr>
                                                    <td colspan="10">
                                                        <table id="tablaX" class="table table-striped table-bordered table-condensed">
                                                            <thead>
                                                            <th>Fecha Realizado</th>
                                                            <th>Avances</th>
                                                            </thead>
                                                            <tbody>
                                                                <c:forEach items="${oportunidadMejora.seguimientos}" var="seguimiento">
                                                                    <tr>
                                                                        <td>   
                                                                            <c:out value="${seguimiento.fechaRealizado}"/>
                                                                        </td>
                                                                        <td>   
                                                                            <c:out value="${seguimiento.avances}"/>
                                                                        </td>
                                                                    </tr>
                                                                </c:forEach>
                                                            </tbody>
                                                        </table>
                                                    </td>           
                                                </tr>
                                            </c:when>
                                            <c:otherwise>
                                                <tr>
                                                    <td colspan="10">No hay seguimientos asociados a la oportunidad de mejora.</td>
                                                </tr>
                                            </c:otherwise>    
                                        </c:choose>
                                    </c:forEach>             
                                </tbody>
                            </table>
                        </c:when>
                        <c:otherwise>
                            Aun no existen Oportunidades de Mejora para este plan de de mejoramiento.<br/>
                        </c:otherwise>
                    </c:choose>      

                </div>
            </div>
        </div>
    </body>
</html>