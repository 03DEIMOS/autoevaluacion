<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="control-group">
    <div class="controls">
        <h4>${pregunta.pregunta}</h4>
        <table>
            <thead>
                <tr>
                    <th></th>
                        <c:choose>
                            <c:when test="${fn:length(pregunta.getTipoPregunta().getItemTipoPreguntaList())!= 0}">
                                <c:forEach items="${pregunta.getTipoPregunta().getItemTipoPreguntaList()}" var="tipoPregunta" varStatus="iter">
                                <th>${tipoPregunta.respuesta}</th>
                                </c:forEach>
                            </c:when>
                        </c:choose>

                </tr>
            </thead>

            <tbody>

                <c:choose>
                    <c:when test="${fn:length(pregunta.getItemPreguntas())!= 0 && sencilla==false}">
                        <c:forEach items="${pregunta.getItemPreguntas()}" var="itemPregunta" varStatus="iter2">
                            <tr>
                                <td style="text-align: left">${itemPregunta.itemPregunta}</td>
                                <c:choose>
                                    <c:when test="${fn:length(pregunta.getTipoPregunta().getItemTipoPreguntaList())!= 0}">
                                        <c:forEach items="${pregunta.getTipoPregunta().getItemTipoPreguntaList()}" var="tipoPregunta2" varStatus="iter">
                                            <td><input type="radio" name="${iter.index}" value="${tipoPregunta2.id}"/></td>
                                            </c:forEach>
                                        </c:when>
                                    </c:choose>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:when test="${sencilla==true}">
                        <tr>
                            <td style="text-align: left"></td>
                            <c:choose>
                                <c:when test="${fn:length(pregunta.getTipoPregunta().getItemTipoPreguntaList())!= 0}">
                                    <c:forEach items="${pregunta.getTipoPregunta().getItemTipoPreguntaList()}" var="tipoPregunta2" varStatus="iter">
                                        <td><input type="radio" name="${iter.index}" value="${tipoPregunta2.id}"/></td>
                                        </c:forEach>
                                    </c:when>
                                </c:choose>
                        </tr>
                    </c:when>       
                </c:choose>
            </tbody>
        </table>
    </div>
</div>