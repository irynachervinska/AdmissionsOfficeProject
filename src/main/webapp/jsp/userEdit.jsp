<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit info</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/userProfile.css">
</head>
<body>

<jsp:include page="header.jsp"></jsp:include>
<div id='center' class="main center">
    <div class="wrapper">
        <div class="profile-card js-profile-card">
            <form action="${pageContext.request.contextPath}/userProfile/edit/${user.id}" method="post">

                <div class="profile-card__img">
                    <img src="/user-photo/download/${user.userPhotoId}" alt="profile card" id="userPhoto">
                </div>

                <div class="profile-card__cnt js-profile-cnt">
                    <label class="inputEdits">
                        <input type="text" placeholder="First name" name="firstName" value="${user.firstName}"/>
                    </label>

                    <label class="inputEdits">
                        <input type="text" placeholder="Last name" name="lastName" value="${user.lastName}"/>
                    </label>

                    <label class="inputEdits">
                        <input type="number" placeholder="Age" name="age" value="${user.age}"/>
                    </label>

                    <label class="inputEdits">
                        <input type="text" placeholder="Email" name="email" value="${user.email}"/>
                    </label>

                    <div class="form-group">
                        <label for="photo">User photo</label>
                        <input id="photo" type="file"/>
                    </div>

                    <input id="token" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input type="hidden" value="${user.id}" name="userId">
                    <input type="hidden" value="" class="form-control" id="photo-id" name="userPhotoId">


                    <div class="profile-card-ctr">
                        <button type="submit" class="profile-card__button button--blue">Save edits</button>
                        <button class="profile-card__button button--orange">Upload photo</button>
                        <button class="profile-card__button button--pink">Back to profile</button>
                    </div>
                </div>
            </form>
        </div>
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

<script src="${pageContext.request.contextPath}/js/userPhoto.js"></script>
</body>
</html>