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
                <th>Faculty title</th>
                <th>Number of paid places</th>
                <th>Number of free places</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="faculty" items="${faculties}">
                <tr>
                    <td>${faculty.id}</td>
                    <td>${faculty.title}</td>
                    <td>${faculty.placesNumberPaid}</td>
                    <td>${faculty.placesNumberFree}</td>
                    <td><a href="${pageContext.request.contextPath}/faculty/edit?id=${faculty.id}">edit</a></td>
                    <td><a href="${pageContext.request.contextPath}/faculty/delete?id=${faculty.id}">delete</a></td>
                    <td>
                        <button type="submit" class="button">Save edits</button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <a href="${pageContext.request.contextPath}/faculty/addFaculty">Create new →</a>


    </div>
</div>
</body>

</html>