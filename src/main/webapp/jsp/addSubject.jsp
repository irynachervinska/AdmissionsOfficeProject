<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/sql" %>

<head>

    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <title>Add subjects</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div id='center' class="main center">
    <div class="mainInner">
        <form action="${pageContext.request.contextPath}/faculty/subjects?id=${faculty.id}" method="post">

            <h2>Adding subjects to faculty</h2>
            <div id="info">
                <h4>Faculty info</h4>
                Faculty : <b>${faculty.title}</b> <br/>
                Number of paid places: <b>${faculty.placesNumberPaid}</b><br/>
                Number of free places: <b>${faculty.placesNumberFree}</b><br/>
            </div>

            <br>
            <div class="form-group">
                <label for="exampleFormControlSelect2">Select 3 subjects for faculty: </label>
                <select name="subjectIds" multiple class="form-control" id="exampleFormControlSelect2">
                    <option value="" disabled selected hidden> Select subject</option>
                    <c:forEach var="subject" items="${subjects}">
                        <option value="${subject.id}">${subject.title}</option>
                    </c:forEach>
                </select>
            </div>
            <button type="submit" class="buttonAdd">Add subjects</button>
            <div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </div>
        </form>
    </div>
</div>


<%--<form action="${pageContext.request.contextPath}/faculty/subjects?id=${faculty.id}" method="post">--%>
<%--    <div class="col-md-4">--%>
<%--        <div>--%>
<%--            <div>Faculty info</div>--%>
<%--            <div>--%>
<%--                Title: <b>${faculty.title}</b> <br/>--%>
<%--                placesNumberPaid: <b>${faculty.placesNumberPaid}</b><br/>--%>
<%--                placesNumberFree: <b>${faculty.placesNumberFree}</b><br/>--%>
<%--                <br/>--%>
<%--                Subjects:--%>
<%--                <br/>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <div>--%>

<%--            <label>--%>
<%--                <select name="subjectIds" multiple>--%>
<%--                    <option value="" disabled selected hidden> Select subject</option>--%>
<%--                    <c:forEach var="subject" items="${subjects}">--%>
<%--                        <option value="${subject.id}">${subject.title}</option>--%>
<%--                    </c:forEach>--%>
<%--                </select>--%>
<%--            </label>--%>

<%--            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>
<%--        </div>--%>
<%--        <input type="submit" value="Add subjects"/>--%>
<%--    </div>--%>
<%--</form>--%>


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