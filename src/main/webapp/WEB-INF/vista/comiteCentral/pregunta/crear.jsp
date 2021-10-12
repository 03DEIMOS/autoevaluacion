<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript">
    $(function () {

        $(document).on("click", ".addS", function (e) {
            e.stopPropagation();
            $("#opciones").append('<div class="controls">'
                    + ' <input type="text" name="subpregunta[]" placeholder="subpregunta" class="input-xxlarge {required:true}" value=""/>'
                    + ' <a class="addS" title="Agregar"><i class="icon-plus-sign"></i></a>'
                    + ' <a class="removeS" title="Eliminar"><i class="icon-remove-sign"></i></a>'
                    + ' </div>');
        });
        $("#sencilla").click(function () {
            $("#opciones").toggle();
        });
        $("#btnVistaPrevia").click(function () {

            var values = $("#formCrearPregunta").serializeArray();
            /* Because serializeArray() ignores unset checkboxes and radio buttons: */
            values = values.concat($('#formCrearPregunta input[type=checkbox]:not(:checked)')
                    .map(function () {
                        return {"name": this.name, "value": false}
                    }).get()
                    );
            $.ajax({
                type: 'POST',
                url: "/autoevaluacion/pregunta/vistaPrevia",
                data: values,
                success: function (data) {
                    $("#dancing-dots-text").remove();
                    $("#vista-previa").html(data);
                } //fin success
            }
            ); //fin $.ajax    
        });
        $("#formCrearPregunta").validate({
            submitHandler: function () {
                var values = $("#formCrearPregunta").serializeArray();
                /* Because serializeArray() ignores unset checkboxes and radio buttons: */
                values = values.concat($('#formCrearPregunta input[type=checkbox]:not(:checked)')
                        .map(function () {
                            return {"name": this.name, "value": false}
                        }).get()
                        );
                $.ajax({
                    type: 'POST',
                    url: "/autoevaluacion/pregunta/crear",
                    data: values,
                    success: function () {
                        location.hash = "/pregunta/preguntas";
                    } //fin success
                }); //fin $.ajax    
            }
        });
    }
    );
</script>
<div class="hero-unit">
    <div class="row">
        <div id="conte" class="span10">
            <form id="formCrearPregunta" class="form-horizontal" method="post">
                <fieldset>
                    <legend>Crear Pregunta</legend>
                    <div class="control-group">
                        <label for="pregunta"  class="control-label">Pregunta</label>
                        <div class="controls">
                            <textarea rows="3" name="pregunta" id="pregunta" class="input-xxlarge {required:true}"></textarea>
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="tipoId" class="control-label">Tipo de la Pregunta</label>
                        <div class="controls">
                            <select name="tipoId" id="tipoId">
                                <c:choose>
                                    <c:when test="${fn:length(listaTipoP)!= 0}">
                                        <c:forEach items="${listaTipoP}" var="tipoPregunta" varStatus="iter">
                                            <option value="${tipoPregunta.id}" >${tipoPregunta.tipo}</option>
                                            </c:forEach>
                                        </c:when>
                                    </c:choose>
                            </select>
                        </div>
                    </div>
                    <div class="control-group">
                        <div class="controls">
                            <input name="sencilla" id="sencilla" type="checkbox" value="1"> Establecer esto como una escala de valoración de una sola fila (quitar las opciones de fila)?
                        </div>
                    </div>

                    <div id="opciones" class="control-group">
                        <label for="respuesta" class="control-label">SubPreguntas</label>
                        <div class="controls">
                            <input type="text" name="subpregunta[]" id="respuesta" placeholder="subpregunta" class="input-xxlarge {required:true}" value=""/>
                            <a class="addS" title="Agregar"><i class="icon-plus-sign"></i></a>
                            <a class="removeS" title="Eliminar"><i class="icon-remove-sign"></i></a>
                        </div>
                    </div>
                    <button type="button" id="btnVistaPrevia">Vista Previa</button> 
                    <div id="vista-previa"></div>
                    <div class="form-actions">
                        <button class="btn btn-primary" type="submit">Crear Pregunta</button>
                        <button class="btn" type="reset">Cancelar</button>
                    </div>
                </fieldset>
            </form>
        </div><!--/span-->        
    </div><!--/row-->    
</div>