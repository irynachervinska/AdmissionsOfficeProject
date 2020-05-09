<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>

<head>
    <meta charset="UTF-8">
    <title>Create faculty</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
</head>
<body>
<h1>Add subject</h1>
<form action="${pageContext.request.contextPath}/faculty/subjects?id=${faculty.id}" method="post">
    <div class="col-md-4">
        <div>
            <div>Faculty info</div>
            <div>
                Title: <b>${faculty.title}</b> <br/>
                placesNumberPaid: <b>${faculty.placesNumberPaid}</b><br/>
                placesNumberFree: <b>${faculty.placesNumberFree}</b><br/>
                <br/>
                Subjects:


<%--                <c:forEach var="subject" items="${faculty.subjects}">--%>
<%--                    &lt;%&ndash;                    ${subject}&ndash;%&gt;--%>
<%--                    <s:query var=""--%>

<%--                </c:forEach>--%>
                <br/>
            </div>
        </div>
        <div>

            <label>
                <select name="subjectIds" multiple>
                    <option value="" disabled selected hidden> Select subject</option>
                    <c:forEach var="subject" items="${subjects}">
                        <option value="${subject.id}">${subject.title}</option>
                    </c:forEach>
                </select>
            </label>

            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </div>
        <input type="submit" value="Add subjects"/>
    </div>
</form>
</body>
</html>