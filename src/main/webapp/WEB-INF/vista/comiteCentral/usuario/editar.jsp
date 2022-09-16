<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/bootstrap-tagsinput.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/typeaheadjs.css" />
<style type="text/css">
    .twitter-typeahead .tt-query,
    .twitter-typeahead .tt-hint {
        margin-bottom: 0;
    }

    .twitter-typeahead .tt-hint
    {
        display: none;
    }
    
    .tt-dropdown-menu {
        position: absolute;
        top: 100%;
        left: 0;
        z-index: 1000;
        display: none;
        float: left;
        min-width: 160px;
        padding: 5px 0;
        margin: 2px 0 0;
        list-style: none;
        font-size: 14px;
        background-color: #ffffff;
        border: 1px solid #cccccc;
        border: 1px solid rgba(0, 0, 0, 0.15);
        border-radius: 4px;
        -webkit-box-shadow: 0 6px 12px rgba(0, 0, 0, 0.175);
        box-shadow: 0 6px 12px rgba(0, 0, 0, 0.175);
        background-clip: padding-box;
    }
    .tt-suggestion > p {
        display: block;
        padding: 3px 20px;
        clear: both;
        font-weight: normal;
        line-height: 1.428571429;
        color: #333333;
        white-space: nowrap;
    }
    .tt-suggestion > p:hover,
    .tt-suggestion > p:focus,
    .tt-suggestion.tt-cursor p {
        color: #ffffff;
        text-decoration: none;
        outline: 0;
        background-color: #428bca !important;
    }
</style>
<script src="<%=request.getContextPath()%>/js/typeahead.bundle.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap-tagsinput.min.js"></script>
<script type="text/javascript">
    $(function() {

    var programas = new Bloodhound({
    datumTokenizer: Bloodhound.tokenizers.obj.whitespace('text'),
            queryTokenizer: Bloodhound.tokenizers.whitespace,
            local: [
    <c:forEach items="${programas}" var="programa" varStatus="status">
        <c:choose>
            <c:when test="${(status.index+1) != programas.size()}">
            {
            value: '${programa.id}',
                    text: '${programa.nombre}'
            },</c:when><c:otherwise>
            {
            value: '${programa.id}',
                    text: '${programa.nombre}'
            }
            </c:otherwise>
        </c:choose>
    </c:forEach>
            ]
    });
    programas.initialize();
    var elt = $('#inputProgramas');
    elt.tagsinput({
    itemValue: 'value',
            itemText: 'text',
            typeaheadjs: {
            name: 'programas',
                    displayKey: 'text',
                    source: programas.ttAdapter()
            }
    });
        
        <c:forEach items="${usuario.programaList}" var="programa">
            elt.tagsinput('add', { value: ${programa.id}, text: '${programa.nombre}' });
            </c:forEach>
        
        $.validator.addMethod('positiveNumber',
                function (value) {
                    return (Number(value) > 0) && (value == parseInt(value, 10));
                }, 'Ingrese un numero entero positivo.');

        $("#formEditarUsuario").validate({
            submitHandler: function () {
                $.ajax({
                    type: 'PUT',
                    url: "/autoevaluacion/usuario/editar",
                    data: $("#formEditarUsuario").serialize(),
                    success: function () {
                        location.hash = "usuario/usuarios";
                    } //fin success
                }); //fin $.ajax

            }
        });
        
        $("#tipo").change(function () {
            var selected = $("#tipo option:selected").val();
            elt.tagsinput('removeAll');
            if(selected == "Administrador" ){
                $("#programas").hide();
            }else{
                $("#programas").show();
            }
        });
    });
</script>
<div class="hero-unit">
    <div class="row">
        <ul class="breadcrumb">
            <li><a href="#usuario/usuarios" >Usuarios</a><span class="divider">/</span></li>
            <li>Editar</li>
        </ul>
        <div id="conte" class="span10">
            <form id="formEditarUsuario" class="form-horizontal" method="post">
                <fieldset>
                    <legend>Editar Usuario</legend>
                    <div class="control-group">
                        <label for="codigox"  class="control-label">C&oacute;digo</label>
                        <div class="controls">
                            <input disabled="" type="text" name="codigox" id="codigox" class="input-xlarge uneditable-input {required:true, positiveNumber:true}" value="${usuario.usuario}"/>
                            <input type="hidden" name="codigo" value="${usuario.usuario}"/>
                            <input type="hidden" name="usuarioId" value="${usuario.id}"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="identificacion"  class="control-label">Identificacion</label>
                        <div class="controls">
                            <input type="text" name="identificacion" id="identificacion" class="input-xlarge {required:true, positiveNumber:true}" value="${usuario.identificacion}"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="nombre"  class="control-label">Nombres</label>
                        <div class="controls">
                            <input type="text" name="nombre" id="nombre" class="input-xlarge {required:true}" value="${usuario.nombre}"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="apellidos"  class="control-label">Apellidos</label>
                        <div class="controls">
                            <input type="text" name="apellidos" id="apellidos" class="input-xlarge {required:true}" value="${usuario.apellido}"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <label for="email"  class="control-label">Correo electr&oacute;nico</label>
                        <div class="controls">
                            <input type="text" name="email" id="email" class="input-xlarge" value="${usuario.email}"/>
                        </div>
                    </div>
                        
                            <div class="control-group">
                                <label for="tipo"  class="control-label">Tipo</label>
                                <div class="controls">
                                    <select id="tipo" name="tipo" class="{required:true}">
                                        <option></option>
                                        <option value="Administrador" 
                                                <c:if test="${usuario.tipo == 'Administrador'}">
                                                    selected="selected"
                                                </c:if>     
                                                >Administrador</option>
                                        <option value="Decano"
                                                <c:if test="${usuario.tipo == 'Decano'}">
                                                    selected="selected"
                                                </c:if>     
                                                >Decano</option>
                                        <option value="Director de Programa"
                                                <c:if test="${usuario.tipo == 'Director de Programa'}">
                                                    selected="selected"
                                                </c:if>     
                                                >Director de Programa</option>
                                    </select>  
                                </div>
                            </div>    
                        
                    <div id="programas" class="control-group">
                        <label for="programas"  class="control-label">Programa</label>
                        <div class="controls">
                            <input type="text" name="programas" id="inputProgramas"/>
                        </div>
                    </div>
                    <div class="form-actions">
                        <button class="btn btn-primary" type="submit">Editar Usuario</button>
                        <button class="btn" type="reset">Cancelar</button>
                    </div>
                </fieldset>
            </form>
        </div><!--/span-->        
    </div><!--/row-->    
</div>