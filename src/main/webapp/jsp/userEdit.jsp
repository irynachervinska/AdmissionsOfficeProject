<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit faculty</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/userProfile.css">
</head>
<body>

<jsp:include page="header.jsp"></jsp:include>


<div id='center' class="main center">
    <div class="wrapper">
        <div class="profile-card js-profile-card">
            <div class="profile-card__img">
                <img src="https://99181-282044-raikfcquaxqncofqfm.stackpathdns.com/wp-content/uploads/2016/05/icon-user-default.png"
                     alt="profile card">
            </div>

            <div class="profile-card__cnt js-profile-cnt">

                <form action="${pageContext.request.contextPath}/userProfile/edit" method="post">



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


                    <div class="profile-card-ctr">
                        <button type="submit" class="profile-card__button button--blue">Save edits</button>
                        <button class="profile-card__button button--orange">Upload photo</button>
                        <button class="profile-card__button button--pink">Back to profile</button>
                    </div>

                    <div>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <input type="hidden" value="${user.id}" name="userId">
                    </div>


                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>