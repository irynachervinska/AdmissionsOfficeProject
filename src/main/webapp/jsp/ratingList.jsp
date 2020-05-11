<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns:sec="http://www.springframework.org/security/tags">

<html>
<head>
    <meta charset="UTF-8">
    <title>Rating List</title>
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
                <th>Faculty title</th>
                <th>User details</th>
                <th>User total mark</th>
                <th>Accepted</th>

            </tr>
            </thead>
            <c:forEach var="ratingList" items="${ratingList}">
                <tbody>
                <tr>

                    <td>${faculty}</td>
                    <td>${faculty.title}</td>
                    <td>
                            ${ratingList.statement.user.email} -
                            ${ratingList.statement.user.firstName} ${ratingList.statement.user.lastName}
                    </td>
                    <td>${ratingList.totalMark}</td>
                    <td>${ratingList.accepted}</td>

                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <td>
                            <a href="">Accept</a>
                            <a href="">Reject</a>
                        </td>
                    </sec:authorize>

                </tr>

                </tbody>
            </c:forEach>
        </table>

        <a href="${pageContext.request.contextPath}/faculty"> Back to faculties </a>


    </div>
</div>
</body>

</html>