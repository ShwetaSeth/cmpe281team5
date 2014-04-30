<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%String username = (String)session.getAttribute("username"); %>
<%   
String myColor = (String)session.getAttribute("color");  
  if (myColor == null || myColor == ""){  
	myColor = "red";
  }  
%> 

<html>
<head>
	<title>What's Your Tech?</title>
	<link href="<c:url value="/resources/sample.css" />"  rel="stylesheet" type="text/css"  />

<script language="javascript" type="text/javascript">

var reset = 0;
var t = 0;
var count = 0;

function displayTimer() {
    document.getElementById('time').innerHTML = count;
}

function startTimer() {
    displayTimer();
    count++;
    t = setTimeout("startTimer()", 1000);
    if (count == 32){	
		checkRound();	    	
    }
}

function checkRound(){
	if(document.getElementById("round").value != "three"){
		document.forms.form.submit();
	}
	else{
		alert("Your final score is: " + document.getElementById("score").value);
		document.forms.form.submit();
	}
}

function submitForm(){
	document.forms.form.submit();
}

function nextHint(){
	if(document.getElementById("count").value == "1"){
		document.getElementById("count").value = "2";
		document.getElementById("hint2").style.visibility = "visible";
	}
	else if(document.getElementById("count").value == "2"){
		document.getElementById("count").value = "3";
		document.getElementById("hint3").style.visibility = "visible";
		document.getElementById("next").disabled = true;
	}
}

function checkAnswer(){
	if(document.getElementById("guess").value.toUpperCase() == document.getElementById("answer").value.toUpperCase()){
		alert("Correct Answer");
		if(document.getElementById("count").value == "1"){
			document.getElementById("score").value = parseInt(document.getElementById("score").value) + 200;
			document.getElementById("scores").innerHTML = document.getElementById("score").value;			
		}
		else if(document.getElementById("count").value == "2"){
			document.getElementById("score").value = parseInt(document.getElementById("score").value) + 100;
			document.getElementById("scores").innerHTML = document.getElementById("score").value;
		}
		else if(document.getElementById("count").value == "3"){
			document.getElementById("score").value = parseInt(document.getElementById("score").value) + 30;
			document.getElementById("scores").innerHTML = document.getElementById("score").value;
		}
		checkRound();
		
	}	
	else{
		alert("Wrong Answer");
	}
}

function load(){
	document.body.style.background = "<%=myColor%>";
	var val = document.getElementById("round").value;
	if(val != "none" && val != "done"){
		
		document.getElementById("startGame").disabled = true;
		
		document.getElementById("hint1").style.visibility = "visible";
		document.getElementById("hint2").style.visibility = "hidden";
		document.getElementById("hint3").style.visibility = "hidden";				
		document.getElementById("scores").innerHTML = document.getElementById("score").value;		
		startTimer();
	}
	else{	
		document.getElementById("scores").value = 0;
		document.getElementById("time").value = 0;
		document.getElementById("next").disabled = true;		
		document.getElementById("skip").disabled = true;		
		document.getElementById("submit").disabled = true;		
	}
}

</script>

</head>
<body onload="load()">
	
	<font size=3 style="position: absolute; right:30px; top:30px"> Welcome <%=username %> | <a href = "signout"> Sign out</a> </font> <br>
	<font size=3 style="position: absolute; right:30px; top:50px"> <a href = "profile" id="back"> Back </a> </font>
	
	<div id="container" style="width:1300px;height:1000px;margin-top:50px;">
		<div id="hints" style="background-color:#D5D5D5;height:600px;width:700px;float:left;margin-left:40px;margin-top:10px;padding-left:20px;">
			
			<div id="stats" style="background-color:#C5C5C5;height:200px;width:150px;float:right;margin-right:20px;margin-top:30px;">
				<center>
					<p><b>Time</b></p>
					<p id="time"> 0 </p>
					<p><b>Score</b></p>
					<p id="scores"> 0 </p>
				</center>
			</div>
			
			<br>
			<font size=3><b>Hint1</b></font>
			<br><hr style="width:200px;margin-left:20px;">
			<p id="hint1" style="width:400px;padding-left:20px;"> <font size=3> ${hint1} </font> </p>
			<br><br>
			
			<font size=3><b>Hint2</b></font>
			<br><hr style="width:200px;margin-left:20px;">
			<p id="hint2" style="width:400px;padding-left:20px;"> <font size=3> ${hint2} </font> </p>
			<br><br>
			
			<font size=3><b>Hint3</b></font>
			<br><hr style="width:200px;margin-left:20px;">
			<p id="hint3" style="width:400px;padding-left:20px;"> <font size=3> ${hint3} </font> </p>
			<br>
			
			<form id="form" method="post" action="WhatsYourTech">			
				<div class="form-row"> <input type="hidden" id="count" name="count" value="1"/> </div> <br>								
				
				<div class="form-row"> <input type="hidden" id="answer" name="answer" value="${answer}" /> </div> <br>
				<div class="form-row"> <input type="hidden" id="difficulty" name="difficulty" value="${difficulty}" /> </div> <br>				
				<div class="form-row"> <input type="hidden" id="round" name="round" value="${round}" /> </div> <br>				
				<div class="form-row"> <input type="hidden" id="score" name="score" value="${score}" /> </div> <br>				
				<div class="form-row"> <input type="hidden" id="game_id" name="game_id" value="${game_id}" /> </div> <br>				
								
			</form>
			
		</div>
		
		<div id="content" style="background-color:#D5D5D5;height:600px;width:400px;float:left;margin-left:120px;margin-top:10;">
			<br><font size="3" style="padding-left:20px;"><b>Instructions:</b></font> <br>
			<p style="padding-left:20px;padding-right:20px;" align="justify"> Guess the name of the technology from the hints provided. 
			The game consists of three rounds and the difficulty of the questions will increase with each round.
			The time limit for each round is 2 minutes. 
			If you guess the answer from the first hint, you will get full points (200).
			If you get the answer correct after 2nd hint, you'll get 100 points and correct answer after 3rd hint will get you only 30 points as the 3rd hint for almost all the questions is a give away.
			If you are unable to guess the technology after 3 hints or if the time runs out, you won't get any points and will move forward to next question.
			</p>
			<div id = "buttons" style="float:center;margin-left:20px;margin-top:30px;"> 
				<input type="button" id="startGame" value="New Game" style="font-size:18;width:150px;height:50px;margin-left:20px;" onclick="submitForm()"/>
				<input type="button" id="next" value="Next Hint" style="font-size:18;width:150px;height:50px;margin-left:20px;" onclick="nextHint()" /> <br>
				
				<input size="35" style="font-size:18;height:50px;margin-top:30px;padding:10px;" type="text" id="guess" name="guess" /> <br> 
				<input type="button" id="skip" value="Skip" style="font-size:18;width:150px;height:50px;margin-top:20px;margin-left:20px;" onclick="checkRound();" />
				<input type="button" id="submit" value="Submit" style="font-size:18;width:150px;height:50px;margin-left:20px;" onclick="checkAnswer();" />
			</div>						
			
		</div>
		
	</div>
</body>
</html>