<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <title>Edit certificate</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
</head>
<body>

<jsp:include page="header.jsp"></jsp:include>

<div id='center' class="main center">
    <div class="mainInner">
        <form action="${pageContext.request.contextPath}/certificate/edit" method="post">
            <table class="table table-striped">
                <thead id="tableHeader">
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Select subject</th>
                    <th scope="col">Put your mark </th>
                    <th scope="col">Action</th>
                </tr>
                </thead>

                <tbody>
                <tr>
                    <th scope="row">${certificate.id}</th>
                    <td>
                        <div class="form-group">
                            <select name="subjectId" class="form-control" id="exampleFormControlSelect2">
                                <option value="" disabled selected hidden> ${certificate.subject.title}</option>
                                <c:forEach var="subject" items="${subjects}">
                                    <option value="${subject.id}" >${subject.title}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </td>
                    <td>
                        <label class="markinput">
                            <input type="number" placeholder="Mark" name="mark" value="${certificate.mark}"/>
                        </label>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/certificate"> Back to Certificate </a>
                    </td>
                </tr>
                </tbody>
            </table>

            <button class="buttonAdd"> Save edits</button>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input type="hidden" value="${certificate.id}" name="id">
        </form>





<%--        <form action="${pageContext.request.contextPath}/certificate/edit" method="post">--%>

<%--            <input type="hidden" value="${certificate.id}" name="id">--%>
<%--            <label>--%>
<%--                <select name="subjectId">--%>
<%--                    <c:forEach var="subject" items="${subjects}">--%>
<%--                    <option value=""  disabled selected hidden > ${certificate.subject.title}</option>--%>
<%--                        <option value="${subject.id}">${subject.title}</option>--%>
<%--                    </c:forEach>--%>
<%--                </select>--%>
<%--            </label>--%>

<%--            <br>--%>
<%--            <br>--%>

<%--            <label>--%>
<%--                <input type="number" placeholder="Mark" name="mark"/>--%>
<%--            </label>--%>


<%--            <button type="submit" class="button">Save edits</button>--%>
<%--            <div>--%>
<%--                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>
<%--            </div>--%>

<%--        </form>--%>


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