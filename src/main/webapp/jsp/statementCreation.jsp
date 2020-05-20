<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <title>Create statement</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
</head>
<body>

<jsp:include page="header.jsp"></jsp:include>

<div id='center' class="main center">
    <div class="mainInner">
        <form action="${pageContext.request.contextPath}/statement/create" method="post">

            <h2><spring:message code="statement.info"/></h2>
            <div id="info">
                <spring:message code="statement.fn"/>: <b> ${sessionScope.user.get().firstName} </b> <br>
                <spring:message code="statement.ln"/>: <b> ${sessionScope.user.get().lastName} </b> <br>
                <spring:message code="statement.useremail"/>: <b> ${sessionScope.user.get().email}</b> <br>
                <spring:message code="statement.age"/>: <b>${sessionScope.user.get().age} </b> <br>
            </div>
            <br>

            <label class="putAver"> <spring:message code="statement.putAverage"/> : </label>
            <input type="number" name="averageCertificateMark" value="averageCertificateMark"
                   placeholder="Average Certificate Mark">
            ${hasErrors}

            <br>
            <div class="form-group">
                <label for="exampleFormControlSelect2"> ${statementExistError} </label>
                <select name="facultyId" class="form-control" id="exampleFormControlSelect2">
                    <option value="" disabled selected hidden><spring:message code="statement.select"/></option>
                    <c:forEach items="${faculties}" var="faculty">
                        <option value="${faculty.id}">${faculty.title}</option>
                    </c:forEach>
                </select>


            </div>
            <div class="form-group">
                <label for="exampleFormControlSelect2">
                    <select name="certificateIds" multiple class="form-control">
                        <option value="" disabled selected hidden>
                            <spring:message code="statement.selectCert"/></option>
                        <c:forEach items="${certificates}" var="certificate">
                            <option value="${certificate.id}">${certificate.subject.title}
                                - ${certificate.mark}</option>
                        </c:forEach>
                    </select>
                </label>
            </div>
            <button type="submit" class="buttonAdd"><spring:message code="statement.create"/></button>
            <div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
<%--                <input type="hidden" name="userId" value="${sessionScope.user.get().id}"/>--%>
                <input type="hidden" name="userId" value="${userId}"/>
            </div>
        </form>
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