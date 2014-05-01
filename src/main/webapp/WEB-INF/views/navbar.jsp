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
<%String username = (String)session.getAttribute("username"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
 
</head>

<body>

<ul class="navigation">
	
	
		   <li>
			<h3>My Profile</h3>
			<p>
			<a href="profile">Home Page</a>
			</p>
			</li>
		
			<li>
			<h3>Edit Profile</h3>
			<p>
			<a href="editProfile.jsp">Edit Profile</a>
			</p></li>
			
			
			<li>
			<h3>Game Selection</h3>
			<p>
			<a href="profile">Search Flights</a>
			</p></li>
			
			<li>
			<h3>Sign Out</h3>
			<p>
			<a href="signout">Sign Out</a>
			</p></li>
	</ul>

</body>
</html>