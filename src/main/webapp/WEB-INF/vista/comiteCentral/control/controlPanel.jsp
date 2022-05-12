
<script type="text/javascript">
    $(function () {
        var proceso, estado, title, description, isOK;

        $('.cambiarEstadoProceso').click(function (e) {
            e.preventDefault();
            e.stopPropagation();
            proceso = $(this).attr('procesoid');
            estado = $(this).attr('estado');
            title = $(this).attr('modalTitle');
            description = $(this).attr('modalDescription');
            $('#title').html(title);
            $('#question').html(description);
            $('#modalCc1').modal();
            isOK = false;
        });

        $('#modalCc1').off("hidden.bs.modal").on("hidden.bs.modal", function (e) {
            e.preventDefault();
            if (isOK) {
                $.ajax({
                    type: 'POST',
                    url: "/autoevaluacion/proceso/cambiarEstado",
                    data: {procesoId: proceso, estado: estado},
                    success: function () {
                        $("#dancing-dots-text").remove();
                        $.ajax({
                            type: "GET",
                            url: "/autoevaluacion/proceso/procesos",
                            success: function (data)
                            {
                                $("#contenido").html(data);
                                $("#contenido").show(200, function () {
                                    $("#dancing-dots-text").remove();
                                });
                            } //fin success
                        }); //fin del $.ajax
                    }
                });
            }
        });
        $('#modalCc1').on('click', '#modalCc1b1', function (e) {
            isOK = true;
        });
    });</script>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="hero-unit">
    <div class="row">
        <div id="conte" class="span10">
            <h3>Procesos de autoevaluación activos</h3>
            <c:choose>
                <c:when test="${fn:length(listProcesos)!= 0}">
                    <table id="tablaX" class="table table-striped table-bordered table-condensed">
                        <thead>
                        <th>Facultad</th>    
                        <th>Programa</th>
                        <th>Fecha de Inicio</th>
                        <th>Estado</th>
                        <th>Operaciones</th>
                        </thead>
                        <tbody>
                            <c:forEach items="${listProcesos}" var="row" varStatus="iter">
                                <c:if test="${row.fechaInicio == 'En Configuración'}">
                                    <tr  class="warning">
                                        <td>   
                                            <c:out value="${row.programaId.facultadId.nombre}"/>
                                        </td>
                                        <td>   
                                            <a href="#proceso/entrar/${row.id}">${row.programaId.nombre}</a>
                                        </td>
                                        <td>   
                                            <c:out value="${row.fechaInicio}"/>
                                        </td>
                                        <td>
                                            <span class="label label-warning"><i class="icon-cog"></i> En Configuración</span>
                                        </td>
                                        <td>
                                            <i class="icon-chevron-sign-right"></i>
                                            <a  class="cambiarEstadoProceso" 
                                                modalTitle="Ejecutar Proceso de Autoevaluación." 
                                                modalDescription="¿Está seguro que desea cambiar al estado en Ejecución el Proceso?."
                                                procesoid="${row.id}" 
                                                estado="En Ejecución"> Ejecutar Proceso</a>
                                        </td>
                                    </tr>
                                </c:if>
                                <c:if test="${row.fechaInicio != 'En Configuración' && row.fechaCierre=='--'}">
                                    <tr  class="info">
                                        <td>   
                                            <c:out value="${row.programaId.facultadId.nombre}"/>
                                        </td>
                                        <td>   
                                            <a href="#proceso/entrar/${row.id}">${row.programaId.nombre}</a>
                                        </td>
                                        <td>   
                                            <c:out value="${row.fechaInicio}"/>
                                        </td>
                                        <td>   
                                            <span class="label label-info"><i class="icon-play-sign"></i> En Ejecución</span>
                                        </td>
                                        <td>
                                            <i class="icon-flag-checkered"></i>
                                            <a class="cambiarEstadoProceso"  title="Finalizar proceso"  
                                               modalTitle="Finalizar Proceso de Autoevaluación." 
                                               modalDescription="¿Está seguro que desea finalizar el Proceso?."
                                               procesoid="${row.id}" 
                                               estado="Finalizado"> Finalizar Proceso</a>

                                            <i class="icon-undo"></i>
                                            <a class="cambiarEstadoProceso"  title="Devolver a estado anterior"  
                                               modalTitle="Devolver Proceso a estado anterior." 
                                               modalDescription="¿Está seguro que desea devolver el estado Proceso?."
                                               procesoid="${row.id}" 
                                               estado="En Configuración"> Devolver a estado anterior</a>   
                                        </td>
                                    </tr>
                                </c:if>
                                <c:if test="${row.fechaInicio != 'En Configuración' && row.fechaCierre!='--'}">
                                    <tr  class="success">
                                        <td>   
                                            <c:out value="${row.programaId.facultadId.nombre}"/>
                                        </td>
                                        <td>   
                                            <a href="#proceso/entrar/${row.id}">${row.programaId.nombre}</a>
                                        </td>
                                        <td>   
                                            <c:out value="${row.fechaInicio}"/>
                                        </td>
                                        <td>   
                                            <span class="label label-success"><i class="icon-flag-checkered"></i> Proceso finalizado</span>
                                        </td>
                                        <td>    <i class="icon-undo"></i>
                                            <a class="cambiarEstadoProceso"  title="Devolver a estado anterior"  
                                               modalTitle="Devolver Proceso a estado anterior." 
                                               modalDescription="¿Está seguro que desea devolver el estado Proceso?."
                                               procesoid="${row.id}" 
                                               estado="En Ejecución"> Devolver a estado anterior</a>   
                                        </td>
                                    </tr>
                                </c:if>    
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
                                        title: 'Procesos'
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
                    No  se han encontrado procesos activos.
                </c:otherwise>
            </c:choose>
            <br>
            <a href="#proceso/crear" class="btn btn-large btn-primary llamador"><i class="icon-plus"></i> Crear nuevo proceso</a>
        </div>
    </div>
</div>

