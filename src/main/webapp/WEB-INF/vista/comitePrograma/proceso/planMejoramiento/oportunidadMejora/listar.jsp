<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script type="text/javascript">
    $(function () {
        var oportunidadMejoramientoId, title, description, isOK;

        $('.eliminarOportunidadMejora').click(function (e) {
            e.preventDefault();
            e.stopPropagation();
            oportunidadMejoramientoId = $(this).attr('oportunidadMejoramientoId');
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
                    type: 'DELETE',
                    url: "/autoevaluacion/oportunidadMejora/eliminar",
                    data: {oportunidadMejoramientoId: oportunidadMejoramientoId},
                    success: function () {
                        $("#dancing-dots-text").remove();
                        $.ajax({
                            type: "GET",
                            url: "/autoevaluacion/oportunidadMejora/oportunidadesMejora/${planMejoramientoId}",
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
    });
</script>


<div class="hero-unit">
    <div class = "row">
        <div id="conte" class="span10">

            <!--<div class="row">
                <ul class="nav nav-tabs" role="tablist">
                    <li class="active"><a href="#home" role="tab" data-toggle="tab">Plan de mejoramiento</a></li>
                    <li><a href="#profile" role="tab" data-toggle="tab">Plan de mantenimiento</a></li>
                </ul>
            -->

     <!--        <ul class="breadcrumb">
                <li></li>
                <a id="printEnlace" target="_blank" href="#" style="float: right; cursor: pointer;"><i class="icon-eye-open"></i> Ver Plan de Mejoramiento</a>
            </ul>
     -->

            <h3 style="margin: 0;">Listado de  Oportunidades de Mejoramiento</h3>
            <c:choose>
                <c:when test="${fn:length(listaO)!= 0}">
                    <table id="tablaX" class="table table-striped table-bordered table-condensed">
                        <thead>
                        <th>Factor</th>
                        <th>Caracteristica</th>
                        <th>Oportunidad de mejoramiento</th>
                        <th>Eje estrategico del PDI</th>
                        <th>L&iacute;neas de Acci&oacute;n del PDI</th>    
                        <th>Estado</th>    
                        <th>Tipo</th>    
                        <th>Reponsable</th>    
                        <th>Fecha inicio</th>    
                        <th>Fecha fin</th>    
                        </thead>
                        <tbody>
                            <c:forEach items="${listaO}" var="oportunidadMejora" varStatus="iter">
                                <tr>
                                    <td>   
                                        <c:out value="${oportunidadMejora.caracteristicaId.factorId.nombre}"/>
                                    </td>
                                    <td>   
                                        <c:out value="${oportunidadMejora.caracteristicaId.nombre}"/>
                                    </td>
                                    <td>   
                                        <c:out value="${oportunidadMejora.hallazgo}"/>
                                    </td>
                                    <td>   
                                        <c:out value="${oportunidadMejora.eje}"/>
                                    </td>
                                    <td>   
                                        <c:out value="${oportunidadMejora.lineaAccion}"/>
                                    </td>
                                    <td>   
                                        <c:out value="${oportunidadMejora.estado}"/>
                                    </td>
                                     <td>   
                                        <c:out value="${oportunidadMejora.tipo}"/>
                                    </td>
                                    <td>   
                                        <c:out value="${oportunidadMejora.responsable}"/>
                                    </td>
                                    <td>   
                                        <c:out value="${oportunidadMejora.fechaInicio}"/>
                                    </td>
                                    <td>   
                                        <c:out value="${oportunidadMejora.fechaFin}"/>
                                    </td>
                                    <td>   
                                        <a href="#oportunidadMejora/editar/${oportunidadMejora.idHallazgo}" title="Editar"><i class="icon-edit"></i></a>
                                        <a href="#oportunidadMejora/eliminar/${oportunidadMejora.idHallazgo}" title="Eliminar" class = "eliminarOportunidadMejora"
                                            modalTitle="Eliminar Oportunidad de Mejoramiento." 
                                            modalDescription="¿Está seguro que desea cambiar eliminar esta Oportunidad de Mejoramiento?."
                                            oportunidadMejoramientoId="${oportunidadMejora.idHallazgo}" 
                                            ><i class="icon-remove"></i></a>
                                        <a href="#seguimiento/seguimientos/${oportunidadMejora.idHallazgo}" title="Ver seguimientos"><i class="icon-signin"></i></a>
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
            <a href="#oportunidadMejora/crear/${planMejoramientoId}" class="btn btn-large btn-primary llamador"><i class="icon-plus"></i> Crear Oportunidad de Mejora</a>
            <a href="#oportunidadMejora/clonar/${planMejoramientoId}" class="btn btn-large btn"><i class="icon-group"></i> Clonar Oportunidades de Mejora</a>
            <a href="#oportunidadMejora/reporteCuantitativo/${planMejoramientoId}" class="btn btn-large btn"><i class="icon-group"></i> Reporte cuantitativo por tipos de acción</a>
        </div>

    </div>
</div>


