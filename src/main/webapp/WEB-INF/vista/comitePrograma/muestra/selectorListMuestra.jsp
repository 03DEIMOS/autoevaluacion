<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/print.css" media="print">
<link rel="stylesheet" href="css/jquery.fileupload.css">
<style type="text/css">

    tr.terminadoC td{
        background-color: #DFF0D8 !important; 
        color: #468847;
    }

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
        marcacion = new Date();
        Hora = marcacion.getHours();
        Minutos = marcacion.getMinutes();
        Segundos = marcacion.getSeconds();
        if (Hora <= 9)
            Hora = "0" + Hora
        if (Minutos <= 9)
            Minutos = "0" + Minutos
        if (Segundos <= 9)
            Segundos = "0" + Segundos
        var Dia = new Array("Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo");
        var Mes = new Array("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre");
        var Hoy = new Date();
        var Anio = Hoy.getFullYear();
        var Fecha = Dia[Hoy.getDay()] + " " + Hoy.getDate() + " de " + Mes[Hoy.getMonth()] + " de " + Anio + ", a las " + Hora + ":" + Minutos + ":" + Segundos;
        $("#hora").html(" " + Fecha);

        $("#bpreparedCrearPersona").click(function () {
            $.ajax({
                type: 'GET',
                url: "/autoevaluacion/persona/crearEvaluador/${proceso}/${fuente}",
                success: function (datos) {
                    $("#editM").empty();
                    $("#editM").append(datos);
                    $("#contenido").show(200, function () {
                        $("#dancing-dots-text").remove();
                    });
                } //fin success
            }); //fin $.ajax    

        });
        $("#bpreparedEliminarPersonas").click(function () {
            $.ajax({
                type: 'POST',
                url: "/autoevaluacion/controladorCP?action=eliminarPersonas&fuente=${fuente}",
                success: function () {
                    $("#listM").empty();
                    $.ajax({
                        type: 'POST',
                        url: "/autoevaluacion/controladorCP?action=selectorListMuestra",
                        data: $("#formListarMuestra").serialize(),
                        success: function (datos) {
                            $(".divEvaluador").remove();
                            $("#listM").append(datos);
                            $("#contenido").show(200, function () {
                                $("#dancing-dots-text").remove();
                            });
                        } //fin success
                    }); //fin $.ajax 
                } //fin success
            }); //fin $.ajax    

        });

        $("#bpreparedEditarMuestra").click(function () {
            $.ajax({
                type: 'POST',
                url: "/autoevaluacion/controladorCP?action=preparedEditarMuestra&fuente=${fuente}",
                success: function (datos) {
                    $("#editM").empty();
                    $("#editM").append(datos);
                    $("#contenido").show(200, function () {
                        $("#dancing-dots-text").remove();
                    });
                } //fin success
            }); //fin $.ajax    

        });


        $("#printEnlace").click(function () {
            $('#printMuestra').printArea();
            return false;
        });
        $("#actEnlace").click(function () {
            marcacion = new Date()
            Hora = marcacion.getHours()
            Minutos = marcacion.getMinutes()
            Segundos = marcacion.getSeconds()
            if (Hora <= 9)
                Hora = "0" + Hora
            if (Minutos <= 9)
                Minutos = "0" + Minutos
            if (Segundos <= 9)
                Segundos = "0" + Segundos
            var Dia = new Array("Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo");
            var Mes = new Array("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre");
            var Hoy = new Date();
            var Anio = Hoy.getFullYear();
            var Fecha = Dia[Hoy.getDay()] + " " + Hoy.getDate() + " de " + Mes[Hoy.getMonth()] + " de " + Anio + ", a las " + Hora + ":" + Minutos + ":" + Segundos;
            $("#hora").html(" " + Fecha);
            $.ajax({
                type: 'POST',
                url: "/autoevaluacion/controladorCP?action=selectorListSemestre",
                data: $("#formListarMuestra").serialize(),
                success: function (datos) {
                    $("#listM").empty();
                    $("#listM").append(datos);
                    $("#contenido").show(200, function () {
                        $("#dancing-dots-text").remove();
                    });
                } //fin success
            }); //fin $.ajax   
        });

        $("#sharefA_2").click(function () {
            fetch('/autoevaluacion/utilitario/downloadFile?archivo=plantilla')
                    .then(resp => resp.blob())
                    .then(blob => {
                        const url = window.URL.createObjectURL(blob);
                        const a = document.createElement('a');
                        a.style.display = 'none';
                        a.href = url;
                        // the filename you want
                        a.download = 'plantilla.xlsx';
                        document.body.appendChild(a);
                        a.click();
                        window.URL.revokeObjectURL(url);
                    })
                    .catch(() => alert('Ha ocurrido un error intentando descargar el formato de población!'));
        });

    });
</script>


<div class="span10">
    <a  style="float: right; cursor: pointer" id="printEnlace"><i class="icon-print"></i> Imprimir</a>  
