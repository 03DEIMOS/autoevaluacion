$(function () {
  //  location = "/autoevaluacion/#inicio";
    /*$(document).ajaxStart(function() {
     
     });*/

    var myLayout;
    myLayout = $('body').layout({
        //	enable showOverflow on west-pane so CSS popups will overlap north pane

        center__paneSelector: ".ui-layout-center"
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



        , south__paneClass: "ui-layout-pane"
    });
    myLayout.allowOverflow('north');
    // setTimeout( myLayout.resizeAll, 1000 ); /* allow time for browser to re-render with new theme */
    // save selector strings to vars so we don't have to repeat it
    // must prefix paneClass with "body > " to target ONLY the outerLayout panes

    var hash;
    $(window).hashchange(function () {
        hash = location.hash;
        if (hash === "#cerrarSesion") {
            $.post('/autoevaluacion/cerrarSesion', function () {
                location = "/autoevaluacion";
            }); //fin post

        }
    });
});
