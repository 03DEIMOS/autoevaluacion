<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="<%=request.getContextPath()%>/js/typeahead.bundle.js"></script>
<script type="text/javascript">
    $(function () {
        $("#btnCancelar").click(function () {
            $("#listM").empty();
            $.ajax({
                type: 'GET',
                url: "/autoevaluacion/persona/poblacion",
                data: $("#formListarMuestra").serialize(),
                success: function (datos) {
                    $("#listM").append(datos);
                    setTimeout(function () {
                        $("#dancing-dots-text").remove();
                    }, 200);
                } //fin success
            }); //fin $.ajax  
        });

        $("#formCrearEvaluador").validate({
            submitHandler: function () {
                $("#btnCrearEvaluador").attr("disabled", true);
                $.ajax({
                    type: 'POST',
                    url: "/autoevaluacion/persona/crearEvaluador",
                    data: $("#formCrearEvaluador").serialize(),
                    success: function () {
                        $("#listM").empty();
                        $.ajax({
                            type: 'GET',
                            url: "/autoevaluacion/persona/poblacion",
                            data: $("#formListarMuestra").serialize(),
                            success: function (datos) {
                                $("#listM").append(datos);
                                setTimeout(function () {
                                    $("#dancing-dots-text").remove();
                                }, 200);
                            } //fin success
                        }); //fin $.ajax  
                    } //fin success
                }); //fin $.ajax    
            }
        });
    }
    );
</script>
<div class="hero-unit divEvaluador">
    <div class="row">
        <div id="conte" class="span10">
            <form id="formCrearEvaluador" class="form-horizontal" method="post">
                <fieldset>
                    <legend>Crear Evaluador</legend>
                    <input type="hidden" name="fuenteId" value="${fuenteId}"/>
                    <input type="hidden" name="procesoId" value="${procesoId}"/>
                    <div class="control-group">
                        <label for="codigo" class="control-label">Código</label>
                        <div class="controls">
                            <input type="text" name="codigo" id="codigo" class="input-xlarge {required:true}" value=""/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="identificacion" class="control-label">Identificación</label>
                        <div class="controls">
                            <input type="text" name="identificacion" id="identificacion" class="input-xlarge {required:true}" value=""/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="nombre"  class="control-label">Nombres</label>
                        <div class="controls">
                            <input type="text" name="nombre" id="nombre" class="input-xlarge {required:true}" value=""/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="apellido"  class="control-label">Apellidos</label>
                        <div class="controls">
                            <input type="text" name="apellido" id="apellido" class="input-xlarge {required:true}" value=""/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="correo"  class="control-label">Correo electrónico</label>
                        <div class="controls">
                            <input type="text" name="correo" id="correo" class="input-xlarge {required:true}" value=""/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="variable1"  class="control-label">Variable 1</label>
                        <div class="controls">
                            <input type="text" name="variable1" id="variable1" class="input-xlarge {required:true}" value=""/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="variable2"  class="control-label">Variable 2</label>
                        <div class="controls">
                            <input type="text" name="variable2" id="variable2" class="input-xlarge {required:true}" value=""/>
                        </div>
                    </div>
                    <div class="form-actions">
                        <button class="btn btn-primary" type="submit" id="btnCrearEvaluador">Crear Evaluador</button>
                        <button class="btn" id="btnCancelar" type="button">Cancelar</button>
                    </div>
                </fieldset>
            </form>
        </div><!--/span-->        
    </div><!--/row-->    
</div>