package com.sample.team5;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WhatsYourTechController {
	
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
	public String home(Model model) throws SQLException{		
		WhatsYourTechController controller = (WhatsYourTechController)appContext.getBean("WhatsYourTech");		
		controller.createTableGames();
		controller.createTableTeams();
		controller.createTableHints();
		return "home";
	}
	
	public void createTableTeams() throws SQLException{
		Connection conn;

		conn = dataSource.getConnection();
		
		DatabaseMetaData meta = conn.getMetaData();
		ResultSet res = meta.getTables(null, null, "teams", null);
		if(res.next()){
			System.out.println("Table 'teams' already exists.");
			logger.info("Table 'teams' already exists.");
		}
		else{
			Statement stmt = conn.createStatement();
			
			String query = " CREATE TABLE IF NOT EXISTS teams (  " +
							"team_id int(11) NOT NULL AUTO_INCREMENT, " +
							"player1 varchar(45) DEFAULT NULL, " +
							"player2 varchar(45) DEFAULT NULL, " +
							"player3 varchar(45) DEFAULT NULL, " +
							"round1 int(11) DEFAULT NULL, " +
							"round2 int(11) DEFAULT NULL, " +
							"round3 int(11) DEFAULT NULL, " +
							"total_score int(11) DEFAULT NULL, " +
							"ready int(11) NOT NULL DEFAULT 0, " +
							"PRIMARY KEY (team_id), " +
							"UNIQUE KEY team_id_UNIQUE (team_id), " +
							"KEY player1_fk_idx (player1), " +
							"KEY player2_fk_idx (player2), " +
							"KEY player3_fk_idx (player3), " +
							"CONSTRAINT player1_fk FOREIGN KEY (player1) REFERENCES users (username) ON DELETE NO ACTION ON UPDATE NO ACTION, " +
							"CONSTRAINT player2_fk FOREIGN KEY (player2) REFERENCES users (username) ON DELETE NO ACTION ON UPDATE NO ACTION, " +
							"CONSTRAINT player3_fk FOREIGN KEY (player3) REFERENCES users (username) ON DELETE NO ACTION ON UPDATE NO ACTION )";
			stmt.executeUpdate(query);
			logger.info("Table 'teams' created.");
			System.out.println("Table 'games' created.");
		}
		conn.close();			
	}
	
	public void createTableGames() throws SQLException {
		Connection conn;

		conn = dataSource.getConnection();
		
		DatabaseMetaData meta = conn.getMetaData();
		ResultSet res = meta.getTables(null, null, "games", null);
		if(res.next()){
			System.out.println("Table 'games' already exists.");
			logger.info("Table 'games' already exists.");
		}
		else{
			Statement stmt = conn.createStatement();
			
			String query = " CREATE TABLE IF NOT EXISTS games (  " +
							"game_id int(11) NOT NULL AUTO_INCREMENT, " +
							"team1 int(11) DEFAULT NULL, " +
							"team2 int(11) DEFAULT NULL, " +
							"PRIMARY KEY (game_id), " +
							"UNIQUE KEY game_id_UNIQUE (game_id), " +
							"KEY team1_fk_idx (team1), " +
							"KEY team2_fk_idx (team2), " +
							"CONSTRAINT team1_fk FOREIGN KEY (team1) REFERENCES teams (team_id) ON DELETE NO ACTION ON UPDATE NO ACTION, " +
							"CONSTRAINT team2_fk FOREIGN KEY (team2) REFERENCES teams (team_id) ON DELETE NO ACTION ON UPDATE NO ACTION )";
			stmt.executeUpdate(query);
			logger.info("Table 'games' created.");
			System.out.println("Table 'games' created.");
		}
		conn.close();			
	}
	
	public void createTableHints() throws SQLException {
		
	}
}
