<%--
  Created by IntelliJ IDEA.
  User: fimia
  Date: 02.08.2022
  Time: 12:13
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.spring.utils.SortUtils" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Customers list</title>

    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css"/>
</head>
<body>
    <div id="wrapper">
        <div id="header">
            <h2>CRM - Customer Relationship Manager</h2>
        </div>
    </div>
    <div id="container">
        <hr>
        <p>
            <security:authorize access="isAuthenticated()">
                Logged in as <security:authentication property="principal.username" />
            </security:authorize>
        </p>
        <hr>
        <div id="content">
            <table>
                <tr>
                    <th>Username</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                </tr>
                <c:forEach var="user" items="${users}">
                    <tr>
                        <td> ${user.username}</td>
                        <td> ${user.firstName}</td>
                        <td> ${user.lastName}</td>
                        <td> ${user.email}</td>
                    </tr>

                </c:forEach>
            </table>
        </div>
    </div>

    <hr>

    <p>
        <a href="${pageContext.request.contextPath}/issue/list">Issues</a>
    </p>

    <hr>

    <br><br>
    <form:form action="${pageContext.request.contextPath}/logout" method="post">
        <input type="submit" value="Logout"/>
    </form:form>


</body>
</html>
