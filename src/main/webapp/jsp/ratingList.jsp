<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


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

                    <td>${ratingList.id}</td>
                    <td>${ratingList.statement.faculty.title}</td>
                    <td>
                            ${ratingList.statement.user.email} -
                            ${ratingList.statement.user.firstName} ${ratingList.statement.user.lastName}
                    </td>
                    <td>${ratingList.totalMark}</td>
                    <td>${ratingList.accepted}</td>


                </tr>

                </tbody>
            </c:forEach>
        </table>

        <a href="${pageContext.request.contextPath}/faculty"> Back to faculties </a>


    </div>
</div>
</body>

</html>