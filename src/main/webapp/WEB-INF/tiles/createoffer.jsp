<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<script>
  function onDeleteClick(event){
    var doDelete = confirm("Are you sure you want to delete this offer?");
    if (doDelete == false) {
      event.preventDefault();
    }
  }

  function onReady(){
    $("#delete").click(onDeleteClick);
  }

  $(document).ready(onReady);
</script>

<sf:form action="${pageContext.request.contextPath}/docreate" method="post" commandName="offer">
  <sf:input path="id" type="hidden" name="id"/>

  <table class="formtable">
    <tr><td class="label">Your offer: </td><td><sf:textarea class="control"  name="text"  rows="10" cols="10" path="text"></sf:textarea><br/><sf:errors path="text" cssClass="error"/></td></tr>
    <tr><td class="label"> </td><td><input class="control"  value="Save advert" type="submit" /></td></tr>

    <c:if test="${offer.id !=0}">
    <tr><td class="label"> </td><td><input class="control" name="delete" id="delete" value="Delete advert" type="submit" /></td></tr>
    </c:if>
  </table>
</sf:form>
