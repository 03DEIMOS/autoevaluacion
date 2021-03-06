/* Set the defaults for DataTables initialisation */
$.extend(true, $.fn.dataTable.defaults, {
    "sDom": "<'row-fluid'<'span6'l><'span6'f>r>t<'row-fluid'<'span6'i><'span6'p>>",
    "sPaginationType": "bootstrap",
    "oLanguage": {
        "sLengthMenu": "_MENU_ records per page"
    }
});
/* Default class modification */
$.extend($.fn.dataTableExt.oStdClasses, {
    "sWrapper": "dataTables_wrapper form-inline"
});
/* API method to get paging information */
$.fn.dataTableExt.oApi.fnPagingInfo = function(oSettings)
{
    return {
        "iStart": oSettings._iDisplayStart,
        "iEnd": oSettings.fnDisplayEnd(),
        "iLength": oSettings._iDisplayLength,
        "iTotal": oSettings.fnRecordsTotal(),
        "iFilteredTotal": oSettings.fnRecordsDisplay(),
        "iPage": oSettings._iDisplayLength === -1 ?
                0 : Math.ceil(oSettings._iDisplayStart / oSettings._iDisplayLength),
        "iTotalPages": oSettings._iDisplayLength === -1 ? 0 : Math.ceil(oSettings.fnRecordsDisplay() / oSettings._iDisplayLength)
    };
};
/* Bootstrap style pagination control */
$.extend($.fn.dataTableExt.oPagination, {
    "bootstrap": {
        "fnInit": function(oSettings, nPaging, fnDraw) {
            var oLang = oSettings.oLanguage.oPaginate;
            var fnClickHandler = function(e) {
                e.preventDefault();
                if (oSettings.oApi._fnPageChange(oSettings, e.data.action)) {
                    fnDraw(oSettings);
                }
            };
            $(nPaging).addClass('pagination').append(
                    '<ul>' +
                    '<li class="prev disabled"><a href="#">&larr; ' + oLang.sPrevious + '</a></li>' +
                    '<li class="next disabled"><a href="#">' + oLang.sNext + ' &rarr; </a></li>' +
                    '</ul>'
                    );
            var els = $('a', nPaging);
            $(els[0]).bind('click.DT', {action: "previous"}, fnClickHandler);
            $(els[1]).bind('click.DT', {action: "next"}, fnClickHandler);
        }, "fnUpdate": function(oSettings, fnDraw) {
            var iListLength = 5;
            var oPaging = oSettings.oInstance.fnPagingInfo();
            var an = oSettings.aanFeatures.p;
            var i, ien, j, sClass, iStart, iEnd, iHalf = Math.floor(iListLength / 2);
            if (oPaging.iTotalPages < iListLength) {
                iStart = 1;
                iEnd = oPaging.iTotalPages;
            }
            else if (oPaging.iPage <= iHalf) {
                iStart = 1;
                iEnd = iListLength;
            } else if (oPaging.iPage >= (oPaging.iTotalPages - iHalf)) {
                iStart = oPaging.iTotalPages - iListLength + 1;
                iEnd = oPaging.iTotalPages;
            } else {
                iStart = oPaging.iPage - iHalf + 1;
                iEnd = iStart + iListLength - 1;
            }

            for (i = 0, ien = an.length; i < ien; i++) {
                // Remove the middle elements
                $('li:gt(0)', an[i]).filter(':not(:last)').remove();
                // Add the new list items and their event handlers
                for (j = iStart; j <= iEnd; j++) {
                    sClass = (j == oPaging.iPage + 1) ? 'class="active"' : '';
                    $('<li ' + sClass + '><a href="#">' + j + '</a></li>')
                            .insertBefore($('li:last', an[i])[0])
                            .bind('click', function(e) {
                                e.preventDefault();
                                oSettings._iDisplayStart = (parseInt($('a', this).text(), 10) - 1) * oPaging.iLength;
                                fnDraw(oSettings);
                            });
                }

                // Add / remove disabled classes from the static elements
                if (oPaging.iPage === 0) {
                    $('li:first', an[i]).addClass('disabled');
                } else {
                    $('li:first', an[i]).removeClass('disabled');
                }

                if (oPaging.iPage === oPaging.iTotalPages - 1 || oPaging.iTotalPages === 0) {
                    $('li:last', an[i]).addClass('disabled');
                } else {
                    $('li:last', an[i]).removeClass('disabled');
                }
            }
        }
    }
});
/*
 * TableTools Bootstrap compatibility
 * Required TableTools 2.1+
 */
