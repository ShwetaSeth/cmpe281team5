<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Memory Game Score</title>
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
</style>
<% 
String mins = request.getParameter( "mins" ); 
if( mins == null ) mins = "1";  
   
String secs = request.getParameter( "secs" );  
if( secs == null ) secs = "1";  

int result=0;
String x = request.getParameter("submit");
if(x!=null && x.equals("Submit")) result = 1;
%> 
 
<script type="text/javascript">  

window.addEventListener("keyup", hitenter, false);
  
function hitenter(e)
  {
	  //go to nextline is spacebar pressed
	if (window.event.keyCode == "32") 
	{
      
       document.getElementById('ans').value = document.getElementById('ans').value+"\n";
      
    }
		   
  } 
  
  
var mins = <%=mins%>; // write mins to javascript 
var secs = <%=secs%>; // write secs to javascript
var text = <%=result%>;
if(text==1)
{
	
	alert(document.getElementById("ins").style.visibility);
	document.getElementById("ins").style.visibility = "hidden"; 
}

	

function timer()  
{  

if( --secs == -1 )  
{  
secs = 20;  
mins;  
}  
  //
// leading zero? formatting  
if( secs < 10 ) secs = "0" + secs;               
if( mins < 10 ) mins = "0" + parseInt( mins, 10 );  
  
// display  
document.memoryscore.mins.value = mins;   
document.memoryscore.secs.value = secs;  
 
window.setTimeout( function(){document.getElementById('submit').disabled='disabled';}, 20000 );
if(document.getElementById('secs').value == '00' && document.getElementById('mins').value == '00') // time over  
{  
	document.memoryscore.mins.disabled = true;   
	document.memoryscore.secs.disabled = true;   
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

 <div align="center">
<form name="memoryscore" method="get" action="memory">


<input type="hidden" name="picid" id="picid" value="${picid}"/>
<input type="hidden" name="message" id="message" value="${message}"/>
<input type="hidden" name="score" id="score" value="${score}"/>


<fieldset style="width:50%;height:500%;" id="bgpic">

<!-- <input  align="middle" type="text" name="mins" id="mins" size="1" style="border:0px solid black;text-align:right;font-size: 22pt;color:red;"/><span style="color:red;font-size: 22pt;">:</span>
<input type="text" id="secs" name="secs" size="1" style="border:0px solid black;font-size: 18pt;color:red;font-size: 22pt;"/>   -->


  
<table height=500px>
<tr><td><label style="font-size: 18pt;color:#ffffff;">${message}${score}</label></td></tr>
<tr id="ins">
<td><label style="color:#ffffff;font-size: 14pt;">Enter the words:</label></td>
<td><textarea name="ans" id="ans" cols="20" rows="10" style="overflow:hidden;"></textarea><br/></td>
</tr>
<tr id="ins">
<td>&nbsp;&nbsp;</td><td><input id="submit" type="submit" name="submit" value="Play Again"></td></tr>
</table>
</fieldset>
</form>


</div>

<script>  
<!--  
timer(); // call timer() after page is loaded  
//-->  
</script>  

</body>
</html>

 





  

