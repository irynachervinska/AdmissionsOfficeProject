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
<body>

<div class="flip-container">
    <div class="flipperLogin" id="flipper">

        <div class="front">
            <h1 class="title"> <spring:message code="login.welcome-back"/> </h1>
            <form action="/spring_security_check" method="post">

                <input type="text" placeholder="<spring:message code="login.email"/>" name="email"/>
                <input type="password" placeholder="<spring:message code="login.password"/>" name="password"/>

                <button type="submit" class="button"><spring:message code="login.login"/></button>
                <div>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                </div>
                <c:if test="${param.error ne null}">
                    <div><spring:message code="login.wrong"/></div>
                </c:if>
            </form>
            <a class="flipbutton" id="loginButton" href="${pageContext.request.contextPath}/new"><spring:message code="login.create"/></a>
        </div>

    </div>
</div>
</body>
</html>