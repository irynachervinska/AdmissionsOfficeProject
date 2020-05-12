<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns:sec="http://www.springframework.org/security/tags">

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
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>User email</th>
                <th>Faculty</th>
                <th>Required subjects</th>
                <th>Exam marks</th>
                <th>Average certificate</th>
                <th>Average exams</th>
                <th>Total point</th>
                <th>Status</th>
                <th>Accept</th>
            </tr>
            </thead>
            <c:forEach var="statement" items="${allStatementsToAccept}">
                <tbody>
                <tr>
                    <td>${statement.id}</td>
                    <td>${statement.statement.user.email}</td>
                    <td>${statement.statement.faculty.title}</td>
                    <td>${statement.statement.faculty.subjects}</td>
                    <td>${statement.statement.examMarks}</td>
                    <td>${statement.statement.averageCertificateMark}</td>
                    <td>${statement.statement.averageExamMark}</td>
                    <td>${statement.totalMark}</td>
                    <td>${statement.accepted}</td>
                    <td><a href="${pageContext.request.contextPath}/statementsToAccept/accept/${statement.id}"> Accept </a></td>
                </tr>
                </tbody>
            </c:forEach>
        </table>


    </div>
</div>
</body>

</html>