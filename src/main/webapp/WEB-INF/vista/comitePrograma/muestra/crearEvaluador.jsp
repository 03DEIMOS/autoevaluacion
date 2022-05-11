<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="<%=request.getContextPath()%>/js/typeahead.bundle.js"></script>
<script type="text/javascript">
    $(function () {

        $('#cedula').blur(function () {
            var cedula = $('#cedula').val();
            $.ajax({
                type: 'POST',
                url: "/autoevaluacion/controladorCP?action=buscarPersona",
                data: 'cedula=' + cedula,
                success: function (persona) {
                    $("#dancing-dots-text").remove();
                    if (persona !== "1") {
                        $("#nombre").val(persona.nombre);
                        $("#apellido").val(persona.apellido);
                        $("#password").val(persona.pass);
                        $("#mail").val(persona.correo);
                        $("#nombre").attr("disabled", true);
                        $("#apellido").attr("disabled", true);
                        $("#password").attr("disabled", true);
                        $("#mail").attr("disabled", true);
                    } else {
                        $("#nombre").attr("disabled", false);
                        $("#apellido").attr("disabled", false);
                        $("#password").attr("disabled", false);
                        $("#mail").attr("disabled", false);
                    }

                } //fin success
            }); //fin $.ajax   
        });


        $("#formCrearEvaluador").validate({
            submitHandler: function () {
                $("#btnCrearEvaluador").attr("disabled", true);
                $.ajax({
                    type: 'POST',
                    url: "/autoevaluacion/controladorCP?action=crearEvaluador",
                    data: $("#formCrearEvaluador").serialize(),
                    success: function () {
                        $("#listM").empty();
                        $.ajax({
                            type: 'POST',
                            url: "/autoevaluacion/controladorCP?action=selectorListMuestra",
                            data: $("#formListarMuestra").serialize(),
                            success: function (datos) {
                                $(".divEvaluador").remove();
                                $("#listM").append(datos);
                                $("#contenido").show(200, function () {
                                    $("#dancing-dots-text").remove();
                                });
                            } //fin success
                        }); //fin $.ajax 
                    } //fin success
                }); //fin $.ajax    
            }
        });
    });
</script>
<div class="hero-unit divEvaluador">
    <div class="row">
        <div id="conte" class="span10">
            <form id="formCrearEvaluador" class="form-horizontal" method="post">
                <fieldset>
                    <legend>Crear Evaluador</legend>
                    <div class="control-group">
                        <label for="cedula" class="control-label">Cédula</label>
                        <div class="controls">
                            <input type="text" name="cedula" id="cedula" class="input-xlarge {required:true}" value=""/>
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
                        <label for="password"  class="control-label">Contraseña</label>
                        <div class="controls">
                            <input type="text" name="password" id="password" class="input-xlarge {required:true}" value=""/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="mail"  class="control-label">Correo electrónico</label>
                        <div class="controls">
                            <input type="text" name="mail" id="mail" class="input-xlarge {required:true}" value=""/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="empresa"  class="control-label">Empresa</label>
                        <div class="controls">
                            <input type="text" name="empresa" id="empresa" class="input-xlarge {required:true}" value=""/>
                        </div>
                    </div>
                    <div class="form-actions">
                        <button class="btn btn-primary" type="submit" id="btnCrearEvaluador">Crear Evaluador</button>
                        <button class="btn" type="reset">Cancelar</button>
                    </div>
                </fieldset>
            </form>
        </div><!--/span-->        
    </div><!--/row-->    
</div>