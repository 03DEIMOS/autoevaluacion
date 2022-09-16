<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/datepicker.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" language="JavaScript">
    $(document).ready(function () {
        $('.tool').tooltip().click(function (e) {
            $(this).tooltip('hide');
        });
        
        $('#fechaRealizado').datepicker({
            format: 'dd/mm/yyyy'
        });
        $("#formEditarSeguimiento").validate({
            submitHandler: function () {
                $.ajax({
                    type: 'PUT',
                    url: "/autoevaluacion/seguimiento/editar",
                    data: $("#formEditarSeguimiento").serialize(),
                    success: function () {
                        location.hash = "seguimiento/seguimientos/${seguimiento.oportunidadMejora.idHallazgo}";
                    } //fin success
                }); //fin $.ajax    */
            }
        });
    });
</script>
<div class="hero-unit">
    <div style="margin-left: -30px;">
         <div id="conte" class="span10" style="text-align: justify">
            <form id="formEditarSeguimiento" class="form-horizontal">
                 <input type="hidden" name="idSeguimiento"  value="${seguimiento.idSeguimiento}"/>
                <fieldset>
                    <legend>Editar Seguimiento: ${oportunidadMejora.getHallazgo()}</legend>
                    <div class="control-group">
                        <label for="fechaRealizado" class="control-label">Fecha Realizado: </label>
                        <div class="controls">
                            <input type="text" name="fechaRealizado" id="fechaRealizado" class="input-xlarge {required:false}" value="${seguimiento.fechaRealizado}"/>
                        </div>
                    </div>

                   <div class="control-group">
                        <label for="avances" class="control-label">Avances: </label>
                        <div class="controls">
                            <textarea rows="3" name="avances" id="avances" class="input-xlarge {required:false}">${seguimiento.avances}</textarea>
                        </div>
                    </div>
                        
                    <div class="control-group">
                        <label for="estadoId" class="control-label">Estado</label>
                        <div class="controls">
                            <select id="estadoId" name="estadoId" class="{required:true}">
                                <option></option>
                                <c:forEach items="${tiposAccion}" var="tipoAccion" varStatus="iter">
                                    <c:choose>
                                        <c:when test="${tipoAccion != oportunidadMejora.getEstadoId()}">
                                            <option value="${tipoAccion.id}">${tipoAccion.tipo}</option>    
                                        </c:when>
                                        <c:otherwise>
                                            <option selected="selected" value="${tipoAccion.id}">${tipoAccion.tipo}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>      
                        </div>
                    </div>  
                    
                    <div class="form-actions span8">
                        <button class="btn btn-primary" type="submit">Editar Actividad de Seguimiento</button>
                        <button class="btn" type="reset">Cancelar</button>
                    </div>
                </fieldset>
            </form>
        </div>
    </div>
</div>