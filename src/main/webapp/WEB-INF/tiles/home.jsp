<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<%--<table class="offers">--%>
	<%--<tr><td>Name</td>--%>
		<%--&lt;%&ndash;<td>Email</td>&ndash;%&gt;--%>
		<%--<td>Text</td></tr>--%>
	<%--<c:forEach var="offer" items="${offerList}">--%>
		<%--&lt;%&ndash;Id = ${offer.id}&ndash;%&gt;--%>
		<%--&lt;%&ndash;<c:out value="${item.id}"></c:out><br/>&ndash;%&gt;--%>
		<%--<tr>--%>
			<%--<td><c:out value="${offer.user.name}"></c:out></td>--%>
			<%--&lt;%&ndash;<td><c:out value="${offer.user.email}"></c:out></td>&ndash;%&gt;--%>
			<%--<td><c:out value="${offer.text}"></c:out></td>--%>
		<%--</tr>--%>
	<%--</c:forEach>--%>
	<%--<br/>--%>
<%--</table>--%>

<%--<c:choose>--%>
	<%--<c:when test="${hasOffers==true}">--%>
		<%--<p><a href="${pageContext.request.contextPath}/createoffer">Edit your offer.</a></p>--%>
	<%--</c:when>--%>
	<%--<c:otherwise>--%>
		<%--<p><a href="${pageContext.request.contextPath}/createoffer">Add a new offer.</a></p>--%>
	<%--</c:otherwise>--%>
<%--</c:choose>--%>


<table class="offers">
	<tr><td></td>
		<td></td></tr>
	<c:forEach var="purchasedItem" items="${purchasedItemList}">
		<tr>
			<td><a href="${purchasedItem.itemURL}"/><img src="${purchasedItem.itemPicURL}"/></td>
			<td><c:out value="${purchasedItem.itemName}"></c:out></td>
			<%--<td><c:out value="${purchasedItem.itemURL}"></c:out></td>--%>
		</tr>
	</c:forEach>
	<br/>
</table>

<c:choose>
	<c:when test="${hasItems==true}">
		<p><a href="${pageContext.request.contextPath}/emailform">Edit your email.</a></p>
	</c:when>
	<c:otherwise>
		<p><a href="${pageContext.request.contextPath}/emailform">Add a new email.</a></p>
	</c:otherwise>
</c:choose>

<sec:authorize access="hasRole('ROLE_ADMIN')">
	<p><a href="<c:url value='/admin'/>">Admin</a></p>
</sec:authorize>
