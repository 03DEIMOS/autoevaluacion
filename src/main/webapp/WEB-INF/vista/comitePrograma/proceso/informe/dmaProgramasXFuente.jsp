<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style type="text/css">
    .table td{
        font-size: 10px;
        vertical-align: middle;
    }
    .table thead th {
        font-size: 11px;
        vertical-align: middle;
        text-align: center;
    }
    .table .success{
        background-color: #DFF0D8; 
        color: #468847;
    }
    .table .error{
        background-color: #F2DEDE; 
        color: #B94A48;
    }
    .table .warning{
        background-color: #f0ad4e;
        color: #fff;
    }

    .form-row {
        display: -webkit-box;
        display: -ms-flexbox;
        display: flex;
        -ms-flex-wrap: wrap;
        flex-wrap: wrap;
        margin-right: -5px;
        margin-left: -5px;
    }

</style>
<script type="text/javascript">
    $(function() {
        $("#filtrar").click(function() {
            var a = $("#variables1 option:selected").index();
            var b = $("#variables2 option:selected").index();
            console.log(a);
            console.log(b);
            if ((a==-1||a == 0) && (b==-1 || b == 0)) {
                $("#help1").html('<div class="alert alert-info" role="alert"><strong>Atenci&oacute;n</strong> Seleccione al menos una opción para poder filtrar.</div>');
            }else {//para hacer el editar muestra
                $("#help1").empty();
                $("#tablaInformeDMA").empty();
                $.ajax({
                    type: 'POST',
                    url: "/autoevaluacion/informe/informeDMAFiltrado/proceso/${proceso.id}/publico/${fuente.id}",
                    data: $("#formFiltro").serialize(),
                    success: function(datos) {
                        $("#tablaInformeDMA").append(datos);
                        setTimeout(function() {
                            $("#dancing-dots-text").remove();
                        }, 200);
                    } //fin success
                }); //fin $.ajax    
            }
        });
    });
