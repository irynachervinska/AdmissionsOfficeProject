<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Confirm email</title>
    <link href="https://fonts.googleapis.com/css2?family=Raleway:wght@500&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
</head>
<body>

<div class="flip-container">
    <div class="flipper" id="flipper">

        <div class="front">
            <h1 class="title"> <spring:message code="confirm.long"/> </h1>

            <div>
                <spring:message code="confirm.please"/>
                <br>
                <br>
                <spring:message code="confirm.donthave"/>
            </div>
            <br>
            <a class="flipbutton" id="loginButton" href="${pageContext.request.contextPath}/new"> <spring:message code="confirm.create"/></a>
            <br>
            <a class="flipbutton" href="${pageContext.request.contextPath}/"> <spring:message code="confirm.home"/></a>
        </div>

    </div>
</div>
</body>
</html>