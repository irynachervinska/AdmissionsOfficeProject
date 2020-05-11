<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>All faculties</title>
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
                <th>Subject title</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="subject" items="${subjects}">
                <tr>
                    <td>${subject.id}</td>
                    <td>${subject.title}</td>
                    <td><a href="${pageContext.request.contextPath}/subject/edit?id=${subject.id}">edit</a></td>
                    <td><a href="${pageContext.request.contextPath}/subject/delete?id=${subject.id}">delete</a></td>

                </tr>
            </c:forEach>
            </tbody>
        </table>

        <a href="${pageContext.request.contextPath}/subject/addSubject">Create new →</a>


    </div>
</div>
</body>

</html>