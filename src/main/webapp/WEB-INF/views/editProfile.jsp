<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit Profile</title>
<link href="<c:url value="/resources/sample.css" />"  rel="stylesheet" type="text/css"  />


<script language="javascript" type="text/javascript">

function load(){
	var color = "${user.bgcolor}";
	document.getElementById(color).checked = true;
	
	
	var topscore = "${user.topscoreChecked}";
	
	if (topscore.contains("true"))
		document.getElementById("topscore").checked = true;
	
	var favgame = "${user.favgame}";
	document.getElementById(favgame).checked = true;

}

</script>

<c:if test="${not empty user.fname}">
	<c:out value="Welcome, ${user.fname}"></c:out>
	<c:out value="${user.bgcolor}"></c:out>
	<c:out value="${user.topscoreChecked}"></c:out>
	<c:out value="${user.favgame}"></c:out>
</c:if>



</head>
<body onload="return load();">
<div><b><font size="8"> Cmpe281 </font></b></div>
<div><b><font size="4">Team5</font></b></div>
<div><b><font size="3">Edit Profile</font></b></div>

<br>
<form id="formEdit" method="post" action="editProfile">

	<br><div class="form-row"><label>First Name</label> <input type="text"  style="position: absolute; left: 140px;" type="text"  value = "${user.fname}" required name="fname"></div>
	<br><div class="form-row"><label>Last Name</label> <input type="text"  style="position: absolute; left: 140px;" type="text"  value = "${user.lname}" name="lname"></div>
	<br><div class="form-row"><label>Username</label> <input type="text"  style="position: absolute; left: 140px;" type="text" value = "${user.username}" required name="username"></div>
	<br><div class="form-row"><label>Password</label> <input type="password"  style="position: absolute; left: 140px;" type="text"  value = "${user.password}" required name="password"></div>

	<div class="form-row"><span class="label"><font size="3"> Background Color </font></span></div><br>
<br>
   <div  class="form-row"> 
		<table border="0">
		<tr>
              <td>
              <table>
				<td><input type="radio" name="colors" value="whitesmoke" /></td>
				<td><div id = "whitesmoke" class="colors" style="background-color:whitesmoke;border:2px solid ##420101;"></div></td>
				 </table>
              </td>
			
			<td>
              <table>	
				<td><input type="radio" name="colors" value="honeydew" /></td>
				<td><div id = "honeydew" class="colors" style="background-color:honeydew;border:2px solid ##420101;"></div></td>
			  </table>
			</td>
				
			<td>
              <table>
				<td><input type="radio" name="colors" value="lightyellow"/></td>
				<td><div id = "lightyellow" class="colors" style="background-color:lightyellow;border:2px solid ##420101;"></div></td>
			  </table>
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
	
</form>
</body>
</html>