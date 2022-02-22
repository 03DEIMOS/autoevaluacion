<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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


