<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<script type="text/javascript">

    $(function() {
        $("#formCrearPlanMejoramiento").validate({
            submitHandler: function () {
                $.ajax({
                    type: 'POST',
                    url: "/autoevaluacion/planMejoramiento/crear",
                    data: $("#formCrearPlanMejoramiento").serialize(),
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
            <li>Crear</li>
        </ul>
        <div id="conte" class="span10">
            <form id="formCrearPlanMejoramiento" class="form-horizontal" method="post">
                <fieldset>
                    <legend>Nuevo Plan de Mejoramiento</legend>
                    <div class="control-group">
                        <label for="programaId"  class="control-label">Programa</label>
                        <div class="controls">
                            <select id="programaId" name="programaId" class="input-xlarge">
                                <c:forEach items="${programas}" var="item" >
                                    <option value="${item.id}">${item.nombre}</option>
                                </c:forEach>
                            </select>   
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="plan" class="control-label">Descripci&oacute;n</label>
                        <div class="controls">
                            <textarea rows="3" name="plan" id="plan" class="input-xxlarge {required:true}"></textarea>
                        </div>
                    </div>
                    <div class="form-actions">
                        <button class="btn btn-primary" type="submit">Crear</button>
                        <button class="btn" type="reset">Cancelar</button>
                    </div>
                </fieldset>
            </form>
        </div><!--/span-->        
    </div><!--/row-->    
</div>