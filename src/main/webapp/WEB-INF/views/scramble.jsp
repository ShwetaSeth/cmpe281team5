<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CMPE Scramble</title>
 <style type="text/css">
<%@ include file="/resources/profile.css" %>
</style> 
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<% 
  
String myColor = (String)session.getAttribute("color");  
  if (myColor == null || myColor == ""){  
	myColor = "red";
  }
  
  
%>  
   


<%  
String mins = request.getParameter( "mins" );  
if( mins == null ) mins = "1";  
   
String secs = request.getParameter( "secs" );  
if( secs == null ) secs = "1";  
%>  

<script>  
window.onload=function(){
    document.getElementById("word").focus();
};


 
var mins = <%=mins%>; // write mins to javascript  
var secs = <%=secs%>; // write secs to javascript  
function timer()  
{  
  
if( --secs == -1 )  
{  
secs = 59;  
--mins;  
}  
  
// leading zero? formatting  
if( secs < 10 ) secs = "0" + secs;               
if( mins < 10 ) mins = "0" + parseInt( mins, 10 );  
  
// display  
document.forma.mins.value = mins;   
document.forma.secs.value = secs;  
  
// continue?  
if( secs == 0 && mins == 0 ) // time over  
{  
document.forma.Submit.disabled = true;  
document.forma.mins.disabled = true; 
document.forma.secs.disabled = true; 
document.formb.results.style.display = "block"; 


}  
else // call timer() recursively every 1000 ms == 1 sec  
{  
window.setTimeout( "timer()", 1000 );  
}  
}  
//-->  
</script></head>  
<div id="navbar">
				<jsp:include page="navbar.jsp"></jsp:include>
				
</div>
 <center>
				<font size="4" color="darkblue" ><b>Welcome, ${user.fname}</b> </font> <br>
				<font size="3" color="darkblue" >Scramble</font>
</center>

<body bgcolor="<%=myColor %>">
<div id = "content">
<h3>Enter any cloud related words seen in the grid - hortizonally, vertically, or backwards !! Have fun !!</h3>


<form  name="forma" method="post" action="scramble">
Time Remaining: <input type="text" name="mins" size="1" style="border:0px solid black;text-align:right">:<input type="text" name="secs" size="1" style="border:0px solid black">  


<div>
<c:if test="${topscoreChecked ne 'false' }">
<label>Highest Score :</label><input type="text" value = "${highScore}" name = "highScore"/>
</c:if>
</div>	


<center>
<table class = "CSSTableGenerator" border = '1'>

<tr><td>a</td><td>m</td><td>a</td><td>z</td><td>o</td><td>n</td><td>a</td><td>a</td></tr>
<tr><td>a</td><td>w</td><td>a</td><td>a</td><td>a</td><td>a</td><td>a</td><td>a</td></tr>
<tr><td>a</td><td>a</td><td>s</td><td>a</td><td>a</td><td>a</td><td>a</td><td>a</td></tr>
<tr><td>a</td><td>a</td><td>a</td><td>a</td><td>a</td><td>a</td><td>a</td><td>a</td></tr>
<tr><td>a</td><td>a</td><td>a</td><td>a</td><td>a</td><td>a</td><td>a</td><td>k</td></tr>
<tr><td>d</td><td>y</td><td>n</td><td>a</td><td>m</td><td>o</td><td>a</td><td>a</td></tr>
<tr><td>a</td><td>a</td><td>a</td><td>a</td><td>a</td><td>i</td><td>i</td><td>a</td></tr>
<tr><td>a</td><td>a</td><td>a</td><td>a</td><td>r</td><td>r</td><td>a</td><td>a</td></tr>

</table>
</center>
<label>Enter the words</label><input type="text" id = "word" name = "word"><button name="Submit" value="Submit"  onclick="load()">Submit</button>
<label>Score</label><input type="text" value = "${score}" name = "word"/>


</form>	
<hr>  
<form action="results" name="formb" method="post">  
<input type="hidden" value = "${score}" name = "score"/>
<input type="submit" name="results" value="View and Save Results" style="display:none;">   
</form>  
<script>  
<!--  
timer(); // call timer() after page is loaded  
//-->  
</script>  
<!-- <hr>
<form action="results" name="formb">  
<input type="submit" name="results" value="show results" style="display:none;">   
</form>  
 -->
 </div>
</body>
</html>
