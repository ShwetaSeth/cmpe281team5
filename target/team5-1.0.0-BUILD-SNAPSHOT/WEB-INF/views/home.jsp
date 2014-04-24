<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	<link href="<c:url value="/resources/sample.css" />"  rel="stylesheet" type="text/css"  />
</head>
<body>
<div><b><font size="8"> CmpE281 </font></b></div>
<div><b><font size="4">Team5</font></b></div>
<div><b><font size="4" style="position: absolute; right: 50px; top: 150px">Sign In</font></b></div><br>
<form id="form1" method="post" action="signin">
	<div class="form-row"> <font color="red" size="2" style="position: absolute; right: 50px; top: 180px">${message}</font></div><br><br>
	<div class="form-row"><span class="label"><font size="3" style="position: absolute; right: 230px; top:203px"> Username </font> </span><input style="position: absolute; right: 50px; top: 200px" type="text" name="username" /></div><br>
	<div class="form-row"><span class="label"><font size="3" style="position: absolute; right: 230px; top: 233px"> Password </font> </span><input style="position: absolute; right: 50px; top: 230px" type="password" name="password" /></div><br>
	<div class="form-row"><font size="2" style="position: absolute; right: 120px; top: 265px"> New User? <a href = "signup">Register</a> </font> <input class="submit" type="submit" value="Sign In" style="position: absolute; right: 50px; top: 260px"></div><br>
</form>
</body>

</html>
