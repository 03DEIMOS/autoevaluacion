<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style type="text/css">

    .inicial td {
        text-align: right;
    }
</style>

<div class="hero-unit">
    <div class="row">
        <div id="conte" class="span10">
            <legend>Matriz de Calidad de Caracteristicas</legend>
            <ul class="breadcrumb">
                <li><a href="#informe/informeFactores/${procesoId}">Matriz de Calidad de Factores</a> <span class="divider">/</span></li>
                <li class="active">Matriz de Calidad de Características</li>
            </ul>
            <br>
            <c:choose>
                <c:when test="${resultado.caracteristicas.size()!= 0}">

                    <table class="table table-striped table-bordered table-condensed inicial">
                        <thead>
                        <th>Id</th>
                        <th>Caracteristica</th>
                        <th>Grado de Cumplimiento</th>
                        </thead>
                        <tbody>
                            <c:set var="indice" value="0"></c:set>
                            <c:forEach items="${resultado.caracteristicas}" var="caracteristica" varStatus="iter">
                                <fmt:parseNumber var="cum"  value="${resultado.cumplimiento[iter.index]}" />
                                <c:choose>
                                    <c:when test="${cum>0}"> 
                                        <tr>
                                            <td style="text-align: left">   
                                                ${caracteristica.codigo}
                                            </td>
                                            <td style="text-align: left">   
                                                <a href="#informe/detallePCaracteristica/${procesoId}/${caracteristica.id}" data="${caracteristica.nombre}">${caracteristica.nombre}</a> 
                                            </td>
                                            <td>   
                                                <fmt:formatNumber type="number" maxFractionDigits="1" value="${resultado.cumplimiento[iter.index]}"/>
                                            </td>
                                        </tr>
                                        <c:set var="indice" value="${indice+1}"></c:set>
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
