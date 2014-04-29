<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Memory Game</title>
<link href="<c:url value="/resources/sample.css" />"  rel="stylesheet" type="text/css"  />

<%  
String mins = request.getParameter( "mins" );  
if( mins == null ) mins = "1";  
   
String secs = request.getParameter( "secs" );  
if( secs == null ) secs = "1";  
%>  
<script>  
<!--  
var mins = <%=mins%>; // write mins to javascript  
var secs = <%=secs%>; // write secs to javascript  


function timer()  
{  

if( --secs == -1 )  
{  
secs = 10;  
--mins;  
}  
  
// leading zero? formatting  
if( secs < 10 ) secs = "0" + secs;               
if( mins < 10 ) mins = "0" + parseInt( mins, 10 );  
  
// display  
document.memform.mins.value = mins;   
document.memform.secs.value = secs;  
  
// continue?  

window.setTimeout( function(){document.getElementById('wcloud').style.visibility='hidden';document.getElementById('submit').disabled='';}, 11000 );
//window.setTimeout( function(){document.getElementbyId('answer').style.visibility='';}, 12000 );
if( document.getElementById('secs').value == '00' && document.getElementById('mins').value == '00' ) // time over  
{  

}
else // call timer() recursively every 1000 ms == 1 sec  
{  
window.setTimeout( "timer()", 1000 ); 

}


}  
//-->  
</script></head>  
 



<form name="memform" method="post" action="memoryans">

<input type="hidden" name="pic" id="pic" value="${pic}"/>

<input type="hidden" name="picid" id="picid" value="${picid}"/>
<fieldset>
<legend>

<input  align="middle" type="text" name="mins" id="mins" size="1" style="border:0px solid black;text-align:right;background-color: lightblue;font-size: 18pt;">:
<input type="text" id="secs" name="secs" size="1" style="border:0px solid black;background-color: lightblue;font-size: 18pt;">  

</legend>
<div align='center' id='wcloud'>
<img src="<c:url value="/resources/${pic}" />" alt="wordcloud" height="300" width="500" border="1"/>
</div>
<br/><br/>
<div align='center' id='answer'>

<input id="submit" type="submit" name="Submit" value="Recollect!!" disabled="disabled">

</div>
</fieldset>
</form>


<script>  
<!--  
timer(); // call timer() after page is loaded  
//-->  
</script>  

</body>
</html>
