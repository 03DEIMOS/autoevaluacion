<script type="text/javascript">
    $(function() {
        $("#formEditarParametro").validate({
            submitHandler: function() {
                $.ajax({
                    type: 'PUT',
                    url: "/autoevaluacion/parametro/editar",
                    data: $("#formEditarParametro").serialize(),
                    success: function() {
                        location.hash = "parametro/parametros";
                    } //fin success
                }); //fin $.ajax    
            }
        });
    });
</script>
<div class="hero-unit">
    <div class="row">
        <div id="conte" class="span10">
            <form id="formEditarParametro" class="form-horizontal" method="post">
                <fieldset>
                    <legend>Editar Parametro</legend>
                    <input type="hidden" name="variableId" value="${variable.id}"/>
                    <div class="control-group">
                        <label for="llave"  class="control-label">Llave</label>
                        <div class="controls">
                            <input type="text" name="llave" id="llave" class="input-xlarge {required:true}" value="${variable.llave}"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="valor" class="control-label">Valor</label>
                        <div class="controls">
                            <textarea rows="10" name="valor" id="valor" class="input-xxlarge {required:true}">${variable.valor}</textarea>
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