<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="hero-unit">
    <div class="row">
        <div id="conte" class="span10">
            <ul class="breadcrumb">
                <li>Planes de Mejoramiento<span class="divider">/</span></li>
            </ul>
            <h3>Listado de Planes de Mejoramiento</h3>
            <c:choose>
                <c:when test="${fn:length(listPlanes)!= 0}">
                    <table id="tablaX" class="table table-striped table-bordered table-condensed">
                        <thead>
                        <th>Descripción</th>
                        <th>Programa</th>    
                        <th>Estado</th>
                        <th></th>
                        </thead>
                        <tbody>
                            <c:forEach items="${listPlanes}" var="item" varStatus="iter">
                                <tr>
                                    <td>   
                                        <c:out value="${item.plan}"/>
                                    </td>
                                    <td>   
                                        <c:out value="${item.programaId.nombre}"/>
                                    </td>
                                    <td>   
                                        <c:out value="${item.estado}"/>
                                    </td>
                                    <td class="action span2">
                                        <a href="#planMejoramiento/editar/${item.id}/${usuarioId}" title="Editar"><i class="icon-edit"></i></a>
                                        <a href="#oportunidadMejora/oportunidadesMejora/${item.id}" title="Entrar"><i class="icon-signin"></i></a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    No existen planes de mejoramiento registrados en el sistema.
                </c:otherwise>
            </c:choose>
            <br/>
            <a href="#planMejoramiento/crear/${usuarioId}" class="btn btn-large btn-primary llamador"><i class="icon-plus"></i> Crear Plan de Mejoramiento</a>
        </div>
    </div>
</div>
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
                    title: 'Planes de mejoramiento'
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