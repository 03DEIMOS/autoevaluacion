<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<script type="text/javascript">

    $(function () {
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

        $("#tipoPlan").change(function () {
            $("#facultadId").empty();
            $("#programaId").empty();

            var selected = $("#tipoPlan option:selected").val();
            if (selected == "Institucional") {
                $("#facultades").hide();
                $.ajax({
                    type: 'GET',
                    url: "/autoevaluacion/programa/programaInstitucional",
                    success: function (datos) {
                        $("#programaId").append(datos);
                        setTimeout(function () {
                            $("#dancing-dots-text").remove();
                        }, 200);
                    } //fin success
                }); //fin $.ajax   
            } else if (selected == "Programas") {
                $("#facultades").show();
                $.ajax({
                    type: 'GET',
                    url: "/autoevaluacion/facultad/facultadesEmbedded",
                    success: function (datos) {
                        $("#facultadId").append(datos);
                        setTimeout(function () {
                            $("#dancing-dots-text").remove();
                        }, 200);
                    } //fin success
                }); //fin $.ajax   
            }

            $("#facultadId").change(function () {
                $("#programaId").empty();
                            $.ajax({
                                type: 'GET',
                                url: "/autoevaluacion/programa/programasByFacultad/" + $("#facultadId option:selected").val(),
                                success: function (datos) {
                                    $("#programaId").append(datos);
                                    setTimeout(function () {
                                        $("#dancing-dots-text").remove();
                                    }, 200);
                                } //fin success
                            }); //fin $.ajax   
                        }
                        );



            var a = $("#factorId option:selected").index();
            if (a !== 0) {
                $("#caracteristicaId").empty();
                $.ajax({
                    type: 'GET',
                    url: "/autoevaluacion/caracteristica/caracteristicasByFactor/" + $("#factorId option:selected").val(),
                    success: function (datos) {
                        $("#caracteristicaId").append(datos);
                        setTimeout(function () {
                            $("#dancing-dots-text").remove();
                        }, 200);
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
                        <label for="tipoPlan" class="control-label">Tipo de Plan de Mejoramiento</label>
                        <div class="controls">
                            <select name="tipoPlan" id="tipoPlan" class="{required:true}">
                                <option></option>
                                <option value="Institucional">Institucional</option>
                                <option value="Programas" >Programas</option>
                            </select>
                        </div>
                    </div>
                    <div id="facultades" class="control-group">
                        <label for="facultadId"  class="control-label">Facultad</label>
                        <div class="controls">
                            <select id="facultadId" name="facultadId" class="input-xlarge">
                                <c:forEach items="${facultades}" var="item" >
                                    <option value="${item.id}">${item.nombre}</option>
                                </c:forEach>
                            </select>   
                        </div>
                    </div>
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
                        <label for="plan" class="control-label">Temporalidad del plan de mejoramiento</label>
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