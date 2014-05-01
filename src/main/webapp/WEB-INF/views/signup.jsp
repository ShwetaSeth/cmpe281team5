<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sign Up</title>
<link href="<c:url value="/resources/sample.css" />"  rel="stylesheet" type="text/css"  />

<script language="javascript" type="text/javascript">

function validate(){
	
	var fname = document.forms.form1.fname.value;
	var lname = document.forms.form1.lname.value;
// 	var email_id = document.forms.form1.email_id.value;
	var username = document.forms.form1.username.value;
	var password = document.forms.form1.password.value;
	var password1 = document.forms.form1.password1.value;
// 	var atpos = email_id.indexOf("@");
// 	var dotpos = email_id.lastIndexOf(".");
	
	if(fname == "" || lname == "" || username == "" || password == "" || password1 == ""){
		alert("Please enter all the values.");
		return false;
	}
	
// 	if ( (atpos < 1) || (dotpos < (atpos+2)) || ((dotpos+2) >= email_id.length))
// 	{
// 		alert("Not a valid e-mail address");
// 		return false;
// 	}
	
	var uChars = "!`~@#$%^&*()+=-[]\\\';,/{}|\":<>?";
	for (var j = 0; j < username.length; j++) {
	    if (uChars.indexOf(username.charAt(j)) != -1) {
	    	alert ("Invalid Username.");
	    	return false;
	    }
	}
	
	if(password.length<6){
		alert("Password too small.");
		return false;
	}
	
	if(password.length>16){
		alert("Password too long.");
		return false;
	}
	
	var iChars = "!`~@#$%^_&*()+=-[]\\\';,/{}|\":<>?";
	for (var i = 0; i < password.length; i++) {
	    if (iChars.indexOf(password.charAt(i)) != -1) {
	    	alert ("No special characters in password.");
	    	return false;
	    }
	}	
	
	if(password != password1){
		alert("Passwords don't match.");
		return false;
	}
}

function addColor(cell){
	
		
	if(document.getElementById(cell).style.border == "+l")
		{
		document.getElementById(cell).style.border = "2px solid ##420101";
		}
	else
		{
		document.getElementById(cell).style.border = "2px solid #000000";
		document.getElementById("bgcolor").value = cell;
		}
	
}

</script>

</head>
<body>
<div><b><font size="8"> CmpE281 </font></b></div>
<div><b><font size="4">Team5</font></b></div>
<div><b><font size="3">Register</font></b></div>
<font size=3 style="position: absolute; right:50px; top:50px"> <a href = "/team5/home"> Home</a><br></font> 
<div><font size="2" style="position: absolute; left: 440px; top: 150px">- Fields marked (*) are mandatory.</font></div>
<div><font size="2" style="position: absolute; left: 440px; top: 170px">- Username can be only alphanumeric and can contain (. and _).</font></div>
<div><font size="2" style="position: absolute; left: 440px; top: 190px">- Password must be between 6 and 16 characters and <br> must not have the special characters (!`~@#,$_%^&amp;*()+=-[]\/';{}|":&lt;&gt;?).</font></div>

<form id="form1" method="post" action="signup">
	<input type="hidden" name="bgcolor" id="bgcolor" value="whitesmoke"/>
	
	<div class="form-row"> <font color="red" size="2">${message}</font></div><br>
	<div class="form-row"><span class="label"><font size="3"> *First name </font> </span><input size="30" style="position: absolute; left: 140px;" type="text" id="fname" name="fname" /></div><br>
	<div class="form-row"><span class="label"><font size="3"> *Last name </font> </span><input size="30" style="position: absolute; left: 140px;" type="text" id="lname" name="lname" /></div><br>
	<div class="form-row"><span class="label"><font size="3"> *Username </font> </span><input size="30" style="position: absolute; left: 140px;" type="text" id="username" name="username" /></div><br>
	<div class="form-row"><span class="label"><font size="3"> *Password </font> </span><input size="30" style="position: absolute; left: 140px;" type="password" id="password" name="password" /></div><br>
	<div class="form-row"><span class="label"><font size="3"> *Retype Password </font> </span><input size="30" style="position: absolute; left: 140px;" type="password" id="password1" name="password1" /></div><br>
	<div class="form-row"><span class="label"><font size="3"> Background Color </font></span></div><br>
	<div class="form-row"> 
		<table border="0">
		<tr>
              <td>
              <table>
				<td><input type="radio" name="colors" value="whitesmoke" onclick="addColor(whitesmoke)"/></td>
				<td><div id = "whitesmoke" class="colors" style="background-color:whitesmoke;border:2px solid ##420101;"></div></td>
				 </table>
              </td>
			
			<td>
              <table>	
				<td><input type="radio" name="colors" value="honeydew" onclick="addColor(honeydew)"/></td>
				<td><div id = "honeydew" class="colors" style="background-color:honeydew;border:2px solid ##420101;"></div></td>
			  </table>
			</td>
				
			<td>
              <table>
				<td><input type="radio" name="colors" value="lightyellow" onclick="addColor(lightyellow)"/></td>
				<td><div id = "lightyellow" class="colors" style="background-color:lightyellow;border:2px solid ##420101;"></div></td>
			  </table>
			</td>
			</tr>
			</table>
	</div>
	
	<br>
	<div class="form-row">
	<input size="30" type="checkbox" id="topscore" name="topscore" value= "topscore" />
	<span class="label"><font size="3">See my highest score when playing game </font> </span></div><br>
	
	<div class="form-row"><span class="label"><font size="3">Select a game to start at log in </font> </span>
	
		<table border="0">
		<tr>
              <td>
              <table>
                <td><input type="radio" name="favgame" value="scramble" /></td>
                <td width="250px" height="250px"> <div class="image">  <img src="<c:url value="/resources/scramble.png" />" alt="" height="200" width="200" style="border-radius:20px;" /></div> </td>   
              </table>
              </td>
            
              <td>
              <table>
                <td><input type="radio" name="favgame" value="WhatsYourTech" /></td>
                <td width="250px" height="250px"> <div class="image">  <img src="<c:url value="/resources/icon.png" />" alt="" height="200" width="200" style="border-radius:20px;" /></div> </td>   
              </table>
              </td>
              
             <td>
              <table>
                <td><input type="radio" name="favgame" value="memory" /></td>
                <td width="250px" height="250px"> <div class="image">  <img src="<c:url value="/resources/memory.jpg" />" alt="" height="200" width="200" style="border-radius:20px;" /></div> </td>   
           </table>
              </td>
            
             <td>
              <table>
                <td><input type="radio" name="favgame" value="Puzzler" /></td>
                <td width="250px" height="250px"> <div class="image">  <img src="<c:url value="/resources/Puzzler1.jpg" />" alt="" height="200" width="200" style="border-radius:20px;" /></div> </td>   
            </table>
             </td>      
        </table>

     </div>
	
	
	
	<div class="form-row"><input class="submit" type="submit" value="Sign Up" style="position: absolute; left: 230px;" onclick = "return validate();"></div><br>
</form>

</body>
</html>