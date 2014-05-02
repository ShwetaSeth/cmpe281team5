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
		con.createColorsTable();
		con.populateColorsTable();
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
						 		"lname VARCHAR(45) NULL ," +
						 		"bgcolor VARCHAR(45) NULL DEFAULT 'whitesmoke'," +
						 		"topscoreChecked VARCHAR(45) NOT NULL ," +
						 		"favgame VARCHAR(45) NOT NULL," +
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
	
	
	public void createColorsTable() throws SQLException{
		Connection conn;

		conn = dataSource.getConnection();
		
		DatabaseMetaData meta = conn.getMetaData();
		ResultSet res = meta.getTables(null, null, "colors", null);
		if(res.next()){
//			System.out.println("Table 'users' already exists.");
			logger.info("Table 'users' already exists.");
		}
		else{
			Statement stmt = conn.createStatement();
			
			String query =  "CREATE TABLE IF NOT EXISTS colors ( " +
					 		"bcolor VARCHAR(15) NOT NULL ," + 
					 		"PRIMARY KEY (bcolor) )";
			stmt.executeUpdate(query);
			logger.info("Table 'colors' created.");
			System.out.println("Table 'colors' created.");
		}
		conn.close();			
}	
	
	public void populateColorsTable() throws SQLException{
		Connection conn;
		conn = dataSource.getConnection();
		Statement stmt = conn.createStatement();
		String query = "INSERT IGNORE INTO colors (bcolor) VALUES ('whitesmoke')";
		stmt.executeUpdate(query);
		query = "INSERT IGNORE INTO colors (bcolor) VALUES ('honeydew')";
		stmt.executeUpdate(query);
		query = "INSERT IGNORE INTO colors (bcolor) VALUES ('lightyellow')";
		stmt.executeUpdate(query);


		logger.info("Table colors inserted with values");
		System.out.println("Table colors inserted with values");
				
		conn.close();				
	}	

	@RequestMapping(value = "home", method = RequestMethod.POST)
	public String getSignIn(HttpServletRequest request, HttpSession session, Model model) throws SQLException{		
		session.setMaxInactiveInterval(300);
		HomeController con = (HomeController)appContext.getBean("homeController");
		User user = new User();
		UserDAO userDAO = (UserDAO)appContext.getBean("userDAOImpl");
		
		String message = con.signIn(request,session);	
		
		if(message.substring(0, 7).equals("Success")){
			
			model.addAttribute("username", request.getParameter("username"));
			uname = request.getParameter("username");	

			session.setAttribute("username", uname);
			user = userDAO.getUser(uname);
			
			session.setAttribute("color",user.getBgcolor());
			session.setAttribute("favgame", user.getFavgame());
			session.setAttribute("topscoreChecked", user.getTopscoreChecked());
			
			session.setAttribute("user", user);
			
			if(session.getAttribute("user")== null)
				System.out.println("session user is null");
			else
				System.out.println("session user is not null");
			
			
			return "redirect:profile";
		}
		else{
			model.addAttribute("message", message.substring(8));
			return "home";
		}			
	}
	
	
	public String signIn(HttpServletRequest request,HttpSession session) throws SQLException {		
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
	public String getSignUp(HttpServletRequest request, HttpSession session, Model model) throws SQLException {		
		session.setMaxInactiveInterval(300);
		HomeController con = (HomeController)appContext.getBean("homeController");
		String message = con.signUp(request,session,model);				
		if(message.substring(0, 7).equals("Success")){
			
			return "redirect:profile";
		}
		else{
			model.addAttribute("message", message.substring(8));
			return "signup";
		}							
	}
	
	public String signUp(HttpServletRequest request, HttpSession session,Model model) throws SQLException {		
		UserDAO userDAO = (UserDAO)appContext.getBean("userDAOImpl");
		User user = new User();
		String topscoreChecked ;
		
		String topscoreCheckBoxValue = request.getParameter("topscore");
		if (topscoreCheckBoxValue != null)
			topscoreChecked = "true";
		else
			topscoreChecked = "false";
		
		
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		user.setFname(request.getParameter("fname"));
		user.setLname(request.getParameter("lname"));
		user.setBgcolor(request.getParameter("colors"));
		user.setTopscoreChecked(topscoreChecked);
		user.setFavgame(request.getParameter("favgame"));
		
		String result = userDAO.register(user);
		
		String favgame = request.getParameter("favgame");
		String color = request.getParameter("colors");
		uname = request.getParameter("username");
			
		System.out.println(color);
		session.setAttribute("username", uname);
		session.setAttribute("color",color);
		session.setAttribute("favgame", favgame);
		session.setAttribute("topscoreChecked", topscoreChecked);
		session.setAttribute("game1_highscore", 0);
		session.setAttribute("game2_highscore", 0);
		session.setAttribute("game3_highscore", 0);
		session.setAttribute("game4_highscore", 0);
		
		session.setAttribute("user", user);
				
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
	public String getProfile(Model model, HttpSession session) throws SQLException{		
		model.addAttribute("username",uname);
		User user = (User)session.getAttribute("user");
		model.addAttribute("color",user.getBgcolor());
		return "profile";
	}
	
	@RequestMapping(value = "play", method = RequestMethod.GET)
	public String play(HttpServletRequest request, HttpSession session, Model model) throws SQLException {	
		
		String game = request.getParameter("game");
		
		model.addAttribute("username",uname);

		User user = (User)session.getAttribute("user");
		model.addAttribute("color",user.getBgcolor());		
		return "redirect:"+game;
	}
		
	@RequestMapping(value = "editProfile", method = RequestMethod.GET)
	public String editProfile(HttpServletRequest request, HttpSession session, Model model) throws SQLException{	
		
		User user = new User();
		user = (User)session.getAttribute("user");
		if(user== null)
			System.out.println("user is null");
		System.out.println("First name is :"+user.getFname());
		model.addAttribute("user",user);
		return "editProfile";
	}
	
	@RequestMapping(value = "editProfile", method = RequestMethod.POST)
	public String updateProfile(HttpServletRequest request, HttpSession session, Model model) throws SQLException{	
		
		User user = new User();
		UserDAO userDAO = (UserDAO)appContext.getBean("userDAOImpl");
		
		String topscoreChecked ;
	
		String topscoreCheckBoxValue = request.getParameter("topscore");
		if (topscoreCheckBoxValue != null)
			topscoreChecked = "true";
		else
			topscoreChecked = "false";
		
		user.setUsername((String)session.getAttribute("username"));
		user.setPassword(request.getParameter("password"));
		user.setFname(request.getParameter("fname"));
		user.setLname(request.getParameter("lname"));
		user.setBgcolor(request.getParameter("colors"));
		user.setTopscoreChecked(topscoreChecked);
		user.setFavgame(request.getParameter("favgame"));
		
		userDAO.update(user);
		
		String favgame = request.getParameter("favgame");
		String color = request.getParameter("colors");
		uname = request.getParameter("username");
		
		System.out.println(color);
		session.setAttribute("username", uname);
		session.setAttribute("color",color);
		session.setAttribute("favgame", favgame);
		session.setAttribute("topscoreChecked", topscoreChecked);
		
		session.setAttribute("user", user);
			
		return "redirect:profile";
	}
}