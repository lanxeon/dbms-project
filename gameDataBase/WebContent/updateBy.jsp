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
<%!
String field,value,qry;
%>
<%
new gameDB();
field=request.getParameter("field");
value=request.getParameter("value");
qry="SELECT playerid,username,level,cname,wname,aname, maxhp, maxmp, phys_attack, magic_attack, defence, recovery, mobility"
	+" from player, class, weapons,armour,stats where playerid=pid and classid=cid and weaponid=wid and armourid=aid and "+field+" like '%"+value+"%'";
gameDB.rs=gameDB.stmt.executeQuery(qry);
if(!gameDB.rs.next())
	out.println("<b>There are no players with players with "+field+" of "+value+"<b>");
	
	else
	{
		
	}
		%>
</body>
</html>