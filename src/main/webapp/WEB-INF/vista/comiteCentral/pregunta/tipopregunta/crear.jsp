<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript">
    $(function () {
        $("#tipo").on('mouseleave', function (e) {
            $('#tipo').popover('destroy');
        });
        $("#indicador").on('mouseleave', function (e) {
            $('#indicador').popover('destroy');
        });

        $(document).on("click", ".add", function (e) {
            e.stopPropagation();
            $("#opciones").append('<div class="controls">'
                    + ' <input type="text" name="respuesta[]" id="respuesta" placeholder="respuesta" class="input-xlarge {required:true}" value=""/>'
                    + ' <input type="text" name="valor[]" id="valor" placeholder="valor" class="input-xlarge {required:true}" value=""/>'
                    + ' <a class="add" title="Agregar"><i class="icon-plus-sign"></i></a>'
                    + ' <a class="remove" title="Eliminar"><i class="icon-remove-sign"></i></a>'
                    + ' </div>');
        });

        $(document).on("click", ".remove", function (e) {
            $(this).parent().remove();
        });




        $("#formCrearTipoPregunta").validate({
            submitHandler: function () {
                $.ajax({
                    type: 'POST',
                    url: "/autoevaluacion/tipopregunta/crear",
                    data: $("#formCrearTipoPregunta").serialize(),
                    success: function () {
                        location.hash = "tipopregunta/tipospregunta";
                    } //fin success
                }); //fin $.ajax    
            }
        });
    });
</script>
<div class="hero-unit">
    <div class="row">
        <div id="conte" class="span10">
            <form id="formCrearTipoPregunta" class="form-horizontal" method="post">
                <fieldset>
                    <legend>Crear Tipo de Pregunta</legend>
                    <div class="control-group">
                        <label for="tipo" class="control-label">Nombre</label>
                        <div class="controls">
                            <input type="text" name="tipo" id="tipo" class="input-xlarge {required:true}" value=""/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="descripcion"  class="control-label">Descripción</label>
                        <div class="controls">
                            <textarea rows="3" name="descripcion" id="descripcion" class="input-xxlarge {required:true}"></textarea>
                        </div>
                    </div>
                    <div id="opciones" class="control-group">
                        <label for="respuesta" class="control-label">Opciones de respuesta</label>
                        <div class="controls">
                            <input type="text" name="respuesta[]" id="respuesta" placeholder="respuesta" class="input-xlarge {required:true}" value=""/>
                            <input type="text" name="valor[]" id="valor" placeholder="valor" class="input-xlarge {required:true}" value=""/>
                            <a class="add" title="Agregar"><i class="icon-plus-sign"></i></a>
                            <a class="remove" title="Eliminar"><i class="icon-remove-sign"></i></a>
                        </div>
                    </div>
                    <div class="form-actions">
                        <button class="btn btn-primary" type="submit">Crear tipo de pregunta</button>
                        <button class="btn" type="reset">Cancelar</button>
                    </div>
                </fieldset>
            </form>
        </div><!--/span-->        
    </div><!--/row-->    
</div>