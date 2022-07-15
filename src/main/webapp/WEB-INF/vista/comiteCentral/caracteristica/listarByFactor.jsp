<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<option></option>
<c:forEach items="${listaC}" var="caracteristica" varStatus="iter">
    <option value="${caracteristica.id}">${caracteritica.codigo} ${caracteristica.nombre}</option>
</c:forEach>
