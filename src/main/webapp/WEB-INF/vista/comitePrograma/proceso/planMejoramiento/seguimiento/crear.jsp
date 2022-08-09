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
        
        $('#fechaRealizado').datepicker({
            format: 'dd/mm/yyyy'
        });
        
        $("#formCrearSeguimiento").validate({
            submitHandler: function () {
                $.ajax({
                    type: 'POST',
                    url: "/autoevaluacion/seguimiento/crear",
                    data: $("#formCrearSeguimiento").serialize(),
                    success: function () {
                        location.hash = "seguimiento/seguimientos/${idHallazgo}";
                    } //fin success
                }); //fin $.ajax    */
            }
        });
    });
</script>
<div class="hero-unit">
    <div style="margin-left: -30px;">
        <div id="conte" class="span10" style="text-align: justify">
            <form id="formCrearSeguimiento" class="form-horizontal" method="post">
                <fieldset>
                    <legend>Crear Actividades de Seguimiento</legend>
                    <input name="idHallazgo" type="hidden" value="${idHallazgo}"/>
                    <div class="control-group">
                        <label for="fechaRealizado" class="control-label">Fecha Seguimiento Realizada</label>
                        <div class="controls">
                            <input type="text" name="fechaRealizado" id="fechaRealizado" class="input-xlarge {required:false}"/>
                        </div>
                    </div>

                    <div class="control-group">
                        <label for="avances" class="control-label">Avances*</label>
                        <div class="controls">
                            <textarea rows="3" name="avances" id="avances" class="input-xlarge {required:false}"></textarea>             
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="estado" class="control-label">Estado</label>
                        <div class="controls">
                            <input type="text" name="estado" id="estado" class="input-xlarge {required:false}"/> 
                        </div>
                    </div>
                    <div class="form-actions">
                        <button class="btn btn-primary" type="submit">Registrar Seguimiento</button>
                        <button class="btn" type="reset">Cancelar</button>
                    </div>
                </fieldset>

            </form>
        </div>
    </div>
</div>
