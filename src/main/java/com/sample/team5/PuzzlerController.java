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

import DAO.PuzzlerDAO;
import Entity.Puzzler;


@Controller
public class PuzzlerController {

	public DataSource dataSource;

	public DataSource getDataSource(){
			return dataSource;
	}
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(PuzzlerController.class);	
	ApplicationContext appContext = AppContext.getApplicationContext();
	
	@RequestMapping(value = "Puzzler", method = RequestMethod.GET)
	public String Puzzler(HttpServletRequest request,Model model) throws SQLException {	
		PuzzlerController con = (PuzzlerController)appContext.getBean("puzzlerController");		
		PuzzlerDAO puzzlerDAO = (PuzzlerDAO)appContext.getBean("puzzlerDAOImpl");
		
		con.createPuzzlerTable();
		return "Puzzler";
		}
	
	public void createPuzzlerTable() throws SQLException{
		
		Connection conn;

		conn = dataSource.getConnection();
		try{
			
		
		DatabaseMetaData meta = conn.getMetaData();
		ResultSet res = meta.getTables(null, null, "puzzler", null);
		if(res.next()){
			//System.out.println("Table 'puzzler' already exists.");
			logger.info("Table 'puzzler' already exists.");
		}
		else{
			Statement stmt = conn.createStatement();
			
			String query = "CREATE TABLE puzzler (userid INT NULL, username VARCHAR(15) NOT NULL, Score INT NULL, "+
					" Time INT NULL, Moves INT NULL, PRIMARY KEY (userid), CONSTRAINT CONS_USERNAME " +
					"FOREIGN KEY (username) REFERENCES users (username) ON DELETE CASCADE ON UPDATE CASCADE)";
			
			stmt.executeUpdate(query);
			logger.info("Table 'puzzler' created.");
			System.out.println("Table 'puzzler' created.");
									
		}
		}finally{		
		conn.close();
		}
	}	

	//not sure wht needs to be done here..
	@RequestMapping(value = "shuffle", method = RequestMethod.POST)
	public String getGameScore (HttpServletRequest request, HttpSession session, Model model) {		
		
		PuzzlerController con = (PuzzlerController)appContext.getBean("puzzlerController");	
		Puzzler puzzler = new Puzzler();
		//DB Update
		//SCORE cal
		//
		int Score = con.getGameScore(request);
		
		//model.addAttribute("username", request.getParameter("username"));
		model.addAttribute("Score", request.getParameter("Score"));
		return "Puzzler";	
				
	}
	
	public int getGameScore(HttpServletRequest request) {		
		PuzzlerDAO puzzlerDAO = (PuzzlerDAO)appContext.getBean("puzzlerDAOImpl");
		Puzzler puzzler = new Puzzler();
		//puzzler.setTime(Integer.parseInt(request.getParameter("Timer")));// get and set Time 
		System.out.println(request.getParameter("counter1"));
		puzzler.setMoves(Integer.parseInt(request.getParameter("counter1")));//do i directly do a setgame in DAO??
	
		
		int Score=puzzler.getMoves();// or do it via DAO
		
		//int Time = puzzlerDAO.getTime(puzzler);
		//int Moves = puzzlerDAO.getMoves(puzzler);
		//puzzler.setTime(request.getParameter("Time"));//how to insert the time in the db
		//puzzler.setMoves(request.getParameter("counter"));
		return Score;
	}	
	
	@RequestMapping(value = "instructions", method = RequestMethod.GET)
	public String puzzlerinstructions(Model model) throws SQLException{		
		return "instructions";
	}
	
	
}


	

