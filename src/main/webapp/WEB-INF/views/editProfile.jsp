<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit Profile</title>
<link href="<c:url value="/resources/sample.css" />"  rel="stylesheet" type="text/css"  />


<script language="javascript" type="text/javascript">

function validate(){

	var fname = document.forms.form1.fname.value;
	var lname = document.forms.form1.lname.value;
	var password = document.forms.form1.password.value;
	var password1 = document.forms.form1.password1.value;

	if(fname == "" || lname == "" || password == "" || password1 == ""){
		alert("Please enter all the values.");
		return false;
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


function load(){
	document.getElementById("${user.bgcolor}").checked = true;
	
	var topscoreval = "${user.topscoreChecked}";
	
	if(topscoreval == "false")
	   document.getElementById("topscore").checked = false;
	else
		document.getElementById("topscore").checked = true;
	
	
	
	document.getElementById("${user.favgame}").checked = true;

}

</script>

</head>
<body onload="return load();">
<div><b><font size="8"> Cmpe281 </font></b></div>
<div><b><font size="4">Team5</font></b></div>
<div><b><font size="3">Edit Profile</font></b></div>

<br>
<form id="formEdit" method="post" action="editProfile">

	<div class="form-row"><span class="label"><font size="3"> *First name </font> </span><input size="30" style="position: absolute; left: 140px;" value = "${user.fname}" type="text" id="fname" name="fname" /></div><br>
	<div class="form-row"><span class="label"><font size="3"> *Last name </font> </span><input size="30" style="position: absolute; left: 140px;" value = "${user.lname}" type="text" id="lname" name="lname" /></div><br>
	<div class="form-row"><span class="label"><font size="3"> *Password </font> </span><input size="30" style="position: absolute; left: 140px;" value = "${user.password}" type="password" id="password" name="password" /></div><br>
	<div class="form-row"><span class="label"><font size="3"> *Retype Password </font> </span><input size="30" style="position: absolute; left: 140px;" value = "${user.password}" type="password" id="password1" name="password1" /></div><br>

	<div class="form-row"><span class="label"><font size="3"> Background Color </font></span></div><br>
<br>
   <div  class="form-row"> 
		<table border="0">
		<tr>
              <td>
              <table><tr>
				<td><input id = "whitesmoke" type="radio" name="colors" value="whitesmoke" /></td>
				<td><div id = "whitesmoke" class="colors" style="background-color:whitesmoke;border:2px solid ##420101;"></div></td>
			  </tr></table>
              </td>
			
			<td>
              <table><tr>	
				<td><input  id = "honeydew" type="radio" name="colors" value="honeydew" /></td>
				<td><div id = "honeydew" class="colors" style="background-color:honeydew;border:2px solid ##420101;"></div></td>
			  </tr></table>
			</td>
				
			<td>
              <table><tr>
				<td><input id = "lightyellow" type="radio" name="colors" value="lightyellow"/></td>
				<td><div id = "lightyellow" class="colors" style="background-color:lightyellow;border:2px solid ##420101;"></div></td>
			  </tr></table>
			</td>
			</tr>
			</table>
	</div>
	
	<br>
	<div class="form-row">
	<input size="30" type="checkbox" id="topscore" name="topscore" value= "topscore" />
	<span class="label"><font size="3">See my highest score when playing game </font> </span>
	</div><br>
	
	<div class="form-row"><span class="label"><font size="3">Select a game to start at log in </font> </span>
	
		<table border="0">
		<tr>
              <td>
              <table><tr>
                <td><input id = "scramble" type="radio" name="favgame" value="scramble" /></td>
                <td width="250px" height="250px"> <div id = "scramble" class="image">  <img src="<c:url value="/resources/scramble.png" />" alt="" height="200" width="200" style="border-radius:20px;" /></div> </td>   
              </tr></table>
              </td>
            
              <td>
              <table><tr>
                <td><input id = "WhatsYourTech"  type="radio" name="favgame" value="WhatsYourTech" /></td>
                <td width="250px" height="250px"> <div id = "WhatsYourTech" class="image">  <img src="<c:url value="/resources/icon.png" />" alt="" height="200" width="200" style="border-radius:20px;" /></div> </td>   
              </tr></table>
              </td>
              
             <td>
              <table><tr>
                <td><input id = "memory"  type="radio" name="favgame" value="memory" /></td>
                <td width="250px" height="250px"> <div id = "memory"  class="image">  <img src="<c:url value="/resources/memory.jpg" />" alt="" height="200" width="200" style="border-radius:20px;" /></div> </td>   
           	  </tr></table>
              </td>
            
             <td>
              <table><tr>
                <td><input id = "Puzzler"  type="radio" name="favgame" value="Puzzler" /></td>
                <td width="250px" height="250px"> <div id = "Puzzler" class="image">  <img src="<c:url value="/resources/Puzzler1.jpg" />" alt="" height="200" width="200" style="border-radius:20px;" /></div> </td>   
              </tr></table>
             </td>      
        </table>

     </div>
	<div class="form-row"><input class="submit" type="submit" value="Update" style="position: absolute; left: 230px;" ></div><br>
</form>
</body>
</html>