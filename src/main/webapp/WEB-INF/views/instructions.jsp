<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Instructions</title> 
<style>
.box1,.box4,.box7
{
border:1px solid black;
height:100px;
width:100px;
text-align:center;
background-color:#e5eecc;
float: left;
position: absolute;
font-family:copper black;color:black;font-size:75px;
}

.box2,.box5,.box8
{
border:1px solid black;
height:100px;
width:100px;
text-align:center;
font-family:copper black;color:black;font-size:75px;
background-color:#e5eecc;
float: left;
position: absolute;
left: 110px
}
.box3,.box6
{
border:1px solid black;
height:100px;
width:100px;
text-align:center;
font-family:copper black;color:black;font-size:75px;
background-color:#e5eecc;
float: left;
position: absolute;
left: 210px
}
.box9
{
border:1px solid black;
border-collapse:collapse;
height:100px;
width:100px;
text-align:center;
background-color:grey;
float: left;
position: absolute;
left: 210px
}
.boxrow{
border: 1px solid green;
border-collapse:collapse;
width: 300px;
height:100px;
margin:1px;

}
</style>

<body>
<form >

<div id="main">
<h1 style= "text-align: center;" >How to play Puzzler</h1>

</div >


<a href= "Puzzler">Back to Game</a> <br>
<div>
<p>The Puzzler has a grid of numbers(3*3).</p>
<p>Click on ""START to shuffle the numbers.</p>
<p>Push the numbers around the board till they are in the right order. </p>
<p>Move only one piece at a time in the empty slot.</p>
<p>The number on each piece tells its position on the board.</p>
<p>The Number1 should be placed in the upper left corner of the puzzle.</p> 
<p>Finally the puzzle should look like this when it is solved:</p>
</div>


<div id="container"  >

<div id="row" class="boxrow" >
<div id="1"class ="box1"> 1</div>
<div id="2"class ="box2"> 2</div>
<div id="3"class ="box3"> 3</div>
</div>

<div id="row" class="boxrow" >
<div id="4"class ="box4"> 4</div>
<div id="5"class ="box5"> 5</div>
<div id="6"class ="box6"> 6</div>
</div>

<div id="row" class="boxrow" >
<div id="7"class ="box7"> 7</div>
<div id="8"class ="box8"> 8</div>
<div id="9"class ="box9"> </div>
</div>
</div>

<br>
<div>
<input type="button" 	value="Shuffle" id="shuffle" style ="width:300px;font-family:copper black;font-size:20px " />
</div>

<p>Tips -&gt; </p>
<ul>
<li>Start with the first row so that you get numbers 1, 2, 3 in order.</li>
<li>Work on rows two and three simultaneously to complete the puzzle.</li>
</ul>
</form>

</body>

</html>
