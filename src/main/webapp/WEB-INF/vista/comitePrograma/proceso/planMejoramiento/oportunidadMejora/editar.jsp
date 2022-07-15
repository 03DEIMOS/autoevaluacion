<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/datepicker.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" language="JavaScript">
    $(document).ready(function () {
        $('.tool').tooltip().click(function (e) {
            $(this).tooltip('hide');
        });

        $('#fechaInicio').datepicker({
            format: 'dd/mm/yyyy'
        });
        $('#fechaFinal').datepicker({
            format: 'dd/mm/yyyy'
        });
        $("#formEditarOportunidadMejora").validate({
            submitHandler: function () {
                $.ajax({
                    type: 'PUT',
                    url: "/autoevaluacion/oportunidadMejora/editar",
                    data: $("#formEditarOportunidadMejora").serialize(),
                    success: function () {
                        location.hash = "oportunidadMejora/oportunidadesMejora/${planMejoramientoId}";
                    } //fin success
                }); //fin $.ajax    */
            }
        });

        $("#factorId").change(function () {
            $("#caracteristicaId").empty();
            var a = $("#factorId option:selected").index();
            if (a !== 0) {
                $("#caracteristicaId").empty();
                $.ajax({
                    type: 'GET',
                    url: "/autoevaluacion/caracteristica/caracteristicasByFactor/" + $("#factorId option:selected").val(),
                    success: function (datos) {
                        $("#caracteristicaId").append(datos);
                        setTimeout(function () {
                            $("#dancing-dots-text").remove();
                        }, 200);
                    } //fin success
                }); //fin $.ajax    
            }
        });
    });
</script>
<div class="hero-unit">
    <div style="margin-left: -30px;">
        <div id="conte" class="span10" style="text-align: justify">
            <form id="formEditarOportunidadMejora" class="form-horizontal">
                <input type="hidden" name="hallazgoId" value="${oportunidadMejora.idHallazgo}">
                <input type="hidden" name="planMejoramientoId"  value="${planMejoramientoId}"/>
                <fieldset>
                    <legend>Editar Oportunidad de mejora</legend>
                    <div class="control-group">
                        <label for="factorId" class="control-label">Factor</label>
                        <div class="controls">
                            <select id="factorId" name="factorId" class="{required:true}">
                                <option></option>
                                <c:forEach items="${listaF}" var="factor" varStatus="iter">
                                    <c:choose>
                                        <c:when test="${factor != oportunidadMejora.getCaracteristicaId().getFactorId()}">
                                            <option value="${factor.id}">${factor.codigo} ${factor.nombre}</option>    
                                        </c:when>
                                        <c:otherwise>
                                            <option  selected="selected" value="${factor.id}">${factor.codigo} ${factor.nombre}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>                
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="caracteristicaId" class="control-label">Caracter&iacute;stica</label>
                        <div class="controls">
                            <select id="caracteristicaId" name="caracteristicaId" class="{required:true}">
                                <option></option>
                                <c:forEach items="${listaC}" var="caracteristica" varStatus="iter">
                                    <c:choose>
                                        <c:when test="${caracteristica != oportunidadMejora.getCaracteristicaId()}">
                                            <option value="${caracteristica.id}">${caracteristica.codigo} ${caracteristica.nombre}</option>    
                                        </c:when>
                                        <c:otherwise>
                                            <option  selected="selected" value="${caracteristica.id}">${caracteristica.codigo} ${caracteristica.nombre}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>                
                        </div>
                    </div>

                    <div class="control-group">
                        <label for="oportunidadMejoramiento" class="control-label">Oportunidad Mejoramiento</label>
                        <div class="controls">
                            <textarea rows="3" name="oportunidadMejoramiento" id="oportunidadMejoramiento" class="input-xlarge {required:true}">${oportunidadMejora.hallazgo}</textarea>
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="eje" class="control-label">Eje Estrat&eacute;gico del PDI</label>
                        <div class="controls">
                            <input type="text" name="eje" id="eje" class="input-xlarge {required:true}" value="${oportunidadMejora.eje}"/>
                        </div>
                    </div>

                    <div class="control-group">
                        <label for="lineaAccion" class="control-label">L&iacute;neas de acci&oacute;n del PDI</label>
                        <div class="controls">
                            <textarea rows="3" name="lineaAccion" id="lineaAccion" class="input-xlarge {required:true}">${oportunidadMejora.lineaAccion}</textarea>
                        </div>
                    </div>

                    <div class="control-group">
                        <label for="estadoId" class="control-label">Estado</label>
                        <div class="controls">
                            <select id="estadoId" name="estadoId" class="{required:true}">
                                <option></option>
                                <c:forEach items="${tiposAccion}" var="tipoAccion" varStatus="iter">
                                    <c:choose>
                                        <c:when test="${tipoAccion != oportunidadMejora.getEstadoId()}">
                                            <option value="${tipoAccion.id}">${tipoAccion.tipo}</option>    
                                        </c:when>
                                        <c:otherwise>
                                            <option selected="selected" value="${tipoAccion.id}">${tipoAccion.tipo}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>      
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="tipo" class="control-label">Tipo</label>
                        <div class="controls">
                            <select id="tipo" name="tipo" class="{required:true}">
                                <c:choose>
                                    <c:when test="${oportunidadMejora.tipo == 'Institucional'}">
                                        <option selected="selected">Institucional</option>
                                        <option>Programa</option>
                                    </c:when>
                                    <c:when test="${oportunidadMejora.tipo == 'Programa'}">
                                        <option>Institucional</option>
                                        <option selected="selected">Programa</option>
                                    </c:when>
                                </c:choose>
                            </select>                
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="responsable" class="control-label">Responsable</label>
                        <div class="controls">
                            <input type="text" name="responsable" id="responsable" class="input-xlarge {required:true}" value="${oportunidadMejora.responsable}"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="fechaInicio" class="control-label">Fecha de inicio</label>
                        <div class="controls">
                            <input type="text" name="fechaInicio" id="fechaInicio" class="form-control" value="${oportunidadMejora.fechaInicio}" >
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="fechaFinal" class="control-label">Fecha de finalización </label>
                        <div class="controls">
                            <input type="text" name="fechaFinal" id="fechaFinal" class="form-control" value="${oportunidadMejora.fechaFin}" >
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="recurso" class="control-label">Recursos</label>
                        <div class="controls">
                            <input type="text" name="recurso" id="recurso" class="form-control" value="${oportunidadMejora.recurso}" >
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="indicador" class="control-label">Indicador</label>
                        <div class="controls">
                            <input type="text" name="indicador" id="indicador" class="form-control" value="${oportunidadMejora.indicador}" >
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="meta" class="control-label">Meta</label>
                        <div class="controls">
                            <input type="text" name="meta" id="meta" class="form-control" value="${oportunidadMejora.meta}" >
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="lineaBase" class="control-label">Línea de base del indicador</label>
                        <div class="controls">
                            <input type="text" name="lineaBase" id="lineaBase" class="form-control" value="${oportunidadMejora.lineaBase}" >
                        </div>
                    </div>
                    <div class="form-actions span8">
                        <button class="btn btn-primary" type="submit">Editar Oportunidad de mejora</button>
                        <button class="btn" type="reset">Cancelar</button>
                    </div>
                </fieldset>
            </form>
        </div>
    </div>
</div>