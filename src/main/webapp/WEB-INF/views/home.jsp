<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	<link href="<c:url value="/resources/sample.css" />"  rel="stylesheet" type="text/css"  />
</head>

<body>
	<form  class="box login" id="form1" method="post" action="home">
		
		<h1>CMPE 281 Team 5 </h1>
	
		<fieldset class="boxBody">
			<label>Username</label> <input type="text" tabindex="1"
				placeholder="Enter Username"  name="username">
			<!-- <label><a href="#" class="rLink" tabindex="5">Forget your password?</a>Password</label> -->
			<label>Password</label>
			<input type="password" placeholder="Enter Password" tabindex="2" name="password">
		</fieldset>
		
		<footer>
			<label>New User?</label> <a href = "signup"><label>Register,</label></a>
			<!-- <label><input type="checkbox" tabindex="3">Keep me logged in</label>-->
			<input type="submit" class="btnLogin" value="Login" tabindex="3">
			
			
		</footer>
	</form>
	<footer id="main"> </footer>
</body>





<%-- <body>
<div><b><font size="8"> CmpE281 </font></b></div>
<div><b><font size="4">Team5</font></b></div>
<div><b><font size="4" style="position: absolute; right: 50px; top: 150px">Sign In</font></b></div><br>
<form id="form1" method="post" action="home">
	<div class="form-row"> <font color="red" size="2" style="position: absolute; right: 50px; top: 180px">${message}</font></div><br><br>
	<div class="form-row"><span class="label"><font size="3" style="position: absolute; right: 230px; top:203px"> Username </font> </span><input style="position: absolute; right: 50px; top: 200px" type="text" name="username" /></div><br>
	<div class="form-row"><span class="label"><font size="3" style="position: absolute; right: 230px; top: 233px"> Password </font> </span><input style="position: absolute; right: 50px; top: 230px" type="password" name="password" /></div><br>
	<div class="form-row"><font size="2" style="position: absolute; right: 120px; top: 265px"> New User? <a href = "signup">Register</a> </font> <input class="submit" type="submit" value="Sign In" style="position: absolute; right: 50px; top: 260px"></div><br>
</form>
</body> --%>

</html>
