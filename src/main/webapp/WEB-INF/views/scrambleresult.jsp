<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Scramble Result</title>
</head>

<%  
  
String myColor = (String)session.getAttribute("color");  
  if (myColor == null || myColor == ""){  
	myColor = "red";
  }
  
  String currScore = request.getParameter("currScore");
  String prevScore = request.getParameter("prevScore");
  
  
%>  

<body bgcolor="<%=myColor %>">
<div id="container">
<div id = "content">
<h1>Your Results are</h1>

<label>Current Score</label><input type="text" readonly="readonly" value = ${currScore} name = "currScore"/>
<br>
<label>Previous Score</label><input type="text" readonly="readonly" value = ${prevScore} name = "prevScore"/>

</div></div>
</body>
</html>