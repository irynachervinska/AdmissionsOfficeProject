<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>

    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <title>All statements</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
</head>
<body>

<jsp:include page="header.jsp"></jsp:include>

<div class="statementsWrapper">
    <table class="table table-striped">
        <thead id="tableHeader">
        <tr>
            <th scope="col">#</th>
            <th scope="col"> <spring:message code="statement.useremail"/></th>
            <th scope="col"> <spring:message code="statement.faculty"/></th>
            <th scope="col"><spring:message code="statement.subjects"/></th>
            <th scope="col"><spring:message code="statement.marks"/></th>
            <th scope="col"><spring:message code="statement.averCert"/></th>
            <th scope="col"><spring:message code="statement.averEx"/></th>
            <th scope="col"><spring:message code="statement.total"/></th>
            <th scope="col"><spring:message code="statement.status"/></th>
            <th scope="col"><spring:message code="action"/></th>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="statement" items="${allStatementsToAccept}">
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
                <td>
                    <form action="${pageContext.request.contextPath}/statementsToAccept/accept/${statement.id}">
                        <button type="submit" class="buttonAdd"> <spring:message code="accept"/></button>
                    </form>
                    <form action="${pageContext.request.contextPath}/statementsToAccept/reject/${statement.id}"
                          method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <button type="submit" class="buttonAdd"> <spring:message code="reject"/></button>
                        <input class="inputMassage" type="text" placeholder="Reject massage" name="rejectMassage"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>


<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>

</body>

</html>