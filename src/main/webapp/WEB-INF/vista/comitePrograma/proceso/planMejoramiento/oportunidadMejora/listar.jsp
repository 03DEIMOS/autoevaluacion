<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="hero-unit">
    <div class = "row">
        <div id="conte" class="span10">
            
            <!--<div class="row">
                <ul class="nav nav-tabs" role="tablist">
                    <li class="active"><a href="#home" role="tab" data-toggle="tab">Plan de mejoramiento</a></li>
                    <li><a href="#profile" role="tab" data-toggle="tab">Plan de mantenimiento</a></li>
                </ul>
            -->
            
                <h3 style="margin: 0;">Listado de  Oportunidades de Mejoramiento</h3>
                    <c:choose>
                        <c:when test="${fn:length(listaO)!= 0}">
                            <table id="tablaX" class="table table-striped table-bordered table-condensed">
                                <thead>
                                    <th>Hallazgo</th>
                                    <th>L&iacute;nea de Acci&oacute;n</th>    
                                </thead>
                                <tbody>
                                    <c:forEach items="${listaO}" var="oportunidadMejora" varStatus="iter">
                                            <tr>
                                                <td>   
                                                    <c:out value="${oportunidadMejora.hallazgo}"/>
                                                </td>
                                                <td>   
                                                    <c:out value="${oportunidadMejora.linea_accion}"/>
                                                </td>
                                                <td>   
                                                    <a href="#editarHallazgo&${oportunidadMejora.idHallazgo}" title="Editar"><i class="icon-edit"></i></a>
                                                    <a href="#listarObjetivos&${oportunidadMejora.idHallazgo}" title="Ver objetivos"><i class="icon-signin"></i></a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </c:when>
                            <c:otherwise>
                                Aun no existen Oportunidades de Mejora para este plan de estrat&eacute;gico.<br/>
                            </c:otherwise>
                        </c:choose>
                        <br/>
                        <a href="#/${procesoId}" class="btn btn-large btn-primary llamador"><i class="icon-plus"></i> Crear Oportunidad de Mejora</a>
                    </div>
   
                </div>
            </div>


