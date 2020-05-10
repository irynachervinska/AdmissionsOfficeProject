<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create statement</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
</head>
<body>

<jsp:include page="header.jsp"></jsp:include>

<div id='center' class="main center">
    <div class="mainInner">
        <form action="${pageContext.request.contextPath}/statement/create" method="post">


            FN: ${sessionScope.user.get().firstName} <br>
            LN: ${sessionScope.user.get().lastName} <br>
            email: ${sessionScope.user.get().email} <br>
            age: ${sessionScope.user.get().age} <br>
            <input type="hidden" name="userId" value="${sessionScope.user.get().id}"/>

            <br>
            <br>

            <label>
                <input type="number" name="averageCertificateMark" value="averageCertificateMark" placeholder="Average Certificate Mark">
            </label>

            <br>
            <br>

            <label>
                <select name="facultyId">
                    <option value="" disabled selected hidden> Select faculty</option>
                    <c:forEach items="${faculties}" var="faculty">
                        <option value="${faculty.id}">${faculty.title}</option>
                    </c:forEach>
                </select>
            </label>

            <div id="subjects"></div>

            <br>
            <br>

            <label>
                <select name="certificateIds" multiple>
                    <option value="" disabled selected hidden> Select your exams and marks</option>
                    <c:forEach items="${certificates}" var="certificate">
                        <option value="${certificate.id}">${certificate.subject.title} - ${certificate.mark}</option>
                    </c:forEach>
                </select>
            </label>

            <input type="submit" value="Create"/>


            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>


        </form>


    </div>

    <%--    <script src="${pageContext.request.contextPath}/js/statement.js"></script>--%>
</body>

</html>