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

        $("#formCrearParametro").validate({
            submitHandler: function () {
                $.ajax({
                    type: 'POST',
                    url: "/autoevaluacion/parametro/crear",
                    data: $("#formCrearParametro").serialize(),
                    success: function () {
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
            <li><a href="#parametro/parametros" >Parametros</a><span class="divider">/</span></li>
            <li>Crear</li>
        </ul>
        <div id="conte" class="span10">
            <form id="formCrearParametro" class="form-horizontal" method="post">
                <fieldset>
                    <legend>Crear Parametro</legend>
                    <div class="control-group">
                        <label for="llave"  class="control-label">Llave</label>
                        <div class="controls">
                            <input type="text" name="llave" id="llave" class="input-xlarge {required:true}" value=""/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="valor" class="control-label">Valor</label>
                        <div class="controls">
                            <textarea id="valor" name="valor"></textarea>
                        </div>
                    </div>

                    <div class="form-actions">
                        <button class="btn btn-primary" type="submit">Crear Parametro</button>
                        <button class="btn" type="reset">Cancelar</button>
                    </div>
                </fieldset>
            </form>
        </div><!--/span-->        
    </div><!--/row-->    
</div>