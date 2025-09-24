<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<html>
<head>
    <title>Main page</title>
</head>
<body>

<%
    String sessionUser = (String) session.getAttribute("user");
    if (sessionUser == null) {
        response.sendRedirect("login.html");
        return;
    }

    String cookieUser = "";
    String sessionId = session.getId();
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie c : cookies) {
            if ("user".equals(c.getName())) {
                cookieUser = c.getValue();
            }
        }
    }
%>

<h3>
    Hello, <%=sessionUser%>! Login successful
    <br>
    Session ID = <%=sessionId%>
    <br>
    Cookie user = <%=cookieUser%>
</h3>

<a href="login.html">Logout</a>

</body>
</html>