if ($.fn.DataTable.TableTools) {
    // Set the classes that TableTools uses to something suitable for Bootstrap
    $.extend(true, $.fn.DataTable.TableTools.classes, {
        "container": "DTTT btn-group",
        "buttons": {
            "normal": "btn",
            "disabled": "disabled"
        },
        "collection": {
            "container": "DTTT_dropdown dropdown-menu",
            "buttons": {
                "normal": "",
                "disabled": "disabled"
            }
        },
        "print": {
            "info": "DTTT_print_info modal"
        },
        "select": {
            "row": "active"
        }
    });
    // Have the collection use a bootstrap compatible dropdown
    $.extend(true, $.fn.DataTable.TableTools.DEFAULTS.oTags, {
        "collection": {
            "container": "ul", "button": "li",
            "liner": "a"
        }
    });
}


/* Table initialisation */
$(document).ready(function() {
    var oTable1 = $('#tablaY1').dataTable({
        "sPaginationType": "bootstrap",
        "oLanguage": {
            "sProcessing": "Procesando...",
            "sLengthMenu": "Mostrar _MENU_ registros",
            "sZeroRecords": "No se encontraron resultados",
            "sEmptyTable": "Ning??n dato disponible en esta tabla",
            "sInfo": "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
            "sInfoEmpty": "Mostrando registros del 0 al 0 de un total de 0 registros",
            "sInfoFiltered": "(filtrado de un total de _MAX_ registros)",
            "sInfoPostFix": "",
            "sSearch": "Buscar:",
            "sUrl": "",
            "sInfoThousands": ",",
            "sLoadingRecords": "Cargando...",
            "oPaginate": {
                "sFirst": "Primero",
                "sLast": "??ltimo",
                "sNext": "Siguiente",
                "sPrevious": "Anterior"
            },
            "oAria": {
                "sSortAscending": ": Activar para ordenar la columna de manera ascendente",
                "sSortDescending": ": Activar para ordenar la columna de manera descendente"
            }
        }
    });
    
    
    var oTable2 = $('#tablaY2').dataTable({
        "sPaginationType": "bootstrap",
        "oLanguage": {
            "sProcessing": "Procesando...",
            "sLengthMenu": "Mostrar _MENU_ registros",
            "sZeroRecords": "No se encontraron resultados",
            "sEmptyTable": "Ning??n dato disponible en esta tabla",
            "sInfo": "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
            "sInfoEmpty": "Mostrando registros del 0 al 0 de un total de 0 registros",
            "sInfoFiltered": "(filtrado de un total de _MAX_ registros)",
            "sInfoPostFix": "",
            "sSearch": "Buscar:",
            "sUrl": "",
            "sInfoThousands": ",",
            "sLoadingRecords": "Cargando...",
            "oPaginate": {
                "sFirst": "Primero",
                "sLast": "??ltimo",
                "sNext": "Siguiente",
                "sPrevious": "Anterior"
            },
            "oAria": {
                "sSortAscending": ": Activar para ordenar la columna de manera ascendente",
                "sSortDescending": ": Activar para ordenar la columna de manera descendente"
            }
        }
    });
    var oTable3 = $('#tablaY3').dataTable({
        "sPaginationType": "bootstrap",
        "oLanguage": {
            "sProcessing": "Procesando...",
            "sLengthMenu": "Mostrar _MENU_ registros",
            "sZeroRecords": "No se encontraron resultados",
            "sEmptyTable": "Ning??n dato disponible en esta tabla",
            "sInfo": "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
            "sInfoEmpty": "Mostrando registros del 0 al 0 de un total de 0 registros",
            "sInfoFiltered": "(filtrado de un total de _MAX_ registros)",
            "sInfoPostFix": "",
            "sSearch": "Buscar:",
            "sUrl": "",
            "sInfoThousands": ",",
            "sLoadingRecords": "Cargando...",
            "oPaginate": {
                "sFirst": "Primero",
                "sLast": "??ltimo",
                "sNext": "Siguiente",
                "sPrevious": "Anterior"
            },
            "oAria": {
                "sSortAscending": ": Activar para ordenar la columna de manera ascendente",
                "sSortDescending": ": Activar para ordenar la columna de manera descendente"
            }
        }
    });
    var oTable4 = $('#tablaY4').dataTable({
        "sPaginationType": "bootstrap",
        "oLanguage": {
            "sProcessing": "Procesando...",
            "sLengthMenu": "Mostrar _MENU_ registros",
            "sZeroRecords": "No se encontraron resultados",
            "sEmptyTable": "Ning??n dato disponible en esta tabla",
            "sInfo": "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
            "sInfoEmpty": "Mostrando registros del 0 al 0 de un total de 0 registros",
            "sInfoFiltered": "(filtrado de un total de _MAX_ registros)",
            "sInfoPostFix": "",
            "sSearch": "Buscar:",
            "sUrl": "",
            "sInfoThousands": ",",
            "sLoadingRecords": "Cargando...",
            "oPaginate": {
                "sFirst": "Primero",
                "sLast": "??ltimo",
                "sNext": "Siguiente",
                "sPrevious": "Anterior"
            },
            "oAria": {
                "sSortAscending": ": Activar para ordenar la columna de manera ascendente",
                "sSortDescending": ": Activar para ordenar la columna de manera descendente"
            }
        }
    });
    var oTable5 = $('#tablaY5').dataTable({
        "sPaginationType": "bootstrap",
        "oLanguage": {
            "sProcessing": "Procesando...",
            "sLengthMenu": "Mostrar _MENU_ registros",
            "sZeroRecords": "No se encontraron resultados",
            "sEmptyTable": "Ning??n dato disponible en esta tabla",
            "sInfo": "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
            "sInfoEmpty": "Mostrando registros del 0 al 0 de un total de 0 registros",
            "sInfoFiltered": "(filtrado de un total de _MAX_ registros)",
            "sInfoPostFix": "",
            "sSearch": "Buscar:",
            "sUrl": "",
            "sInfoThousands": ",",
            "sLoadingRecords": "Cargando...",
            "oPaginate": {
                "sFirst": "Primero",
                "sLast": "??ltimo",
                "sNext": "Siguiente",
                "sPrevious": "Anterior"
            },
            "oAria": {
                "sSortAscending": ": Activar para ordenar la columna de manera ascendente",
                "sSortDescending": ": Activar para ordenar la columna de manera descendente"
            }
        }
    });
    var oTable6 = $('#tablaY6').dataTable({
        "sPaginationType": "bootstrap",
        "oLanguage": {
            "sProcessing": "Procesando...",
            "sLengthMenu": "Mostrar _MENU_ registros",
            "sZeroRecords": "No se encontraron resultados",
            "sEmptyTable": "Ning??n dato disponible en esta tabla",
            "sInfo": "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
            "sInfoEmpty": "Mostrando registros del 0 al 0 de un total de 0 registros",
            "sInfoFiltered": "(filtrado de un total de _MAX_ registros)",
            "sInfoPostFix": "",
            "sSearch": "Buscar:",
            "sUrl": "",
            "sInfoThousands": ",",
            "sLoadingRecords": "Cargando...",
            "oPaginate": {
                "sFirst": "Primero",
                "sLast": "??ltimo",
                "sNext": "Siguiente",
                "sPrevious": "Anterior"
            },
            "oAria": {
                "sSortAscending": ": Activar para ordenar la columna de manera ascendente",
                "sSortDescending": ": Activar para ordenar la columna de manera descendente"
            }
        }
    });
    

    var tableTools1 = new $.fn.dataTable.TableTools(oTable1, {
        "aButtons": [
            {
                "sExtends": "copy",
                "sButtonText": "Copiar al portapapeles",
                "sCopyMessage": "Your custom message would go here."
            },
            {
                "sExtends": "xls",
                "sButtonText": "Exportar a excel"
            },
            {
                "sExtends": "pdf",
                "sButtonText": "Exportar a pdf"
            }
        ]
        , "sSwfPath": "media/swf/copy_csv_xls_pdf.swf"
    });
    var tableTools2 = new $.fn.dataTable.TableTools(oTable2, {
        "aButtons": [
            {
                "sExtends": "copy",
                "sButtonText": "Copiar al portapapeles",
                "sCopyMessage": "Your custom message would go here."
            },
            {
                "sExtends": "xls",
                "sButtonText": "Exportar a excel"
            },
            {
                "sExtends": "pdf",
                "sButtonText": "Exportar a pdf"
            }
        ]
        , "sSwfPath": "media/swf/copy_csv_xls_pdf.swf"
    });
    var tableTools3 = new $.fn.dataTable.TableTools(oTable3, {
        "aButtons": [
            {
                "sExtends": "copy",
                "sButtonText": "Copiar al portapapeles",
                "sCopyMessage": "Your custom message would go here."
            },
            {
                "sExtends": "xls",
                "sButtonText": "Exportar a excel"
            },
            {
                "sExtends": "pdf",
                "sButtonText": "Exportar a pdf"
            }
        ]
        , "sSwfPath": "media/swf/copy_csv_xls_pdf.swf"
    });
    var tableTools4 = new $.fn.dataTable.TableTools(oTable4, {
        "aButtons": [
            {
                "sExtends": "copy",
                "sButtonText": "Copiar al portapapeles",
                "sCopyMessage": "Your custom message would go here."
            },
            {
                "sExtends": "xls",
                "sButtonText": "Exportar a excel"
            },
            {
                "sExtends": "pdf",
                "sButtonText": "Exportar a pdf"
            }
        ]
        , "sSwfPath": "media/swf/copy_csv_xls_pdf.swf"
    });
    var tableTools5 = new $.fn.dataTable.TableTools(oTable5, {
        "aButtons": [
            {
                "sExtends": "copy",
                "sButtonText": "Copiar al portapapeles",
                "sCopyMessage": "Your custom message would go here."
            },
            {
                "sExtends": "xls",
                "sButtonText": "Exportar a excel"
            },
            {
                "sExtends": "pdf",
                "sButtonText": "Exportar a pdf"
            }
        ]
        , "sSwfPath": "media/swf/copy_csv_xls_pdf.swf"
    });
    var tableTools6 = new $.fn.dataTable.TableTools(oTable6, {
        "aButtons": [
            {
                "sExtends": "copy",
                "sButtonText": "Copiar al portapapeles",
                "sCopyMessage": "Your custom message would go here."
            },
            {
                "sExtends": "xls",
                "sButtonText": "Exportar a excel"
            },
            {
                "sExtends": "pdf",
                "sButtonText": "Exportar a pdf"
            }
        ]
        , "sSwfPath": "media/swf/copy_csv_xls_pdf.swf"
    });
    $("<div id='tools1' class='span9'></div>").prependTo('#tablaY1_wrapper');
    $(".DTTT_button_pdf").add
    $(tableTools1.fnContainer()).prependTo('#tools1');
    
    $("<div id='tools2' class='span9'></div>").prependTo('#tablaY2_wrapper');
    $(".DTTT_button_pdf").add
    $(tableTools2.fnContainer()).prependTo('#tools2');
    
    $("<div id='tools3' class='span9'></div>").prependTo('#tablaY3_wrapper');
    $(".DTTT_button_pdf").add
    $(tableTools3.fnContainer()).prependTo('#tools3');
    
    $("<div id='tools4' class='span9'></div>").prependTo('#tablaY4_wrapper');
    $(".DTTT_button_pdf").add
    $(tableTools4.fnContainer()).prependTo('#tools4');
    
    $("<div id='tools5' class='span9'></div>").prependTo('#tablaY5_wrapper');
    $(".DTTT_button_pdf").add
    $(tableTools5.fnContainer()).prependTo('#tools5');
    
    $("<div id='tools6' class='span9'></div>").prependTo('#tablaY6_wrapper');
    $(".DTTT_button_pdf").add
    $(tableTools6.fnContainer()).prependTo('#tools6');
    
});


