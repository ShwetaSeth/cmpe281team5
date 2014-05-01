<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Memory Game Score</title>
<link href="<c:url value="/resources/sample.css" />"  rel="stylesheet" type="text/css"  />

<% 
String mins = request.getParameter( "mins" ); 
if( mins == null ) mins = "1";  
   
String secs = request.getParameter( "secs" );  
if( secs == null ) secs = "1";  

int result=0;
String x = request.getParameter("submit");
//out.println(x);
if(x!=null && x.equals("Submit")) result = 1;
%> 
 
<script type="text/javascript">  
  
  //document.write("\n");--spacebar event
  
var mins = <%=mins%>; // write mins to javascript 
var secs = <%=secs%>; // write secs to javascript
var text = <%=result%>;
//alert(text);
if(text==1)
{
	//alert("here");
	alert(document.getElementById("ins").style.visibility);
	//document.getElementById("ans").style.visibility = "hidden"; 
	document.getElementById("ins").style.visibility = "hidden"; 
	//document.memoryscore.style.visibility = 'hidden';
	//document.memoryscore.style.visibility = 'hidden';
}

	

function timer()  
{  

if( --secs == -1 )  
{  
secs = 10;  
mins;  
}  
  //
// leading zero? formatting  
if( secs < 10 ) secs = "0" + secs;               
if( mins < 10 ) mins = "0" + parseInt( mins, 10 );  
  
// display  
document.memoryscore.mins.value = mins;   
document.memoryscore.secs.value = secs;  
 
//window.setTimeout( function(){document.getElementById('ans').style.display='none';}, 11000 );
if(document.getElementById('secs').value == '00' && document.getElementById('mins').value == '00') // time over  
{  
	 
}
else // call timer() recursively every 1000 ms == 1 sec  
{  
window.setTimeout( "timer()", 1000 ); 

}


}  
//-->  
</script></head>  
 <div align="center">
<form name="memoryscore" method="post" action="memoryscore">


<input type="hidden" name="picid" id="picid" value="${picid}"/>
<input type="hidden" name="message" id="message" value="${message}"/>
<input type="hidden" name="score" id="score" value="${score}"/>


<fieldset style="width:50%;">
<legend>

<input  align="middle" type="text" name="mins" id="mins" size="1" style="border:0px solid black;text-align:right;font-size: 18pt;"/>:
<input type="text" id="secs" name="secs" size="1" style="border:0px solid black;font-size: 18pt;"/>  

</legend>
  
<table>
<tr><td><label style="font-size: 18pt;">${message}${score}</label></td></tr>
<tr id="ins">
<td><label>Enter the words:</label></td>
<td><textarea name="ans" id="ans" cols="20" rows="10" style="overflow:hidden;"></textarea></td>
</tr>
<tr id="ins"><td>&nbsp;&nbsp;</td><td><input id="submit" type="submit" name="submit" value="Submit"></td></tr>
</table>
<br/><br/>





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

 





  

