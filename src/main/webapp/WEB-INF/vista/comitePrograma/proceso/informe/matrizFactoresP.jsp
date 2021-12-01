<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style type="text/css">

    .inicial td {
        text-align: right;
    }
</style>

<div class="hero-unit">
    <div class="row">
        <div id="conte" class="span10">
            <legend>Matriz de Calidad de Factores</legend>
            <ul class="breadcrumb">
                <li class="active">Matriz de Calidad de Factores  <span class="divider">/</span></li>
                <li><a href="<%=request.getContextPath()%>/#informe/informeCaracteristicas/${procesoId}">Matriz de Calidad de Características</a></li>
            </ul>
            <br>
            <c:choose>
                <c:when test="${resultado.factores.size()!= 0}">

                    <table class="table table-striped table-bordered table-condensed inicial">
                        <thead>
                        <th>Id Factor</th>
                        <th>Factor</th>
                        <th>Grado de Cumplimiento</th>
                        </thead>
                        <tbody>
                            <c:set var="indice2" value="0"></c:set>
                            <c:forEach items="${resultado.factores}" var="factor" varStatus="iter2">
                                <fmt:parseNumber var="cum2"  value="${resultado.cumplimientoF[iter2.index]}" />
                                <c:choose>
                                    <c:when test="${cum2>0}"> 
                                        <tr>
                                            <td style="text-align: left">   
                                                ${factor.codigo}
                                            </td>

                                            <td style="text-align: left">   
                                                <a href="#informe/detallePFactor/${procesoId}/${factor.id}" data="${factor.nombre}">${factor.nombre}</a>
                                            </td>
                                            <td>   
                                                <fmt:formatNumber type="number" maxFractionDigits="1" value="${resultado.cumplimientoF[iter2.index]}"/>
                                            </td>
                                        </tr>
                                        <c:set var="indice2" value="${indice2+1}"></c:set>
                                    </c:when>
                                </c:choose>       
                            </c:forEach>
                        </tbody>
                    </table>
                    <br/>          
                </c:when>
                <c:otherwise>
                    No Existen Hay datos Registrados en el Sistema.
                </c:otherwise>
            </c:choose> 
        </div>
    </div>
</div>

