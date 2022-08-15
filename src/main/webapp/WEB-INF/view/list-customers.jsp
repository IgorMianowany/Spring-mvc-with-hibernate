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
            User: <security:authentication property="principal.username"/>
            <br><br>
            Role (s): <security:authentication property="principal.authorities"/>
        </p>
        <hr>
        <div id="content">

            <security:authorize access="hasAnyRole('MANAGER', 'ADMIN')">
                <!-- put new button: Add Customer -->
                <input type="button" value="Add Customer"
                       onclick="window.location.href='showFormForAdd';
                       return false;"
                       class="add-button"
                />
            </security:authorize>

            <form:form action="search" method="GET">
                Search customer: <input type="text" name="searchName"/>

                <input type="submit" value="Search" class="add-button"/>
            </form:form>

            <table>
                <c:url var="sortLinkFirstName" value="/customer/list">
                    <c:param name="sort" value="<%= Integer.toString(SortUtils.FIRST_NAME) %>" />
                </c:url>

                <c:url var="sortLinkLastName" value="/customer/list">
                    <c:param name="sort" value="<%= Integer.toString(SortUtils.LAST_NAME) %>" />
                </c:url>

                <c:url var="sortLinkRole" value="/customer/list">
                    <c:param name="sort" value="<%= Integer.toString(SortUtils.ROLE) %>" />
                </c:url>

                <tr>
                    <th><a href="${sortLinkFirstName}">First Name</a></th>
                    <th><a href="${sortLinkLastName}">Last Name</a></th>
                    <th><a href="${sortLinkRole}">Role</a></th>
                    <th>Action</th>
                </tr>
                <c:forEach var="customer" items="${customers}">
                    <c:url var="updateLink" value="/customer/showFormForUpdate">
                        <c:param name="customerId" value="${customer.id}"/>
                    </c:url>

                    <c:url var="deleteLink" value="/customer/delete">
                        <c:param name="customerId" value="${customer.id}"/>
                    </c:url>



                    <tr>
                        <td> ${customer.firstName}</td>
                        <td> ${customer.lastName}</td>
                        <td> ${customer.role}</td>
                        <td>
                            <a href="${updateLink}">Update</a>
                             |
                            <a href="${deleteLink}"
                                onclick="if(!(confirm('Are you sure you want to delete this customer?')))return false">
                                Delete</a>
                        </td>
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
