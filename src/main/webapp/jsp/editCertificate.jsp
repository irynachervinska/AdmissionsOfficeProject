<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit certificate</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
</head>
<body>

<jsp:include page="header.jsp"></jsp:include>

<div id='center' class="main center">
    <div class="mainInner">


        <form action="${pageContext.request.contextPath}/certificate/edit" method="post">

            <input type="hidden" value="${certificate.id}" name="id">
            <label>
                <select name="subjectId">
                    <c:forEach var="subject" items="${subjects}">
                    <option value=""  disabled selected hidden > ${certificate.subject.title}</option>
                        <option value="${subject.id}">${subject.title}</option>
                    </c:forEach>
                </select>
            </label>

            <br>
            <br>

            <label>
                <input type="number" placeholder="Mark" name="mark"/>
            </label>


            <button type="submit" class="button">Save edits</button>
            <div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </div>

        </form>


    </div>
</div>
</body>

</html>