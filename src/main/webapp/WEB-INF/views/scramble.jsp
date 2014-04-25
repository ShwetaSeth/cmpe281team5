<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CMPE Scramble</title>
</head>
<body>
<form id="scrambleform" method="post" action="enterWord">
<table border = '1'>

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
<label>Score</label><input type="text" value = ${score} name = "word">


</form>
</body>
</html>