<%-- 
    Document   : informePreguntas
    Created on : 2/11/2021, 11:08:32 PM
    Author     : DEIMOS
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="hero-unit">
    <div class="row">
        <div id="conte" class="span10">

            <h3 style="margin: 0;">Informe  de  Preguntas por Proceso</h3>
            <c:choose>
                <c:when test="${fn:length(listaI)!= 0}">

                    <table id="tablaX" class="table table-striped table-bordered table-condensed">
                        <tbody>
                            <c:forEach items="${listaI}" var="pregunta" varStatus="status">

                            <div class="row" id="pregunta${pregunta.id}">
                                <div class="span10">
                                    <p style="font-weight: bold;">${status.index+1} ${pregunta.getPregunta()}</p>
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
                        </c:forEach>
                        </tbody>
                    </table>
                    <script type="text/javascript">
                        $(document).ready(function () {
                            $('#tablaX').DataTable({
                                dom: 'Bfrtip',
                                buttons: [
                                    {
                                        extend: 'copy',
                                        text: 'Copiar'
                                    },
                                    {
                                        extend: 'excelHtml5',
                                        text: 'Exportar a excel',
                                        title: 'Preguntas'
                                    }
                                ],
                                language: {
                                    buttons: {
                                        copyTitle: 'Copiar'
                                    }
                                }
                            });
                        });
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