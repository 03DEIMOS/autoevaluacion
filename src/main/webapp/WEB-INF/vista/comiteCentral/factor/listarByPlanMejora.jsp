
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<option></option>
<c:forEach items="${listaF}" var="factor" varStatus="iter">
    <option value="${factor.id}">${factor.codigo} ${factor.nombre}</option>
</c:forEach>