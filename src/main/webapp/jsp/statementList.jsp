<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <title>All statements</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
</head>
<body>

<jsp:include page="header.jsp"></jsp:include>

<div id='center' class="main center">
    <div class="mainInner">
        <table class="table table-striped">
            <thead  id="tableHeader">
            <tr>
                <th scope="col">#</th>
                <th scope="col"> <spring:message code="statement.useremail"/></th>
                <th scope="col"> <spring:message code="faculty.title"/></th>
                <th scope="col"><spring:message code="statement.averCert"/></th>
                <th scope="col"><spring:message code="statement.averEx"/></th>
                <th scope="col"><spring:message code="edit"/></th>
                <th scope="col"><spring:message code="delete"/></th>
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
                    <th><a href="${pageContext.request.contextPath}/statement/edit?id=${statement.id}"><spring:message code="edit"/></a></th>
                    <th><a href="${pageContext.request.contextPath}/statement/delete?id=${statement.id}"><spring:message code="delete"/></a></th>

                </tr>
            </c:forEach>
            </tbody>
        </table>

        <input type="hidden" name="userId" value="${sessionScope.user.get().id}"/>

        <c:if test="${errorMassage ne null}">
            ${errorMassage}
        </c:if>
        <c:if test="${errorMassage==null}">
        <form action="${pageContext.request.contextPath}/statement/create">
            <button class="buttonAdd"> <spring:message code="statement.create"/></button>
        </form>
        </c:if>

    </div>
</div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

</body>

</html>