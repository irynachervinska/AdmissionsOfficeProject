<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>All statements</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
</head>
<body>

<jsp:include page="header.jsp"></jsp:include>

<div id='center' class="main center">
    <div class="mainInner">

        <div>
            <c:if test="${errorMassage ne null}">${errorMassage}
            </c:if>
            <c:if test="${errorMassage==null}">
                <a href="${pageContext.request.contextPath}/statement/create"> <spring:message code="statement.create"/>Create new →</a>
            </c:if>

        </div>
        <br>

        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th><spring:message code="statement.useremail"/></th>
                <th><spring:message code="faculty.title"/></th>
                <th><spring:message code="statement.averCert"/></th>
                <th><spring:message code="statement.averEx"/></th>
                <%--                <th>Edit</th>--%>
                <th><spring:message code="delete"/></th>

            </tr>
            </thead>

            <tbody>
            <c:forEach var="statement" items="${statements}">
                <tr>
                    <td>${statement.id}</td>
                    <td>${sessionScope.user.get().email} </td>
                    <td>${statement.faculty.title}</td>
                    <td>${statement.averageCertificateMark}</td>
                    <td>${statement.averageExamMark}</td>
                        <%--                    <th>Edit</th>--%>
                    <th><a href="${pageContext.request.contextPath}/statement/delete?id=${statement.id}"><spring:message code="delete"/></a></th>

                </tr>
            </c:forEach>

            <input type="hidden" name="userId" value="${sessionScope.user.get().id}"/>
            </tbody>
        </table>


    </div>
</div>
</body>

</html>