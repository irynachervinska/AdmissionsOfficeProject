<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="script" uri="http://www.springframework.org/tags/form" %>

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
        <div class="front">
            <h1 class="title"><spring:message code="register.welcome"/></h1>
            <form action="${pageContext.request.contextPath}/register" modelAttribute="userDto" method="post">

                <div class="${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="userDto.firstName" placeholder="First name"/>
                    <form:errors path="userDto.firstName"/>
                </div>

                <div class="${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="userDto.lastName" placeholder="Last name"/>
                    <form:errors path="userDto.lastName"/>
                </div>

                <div class="${status.error ? 'has-error' : ''}">
                    <form:input type="email" path="userDto.email" placeholder="Email@gmail.com"/>
                    <form:errors path="userDto.email"/>
                </div>

                <div>
                    <input type="number" placeholder="Age"/>
                </div>

                <div class="${status.error ? 'has-error' : ''}">
                    <form:input type="password" path="userDto.password" placeholder="Password"/>
                    <form:errors path="userDto.password"/>
                </div>

                <div class="${status.error ? 'has-error' : ''}">
                    <form:input type="password" path="userDto.passwordConfirm" placeholder="Confirm password"/>
                    <form:errors path="userDto.passwordConfirm"/>
                </div>

                <button type="submit" id="register" class="button"><spring:message code="register.register"/></button>
                <div>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    <input type="hidden" value="" class="form-control" id="photo-id" name="userPhotoId">
                </div>
            </form>
            <a class="flipbutton" id="registerButton"
               href="${pageContext.request.contextPath}/login">
                <spring:message code="login.loginToAcc"/>Login to my account →</a>
        </div>

    </div>
</div>
</body>
</html>