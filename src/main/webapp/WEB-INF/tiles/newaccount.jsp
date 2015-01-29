<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<sf:form action="${pageContext.request.contextPath}/createaccount" method="post" commandName="user" id="details">
    <table class="formtable">
        <tr>
            <td class="label">Username:</td>
            <td><sf:input class="control" name="username" type="text" path="username"/><br/>

                <div class="error"><sf:errors path="username"></sf:errors></div>
            </td>
        </tr>
        <tr>
            <td class="label">Name:</td>
            <td><sf:input class="control" name="name" type="text" path="name"/><br/>

                <div class="error"><sf:errors path="name"></sf:errors></div>
            </td>
        </tr>
        <tr>
            <td class="label">Password:</td>
            <td><sf:input class="control" id="password" name="password" type="password" path="password"/><br/>
                <div class="error"><sf:errors path="password"/></div>
            </td>
        </tr>
        <tr>
            <td class="label">Confirm Password:</td>
            <td><input class="control" id="confirmpass" name="confirmpass" type="password"/><div id="matchpass" ></div></td>
        </tr>
        <tr>
            <td class="label"></td>
            <td><input class="control" value="Create account" type="submit"/></td>
        </tr>
    </table>
</sf:form>
