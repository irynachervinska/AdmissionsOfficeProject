<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <li> <% if (session.getAttribute("firstName") == null) { %>
            <p> Welcome! </p>
            <% } else {%>
            <p> <%=session.getAttribute("firstName")%> <%=session.getAttribute("lastName")%> </p>
            <% } %>

            <span><c:out value="${pageContext.request.remoteUser}"></c:out></span></li>

        <li><a href="/login">Login</a></li>
        <li><a href="/faculty">Faculty</a></li>
        <li><a href="/user">User link</a></li>
        <li><a href="/all">All</a></li>
        <% if (session.getAttribute("firstName") != null) { %>
        <li>  <form action="${pageContext.request.contextPath}/logout" method="post" class="logout">
            <input type="submit" class="button red big" value="LOGOUT" />
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        </form>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> </li>
        <% } %>


    </ul>
</div>

</body>
</html>
