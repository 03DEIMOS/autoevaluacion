<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="hero-unit">
    <div class="row">
        <div id="conte" class="span10">
            <h3 style="margin: 0;">Listado de  P&uacute;blicos objetivo</h3>
            <c:choose>
                <c:when test="${fn:length(listaFuentes)!= 0}">
                    <table id="tablaX" class="table table-striped table-bordered table-condensed">
                        <thead>
                        <th>P&uacute;blico objetivo</th>
                        <th>Descripcion</th>
                        <th class="span2">Acci&oacute;n</th>
                        </thead>
                        <tbody>
                            <c:forEach items="${listaFuentes}" var="fuente" varStatus="iter">
                                <tr>
                                    <td>   
                                        <c:out value="${fuente.nombre}"/>
                                    </td>
                                    <td>   
                                        <c:out value="${fuente.descripcion}"/>
                                    </td>
                                    <td class="action span2">
                                        <a href="#fuente/editar/${fuente.id}" title="Editar"><i class="icon-edit"></i></a>
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
                                        title: 'Publicos'
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
                    No existen P&uacute;blicos objetivo registrados en el sistema.
                </c:otherwise>
            </c:choose>
            <br/>
            <a href="#fuente/crear" class="btn btn-large btn-primary llamador"><i class="icon-plus"></i> Crear P&uacute;blico objetivo</a>

        </div>
    </div>
</div>    
