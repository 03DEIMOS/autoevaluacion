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

        $("#formCrearPregunta").validate({
            submitHandler: function () {
                $.ajax({
                    type: 'POST',
                    url: "/autoevaluacion/controladorCC?action=crearPregunta",
                    data: $("#formCrearPregunta").serialize(),
                    success: function () {
                        location = "/autoevaluacion/#listarPreguntas";
                    } //fin success
                }); //fin $.ajax    
            }
        });
    });
</script>
<div class="hero-unit">
    <div class="row">
        <div id="conte" class="span10">
            <form id="formCrearPregunta" class="form-horizontal" method="post">
                <fieldset>
                    <legend>Crear Pregunta</legend>
                    <div class="control-group">
                        <label for="nombre"  class="control-label">Pregunta</label>
                        <div class="controls">
                            <textarea rows="3" name="nombre" id="nombre" class="input-xxlarge {required:true}"></textarea>
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="tipo" class="control-label">Tipo de la Pregunta</label>
                        <div class="controls">
                            <select name="tipo" id="tipo">
                                <c:choose>
                                    <c:when test="${fn:length(listaTipoP)!= 0}">
                                        <c:forEach items="${listaTipoP}" var="tipoPregunta" varStatus="iter">
                                            <option value="${tipoPregunta.id}" >${tipoPregunta.tipo}</a></option>
                                            </c:forEach>
                                        </c:when>
                                    </c:choose>
                            </select>
                        </div>
                    </div>
                    <div class="control-group">
                        <div class="controls">
                            <input name="sencilla" type="checkbox"> Establecer esto como una escala de valoración de una sola fila (quitar las opciones de fila)?
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

                    <div class="form-actions">
                        <button class="btn btn-primary" type="submit">Crear Pregunta</button>
                        <button class="btn" type="reset">Cancelar</button>
                    </div>
                </fieldset>
            </form>
        </div><!--/span-->        
    </div><!--/row-->    
</div>