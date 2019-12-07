<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*, gameModel.gameDB" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<a href="http://localhost:8080/gameDataBase/updateWeapon">Update the weapon being used</a><br/>
	<a href="http://localhost:8080/gameDataBase/updateArmour">Update the armour being used</a><br/>
<form action='updateBy' method='post'>
	<h3>Or else</h3><br/>
	<b>Update</b>
	<select name="field">
	<option value="username">Username</option>
	<option value="level">Level</option>
	</select>
	<b>of user</b><br/>
	
	<table>
	<%
	new gameDB();
	String qry="SELECT playerid,username,wid,wname"
	+" from player, class, weapons,armour,stats where playerid=pid and classid=cid and weaponid=wid and armourid=aid";
	gameDB.rs=gameDB.stmt.executeQuery(qry);
	while(gameDB.rs.next())
	{
	%>
		<tr>
		<td><input type ="radio" name="pid" value="<%=gameDB.rs.getInt(1)%>"/></td>
		<td><%= gameDB.rs.getString(1) %></td>
		<td><%= gameDB.rs.getString(2) %></td>
		</tr>
	<%
	}		
	%>
</table><br/>
	
	<b>to:</b>
	<input type="text" name="value"/>
	<input type="submit" value="Update"/>
</form>
</body>
</html>