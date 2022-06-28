<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<script type="text/javascript">
    $(function () {
        $("#formClonarOportunidadesMejora").validate({
            submitHandler: function () {
                $.ajax({
                    type: 'POST',
                    url: "/autoevaluacion/oportunidadMejora/clonar",
                    data: $("#formClonarOportunidadesMejora").serialize(),
                    success: function () {
                        location.hash = "oportunidadMejora/oportunidadesMejora/${planMejoramientoId}";
                    } //fin success
                }); //fin $.ajax    
            }
        });
    });
</script>
<div class="hero-unit">
    <div class="row">
        <div id="conte" class="span10">
            <form id="formClonarOportunidadesMejora" class="form-horizontal">
                <input type="hidden" name="planMejoramientoDestino"  value="${planMejoramientoId}"/>
                <fieldset>
                    <legend>Clonar Oportunidades de Mejoramiento</legend>
                    <div class="control-group">
                        <label for="planMejoramientoOrigen"  class="control-label">Planes de Mejoramiento</label>
                        <div class="controls">
                            <select id="planMejoramientoOrigen" name="planMejoramientoOrigen" class="input-xlarge">
                                <c:forEach items="${planesMejoramiento}" var="planMejoramiento" >
                                    <c:choose>
                                        <c:when test="${planMejoramiento.id != planMejoramientoId}">
                                            <option value="${planMejoramiento.id}">${planMejoramiento.plan}</option>
                                        </c:when>
                                    </c:choose>
                                </c:forEach>
                            </select>   
                        </div>
                    </div>
                    <div class="form-actions">
                        <button class="btn btn-primary" type="submit">Clonar</button>
                        <button class="btn" type="reset">Cancelar</button>
                    </div>
                </fieldset>
            </form>
        </div><!--/span-->        
    </div><!--/row-->    
</div>