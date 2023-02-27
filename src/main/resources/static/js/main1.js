$(function () {
    var urlx;

    $("#popover").popover({
        trigger: 'hover',
        html: true,
        placement: 'top',
        title: ("Personas logueadas"),
        content: function () {
            return '' + $("#popover").attr("data-content");
        }
    }).popover('show');
    //location = "/autoevaluacion/#inicio2";
    $(document).ajaxStart(function () {
        if ($("div.ui-layout-center").find('div#contenido').length === 0) {
            $("div.ui-layout-center").append("<div id='contenido'></div>");
            //$("#contenido").hide();
            $("div.ui-layout-center").append(""
                    + "<div id='dancing-dots-text'>"
                    + "Cargando <span></span> "
                    + "</div>");
        } else {
            $("div.ui-layout-center").append(""
                    + "<div id='dancing-dots-text'>"
                    + "Cargando <span></span> "
                    + "</div>");
        }

    });

    var myLayout;
    myLayout = $('body').layout({
        //	enable showOverflow on west-pane so CSS popups will overlap north pane
        west__size: 270
        , center__paneSelector: ".ui-layout-center"
        , north__paneClass: "ui-layout-pane2"
                //	reference only - these options are NOT required because 'true' is the default
        , closable: true	// pane can open & close
        , resizable: false	// when open, pane can be resized 
        , slidable: false	// when closed, pane can 'slide' open over other panes - closes on mouse-out
        , north__size: 1
        , north__closable: false
        , north__maxSize: 1
        , north__slidable: false	// OVERRIDE the pane-default of 'slidable=true'
        , north__spacing_open: 0		// no resizer-bar when open (zero height)
        , south__resizable: false	// OVERRIDE the pane-default of 'resizable=true'
        , south__closable: false
        , south__spacing_open: 0		// no resizer-bar when open (zero height)
        , west__spacing_open: 0
        , west__spacing_closed: 20
        , west__togglerLength_closed: 35
        , west__togglerAlign_closed: "top"
        , west__togglerContent_closed: "<button id='west-open' class='close' style='float:left;margin-left:4px;opacity:1;margin-top:-10px;'>&raquo;</button>"
        , west__togglerTip_closed: "Mostrar menú"
        , west__togglerTip_open: "Ocultar menú"
        , west__enableCursorHotkey: false
        , west__onclose_end: function () {
            $("#conte").removeClass("span10").addClass("span12");
        }
        , west__onopen_end: function () {
            $("#conte").removeClass("span12").addClass("span10");
        }
        , south__paneClass: "ui-layout-pane"
        , west__togglerContent_open: ""
        , west__minSize: 200
        , west__maxSize: 350


    });
    myLayout.allowOverflow('north');
    // setTimeout( myLayout.resizeAll, 1000 ); /* allow time for browser to re-render with new theme */
    // save selector strings to vars so we don't have to repeat it
    // must prefix paneClass with "body > " to target ONLY the outerLayout panes
    myLayout.addCloseBtn("#west-closer", "west");




    var actualizaEnlaces = function (hash) {
        $(".ui-layout-west .nav li").removeClass("active");
        $(".ui-layout-north .nav li").removeClass("active");
        $("a[href='" + hash + "']").parent().addClass("active");
    };


    var menuProceso = function (procesoId) {
        $(".ui-layout-content > .alert").remove();
        $("#menu").html('<ul class="nav nav-list">'
                + '<button id="west-closer" class="close">&laquo;</button>'
                + '<li><a href="#proceso/procesos"><i class="icon-level-up"></i>Regresar a procesos</a></li>'
                + '<li class="nav-header">Proceso de Autoevaluación</li>'
                + '<li><a href="#proceso/entrar/' + procesoId + '"><i class="icon-cogs"></i> Detalle de Proceso</a></li>'
                + '<li><a href="#proceso/muestra/' + procesoId + '"><i class="icon-group"></i> Poblacion Asignada</a></li>'
                + '<li class="nav-header">Estado del proceso</li>'
                + '<li><a href="#informe/estadoGeneralDelProceso/' + procesoId + '"><i class="icon-bar-chart"></i> Informes</a></li>'
                + '</ul>');
    };

    var menuModelo = function () {
        $(".ui-layout-content > .alert").remove();
        $("#menu").html('<ul class="nav nav-list">' +
                '<button id="west-closer" class="close">&laquo;</button>' +
                '<li><a href="#planMejoramiento/listar"><i class="icon-th"></i> Planes de Mejoramiento</a></li>' +
                '</ul>');
    };
    function menuFactores(modeloId) {
        $(".ui-layout-content > .alert").remove();
        $("#menu").html('<ul class="nav nav-list">' +
                '<button id="west-closer" class="close">&laquo;</button>' +
                '<li><a href="#modelo/modelos"><i class="icon-level-up"></i>Men&uacute; modelo</a></li>' +
                '<li class="nav-header">Factores</li>' +
                '<li><a href="#factor/factores/' + modeloId + '"><i class="icon-th-large"></i> Listar factores</a></li>' +
                '<li class="divider"></li>' +
                '<li class="nav-header">Caracteristicas</li>' +
                '<li><a href="#caracteristica/caracteristicas/' + modeloId + '"><i class="icon-th-list"></i> Listar caracteristicas</a></li>' +
                '<li class="divider"></li>' +
                '<li class="nav-header">Preguntas</li>' +
                '<li><a href="#pregunta/preguntas"><i class="icon-question"></i> Listar preguntas</a></li>' +
                '<li><a href="#tipopregunta/tipospregunta"><i class="icon-question"></i> Listar tipos de pregunta</a></li>' +
                '<li class="divider"></li>' +
                '<li class="nav-header">Encuestas</li>' +
                '<li><a href="#encuesta/encuestas"><i class="icon-tasks"></i> Listar encuestas</a></li>' +
                '</ul>');
    }
    ;

    var hash;
    $(window).hashchange(function () {
        hash = location.hash;
        
        if (hash === "#inicio") {
            location.reload();
            return;
        }
        
        if (hash === "#CerrarSesion") {
            $.post('/autoevaluacion/cerrarSesion', function () {
                location = "/autoevaluacion/admin";

            });//fin post

        } else {
            var url3 = "/autoevaluacion/" + hash;
            url3 = url3.replace('#', "");
            var modelo, proceso;
            if (hash.indexOf("#modelo/entrar/") !== -1) {
                modelo = hash.replace("#modelo/entrar/", "");
            }
            if (hash.indexOf("#proceso/entrar/") !== -1) {
                proceso = hash.replace("#proceso/entrar/", "");
            }
            if (hash.indexOf("#informe/estadoGeneralDelProceso/") !== -1) {
                proceso = hash.replace("#informe/estadoGeneralDelProceso/", "");
            }
            console.log($("ul.nav-list li:eq(1)").html().trim());
            $("div.ui-layout-center").empty();
            $.ajax({
                type: "GET",
                url: url3,
                success: function (data)
                {
                    $("#contenido").append(data);

                    if ($("ul.nav-list li:eq(1)").html().trim() !== "Factores" && hash.indexOf("#modelo/entrar/") !== -1) {
                        menuFactores(modelo);
                    } else if (($("ul.nav-list li:eq(1)").html().trim() === "Factores" || $("ul.nav-list li:eq(1)").html().trim() === "Proceso de Autoevaluación") && (hash.indexOf("#modelo/modelos") !== -1 || hash.indexOf("#proceso/procesos") !== -1)) {
                        menuModelo();
                    } else if ($("ul.nav-list li:eq(1)").html().trim() !== "Proceso de Autoevaluación" && hash.indexOf("#proceso/entrar/") !== -1) {
                        menuProceso(proceso);
                    }
                    myLayout.addCloseBtn("#west-closer", "west");
                    $("#contenido").show(200, function () {
                        $("#dancing-dots-text").remove();
                    });
                    actualizaEnlaces(hash);
                } //fin success
            }); //fin del $.ajax
        }
    });
});
