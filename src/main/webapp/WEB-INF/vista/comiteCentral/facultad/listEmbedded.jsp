<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<option></option>
<c:forEach items="${listaF}" var="facultad" varStatus="iter">
    <option value="${facultad.id}">${facultad.nombre}</option>
</c:forEach>
