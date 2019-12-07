<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="gameModel.gameDB" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sorted Values</title>
</head>
<body>
<table>
<tr>
<td>ID</td>
<td>Username</td>
<td>Level</td>
<td>Class</td>
<td>Weapon</td>
<td>Armour</td>
<td>Max HP</td>
<td>Max MP</td>
<td>P_Atk</td>
<td>M_Atk</td>
<td>Def</td>
<td>Rec</td>
<td>Mob</td>
</tr>
</table>
<br/>
<table>
	<%
	String order=request.getParameter("order");
	String field=request.getParameter("field");
	new gameDB();
	String qry="SELECT playerid,username,level,cname,wname,aname, maxhp, maxmp, phys_attack, magic_attack, defence, recovery, mobility"
	+" from player, class, weapons,armour,stats where playerid=pid and classid=cid and weaponid=wid and armourid=aid order by "+field+" "+order;
	gameDB.rs=gameDB.stmt.executeQuery(qry);
	while(gameDB.rs.next())
	{
	%>
		<tr>
		<td><%= gameDB.rs.getInt(1) %></td>
		<td><%= gameDB.rs.getString(2) %></td>
		<td><%= gameDB.rs.getInt(3) %></td>
		<td><%= gameDB.rs.getString(4) %></td>
		<td><%= gameDB.rs.getString(5) %></td>
		<td><%= gameDB.rs.getString(6) %></td>
		<td><%= gameDB.rs.getInt(7) %></td>
		<td><%= gameDB.rs.getInt(8) %></td>
		<td><%= gameDB.rs.getInt(9) %></td>
		<td><%= gameDB.rs.getInt(10) %></td>
		<td><%= gameDB.rs.getInt(11) %></td>
		<td><%= gameDB.rs.getInt(12) %></td>
		<td><%= gameDB.rs.getInt(13) %></td>
		</tr>
	<%
	}		
	%>
</table>
</body>
</html>