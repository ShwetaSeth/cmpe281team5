<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>    

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome!</title>
<link href="<c:url value="/resources/sample.css" />"  rel="stylesheet" type="text/css"  />
</head>
<body>
<font size=3 style="position: absolute; right:50px; top:50px"> Welcome ${message}  <a href = "signout"> Sign out</a> <br> </font>

 <a href = "scramble/play"> Play Scramble </a> <br>

</body>
</html>