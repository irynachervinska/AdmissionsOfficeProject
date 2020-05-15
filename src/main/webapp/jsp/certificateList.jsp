<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Certificate</title>
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
                <th>Subject</th>
                <th>Mark</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="certificate" items="${certificates}">
                <tr>
                    <td>${certificate.id}</td>
                    <td>${certificate.subject.title}</td>
                    <td>${certificate.mark}</td>
                    <td><a href="${pageContext.request.contextPath}/certificate/edit?id=${certificate.id}">edit</a></td>
                    <td><a href="${pageContext.request.contextPath}/certificate/delete?id=${certificate.id}">delete</a></td>

                </tr>
            </c:forEach>
            </tbody>
        </table>

        <a href="${pageContext.request.contextPath}/certificate/add">Create new →</a>

        <input type="hidden" value="${userId}" name="user">


    </div>
</div>
</body>

</html>