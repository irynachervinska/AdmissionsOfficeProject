<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit faculty</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
</head>
<body>

<jsp:include page="header.jsp"></jsp:include>

<div id='center' class="main center">
    <div class="mainInner">


        <div class="flipperLogin" id="flipper">

            <div class="front">

                <form action="${pageContext.request.contextPath}/faculty/edit" method="post">

                    <input type="hidden" value="${faculty.id}" name="id">
                    <input type="text" placeholder="Title"  value="${faculty.title}"/>
                    <input type="text" placeholder="Number of paid places"  value="${faculty.placesNumberPaid}"/>
                    <input type="text" placeholder="Number of free places" value="${faculty.placesNumberFree}"/>

                    <button type="submit" class="button">Save edits</button>
                    <div>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </div>

                </form>
            </div>

        </div>


    </div>
</div>
</body>

</html>