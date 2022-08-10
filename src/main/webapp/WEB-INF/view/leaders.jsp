<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World! for leaders" %>
</h1>
<br/>
<a href="TestDbServlet">Hello Servlet</a>
<br>
<a href="${pageContext.request.contextPath}/customer/list">Back to list</a>
</body>
</html>