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
                    <td class="total">
                            ${ratingList.totalMark}
                        <input type="hidden" class="totalMark" name="x" value="${ratingList.totalMark}">
                    </td>
                    <td>${ratingList.accepted}</td>
                </tr>
                </tbody>
            </c:forEach>
        </table>

        <a href="${pageContext.request.contextPath}/faculty"> Back to faculties </a>


    </div>
</div>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/js/ratingList.js"></script>
</body>

</html>