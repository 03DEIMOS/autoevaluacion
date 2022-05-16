<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" language="JavaScript">
    $(document).ready(function() {
        $('.tool').tooltip().click(function(e) {
            $(this).tooltip('hide');
        });
        $("#formCrearOportunidadMejora").validate({
            submitHandler: function() {
                $.ajax({
                    type: 'POST',
                    url: "/autoevaluacion/controladorCP?action=crearHallazgo2",
                    data: $("#formCrearHallazgo").serialize(),
                    success: function() {
                        location = "/autoevaluacion/#listarHallazgos";
                    } //fin success
                }); //fin $.ajax    */
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
                    <div class="control-group">
                        <label for="factorId" class="control-label">Asignar Factor</label>
                        <div class="controls">
                            <select id="factorId" name="factorId" class="{required:true}">
                                <option></option>
                                <c:forEach items="${listaF}" var="factor" varStatus="iter">
                                    <option value="${factor.id}">${factor.codigo} ${factor.nombre}</option>
                                </c:forEach>
                            </select>                
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="caracteristicaId" class="control-label">Asignar Caracter&iacute;stica</label>
                        <div class="controls">
                            <select id="caracteristicaId" name="caracteristicaId" class="{required:true}">
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
                        <label for="eje" class="control-label">Eje Estrat&eacute;gico</label>
                        <div class="controls">
                            <input type="text" name="eje" id="eje" class="input-xlarge {required:true}"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="oportunidadMejoramiento" class="control-label">Oportunidad Mejoramiento</label>
                        <div class="controls">
                            <textarea rows="3" name="oportunidadMejoramiento" id="oportunidadMejoramiento" class="input-xlarge {required:true}"></textarea>
                        </div>
                    </div>
                    
                    <div class="control-group">
                        <label for="lineaAccion" class="control-label">L&iacute;neas de acci&oacute;n</label>
                        <div class="controls">
                            <textarea rows="3" name="lineaAccion" id="lineaAccion" class="input-xlarge {required:true}"></textarea>
                        </div>
                    </div>

                    <div class="control-group">
                        <label for="estado" class="control-label">Estado</label>
                        <div class="controls">
                            <select id="estado" name="estado" class="{required:true}">
                                <option>Abierta</option>
                                <option>Permanente</option>
                                <option>Cerrada</option>
                            </select>                
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="responsable" class="control-label">Responsable</label>
                        <div class="controls">
                            <input type="text" name="responsable" id="responsable" class="input-xlarge {required:true}"/>
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
