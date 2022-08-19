<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Add customer</title>
    <link type="text/css"
          rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/style.css">

    <link type="text/css"
          rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/add-issue-style.css">
</head>
<body>

  <div id="wrapper">
      <div id="header">
            <h2>
                Issue database
            </h2>
      </div>
  </div>

<div>

    <h3>
        Save issue
    </h3>
    <form:form action="saveIssue" modelAttribute="issue" method="post">
        <form:hidden path="id"/>
        <table>
            <tbody>
                <tr>
                    <td><label>Description:</label></td>
                    <td><form:input path="description"/></td>
                </tr>
                <tr>
                    <td><label>Your name:</label></td>
                    <td><form:input path="customer"/></td>
                </tr>
                <tr>
                    <td><label>Status:</label></td>
                    <td><form:select path = "status">
                        <form:option value = "OPEN" label = "OPEN"/>
                        <form:option value = "CLOSED" label = "CLOSED"/>
                    </form:select></td>
                </tr>
                <tr>
                    <td><label></label></td>
                    <td><input type="submit" value="Save" class="save"/></td>
                </tr>
            </tbody>

        </table>
    </form:form>

    <div style="clear; both;"> </div>

    <p>
        <a href="${pageContext.request.contextPath}/issue/list">Back to list</a>
    </p>

</div>

</body>
</html>
