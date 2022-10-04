<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<option></option>
<c:forEach items="${listaP}" var="programa" varStatus="iter">
    <option value="${programa.id}">${programa.nombre}</option>
</c:forEach>
