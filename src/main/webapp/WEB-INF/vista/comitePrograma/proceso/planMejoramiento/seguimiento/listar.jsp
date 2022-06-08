<%-- 
    Document   : listar
    Created on : 4/06/2022, 11:43:58 PM
    Author     : DEIMOS
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<div class="hero-unit">
    <div class = "row">
        <div id="conte" class="span10">
            
                <h3 style="margin: 0;">Listado de Seguimientos</h3>
                    <c:choose>
                        <c:when test="${fn:length(listaS)!= 0}">
                            <table id="tablaX" class="table table-striped table-bordered table-condensed">
                                <thead>
                                    <th>Fecha Programada</th>
                                    <th>Fecha Realizado</th>
                                    <th>Porcentaje Avances</th>
                                    <th>Avances</th>
                                </thead>
                                <tbody>
                                    <c:forEach items="${listaS}" var="seguimiento" varStatus="iter">
                                            <tr>
                                                <td>   
                                                    <c:out value="${seguimiento.fechaProgramada}"/>
                                                </td>
                                                <td>   
                                                    <c:out value="${seguimiento.fechaRealizado}"/>
                                                </td>
                                                <td>   
                                                    <c:out value="${seguimiento.porcentajeAvance}"/>
                                                </td>
                                                <td>   
                                                    <c:out value="${seguimiento.avances}"/>
                                                </td>
                                                <td>   
                                                    <a href="#seguimiento/editar/${seguimiento.idSeguimiento}/${idHallazgo}" title="Editar"><i class="icon-edit"></i></a>
                                                    <a href="#" title="Ver avances"><i class="icon-signin"></i></a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </c:when>
                            <c:otherwise>
                                Aún no existen avances para realizar seguimientos a la oportunidad de mejora.<br/>
                            </c:otherwise>
                        </c:choose>
                        <br/>
                        <a href="#seguimiento/crear/${idHallazgo}" class="btn btn-large btn-primary llamador"><i class="icon-plus"></i> Crear Avance</a>
                    </div>
   
                </div>
            </div>