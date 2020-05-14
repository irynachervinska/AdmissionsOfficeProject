<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

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
                <th scope="col">Faculty title</th>
                <th scope="col">Number of paid places</th>
                <th scope="col">Number of free places</th>
                <th scope="col">Required subjects</th>
                <th scope="col">Rating list</th>

                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <th scope="col">Action</th>
                    <th scope="col">Add subjects</th>
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
                        <a href="faculty/ratingList/${faculty.id}">View rating list by this faculty</a>
                    </td>

                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <td>
                            <a href="${pageContext.request.contextPath}/faculty/edit?id=${faculty.id}"> Edit </a> <br>
                            <a href="${pageContext.request.contextPath}/faculty/delete?id=${faculty.id}"> Delete </a>
                        </td>
                        <td>
                            <a href="/faculty/addSubjects/subjects/${faculty.id}">Add subjects</a>
                        </td>
                    </sec:authorize>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <form action="${pageContext.request.contextPath}/faculty/addFaculty">
                <button class="buttonAdd">Create new →</button>
            </form>
        </sec:authorize>


        <%--        <table>--%>
        <%--            <thead>--%>
        <%--            <tr>--%>
        <%--                <th>Id</th>--%>
        <%--                <th>Faculty title</th>--%>
        <%--                <th>Number of paid places</th>--%>
        <%--                <th>Number of free places</th>--%>
        <%--                <th>Required subjects</th>--%>
        <%--                <th>Edit</th>--%>
        <%--                <th>Delete</th>--%>
        <%--                <th>Add subjects</th>--%>
        <%--            </tr>--%>
        <%--            </thead>--%>

        <%--            <tbody>--%>
        <%--            <c:forEach var="faculty" items="${faculties}">--%>
        <%--                <tr>--%>
        <%--                    <td>${faculty.id}</td>--%>
        <%--                    <td>${faculty.title}</td>--%>
        <%--                    <td>${faculty.placesNumberPaid}</td>--%>
        <%--                    <td>${faculty.placesNumberFree}</td>--%>
        <%--                    <td>--%>

        <%--                        <c:forEach var="subject" items="${faculty.subjects}">--%>
        <%--                            ${subject.title}--%>
        <%--                        </c:forEach>--%>

        <%--                    </td>--%>
        <%--                    <td><a href="${pageContext.request.contextPath}/faculty/edit?id=${faculty.id}">edit</a></td>--%>
        <%--                    <td><a href="${pageContext.request.contextPath}/faculty/delete?id=${faculty.id}">delete</a></td>--%>
        <%--                    <td>--%>

        <%--                        <a href="/faculty/addSubjects/subjects/${faculty.id}">Add subjects</a> <br>--%>
        <%--                        <a href="faculty/ratingList/${faculty.id}">View rating list by this faculty</a>--%>
        <%--                    </td>--%>
        <%--                </tr>--%>
        <%--            </c:forEach>--%>
        <%--            </tbody>--%>
        <%--        </table>--%>

        <%--        <a href="${pageContext.request.contextPath}/faculty/addFaculty">Create new →</a>--%>


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