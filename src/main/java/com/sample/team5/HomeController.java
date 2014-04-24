package com.sample.team5;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import DAO.UserDAO;
import Entity.User;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);	
	ApplicationContext appContext = AppContext.getApplicationContext();
	
	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String home(Model model) {		
		HomeController con = (HomeController)appContext.getBean("homeController");		
		String message = con.getJSP();
		model.addAttribute("message", message);
		return "home";
	}
	
	public String getJSP(){
		return "Welcome";
	}
	
	
	@RequestMapping(value = "signin", method = RequestMethod.POST)
	public String getSignIn(HttpServletRequest request, HttpSession session, Model model) {		
		session.setMaxInactiveInterval(300);
		HomeController con = (HomeController)appContext.getBean("homeController");
		String message = con.signIn(request);	
		if(message.substring(0, 7).equals("Success")){
			model.addAttribute("message", request.getParameter("username"));
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
			model.addAttribute("message", request.getParameter("username"));
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
	public String getSignOut() {		
		return "signout";
	}
	
	@RequestMapping(value = "signout", method = RequestMethod.POST)
	public String getSignOut(HttpServletRequest request, HttpSession session, Model model) {		
		session.setMaxInactiveInterval(300);
		HomeController con = (HomeController)appContext.getBean("homeController");
		String message = con.signOut(request.getParameter("username"), session);				
		model.addAttribute("message", message);
			return "home";				
	}
	
	public String signOut(String username, HttpSession session) {		
		UserDAO userDAO = (UserDAO)appContext.getBean("userDAOImpl");
		User user = new User();
		user.setUsername(username);
		
		String result = userDAO.logOut(user);
		session.invalidate();
		return result;
	}
		
}