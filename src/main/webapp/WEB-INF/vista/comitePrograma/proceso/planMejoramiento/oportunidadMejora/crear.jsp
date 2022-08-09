<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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

        $("#formCrearOportunidadMejora").validate({
            submitHandler: function () {
                $.ajax({
                    type: 'POST',
                    url: "/autoevaluacion/oportunidadMejora/crear",
                    data: $("#formCrearOportunidadMejora").serialize(),
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
            <form id="formCrearOportunidadMejora" class="form-horizontal" method="post">
                <fieldset>
                    <legend>Crear Oportunidad Mejoramiento</legend>
                    <input name="planMejoramientoId" type="hidden" value="${planMejoramientoId}"/>
                    <div class="control-group">
                        <label for="factorId" class="control-label">Factor</label>
                        <div class="controls">
                            <select id="factorId" name="factorId" class="{required:true} input-xxlarge">
                                <option></option>
                                <c:forEach items="${listaF}" var="factor" varStatus="iter">
                                    <option value="${factor.id}">${factor.codigo} ${factor.nombre}</option>
                                </c:forEach>
                            </select>                
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="caracteristicaId" class="control-label">Caracter&iacute;stica</label>
                        <div class="controls">
                            <select id="caracteristicaId" name="caracteristicaId" class="{required:true} input-xxlarge">
                                <option></option>
                                <c:forEach items="${listaC}" var="caracteristica" varStatus="iter">
                                    <option value="${caracteristica.id}">${caracteritica.codigo} ${caracteristica.nombre}</option>
                                </c:forEach>
                            </select>                
                        </div>
                    </div>

                    <div class="control-group">
                        <label for="oportunidadMejoramiento" class="control-label">Oportunidad Mejoramiento</label>
                        <div class="controls">
                            <textarea rows="3" name="oportunidadMejoramiento" id="oportunidadMejoramiento" class="input-xlarge {required:true}"></textarea>
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="eje" class="control-label">Eje Estrat&eacute;gico del PDI</label>
                        <div class="controls">
                            <input type="text" name="eje" id="eje" class="input-xlarge {required:true}"/>
                        </div>
                    </div>

                    <div class="control-group">
                        <label for="lineaAccion" class="control-label">L&iacute;neas de acci&oacute;n del PDI</label>
                        <div class="controls">
                            <textarea rows="3" name="lineaAccion" id="lineaAccion" class="input-xlarge {required:true}"></textarea>
                        </div>
                    </div>

                    <div class="control-group">
                        <label for="tipo" class="control-label">Tipo</label>
                        <div class="controls">
                            <select id="tipo" name="tipo" class="{required:true}">
                                <option>Institucional</option>
                                <option>Programa</option>
                            </select>                
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="responsable" class="control-label">Responsable</label>
                        <div class="controls">
                            <input type="text" name="responsable" id="responsable" class="input-xlarge {required:true}"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="fechaInicio" class="control-label">Fecha de inicio</label>
                        <div class="controls">
                            <input type="text" name="fechaInicio" id="fechaInicio" class="form-control" value="" >
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="fechaFinal" class="control-label">Fecha de finalización</label>
                        <div class="controls">
                            <input type="text" name="fechaFinal" id="fechaFinal" class="form-control" value="" >
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="recurso" class="control-label">Recursos</label>
                        <div class="controls">
                            <input type="text" name="recurso" id="recurso" class="form-control" value="" >
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="indicador" class="control-label">Indicador</label>
                        <div class="controls">
                            <input type="text" name="indicador" id="indicador" class="form-control" value="" >
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="meta" class="control-label">Meta</label>
                        <div class="controls">
                            <input type="text" name="meta" id="meta" class="form-control" value="" >
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="lineaBase" class="control-label">Línea de base del indicador</label>
                        <div class="controls">
                            <input type="text" name="lineaBase" id="lineaBase" class="form-control" value="" >
                        </div>
                    </div>
                    <div class="form-actions">
                        <button class="btn btn-primary" type="submit">Crear Oportunidad Mejoramiento</button>
                        <button class="btn" type="reset">Cancelar</button>
                    </div>
                </fieldset>

            </form>
        </div>
    </div>
</div>
