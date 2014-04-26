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
		WhatsYourTechController controller = (WhatsYourTechController)appContext.getBean("WhatsYourTech");		
		controller.createTablePlayers();
		controller.createTableHints();
		return "WhatsYourTech";
	}
	
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
							"hint1 int(11) DEFAULT NULL, " +
							"hint2 int(11) DEFAULT NULL, " +
							"hint3 int(11) DEFAULT NULL, " +
							"difficulty varchar(10) DEFAULT NULL, " +
							"PRIMARY KEY (hints_id), " +
							"UNIQUE KEY hints_id_UNIQUE (hints_id) )";
			stmt.executeUpdate(query);
			logger.info("Table 'hints' created.");
			System.out.println("Table 'hints' created.");
		}
		conn.close();			
	}

}
