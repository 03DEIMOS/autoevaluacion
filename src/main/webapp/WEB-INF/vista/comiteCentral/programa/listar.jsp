<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="hero-unit">
    <div class="row">
        <div id="conte" class="span10">
            <ul class="breadcrumb">
                <li>Programas<span class="divider">/</span></li>
            </ul>
            <h3 style="margin: 0;">Listado de  programas</h3>
            <c:choose>
                <c:when test="${fn:length(listaPro)!= 0}">
                    <table id="tablaX" class="table table-striped table-bordered table-condensed">
                        <thead>
                        <th>Nombre</th>
                        <th>Modalidad</th>
                        <th>Tipo de formación</th>
                        <th>Facultad</th>
                        <th class="span1">Acci&oacute;n</th>
                        </thead>
                        <tbody>
                            <c:forEach items="${listaPro}" var="row" varStatus="iter">
                                <tr>
                                    <td>   
                                        <c:out value="${row.nombre}"/>
                                    </td>
                                    <td>   
                                        <c:out value="${row.modalidad}"/>
                                    </td>
                                    <td>   
                                        <c:out value="${row.tipoFormacion}"/>
                                    </td>
                                    <td>   
                                        <c:out value="${row.facultadId.nombre}"/>
                                    </td>
                                    <td class="action span2">
                                        <a href="#programa/editar/${row.id}" title="Editar"><i class="icon-edit"></i></a>
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
                                        title: 'Programas'
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
                    No existen Programas registrados en el sistema.
                </c:otherwise>
            </c:choose>
            <br/>
            <a href="#programa/crear" class="btn btn-large btn-primary llamador"><i class="icon-plus"></i> Crear programa</a>
        </div>
    </div>
</div>  