</div>
<div id="printMuestra">
    <div>
        <div style="margin-left: 0px;" class="span1"><span style="margin-left: 0px;" id="spanActualizado" class="label label-info span1">Actualizado</span></div>
        <div class="span9"><p id="hora" class="help-block"></p></div>
    </div>
    <div id="listM2" class="span10" style="margin-left: 0px;">
        <div class="span10" style="margin-left: 0px;">
            <div class="btn-group" data-toggle="buttons">
                <label class="btn btn-danger">
                    <input type="checkbox" autocomplete="off" checked>Pendiente
                </label>
                <label class="btn btn-success">
                    <input type="checkbox" autocomplete="off" checked>Terminado
                </label>
            </div> 								
            <div id="editM">
                <table id="tablaY1" class="table table-striped table-bordered" cellspacing="0" width="100%">
                    <thead>
                        <tr>
                            <th>Código</th>
                            <th>Identificación</th>
                            <th>Nombre</th>
                            <th>Apellidos</th>
                            <th>Correo electrónico</th>
                            <th>Variable1</th>
                            <c:if test="${variable2Exist}"><th>Variable2</th></c:if>
                        </tr>
                    </thead>
                    <tbody id="bodytablaestudiante">
                        <c:choose>
                            <c:when test="${fn:length(personas)!= 0}">
                                <c:forEach items="${personas}" var="persona" varStatus="iter1">
                                    <c:choose>   
                                        <c:when test="${persona.terminado == 'S'}">
                                            <tr class="terminadoC">
                                            </c:when>
                                            <c:otherwise>
                                            <tr class="pendienteC"> 
                                            </c:otherwise>    
                                        </c:choose>
                                        <td>${persona.getUsuarioId().usuario}</td>
                                        <td>${persona.getUsuarioId().identificacion}</td>
                                        <td>${persona.getUsuarioId().nombre}</td>
                                        <td>${persona.getUsuarioId().apellido}</td>
                                        <td>${persona.getUsuarioId().email}</td>
                                        <td>${persona.variable1}</td>
                                         <c:if test="${variable2Exist}"><td>${persona.variable2}</td></c:if>
                                    </tr>
                                </c:forEach>
                            </c:when>
                        </c:choose>
                    </tbody>
                </table>
                <a id="bpreparedCrearPersona" class="btn btn-large btn-primary"><i class="icon-edit-sign"></i> Crear Evaluador</a>
                <p id="total0" style="font-weight: bold">Total: ${fn:length(personas)}</p>

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
                                    <a id="sharefA_2" target="_blank"><i id="sharefI_3" class="icon-download-alt"></i> Descargar formato</a>
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
</div>
<script src="js/vendor/jquery.ui.widget.js"></script>
<script src="js/jquery.iframe-transport.js"></script>
<script src="js/jquery.fileupload.js"></script>
<script src="js/jquery.fileupload-process.js"></script>
<script src="js/jquery.fileupload-validate.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        'use strict';
        // Initialize the jQuery File Upload widget:
        $('#fileupload').fileupload({
            // Uncomment the following to send cross-domain cookies:
            //xhrFields: {withCredentials: true},
            url: '/autoevaluacion/utilitario/uploadFile',
            method: "POST",
            formData: {procesoId: '' + $("#procesoId").val(), fuenteId: '' + $("#selectListMuestra").val()},
            acceptFileTypes: /(\.|\/)(xls|gif|jpg|png|pdf|xlsx)$/i,
            previewFileTypes: /^.*\/(gif|jpeg|png|pdf|PDF)$/
        });

        // Enable iframe cross-domain access via redirect option:
        $('#fileupload').fileupload(
                'option',
                'redirect',
                window.location.href.replace(
                        /\/[^\/]*$/,
                        '/cors/result.html?%s'
                        )
                ).bind('fileuploaddestroy', function (e, data) {
            if (e.isDefaultPrevented()) {
                return false;
            }
            var that = $(this).data('blueimp-fileupload') ||
                    $(this).data('fileupload'),
                    removeNode = function () {
                        that._transition(data.context).done(
                                function () {
                                    $(this).remove();
                                    that._trigger('destroyed', e, data);
                                }
                        );
                    };
            if (data.url) {
                data.dataType = data.dataType || that.options.dataType;
                $.ajax(data).done(removeNode).fail(function () {
                    that._trigger('destroyfailed', e, data);
                    removeNode();
                });
            } else {
                removeNode();
            }

        }).bind('fileuploaddone', function (e, data) {
            $("#dancing-dots-text").remove();
            $('#selectListMuestra').val('--').trigger('change');
        });



        $('#tablaY1').DataTable({
            bPaginate: false,
            aaSorting: [],
            dom: 'Bfrtip',
            buttons: [
                {
                    extend: 'copy',
                    text: 'Copiar'
                },
                {
                    extend: 'excelHtml5',
                    exportOptions: {
                        rows: ':visible'
                    },
                    text: 'Exportar a excel',
                    title: 'Muestra ${fuente}'
                }
            ],
            language: {
                buttons: {
                    copyTitle: 'Copiar'
                },
                "lengthMenu": "Mostrando _MENU_ registros por pagina",
                "zeroRecords": "No hay registros",
                "info": "Mostrando page _PAGE_ of _PAGES_",
                "infoEmpty": "No hay registros disponibles",
                "sInfo": "Mostrando registros del _START_ al _END_ de _TOTAL_",
                "infoFiltered": "(filtrados de un total de _MAX_ registros)",
                sSearch: "Buscar:"
            }
        });
    });
</script>
