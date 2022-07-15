<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
    $(function () {
        $("#formCopiarInstitucionales").validate({
            submitHandler: function () {
                $.ajax({
                    type: 'POST',
                    url: "/autoevaluacion/oportunidadMejora/copiarInstitucionales",
                    data: $("#formCopiarInstitucionales").serialize(),
                    success: function () {
                        location.hash = "oportunidadMejora/oportunidadesMejora/${planMejoramientoId}";
                    } //fin success
                }); //fin $.ajax    
            }
        });

        $("#planMejoramientoOrigen").change(function () {
            $("#factorId").empty();
            var a = $("#planMejoramientoOrigen option:selected").index();
            if (a !== 0) {
                $("#factorId").empty();
                $.ajax({
                    type: 'GET',
                    url: "/autoevaluacion/factor/factoresByPlanMejora/" + $("#planMejoramientoOrigen option:selected").val(),
                    success: function (datos) {
                        $("#factorId").append(datos);
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
    <div class="row">
        <div id="conte" class="span10">
            <form id="formCopiarInstitucionales" class="form-horizontal">
                <input type="hidden" name="planMejoramientoDestino"  value="${planMejoramientoId}"/>
                <fieldset>
                    <legend>Copiar Oportunidades de Mejoramiento Institucionales</legend>
                    <div class="control-group">
                        <label for="planMejoramientoOrigen"  class="control-label">Planes de Mejoramiento Institucionales</label>
                        <div class="controls">
                            <select id="planMejoramientoOrigen" name="planMejoramientoOrigen" class="{required:true} input-xlarge">
                                <option></option>
                                <c:forEach items="${planesMejoramiento}" var="planMejoramientoX" >
                                    <option value="${planMejoramientoX.id}">${planMejoramientoX.plan}</option>
                                </c:forEach>
                            </select>   
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="factorId"  class="control-label">Factor</label>
                        <div class="controls">
                            <select id="factorId" name="factorId" class="{required:true} input-xlarge"></select>   
                        </div>
                    </div>
                    <div class="form-actions">
                        <button class="btn btn-primary" type="submit">Copiar Oportunidades de mejora institucionales</button>
                        <button class="btn" type="reset">Cancelar</button>
                    </div>
                </fieldset>
            </form>
        </div><!--/span-->        
    </div><!--/row-->    
</div>
