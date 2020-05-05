<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create faculty</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
</head>
<body>

<jsp:include page="header.jsp"></jsp:include>

<div id='center' class="main center">
    <div class="mainInner">


        <div class="flipperLogin" id="flipper">

            <div class="front">
                <form action="${pageContext.request.contextPath}/subject/addSubject" method="post">

                    <input type="text" placeholder="Title" name="title"/>

                    <button type="submit" class="button">Create</button>
                    <div>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    </div>

                </form>
            </div>

        </div>

    </div>
</div>
</body>

</html>