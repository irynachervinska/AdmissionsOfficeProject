<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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


            <spring:message code="statement.fn"/> ${sessionScope.user.get().firstName} <br>
            <spring:message code="statement.ln"/> ${sessionScope.user.get().lastName} <br>
            <spring:message code="statement.useremail"/> ${sessionScope.user.get().email} <br>
            <spring:message code="statement.age"/> ${sessionScope.user.get().age} <br>
            <input type="hidden" name="userId" value="${sessionScope.user.get().id}"/>

            <br>
            <br>


            <label
            <spring:message code="statement.putAverage">>
                <input type="number" name="averageCertificateMark" value="averageCertificateMark"
                       placeholder="Average Certificate Mark">
            </label>

            <br>
            <br>

            <label>
                <select name="facultyId">
                    <option value="" disabled selected hidden>
                        <spring:message code="statement.select"></option>
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
                    <option value="" disabled selected hidden>
                        <spring:message code="statement.selectCert"></option>
                    <c:forEach items="${certificates}" var="certificate">
                        <option value="${certificate.id}">${certificate.subject.title} - ${certificate.mark}</option>
                    </c:forEach>
                </select>
            </label>

            <input type="submit" value="<spring:message code="statement.create">"/>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

        </form>
    </div>
</body>
</html>