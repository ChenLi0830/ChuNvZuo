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


  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
  <!-- Latest compiled and minified CSS -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

  <!-- Optional theme -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

  <link href="${pageContext.request.contextPath}/static/css/main.css" rel="stylesheet" type="text/css"/>

  <tiles:insertAttribute name="includes"/>

</head>
<body id="<tiles:insertAttribute name="bodyId"/>">

<%--<tiles:insertAttribute name="bodyId"/>--%>

  <header class="header">
  <tiles:insertAttribute name="header"/>
  </header>

  <div class="content">
  <tiles:insertAttribute name="content"/>
  </div>

  <hr/>
  <div class="footer">
  <tiles:insertAttribute name="footer"/>
  </div>



  <!-- Latest compiled and minified JavaScript -->


  <%--<script type="text/javascript" src="${pageContext.request.contextPath}/static/script/jquery-1.11.2.min.js"></script>--%>

</body>
</html>
