package com.sample.team5;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import DAO.HintsDAO;
import DAO.PlayersDAO;
import Entity.Hints;
import Entity.Players;

@Controller
public class WhatsYourTechController{
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	ApplicationContext appContext = AppContext.getApplicationContext();
	String uname;
	
	public DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@RequestMapping(value = "WhatsYourTech", method = RequestMethod.GET)
	public String getPage(HttpServletRequest request, HttpSession session, Model model) throws SQLException{		
		String username = (String)session.getAttribute("username");
		
		WhatsYourTechController controller = (WhatsYourTechController)appContext.getBean("WhatsYourTech");		
		controller.createTablePlayers();
		controller.createTableHints();
		
		
		model.addAttribute("username", username);
		model.addAttribute("round", "none");
		model.addAttribute("score", 0);
		model.addAttribute("difficulty","none");
		model.addAttribute("game_id",0);
		
		

		String username = (String) session.getAttribute("username");
		String game = (String) session.getAttribute("favgame");
		user.setUsername(username);
		int highScore= userDAO.getHighestScore(user, game);
		model.addAttribute("highScore",highScore);
		
		return "WhatsYourTech";
	}
	
	@RequestMapping(value="WhatsYourTech", method = RequestMethod.POST)
	public String play(HttpServletRequest request, HttpSession session, Model model) throws SQLException{
		String username = (String)session.getAttribute("username");
		String difficulty = request.getParameter("difficulty");
		String round = request.getParameter("round");
		int score = 0;
		int game_id = 0;
		
		try{
			score = Integer.parseInt(request.getParameter("score"));
			game_id = Integer.parseInt(request.getParameter("game_id"));
		}
		catch(NumberFormatException e){			
			System.out.println("Score: " + request.getParameter("score"));
			System.out.println("Game_ID: " + request.getParameter("game_id"));
		}
		
		HintsDAO hints = (HintsDAO)appContext.getBean("hintsDAOImpl");
		Hints hint = new Hints();
		//After clicking Start
		if(difficulty.equals("none")){
			PlayersDAO players = (PlayersDAO)appContext.getBean("playersDAOImpl");
			Players player = new Players();
			player.setPlayer(username);
			player.setTotal_score(0);
			game_id = players.addPlayer(player);

			hint = hints.getEasyHints();			
			difficulty = "Easy";
			round = "one";
		}
		//After Round1
		else if(difficulty.equals("Easy")){
			hint = hints.getModerateHints();
			difficulty = "Moderate";
			round = "two";
		}
		//After Round2
		else if(difficulty.equals("Moderate")){
			hint = hints.getHardHints();			
			difficulty = "Hard";
			round = "three";
		}
		//After Round3
		else if(difficulty.equals("Hard")){
			PlayersDAO players = (PlayersDAO)appContext.getBean("playersDAOImpl");
			Players player = new Players();
			player.setGame_id(game_id);
			player.setPlayer(username);
			player.setTotal_score(score);
			players.updateScore(player);
			
			model.addAttribute("round","none");
			model.addAttribute("score", 0);
			model.addAttribute("difficulty","none");
			model.addAttribute("game_id",0);
			
			return "WhatsYourTech";
		}
		
		model.addAttribute("round", round);
		model.addAttribute("score", score);
		model.addAttribute("difficulty",difficulty);
		model.addAttribute("game_id", game_id);
		
		model.addAttribute("answer",hint.getAnswer());
		model.addAttribute("hint1",hint.getHint1());
		model.addAttribute("hint2",hint.getHint2());
		model.addAttribute("hint3",hint.getHint3());
		
		return "WhatsYourTech";
	}
		
	//Create Table for Players
	public void createTablePlayers() throws SQLException{
		Connection conn;

		conn = dataSource.getConnection();
		
		DatabaseMetaData meta = conn.getMetaData();
		ResultSet res = meta.getTables(null, null, "players", null);
		if(res.next()){
			System.out.println("Table 'players' already exists.");
			logger.info("Table 'players' already exists.");
		}
		else{
			Statement stmt = conn.createStatement();
			
			String query =  "CREATE TABLE IF NOT EXISTS players (  " +
							"game_id int(11) NOT NULL AUTO_INCREMENT, " +
							"player varchar(45) DEFAULT NULL, " +
							"total_score int(11) NOT NULL DEFAULT 0, " +
							"PRIMARY KEY (game_id), " +
							"UNIQUE KEY game_id_UNIQUE (game_id), " +
							"KEY player_fk_idx (player), " +
							"CONSTRAINT player_fk FOREIGN KEY (player) REFERENCES users (username) ON DELETE NO ACTION ON UPDATE NO ACTION )";
			stmt.executeUpdate(query);
			logger.info("Table 'players' created.");
			System.out.println("Table 'players' created.");
		}
		conn.close();			
	}
	
