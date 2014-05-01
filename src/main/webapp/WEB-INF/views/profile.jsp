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
<div class = "content">


<div id="navbar">
		<jsp:include page="navbar.jsp"></jsp:include>
</div>
<h1> Welcome <%=username %></h1>

<form id="form" method="get" action="play">

	<input type="hidden" name="username" id="username" value="<%=username %>"/>	
	<input type="hidden" name="game" id="game" />
	
<h1>Click on game to play game with customized preferences: </h1>

<center>
	<table style="margin-top:200px;">
            <tr>
				<div>
					<c:if test ='<%=favgame.equalsIgnoreCase("scramble") %>'>
					<td width="250px" height="250px"> <div class="image">  <img src="<c:url value="/resources/scramble.png" />" alt="" height="200" width="200" style="border-radius:20px;" onclick="submitForm('scramble');"/></div> </td>   
					</c:if>
				</div>
				
				<div>
					<c:if test ='<%=favgame.equalsIgnoreCase("WhatsYourTech") %>'>t>
					<td width="250px" height="250px"> <div class="image">  <img src="<c:url value="/resources/icon.png" />" alt="" height="200" width="200" style="border-radius:20px;" onclick="submitForm('WhatsYourTech');"/></div> </td>   
					</c:if>
				</div>
				
				<div>
					<c:if test ='<%=favgame.equalsIgnoreCase("memory") %>'>t>
					 <td width="250px" height="250px"> <div class="image">  <img src="<c:url value="/resources/memory.jpg" />" alt="" height="200" width="200" style="border-radius:20px;" onclick="submitForm('memory');" /></div> </td>   
					</c:if>
				</div>
				
				<div>
					<c:if test ='<%=favgame.equalsIgnoreCase("Puzzler") %>'>t>
					<td width="250px" height="250px"> <div class="image">  <img src="<c:url value="/resources/Puzzler1.jpg" />" alt="" height="200" width="200" style="border-radius:20px;" onclick="submitForm('Puzzler');"/></div> </td>   
					</c:if>
				</div>
			
			</tr>
	</table>
	</center>

</form>
</div>
</div>
</body>
</html>
