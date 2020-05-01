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
        <li>
            <%=session.getAttribute("name")%>
            <%=session.getAttribute("first")%>


            <span><c:out value="${pageContext.request.remoteUser}"></c:out></span></li>

        <li><a href="/login">Login</a></li>
        <li><a href="/admin">Hello admin</a></li>
        <li><a href="/user">User link</a></li>
        <li><a href="/all">All</a></li>

    </ul>
</div>

</body>
</html>
