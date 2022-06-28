<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <title>Autoevaluacion</title>
        <link href="http://fonts.googleapis.com/css?family=Lobster|Oswald|Kaushan+Script" rel="stylesheet" type="text/css">
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/layout2.css" />
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/font-awesome.min.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/otro.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap-responsive.min.css">
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.11/css/jquery.dataTables.min.css">
        <link rel="stylesheet" href="https://cdn.datatables.net/buttons/1.1.2/css/buttons.dataTables.min.css">
        <style type="text/css">
            .table-condensed th,
            .table-condensed td {
                padding: 1px 5px;
            }
        </style>
        <script src="<%=request.getContextPath()%>/js/vendor/modernizr-2.6.2-respond-1.1.0.min.js"></script>

    </head>

    <body>
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
                                        <i class="icon-user"></i> ${usuario.nombre}
                                        <span class="caret"></span>
                                    </a>
                                    <ul class="dropdown-menu">
                                        <li><a href="#usuario/contrasena/${usuario.id}">Cambiar Contrase&ntilde;a</a></li>
                                        <li class="divider"></li>
                                        <li><a href="<%=request.getContextPath()%>/#CerrarSesion">Cerrar Sesion</a></li>
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
            <div id="conte" class="span12" style="text-align: justify">
                <div class="hero-unit">
                    <h2>Sistema Interno de Aseguramiento de la Calidad UTB</h2>

                    <p style='font-size: 16px; font-weight: 200; line-height: 22px;'>
                        El Sistema Integral de Aseguramiento de la Calidad SIAC-UTB se soporta en una noción de la calidad1
                        que exige que el actuar institucional sea coherente con los propósitos expresados en la misión y
                        visión institucional (consistencia interna) y la capacidad de atender las necesidades planteadas por
                        el nuestro entorno social, económico, empresarial, cultural y ambiental (consistencia externa). 
                    </p>
                    <p style='font-size: 16px; font-weight: 200; line-height: 22px;'>
                        El Sistema integral de Aseguramiento de la Calidad Institucional se fundamenta en cuatro
                        elementos: Evaluación continua y permanente de la actividad institucional, consideraciones de los
                        resultados evaluativos para ajustar metas institucionales, trabajo por el mejoramiento continuo y la
                        rendición de cuenta a los grupos de interés y comunidad académica. EL SIAC-UTB concibe cinco (5)
                        pilares: Gestión Institucional, Gestión Docente, Gestión Investigativa, Gestión de la Extensión y
                        Gestión Académica- administrativa. 
                    </p>
                    <p>
                        <img src="img/siac-utb.png"/>
                    </p>


                </div>
                <div class="row-fluid">
                    <div class="span7">
                        <h3>Referencias Normativas</h3>
                        <ul> 
                            <li style="display: block;">1) Ley 30 de 1992, por la cual se organiza el servicio público de educación superior.</li>
                            <li style="display: block;">2) Decreto 1075 de 2015, por medio del cual se expide el Decreto Único Reglamentario del Sector Educación.</li>
                            <li style="display: block;">3) Decreto 1330 de 2019, mediante el cual se actualiza las condiciones de calidad del registro calificado.</li>
                            <li style="display: block;">4) Resolución 021795 de 2020, mediante el cual se establecen los parámetros de autoevaluación, verificación y evaluación de las condiciones de calidad de programas. decreto 1330 de 2019.</li>
                            <li style="display: block;">5) Acuerdo CESU 02 de 2020. por el cual se reglamenta el proceso de acreditación.</li>
                            <li style="display: block;">6) Lineamientos y guías del proceso de acreditación institucional y de programas 2021.</li>
                        </ul>
                    </div>
                    <div class="span5">
                        <h3>Modelo de Autoevaluación UTB</h3>
                        <p>
                            La Universidad Tecnológica de Bolívar concibe la autoevaluación como una práctica permanente, una autorreflexión de carácter estratégico, esencialmente analítica, participativa y orientada al mejoramiento continuo. Los procesos de autoevaluación con fines de renovación de la acreditación institucional y de programas son de gran significación para la universidad ya que fortalecen su capacidad de autorregulación. 
                        </p>
                        <p>
                            El modelo de autoevaluación consta de cinco etapas, a saber: i) organización del Comité de Acreditación, ii) diseño e implementación del plan comunicacional, iii) recolección y análisis de la información, iv) consolidación del informe de autoevaluación e identificación de propuestas de mejoramiento, y v) calificación de factores y características.
                        </p>
                    </div><!--/span-->
                </div><!--/row-->
            </div> 
        </div><!--/Center-->

        <div id="ui-layout-west" class="ui-layout-west">
            <div id="menu0" class="ui-layout-content">
                <div id="menu" style="padding: 8px 0pt;" class="well">
                    <ul class="nav nav-list">  
                        <button id="west-closer" class="close">&laquo;</button>
                        <li class="nav-header">Modelo</li>
                        <li><a href="#modelo/modelos"><i class="icon-reorder"></i> Listar Modelos</a></li>
                        <li class="divider"></li>
                        <li class="nav-header">Datos basicos</li>
                        <li><a href="#usuario/usuarios"><i class="icon-reorder"></i> Listar Usuarios</a></li>
                        <li><a href="#facultad/facultades"><i class="icon-reorder"></i> Listar Facultades</a></li>
                        <li><a href="#programa/programas"><i class="icon-reorder"></i> Listar Programas</a></li>
                        <li><a href="#fuente/fuentes"><i class="icon-user"></i> P&uacute;blicos objetivo</a></li>
                        <li class="divider"></li>
                        <li><a href="#proceso/procesos"><i class="icon-th"></i> Procesos</a></li>
                        <li><a href="#planMejoramiento/listar"><i class="icon-th"></i> Planes de Mejoramiento</a></li>
                    </ul>
                </div>
            </div>
        </div><!--/West-->



        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
        <script>window.jQuery || document.write('<script src="<%=request.getContextPath()%>/js/vendor/jquery-1.9.1.min.js"><\/script>')</script>
        <script src="<%=request.getContextPath()%>/js/jquery.layout-latest.min.js"></script>
        <script src="<%=request.getContextPath()%>/js/jquery.validate.js"></script>
        <script src="<%=request.getContextPath()%>/js/jquery.metadata.js"></script>
        <script src="<%=request.getContextPath()%>/js/vendor/bootstrap.min.js"></script>
        <script src="<%=request.getContextPath()%>/js/jquery.ba-hashchange.min.js"></script>
        <script src="<%=request.getContextPath()%>/js/highcharts.js"></script>
        <script src="<%=request.getContextPath()%>/js/highcharts-more.js"></script>
        <script src="<%=request.getContextPath()%>/js/exporting.js"></script>
        <script src="https://cdn.datatables.net/1.10.11/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/buttons/1.1.2/js/dataTables.buttons.min.js"></script>
        <script src="//cdn.datatables.net/buttons/1.1.2/js/buttons.flash.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jszip/2.5.0/jszip.min.js"></script>
        <script src="//cdn.rawgit.com/bpampuch/pdfmake/0.1.18/build/pdfmake.min.js"></script>
        <script src="//cdn.rawgit.com/bpampuch/pdfmake/0.1.18/build/vfs_fonts.js"></script>
        <script src="//cdn.datatables.net/buttons/1.1.2/js/buttons.html5.min.js"></script>
        <script src="//cdn.datatables.net/buttons/1.1.2/js/buttons.print.min.js "></script>
        <script src="<%=request.getContextPath()%>/js/jquery.PrintArea.js"></script>
        <script src="<%=request.getContextPath()%>/js/main2.js"></script>

    </body>
