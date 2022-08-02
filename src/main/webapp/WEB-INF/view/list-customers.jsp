<%--
  Created by IntelliJ IDEA.
  User: fimia
  Date: 02.08.2022
  Time: 12:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <div id="content">
            <table>
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                </tr>
                <c:forEach var="customer" items="${customers}">

                    <tr>
                        <td> ${customer.firstName}</td>
                        <td> ${customer.lastName}</td>
                        <td> ${customer.email}</td>
                    </tr>

                </c:forEach>
            </table>
        </div>
    </div>

</body>
</html>
