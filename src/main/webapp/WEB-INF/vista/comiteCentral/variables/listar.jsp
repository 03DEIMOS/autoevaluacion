<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="hero-unit">
    <div class="row">
        <div id="conte" class="span10">
            <ul class="breadcrumb">
                <li>Parametros<span class="divider">/</span></li>
            </ul>
            <h3>Listado de Parametros del Software</h3>
            <c:choose>
                <c:when test="${fn:length(listaV)!= 0}">

                    <table class="table table-striped table-bordered table-condensed">
                        <thead>
                        <th>Llave</th>    
                        <th>Acci&oacute;n</th>
                        </thead>
                        <tbody>
                            <c:forEach items="${listaV}" var="variable" varStatus="iter">
                                <tr>
                                    <td>   
                                        <c:out value="${variable.llave}"/>
                                    </td>
                                    <td class="action span2">
                                        <a href="#parametro/editar/${variable.id}" title="Editar"><i class="icon-edit"></i></a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    No Existen Parametros Registrados en el Sistema.
                </c:otherwise>
            </c:choose>
            <a href="#parametro/crear" class="btn btn-large btn-primary llamador"><i class="icon-plus"></i> Crear parametro</a>
        </div>
    </div>
</div>    

