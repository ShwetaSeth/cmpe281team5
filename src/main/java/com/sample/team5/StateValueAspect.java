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
	}
	
	@After("execution(* ScrambleController.createScrambleTable(..))")
	public void scrambleAfter() throws SQLException {
		System.out.println("@After annotation: scramble.jsp Loaded");
		logger.info("@After annotation: scramble.jsp Loaded");

	}
	
	
}
