<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create certificate</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
</head>
<body>

<jsp:include page="header.jsp"></jsp:include>

<div id='center' class="main center">
    <div class="mainInner">


        <div class="flipperLogin" id="flipper">

            <div class="front">
                <form action="${pageContext.request.contextPath}/certificate/add" method="post">

                    <label>
                        <select name="subjectId">
                            <c:forEach var="subject" items="${subjects}">
                            <option value="" disabled selected hidden> Select subject </option>
                                <option value="${subject.id}" >${subject.title}</option>
                            </c:forEach>
                        </select>
                    </label>
                    <br>
                    <br>

                    <label>
                        <input type="number" placeholder="Mark" name="mark"/>
                    </label>
                    <br>
                    <br>

                    <button type="submit" class="button">Save edits</button>

                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input type="hidden" value="${userId}" name="user">


                </form>
            </div>

        </div>

    </div>
</div>
</body>

</html>