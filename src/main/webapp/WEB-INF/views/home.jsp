<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Home</title>
	<link href="<c:url value="/resources/sample.css" />"  rel="stylesheet" type="text/css"  />
	
	<script language="javascript" type="text/javascript">
		function signup(){
			document.location.href("signup");
		}
	</script>

</head>

<body>
	<form  class="box login" id="form1" method="post" action="home">
		
		<fieldset class="boxBody" style="position:relative;top:40px;margin:0 auto;width:300px;height:400px;text-align:center;">
			<legend style="text-align:left;Font">
				CMPE 281 Team 5
			</legend>
			
			<input type="text" style="margin-top:30px;height:40px;width:250px;padding:10px;" tabindex="1" placeholder="Enter Username"  name="username" id="username" />
			<input type="password" style="margin-top:20px;height:40px;width:250px;padding:10px;" tabindex="2" placeholder="Enter Password" name="password" id="password" />
			<div style="margin-top:20px;position:relative;right:30px;text-align:right;">
				<font color="red" size="2">${message}</font>
			</div>
			<input type="submit" class="submit" style="margin-top:30px;background-color:#6E329D;border:1px solid #522675;border-radius:2px;width:200px;height:50px;font:20px arial,helvetica,clean,sans-serif;color:white;" value="Login" tabindex="3" />
			<br>
			<div style="margin:0 auto;height:1px;background-color:black;margin-top:30px;width:250px;">
				<span style="background-color:white;position:relative;top:-0.6em;"> OR </span> 
			</div>
			<a href="signup"><input type="button" class="button" style="margin-top:30px;background-color:#0B70BE;border:1px solid #0b5fa2;border-radius: 2px;width:200px;height:50px;font:20px arial,helvetica,clean,sans-serif;color:white;" value="Create New Account" tabindex="4" /></a>							
		</fieldset>
			
	</form>
	<footer id="main"> </footer>
</body>

</html>
