<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="control-group">
    <div class="controls">
        <p><strong>${pregunta.pregunta}</strong></p>
        <c:choose>
            <c:when test="${pregunta.disenio=='Vertical' && fn:length(pregunta.getTipoPregunta().getItemTipoPreguntaList())!= 0}">
                <c:forEach items="${pregunta.getTipoPregunta().getItemTipoPreguntaList()}" var="tipoPregunta" varStatus="iter">
                    <c:out escapeXml="false" value='<label class="radio"><input type="radio" name="pregunta.id">${tipoPregunta.respuesta}</label>'/>
                </c:forEach>
            </c:when>
            <c:when test="${pregunta.disenio=='Horizontal' && fn:length(pregunta.getTipoPregunta().getItemTipoPreguntaList())!= 0}">
                <table class="table">
                    <thead>
                        <tr>
                            <c:forEach items="${pregunta.getTipoPregunta().getItemTipoPreguntaList()}" var="tipoPregunta" varStatus="iter">
                                <c:out escapeXml="false" value='<th>${tipoPregunta.respuesta}</th>'/>
                            </c:forEach>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <c:forEach items="${pregunta.getTipoPregunta().getItemTipoPreguntaList()}" var="tipoPregunta" varStatus="iter">
                                <c:out escapeXml="false" value='<td><label class="radio" ><input type="radio" name="${pregunta.id}"></label></td>'/>
                            </c:forEach>
                        </tr>
                    </tbody>
                </table>
            </c:when>
            <c:when test="${pregunta.disenio=='Matriz con unica respuesta' && fn:length(pregunta.getTipoPregunta().getItemTipoPreguntaList())!= 0}">
                <table class="table">
                    <thead>
                        <tr>
                            <th></th>
                                <c:forEach items="${pregunta.getTipoPregunta().getItemTipoPreguntaList()}" var="tipoPregunta" varStatus="iter">
                                    <c:out escapeXml="false" value='<th>${tipoPregunta.respuesta}</th>'/>
                                </c:forEach>
                        </tr>
                    </thead>
                    <tbody>
                        <c:choose>
                            <c:when test="${fn:length(pregunta.getItemPreguntas())!= 0}">
                                <c:forEach items="${pregunta.getItemPreguntas()}" var="itemPregunta" varStatus="iter2">
                                    <tr>
                                        <td style="text-align: left">${itemPregunta.itemPregunta}</td>
                                        <c:choose>
                                            <c:when test="${fn:length(pregunta.getTipoPregunta().getItemTipoPreguntaList())!= 0}">
                                                <c:forEach items="${pregunta.getTipoPregunta().getItemTipoPreguntaList()}" var="tipoPregunta2" varStatus="iter">
                                                    <c:out escapeXml="false" value='<td><label class="radio"><input type="radio" name="${itemPregunta.id}" value="${tipoPregunta2.id}"/></label></td>'/>
                                                </c:forEach>
                                            </c:when>
                                        </c:choose>
                                    </tr>
                                </c:forEach>
                            </c:when>
                        </c:choose>
                    </tbody>
                </table>
            </c:when>
            <c:when test="${pregunta.disenio=='Desplegable' && fn:length(pregunta.getTipoPregunta().getItemTipoPreguntaList())!= 0}">
                <c:choose>
                    <c:when test="${fn:length(pregunta.getItemPreguntas())!= 0}">
                        <table class="table">
                            <tbody>
                                <c:forEach items="${pregunta.getItemPreguntas()}" var="itemPregunta" varStatus="iter2">
                                    <tr>
                                        <td style="text-align: left">${itemPregunta.itemPregunta}</td>
                                        <td> 
                                            <select name="respuestas">
                                                <option value=""></option>    
                                                <c:forEach items="${pregunta.getTipoPregunta().getItemTipoPreguntaList()}" var="tipoPregunta2" varStatus="iter">
                                                    <option value="${tipoPregunta2.id}">${tipoPregunta2.respuesta}</option>    
                                                </c:forEach>
                                            </select> 
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>    
                    </c:when>
                    <c:otherwise>
                        <select name="respuestas">
                            <option value=""></option>    
                            <c:forEach items="${pregunta.getTipoPregunta().getItemTipoPreguntaList()}" var="tipoPregunta" varStatus="iter">
                                <option value="${tipoPregunta.id}">${tipoPregunta.respuesta}</option>    
                            </c:forEach>
                        </select> 
                    </c:otherwise>
                </c:choose>
            </c:when>
        </c:choose>
    </div>
</div>