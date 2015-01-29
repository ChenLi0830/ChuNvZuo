<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


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

<c:choose>
	<c:when test="${hasOffers==true}">
		<p><a href="${pageContext.request.contextPath}/createoffer">Edit your offer.</a></p>
	</c:when>
	<c:otherwise>
		<p><a href="${pageContext.request.contextPath}/createoffer">Add a new offer.</a></p>
	</c:otherwise>
</c:choose>

<sec:authorize access="hasRole('ROLE_ADMIN')">
	<p><a href="<c:url value='/admin'/>">Admin</a></p>
</sec:authorize>
