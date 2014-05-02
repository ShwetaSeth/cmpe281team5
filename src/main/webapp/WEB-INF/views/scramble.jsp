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
	document.getElementById('timer').style.visibility='visible';  
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
<input type="button" id="start" name="start" value="Start Game" onclick="timer();" style="height:50px;width:100px;"/>
<div id="timer" style="visibility:hidden;">Time Remaining: <input type="text" name="mins" size="1" style="border:0px solid black;text-align:right">:<input type="text" name="secs" size="1" style="border:0px solid black"></div>  


<div>
<c:if test="${topscoreChecked ne 'false' }">
<label>Highest Score :</label><input type="text" value = "${highScore}" name = "highScore"/>
</c:if>
</div>	


<center>
<table class = "CSSTableGenerator" border = '1'>

<tr><td>O</td><td>R</td><td>A</td><td>C</td><td>L</td><td>E</td><td>G</td><td>J</td><td>K</td><td>S</td></tr>
<tr><td>B</td><td>I</td><td>B</td><td>M</td><td>L</td><td>O</td><td>A</td><td>D</td><td>D</td><td>E</td></tr>
<tr><td>O</td><td>A</td><td>L</td><td>L</td><td>O</td><td>O</td><td>A</td><td>F</td><td>Y</td><td>R</td></tr>
<tr><td>R</td><td>K</td><td>M</td><td>G</td><td>A</td><td>Z</td><td>U</td><td>G</td><td>N</td><td>V</td></tr>
<tr><td>D</td><td>L</td><td>L</td><td>C</td><td>S</td><td>W</td><td>Y</td><td>G</td><td>A</td><td>E</td></tr>
<tr><td>E</td><td>E</td><td>D</td><td>L</td><td>A</td><td>N</td><td>S</td><td>H</td><td>M</td><td>R</td></tr>
<tr><td>S</td><td>A</td><td>M</td><td>I</td><td>A</td><td>M</td><td>A</td><td>Z</td><td>O</td><td>N</td></tr>
<tr><td>P</td><td>Q</td><td>R</td><td>S</td><td>S</td><td>W</td><td>X</td><td>B</td><td>C</td><td>L</td></tr>
<tr><td>T</td><td>U</td><td>R</td><td>I</td><td>A</td><td>K</td><td>V</td><td>D</td><td>E</td><td>M</td></tr>

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
/*timer();*/ // call timer() after page is loaded  
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
