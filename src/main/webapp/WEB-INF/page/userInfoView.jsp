<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Info</title>
</head>
<body>
	<%@ include file="navbar.jsp" %>
	
	<h3>Hello: ${user.emailId}</h3>
	
	User Name: <b>${user.emailId}</b>
	<br/>
	
	<%@ include file="footer.jsp" %>
</body>
</html>