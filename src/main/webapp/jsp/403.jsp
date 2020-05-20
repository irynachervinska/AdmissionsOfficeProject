<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome</title>
    <link href="https://fonts.googleapis.com/css2?family=Raleway:wght@500&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
</head>

<body class="login-form">
<div class="flip-container">
    <div class="flipperLogin" id="flipper">

        <div class="front">
            <h1 class="title"> Oops! </h1>
            <p class="not"><spring:message code="not"/></p>
            <form action="${pageContext.request.contextPath}/logout" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
            <form action="${pageContext.request.contextPath}/login">
                <input type="submit" class="anotherName" value="<spring:message code="another"/>"/>
            </form>
            <form action="${pageContext.request.contextPath}/">
                <input type="submit" class="anotherName" value="<spring:message code="confirm.home"/>"/>
            </form>
        </div>

    </div>
</div>
</body>
</html>