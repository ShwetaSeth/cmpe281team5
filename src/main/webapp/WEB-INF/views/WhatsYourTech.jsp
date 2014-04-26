<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>What's Your Tech?</title>
	<link href="<c:url value="/resources/sample.css" />"  rel="stylesheet" type="text/css"  />
</head>
<body>
	
	<font size=3 style="position: absolute; right:50px; top:30px"> Welcome ${username}  <a href = "signout"> Sign out</a> <br> </font>
	
	<div id="container" style="width:1300px;height:1000px;margin-top:60px;">
		
		<div id="game" style="background-color:#D5D5D5;width:700px;float:left;margin-left:40px;margin-top:30px;padding-left:20px;">
			
			<div id="stats" style="background-color:#C5C5C5;height:100px;width:150px;float:right;margin-right:20px;margin-top:30px;">
				<br>
				<font size=4 style="padding-left:50px"> Time </font><br>
				<font size=4 style="padding-left:50px"> Score </font><br>
			</div>
			<br>
			<font size=3><b>Hint1</b></font>
			<br><hr style="width:200px;margin-left:20px;">
			<p style="width:400px;padding-left:20px;"> <font size=3> Framework developed by GoPivotal under Apache License </font> </p>
			<br><br>
			
			<font size=3><b>Hint2</b></font>
			<br><hr style="width:200px;margin-left:20px;">
			<p style="width:400px;padding-left:20px;"> <font size=3> Modules: IoC Container, Aspect oriented Programming, Transaction Management, Convention over Configuration (Roo) </font> </p>
			<br><br>
			
			<font size=3><b>Hint3</b></font>
			<br><hr style="width:200px;margin-left:20px;">
			<p style="width:400px;padding-left:20px;"> <font size=3> Has HTTP- and servlet-based framework providing hooks for extension and customization for web applications and RESTful web services </font> </p>
			<br>
			
			<form id="form" method="post" action="submitAnswer">
			
				<div class="form-row"> <input type="hidden" id="answer" name="answer" value="${answer}" /> </div> <br>
				<div class="form-row"> <input size="40" style="font-size:18;height:50px;margin-left:100px;" type="text" id="guess" name="guess" /> </div> <br>
				<div class="form-row"> <input class="submit" type="submit" value="Submit" style="font-size:18;width:150px;height:50px;margin-left:310px;" disabled="disabled" /></div>					

			</form>
			
		</div>
		
		<div id="content" style="background-color:#D5D5D5;height:500px;width:400px;float:left;margin-left:120px;margin-top:30;">
			<br><font size="3" style="padding-left:20px;"><b>Instructions:</b></font> <br>
			<center> <input type="button" value="Start" style="font-size:18;width:150px;height:50px;margin-top:400px;" disabled="disabled" /> </center>
		</div>
		
	</div>
</body>
</html>