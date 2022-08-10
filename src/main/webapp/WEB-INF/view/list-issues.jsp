<%--
  Created by IntelliJ IDEA.
  User: fimia
  Date: 02.08.2022
  Time: 12:13
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Issue list</title>

    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css"/>
</head>
<body>
    <div id="wrapper">
        <div id="header">
            <h2>Issue database</h2>
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

            <input type="button" value="Add issue"
                   onclick="window.location.href='showFormForIssueAdd';
                   return false;"
                   class="add-button"
            />

            <form:form action="search" method="GET">
                Search issue: <input type="text" name="searchName"/>

                <input type="submit" value="Search" class="add-button"/>
            </form:form>

            <table>

                <tr>
                    <th><a>ID</a></th>
                    <th><a>Description</a></th>
                    <th><a>Customer Name</a></th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>
                <c:forEach var="issue" items="${issues}">
                    <c:url var="updateLink" value="/issue/showFormForIssueUpdate">
                        <c:param name="issueId" value="${issue.id}"/>
                    </c:url>

                    <c:url var="deleteLink" value="/issue/delete">
                        <c:param name="issueId" value="${issue.id}"/>
                    </c:url>



                    <tr>
                        <td> ${issue.id}</td>
                        <td> ${issue.description}</td>
                        <td> ${issue.customer.firstName} ${issue.customer.lastName}</td>
                        <td> ${issue.status}</td>
                        <td>
                            <a href="${updateLink}">Update</a>
                             |
                            <a href="${deleteLink}"
                                onclick="if(!(confirm('Are you sure you want to delete this issue?')))return false">
                                Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>

    <hr>

    <p>
        <a href="${pageContext.request.contextPath}/customer/leaders">Leadership meeting</a>
        <br>
        <a href="${pageContext.request.contextPath}/customer/systems">Admin meeting</a>
        <br>
        <a href="${pageContext.request.contextPath}/customer/list">Customer list</a>
    </p>

    <hr>

    <br><br>
    <form:form action="${pageContext.request.contextPath}/logout" method="post">
        <input type="submit" value="Logout"/>
    </form:form>


</body>
</html>