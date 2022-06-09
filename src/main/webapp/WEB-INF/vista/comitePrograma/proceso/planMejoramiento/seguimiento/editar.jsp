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
        
        $('#fechaProgramada').datepicker({
            format: 'dd/mm/yyyy'
        });
        $('#fechaRealizado').datepicker({
            format: 'dd/mm/yyyy'
        });
        $("#formEditarSeguimiento").validate({
            submitHandler: function () {
                $.ajax({
                    type: 'PUT',
                    url: "/autoevaluacion/seguimiento/editar",
                    data: $("#formEditarSeguimiento").serialize(),
                    success: function () {
                        location.hash = "seguimiento/seguimientos/${seguimiento.oportunidadMejora.idHallazgo}";
                    } //fin success
                }); //fin $.ajax    */
            }
        });
    });
</script>
<div class="hero-unit">
    <div style="margin-left: -30px;">
         <div id="conte" class="span10" style="text-align: justify">
            <form id="formEditarSeguimiento" class="form-horizontal">
                 <input type="hidden" name="idSeguimiento"  value="${seguimiento.idSeguimiento}"/>
                <fieldset>
                    <legend>Editar Actividades de Seguimiento</legend>
                    
                    <div class="control-group">
                        <label for="fechaProgramada" class="control-label">Fecha Programada: </label>
                        <div class="controls">
                            <input type="text" name="fechaProgramada" id="fechaProgramada" class="input-xlarge {required:true}" value="${seguimiento.fechaProgramada}"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="fechaRealizado" class="control-label">Fecha Realizado: </label>
                        <div class="controls">
                            <input type="text" name="fechaRealizado" id="fechaRealizado" class="input-xlarge {required:false}" value="${seguimiento.fechaRealizado}"/>
                        </div>
                    </div>

                    <div class="control-group">
                        <label for="porcentajeAvance" class="control-label">Porcentaje Avances: </label>
                        <div class="controls">
                            <input type="number" name="porcentajeAvance" id="porcentajeAvance" class="input-xlarge {required:true}" value="${seguimiento.porcentajeAvance}"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="avances" class="control-label">Avances: </label>
                        <div class="controls">
                            <textarea rows="3" name="avances" id="avances" class="input-xlarge {required:false}">${seguimiento.avances}</textarea>
                        </div>
                    </div>
                    
                    <div class="form-actions span8">
                        <button class="btn btn-primary" type="submit">Editar Actividad de Seguimiento</button>
                        <button class="btn" type="reset">Cancelar</button>
                    </div>
                </fieldset>
            </form>
        </div>
    </div>
</div>