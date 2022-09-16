<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="hero-unit">
    <div class="row">
        <div id="conte" class="span10">
            <ul class="breadcrumb">
                <li>Tipos de acci�n<span class="divider">/</span></li>
            </ul>
            <h3>Listado de Tipos de Acci�n</h3>
            <c:choose>
                <c:when test="${fn:length(listaTipoAccion)!= 0}">

                    <table class="table table-striped table-bordered table-condensed">
                        <thead>
                        <th>Tipo Acci�n</th>    
                         <th>Descripci�n</th>    
                        <th>Acci&oacute;n</th>
                        </thead>
                        <tbody>
                            <c:forEach items="${listaTipoAccion}" var="item" varStatus="iter">
                                <tr>
                                    <td>   
                                        <c:out value="${item.tipo}"/>
                                    </td>
                                     <td>   
                                        <c:out value="${item.descripcion}"/>
                                    </td>
                                    <td class="action span2">
                                        <a href="#tipoAccion/editar/${item.id}" title="Editar"><i class="icon-edit"></i></a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    No Existen Tipos de Acci�n Registrados en el Sistema.
                </c:otherwise>
            </c:choose>
            <a href="#tipoAccion/crear" class="btn btn-large btn-primary llamador"><i class="icon-plus"></i> Crear tipo de acci�n</a>
        </div>
    </div>
</div>    

