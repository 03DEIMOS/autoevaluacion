<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/fcbklistselection.css">
<script src="<%=request.getContextPath()%>/js/fcbklistselection.js" type="text/javascript"></script>
<script type="text/javascript" language="JavaScript">
    $(document).ready(function () {
        //id(ul id),width,height(element height),row(elements in row)        
        var $fcbklist = $('#fcbklist');
        var $listItems = $fcbklist.find('li');

        $.fcbkListSelection("#fcbklist", "600", "50", "3");

        $(".clearer").before('<input type="text" id="filter" class="input-medium search-query" placeholder="Buscar" style="padding-top: 0px; padding-bottom: 0px; float: right; border-right-width: 1px; padding-right: 14px; margin-right: 35px;">');


        $("#formEditarTipoPregunta").validate({
            submitHandler: function () {
                $.ajax({
                    type: 'PUT',
                    url: "/autoevaluacion/tipopregunta/editar",
                    data: $("#formEditarTipoPregunta").serialize(),
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
            <form id="formEditarTipoPregunta" class="form-horizontal">
                <fieldset>
                    <legend>Editar Tipo de Pregunta</legend>
                    <input type="hidden" name="tipoPreguntaid" value="${tipoPregunta.id}">
                    <div class="control-group">
                        <label for="tipo" class="control-label">Nombre</label>
                        <div class="controls">
                            <input type="text" name="tipo" id="tipo" class="input-xlarge {required:true}" value="${tipoPregunta.tipo}"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="descripcion"  class="control-label">Descripción</label>
                        <div class="controls">
                            <textarea rows="3" name="descripcion" id="descripcion" class="input-xxlarge {required:true}">${tipoPregunta.descripcion}</textarea>
                        </div>
                    </div>
                    <div id="opciones" class="control-group">
                        <label for="respuesta" class="control-label">Opciones de respuesta</label>
                        <c:choose>
                            <c:when test="${fn:length(tipoPregunta.getItemTipoPreguntaList())!= 0}">
                                <c:forEach items="${tipoPregunta.getItemTipoPreguntaList()}" var="itemTipoPregunta" varStatus="iter">
                                    <div class="controls">
                                        <input type="text" name="respuesta[]" id="respuesta" placeholder="respuesta" class="input-xlarge {required:true}" value="${itemTipoPregunta.respuesta}"/>
                                        <input type="text" name="valor[]" id="valor" placeholder="valor" class="input-xlarge {required:true}" value="${itemTipoPregunta.valor}"/>
                                        <a class="add" title="Agregar"><i class="icon-plus-sign"></i></a>
                                        <a class="remove" title="Eliminar"><i class="icon-remove-sign"></i></a>
                                    </div>
                                </c:forEach>
                            </c:when>
                        </c:choose>                            

                    </div>
                    <div class="form-actions">
                        <button class="btn btn-primary" type="submit">Editar tipo de pregunta</button>
                        <button class="btn" type="reset">Cancelar</button>
                    </div>
                </fieldset>
            </form>
        </div><!--/span-->        
    </div><!--/row-->    
</div>