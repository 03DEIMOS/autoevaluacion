<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="hero-unit">
    <div class="row">
        <div id="conte" class="span10">
            <h3 style="margin: 0;">Listado de  Usuarios</h3>
            <c:choose>
                <c:when test="${fn:length(usuarios)!= 0}">
                    <table id="tablaX" class="table table-striped table-bordered table-condensed">
                        <thead>
                        <th class="span1">C&oacute;digo</th>    
                        <th class="span1">Identificaci&oacute;n</th>    
                        <th class="span4">Nombre</th>
                        <th class="span4">Apellido</th>
                        <th class="span1">Acci&oacute;n</th>
                        </thead>
                        <tbody>
                            <c:forEach items="${usuarios}" var="row" varStatus="iter">
                                <tr>
                                    <td>   
                                        <c:out value="${row.usuario}"/>
                                    </td>
                                    <td>   
                                        <c:out value="${row.identificacion}"/>
                                    </td>
                                     <td>   
                                        <c:out value="${row.nombre}"/>
                                    </td>
                                     <td>   
                                        <c:out value="${row.apellido}"/>
                                    </td>
                                    <td class="action span2">
                                        <a href="#usuario/editar/${row.id}" title="Editar"><i class="icon-edit"></i></a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    No existen Usuario registrados en el sistema.
                </c:otherwise>
            </c:choose>
            <br/>
            <a href="#usuario/crear" class="btn btn-large btn-primary llamador"><i class="icon-plus"></i> Crear Usuario</a>
        </div>
    </div>
</div>    