</html>

<div class="modal hide fade" id="modalCp2">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h2 class="modal-title">Atención!</h2>
    </div>
    <div class="modal-body">
        <h4>Ejecutar Proceso de Autoevaluación</h4>
        <br>
        <p>Debe configurar todo el proceso para continuar.</p>
    </div>
    <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
    </div>
</div><!-- /.modal-content -->
</div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div class="modal hide fade" id="modalCc1">
    <div class="modal-header">
        <a data-dismiss="modal" class="close">×</a>
        <h3>Atención!</h3>
    </div>
    <div class="modal-body">
        <h4 id="title">Ejecutar Proceso de Autoevaluación.</h4>
        <br>
        <p id="question" style="text-align: justify">Esta seguro que desea ejecutar el Proceso?.</p>
    </div>
    <div class="modal-footer">
        <a id="modalCc1b2" class="btn btn-secundary" data-dismiss="modal" href="#">Cancelar</a>
        <a id="modalCc1b1" class="btn btn-primary" data-dismiss="modal">Si</a>
    </div>
</div>
<div class="modal hide fade" id="modalCc2">
    <div class="modal-header">
        <a data-dismiss="modal" class="close">×</a>
        <h3>Atención!</h3>
    </div>
    <div class="modal-body">
        <h4>Finalizar Proceso de Autoevaluación.</h4>
        <br>
        <p style="text-align: justify">Esta seguro que desea finalizar el Proceso?.</p>
    </div>
    <div class="modal-footer">
        <a id="modalCcb2" class="btn btn-secundary" data-dismiss="modal" href="#">Cancelar</a>
        <a id="modalCcb1" class="btn btn-primary" data-dismiss="modal" href="#">Finalizar Proceso</a>
    </div>
</div>
<div class="modal hide fade" id="modalCc3">
    <div class="modal-header">
        <a data-dismiss="modal" class="close">×</a>
        <h3>Atención!</h3>
    </div>
    <div class="modal-body">
        <h4>Crear Proceso de Autoevaluación.</h4>
        <br>
        <p style="text-align: justify">El programa seleccionado ya tiene un proceso activo.</p>
    </div>
    <div class="modal-footer">
        <a id="modalCb2" class="btn btn-secundary" data-dismiss="modal" href="#">Cerrar</a>
    </div>
</div>
<div class="modal hide fade" id="modalCp3">
    <div class="modal-header">
        <a data-dismiss="modal" class="close">×</a>
        <h3>Atención!</h3>
    </div>
    <div class="modal-body">
        <h4>Ponderación de Características.</h4>
        <br>
        <p>Debe ponderar los factores antes de proceder con la ponderación de características.</p>
    </div>
    <div class="modal-footer">
        <a class="btn btn-primary" data-dismiss="modal" href="#">Cerrar</a>
    </div>
</div>

<div class="modal hide fade" id="modalClaveOK">
    <div class="modal-header">
        <a data-dismiss="modal" class="close">×</a>
        <h3>Atención!</h3>
    </div>
    <div class="modal-body">
        <h4>Contrase&nacute;a cambiada exitosamente.</h4>
    </div>
    <div class="modal-footer">
        <a class="btn btn-primary" data-dismiss="modal" href="#">Cerrar</a>
    </div>
</div>
<div class="modal hide fade" id="modalClaveERROR">
    <div class="modal-header">
        <a data-dismiss="modal" class="close">×</a>
        <h3>Error!</h3>
    </div>
    <div class="modal-body">
        <h4>Ha ocurrido un error.</h4>
    </div>
    <div class="modal-footer">
        <a class="btn btn-primary" data-dismiss="modal" href="#">Cerrar</a>
    </div>
</div>