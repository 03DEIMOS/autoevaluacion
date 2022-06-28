<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="hero-unit">
    <div class="row">
        <div id="conte" class="span10">

            <h3 style="margin: 0;">Listado de  Caracteristicas</h3>
            <c:choose>
                <c:when test="${fn:length(listaC)!= 0}">

                    <table id="tablaX" class="table table-striped table-bordered table-condensed">
                        <thead>
                        <th class="span3">Factor</th>    
                        <th class="span6">Caracteristica</th>
                        <th class="span1">Acci&oacute;n</th>
                        </thead>
                        <tbody>
                            <c:forEach items="${listaC}" var="caracteristica" varStatus="iter">
                                <tr>
                                    <td>   
                                        <c:out value="${caracteristica.factorId.codigo} - ${caracteristica.factorId.nombre}"/>
                                    </td>
                                    <td>   
                                        <c:out value="${caracteristica.codigo} - ${caracteristica.nombre}"/>
                                    </td>
                                    <td class="action span2">
                                        <a href="#caracteristica/editar/${caracteristica.id}/${modeloId}" title="Editar"><i class="icon-edit"></i></a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <script type="text/javascript">
                        $(document).ready(function() {
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
                                        title: 'Caracteristicas'
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
                    No existen caracteristicas registradas en el sistema para este modelo.
                </c:otherwise>
            </c:choose>
            <br/>
            <a href="#caracteristica/crear/${modeloId}" class="btn btn-large btn-primary llamador"><i class="icon-plus"></i> Crear caracteristica</a>

        </div>
    </div>
</div>    

