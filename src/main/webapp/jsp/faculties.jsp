<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>

    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <title>All faculties</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
</head>
<body>

<jsp:include page="header.jsp"></jsp:include>

<div id='center' class="main center">
    <div class="mainInner">

        <table class="table table-striped">
            <thead id="tableHeader">
            <tr>
                <th scope="col">#</th>
                <th scope="col"><spring:message code="faculty.title"/></th>
                <th scope="col"><spring:message code="subject.paid"/></th>
                <th scope="col"><spring:message code="subject.free"/></th>
                <th scope="col"><spring:message code="faculty.subjects"/></th>
                <th scope="col"><spring:message code="faculty.rating"/></th>

                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <th scope="col"> <spring:message code="faculty.action"/></th>
                    <th scope="col"> <spring:message code="faculty.addsubjects"/></th>
                </sec:authorize>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="faculty" items="${faculties}">
                <tr>
                    <th scope="row">${faculty.id}</th>
                    <td>${faculty.title}</td>
                    <td>${faculty.placesNumberPaid}</td>
                    <td>${faculty.placesNumberFree}</td>
                    <td>
                        <c:forEach var="subject" items="${faculty.subjects}">
                            ${subject.title}
                        </c:forEach>
                    </td>
                    <td>
                        <a href="faculty/ratingList/${faculty.id}"> <spring:message code="faculty.viewRating"/></a>
                    </td>

                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <td>
                            <a href="${pageContext.request.contextPath}/faculty/edit?id=${faculty.id}"> <spring:message code="edit"/> </a> <br>
                            <a href="${pageContext.request.contextPath}/faculty/delete?id=${faculty.id}"> <spring:message code="delete"/>  </a>
                        </td>
                        <td>
                            <a href="/faculty/addSubjects/subjects/${faculty.id}"> <spring:message code="faculty.addsubjects"/></a>
                        </td>
                    </sec:authorize>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <form action="${pageContext.request.contextPath}/faculty/addFaculty">
                <button class="buttonAdd"> <spring:message code="faculty.create"/></button>
            </form>
        </sec:authorize>
    </div>
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