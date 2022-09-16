<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" language="JavaScript">
    $(document).ready(function() {
        $("#formCrearFacultad").validate({
            submitHandler: function() {
                $.ajax({
                    type: 'POST',
                    url: "/autoevaluacion/facultad/crear",
                    data: $("#formCrearFacultad").serialize(),
                    success: function() {
                        location.hash = "facultad/facultades";
                    } //fin success
                }); //fin $.ajax    
            }
        });
    });
</script>
<div class="hero-unit">
    <div class="row">
        <ul class="breadcrumb">
            <li><a href="#facultad/facultades" >Facultades</a><span class="divider">/</span></li>
            <li>Crear</li>
        </ul>
        <div id="conte" class="span10">
            <form id="formCrearFacultad" class="form-horizontal" method="post">
                <fieldset>
                    <legend>Crear Facultad</legend>
                    <div class="control-group">
                        <label for="nombre" class="control-label">Facultad</label>
                        <div class="controls">
                            <input type="text" name="nombre" id="nombre" class="input-xlarge {required:true}" value=""/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="descripcion" class="control-label">Descripci&oacute;n</label>
                        <div class="controls">
                            <textarea rows="3" name="descripcion" id="descripcion" class="input-xxlarge {required:true}"></textarea>
                        </div>
                    </div>
                    <div class="form-actions">
                        <button class="btn btn-primary" type="submit">Crear Facultad</button>
                        <button class="btn" type="reset">Cancelar</button>
                    </div>
                </fieldset>
            </form>
        </div><!--/span-->        
    </div><!--/row-->    
</div>