	//Create Table for Hints 
	public void createTableHints() throws SQLException {
		Connection conn;

		conn = dataSource.getConnection();
		
		DatabaseMetaData meta = conn.getMetaData();
		ResultSet res = meta.getTables(null, null, "hints", null);
		if(res.next()){
			System.out.println("Table 'hints' already exists.");
			logger.info("Table 'hints' already exists.");
		}
		else{
			Statement stmt = conn.createStatement();

			String query = " CREATE TABLE IF NOT EXISTS hints (  " +
							"hints_id int(11) NOT NULL AUTO_INCREMENT, " +
							"answer varchar(45) DEFAULT NULL, " +
							"hint1 varchar(300) DEFAULT NULL, " +
							"hint2 varchar(300) DEFAULT NULL, " +
							"hint3 varchar(300) DEFAULT NULL, " +
							"difficulty varchar(10) DEFAULT NULL, " +
							"PRIMARY KEY (hints_id), " +
							"UNIQUE KEY hints_id_UNIQUE (hints_id) )";
			stmt.executeUpdate(query);
			logger.info("Table 'hints' created.");
			System.out.println("Table 'hints' created.");
			populateTable();
		}
		conn.close();			
	}
	//Populate the Hints Table
	public void populateTable() throws SQLException{
		
		HintsDAO hintsDAO = (HintsDAO)appContext.getBean("hintsDAOImpl"); 
		Hints hint = new Hints();
		
		String[] answer = {"Spring", "Groovy", "AspectJ", "JSON", "Grails", "force.com", "MongoDB", "PostgreSQL"};
		
		String[] hint1 = {"Framework developed by GoPivotal under Apache License", 
						  "An object oriented programming language designed by James Strachan under Apache License and developed by founders of the company g2one", 
						  "AOP Extension developed by Eclipse Foundation", 
						  "An open standard format extended from JavaScript that uses human readable text when transmiting data objects.", 						  
						  "An open source web application framework developed by Graeme Rocher under Apache License", 
						  "A cloud computing PaaS usedby developers to build multi tenant applications", 
						  "A cross platform Document-oriented database system",
						  "A cross platform object-relational database management system (ORDBMS)"};
		
		String[] hint2 = {	"Modules: IoC Container, Aspect oriented Programming, Transaction Management, Convention over Configuration (Roo)", 
						  	"Can also be used as scripting language for Java platform ", 
							"Developers need extra build process with the compiler and have to setup LTW (Load Time Weaving)", 
							"The data objects consist of attribute-value pairs.", 
							"A high productivity framework following 'coding by convention' paradigm and hides a lot of config details from developer", 
							"The applications are built using Apex and the UI is created using and an XML-like syntax in HTML or Flex", 
							"Uses Binary Coded JSON like documents", 
							"Provides features like views, foreign key references, triggers, stored procedures,geographical data,unions, joins etc. Also allows developers to do nested subqueries of subselects" };
		
		String[] hint3 = {  "Has HTTP- and servlet-based framework providing hooks for extension and customization for web applications and RESTful web services", 
							"Features (Not available in JAVA): static and dynamic typing, closures, operator overloading etc.", 
							"Keywords: PointCut, JoinPoint, Advice", 
							"Primarily used to transmit data between a server and web application  as an alternative to XML", 
							"Uses Groovy Programming Language", 
							"Developed by Salesforce", 
							"Classified as NoSQL database", 
							"Evolved from the 'Ingres' project at the University of California, Berkley" };
		
		String[] difficulty = { "Moderate", "Moderate", "Hard", "Hard", "Easy", "Easy", "Easy", "Easy" };
		
		for(int i=0; i< answer.length; i++){
			hint.setAnswer(answer[i]);
			hint.setHint1(hint1[i]);
			hint.setHint2(hint2[i]);
			hint.setHint3(hint3[i]);
			hint.setDifficulty(difficulty[i]);
			hintsDAO.addHints(hint);
		}
	}
	
}
