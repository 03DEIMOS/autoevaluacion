<%-- 
    Document   : informePreguntas
    Created on : 2/11/2021, 11:08:32 PM
    Author     : DEIMOS
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="hero-unit">
    <div class="row">
        <input type="button" class="btn btn-primary" onclick="tableToExcel('conte', 'Informe  de  Preguntas por Proceso')" value="Exportar a Excel">
        <div id="conte" class="span10">
            <h3 style="margin: 0;">Informe  de  Preguntas por Proceso</h3>
            <br/>
            <c:choose>
                <c:when test="${fn:length(resultado)!= 0}">
                    <c:forEach items="${resultado}" var="informeValoresAbsolutosDTO" varStatus="status0">

                        <div class="row">
                            <div class="span12">
                                <p style="font-weight: bold;"> ${status0.index+1} ${informeValoresAbsolutosDTO.pregunta.getPregunta()}</p>
                                <c:choose>
                                    <c:when test="${informeValoresAbsolutosDTO.pregunta.disenio=='Vertical' && fn:length(informeValoresAbsolutosDTO.pregunta.getTipoPregunta().getItemTipoPreguntaList())!= 0}">
                                        <c:forEach items="${informeValoresAbsolutosDTO.pregunta.getTipoPregunta().getItemTipoPreguntaList()}" var="tipoPregunta" varStatus="status">
                                            <c:choose>
                                                <c:when test="${informeValoresAbsolutosDTO.totalPersonaQueContestaron > 0}">
                                                    <c:set var="porcentaje" 
                                                           value="${100* informeValoresAbsolutosDTO.getResultadosAbsolutosSinSubpreguntaContador()
                                                                    .get(status.index)/informeValoresAbsolutosDTO.totalPersonaQueContestaron}"/>
                                                </c:when>
                                                <c:otherwise><c:set var="porcentaje" value="0"/></c:otherwise>
                                            </c:choose>
                                            <label class="radio">
                                                <fmt:formatNumber type="number" maxFractionDigits="1" value="${porcentaje}"/>%
                                                (${informeValoresAbsolutosDTO.getResultadosAbsolutosSinSubpreguntaContador().get(status.index)})  -  ${tipoPregunta.respuesta}</label>
                                            </c:forEach>
                                        </c:when>
                                        <c:when test="${informeValoresAbsolutosDTO.pregunta.disenio=='Horizontal' && fn:length(informeValoresAbsolutosDTO.pregunta.getTipoPregunta().getItemTipoPreguntaList())!= 0}">
                                        <table class="table">
                                            <thead>
                                                <tr>
                                                    <c:forEach items="${informeValoresAbsolutosDTO.pregunta.getTipoPregunta().getItemTipoPreguntaList()}" var="tipoPregunta" varStatus="iter">
                                                        <c:out escapeXml="false" value='<th> ${tipoPregunta.respuesta} ${informeValoresAbsolutosResult.getResultadosAbsolutosSinSubpreguntaContador()}</th>'/>
                                                    </c:forEach>
                                                    <c:out escapeXml="false" value='<th>Total</th>'/>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <c:forEach items="${informeValoresAbsolutosDTO.getResultadosAbsolutosSinSubpreguntaContador()}" var="resultadoAbsoluto">
                                                        <c:choose>
                                                            <c:when test="${informeValoresAbsolutosDTO.totalPersonaQueContestaron > 0}">
                                                                <c:set var="porcentaje" 
                                                                       value="${100* resultadoAbsoluto/informeValoresAbsolutosDTO.totalPersonaQueContestaron}"/>
                                                            </c:when>
                                                            <c:otherwise><c:set var="porcentaje" value="0"/></c:otherwise>
                                                        </c:choose>
                                                        <td><label class="radio" ><fmt:formatNumber type="number" maxFractionDigits="1" value="${porcentaje}"/>%(${resultadoAbsoluto})</label></td>
                                                        </c:forEach>
                                                        <c:out escapeXml="false" value='<td>${informeValoresAbsolutosDTO.totalPersonaQueContestaron}</td>'/>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </c:when>
                                    <c:when test="${informeValoresAbsolutosDTO.pregunta.disenio=='Matriz con unica respuesta' && fn:length(informeValoresAbsolutosDTO.pregunta.getTipoPregunta().getItemTipoPreguntaList())!= 0}">
                                        <table class="table">
                                            <thead>
                                                <tr>
                                                    <th></th>
                                                        <c:forEach items="${informeValoresAbsolutosDTO.pregunta.getTipoPregunta().getItemTipoPreguntaList()}" var="tipoPregunta" varStatus="iter">
                                                            <c:out escapeXml="false" value='<th>${tipoPregunta.respuesta}</th>'/>
                                                        </c:forEach>
                                                        <c:out escapeXml="false" value='<th>Total</th>'/>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:choose>
                                                    <c:when test="${fn:length(informeValoresAbsolutosDTO.itemPregunta)!= 0}">
                                                        <c:forEach items="${informeValoresAbsolutosDTO.itemPregunta}" var="itemPregunta" varStatus="status">
                                                            <tr>
                                                                <td style="text-align: left">${itemPregunta}</td>
                                                                <c:choose>
                                                                    <c:when test="${fn:length(informeValoresAbsolutosDTO.getResultadosAbsolutosConSubpreguntaContador().get(status.index))!= 0}">
                                                                        <c:forEach items="${informeValoresAbsolutosDTO.getResultadosAbsolutosConSubpreguntaContador().get(status.index)}" var="resultadoAbsoluto">
                                                                            <c:choose>
                                                                                <c:when test="${informeValoresAbsolutosDTO.totalPersonaQueContestaron > 0}">
                                                                                    <c:set var="porcentaje" 
                                                                                           value="${100* resultadoAbsoluto/informeValoresAbsolutosDTO.totalPersonaQueContestaron}"/>
                                                                                </c:when>
                                                                                <c:otherwise><c:set var="porcentaje" value="0"/></c:otherwise>
                                                                            </c:choose>
                                                                            <td><label class="radio"><fmt:formatNumber type="number" maxFractionDigits="1" value="${porcentaje}"/>%(${resultadoAbsoluto})</label></td>
                                                                        </c:forEach>
                                                                    </c:when>
                                                                </c:choose>
                                                                <c:out escapeXml="false" value='<td>${informeValoresAbsolutosDTO.totalPersonaQueContestaron}</td>'/>
                                                            </tr>
                                                        </c:forEach>
                                                    </c:when>
                                                </c:choose>
                                            </tbody>
                                        </table>
                                    </c:when>
                                    <c:when test="${informeValoresAbsolutosDTO.pregunta.disenio=='Desplegable' 
                                                    && fn:length(informeValoresAbsolutosDTO.pregunta.getTipoPregunta().getItemTipoPreguntaList())!= 0}">
                                        <c:choose>
                                            <c:when test="${fn:length(informeValoresAbsolutosDTO.pregunta.getItemPreguntas())!= 0}">
                                                <table class="table">
                                                    <thead>
                                                        <tr>
                                                            <th></th>
                                                                <c:forEach items="${informeValoresAbsolutosDTO.pregunta.getTipoPregunta().getItemTipoPreguntaList()}" var="tipoPregunta" varStatus="iter">
                                                                    <c:out escapeXml="false" value='<th>${tipoPregunta.respuesta}</th>'/>
                                                                </c:forEach>
                                                                <c:out escapeXml="false" value='<th>Total</th>'/>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:choose>
                                                            <c:when test="${fn:length(informeValoresAbsolutosDTO.itemPregunta)!= 0}">
                                                                <c:forEach items="${informeValoresAbsolutosDTO.itemPregunta}" var="itemPregunta" varStatus="status">
                                                                    <tr>
                                                                        <td style="text-align: left">${itemPregunta}</td>
                                                                        <c:choose>
                                                                            <c:when test="${fn:length(informeValoresAbsolutosDTO.getResultadosAbsolutosConSubpreguntaContador().get(status.index))!= 0}">
                                                                                <c:forEach items="${informeValoresAbsolutosDTO.getResultadosAbsolutosConSubpreguntaContador().get(status.index)}" var="resultadoAbsoluto">
                                                                                    <c:choose>
                                                                                        <c:when test="${informeValoresAbsolutosDTO.totalPersonaQueContestaron > 0}">
                                                                                            <c:set var="porcentaje" 
                                                                                                   value="${100* resultadoAbsoluto/informeValoresAbsolutosDTO.totalPersonaQueContestaron}"/>
                                                                                        </c:when>
                                                                                        <c:otherwise><c:set var="porcentaje" value="0"/></c:otherwise>
                                                                                    </c:choose>
                                                                                    <td><label class="radio"><fmt:formatNumber type="number" maxFractionDigits="1" value="${porcentaje}"/>%(${resultadoAbsoluto})</label></td>
                                                                                </c:forEach>
                                                                            </c:when>
                                                                        </c:choose>
                                                                        <c:out escapeXml="false" value='<td>${informeValoresAbsolutosDTO.totalPersonaQueContestaron}</td>'/>
                                                                    </tr>
                                                                </c:forEach>
                                                            </c:when>
                                                        </c:choose>
                                                    </tbody>
                                                </table>    
                                            </c:when>
                                            <c:otherwise>
                                                <c:forEach items="${informeValoresAbsolutosDTO.pregunta.getTipoPregunta().getItemTipoPreguntaList()}" var="tipoPregunta" varStatus="status">
                                                    <c:choose>
                                                        <c:when test="${informeValoresAbsolutosDTO.totalPersonaQueContestaron > 0}">
                                                            <c:set var="porcentaje" 
                                                                   value="${100* informeValoresAbsolutosDTO.getResultadosAbsolutosSinSubpreguntaContador().get(status.index)/informeValoresAbsolutosDTO.totalPersonaQueContestaron}"/>
                                                        </c:when>
                                                        <c:otherwise><c:set var="porcentaje" value="0"/></c:otherwise>
                                                    </c:choose>
                                                    <label class="radio"><fmt:formatNumber type="number" maxFractionDigits="1" value="${porcentaje}"/>%(${informeValoresAbsolutosDTO.getResultadosAbsolutosSinSubpreguntaContador().get(status.index)})  -  ${tipoPregunta.respuesta}</label>
                                                </c:forEach>
                                                <label class="radio">${informeValoresAbsolutosDTO.totalPersonaQueContestaron} - Total</label>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:when>
                                </c:choose>
                            </div>
                        </div>
                    </c:forEach>
                    <script type="text/javascript">
                        var tableToExcel = (function() {
                                var uri = 'data:application/vnd.ms-excel;base64,'
                                        , template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--><meta http-equiv="content-type" content="text/plain; charset=UTF-8"/></head><body><table>{table}</table></body></html>'
    , base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) }
    , format = function(s, c) { return s.replace(/{(\w+)}/g, function(m, p) { return c[p]; }) }
  return function(table, name) {
    if (!table.nodeType) table = document.getElementById(table)
    var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}
    window.location.href = uri + base64(format(template, ctx))
  }
})()
                    </script>
                </c:when>
                <c:otherwise>
                    No existen preguntas registradas en el sistema para este proceso.
                </c:otherwise>
            </c:choose>
            <br/>
        </div>
    </div>
</div>    