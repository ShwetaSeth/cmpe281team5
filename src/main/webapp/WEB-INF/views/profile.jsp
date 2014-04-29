<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>    

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome!</title>
<link href="<c:url value="/resources/profile.css" />"  rel="stylesheet" type="text/css"  />

</head>

<body>
<font size=3 style="position: absolute; right:50px; top:30px"> Welcome ${username}  <a href = "signout"> Sign out</a> <br> </font>

<form id="form" method="get" action="play">
	<input type="hidden" name="username" id="username" value="${username}"/>	
	<input type="hidden" name="game" id="game" />
	
	<center>
	<table style="margin-top:200px;">
		<tr>
			<td width="250px" height="250px"> <div class="image"> <a href="scramble"> <img src="<c:url value="/resources/scramble.png" />" alt="" height="200" width="200" style="border-radius:20px;" /> </a> </div> </td>
			<td width="250px" height="250px"> <div class="image"> <a href="WhatsYourTech"> <img src="<c:url value="/resources/icon.png" />" alt="" height="200" width="200" style="border-radius:20px;" /> </a> </div> </td>
			<td width="250px" height="250px"> <div class="image"> <a href="profile"> <img src="<c:url value="/resources/game3.png" />" alt="" height="200" width="200" style="border-radius:20px;" /> </a> </div> </td>
			<td width="250px" height="250px"> <div class="image"> <a href="Puzzler"> <img src="<c:url value="/resources/Puzzler1.jpg" />" alt="" height="200" width="200" style="border-radius:20px;" /> </a> </div> </td>		
		</tr>	
	</table>
	</center>
</form>

</body>
</html>