<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="hero-unit">
    <div class="row">
        <div id="conte" class="span10">

            <h3 style="margin: 0;">Listado de Tipos de Pregunta</h3>
            <c:choose>
                <c:when test="${fn:length(listaTipoP)!= 0}">

                    <table id="tablaX" class="table table-striped table-bordered table-condensed">
                        <thead>
                        <th class="span1">Tipo;digo</th>    
                        <th class="span8">Descripci&oacute;n</th>
                        <th class="span1">Acci&oacute;n</th>
                        </thead>
                        <tbody>
                            <c:forEach items="${listaTipoP}" var="tipopregunta" varStatus="iter">
                                <tr>
                                    <td>   
                                        <c:out value="${tipopregunta.tipo}"/>
                                    </td>
                                    <td>   
                                        <c:out value="${tipopregunta.descripcion}"/>
                                    </td>
                                    <td class="action span2">
                                        <a href="#tipopregunta/editar/${tipopregunta.id}" title="Editar"><i class="icon-edit"></i></a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <script type="text/javascript">
                        $(document).ready(function() {
                            $('#tablaX').DataTable({
                                dom: 'Bfrtip',
                                buttons: [
                                    {
                                        extend: 'copy',
                                        text: 'Copiar'
                                    },
                                    {
                                        extend: 'excelHtml5',
                                        text: 'Exportar a excel',
                                        title: 'Preguntas'
                                    }
                                ],
                                language: {
                                    buttons: {
                                        copyTitle: 'Copiar'
                                    }
                                }
                            });
                        });
                    </script>
                </c:when>
                <c:otherwise>
                    No existen tipos de pregunta registradas en el sistema.
                </c:otherwise>
            </c:choose>
            <br/>
            <a href="#tipopregunta/crear" class="btn btn-large btn-primary llamador"><i class="icon-plus"></i> Crear tipo de pregunta</a>

        </div>
    </div>
</div>    

