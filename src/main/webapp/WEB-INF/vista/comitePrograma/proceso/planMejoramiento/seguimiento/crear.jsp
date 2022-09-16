<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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

        $("#formCrearSeguimiento").validate({
            submitHandler: function () {
                $.ajax({
                    type: 'POST',
                    url: "/autoevaluacion/seguimiento/crear",
                    data: $("#formCrearSeguimiento").serialize(),
                    success: function () {
                        location.hash = "seguimiento/seguimientos/${idHallazgo}";
                    } //fin success
                }); //fin $.ajax    */
            }
        });
    });
</script>
<div class="hero-unit">
    <div style="margin-left: -30px;">
        <div id="conte" class="span10" style="text-align: justify">
            <form id="formCrearSeguimiento" class="form-horizontal" method="post">
                <fieldset>
                    <legend>Seguimiento: ${oportunidadMejora.getHallazgo()}</legend>
                    <input name="idHallazgo" type="hidden" value="${idHallazgo}"/>
                    <div class="control-group">
                        <label for="fechaRealizado" class="control-label">Fecha Seguimiento Realizada</label>
                        <div class="controls">
                            <input type="text" name="fechaRealizado" id="fechaRealizado" class="input-xlarge {required:false}"/>
                        </div>
                    </div>

                    <div class="control-group">
                        <label for="avances" class="control-label">Avances*</label>
                        <div class="controls">
                            <textarea rows="3" name="avances" id="avances" class="input-xlarge {required:false}"></textarea>             
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
                    
                    <div class="form-actions">
                        <button class="btn btn-primary" type="submit">Registrar Seguimiento</button>
                        <button class="btn" type="reset">Cancelar</button>
                    </div>
                </fieldset>

            </form>
        </div>
    </div>
</div>
