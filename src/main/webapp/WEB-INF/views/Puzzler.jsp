<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta charset="US-ASCII">
<title>Puzzler</title>

<style>

body {
	text-align: center;
	
}

#container {
	width: 450px;/*150+150+150*/
	height: 300px;/*100+100+100*/
	border: 9px solid black;
	position: absolute;
	top: 150px;
	left: 250px
}

.boxes {
	height: 100px;
	width: 150px;
	position: absolute;
	color: black;
	font-size: 60px;
	align:center;
}

#shuffle {/*checked..*/
	font-weight: bold;
	position: absolute;
	top: 490px;
	left: 420px;
	height: 50px;
	width: 135px;
	
}

#Timer{
position: absolute;
	top: 100px;
	left: 560px;
	width: 135px;
	height: 31px;
	}
#User{
position: absolute;
	top: 100px;
	left: 220px;
	width: 135px;
	height: 31px;
	}
#link
{
position: absolute;
	top: 600px;
	left: 420px;
	height: 50px;
	width: 135px;
}	
#submit
{
position: absolute;
	top: 550px;
	left: 420px;
	height: 50px;
	width: 135px;
}
</style>
<%  
  
String myColor = (String)session.getAttribute("color");  
  if (myColor == null || myColor == ""){  
	myColor = "red";
  }
  
%>  

<script src="http://code.jquery.com/jquery-2.1.0.min.js"></script>
<script type="text/javascript">
	var counter = 0;
	var refreshIntervalId;

function startTimer(){
	var start = new Date;
	refreshIntervalId = setInterval(function() {
		var c =(new Date - start)/1000;
		
	    $('#Timer').text(c .toFixed() + " seconds (Time)");
	}, 1000);
}
function stopTimer(){
	clearInterval(refreshIntervalId);
}

function displaycounter(){
	counter++;
	//alert(counter);
	if ( counter != null){
		$("#v1").val(counter);
	}
	
}



function FindRow(top) { 
	var row = top / 100;
	return row;
}

function FindColumn(left) { 
	var column = left / 150;
	return column;
}

	
	$(document).ready(function() {
		
		function shuffle() {

			var previousShift=0;
			$('body').css('cursor', 'progress'); //Change the normal cursor to progress cursor.

			for (var i = 0; i < 100; i++) { //shuffle 
				
				var nemptytop = parseInt($('#empty').css('top')); //top of the empty block.
				var nemptyleft = parseInt($('#empty').css('left')); //left of  empty block.
				var random = parseInt(1 + (Math.random() * (7))); //random #s 1-8
				var ntop = parseInt($('#' + random).css('top')); //top of random block.
				var nleft = parseInt($('#' + random).css('left')); //left of the random block.
				
				
				var nemptyrow = FindRow(nemptytop); //row of the empty block
				var nemptycolumn = FindColumn(nemptyleft);//coloumn of the random block
				var nrow = FindRow(ntop); //row of the random block
				var ncolumn = FindColumn(nleft);//coloumn of the block
				

				if (nrow == nemptyrow && previousShift != random) { //check to see If both random block and empty block are in same row and last move is not same 
					var difference = ncolumn - nemptycolumn;
					if (difference == 1 || difference == -1) { //Check the diff. between columns, if they are adjacent then move the blocks.
						$('#empty').css('left', nleft);
						$('#' + random).css('left', nemptyleft);
					}
				}

				else if (ncolumn == nemptycolumn && previousShift != random) { //Else if column are same and again last move is not same the enter the else block.
					var difference = nrow - nemptyrow;
					if (difference == 1 || difference == -1) { //Again check the diff. between rows, if they are adjacent then move the blocks.
						$('#empty').css('top', ntop);
						$('#' + random).css('top', nemptytop);
					}
				}
				previousShift = random;
			}
		}

		$('.boxes').click(function() { 			
			$('body').css('cursor', 'progress'); 
			
			var ntop = parseInt($(this).css('top')); //it moves the block.
			var nleft = parseInt($(this).css('left'));
			var emptytop = parseInt($('#empty').css('top'));
			var emptyleft = parseInt($('#empty').css('left'));

			var row = FindRow(ntop);
			var column = FindColumn(nleft);
			var emptyrow = FindRow(emptytop);
			var emptycolumn = FindColumn(emptyleft);
			if (row == emptyrow) {
				var difference = column - emptycolumn;
				if (difference == 1 || difference == -1) {
					displaycounter();
					//counter++;

					$('#empty').animate({
						left : nleft
					}, "fast");
					$(this).animate({
						left : emptyleft
					}, "fast"); //Block is moved with a bit of animation.
				}
			}

			else if (column == emptycolumn) {
				var difference = row - emptyrow;
				if (difference == 1 || difference == -1) {
					displaycounter();
					//counter++;

					$('#empty').animate({
						top : ntop
					}, "fast");
					$(this).animate({
						top : emptytop
					}, "fast");
				}
			}
			setTimeout(function() {
				$('body').css('cursor', 'default');
			}, 0);
			//alert(counter);
		});

		
		$('#shuffle').click(function() { //Event is fired when Shuffle button is clicked.
		  var value = $('#shuffle').val();
		  if( value === 'Start'){
				shuffle();
				startTimer();
				$('#shuffle').val("Stop");
		  }else{
				stopTimer();
				//updateValues();
				$('#shuffle').val("Start");
		  }
		});

		
	});
	

