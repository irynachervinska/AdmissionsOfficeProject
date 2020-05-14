<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document</title>
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
            <p> Welcome! </p>
            <% } else {%>
            <p><%=session.getAttribute("firstName")%> <%=session.getAttribute("lastName")%>
            </p>
            <% } %>

            <span><c:out value="${pageContext.request.remoteUser}"></c:out></span></li>


        <sec:authorize access="!isAuthenticated()">
            <li><a href="${pageContext.request.contextPath}/login">Login</a></li>
        </sec:authorize>


        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <li><a href="${pageContext.request.contextPath}/userProfile">Profile</a></li>
            <li><a href="${pageContext.request.contextPath}/subject">Manipulating the subjects</a></li>
            <li><a href="${pageContext.request.contextPath}/faculty">Manipulating the faculties</a></li>
            <li><a href="${pageContext.request.contextPath}/statementsToAccept">Statements to accept</a></li>
        </sec:authorize>

        <sec:authorize access="hasRole('ROLE_ENROLLEE')">
            <li><a href="${pageContext.request.contextPath}/userProfile">Profile</a></li>
            <li><a href="${pageContext.request.contextPath}/faculty">Faculty list</a></li>
            <li><a href="${pageContext.request.contextPath}/certificate">Add subject/marks</a></li>
            <li><a href="${pageContext.request.contextPath}/statement">Create statement</a></li>
        </sec:authorize>

        <sec:authorize access="isAuthenticated()">
            <li>
                <form action="${pageContext.request.contextPath}/logout" method="post" class="logout">
                    <input type="submit" class="button red big" value="LOGOUT"/>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
            </li>
        </sec:authorize>

    </ul>
</div>

</body>
</html>
