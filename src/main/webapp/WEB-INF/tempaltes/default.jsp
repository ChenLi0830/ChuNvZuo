<%--
  Created by IntelliJ IDEA.
  User: Chen
  Date: 15-01-22
  Time: 7:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>


<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">

  <title><tiles:insertAttribute name="title"></tiles:insertAttribute></title>

  <link href="${pageContext.request.contextPath}/static/css/main.css" rel="stylesheet" type="text/css"/>
  <script type="text/javascript" src="${pageContext.request.contextPath}/static/script/jquery-1.11.2.min.js"></script>

  <tiles:insertAttribute name="includes"/>

</head>
<body>

<div>

  <div class="header">
  <tiles:insertAttribute name="header"/>
  </div>

  <div class="content">
  <tiles:insertAttribute name="content"/>
  </div>

  <hr/>
  <div class="footer">
  <tiles:insertAttribute name="footer"/>
  </div>
</div>



</body>
</html>
