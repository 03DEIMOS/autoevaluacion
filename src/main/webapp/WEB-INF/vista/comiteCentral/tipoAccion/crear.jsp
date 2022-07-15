<script type="text/javascript">
    $(function() {
        $("#formCrearTipoAccion").validate({
            submitHandler: function() {
                $.ajax({
                    type: 'POST',
                    url: "/autoevaluacion/tipoAccion/crear",
                    data: $("#formCrearTipoAccion").serialize(),
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
            <form id="formCrearTipoAccion" class="form-horizontal" method="post">
                <fieldset>
                    <legend>Crear Tipo de Acción</legend>
                    <div class="control-group">
                        <label for="tipo"  class="control-label">Tipo</label>
                        <div class="controls">
                            <input type="text" name="tipo" id="tipo" class="input-xlarge {required:true}" value=""/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="descripcion" class="control-label">Descripción</label>
                        <div class="controls">
                            <textarea rows="3" name="descripcion" id="descripcion" class="input-xxlarge {required:true}"></textarea>
                        </div>
                    </div>

                    <div class="form-actions">
                        <button class="btn btn-primary" type="submit">Crear Tipo de Acción</button>
                        <button class="btn" type="reset">Cancelar</button>
                    </div>
                </fieldset>
            </form>
        </div><!--/span-->        
    </div><!--/row-->    
</div>