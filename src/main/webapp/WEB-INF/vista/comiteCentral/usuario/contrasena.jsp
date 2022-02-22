<script type="text/javascript">
    $(function () {
        $.validator.addMethod("coincidencia", function (value, element) {
            return $('#nueva1').val() === $('#nueva2').val();
        }, "Las contraseñas ingresadas no coinciden");

        $("#formularioContrasena").validate({
            submitHandler: function () {
                $.ajax({
                    type: 'PUT',
                    url: "/autoevaluacion/usuario/contrasena",
                    data: $("#formularioContrasena").serialize(),
                    success: function () {
                        $("#dancing-dots-text").remove();
                        $("#modalClaveOK").modal();
                    },
                    error: function () {
                        $("#dancing-dots-text").remove();
                        $("#modalClaveERROR").modal();
                    }
                }); //fin $.ajax    
            }
        });
    });
</script>    
<div class="hero-unit">
    <div class="row">
        <div id="conte" class="span10">
            <form name="formularioContrasena"  id="formularioContrasena">
                <fieldset>
                    <legend>Cambiar Contrase&nacute;a</legend>
                    <input type="hidden" name="usuarioId" value="${usuario.id}">
                    <div class="control-group">
                        <label for="claveActual" class="control-label">Contrase&nacute;a actual</label>
                        <div class="controls">
                            <input type="password" placeholder="Contraseña actual" name="claveActual" id="claveActual" class="input-xlarge {required:true}">
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="nuevaClave1" class="control-label">Nueva contrase&nacute;a</label>
                        <div class="controls">
                            <input type="password" placeholder="Nueva contraseña" name="nuevaClave1" id="nuevaClave1" class="input-xlarge {required:true}" >
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="nuevaClave2" class="control-label">Nueva contrase&nacute;a</label>
                        <div class="controls">
                            <input type="password" placeholder="Nueva contraseña" name="nuevaClave2" id="nuevaClave2" class="input-xlarge {required:true, coincidencia:true }" >
                        </div>
                    </div>
                    <div class="form-actions">
                        <button class="btn btn-primary" type="submit">Cambiar contrase&nacute;a</button>
                    </div>

                </fieldset>
            </form>
        </div>
    </div> 
</div> 
