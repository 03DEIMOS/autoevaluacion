<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style type="text/css">
    .table td{
        font-size: 8px;
    }
    .table th {
        font-size: 8px;
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
</style>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="hero-unit">
    <div class="row">
        <div id="conte" class="span12">
            <fieldset>
                <legend>
                    Estado del proceso
                </legend>
                <br>
            </fieldset>
            <div>


                <c:forEach items="${resultado}" var="InformeDMA" varStatus="status">
                    <table class="table table-bordered" id="tableR">
                        <thead style="background-color: #ffffff;">
                            <tr>
                                <th class="span1">FACTOR</th>
                                <th class="span1">CARACTERISTICA</th>
                                <th class="span4">PREGUNTA</th>
                                    <c:forEach items="${InformeDMA.fuente}" var="fuente" varStatus="status2">
                                    <th class="span1">${fuente}</th>
                                    </c:forEach>
                            </tr>
                        </thead>
                        <tbody>
                            <c:choose>
                                <c:when test="${fn:length(InformeDMA.pregunta.itemPreguntas)!= 0}">
                                    <tr>
                                        <td rowspan="${InformeDMA.pregunta.itemPreguntas.size() + 1}">${InformeDMA.factor}</td>
                                        <td rowspan="${InformeDMA.pregunta.itemPreguntas.size()+1}">${InformeDMA.caracteristica}</td>
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
                                                        <td  bgcolor="#008000">${dma}</td>
                                                    </c:when>
                                                    <c:when  test="${ dma >= 50.0 && dma < 70.0}">
                                                        <td  bgcolor="#FFFF00">${dma}</td>
                                                    </c:when>    
                                                    <c:when  test="${ dma < 50.0 && dma > 0.0}">
                                                        <td  bgcolor="#FF0000">${dma}</td>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <td>${dma}</td>
                                                    </c:otherwise>    
                                                </c:choose>
                                            </c:forEach>
                                        </tr>
                                    </c:forEach>

                                </c:when>
                                <c:otherwise>
                                    <tr>
                                        <td rowspan="2">${InformeDMA.factor}</td>
                                        <td rowspan="2">${InformeDMA.caracteristica}</td>
                                        <td>${InformeDMA.pregunta.pregunta}</td>
                                        <c:forEach items="${InformeDMA.DMA}" var="dma" varStatus="status5">
                                            <c:choose>
                                                <c:when  test="${dma==-1}">
                                                    <td>N/A</td>
                                                </c:when>
                                                <c:when  test="${dma>=70}">
                                                    <td  bgcolor="#008000">${dma}</td>
                                                </c:when>
                                                <c:when  test="${ dma < 50 && dma > 0}">
                                                    <td  bgcolor="#FF0000">${dma}</td>
                                                </c:when>
                                                <c:when  test="${ dma >= 50 && dma < 70}">
                                                    <td  bgcolor="#FFFF00">${dma}</td>
                                                </c:when>
                                                <c:otherwise>
                                                    <td>${dma}</td>
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




