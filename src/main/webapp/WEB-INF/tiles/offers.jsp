<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="${pageContext.request.contextPath}/static/css/main.css" rel="stylesheet" type="text/css" />

<table class="offers">
<tr><td>Name</td>
  <%--<td>Email</td>--%>
  <td>Text</td></tr>
<c:forEach var="offer" items="${offerList}">
  <%--Id = ${offer.id}--%>
  <%--<c:out value="${item.id}"></c:out><br/>--%>
  <tr>
  <td><c:out value="${offer.user.name}"></c:out></td>
  <%--<td><c:out value="${offer.user.email}"></c:out></td>--%>
  <td><c:out value="${offer.text}"></c:out></td>
  </tr>
</c:forEach>
<br/>
</table>