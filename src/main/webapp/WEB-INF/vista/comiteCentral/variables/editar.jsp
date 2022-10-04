<link rel="stylesheet" href="css/summernote-lite.css">
<script src="js/summernote-lite.js"></script> 
<script type="text/javascript">
    $(function () {

        $('#valor').summernote({
            tabsize: 2,
            height: 120,
            toolbar: [
                ['style', ['style']],
                ['font', ['bold', 'underline', 'clear']],
                ['color', ['color']],
                ['para', ['ul', 'ol', 'paragraph']],
                ['table', ['table']],
                ['insert', ['link', 'picture']],
                ['view', ['fullscreen', 'codeview', 'help']]
            ]
        });
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
        <ul class="breadcrumb">
            <li><a href="#parametro/parametros" >Páginas Personalizadas</a><span class="divider">/</span></li>
            <li>Editar</li>
        </ul>
        <div id="conte" class="span10">
            <form id="formEditarParametro" class="form-horizontal" method="post">
                <fieldset>
                    <legend>Editar página personalizada</legend>
                    <input type="hidden" name="variableId" value="${variable.id}"/>
                    <div class="control-group">
                        <label for="llave"  class="control-label">Página personalizada</label>
                        <div class="controls">
                            <input type="text" name="llave" id="llave" class="input-xlarge {required:true}" value="${variable.llave}"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="valor" class="control-label">Valor</label>
                        <div class="controls">
                            <textarea rows="10" name="valor" id="valor" class="{required:true}">${variable.valor}</textarea>
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