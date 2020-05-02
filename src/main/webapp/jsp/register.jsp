<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
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
    <div class="flipper" id="flipper">
<%--        back--%>
        <div class="front">
            <h1 class="title">Welcome!</h1>
            <form action="${pageContext.request.contextPath}/register" method="post">
                <input type="text" placeholder="First Name" name="firstName"/>
                <input type="text" placeholder="Last Name" name="lastName"/>
                <input type="email" placeholder="Email" name="email"/>
                <input type="number" placeholder="Age" name="age"/>
                <input type="password" placeholder="Password" name="password"/>

                <button type="submit" id="register" class="button">Register me</button>
                <div>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                </div>
            </form>
            <a class="flipbutton" id="registerButton" href="/login">Login to my account →</a>
        </div>

    </div>
</div>
</body>
</html>