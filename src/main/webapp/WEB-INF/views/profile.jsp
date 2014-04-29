<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%String username = (String)session.getAttribute("username"); %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome!</title>
<link href="<c:url value="/resources/profile.css" />"  rel="stylesheet" type="text/css"  />
<script language="javascript" type="text/javascript">

function submitForm(cell){
	document.getElementById("game").value = cell;
	document.forms.form.submit();
}

</script>

</head>

<body>
<font size=3 style="position: absolute; right:50px; top:30px"> Welcome <%=username %> <a href = "signout"> Sign out</a> <br> </font>

<form id="form" method="get" action="play">

	<input type="hidden" name="username" id="username" value="<%=username %>"/>	
	<input type="hidden" name="game" id="game" />
	
	<label>Choose your background color</label>
	<select name="backgroundColor" id = "backgroundColor">
        <option value="blue">Blue</option>
        <option value="green">Green</option>
        <option value="red">Red</option>
   </select>
	
	<center>
	<table style="margin-top:200px;">
            <tr>
                <td width="250px" height="250px"> <div class="image">  <img src="<c:url value="/resources/scramble.png" />" alt="" height="200" width="200" style="border-radius:20px;" onclick="submitForm('scramble');"/></div> </td>   
                <td width="250px" height="250px"> <div class="image">  <img src="<c:url value="/resources/icon.png" />" alt="" height="200" width="200" style="border-radius:20px;" onclick="submitForm('WhatsYourTech');"/></div> </td>   
                <td width="250px" height="250px"> <div class="image">  <img src="<c:url value="/resources/memory.png" />" alt="" height="200" width="200" style="border-radius:20px;" onclick="submitForm('memory');" /></div> </td>   
                <td width="250px" height="250px"> <div class="image">  <img src="<c:url value="/resources/Puzzler1.jpg" />" alt="" height="200" width="200" style="border-radius:20px;" onclick="submitForm('Puzzler');"/></div> </td>   
            </tr>
	</table>
	</center>
</form>

</body>
</html>
