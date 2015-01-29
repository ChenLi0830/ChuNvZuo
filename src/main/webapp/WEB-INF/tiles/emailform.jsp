<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>


<sf:form action="${pageContext.request.contextPath}/addemail" method="post" commandName="email">
    <table class="formtable">
        <tr>
            <td class="label">Account:</td>
            <td><sf:input class="control" name="account" type="text" path="account"/><br/>

                <div class="error"><sf:errors path="account"></sf:errors></div>
            </td>
        </tr>
        <tr>
            <td class="label">Password:</td>
            <td><sf:input class="control" id="password" name="password" type="password" path="password"/><br/>
                <div class="error"><sf:errors path="password"/></div>
            </td>
        </tr>
        <tr>
            <td class="label"></td>
            <td><input class="control" value="Add email" type="submit"/></td>
        </tr>
    </table>
</sf:form>
