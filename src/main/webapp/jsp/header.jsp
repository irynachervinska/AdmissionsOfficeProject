<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
</head>
<body>
<div class="header"></div>
<input type="checkbox" class="openSidebarMenu" id="openSidebarMenu">
<label for="openSidebarMenu" class="sidebarIconToggle">
    <div class="spinner diagonal part-1"></div>
    <div class="spinner horizontal"></div>
    <div class="spinner diagonal part-2"></div>
</label>
<div id="sidebarMenu">
    <ul class="sidebarMenuInner">
        <li><% if (session.getAttribute("firstName") == null) { %>
            <p><spring:message code="welcome"/></p>
            <% } else {%>
            <p><%=session.getAttribute("firstName")%> <%=session.getAttribute("lastName")%>
            </p>
            <% } %>

            <span><c:out value="${pageContext.request.remoteUser}"></c:out></span></li>


        <sec:authorize access="!isAuthenticated()">
            <li><a href="${pageContext.request.contextPath}/login"><spring:message code="login"/></a></li>
        </sec:authorize>


        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <li><a href="${pageContext.request.contextPath}/userProfile"><spring:message code="profile"/></a></li>
            <li><a href="${pageContext.request.contextPath}/subject"><spring:message
                    code="manipulating-the-subjects"/></a></li>
            <li><a href="${pageContext.request.contextPath}/faculty"><spring:message
                    code="manipulating-the-faculties"/></a></li>
            <li><a href="${pageContext.request.contextPath}/statementsToAccept"><spring:message
                    code="statements-to-accept"/></a></li>
        </sec:authorize>

        <sec:authorize access="hasRole('ROLE_ENROLLEE')">
            <li><a href="${pageContext.request.contextPath}/userProfile"><spring:message code="profile"/></a></li>
            <li><a href="${pageContext.request.contextPath}/faculty"><spring:message code="faculty-list"/></a></li>
            <li><a href="${pageContext.request.contextPath}/certificate"><spring:message code="certificate"/></a></li>
            <li><a href="${pageContext.request.contextPath}/statement"><spring:message code="statements"/></a></li>
        </sec:authorize>

        <sec:authorize access="isAuthenticated()">
            <li>
                <form action="${pageContext.request.contextPath}/logout" method="post" class="logout">
                    <input type="submit" class="button red big" value="<spring:message code="logout"/>"/>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
            </li>
        </sec:authorize>
    </ul>
</div>

<div class="mr-2">
    <select id="locates">
        <option value="en">EN</option>
        <option value="ua">UA</option>
    </select>
</div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>

<script src="${pageContext.request.contextPath}/js/lang.js"></script>
</body>
</html>
