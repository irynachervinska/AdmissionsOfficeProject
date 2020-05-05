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
            <h1 class="title"> It won`t be long </h1>

            <div>
                Please check your mailbox and follow confirmation instructions.
                <br>
                <br>
                If you do not receive a letter within a few minutes, please ensure that yout mail server did not classify the e-mail as spam and therefore automatically put the email in a separate folder.
            </div>
            <a class="flipbutton" id="loginButton" href="/new">Create my account →</a>
            <br>
            <a class="flipbutton"href="/home">Back to Home page →</a>
        </div>

    </div>
</div>
</body>
</html>