</script>
</head>

<body bgcolor="<%=myColor %>" >


	<form id="form1" action="shuffle" method="POST">
		<div id="main" align="center">
			<h1 >Puzzler</h1>
            <br></br>
            
<c:if test="${topscoreChecked ne 'false' }">
<label>Highest Score</label><input type="text" value = ${highScore} name = "highScore"/>
</c:if>
</div>	

<br><br>
  <font size=3 style="position: absolute; right:30px; top:50px"> <a href = "profile" id="back"> Back </a> </font>
            <div id="User" class ="User"><label >Moves:</label><input id="v1" type="text" Size = 6 name="moves" /></div>
            <div id="Timer"class="Timer" >Time</div>
             <br></br>
			<div id="container">
				<div id="1" class="boxes"
					style="top: 0px; left: 0px; background-color: #5CB3FF ;border:1px solid green">1</div>
				<div id="2" class="boxes"
					style="top: 0px; left: 150px; background-color: grey;border:1px solid green">2</div>
				<div id="3" class="boxes"
					style="top: 0px; left: 300px; background-color: #5CB3FF;border:1px solid green">3</div>
				<div id="4" class="boxes"
					style="top: 100px; left: 0px; background-color: grey;border:1px solid green">4</div>
				<div id="5" class="boxes"
					style="top: 100px; left: 150px; background-color: #5CB3FF;border:1px solid green">5</div>
				<div id="6" class="boxes"
					style="top: 100px; left: 300px; background-color: grey;border:1px solid green">6</div>
				<div id="7" class="boxes"
					style="top: 200px; left: 0px; background-color: #5CB3FF;border:1px solid green">7</div>
				<div id="8" class="boxes"
					style="top: 200px; left: 150px; background-color: grey;border:1px solid green">8</div>
				<div id="empty" class="boxes" style="top: 200px; left: 300px"></div>
			</div>
			
		</div>
		<div>
		<input type="button" value="Start" id="shuffle" />
			<!--  <input type="hidden" value="Start" name="action" />-->
			<!-- <input type="hidden" value="0" name="time" />-->
			<!--  <input type="submit" value="Start" id="shuffle" />-->
			<!--<input type="submit" value="Stop" id="shuffle" />-->
		</div>
		<div id="submit"><button name="Submit" value="Submit">Submit</button></div>
		 <a id="link" href ="instructions">Click for Instructions</a>
		
		
	</form>
</body>

</html>