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
            <h1 class="title">Welcome back!</h1>
            <form action="/spring_security_check" method="post">

                <input type="text" placeholder="Email" name="email"/>
                <input type="text" placeholder="Password" name="password"/>

                <button type="submit" class="button">Login</button>
                <div>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                </div>
                <c:if test="${param.error ne null}">
                    <div>Wrong password or username</div>
                </c:if>
            </form>
            <a class="flipbutton" id="loginButton" href="/new">Create my account →</a>
        </div>

    </div>
</div>
</body>
</html>