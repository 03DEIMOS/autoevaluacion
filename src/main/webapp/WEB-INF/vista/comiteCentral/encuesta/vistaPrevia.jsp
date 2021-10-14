<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link media="print" href="<%=request.getContextPath()%>/css/print.css" rel="stylesheet"/>
<style type="text/css">
    @media all {
        div.saltopagina{
            display: none;
        }
        .insp{
            line-height: 22px;
            text-align: justify;
        }

        .span5{
            width: 360px;
            font-size: 14px;
            font-family: "Helvetica Neue",​Helvetica,​Arial,​sans-serif;
        }
    } 


</style>

<a class="span10" style="text-align: right;  margin-left: 60px; cursor: pointer;" id="printEnlace"><i class="icon-print"></i> Imprimir</a>  
<br>
<div class="hero-unit" id="toPrint">
    <div>
        <div id="conte" class="span10" style="text-align: justify">
            <div class="row">
                <table class="table table-bordered table-striped" style="font-weight: bold;">
                    <tbody>
                        <tr>
                            <td rowspan="2" style="width: 25%; text-align: center;"><img src="/autoevaluacion/img/LogoUTB.png"></td>
                            <td style="width: 50%; text-align: center;">UNIVERSIDAD TECNOLÓGICA DE BOLÍVAR</td>
                            <td rowspan="2" style="width: 25%; text-align: center;"><img src="/autoevaluacion/img/CalidadUTB.jpg"></td>
                        </tr>
                        <tr>
                            <td style="width: 50%; text-align: center;">${encuesta.getNombre()}</td>
                        </tr>
                    </tbody>
                </table>
                <br/>
                <p class="insp">${encuesta.getObjetivo()}</p>
                <p class="insp">${encuesta.getInstrucciones()}</p>
                <br/>
            </div>

            <c:forEach items="${encuesta.getPreguntaList()}" var="pregunta" varStatus="status">

                <div class="row" id="pregunta${pregunta.id}">
                    <div class="span10">
                        <p style="font-weight: bold;">${status.index+1} ${pregunta.getPregunta()}</p>
                        <c:choose>
                            <c:when test="${pregunta.disenio=='Vertical' && fn:length(pregunta.getTipoPregunta().getItemTipoPreguntaList())!= 0}">
                                <c:forEach items="${pregunta.getTipoPregunta().getItemTipoPreguntaList()}" var="tipoPregunta" varStatus="iter">
                                    <c:out escapeXml="false" value='<label class="radio"><input type="radio" name="pregunta.id">${tipoPregunta.respuesta}</label>'/>
                                </c:forEach>
                            </c:when>
                            <c:when test="${pregunta.disenio=='Horizontal' && fn:length(pregunta.getTipoPregunta().getItemTipoPreguntaList())!= 0}">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <c:forEach items="${pregunta.getTipoPregunta().getItemTipoPreguntaList()}" var="tipoPregunta" varStatus="iter">
                                                <c:out escapeXml="false" value='<th>${tipoPregunta.respuesta}</th>'/>
                                            </c:forEach>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <c:forEach items="${pregunta.getTipoPregunta().getItemTipoPreguntaList()}" var="tipoPregunta" varStatus="iter">
                                                <c:out escapeXml="false" value='<td><label class="radio" ><input type="radio" name="${pregunta.id}"></label></td>'/>
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
                                                <c:forEach items="${pregunta.getTipoPregunta().getItemTipoPreguntaList()}" var="tipoPregunta" varStatus="iter">
                                                    <c:out escapeXml="false" value='<th>${tipoPregunta.respuesta}</th>'/>
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
                                                                <c:forEach items="${pregunta.getTipoPregunta().getItemTipoPreguntaList()}" var="tipoPregunta2" varStatus="iter">
                                                                    <c:out escapeXml="false" value='<td><label class="radio"><input type="radio" name="${itemPregunta.id}" value="${tipoPregunta2.id}"/></label></td>'/>
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
                                                            <select name="respuestas">
                                                                <option value=""></option>    
                                                                <c:forEach items="${pregunta.getTipoPregunta().getItemTipoPreguntaList()}" var="tipoPregunta2" varStatus="iter">
                                                                    <option value="${tipoPregunta2.id}">${tipoPregunta2.respuesta}</option>    
                                                                </c:forEach>
                                                            </select> 
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>    
                                    </c:when>
                                    <c:otherwise>
                                        <select name="respuestas">
                                            <option value=""></option>    
                                            <c:forEach items="${pregunta.getTipoPregunta().getItemTipoPreguntaList()}" var="tipoPregunta" varStatus="iter">
                                                <option value="${tipoPregunta.id}">${tipoPregunta.respuesta}</option>    
                                            </c:forEach>
                                        </select> 
                                    </c:otherwise>
                                </c:choose>
                            </c:when>
                        </c:choose>
                    </div> 
                </div>
            </c:forEach>  

        </div>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        $("p").html(function (busca, reemplaza) {
            return reemplaza.replace('_PROGRAMA_', 'Administración');
        });
        $("#spanprograma").html(function (busca, reemplaza) {
            return reemplaza.replace('_PROGRAMA_', 'Administración');
        });


        setTimeout(function () {
            $("#printEnlace").click(function () {
                var contents = $("#toPrint").html();
                var frame1 = $('<iframe />');
                frame1[0].name = "frame1";
                frame1.css({"position": "absolute", "top": "-1000000px"});
                $("body").append(frame1);
                var frameDoc = frame1[0].contentWindow ? frame1[0].contentWindow : frame1[0].contentDocument.document ? frame1[0].contentDocument.document : frame1[0].contentDocument;
                frameDoc.document.open();
                //Create a new HTML document.
                frameDoc.document.write('<html><head><title>${encuesta.getNombre()}</title>');
                frameDoc.document.write('</head><body>');
                //Append the external CSS file.
                frameDoc.document.write('<link href="css/print.css" rel="stylesheet" type="text/css" />');
                //Append the DIV contents.
                frameDoc.document.write(contents);
                frameDoc.document.write('</body></html>');
                frameDoc.document.close();
                setTimeout(function () {
                    window.frames["frame1"].focus();
                    window.frames["frame1"].print();
                    frame1.remove();
                }, 500);
            });
        }, 1000);

    });
</script>