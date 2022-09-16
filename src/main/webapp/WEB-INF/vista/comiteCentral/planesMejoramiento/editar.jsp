<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript">
    $(function () {
        $("#formEditarPlanMejoramiento").validate({
            submitHandler: function () {
                $.ajax({
                    type: 'PUT',
                    url: "/autoevaluacion/planMejoramiento/editar",
                    data: $("#formEditarPlanMejoramiento").serialize(),
                    success: function () {
                        location.hash = "planMejoramiento/listar/${usuarioId}";
                    } //fin success
                }); //fin $.ajax    
            }
        });
    });
</script>
<div class="hero-unit">
    <div class="row">
        <ul class="breadcrumb">
            <li><a href="#planMejoramiento/listar/${usuarioId}">Planes de Mejoramiento</a><span class="divider">/</span></li>
            <li>Editar</li>
        </ul>
        <div id="conte" class="span10">
            <form id="formEditarPlanMejoramiento" class="form-horizontal">
                <fieldset>
                    <legend>Editar Plan de Mejoramiento</legend>
                    <input type="hidden" name="planMejoramientoId" value="${planMejoramiento.id}"/>
                    <div class="control-group">
                        <label for="programaId"  class="control-label">Programa</label>
                        <div class="controls">
                            <select id="programaId" name="programaId" class="{required:true}">
                                <option value=""></option>    
                                <c:forEach items="${programas}" var="programa" varStatus="iter">
                                    <c:choose>
                                        <c:when test="${programa.id==planMejoramiento.programaId.id}">
                                            <option value="${programa.id}" selected="selected">${programa.nombre}</option>    
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${programa.id}">${programa.nombre}</option>    
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>    
                        </div>
                    </div>

                    <div class="control-group">
                        <label for="plan" class="control-label">Descripci&oacute;n</label>
                        <div class="controls">
                            <textarea rows="3" name="plan" id="plan" class="input-xxlarge {required:true}">${planMejoramiento.plan}</textarea>
                        </div>
                    </div>

                    <div class="control-group">
                        <label for="estado" class="control-label">Estado</label>
                        <div class="controls">
                            <select id="estado" name="estado" class="{required:true}">
                                <c:choose>
                                    <c:when test="${planMejoramiento.estado == 'Activo'}">
                                        <option selected="selected">Activo</option>
                                        <option>Finalizado</option>
                                    </c:when>
                                    <c:when test="${planMejoramiento.estado == 'Finalizado'}">
                                        <option>Activo</option>
                                        <option selected="selected">Finalizado</option>
                                    </c:when>
                                </c:choose>
                            </select>                
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