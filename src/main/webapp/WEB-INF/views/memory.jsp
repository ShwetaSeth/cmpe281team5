<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Memory Game</title>
<link href="<c:url value="/resources/sample.css" />"  rel="stylesheet" type="text/css"  />
<style>

#bgpic{
background:url(<c:url value="/resources/wood.jpg" />); 
 /*background-size:100%;*/
    }
    
 input[type="text"]
{
    background: transparent;
    border: none;
}   

.timer{
color:red;
font-size: 22pt;
}

</style>
<%  

String myColor = (String)session.getAttribute("color");  
if (myColor == null || myColor == ""){  
	myColor = "cyan";
}
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
	document.getElementById('wcloud').style.visibility='visible';
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

window.setTimeout( function()
{document.getElementById('wcloud').style.visibility='hidden';document.getElementById('submit').disabled='';document.getElementById('start').disabled='disabled';}, 11000 );

if( document.getElementById('secs').value == '00' && document.getElementById('mins').value == '00' ) // time over  
{  
	document.memform.secs.disabled = true;  
	document.memform.mins.disabled = true;  
}
else // call timer() recursively every 1000 ms == 1 sec  
{  
window.setTimeout( "timer()", 1000 ); 

}


}  

</script></head> 
<div id="navbar">
				<jsp:include page="navbar.jsp"></jsp:include>
</div> 
 <center>
				<font size="4" color="darkblue" ><b>Welcome, ${user.fname}</b> </font> <br>
				<font size="3" color="darkblue" >Memory Game</font>
</center>
 

<body bgcolor="<%=myColor %>">

<div align="center">
	


<form name="memform" method="post" action="memoryans">
	
<div align="center">
	<c:if test="${topscoreChecked ne 'false' }">
	<label>Highest Score</label><input type="text" value = "${highScore}" name = "highScore"/>
	</c:if>
</div>


<input type="hidden" name="pic" id="pic" value="${pic}"/>

<input type="hidden" name="picid" id="picid" value="${picid}"/>
<fieldset  id="bgpic" style="width:50%;height:500%;">


<input   class="timer" align="middle" type="text" readonly="readonly" name="mins" id="mins" size="1" style="border:0px solid black;text-align:right;" value="00">
<span class="timer">:</span>
<input class="timer" type="text" id="secs" readonly="readonly" name="secs" size="1" style="border:0px solid black;" value="00">  


<p><span style="font-size:24px;font-weight:bold;color:#ffffff;">Instructions:</span>&nbsp;<span style="color:#ffffff;font-size:16px;"> Memorize the words in the picture in a time of 10 seconds!Test your memory by remembering all the words.</span> </p>
<br/>
<input type="button" id="start" name="start" value="Start!" onclick="timer();"/>
<br/><br/>
<div align='center' id='wcloud' style="visibility:hidden">
<img src="<c:url value="/resources/${pic}" />" alt="wordcloud" height="300" width="500" border="1"/>
</div>
<br/><br/>
<div align='center' id='answer'>
<input id="submit" type="submit" name="Submit" value="Recollect!!" disabled="disabled">
</div>
<br/>
</fieldset>

</form>
</div>

</body>
</html>
