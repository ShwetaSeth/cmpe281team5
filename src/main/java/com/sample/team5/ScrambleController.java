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

import DAO.ScrambleDAO;
import DAO.UserDAO;
import Entity.Scramble;
import Entity.User;

@Controller
public class ScrambleController {
	private static final Logger logger = LoggerFactory.getLogger(ScrambleController.class);	
	ApplicationContext appContext = AppContext.getApplicationContext();
	
	@RequestMapping(value = "scramble/play", method = RequestMethod.GET)
	public String scramble(Model model) {		
		ScrambleController con = (ScrambleController)appContext.getBean("scrambleController");		
		String message = con.getJSP();
		//model.addAttribute("message", message);
		return "scramble";
	}
	
	public String getJSP(){
		return "Welcome";
	}
	
	
	@RequestMapping(value = "scramble/enterWord", method = RequestMethod.POST)
	public String enterWord(HttpServletRequest request, HttpSession session, Model model) {		
		ScrambleController con = (ScrambleController)appContext.getBean("scrambleController");		
		boolean message = con.enterWord(request);	
		
		System.out.println(message);
		
		if(message)
		{
			System.out.println(message);
			//increment score by 1
		}
		//String message = con.getJSP();
		//model.addAttribute("message", message);
		return "scramble";
	}
	
	public Boolean enterWord(HttpServletRequest request) {		
		ScrambleDAO scrambleDAO = (ScrambleDAO)appContext.getBean("scrambleDAOImpl");
		Scramble scramble = new Scramble();
		System.out.println(request.getParameter("word"));
		scramble.setLastWord(request.getParameter("word"));
		
		boolean result = scrambleDAO.enterWord(scramble);
		return result;
	}
	
	
	/*@RequestMapping(value = "signin", method = RequestMethod.POST)
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
	}*/
		

}
