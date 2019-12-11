<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="getPlayers.jsp"/><br/><br/>

<h4>Choose which user to delete</h4>
<form action="delete" method="POST">
	<b>Username to delete:</b><input type="text" name="usn"/>
	<input type="submit" value="Delete User"/>
</form>
</body>
</html>