</script>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="hero-unit">
    <div class="row">
        <input type="button" class="btn btn-primary" onclick="tableToExcel('conte', 'Informe  de  Preguntas por Proceso')" value="Exportar a Excel">
        <div id="conte" class="span12">
            <form id="formFiltro" class=""  style="margin-bottom: 0px">
                <fieldset>
                    <legend>
                        Informe DMA por Fuente
                    </legend>
                    <br>
                </fieldset>

                <div class="span10" style="margin-left: 0px">
                    <div class="form-inline">
                        <c:if test="${fn:length(variables1)!= 0}">
                            <label for="variables1">Variable1</label>
                            <select name="variables1" id="variables1" class="form-control">
                                <option value="--">Seleccionar</option>
                                <c:choose>
                                    <c:when test="${fn:length(variables1)!= 0}">
                                        <c:forEach items="${variables1}" var="variable1" varStatus="iter">
                                            <option value="${variable1}">${variable1}</option>
                                        </c:forEach>
                                    </c:when>
                                </c:choose>
                            </select>
                        </c:if>
                        <c:if test="${fn:length(variables2)!= 0}">
                            <label for="variables2">Variable2</label>
                            <select name="variables2" id="variables2" class="form-control">
                                <option value="--">Seleccionar</option>
                                <c:forEach items="${variables2}" var="variable2" varStatus="iter">
                                    <option value="${variable2}">${variable2}</option>
                                </c:forEach>
                            </select>
                        </c:if>
                        <c:if test="${fn:length(variables1)!= 0 || fn:length(variables2)!= 0}">
                            <button type="button" class="btn btn-primary" id="filtrar">Filtrar</button>
                        </c:if>    

                    </div>

                </div>
            </form> 
            <br/>
            <div id="help1"></div>
            <div id="tablaInformeDMA">
                <c:forEach items="${resultado}" var="InformeDMA" varStatus="status">
                    <table class="table table-bordered" id="tableR">
                        <thead style="background-color: #ffffff;">
                            <tr>
                                <th class="span1" rowspan="2" >Factor</th>
                                <th class="span1" rowspan="2">Caracteristica</th>
                                <th class="span4" rowspan="2">Pregunta</th>
                                <th class="span6" colspan="${InformeDMA.fuente.size()}" style="text-align: center">Público</th>    
                            </tr>
                            <tr>
                                <c:forEach items="${InformeDMA.fuente}" var="fuente" varStatus="status2">
                                    <th class="span1">${fuente}</th>
                                    </c:forEach>
                            </tr>
                        </thead>
                        <tbody>
                            <c:choose>
                                <c:when test="${fn:length(InformeDMA.pregunta.itemPreguntas)!= 0}">
                                    <tr>
                                        <td rowspan="${InformeDMA.pregunta.itemPreguntas.size() + 1}">${InformeDMA.factor.codigo} ${InformeDMA.factor.nombre}</td>
                                        <td rowspan="${InformeDMA.pregunta.itemPreguntas.size()+1}">${InformeDMA.caracteristica.codigo} ${InformeDMA.caracteristica.nombre}</td>
                                        <td>${InformeDMA.pregunta.pregunta}</td>
                                        <td colspan="${InformeDMA.fuente.size()}"></td>
                                    </tr>

                                    <c:forEach items="${InformeDMA.itemPregunta}" var="itemPregunta" varStatus="status3">
                                        <tr>
                                            <td>${itemPregunta}</td>
                                            <c:forEach items="${InformeDMA.DMAList.get(status3.index)}" var="dma" varStatus="status4">
                                                <c:choose>
                                                    <c:when  test="${dma==-1}">
                                                        <td>N/A</td>
                                                    </c:when>
                                                    <c:when  test="${dma>=70.0}">
                                                        <td  bgcolor="#008000"><fmt:formatNumber type="number" maxFractionDigits="1" value="${dma}"/></td>
                                                    </c:when>
                                                    <c:when  test="${ dma >= 50.0 && dma < 70.0}">
                                                        <td  bgcolor="#FFFF00"><fmt:formatNumber type="number" maxFractionDigits="1" value="${dma}"/></td>
                                                    </c:when>    
                                                    <c:when  test="${ dma < 50.0}">
                                                        <td  bgcolor="#FF0000"><fmt:formatNumber type="number" maxFractionDigits="1" value="${dma}"/></td>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <td><fmt:formatNumber type="number" maxFractionDigits="1" value="${dma}"/></td>
                                                    </c:otherwise>    
                                                </c:choose>
                                            </c:forEach>
                                        </tr>
                                    </c:forEach>

                                </c:when>
                                <c:otherwise>
                                    <tr>
                                        <td rowspan="2">${InformeDMA.factor.codigo} ${InformeDMA.factor.nombre}</td>
                                        <td rowspan="2">${InformeDMA.caracteristica.codigo} ${InformeDMA.caracteristica.nombre}</td>
                                        <td>${InformeDMA.pregunta.pregunta}</td>
                                        <c:forEach items="${InformeDMA.DMA}" var="dma" varStatus="status5">
                                            <c:choose>
                                                <c:when  test="${dma==-1}">
                                                    <td>N/A</td>
                                                </c:when>
                                                <c:when  test="${dma>=70}">
                                                    <td  bgcolor="#008000"><fmt:formatNumber type="number" maxFractionDigits="1" value="${dma}"/></td>
                                                </c:when>
                                                <c:when  test="${ dma < 50 && dma > 0}">
                                                    <td  bgcolor="#FF0000"><fmt:formatNumber type="number" maxFractionDigits="1" value="${dma}"/></td>
                                                </c:when>
                                                <c:when  test="${ dma >= 50 && dma < 70}">
                                                    <td  bgcolor="#FFFF00"><fmt:formatNumber type="number" maxFractionDigits="1" value="${dma}"/></td>
                                                </c:when>
                                                <c:otherwise>
                                                    <td><fmt:formatNumber type="number" maxFractionDigits="1" value="${dma}"/></td>
                                                </c:otherwise>    
                                            </c:choose>
                                        </c:forEach>
                                    </tr>
                                </c:otherwise>
                            </c:choose>
                        </tbody>
                    </table>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
                    <script type="text/javascript">
                            var tableToExcel = (function() {             var uri = 'data:application/vnd.ms-excel;base64,'
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



