<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/fcbklistselection.css">
<script src="<%=request.getContextPath()%>/js/fcbklistselection.js" type="text/javascript"></script>
<script type="text/javascript">
    $(function () {
        //id(ul id),width,height(element height),row(elements in row)        
        var $fcbklist = $('#fcbklist');
        var $listItems = $fcbklist.find('li');

        $.fcbkListSelection("#fcbklist", "600", "50", "3");

        $(".clearer").before('<input type="text" id="filter" class="input-medium search-query" placeholder="Buscar" style="padding-top: 0px; padding-bottom: 0px; float: right; border-right-width: 1px; padding-right: 14px; margin-right: 35px;">');

        $('#filter').keyup(function () {
            var $this = $(this);

            var val = $this.val();

            if (!val) {
                $this.data('lastVal', val);
                var $tabItems2;
                switch ($(".view_on").attr("id").replace("view_", "")) {
                    case "all":
                        $tabItems2 = $listItems;
                        break;
                    case "selected":
                        $tabItems2 = $listItems.filter('[addedid]');
                        break;
                    case "unselected":
                        $tabItems2 = $listItems.filter(':not([addedid])');
                        break;
                }
                $tabItems2.show();
                return;
            }

            var lastVal = $this.data('lastVal');
            $this.data('lastVal', val);
            /*** If the filter hasn't changed, do nothing ***/
            if (val === lastVal) {
                return;
            }

            /*** Hide the results of the previous filter ***/
            $listItems.filter(':visible').hide();

            /*** 
             Show only the items of the current tab that match 
             the filter. 
             ***/
            var $tabItems;
            switch ($(".view_on").attr("id").replace("view_", "")) {
                case "all":
                    $tabItems = $listItems;
                    break;
                case "selected":
                    $tabItems = $listItems.filter('[addedid]');
                    break;
                case "unselected":
                    $tabItems = $listItems.filter(':not([addedid])');
                    break;
            }
            $tabItems.filter(':icontains(' + val + ')').show();
        });

        /*** 
         This is a custom pseudo-selector that selects 
         elements whose text contains the specified substring. 
         It is case-insensitive, unlike the built-in :contains selector. 
         ***/
        $.extend($.expr[':'], {
            icontains: function (elem, i, match) {
                return (new RegExp(match[3], 'im')).test($(elem).text());
            }
        });

        $("#formCrearEncuesta").validate({
            submitHandler: function () {
                $.ajax({
                    type: 'POST',
                    url: "/autoevaluacion/encuesta/crear",
                    data: $("#formCrearEncuesta").serialize(),
                    success: function () {
                        location.hash = "encuesta/encuestas";
                    } //fin success
                }); //fin $.ajax    
            }
        });
        var removeValue = function (obj) {
            var randid = obj.find("[type=hidden]").attr("randid");
            var inputid = elem.attr('id') + "_values";
            if ($("#" + inputid).length != 0) {
                try {
                    eval("json = " + $("#" + inputid).val() + ";");
                    var string = "{";
                    $.each(json, function (i, item) {
                        if (i && item && i != randid) {
                            string += "\"" + i + "\":\"" + item + "\",";
                        }
                    });
                    //remove last ,
                    if (string.length > 2) {
                        string = string.substr(0, (string.length - 1));
                        string += "}"
                    } else {
                        string = "";
                    }
                    $("#" + inputid).val(string);
                } catch (e) {
                }
            }
        }
        var seleccionados = [];
        elem = $("#fcbklist");
        $.each(elem.children("li").children(".fcbklist_item"), function (i, obj) {
            obj = $(obj);
            if (obj.hasClass("itemselected")) {
                seleccionados.push(obj.find("[type=hidden]").attr("randid"));
            }

        })


        $("button[type='reset']").click(function () {

            elem = $("#fcbklist");
            $.each(elem.children("li").children(".fcbklist_item"), function (i, obj) {
                obj = $(obj);
                var encontrado = false;
                for (var i = 0; i < seleccionados.length && !encontrado; i++)
                {
                    if (obj.find("[type=hidden]").attr("randid") == seleccionados[i]) {
                        encontrado = true;

                    }

                }
                if (!encontrado) {
                    if (obj.hasClass("itemselected")) {
                        obj.find("input:hidden").val("0");
                        $("#view_selected_count").text(parseInt($("#view_selected_count").text(), 10) - 1);
                        obj.parents("li").removeAttr("addedid");
                        //removeValue(obj);
                        obj.removeClass("itemselected");
                        obj.parents("li").removeClass("liselected");
                    }

                } else {
                    if (!obj.hasClass("itemselected")) {
                        obj.find("input:hidden").val("1");
                        $("#view_selected_count").text(parseInt($("#view_selected_count").text(), 10) + 1);
                        obj.parents("li").attr("addedid", "tester");
                        obj.addClass("itemselected");
                        obj.parents("li").addClass("liselected");
                    }

                }
            })
        })        
    });
</script>
<div class="hero-unit">
    <div class="row">
        <div id="conte" class="span10">
            <form id="formCrearEncuesta" class="form-horizontal" method="post">
                <fieldset>
                    <legend>Crear Encuesta</legend>
                    <div class="control-group">
                        <label for="nombre" class="control-label">Nombre</label>
                        <div class="controls">
                            <input type="text" name="nombre" id="nombre" class="input-xlarge {required:true}" value=""/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="objetivo" class="control-label">Objetivo</label>
                        <div class="controls">
                            <textarea rows="3" name="objetivo" id="objetivo" class="input-xxlarge {required:true}"></textarea>
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="instrucciones" class="control-label">Instrucciones</label>
                        <div class="controls">
                            <textarea rows="6" name="instrucciones" id="instrucciones" class="input-xxlarge {required:true}"></textarea>
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="codigo" class="control-label">C&oacute;digo</label>
                        <div class="controls">
                            <input type="text" name="codigo" id="codigo" class="input-xlarge {required:true}" value=""/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="version" class="control-label">Versi&oacute;n</label>
                        <div class="controls">
                            <input type="text" name="version" id="version" class="input-xlarge {required:true}" value=""/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="fecha" class="control-label">Fecha</label>
                        <div class="controls">
                            <input type="text" name="fecha" id="fecha" class="input-xlarge {required:true}" value=""/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="fuenteId" class="control-label">Asignar P&uacute;blico objetivo</label>
                        <div class="controls">
                            <select id="fuenteId" name="fuenteId" class="{required:true}">
                                <option value=""></option>    
                                <c:forEach items="${listaFu}" var="item" varStatus="iter">
                                    <option value="${item.id}">${item.nombre}</option>    
                                </c:forEach>
                            </select>                
                        </div>
                    </div>
                    <div class="control-group" id="preguntas">
                        <label for="fcbklist" class="control-label">Asignar Preguntas</label>
                        <div class="controls">
                            <ul id="fcbklist">
                                <c:forEach items="${listaP}" var="row" varStatus="iter">
                                    <li>
                                        <strong>${row.codigo}</strong><br/> 
                                        <span class="fcbkitem_text">${row.pregunta}</span>
                                        <input name="P${row.id}" type="hidden" value="0"/>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>             
                    <div class="form-actions">
                        <button class="btn btn-primary" type="submit">Crear Encuesta</button>
                        <button class="btn" type="reset">Cancelar</button>
                    </div>
                </fieldset>
            </form>
        </div><!--/span-->        
    </div><!--/row-->    
</div>