<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Signing out..</title> 
<script>
setTimeout(function (){document.forms.form.submit();}, 2000);
</script>

<link href="<c:url value="/resources/sample.css" />"  rel="stylesheet" type="text/css"  />
</head>
<body onload="load()">
<p><font size = "4">Please wait...</font></p><br>
<form id="form" action="signout" method="post">
<input type="hidden" name="username" id="username" value="${username}"/>
</form>
</body>
</html>