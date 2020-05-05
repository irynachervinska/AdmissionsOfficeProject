<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
    <meta charset="UTF-8">
    <title>Create faculty</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
</head>
<body>
<h1>Add course</h1>
<form action="${pageContext.request.contextPath}/faculty/${faculty.id}/subjects" method="get">
    <div class="col-md-4">
        <div>
            <div>Faculty info</div>
            <div>
                Title: <b>${faculty.title}</b> <br/>
                placesNumberPaid: <b>${faculty.placesNumberPaid}</b><br/>
                placesNumberFree: <b>${faculty.placesNumberFree}</b><br/>
                <br/>
                Subjects:
                <c:forEach var="subject" items="${faculty.subjects}">
                    ${subject.title}
                </c:forEach>
                <br/>
            </div>
        </div>
        <div>

            <select name="subjectId">
                <option value="" disabled selected hidden> Select subject</option>
                <c:forEach var="subject" items="${subjects}">
                    <option value="${subject.id}">${subject.title}</option>
                </c:forEach>
            </select>
        </div>
        <input type="submit" value="Add course"/>
    </div>
</form>
</body>
</html>