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

import DAO.UserDAO;
import Entity.User;

@Controller
public class HomeController {
	
	public DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	ApplicationContext appContext = AppContext.getApplicationContext();
	String uname;
	
	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String home(Model model) throws SQLException{		
		HomeController con = (HomeController)appContext.getBean("homeController");		
		con.createTable();
		return "home";
	}
	
	public void createTable() throws SQLException{
			Connection conn;

			conn = dataSource.getConnection();
			
			DatabaseMetaData meta = conn.getMetaData();
			ResultSet res = meta.getTables(null, null, "users", null);
			if(res.next()){
//				System.out.println("Table 'users' already exists.");
				logger.info("Table 'users' already exists.");
			}
			else{
				Statement stmt = conn.createStatement();
				
				String query =  "CREATE TABLE IF NOT EXISTS users ( " +
						 		"username VARCHAR(45) NOT NULL ," + 
						 		"password VARCHAR(45) NOT NULL ," +
						 		"fname VARCHAR(45) NOT NULL ," +
						 		"lname VARCHAR(45) NOT NULL ," +
						 		"game1_highscore INT NULL DEFAULT 0 ," +					  
						 		"game2_highscore INT NULL DEFAULT 0 ," +
						 		"game3_highscore INT NULL DEFAULT 0 ," +
						 		"game4_highscore INT NULL DEFAULT 0 ," +
						 		"PRIMARY KEY (username) ," +
						 		"UNIQUE INDEX username_UNIQUE (username ASC) )";
				stmt.executeUpdate(query);
				logger.info("Table 'users' created.");
				System.out.println("Table 'users' created.");
			}
			conn.close();			
	}		
	
	@RequestMapping(value = "signin", method = RequestMethod.POST)
	public String getSignIn(HttpServletRequest request, HttpSession session, Model model) {		
		session.setMaxInactiveInterval(300);
		HomeController con = (HomeController)appContext.getBean("homeController");
		String message = con.signIn(request);	
		if(message.substring(0, 7).equals("Success")){
			model.addAttribute("username", request.getParameter("username"));
			uname = request.getParameter("username");			
			return "profile";
		}
		else{
			model.addAttribute("message", message.substring(8));
			return "home";
		}			
	}
	
	public String signIn(HttpServletRequest request) {		
		UserDAO userDAO = (UserDAO)appContext.getBean("userDAOImpl");
		User user = new User();
		
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		
		String result = userDAO.logIn(user);
		return result;
	}
	
	@RequestMapping(value = "signup", method = RequestMethod.GET)
	public String getSignUp(Model model) {		
		model.addAttribute("message", "Welcome");
		return "signup";
	}
	
	@RequestMapping(value = "signup", method = RequestMethod.POST)
	public String getSignUp(HttpServletRequest request, HttpSession session, Model model) {		
		session.setMaxInactiveInterval(300);
		HomeController con = (HomeController)appContext.getBean("homeController");
		String message = con.signUp(request);				
		if(message.substring(0, 7).equals("Success")){
			model.addAttribute("username", request.getParameter("username"));
			uname = request.getParameter("username");
			return "profile";
		}
		else{
			model.addAttribute("message", message.substring(8));
			return "signup";
		}							
	}
	
	public String signUp(HttpServletRequest request) {		
		UserDAO userDAO = (UserDAO)appContext.getBean("userDAOImpl");
		User user = new User();
		
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		user.setFname(request.getParameter("fname"));
		user.setLname(request.getParameter("lname"));
		
		String result = userDAO.register(user);
		return result;
	}
	
	@RequestMapping(value = "signout", method = RequestMethod.GET)
	public String getSignOut(Model model) {		
		model.addAttribute("username",uname);
		return "signout";
	}
	
	@RequestMapping(value = "signout", method = RequestMethod.POST)
	public String getSignOut(HttpServletRequest request, HttpSession session, Model model) {		
		session.setMaxInactiveInterval(300);
		String message = "Logged out successfully";				
		model.addAttribute("message", message);
			return "home";				
	}
	
	@RequestMapping(value = "profile", method = RequestMethod.GET)
	public String getProfile(Model model) {		
		model.addAttribute("username",uname);
		return "profile";
	}
	
}