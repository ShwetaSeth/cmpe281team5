<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CMPE Scramble</title>
<%-- <style type="text/css">
<%@ include file="/resources/profile.css" %>
</style> --%>

<%  
  
String myColor = request.getParameter("color");  
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
 
var mins = <%=mins%>; // write mins to javascript  
var secs = <%=secs%>; // write secs to javascript  
function timer()  
{  
// tic tac  
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
//document.formb.results.style.display = "block";  
}  
else // call timer() recursively every 1000 ms == 1 sec  
{  
window.setTimeout( "timer()", 1000 );  
}  
}  
//-->  
</script></head>  
 

<body bgcolor="<%=myColor %>">

<form  name="forma" method="post" action="scramble">
Time Remaining: <input type="text" name="mins" size="1" style="border:0px solid black;text-align:right">:<input type="text" name="secs" size="1" style="border:0px solid black">  


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

<label>Enter the words</label><input type="text" name = "word"><button name="Submit" value="Submit">Submit</button>
<label>Score</label><input type="text" value = ${score} name = "word"/>

</form>
 
</body>
</html>
