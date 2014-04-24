package com.sample.team5;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class StateValueAspect {
	
	private static final Logger logger = LoggerFactory.getLogger(StateValueAspect.class);	
	public DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@After("execution(* HomeController.getJSP(..))")
	public void logAfter() throws SQLException {
		System.out.println("@After annotation: Home.jsp Loaded");
		logger.info("@After annotation: Home.jsp Loaded");
		createTable();		
	}
	
	@After("execution(* ScrambleController.getJSP(..))")
	public void scrambleAfter() throws SQLException {
		System.out.println("@After annotation: scramble.jsp Loaded");
		logger.info("@After annotation: scramble.jsp Loaded");
		createScrambleTables();		
	}
	
	
	
//	@Before("execution(* TestController.formImpl(..))")
//	public void logBefore() {
//		log.info("@Before annotation: Processing Request...");
//	}
//	
//	@After("execution(* TestController.getForm(..))")
//	public void logAfterTest() {
//		log.info("@After annotation: Greetings!");
//	}	

	public void createTable() throws SQLException{
		Connection conn;

		conn = dataSource.getConnection();
		
		DatabaseMetaData meta = conn.getMetaData();
		ResultSet res = meta.getTables(null, null, "users", null);
		if(res.next()){
			System.out.println("Table 'users' already exists.");
			logger.info("Table 'users' already exists.");
		}
		else{
			Statement stmt = conn.createStatement();
			
			String query = " CREATE TABLE IF NOT EXISTS users ( " +
					 	"username VARCHAR(45) NOT NULL ," + 
					 	"password VARCHAR(45) NOT NULL ," +
					 	"fname VARCHAR(45) NOT NULL ," +
					 	"lname VARCHAR(45) NOT NULL ," +
					 	"game1_highscore INT NULL DEFAULT 0 ," +					  
					 	"game2_highscore INT NULL DEFAULT 0 ," +
					 	"game3_highscore INT NULL DEFAULT 0 ," +
					 	"game4_highscore INT NULL DEFAULT 0 ," +
					 	"active BIT NOT NULL DEFAULT 0 ," +
					 	"PRIMARY KEY (username) ," +
					 	"UNIQUE INDEX username_UNIQUE (username ASC) )";
			stmt.executeUpdate(query);
			logger.info("Table 'users' created.");
			System.out.println("Table 'users' created.");
		}
		conn.close();			
	}		
	
	
	
	public void createScrambleTables() throws SQLException{
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
			
			String query = "CREATE TABLE scramble (username VARCHAR(15) NOT NULL, prevScore INT NULL, "+
					"currScore INT NULL, lastWord CHAR(15) NULL, PRIMARY KEY (username), CONSTRAINT username " +
					"FOREIGN KEY (username) REFERENCES users (username) ON DELETE CASCADE ON UPDATE CASCADE)";
			
			stmt.executeUpdate(query);
			logger.info("Table 'scramble' created.");
			System.out.println("Table 'scramble' created.");
			
			query = "CREATE TABLE IF NOT EXISTS scramblewords ( Word CHAR(15) NOT NULL,PRIMARY KEY (word))";
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

		
		
		
		
		logger.info("Table scrablewords inserted with values");
		System.out.println("Table scramblewords inserted with values");
				
		conn.close();			
	}		
	
}
