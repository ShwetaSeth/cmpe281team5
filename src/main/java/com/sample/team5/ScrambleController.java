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

import DAO.ScrambleDAO;
import DAO.UserDAO;
import Entity.Scramble;
import Entity.User;

@Controller
public class ScrambleController {
	
	public DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(ScrambleController.class);	
	ApplicationContext appContext = AppContext.getApplicationContext();
	
	@RequestMapping(value = "scramble", method = RequestMethod.GET)
	public String scramble(HttpServletRequest request, HttpSession session, Model model) throws SQLException {		
		ScrambleController con = (ScrambleController)appContext.getBean("scrambleController");		
		ScrambleDAO scrambleDAO = (ScrambleDAO)appContext.getBean("scrambleDAOImpl");
		UserDAO userDAO = (UserDAO)appContext.getBean("userDAOImpl");
		User user = new User();
		
		con.createScrambleTable();
		con.createScrambleWords();
		scrambleDAO.setGame((String)session.getAttribute("username"));	
		
		
		model.addAttribute("score", "0");
		
		user = (User)session.getAttribute("user");
		int highScore= user.getGame1_highscore();
		model.addAttribute("highScore",highScore);
		
		
		return "scramble";
	}
	
	
	public void createScrambleTable() throws SQLException{
		Connection conn;

		conn = dataSource.getConnection();
		
		DatabaseMetaData meta = conn.getMetaData();
		ResultSet res = meta.getTables(null, null, "scramble", null);
		if(res.next()){
			System.out.println("Table 'scramble' already exists.");
			logger.info("Table 'scramble' already exists.");
		}
		else{
			Statement stmt = conn.createStatement();
			
			String query = "CREATE TABLE scramble (username VARCHAR(15) NOT NULL, prevScore INT NULL DEFAULT 0, "+
					"currScore INT NULL, lastWord CHAR(15) NULL, PRIMARY KEY (username), CONSTRAINT username " +
					"FOREIGN KEY (username) REFERENCES users (username) ON DELETE CASCADE ON UPDATE CASCADE)";
			
			stmt.executeUpdate(query);
			System.out.println("Table 'scramble' created.");
			
		}
				
		conn.close();			
	}	
	
	public void createScrambleWords() throws SQLException{
		Connection conn;

		conn = dataSource.getConnection();
		
		DatabaseMetaData meta = conn.getMetaData();
		ResultSet res = meta.getTables(null, null, "scramblewords", null);
		if(res.next()){
			System.out.println("Table 'scramble' already exists.");
			logger.info("Table 'scramble' already exists.");
		}
		else{
			Statement stmt = conn.createStatement();
			String query = "CREATE TABLE IF NOT EXISTS scramblewords ( Word CHAR(15) NOT NULL,guess INT NULL DEFAULT 0,PRIMARY KEY (word))";
			stmt.executeUpdate(query);
			logger.info("Table 'scramblewords' created.");
			System.out.println("Table 'scramblewords' created.");
			
		}

		Statement stmt = conn.createStatement();
		String query = "INSERT IGNORE INTO scramblewords (word) VALUES ('riak')";
		stmt.executeUpdate(query);
		query = "INSERT IGNORE INTO scramblewords (word) VALUES ('amazon')";
		stmt.executeUpdate(query);
		query = "INSERT IGNORE INTO scramblewords (word) VALUES ('aws')";
		stmt.executeUpdate(query);
		query = "INSERT IGNORE INTO scramblewords (word) VALUES ('dynamo')";
		stmt.executeUpdate(query);
		query = "INSERT IGNORE INTO scramblewords (word) VALUES ('cloud')";
		stmt.executeUpdate(query);
		query = "INSERT IGNORE INTO scramblewords (word) VALUES ('ibm')";
		stmt.executeUpdate(query);
		query = "INSERT IGNORE INTO scramblewords (word) VALUES ('riak')";
		stmt.executeUpdate(query);
		query = "INSERT IGNORE INTO scramblewords (word) VALUES ('google')";
		stmt.executeUpdate(query);
		query = "INSERT IGNORE INTO scramblewords (word) VALUES ('oracle')";
		stmt.executeUpdate(query);
		query = "INSERT IGNORE INTO scramblewords (word) VALUES ('server')";
		stmt.executeUpdate(query);
		query = "INSERT IGNORE INTO scramblewords (word) VALUES ('lan')";
		stmt.executeUpdate(query);
		query = "INSERT IGNORE INTO scramblewords (word) VALUES ('saas')";
		stmt.executeUpdate(query);
		query = "INSERT IGNORE INTO scramblewords (word) VALUES ('load')";
		stmt.executeUpdate(query);

		logger.info("Table scrablewords inserted with values");
		System.out.println("Table scramblewords inserted with values");
				
		conn.close();			
	}	
	
	@RequestMapping(value = "scramble", method = RequestMethod.POST)
	public String enterWord(HttpServletRequest request, HttpSession session, Model model) {		
		ScrambleController con = (ScrambleController)appContext.getBean("scrambleController");		
		int score = con.enterWord(request,session);	
		
		String highScore = request.getParameter("highScore");
		model.addAttribute("highScore",highScore);
		//String message = con.getJSP();
		model.addAttribute("score", score);
		
		return "scramble";
	}
	
	public int enterWord(HttpServletRequest request, HttpSession session) {		
		ScrambleDAO scrambleDAO = (ScrambleDAO)appContext.getBean("scrambleDAOImpl");
		Scramble scramble = new Scramble();
		System.out.println(request.getParameter("word"));
		scramble.setLastWord(request.getParameter("word"));
		String username = (String) session.getAttribute("username");
		System.out.println("username is "+username);
		scramble.setUsername(username);
		
		int currScore = scrambleDAO.getCurrentScore(scramble);
		
		scramble.setCurrScore(currScore);
		
		
		int score = scrambleDAO.enterWord(scramble);
		
		System.out.println("after increment score is "+score);
		return score;
	}
	
	@RequestMapping(value = "results", method = RequestMethod.POST)
	public String getResult(HttpServletRequest request, HttpSession session, Model model) throws SQLException {		
		ScrambleController con = (ScrambleController)appContext.getBean("scrambleController");		
		int prevScore = con.getResult(request,session);	
		String score = request.getParameter("score");
		//String message = con.getJSP();
		model.addAttribute("currScore", score);
		model.addAttribute("prevScore", prevScore);
		
		return "scrambleresult";
	}
	
	public int getResult(HttpServletRequest request, HttpSession session) throws SQLException {
		ScrambleDAO scrambleDAO = (ScrambleDAO)appContext.getBean("scrambleDAOImpl");
		UserDAO userDAO = (UserDAO)appContext.getBean("userDAOImpl");
		User user = new User();
		Scramble scramble = new Scramble();
		
		String username = (String) session.getAttribute("username");
		int currScore = Integer.parseInt(request.getParameter("score"));
		scramble.setUsername(username);
		scramble.setCurrScore(currScore);
		
		
		int prevScore = scrambleDAO.getResult(scramble);
		user = userDAO.getUser(username);
		
		session.setAttribute("user", user);
		
		
		
		return prevScore;
		
	}
	}
	
	

