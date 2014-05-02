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

<%String favgame = (String)session.getAttribute("favgame"); %>

function submitForm(cell){
	document.getElementById("game").value = cell;
	document.forms.form.submit();
}

</script>
</head>

<body>
	<div class="container">
			<div id="navbar">
				<jsp:include page="navbar.jsp"></jsp:include>
			</div>

		<form id="form" method="get" action="play">
			<input type="hidden" name="username" id="username" value="<%=username %>"/>	
			<input type="hidden" name="game" id="game" />
			<br>	
			<center>
				<font size="4" color="darkblue" ><b>Welcome <%=username %></b> </font> <br>
				<font size="3" color="darkblue" >Click on the icon to play the game</font>
			</center>
			
			<center>
				<table style="margin-top:20px;text-align:center;">
            	<tr>
					<c:if test ='<%=favgame.equalsIgnoreCase("scramble") %>'>
						<td width="450px" height="450px"> <div class="image">  <img src="<c:url value="/resources/scramble.png" />" alt="" height="400" width="400" style="border-radius:20px;" onclick="submitForm('scramble');"/></div> </td>   
					</c:if>
				
					<c:if test ='<%=favgame.equalsIgnoreCase("WhatsYourTech") %>'>
						<td width="450px" height="450px"> <div class="image">  <img src="<c:url value="/resources/icon.png" />" alt="" height="400" width="400" style="border-radius:20px;" onclick="submitForm('WhatsYourTech');"/></div> </td>   
					</c:if>
				
					<c:if test ='<%=favgame.equalsIgnoreCase("memory") %>'>
						 <td width="450px" height="450px"> <div class="image">  <img src="<c:url value="/resources/memory.jpg" />" alt="" height="400" width="400" style="border-radius:20px;" onclick="submitForm('memory');" /></div> </td>   
					</c:if>
			
					<c:if test ='<%=favgame.equalsIgnoreCase("Puzzler") %>'>
						<td width="450px" height="450px"> <div class="image">  <img src="<c:url value="/resources/Puzzler1.jpg" />" alt="" height="400" width="400" style="border-radius:20px;" onclick="submitForm('Puzzler');"/></div> </td>   
					</c:if>
				</tr>
				</table>
			</center>
		</form>
	</div>
</body>
</html>
