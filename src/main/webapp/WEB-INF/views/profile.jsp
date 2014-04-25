<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>    

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome!</title>
<link href="<c:url value="/resources/sample.css" />"  rel="stylesheet" type="text/css"  />
</head>

<body>
<font size=3 style="position: absolute; right:50px; top:50px"> Welcome ${username}  <a href = "signout"> Sign out</a> <br> </font>

<form id="form" method="post" action="profile">
	<input type="hidden" name="username" id="username" value="${username}"/>	
	<input type="hidden" name="game" id="game" />
	
	<center>
	<table style="margin-top:200px;">
		<tr>
			<td> <a href="scramble"> Scramble </a> </td>
			<td> <a href=""> <img src="<c:url value="/resources/icon.png" />" alt="" height="200" width="200" style="border-radius:25px;"/> </a> </td>
			<td> <a href="profile"> Game3 </a> </td>
			<td> <a href="profile"> Game4 </a> </td>
		</tr>	
	</table>
	</center>
</form>

</body>
</html>