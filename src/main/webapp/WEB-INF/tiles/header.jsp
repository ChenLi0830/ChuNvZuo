<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<%--<a class="title" href="<c:url value='/'/>">Offers</a>--%>
<nav class="navbar navbar-default navbar-virgo  navbar-static-top">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">
                <img alt="Brand" class="logo-container" src="/static/images/ChuNvZuoLogo_white.png">
            </a>

            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#navbar-collapse1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>

        </div>

        <div id="navbar-collapse1" class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <a class="" href="#">FEATURES</a>
                </li>

                <sec:authorize access="!isAuthenticated()">
                    <li>
                        <a class="" href="<c:url value='/login'/>">SIGN IN</a>
                    </li>
                </sec:authorize>

                <li>
                    <a id="SignUp" class="btn-trans btn btn-virgo" href="#">SIGN UP</a>
                </li>


                <sec:authorize access="isAuthenticated()">
                    <li>
                        <a class="" href="<c:url value='/j_spring_security_logout'/>">SIGN OUT</a>
                    </li>
                </sec:authorize>

            </ul>
        </div>
    </div>
</nav>

<div class="">
    <div class="row text-left">
        <%--<div class="col-xs-12 col-sm-12 col-md-8 col-md-offset-0 col-lg-6 col-lg-offset-1 ">--%>
            <div class="col-xs-12 col-lg-6 col-lg-offset-0 ">
            <div class="header-content">
                <h1 class="">
                    The electronics you love
                </h1>

                <h2 class="subheading">at price you can’t resist.</h2>

            </div>
        </div>
        <div class="col-lg-5"></div>
    </div>
</div>