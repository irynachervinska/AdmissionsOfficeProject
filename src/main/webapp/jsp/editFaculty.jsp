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

    <title>Edit faculty</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
</head>
<body>

<jsp:include page="header.jsp"></jsp:include>

<div id='center' class="main center">
    <div class="mainInner">

        <form action="${pageContext.request.contextPath}/faculty/edit" method="post">

            <table class="table table-striped">
                <thead id="tableHeader">
                <tr>
                    <th scope="col">#</th>
                    <th scope="col"><spring:message code="faculty.title"/></th>
                    <th scope="col"><spring:message code="subject.paid"/></th>
                    <th scope="col"><spring:message code="subject.free"/></th>
                    <th scope="col"><spring:message code="faculty.subjects"/></th>
                </tr>
                </thead>

                <tbody>

                    <tr>
                        <th scope="row">${faculty.id}</th>
                        <td>
                            <input type="hidden" value="${faculty.id}" name="id">
                            <input type="text" placeholder="Title" value="${faculty.title}" name="title"/>
                        </td>
                        <td>
                            <input type="text" placeholder="Number of paid places" name="placesNumberPaid" value="${faculty.placesNumberPaid}"/>
                        </td>
                        <td>
                            <input type="text" placeholder="Number of free places" name="placesNumberFree" value="${faculty.placesNumberFree}"/>
                        </td>
                        <td>
                            <select name="subjectIds" multiple class="form-control" id="exampleFormControlSelect2">
                                <option value="" disabled selected hidden> Select subject</option>
                                <c:forEach var="subject" items="${subjects}">
                                    <option value="${subject.id}">${subject.title}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                </tbody>
            </table>

            <button type="submit" class="buttonAdd"> <spring:message code="faculty.saveedits"/></button>
            <div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
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