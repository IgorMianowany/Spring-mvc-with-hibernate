<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: fimia
  Date: 15.08.2022
  Time: 14:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form action="${pageContext.request.contextPath}/register/processRegistrationForm"
           modelAttribute="CRMUser"
           class="form-horizontal">
  <!-- Check for registration error -->
  <c:if test="${registrationError != null}">
    <div class="alert alert-danger col-xs-offset-1 col-xs-10">
        ${registrationError}
    </div>
  </c:if>
  Username:
  <!-- User name -->
  <form:input path="username" placeholder="username" class="form-control" />
  <br><!-- Password -->
  Password:
  <form:password path="password" placeholder="password" class="form-control" />
  <br>
  First Name:
  <form:input path="firstName" placeholder="first name" class="form-control" />
  <br>
  <button type="submit" class="btn btn-primary">Register</button>
</form:form>

</body>
</html>
