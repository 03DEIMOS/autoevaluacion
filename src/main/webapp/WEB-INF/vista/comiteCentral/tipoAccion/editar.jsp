<script type="text/javascript">
    $(function() {
        $("#formEditarTipoAccion").validate({
            submitHandler: function() {
                $.ajax({
                    type: 'PUT',
                    url: "/autoevaluacion/tipoAccion/editar",
                    data: $("#formEditarTipoAccion").serialize(),
                    success: function() {
                        location.hash = "tipoAccion/listar";
                    } //fin success
                }); //fin $.ajax    
            }
        });
    });
</script>
<div class="hero-unit">
    <div class="row">
        <div id="conte" class="span10">
            <form id="formEditarTipoAccion" class="form-horizontal" method="post">
                <fieldset>
                    <legend>Editar Tipo de Acción</legend>
                    <input type="hidden" name="tipoAccionId" value="${tipoAccion.id}"/>
                    <div class="control-group">
                        <label for="tipo"  class="control-label">Tipo</label>
                        <div class="controls">
                            <input type="text" name="tipo" id="tipo" class="input-xlarge {required:true}" value="${tipoAccion.tipo}"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="descripcion" class="control-label">Descripción</label>
                        <div class="controls">
                            <textarea rows="10" name="descripcion" id="descripcion" class="input-xxlarge {required:true}">${tipoAccion.descripcion}</textarea>
                        </div>
                    </div>

                    <div class="form-actions">
                        <button class="btn btn-primary" type="submit">Guardar cambios</button>
                        <button class="btn" type="reset">Cancelar</button>
                    </div>
                </fieldset>
            </form>
        </div><!--/span-->        
    </div><!--/row-->    
</div>