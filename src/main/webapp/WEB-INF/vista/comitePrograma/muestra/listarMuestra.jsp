<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/print.css" media="print">
<!-- CSS to style the file input field as button and adjust the Bootstrap progress bars -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery.fileupload.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery.fileupload-ui.css">
<style type="text/css">
    #sharefLI_1 {
        box-sizing: border-box;
        color: rgb(95, 118, 118);
        height: 43px;
        width: 506.031px;
        perspective-origin: 253.016px 21.5px;
        transform-origin: 253.016px 21.5px;
        border: 0px none rgb(95, 118, 118);
        font: normal normal normal normal 14px / 20px 'Source Sans Pro', 'Segoe UI', 'Droid Sans', Tahoma, Arial, sans-serif;
        list-style: none outside none;
        outline: rgb(95, 118, 118) none 0px;
    }/*#sharefLI_1*/

    #sharefA_2 {
        box-sizing: border-box;
        color: rgb(52, 152, 219);
        cursor: pointer;
        display: inline-block;
        height: 43px;
        text-align: center;
        text-decoration: none;
        touch-action: manipulation;
        vertical-align: middle;
        white-space: nowrap;
        width: 235.297px;
        perspective-origin: 117.641px 21.5px;
        transform-origin: 117.641px 21.5px;
        background: rgba(255, 255, 255, 0) none repeat scroll 0% 0% / auto padding-box border-box;
        border: 1px solid rgb(52, 152, 219);
        border-radius: 3px 3px 3px 3px;
        font: normal normal normal normal 17px / 22.61px 'Source Sans Pro', 'Segoe UI', 'Droid Sans', Tahoma, Arial, sans-serif;
        list-style: none outside none;
        outline: rgb(52, 152, 219) none 0px;
        padding: 0px 29px 0px 21px;
    }/*#sharefA_2*/

    #sharefI_3 {
        box-shadow: rgba(0, 0, 0, 0.0980392) -1px 0px 0px 0px inset;
        box-sizing: border-box;
        color: rgb(52, 152, 219);
        cursor: pointer;
        display: inline-block;
        height: 41px;
        left: -21px;
        position: relative;
        text-align: center;
        white-space: nowrap;
        width: 48px;
        perspective-origin: 24px 20.5px;
        transform-origin: 24px 20.5px;
        background: rgba(0, 0, 0, 0.0196078) none repeat scroll 0% 0% / auto padding-box border-box;
        border: 0px none rgb(52, 152, 219);
        border-radius: 3px 0 0 3px;
        font: normal normal normal normal 17px / 23px FontAwesome;
        list-style: none outside none;
        outline: rgb(52, 152, 219) none 0px;
        padding: 9px;
    }/*#sharefI_3*/

    #sharefI_3:before {
        box-sizing: border-box;
        color: rgb(52, 152, 219);
        cursor: pointer;
        text-align: center;
        white-space: nowrap;
        border: 0px none rgb(52, 152, 219);
        font: normal normal normal normal 17px / 23px FontAwesome;
        list-style: none outside none;
        outline: rgb(52, 152, 219) none 0px;
    }/*#sharefI_3:before*/
</style>
<script type="text/javascript">
    $(function () {
        $("#selectListMuestra").change(function () {
            $("#listM").empty();
            var a = $("#selectListMuestra option:selected").index();
            if (a == 0) {
                $("#listM").empty();
                $("#help1").html('<div class="alert alert-info" role="alert"><strong>Atenci&oacute;n</strong> Seleccione una fuente para ver la muestra asignada a la misma.</div>');
            }else {//para hacer el editar muestra
                $("#help1").empty();
                $("#divPrograma").hide();
                $("#listM").empty();
                $.ajax({
                    type: 'GET',
                    url: "/autoevaluacion/persona/poblacion",
                    data: $("#formListarMuestra").serialize(),
                    success: function (datos) {
                        $("#listM").append(datos);
                        setTimeout(function () {
                            $("#dancing-dots-text").remove();
                        }, 200);
                    } //fin success
                }); //fin $.ajax    
            }
        });
        
        $(".btn-group > .btn").click(function () {
            $("tr.terminadoC").hide();
            $("tr.pendienteC").hide();
            $(".btn-group input").each(function (index) {
                if ($(this).prop("checked") && index == 0) {
                    $("tr.pendienteC").show();
                } else if ($(this).prop("checked") && index == 1) {
                    $("tr.terminadoC").show();
                }
            });
            $("#total").text("Total: " + ($("tr.terminadoC:visible").length + $("tr.pendienteC:visible").length));
        });
    });
</script>   

<div class="hero-unit">
    <div class="row">
        <div id="conte" class="span10">
            <ul class="nav nav-pills" style="margin-bottom: 0px">
                <form id="formListarMuestra" class="" method="post" style="margin-bottom: 0px">
                    <input type="hidden" name="procesoId" value="${proceso.id}">
                    <fieldset>
                        <legend>Asignación de  público</legend>
                        <div class="span3" style="margin-left: 0px">
                            <div class="control-group">
                                <label for="selectListMuestra"  class="control-label">Público: </label>
                                <div class="controls">
                                    <select name="fuenteId" id="selectListMuestra">
                                        <option value="--">Seleccionar Público</option>
                                        <c:choose>
                                            <c:when test="${fn:length(fuentes)!= 0}">
                                                <c:forEach items="${fuentes}" var="fuente" varStatus="iter">
                                                    <option value="${fuente.id}">${fuente.nombre}</option>
                                                </c:forEach>
                                            </c:when>
                                        </c:choose>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </fieldset>
                </form>
                <div id="help1"><div class="alert alert-info" role="alert"><strong>Atenci&oacute;n</strong> Seleccione un público para ver la población asignada al mismo.</div></div>             
            </ul>
            <div id="listM"></div>

            <h2>Adjuntar Archivo con la población</h2> 
            <form action="Formulario" class="form row-border" enctype='multipart/form-data'>
                <div class="form-group">
                    <!-- The global progress bar -->
                    <div class="col-sm-12">
                        <div id="progress" class="progress">
                            <div class="progress-bar progress-bar-success"></div>
                        </div>
                    </div>
                    <div class="col-sm-5">

                        <span class="btn btn-success fileinput-button">      
                            <i class="glyphicon glyphicon-plus"></i>
                            <span>Seleccionar archivo...</span>
                            <input id="fileupload" type="file" name="files[]" multiple>
                        </span>
                    </div>
                    <label class="col-sm-10 control-label">Ingrese el archivo excel con la poblaci&oacute;n.<br>Solamente se aceptan archivos con el formato brindado.</label>
                    <div class="col-sm-10">
                        <div class="col-sm-5">
                            <li id="sharefLI_1">
                                <a href="DescargarFormato" id="sharefA_2" target="_blank"><i id="sharefI_3" class="icon-download-alt"></i> Descargar formato</a>
                            </li>
                        </div>
                        <!-- The file input field used as target for the file upload widget -->
                        <!-- The container for the uploaded files -->
                        <div id="files" class="files"></div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>                      
