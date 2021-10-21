<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <link href="css/layout-sitenav.css" type="text/css" rel="stylesheet" media="screen">
        <title></title>
        <link href="http://fonts.googleapis.com/css?family=Lobster|Oswald|Kaushan+Script" rel="stylesheet" type="text/css">
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/layout2.css" />
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/font-awesome.min.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/otro.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/docs.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap-responsive.min.css">
        <script src="<%=request.getContextPath()%>/js/vendor/modernizr-2.6.2-respond-1.1.0.min.js"></script>
    </head>
    <body>
        <!--[if lt IE 7]>
            <p class="chromeframe">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> or <a href="http://www.google.com/chromeframe/?redirect=true">activate Google Chrome Frame</a> to improve your experience.</p>
        <![endif]-->

        <div class="ui-layout-north ui-widget-content">
            <div class="navbar navbar-inverse navbar-fixed-top">
                <div class="navbar-inner">
                    <div class="container-fluid">
                        <a data-target=".nav-collapse" data-toggle="collapse" class="btn btn-navbar">
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </a>
                        <a class="brand" style="padding-top: 10px; padding-bottom: 5px;" href="#"><img src="img/LETRAS.png"/></a>
                        <div class="nav-collapse collapse">
                            <ul class="nav barra" >
                                <li class="active"><a href="#inicio"><i class="icon-home"></i> Inicio</a></li>
                                <li class="dropdown loggining"> 
                                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                                        <i class="icon-user"></i> 
                                        ${persona.getUsuarioId().nombre}
                                        <span class="caret"></span>
                                    </a>
                                    <ul class="dropdown-menu">
                                        <li><a href="<%=request.getContextPath()%>/#cerrarSesion">Cerrar Sesion</a></li>
                                    </ul>

                                </li>
                            </ul>
                        </div>
                        <!--/.nav-collapse -->
                    </div>
                </div>
            </div>        

        </div><!--North-->

        <div class="ui-layout-south ui-widget-content"> 
            <footer id="footer" class="contenedor_footer fondo_footer">
                <div class="links">
                    <a style="font-weight: normal;-moz-text-decoration-line: none;"><span class="muted">&copy; Universidad Tecnológica de Bolívar</span></a>
                </div>
            </footer>
        </div><!--South-->

        <div class="ui-layout-center">
            <div class="container">
                <div>
                    <div id="conte" class="span12" style="text-align: justify">
                        <div class="row">
                            <table class="table table-bordered table-striped" style="font-weight: bold;">
                                <tbody>
                                    <tr>
                                        <td rowspan="2" style="width: 25%; text-align: center;"><img src="/autoevaluacion/img/LogoUTB.png"></td>
                                        <td style="width: 75%; text-align: center; vertical-align: middle;">UNIVERSIDAD TECNOLÓGICA DE BOLÍVAR</td>
                                    </tr>
                                    <tr>
                                        <td style="width: 75%; text-align: center; vertical-align: middle;">${encuesta.getNombre()}</td>
                                    </tr>
                                </tbody>
                            </table>
                            <br/>
                            <p class="insp">${encuesta.getObjetivo()}</p>
                            <p class="insp">${encuesta.getInstrucciones()}</p>
                            <br/>
                        </div>
                        <form id="formResponderE" method="POST">
                            <input type="hidden" name="personaId" value="${persona.getId()}">
                            <input type="hidden" name="encuestaId" value="${encuesta.getId()}">
                            <c:forEach items="${encuesta.getPreguntaList()}" var="pregunta" varStatus="status">

                                <div class="row" id="pregunta${pregunta.id}">
                                    <div class="span10">
                                        <p class="insp" style="font-weight: bold;">${status.index+1} ${pregunta.getPregunta()}</p>
                                        <c:choose>
                                            <c:when test="${pregunta.disenio=='Vertical' && fn:length(pregunta.getTipoPregunta().getItemTipoPreguntaList())!= 0}">
                                                <c:forEach items="${pregunta.getTipoPregunta().getItemTipoPreguntaList()}" var="itemTipoPregunta" varStatus="iter">
                                                    <c:out escapeXml="false" value='<label class="radio"><input type="radio" name="${pregunta.id}" value="${itemTipoPregunta.valor}" class="{required:true}">${itemTipoPregunta.respuesta}</label>'/>
                                                </c:forEach>
                                            </c:when>
                                            <c:when test="${pregunta.disenio=='Horizontal' && fn:length(pregunta.getTipoPregunta().getItemTipoPreguntaList())!= 0}">
                                                <table class="table">
                                                    <thead>
                                                        <tr>
                                                            <c:forEach items="${pregunta.getTipoPregunta().getItemTipoPreguntaList()}" var="itemTipoPregunta" varStatus="iter">
                                                                <c:out escapeXml="false" value='<th>${itemTipoPregunta.respuesta}</th>'/>
                                                            </c:forEach>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <tr>
                                                            <c:forEach items="${pregunta.getTipoPregunta().getItemTipoPreguntaList()}" var="itemTipoPregunta" varStatus="iter">
                                                                <c:out escapeXml="false" value='<td><label class="radio" ><input type="radio" name="${pregunta.id}" value="${itemTipoPregunta.valor}" class="{required:true}"></label></td>'/>
                                                            </c:forEach>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                            </c:when>
                                            <c:when test="${pregunta.disenio=='Matriz con unica respuesta' && fn:length(pregunta.getTipoPregunta().getItemTipoPreguntaList())!= 0}">
                                                <table class="table">
                                                    <thead>
                                                        <tr>
                                                            <th></th>
                                                                <c:forEach items="${pregunta.getTipoPregunta().getItemTipoPreguntaList()}" var="itemTipoPregunta" varStatus="iter">
                                                                    <c:out escapeXml="false" value='<th>${itemTipoPregunta.respuesta}</th>'/>
                                                                </c:forEach>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:choose>
                                                            <c:when test="${fn:length(pregunta.getItemPreguntas())!= 0}">
                                                                <c:forEach items="${pregunta.getItemPreguntas()}" var="itemPregunta" varStatus="iter2">
                                                                    <tr>
                                                                        <td style="text-align: left">${itemPregunta.itemPregunta}</td>
                                                                        <c:choose>
                                                                            <c:when test="${fn:length(pregunta.getTipoPregunta().getItemTipoPreguntaList())!= 0}">
                                                                                <c:forEach items="${pregunta.getTipoPregunta().getItemTipoPreguntaList()}" var="itemTipoPregunta2" varStatus="iter">
                                                                                    <c:out escapeXml="false" value='<td><label class="radio"><input type="radio" name="i${itemPregunta.id}" class="{required:true}" value="${itemTipoPregunta2.valor}"/></label></td>'/>
                                                                                </c:forEach>
                                                                            </c:when>
                                                                        </c:choose>
                                                                    </tr>
                                                                </c:forEach>
                                                            </c:when>
                                                        </c:choose>
                                                    </tbody>
                                                </table>
                                            </c:when>
                                            <c:when test="${pregunta.disenio=='Desplegable' && fn:length(pregunta.getTipoPregunta().getItemTipoPreguntaList())!= 0}">
                                                <c:choose>
                                                    <c:when test="${fn:length(pregunta.getItemPreguntas())!= 0}">
                                                        <table class="table">
                                                            <tbody>
                                                                <c:forEach items="${pregunta.getItemPreguntas()}" var="itemPregunta" varStatus="iter2">
                                                                    <tr>
                                                                        <td style="text-align: left">${itemPregunta.itemPregunta}</td>
                                                                        <td> 
                                                                            <select name="i${itemPregunta.id}" class="{required:true}">
                                                                                <option value=""></option>    
                                                                                <c:forEach items="${pregunta.getTipoPregunta().getItemTipoPreguntaList()}" var="itemTipoPregunta" varStatus="iter">
                                                                                    <option value="${itemTipoPregunta.valor}">${itemTipoPregunta.respuesta}</option>    
                                                                                </c:forEach>
                                                                            </select> 
                                                                        </td>
                                                                    </tr>
                                                                </c:forEach>
                                                            </tbody>
                                                        </table>    
                                                    </c:when>
                                                    <c:otherwise>
                                                        <select name="${pregunta.id}" class="{required:true}">
                                                            <option value=""></option>    
                                                            <c:forEach items="${pregunta.getTipoPregunta().getItemTipoPreguntaList()}" var="itemTipoPregunta2" varStatus="iter">
                                                                <option value="${itemTipoPregunta2.valor}">${itemTipoPregunta2.respuesta}</option>    
                                                            </c:forEach>
                                                        </select> 
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:when>
                                        </c:choose>
                                    </div> 
                                </div>
                            </c:forEach> 
                            <div class="row"> 
                                <div class="span2">
                                    <div style="text-align: left; margin-top: 22px;">
                                        <button class="btn btn-primary" data-content="Env&iacute;a la encuesta evaluada. Verifique que todas las preguntas han sido respondidas correctamente. Esta operación no se podrá deshacer."  value="1" data-original-title="Enviar encuesta" type="submit">Enviar</button>
                                    </div>    
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div><!--/Center-->







        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
        <script>window.jQuery || document.write('<script src="<%=request.getContextPath()%>/js/vendor/jquery-1.9.1.min.js"><\/script>')</script>
        <script src="<%=request.getContextPath()%>/js/jquery.layout-latest.min.js"></script>
        <script src="<%=request.getContextPath()%>/js/jquery.validate.js"></script>
        <script src="<%=request.getContextPath()%>/js/jquery.metadata.js"></script>
        <script src="<%=request.getContextPath()%>/js/vendor/bootstrap.min.js"></script>
        <script src="<%=request.getContextPath()%>/js/jquery.ba-hashchange.min.js"></script>
        <script src="<%=request.getContextPath()%>/js/main3.js"></script>
        <script type="text/javascript">
            $(function () {
                $('.insp').each(function(index) {
                    var textoConSaltosHtml = $(this).text().replace(/(?:\r\n|\r|\n)/g, '<br/>');
                    console.log(textoConSaltosHtml);
                    $(this).html(textoConSaltosHtml);
                });
                
                var validator = $("#formResponderE").bind("invalid-form.validate", function () {
                    alert("usted ha dejado de contestar " + validator.numberOfInvalids() + " preguntas, por favor contestelas todas.");
                })
                        .validate({
                            ignore: "",
                            submitHandler: function () {
                                $("button").attr("disabled", true);
                                $.ajax({
                                    type: 'POST',
                                    url: "<%=request.getContextPath()%>/encuesta/enviar",
                                    data: $("#formResponderE").serialize(),
                                    beforeSend: function () {
                                        $("div.ui-layout-center").append("" +
                                                "<div id='dancing-dots-text'>" +
                                                "Enviando <span><span>.</span><span>.</span><span>.</span><span>.</span><span>.</span></span> " +
                                                "</div>");
                                    },
                                    success: function () {
                                        $("#dancing-dots-text").remove();
                                        $("#myModalGracias").modal();
                                        $('#myModalGracias').on('hidden', function () {
                                            location.hash = "cerrarSesion";
                                        });
                                    } //fin success
                                }); //fin $.ajax
                            }
                        });
                $("button").popover({trigger: "hover", placement: 'top'});
                });
        </script>
    </body>
</html>
<div class="modal hide fade" id="myModalGracias">
    <div class="modal-header">
        <a data-dismiss="modal" style="margin-top: 0px; padding: 0px" class="close">×</a>
        <h3>Gracias</h3>
    </div>
    <div class="modal-body">
        La encuesta se ha enviado correctamente.
        Muchas gracias por participar del proceso de autoevaluaci&oacute;n.
    </div>
    <div class="modal-footer">
        <a class="btn btn-primary" data-dismiss="modal" href="#">Cerrar</a>
    </div>
</div>
<div class="modal hide fade" id="modalSeleccionPerfil">
    <div class="modal-header">
        <a data-dismiss="modal" style="margin-top: 0px; padding: 0px" class="close">×</a>
        <h3>Atención</h3>
    </div>
    <div class="modal-body">
        Debe seleccionar al menos un perfil para diligenciar la encuesta.
    </div>
    <div class="modal-footer">
        <a class="btn btn-primary" data-dismiss="modal" href="#">Cerrar</a>
    </div>
</div>
